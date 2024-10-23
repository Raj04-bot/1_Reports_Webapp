package com.package1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.package1.entity.CitizenPlan;
import com.package1.rqst.SearchRequest;

@Service
public interface ReportService {
	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel();
	
	public boolean exportPdf();

}
