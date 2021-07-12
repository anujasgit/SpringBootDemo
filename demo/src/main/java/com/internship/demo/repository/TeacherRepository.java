package com.internship.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.internship.demo.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> 
{

	Teacher findByName(String name);

	List<Teacher> findByAddress(String address);
	
	List<Teacher> findByNameContaining(String name);
	
	@Query("Select t from Teacher t where t.name like :name%")
	List<Teacher> findNameByNameInitials(@Param(value = "name") String name);
	
}
