/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.resources;

import com.barric.nexars.NexarsFacialApp.entities.Citizens;
import com.barric.nexars.NexarsFacialApp.entities.Media;
import com.barric.nexars.NexarsFacialApp.entities.Reports;
import com.barric.nexars.NexarsFacialApp.repositories.CitizensRepo;
import com.barric.nexars.NexarsFacialApp.repositories.MediaRepo;
import com.barric.nexars.NexarsFacialApp.repositories.ReportRepo;
import com.barric.nexars.NexarsFacialApp.util.ReportObject;
import com.barric.nexars.NexarsFacialApp.util.ResponseMessage;
import com.barric.nexars.NexarsFacialApp.util.UtilHelper;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Barima
 */
@RestController
@RequestMapping(value = "/api/reports")
public class ReportResources {

    
    @Autowired
    private ReportRepo repo;

    @Autowired
    private MediaRepo mediaRepo;
    
    @Autowired
    private CitizensRepo citiRepo;

    @PostMapping(value = "/save")
    public ResponseMessage find(@RequestBody final ReportObject reports) {
        Reports re = null;
      //  System.err.println("RE: "+reports);
        try {
            re = new Reports();
            re.setReportTypeId(0);
            re.setAnonimity(false);
            re.setDateCreated(new Date());
            re.setHasMedia(true);
            re.setCitizenId(citiRepo.findById(reports.getUserId()).get());
            re.setMessage(reports.getContent());
            re.setCaption(reports.getPostTitle());
            repo.save(re);
            
            saveMedia(reports.getPostImage(),re);
            return new ResponseMessage(0, "Reported");
        } catch (Exception e) {
            System.err.println("Error "+e);
            e.printStackTrace();
              return new ResponseMessage(-1, "Error while reporting");
        }
        
    }

    @GetMapping(value = "/all")
    public List<Reports> findAll() {
        return repo.findAllReports();
    }

    @GetMapping(value = "/allpost")
    public List<ReportObject> findAllPost() {
        String dir = new File("").getAbsolutePath() + "\\image\\";
        try {
            List<Reports> report = repo.findAllReports();
            List<ReportObject> re = new ArrayList<>();
            for(Reports r : report){
                ReportObject ro = new ReportObject();
                ro.setComment(r.getReportCommentsList().size());
                ro.setContent(r.getMessage());
                ro.setPostTitle(r.getCaption());
                Citizens c = r.getCitizenId();
                ro.setUsername(c.getFirstname() + " " + c.getLastname());
                ro.setUserId(c.getId());
                String img = "";
                if (c.getMediaId() != null) {
                    img = c.getMediaId().getUrl();
                }
                img = UtilHelper.base64Encoder(dir + img);
                ro.setUserImage(img);
                if (mediaRepo.findByReportId(r.getId()) !=null) {
                    Media me = mediaRepo.findByReportId(r.getId());
                    ro.setPostImage(UtilHelper.base64Encoder(dir + me.getUrl()));
                } else {
                    ro.setPostImage(UtilHelper.base64Encoder(""));
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(r.getDateCreated());
                ro.setDate(date);
                re.add(ro);
                
            };
            
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public boolean saveMedia(String image, Reports postId){
          String dir = new File("").getAbsolutePath() + "\\image\\";
        try {
            String imageName = Calendar.getInstance().getTimeInMillis() + "image.png";
            String filePath = dir + imageName;

            if (!new File(dir).exists()) {
                new File(dir).mkdir();
            }
            System.out.println(filePath);
            //   UtilHelper.saveFile(file, filePath);
            UtilHelper.saveBase(image, filePath);
           
            Media m   = new Media();
                m.setIsCensored(false);
                m.setUrl(imageName);
                m.setMediaType("png");
                m.setReportId(postId);
                this.mediaRepo.save(m); 

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
return true;
    }
}
