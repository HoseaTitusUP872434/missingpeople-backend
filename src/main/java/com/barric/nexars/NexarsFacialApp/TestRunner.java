/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp;

/**
 *
 * @author Barima
 */
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestRunner {
	
	//@Autowired
	//private TopicService topicService;
	
	@RequestMapping("/call")
	@ResponseBody
	public String callMain() {
		return "Call this from spring netbeans";
		
	}

}