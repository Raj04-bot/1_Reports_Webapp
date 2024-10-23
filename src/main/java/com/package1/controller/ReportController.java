package com.package1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.package1.entity.CitizenPlan;
import com.package1.rqst.SearchRequest;
import com.package1.service.ReportService;



@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@PostMapping("/search")
	public String handleSearchRqst(SearchRequest request,Model model)
	{
		System.out.println(request);
		List<CitizenPlan> plans= service.search(request);
		model.addAttribute("plans",plans);
		
		
		init(model);
		return "page1";
		
	}
	
	@GetMapping("/index")
	public String indexPage(Model model)
	{
		//SearchRequest searchObj = new SearchRequest();
	
		init(model);
		
		return "page1";
	}
	
	private void init(Model model) {
		model.addAttribute("search",new  SearchRequest());
		model.addAttribute("names",service.getPlanNames());
		model.addAttribute("status",service.getPlanStatus());
	}

}
