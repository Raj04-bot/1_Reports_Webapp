package com.package1.rqst;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchRequest {
	
	private String planName;
	private String planStatus;
	private String gender;
	//@DateTimeFormat(pattern = "dd-mm-yyyy")
	private String startDate;
	
//	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private String endDate;
	
	
	

}
