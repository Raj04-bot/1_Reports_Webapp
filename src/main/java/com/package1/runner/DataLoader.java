package com.package1.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.slf4j.helpers.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.support.xml.Jdbc4SqlXmlHandler;
import org.springframework.stereotype.Component;

import com.package1.entity.CitizenPlan;
import com.package1.repo.CitizenPlanRepo;

import jakarta.transaction.Transactional;

@Component// implements runner class auto matically

public class DataLoader implements ApplicationRunner{
	
	@Autowired
	private CitizenPlanRepo citizenPlanRepo;
	
	public void run(ApplicationArguments args) throws Exception
	{
		citizenPlanRepo.deleteAll();
		CitizenPlan c1 = new CitizenPlan();
		
		//cash plan Approved
		
		c1.setCitizenName("Raj");
		c1.setGender("male");
		c1.setPlanName("cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenifitAmount(5000.00);
		
		// cash Plan deined
		
		CitizenPlan c2 = new CitizenPlan();		
		c2.setCitizenName("Ram");
		c2.setGender("male");
		c2.setPlanName("cash");
		c2.setPlanStatus("denied");
		c2.setDenialReason("govt service");
		
		// food plan data
		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Anisha ");
		c3.setGender("Female");
		c3.setPlanName("Food");
		c3.setPlanStatus("Approved");
		c3.setPlanStartDate(LocalDate.now());
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenifitAmount(4000.00);
		
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("Anjaly");
		c4.setGender("female");
		c4.setPlanName("cash");
		c4.setPlanStatus("Terminated");
		c4.setPlanStartDate(LocalDate.now().minusMonths(4));
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setTerminationDate(LocalDate.now());
		c4.setTerminationReason("Employed");
		
		CitizenPlan c5 = new CitizenPlan();		
		c5.setCitizenName("Ram");
		c5.setGender("male");
		c5.setPlanName("food");
		c5.setPlanStatus("denied");
		c5.setDenialReason("rental income");	
		
		List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5); 
		citizenPlanRepo.saveAll(list);
		
	}

}
