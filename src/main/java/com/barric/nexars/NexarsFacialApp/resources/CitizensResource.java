/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.resources;
import com.barric.nexars.NexarsFacialApp.entities.Citizens;
import com.barric.nexars.NexarsFacialApp.entities.Media;
import com.barric.nexars.NexarsFacialApp.repositories.CitizensRepo;
import com.barric.nexars.NexarsFacialApp.repositories.MediaRepo;
import com.barric.nexars.NexarsFacialApp.util.FileUploads;
import com.barric.nexars.NexarsFacialApp.util.ResponseMessage;
import com.barric.nexars.NexarsFacialApp.util.UserLogin;
import com.barric.nexars.NexarsFacialApp.util.UtilHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sun.jersey.multipart.FormDataParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Barima
 */
@RestController
@RequestMapping(value = "/api/citizens")
public class CitizensResource {

    private Map map;
    @Autowired
    private CitizensRepo repo;

    @Autowired
    private MediaRepo mediaRepo;

    @GetMapping(value = "/byemail/{email}")
    public Citizens find(@PathVariable("email") String email) {
        Citizens citi = repo.findByEmail(email);

        citi.setPassword("");
        return citi;

    }

    @PostMapping(value = "/login")
    public UserLogin find(@RequestBody final UserLogin login) {
        Citizens citi = repo.findByEmailAndPassword(login.getEmail(), login.getPassword());
        UserLogin nL = new UserLogin();
        if (citi != null) {
            nL.setCode(0);
            citi.setPassword("");
            nL.setCitizen(citi);
        } else {
            nL.setCode(-1);
        }
        return nL;
    }

    @GetMapping(value = "/all")
    public List<Citizens> findAll() {
        return repo.findAll();
    }

    @PostMapping(value = "/save")
    public ResponseMessage save(@RequestBody final Citizens citizens) {
        try {
//            System.out.println(repo.findByEmail(citizens.getEmail()));
            if (repo.findByEmail(citizens.getEmail()) != null) {
                return new ResponseMessage(1, "Email already exist, please login if is yours");
            }
            if (repo.findByNin(citizens.getNin()) != null) {
                return new ResponseMessage(2, "NIN already exist,please make use of your NIN");
            }
            repo.save(citizens);

            return new ResponseMessage(0, "Regigistration Successful, please login to continue");
        } catch (Exception e) {
            return new ResponseMessage(-1, "Error occured while registering");
        }

    }

    @PostMapping("/upload") // //new annotation since 4.3
    public ResponseMessage singleFileUpload(@RequestBody final FileUploads file) {
        //System.out.println("id: "+id);
        // System.out.println("File: "+file);
       // String dir = UtilHelper.IMAGEPATH + "image\\";
        try {
           final String imageName = Calendar.getInstance().getTimeInMillis() + "image.png";

            UtilHelper.saveBase(file.getFile(), imageName);
            Citizens citi = repo.findById(file.getId()).get();
            Media m = null;
            if (citi.getMediaId() != null) {
                m = citi.getMediaId();
                File f = new File(UtilHelper.IMAGEPATH + m.getUrl());
                if (f.exists()) {
                    f.delete();
                }
                m.setUrl(imageName);
                mediaRepo.save(m);
            } else {

                m = new Media();
                m.setIsCensored(false);
                m.setUrl(imageName);
                m.setMediaType("png");
                this.mediaRepo.save(m);
            }

            citi.setMediaId(m);
            repo.save(citi);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseMessage(0, "File uploaded");
    }

    @PostMapping("/upload2") // //new annotation since 4.3
    public String singleFileUpload2(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        String dir = new File("").getAbsolutePath() + "/image/";
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(dir + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping(value = "/profileimage/{id}")
    public ResponseMessage findMessage(@PathVariable("id") int id) {
        Citizens c = repo.findById(id).get();
        Media m = c.getMediaId();
        if (m != null) {
            String dir = new File("").getAbsolutePath() + "\\image\\" + m.getUrl();
            return new ResponseMessage(0, UtilHelper.base64Encoder(dir));
        } else {
            return new ResponseMessage(0, UtilHelper.base64Encoder(""));
        }

    }

}
