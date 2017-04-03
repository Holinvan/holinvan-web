package com.holinvan.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.holinvan.web.model.User;


@Service
public class EmailService {
	
	private static Logger LOG = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	TemplateEngine emailTemplateEngine;

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	ResourceBundleMessageSource emailMessageSource;

	public void sendConfirmationMail(User user, Locale locale, String appUrl)
			throws MessagingException {

		final Context ctx = new Context(locale);
//		ctx.setVariable("name", user.getUserData().getName()); //Si en "registro" se envía nombre.
		ctx.setVariable("subscriptionDate", new Date());
		String url = appUrl + "/hello/"+user.getSecurityCode();
		ctx.setVariable("url", url);
		ctx.setVariable("imageResourceName", "logo.png"); 

		final String htmlContent = emailTemplateEngine.process("html/registration-mail", ctx);

		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject(emailMessageSource.getMessage("ForEmail.subject", null, locale));
		message.setFrom("Holinvan <hello@holinvan.com>");
		message.setTo(user.getUsername());
		message.setText(htmlContent, true);
		
		// Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
		byte[] image = getLogo("/logo.png");
		if(image != null){
			message.addInline("logo.png", new ByteArrayResource(image), "image/png");
		}
		

		mailSender.send(mimeMessage);
	}
	
	private byte[] getLogo(String fileName){
		byte[] image;
		try {
			InputStream is = this.getClass().getResourceAsStream(fileName);
			image = IOUtils.toByteArray(is);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			return null;
		}
		return image;
	}
	

	public void sendChangedPassMail(String email, Locale locale) throws MessagingException {
		
		final Context ctx = new Context(locale);
		ctx.setVariable("subscriptionDate", new Date());
				
		final String htmlContent = emailTemplateEngine.process("html/changed-pass-mail", ctx);
		
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		final MimeMessageHelper message =
				new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject("Caravaning: Se ha modificado su contraseña");
		message.setFrom("caravaning.tgn@gmail.com");
		message.setTo(email);

		message.setText(htmlContent, true);

		mailSender.send(mimeMessage);
		
	}

	
	
}
