/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.resources;

import com.barric.nexars.NexarsFacialApp.entities.Citizens;
import com.barric.nexars.NexarsFacialApp.entities.FacialFaceIds;
import com.barric.nexars.NexarsFacialApp.entities.Media;
import com.barric.nexars.NexarsFacialApp.entities.Reports;
import com.barric.nexars.NexarsFacialApp.facialrecognition.CreateFacesImpl;
import com.barric.nexars.NexarsFacialApp.facialrecognition.FacialInterface;
import com.barric.nexars.NexarsFacialApp.facialrecognition.FacialRecognitionApiCall;
import com.barric.nexars.NexarsFacialApp.facialrecognition.RecognitionInterface;
import com.barric.nexars.NexarsFacialApp.repositories.CitizensRepo;
import com.barric.nexars.NexarsFacialApp.repositories.FacialIdsRepo;
import com.barric.nexars.NexarsFacialApp.repositories.MediaRepo;
import com.barric.nexars.NexarsFacialApp.repositories.ReportRepo;
import com.barric.nexars.NexarsFacialApp.util.ReportObject;
import com.barric.nexars.NexarsFacialApp.util.ResponseMessage;
import com.barric.nexars.NexarsFacialApp.util.UtilHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
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
    private FacialIdsRepo faceRepo;

    @Autowired
    private MediaRepo mediaRepo;

    @Autowired
    private CitizensRepo citiRepo;

    @PostMapping(value = "/save")
    public ResponseMessage save(@RequestBody final ReportObject reports) {
        FacialInterface inter = new CreateFacesImpl();

        Reports re = null;
        // System.err.println("RE: "+reports);
        try {
            Citizens citi = citiRepo.findById(reports.getUserId()).get();
            re = new Reports();
            re.setReportTypeId(0);
            re.setAnonimity(false);
            re.setDateCreated(new Date());
            re.setHasMedia(true);
            re.setCitizenId(citi);
            re.setMessage(reports.getContent());
            re.setCaption(reports.getPostTitle());
            repo.save(re);

            Media imgURI = saveMedia(reports.getPostImage(), re);
            String personId = inter.createGroupPerson(citi.getEmail(), reports.getPostTitle());
            String imU = UtilHelper.IMAGEURL + imgURI.getUrl();
            String persisId = inter.addFace(personId, imU);
            FacialFaceIds face = new FacialFaceIds();
            face.setDateCreated(new Date());
            face.setPersistedFaceId(persisId);
            face.setPersonId(personId);
            face.setMediaId(imgURI);
            faceRepo.save(face);
            System.out.println("PERSONID: " + personId + " IMAGEURL: " + imU + " PERSISTID: " + persisId);

            return new ResponseMessage(0, "Reported");
        } catch (Exception e) {
            System.err.println("Error " + e);
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
        //  String dir = new File("").getAbsolutePath() + "\\image\\";
        System.err.println("Call first");
        try {
            List<Reports> report = repo.findAllReports();

            List<ReportObject> re = new ArrayList<>();
            report.stream().map((r) -> {
                ReportObject ro = new ReportObject();
                ro.setId(r.getId());
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
                img = UtilHelper.base64Encoder(UtilHelper.IMAGEPATH + img);
                ro.setUserImage(img);
                //     ro.setUserImage(UtilHelper.IMAGEURL+c.getMediaId().getUrl());
                if (mediaRepo.findByReportId(r.getId()) != null) {
                    Media me = mediaRepo.findByReportId(r.getId());
                    ro.setPostImage(UtilHelper.base64Encoder(UtilHelper.IMAGEPATH + me.getUrl()));
                    System.out.println(UtilHelper.IMAGEPATH + me.getUrl());
                    //  ro.setPostImage(UtilHelper.IMAGEURL+me.getUrl());
                } else {
                    ro.setPostImage("");
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(r.getDateCreated());
                ro.setDate(date);
                return ro;
            }).forEachOrdered((ro) -> {
                re.add(ro);
            });
            ;
            System.err.println("Call end....");
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Media saveMedia(String image, Reports postId) {
        // String dir = new File("").getAbsolutePath() + "\\image\\";
        final String imageName = postId.getId() + Calendar.getInstance().getTimeInMillis() + "image.png";
        try {

            //   UtilHelper.saveFile(file, filePath);
            UtilHelper.saveBase(image, imageName);

            Media m = new Media();
            m.setIsCensored(false);
            m.setUrl(imageName);
            m.setMediaType("png");
            m.setReportId(postId);
            this.mediaRepo.save(m);
            return m;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @PostMapping(value = "/searchface")
    public List<ReportObject> searchByFace(@RequestBody final ReportObject reports) {
        try {
            FacialInterface inter = new CreateFacesImpl();
            Citizens citi = citiRepo.findById(reports.getUserId()).get();
            Reports re = new Reports();
            Media imgURI = saveMedia(reports.getPostImage(), re);
            String personId = inter.createGroupPerson(citi.getEmail(), reports.getPostTitle());
            String imU = UtilHelper.IMAGEURL + imgURI.getUrl();
            String persisId = inter.addFace(personId, imU);
            FacialFaceIds face = new FacialFaceIds();
            face.setDateCreated(new Date());
            face.setPersistedFaceId(persisId);
            face.setPersonId(personId);
            face.setMediaId(imgURI);
            faceRepo.save(face);
            System.out.println("PERSONID: " + personId + " IMAGEURL: " + imU + " PERSISTID: " + persisId);
            RecognitionInterface recog = new FacialRecognitionApiCall();
            recog.trainFaces();
            Thread.sleep(5000);
          String res =   recog.detectFaces(persisId);
           JSONArray ja = new JSONArray(res);
          System.err.println("JN: "+ja);
          List<ReportObject> reportObj = new ArrayList<>();
         ja.forEach(e->{
         
          JSONObject can = new JSONObject(e.toString());
           System.err.println("FALL: : "+can.getJSONArray("candidates"));
           System.err.println("ENT: "+can.getString("faceId"));
         JSONArray canArray =  can.getJSONArray("candidates");
         canArray.forEach(a->{
         JSONObject resOb = new JSONObject(a.toString());
         String jpersonId = resOb.getString("personId");
         Double confidence = resOb.getDouble("confidence");
             System.err.println("PERSONID: "+jpersonId+" confidence: "+confidence*100);
             FacialFaceIds f = faceRepo.findByPersonIdOrPersist(jpersonId,jpersonId);
             if(f!=null){
             Media m = f.getMediaId();
             Reports r = f.getMediaId().getReportId();
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
                img = UtilHelper.base64Encoder(UtilHelper.IMAGEPATH + img);
                ro.setUserImage(img);
                //     ro.setUserImage(UtilHelper.IMAGEURL+c.getMediaId().getUrl());
                if (mediaRepo.findByReportId(r.getId()) != null) {
                    Media me = mediaRepo.findByReportId(r.getId());
                    ro.setPostImage(UtilHelper.base64Encoder(UtilHelper.IMAGEPATH + me.getUrl()));
                    System.out.println(UtilHelper.IMAGEPATH + me.getUrl());
                    //  ro.setPostImage(UtilHelper.IMAGEURL+me.getUrl());
                } else {
                    ro.setPostImage("");
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(r.getDateCreated());
                ro.setDate(date);
                reportObj.add(ro);
             }
         
         });
         });
         return reportObj;
        } catch (Exception e) {

        }
        
        return null;
    }
}
