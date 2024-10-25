package com.package1.util;

import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
 
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendEmail(String subject, String body, String to)
	{
		try {
			
			MimeMessage mimeMessage= javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage); 
			helper.setSubject(subject);
			helper.setText(body,true);
			helper.setTo("dasrajranjan96@outlook.com");
			javaMailSender .send(mimeMessage  ); 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();	
		}
		return true;
	}
}
