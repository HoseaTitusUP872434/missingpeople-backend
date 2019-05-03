/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.resources;

import com.barric.nexars.NexarsFacialApp.entities.ReportComments;
import com.barric.nexars.NexarsFacialApp.repositories.CommentRepo;
import com.barric.nexars.NexarsFacialApp.util.ResponseMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Barima
 */
@RestController
@RequestMapping(value = "/api/comment")
public class CommentResource {

    private Map map;
    @Autowired
    private CommentRepo repo;

    @PostMapping(value = "/save")
    public ResponseMessage save(@RequestBody final ReportComments comment) {
        try {
            comment.setDateCreated(new Date());
            repo.save(comment);
            return new ResponseMessage(0, "comment successful");
        } catch (Exception e) {
            return new ResponseMessage(-1, "Error occured while commenting");
        }
    }

    @GetMapping(value = "/byreportid/{id}")
    public List<Map> reportComment(@PathVariable("id") int id) {
        List<Map> lis = new ArrayList<>();
        this.repo.findAllByReport(id).forEach((e) -> {
            Map mm = new HashMap();
            mm.put("personId", e.getCitizenId().getId());
            mm.put("comment", e.getComment());
            mm.put("dateCreated", e.getDateCreated());
            mm.put("reportId", e.getReportId().getId());
            mm.put("name", e.getCitizenId().getFirstname()+" "+ e.getCitizenId().getLastname());
            mm.put("personImage", e.getCitizenId().getMediaId().getUrl());
            lis.add(mm);
        });
        return lis;
    }

}
