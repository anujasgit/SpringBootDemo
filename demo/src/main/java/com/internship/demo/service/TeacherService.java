package com.internship.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.internship.demo.model.Teacher;
import com.internship.demo.repository.TeacherRepository;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class TeacherService {
	
	@Value("${email.host-smtp}")
	private String hostSmtp;
	
	@Value("${email.host-port}")
	private String hostPort;
	
	@Value("${email.username}")
	private String username;
	
	@Value("${email.password}")
	private String password;
	
	
	@Autowired
	TeacherRepository teacherRepository;
	
	public Teacher addTeacher(Teacher teacher) 
	{
		Teacher savedTeacher = teacherRepository.save(teacher);
		return savedTeacher;
	}

	public List<Teacher> addTeacherInBulk(List<Teacher> teacherList) 
	{
		List<Teacher> savedList = teacherRepository.saveAll(teacherList);
		return savedList;
	}

	public List<Teacher> getTeachers() 
	{
		List<Teacher> allTeachers = teacherRepository.findAll();
		return allTeachers;
	}

	public Teacher getTeacherById(int id) {
		if(teacherRepository.findById(id).get().equals(null)) 
		{
			Teacher teacherById = null;
			return teacherById;
		}
		else
		{
			Teacher teacherById = teacherRepository.findById(id).get();
			return teacherById;
		}
//		Optional<Teacher> teacherById = teacherRepository.findById(id);
		
	}
	
	public Teacher getTeacherByName(String name) {
		Teacher teacherByName = teacherRepository.findByName(name);
		return teacherByName;
	}
	
	public List<Teacher> getTeacherByAddress(String address) {
		List<Teacher> teacherByAddress = teacherRepository.findByAddress(address);
		return teacherByAddress;
	}

	public void deleteTeachers() {
		teacherRepository.deleteAll();
	}

	public void deleteById(int id) {
		teacherRepository.deleteById(id);
	}

	public Teacher updateTeacher(Teacher teacher) {
		Teacher updatedTeacher = teacherRepository.save(teacher);
		return updatedTeacher;
	}

	public Teacher updateTeacherById(Teacher teacher, int id)
	{
		teacher.setId(id);
		Teacher updatedTeacher = teacherRepository.save(teacher);
		return updatedTeacher;
	}

	public List<Teacher> getTeacherByNameInitials(String name)
	{
//		List<Teacher> teacherByNameInitials = teacherRepository.findNameByNameInitials(name);
		List<Teacher> teacherByNameInitials = teacherRepository.findByNameContaining(name);
		return teacherByNameInitials;
	}
	
	public void sendEmail() throws MessagingException
	{
		String subject = "Subject";
		String message = "Mail received.";
		String to = "janakibastakoti@gmail.com";
		String from = "anujasmails@gmail.com";
		
		sendMessage(subject, message, to, from);
//		sendMessageMultipart(subject, message, to, from);
	}
	
	private void sendMessage(String subject, String message, String to, String from) throws AddressException, MessagingException 
	{
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", true);
		properties.put("mail.smtp.auth", true);
		
		Session session = Session.getInstance(properties, new Authenticator() 
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "thestateofgrace");
			}
		});
		
		MimeMessage mime = new MimeMessage(session);
		mime.setFrom(from);
		mime.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		mime.setSubject(subject);
		mime.setText(message);
		
		String msg = "<h1><p>" + "Hello" + "</p></h1>";
		mime.setContent(msg, "text/html");
		
		Transport.send(mime);
		
		System.out.println("Mail sent"); 
	}

	private void sendMessageMultipart(String subject, String message, String to, String from) throws AddressException, MessagingException 
	{
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", true);
		properties.put("mail.smtp.auth", true);
		
		Session session = Session.getInstance(properties, new Authenticator() 
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "thestateofgrace");
			}
		});
		
		MimeMessage mime = new MimeMessage(session);
		mime.setFrom(from);
		mime.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		mime.setSubject(subject);
		
		MimeMultipart mimeMultipart = new MimeMultipart();
		
		MimeBodyPart text = new MimeBodyPart();
		text.setText("Mail received with an attachment.");
		
		MimeBodyPart attachment = new MimeBodyPart();
		try 
		{
			attachment.attachFile("C:\\Users\\dell\\Documents\\Test.txt");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		mimeMultipart.addBodyPart(text);
		mimeMultipart.addBodyPart(attachment);
		
		mime.setContent(mimeMultipart);
		
		Transport.send(mime);
		
		System.out.println("Mail sent with an attachment.");
	}

	public Page<Teacher> getTeachersByPaging(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 2, Sort.by("name").ascending());
		Page page = teacherRepository.findAll(pageable);
		return page;
	}
	
	
//	public void addTeacher(Teacher teacher) 
//	{
//		teacherRepository.save(teacher);
//	}
}
