package com.cts.sms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.sms.dto.TeacherDto;
import com.cts.sms.entity.Teacher;
import com.cts.sms.exception.TeacherNotFoundException;
import com.cts.sms.repository.TeacherRepository;

//this annotation indiacating that this is the service class of the spring
@Service
public class TeacherService {

//	Using the autowired we are inserting the reference of teacherRepository interface in this class
	@Autowired
	private TeacherRepository teacherRepository;

	
//	Using this method we can find the list of all the teachers present in the database 
	public List<Teacher> findAllTeachers() throws TeacherNotFoundException {

		List<Teacher> teacher = new ArrayList<>();
		teacher = teacherRepository.findAll();
		if(teacher.isEmpty())
		{
			throw new TeacherNotFoundException("Teacher list is empty");
		}
		return teacher;
	}
	
//	using this method we are able to add new teacher in the database with proper validation
	public Teacher saveTeacher(TeacherDto teacherDtoObj)
	{
		Teacher teacher = Teacher.builder()
				.firstName(teacherDtoObj.getFirstName())
				.lastName(teacherDtoObj.getLastName())
				.address(teacherDtoObj.getAddress())
				.qualification(teacherDtoObj.getQualification())
				.dob(teacherDtoObj.getDob())
				.doj(teacherDtoObj.getDoj())
				.email(teacherDtoObj.getEmail())
				.mobileNumber(teacherDtoObj.getMobileNumber())
				.specialization(teacherDtoObj.getSpecialization())
				.build();
		
		return teacherRepository.save(teacher);
	}

//	Using this method we are able to delete the teacher from database with the id 
	public void deleteTeacherById(Long id) {
		teacherRepository.deleteById(id);
		
	}

//	using this method we can find the teacher with firstname  provided
	public List<Teacher> findTeacherByFirstName(String firstName) throws TeacherNotFoundException {
		List<Teacher> teacher = new ArrayList<>();
		teacher = teacherRepository.findTeacherByFirstName(firstName);
		if(teacher.isEmpty())
		{
			throw new TeacherNotFoundException("Teacher with the first name is empty");
		}
		return teacher;
	}
//	using this method we can find the teacher with lastname  provided
	public List<Teacher> findTeacherByLastName(String lastName) throws TeacherNotFoundException {
		
		List<Teacher> teacher = new ArrayList<>();
		teacher = teacherRepository.findTeacherByLastName(lastName);
		if(teacher.isEmpty())
		{
			throw new TeacherNotFoundException("Teacher with the last name is empty");
		}
		return teacher;
	}
//	using this method we can find the teacher with doj  provided
	public List<Teacher> findTeacherByDOJ(LocalDate doj) throws TeacherNotFoundException {
		
		List<Teacher> teacher = new ArrayList<>();
		teacher = teacherRepository.findTeacherByDOJ(doj);
		if(teacher.isEmpty())
		{
			throw new TeacherNotFoundException("Teacher with the DOJ is empty");
		}
		return teacher;
	}

//	using this method we can find the teacher with dob  provided
	public List<Teacher> findTeacherByDOB(LocalDate dob) throws TeacherNotFoundException {
		List<Teacher> teacher = new ArrayList<>();
		teacher = teacherRepository.findTeacherByDOB(dob);
		if(teacher.isEmpty())
		{
			throw new TeacherNotFoundException("Teacher with the DOB is empty");
		}
		return teacher;
	}

//	Using this method we can update the info of the teacher

	public Teacher updateTeacher(Teacher teacher) throws TeacherNotFoundException {
		teacherRepository.findById(teacher.getId()).orElseThrow(()->new TeacherNotFoundException("Teacher with Id: "+teacher.getId()+" is not present in database"));
		return teacherRepository.save(teacher);
	}
}
