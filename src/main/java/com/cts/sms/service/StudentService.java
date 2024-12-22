package com.cts.sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.sms.dto.StudentDto;
import com.cts.sms.entity.Student;
import com.cts.sms.repository.StudentRepository;

import jakarta.validation.Valid;

@Service
public class StudentService {

	@Autowired	
	private StudentRepository studentRepository;
	
	public List<Student> findAllStudents()
	{
		return studentRepository.findAll();
	}
	
	public Student saveStudent(StudentDto saveStudent)
	{
		Student student = Student.builder()
				.firstName(saveStudent.getFirstName())
				.lastName(saveStudent.getLastName())
				.gender(saveStudent.getGender())
				.email(saveStudent.getEmail())
				.address(saveStudent.getAddress())
				.dateOfAdmission(saveStudent.getDateOfAdmission())
				.dateOfBirth(saveStudent.getDateOfBirth())
				.fees(saveStudent.getFees())
				.section(saveStudent.getSection())
				.className(saveStudent.getClassName())
				.mobileNumber(saveStudent.getMobileNumber())
				.build();
		return studentRepository.save(student);
	}

	public Optional<Student> findStudentById(Long id) {
		
		return studentRepository.findById(id);
	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
	}

	public List<Student> findStudentByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return studentRepository.findStudentByFirstName(firstName);
	}

	public List<Student> findStudentByLastName(String lastName) {
		// TODO Auto-generated method stub
		return studentRepository.findStudentByLastName(lastName);
	}

	public Student updateStudent(@Valid Student student) {
		studentRepository.findById(student.getStudentId()).orElseThrow();
		return studentRepository.save(student);
		
	}

	public List<Object[]> getStudentsWithPendingFees(Long fees) {
		// TODO Auto-generated method stub
		return studentRepository.getStudentsWithPendingFees(fees);
	}

//	public Student updateById(Long id, Student student) {
//		
//		if(student.getAddress().equals(null))
//		{
//			student.setAddress(student.getAddress());
//		}
//	}
}
