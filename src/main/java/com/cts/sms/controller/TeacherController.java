package com.cts.sms.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.sms.dto.TeacherDto;
import com.cts.sms.entity.Teacher;
import com.cts.sms.exception.TeacherNotFoundException;
import com.cts.sms.service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	private static final Logger log = LoggerFactory.getLogger(TeacherController.class);
	
//	using this we can find the list of all the teachers 
	
	@GetMapping("/")
	public List<Teacher> findAllTeacher() throws TeacherNotFoundException
	{
		List<Teacher> teacherList= teacherService.findAllTeachers();
		if(teacherList.isEmpty())
		{
			log.info("Teacher list is empty");
			throw new TeacherNotFoundException("Teacher List is Empty");
		}
		log.info("Teacher List Is empty");
		return teacherList;
	}
	
//	using this method we are able to add new teacher 
	
	@PostMapping("/")
	public ResponseEntity<Teacher> saveTeacher(@RequestBody @Valid TeacherDto teacherDtoObj)
	{
		return new ResponseEntity<>(teacherService.saveTeacher(teacherDtoObj),HttpStatus.CREATED);
	}
	
//	using this method we can delete a teacher by id 
	
	@DeleteMapping("/{id}")
	public void deleteTeacherById(@PathVariable Long id)
	{
		teacherService.deleteTeacherById(id);
		log.info("Teacher deleted with id:"+id);
	}
	
//	Using this method we are able to find the teacher by using its first name
	
	@GetMapping("/find-by-first-name/{firstName}")
	public List<Teacher> findTeacherByFirstName(@PathVariable String firstName) throws TeacherNotFoundException
	{
		List<Teacher> teacher = teacherService.findTeacherByFirstName(firstName);
		if(teacher.isEmpty())
		{
			log.info("Unable to find the teacher with name:"+firstName);
			throw new TeacherNotFoundException("Unable to find the teacher with name:"+firstName);
		}
		log.info("Teacher found");
		return teacher;
		
	}
	
//	Using this method we are able to find the teacher by using its last name
	@GetMapping("/find-by-last-name/{lastName}")
	public List<Teacher> findTeacherByLastName(@PathVariable String lastName) throws TeacherNotFoundException
	{
		List<Teacher> teacher = teacherService.findTeacherByLastName(lastName);
		if(teacher.isEmpty())
		{
			log.info("Unable to find the teacher with name:"+lastName);
			throw new TeacherNotFoundException("Unable to find the teacher with name:"+lastName);
		}
		log.info("Teacher found");
		return teacher;
	}
	
//	Using this method we can find all the students using their date of joining
	
	@GetMapping("/find-by-doj/{doj}")
	public List<Teacher> findTeacherByDOJ(@PathVariable LocalDate doj) throws TeacherNotFoundException
	{
		List<Teacher> teacher = teacherService.findTeacherByDOJ(doj);
		if(teacher.isEmpty())
		{
			log.info("Unable to find the teacher with doj:"+doj);
			throw new TeacherNotFoundException("Unable to find the teacher with doj:"+doj);
		}
		log.info("Teacher found");
		return teacher;
	}
	
//	Using this method we can find all the students using their date of birth
	
	@GetMapping("/find-by-dob/{dob}")
	public List<Teacher> findTeacherByDOB(@PathVariable LocalDate dob) throws TeacherNotFoundException
	{
		List<Teacher> teacher = teacherService.findTeacherByDOB(dob);
		if(teacher.isEmpty())
		{
			log.info("Unable to find the teacher with dob:"+dob);
			throw new TeacherNotFoundException("Unable to find the teacher with dob:"+dob);
		}
		log.info("Teacher found");
		return teacher;
	}
	
	
//	This method will update the teacher data 
	@PutMapping("/update")
	public Teacher updateTeacher(@RequestBody Teacher teacher) throws TeacherNotFoundException
	{
		return teacherService.updateTeacher(teacher);
	}
	
	
	
}
