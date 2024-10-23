package com.package1.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.package1.entity.CitizenPlan;
import com.package1.repo.CitizenPlanRepo;
import com.package1.rqst.SearchRequest;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private CitizenPlanRepo citizenPlanRepo;

	@Override
	public List<String> getPlanNames() {
		List<String> planNames = citizenPlanRepo.getPalnNames();
		return planNames;
	}

	@Override
	public List<String> getPlanStatus() {
		return citizenPlanRepo.getPlanStatus();
		// above and this one both are same
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		
		if(null!= request.getPlanName() && !" ".equals(request.getPlanName()))
		{
			entity.setPlanName(request.getPlanName());
		}
		
		if(null!= request.getPlanStatus() && !" ".equals(request.getPlanStatus()))
		{
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if (null!= request.getGender() && ! " ".equals(request.getGender()));
		{
			entity .setGender(request.getGender());
		}
		
		return citizenPlanRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}
