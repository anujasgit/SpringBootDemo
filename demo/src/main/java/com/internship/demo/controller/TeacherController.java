package com.internship.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internship.demo.exception.ApiRequestException;
import com.internship.demo.model.Teacher;
import com.internship.demo.service.TeacherService;

import java.util.List;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@PostMapping("/add")
	public Teacher addTeacher(@RequestBody Teacher teacher) 
	{
		Teacher savedTeacher = teacherService.addTeacher(teacher);
		return savedTeacher;
	}
	
	@PostMapping("/addInBulk")
	public List<Teacher> addTeacherInBulk(@RequestBody List<Teacher> teacherList) 
	{
		List<Teacher> savedList = teacherService.addTeacherInBulk(teacherList);
		return savedList;
	}
	
	@GetMapping("/getTeachers")
	public List<Teacher> getTeachers() 
	{
		throw new ApiRequestException("An error occurred. CUSTOM EXCEPTION HANDLING SUCCESSFUL.");
//		List<Teacher> allTeachers = teacherService.getTeachers();
//		return allTeachers;
	}
	
	@GetMapping("/getTeachersByPaging/{pageNumber}")
	public Page<Teacher> getTeachersByPaging(@PathVariable int pageNumber) 
	{
		Page<Teacher> allTeachers = teacherService.getTeachersByPaging(pageNumber);
		return allTeachers;
	}
	
	@GetMapping("getTeacherById/{id}")
	public Teacher getTeacherById(@PathVariable int id)
	{
		Teacher teacherByIdTeacher = teacherService.getTeacherById(id);
		return teacherByIdTeacher;
	}
	
	@GetMapping("getTeacherByName/{name}")
	public Teacher getTeacherByName(@PathVariable String name)
	{
		Teacher teacherByIdTeacher = teacherService.getTeacherByName(name);
		return teacherByIdTeacher;
	}
	
	@GetMapping("getTeacherByAddress/{address}")
	public List<Teacher> getTeacherByAddress(@PathVariable String address)
	{
		List<Teacher> teacherByIdTeacher = teacherService.getTeacherByAddress(address);
		return teacherByIdTeacher;
	}
	
	@DeleteMapping("/deleteTeachers")
	public void deleteTeachers()
	{
		teacherService.deleteTeachers();
	}
	
	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable int id) 
	{
		teacherService.deleteById(id);
	}
	
	@PutMapping("/updateTeacher")
	public Teacher updateTeacher(@RequestBody Teacher teacher) 
	{
		Teacher updatedTeacher = teacherService.updateTeacher(teacher);
		return updatedTeacher;
	}
	
	@PutMapping("/updateTeacherById/{id}")
	public Teacher updateTeacherById(@RequestBody Teacher teacher, @PathVariable int id) 
	{
		Teacher updatedTeacher = teacherService.updateTeacherById(teacher, id);
		return updatedTeacher;
	}
	
	@GetMapping("search/{name}")
	public List<Teacher> getTeacherByNameInitials(@PathVariable String name)
	{
		List<Teacher> teacherByNameInitials = teacherService.getTeacherByNameInitials(name);
		return teacherByNameInitials;
	}
	
	@PostMapping("/sendEmail")
	public void sendEmail() throws MessagingException
	{
		teacherService.sendEmail();
	}
	

//	@PostMapping("/add")
//	public void addTeacher(@RequestBody Teacher teacher) 
//	{
//		teacherService.addTeacher(teacher);
//	}
}
