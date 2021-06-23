package com.internship.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.demo.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> 
{

	Teacher findByName(String name);

	List<Teacher> findByAddress(String address);
	
}
