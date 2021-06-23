package com.internship.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.demo.model.Teacher;
import com.internship.demo.repository.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {
	
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
		Teacher teacherById = teacherRepository.findById(id).get();
//		Optional<Teacher> teacherById = teacherRepository.findById(id);
		return teacherById;
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

	public Teacher updateTeacherById(Teacher teacher, int id) {
		teacher.setId(id);
		Teacher updatedTeacher = teacherRepository.save(teacher);
		return updatedTeacher;
	}
	
	
//	public void addTeacher(Teacher teacher) 
//	{
//		teacherRepository.save(teacher);
//	}
}
