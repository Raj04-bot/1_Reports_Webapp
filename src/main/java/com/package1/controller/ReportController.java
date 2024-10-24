package com.package1.controller;

import java.util.List;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.package1.entity.CitizenPlan;
import com.package1.rqst.SearchRequest;
import com.package1.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;



@Controller
public class ReportController {
    
    @Autowired
    private ReportService service;
    
    @GetMapping("/excel")
    public void excelExport(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        
        // Correcting the header for file download
        response.setHeader("Content-Disposition", "attachment;filename=plans.xls");
        
        // Assuming service.exportExcel(response) writes the Excel data to the response output stream
        service.exportExcel(response);
    }
    
    @GetMapping("/pdf")
    public void pdfExport(HttpServletResponse response) throws Exception{
    	response.setContentType("application/pdf");
    	response.setHeader("Content-Disposition", "attachment;filename=plans.pdf");
    	service.exportPdf(response);
    }

    
    @PostMapping("/search")
    public String handleSearchRqst(SearchRequest request, Model model) {
        // Add the request object to the model
        model.addAttribute("search", request);
        
        List<CitizenPlan> plans = service.search(request);
        model.addAttribute("plans", plans);
        
        init(model);
        return "page1";
    }
    
    @GetMapping("/index")
    public String indexPage(Model model) {
        // Add a new SearchRequest object to the model
        SearchRequest searchObj = new SearchRequest();
        model.addAttribute("search", searchObj);
        
        init(model);
        return "page1";
    }
    
    private void init(Model model) {
        // Ensure names and status are populated in the model
        model.addAttribute("names", service.getPlanNames());
        model.addAttribute("status", service.getPlanStatus());
    }
}
