package com.package1.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.package1.entity.CitizenPlan;
import com.package1.repo.CitizenPlanRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
//	
//	@Autowired
//	CitizenPlanRepo citizenPlanRepo;
	
	public void generate(HttpServletResponse response,List<CitizenPlan> records) throws Exception
	{
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet=workbook.createSheet("plans_Data");
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("citizenName");
		headerRow.createCell(2).setCellValue("gender");
		headerRow.createCell(3).setCellValue("plan Name");
		headerRow.createCell(4).setCellValue("plan Status");
		headerRow.createCell(5).setCellValue("plan Start Date");
		headerRow.createCell(6).setCellValue("Plan End Date");
		
		
		
		int dataRowIndex = 1;
		
		for (CitizenPlan plan : records)
		{
			Row dataRow= sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenName());
			dataRow.createCell(1).setCellValue(plan.getCitizenId());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanStartDate());
			dataRow.createCell(4).setCellValue(plan.getPlanEndDate());
			dataRow.createCell(5).setCellValue(plan.getTerminationDate());
			dataRow.createCell(6).setCellValue(plan.getTerminationReason());
			
			dataRowIndex++;
		}
			
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
	}

}
