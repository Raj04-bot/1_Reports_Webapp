package com.package1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.package1.entity.CitizenPlan;
import com.package1.repo.CitizenPlanRepo;
import com.package1.rqst.SearchRequest;
import com.package1.util.EmailUtils;
import com.package1.util.ExcelGenerator;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private CitizenPlanRepo citizenPlanRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private EmailUtils email;
	
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
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plans=citizenPlanRepo.findAll();
		excelGenerator.generate(response, plans);
		String subject = "Test mail";
		String body ="<h1>Test mail Strring</h1>";
		String to ="dasrajranjan96@outlook.com";
		
		email.sendEmail( subject, body, to);
		
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response)throws Exception {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Paragraph p = new Paragraph("citizen Plan Info");
		document.add(p);
		
		PdfPTable table = new PdfPTable(6);
		
		table.addCell("Id");
		table.addCell("citizen Name");
		table.addCell("plan Status");
		table.addCell("start date");
		table.addCell("plan end Date");
		table.addCell("plan Name");
		
		  List<CitizenPlan> plans=citizenPlanRepo.findAll();
		  
		  for (CitizenPlan plan :plans)
		  {
			  table.addCell(String.valueOf(plan.getCitizenId()));
			  table.addCell(plan.getCitizenName());
			  table.addCell(plan.getPlanStatus());
			  table.addCell(plan.getPlanStartDate()+"");
			  table.addCell(plan.getPlanEndDate()+"");
			  table.addCell(plan.getPlanName());
		  }
		
		
		
		document.add(table);
		document.close();
		
		return true;
	}

	

	




}
