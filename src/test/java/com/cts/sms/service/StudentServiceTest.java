package com.cts.sms.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.sms.dto.StudentDto;
import com.cts.sms.entity.Address;
import com.cts.sms.entity.Student;
import com.cts.sms.entity.Student.StudentBuilder;
import com.cts.sms.repository.StudentRepository;

import jakarta.inject.Inject;

class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;
	
	@InjectMocks
	private StudentService studentService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
	}

	@Test
	void saveStudentTest()
	{
		Address address = Address.builder()
				.street("Nagapur")
				.state("Maharahstra")
				.city("Ahmednagar")
				.pinCode(414111L)
				.build();
	
		StudentDto studentDto = StudentDto.builder()
				.firstName("Suraj")
				.lastName("Thorat")
				.mobileNumber("7249729735")
				.email("suraj.thorat7259@gmail.com")
				.className("BE")
				.section("A")
				.dateOfBirth(LocalDate.parse("2002-08-22"))
				.dateOfAdmission(LocalDate.parse("2020-01-01"))
				.fees(65300.00)
				.address(address)
				.gender("Male")
				.studentId(1L)
				.build();
		
		Student student = Student.builder()
				.firstName(studentDto.getFirstName())
				.lastName(studentDto.getLastName())
				.address(studentDto.getAddress())
				.className(studentDto.getClassName())
				.section(studentDto.getSection())
				.dateOfAdmission(studentDto.getDateOfAdmission())
				.dateOfBirth(studentDto.getDateOfBirth())
				.email(studentDto.getEmail())
				.mobileNumber(studentDto.getMobileNumber())
				.fees(studentDto.getFees())
				.gender(studentDto.getGender())
				.studentId(studentDto.getStudentId())
				.build();
		
		when(studentService.saveStudent(studentDto)).thenReturn(student);
		Student expected = studentService.saveStudent(studentDto);
		
		assertEquals(expected, student);
				
				
	}
	@Test
	void findStudentById()
	{
		
		Address address = Address.builder()
				.street("Nagapur")
				.state("Maharahstra")
				.city("Ahmednagar")
				.pinCode(414111L)
				.build();
	
		StudentDto studentDto = StudentDto.builder()
				.firstName("Suraj")
				.lastName("Thorat")
				.mobileNumber("7249729735")
				.email("suraj.thorat7259@gmail.com")
				.className("BE")
				.section("A")
				.dateOfBirth(LocalDate.parse("2002-08-22"))
				.dateOfAdmission(LocalDate.parse("2020-01-01"))
				.fees(65300.00)
				.address(address)
				.gender("Male")
				.studentId(1L)
				.build();
		
		Student student = Student.builder()
				.firstName(studentDto.getFirstName())
				.lastName(studentDto.getLastName())
				.address(studentDto.getAddress())
				.className(studentDto.getClassName())
				.section(studentDto.getSection())
				.dateOfAdmission(studentDto.getDateOfAdmission())
				.dateOfBirth(studentDto.getDateOfBirth())
				.email(studentDto.getEmail())
				.mobileNumber(studentDto.getMobileNumber())
				.fees(studentDto.getFees())
				.gender(studentDto.getGender())
				.studentId(studentDto.getStudentId())
				.build();
		when(studentService.findStudentById(1L)).thenReturn(Optional.of(student));
	}
	
	@Test
	void findStudentByFirstName()
	{
		Address address = Address.builder()
				.street("Nagapur")
				.state("Maharahstra")
				.city("Ahmednagar")
				.pinCode(414111L)
				.build();
	
		StudentDto studentDto = StudentDto.builder()
				.firstName("Suraj")
				.lastName("Thorat")
				.mobileNumber("7249729735")
				.email("suraj.thorat7259@gmail.com")
				.className("BE")
				.section("A")
				.dateOfBirth(LocalDate.parse("2002-08-22"))
				.dateOfAdmission(LocalDate.parse("2020-01-01"))
				.fees(65300.00)
				.address(address)
				.gender("Male")
				.studentId(1L)
				.build();
		
		Student student = Student.builder()
				.firstName(studentDto.getFirstName())
				.lastName(studentDto.getLastName())
				.address(studentDto.getAddress())
				.className(studentDto.getClassName())
				.section(studentDto.getSection())
				.dateOfAdmission(studentDto.getDateOfAdmission())
				.dateOfBirth(studentDto.getDateOfBirth())
				.email(studentDto.getEmail())
				.mobileNumber(studentDto.getMobileNumber())
				.fees(studentDto.getFees())
				.gender(studentDto.getGender())
				.studentId(studentDto.getStudentId())
				.build();
		when(studentService.findStudentByFirstName("Suraj")).thenReturn(List.of(student));
	}
	
	@Test
	void findStudentByLastName()
	{
		Address address = Address.builder()
				.street("Nagapur")
				.state("Maharahstra")
				.city("Ahmednagar")
				.pinCode(414111L)
				.build();
	
		StudentDto studentDto = StudentDto.builder()
				.firstName("Suraj")
				.lastName("Thorat")
				.mobileNumber("7249729735")
				.email("suraj.thorat7259@gmail.com")
				.className("BE")
				.section("A")
				.dateOfBirth(LocalDate.parse("2002-08-22"))
				.dateOfAdmission(LocalDate.parse("2020-01-01"))
				.fees(65300.00)
				.address(address)
				.gender("Male")
				.studentId(1L)
				.build();
		
		Student student = Student.builder()
				.firstName(studentDto.getFirstName())
				.lastName(studentDto.getLastName())
				.address(studentDto.getAddress())
				.className(studentDto.getClassName())
				.section(studentDto.getSection())
				.dateOfAdmission(studentDto.getDateOfAdmission())
				.dateOfBirth(studentDto.getDateOfBirth())
				.email(studentDto.getEmail())
				.mobileNumber(studentDto.getMobileNumber())
				.fees(studentDto.getFees())
				.gender(studentDto.getGender())
				.studentId(studentDto.getStudentId())
				.build();
		when(studentService.findStudentByLastName("Thorat")).thenReturn(List.of(student));
	}
	
	@Test
	void findAllStudentsTest()
	{
		Address address = Address.builder()
				.street("Nagapur")
				.state("Maharahstra")
				.city("Ahmednagar")
				.pinCode(414111L)
				.build();
	
		StudentDto studentDto = StudentDto.builder()
				.firstName("Suraj")
				.lastName("Thorat")
				.mobileNumber("7249729735")
				.email("suraj.thorat7259@gmail.com")
				.className("BE")
				.section("A")
				.dateOfBirth(LocalDate.parse("2002-08-22"))
				.dateOfAdmission(LocalDate.parse("2020-01-01"))
				.fees(65300.00)
				.address(address)
				.gender("Male")
				.studentId(1L)
				.build();
		
		Student student1 = Student.builder()
				.firstName(studentDto.getFirstName())
				.lastName(studentDto.getLastName())
				.address(studentDto.getAddress())
				.className(studentDto.getClassName())
				.section(studentDto.getSection())
				.dateOfAdmission(studentDto.getDateOfAdmission())
				.dateOfBirth(studentDto.getDateOfBirth())
				.email(studentDto.getEmail())
				.mobileNumber(studentDto.getMobileNumber())
				.fees(studentDto.getFees())
				.gender(studentDto.getGender())
				.studentId(studentDto.getStudentId())
				.build();
		
		StudentDto studentDto1 = StudentDto.builder()
				.firstName("Santosh")
				.lastName("Thorat")
				.mobileNumber("7276729735")
				.email("santosh.thorat7259@gmail.com")
				.className("BE")
				.section("A")
				.dateOfBirth(LocalDate.parse("2002-08-22"))
				.dateOfAdmission(LocalDate.parse("2020-01-01"))
				.fees(65300.00)
				.address(address)
				.gender("Male")
				.studentId(1L)
				.build();
		
		Student student2 = Student.builder()
				.firstName(studentDto1.getFirstName())
				.lastName(studentDto1.getLastName())
				.address(studentDto1.getAddress())
				.className(studentDto1.getClassName())
				.section(studentDto1.getSection())
				.dateOfAdmission(studentDto1.getDateOfAdmission())
				.dateOfBirth(studentDto1.getDateOfBirth())
				.email(studentDto1.getEmail())
				.mobileNumber(studentDto1.getMobileNumber())
				.fees(studentDto1.getFees())
				.gender(studentDto1.getGender())
				.studentId(studentDto1.getStudentId())
				.build();
		
		List<Student> studentList = new ArrayList<>();
		
		studentList.add(student1);
		studentList.add(student2);
				
	}
	
	@Test
	void updateStudentTest()
	{
		Address address = Address.builder()
				.street("Nagapur")
				.state("Maharahstra")
				.city("Ahmednagar")
				.pinCode(414111L)
				.build();
	
		StudentDto studentDto = StudentDto.builder()
				.firstName("Suraj")
				.lastName("Thorat")
				.mobileNumber("7249729735")
				.email("suraj.thorat7259@gmail.com")
				.className("BE")
				.section("A")
				.dateOfBirth(LocalDate.parse("2002-08-22"))
				.dateOfAdmission(LocalDate.parse("2020-01-01"))
				.fees(65300.00)
				.address(address)
				.gender("Male")
				.studentId(1L)
				.build();
		
		Student student1 = Student.builder()
				.firstName(studentDto.getFirstName())
				.lastName(studentDto.getLastName())
				.address(studentDto.getAddress())
				.className(studentDto.getClassName())
				.section(studentDto.getSection())
				.dateOfAdmission(studentDto.getDateOfAdmission())
				.dateOfBirth(studentDto.getDateOfBirth())
				.email(studentDto.getEmail())
				.mobileNumber(studentDto.getMobileNumber())
				.fees(studentDto.getFees())
				.gender(studentDto.getGender())
				.studentId(studentDto.getStudentId())
				.build();
		
		StudentDto studentDto1 = StudentDto.builder()
				.firstName("Santosh")
				.lastName("Thorat")
				.mobileNumber("7276729735")
				.email("santosh.thorat7259@gmail.com")
				.className("BE")
				.section("A")
				.dateOfBirth(LocalDate.parse("2002-08-22"))
				.dateOfAdmission(LocalDate.parse("2020-01-01"))
				.fees(65300.00)
				.address(address)
				.gender("Male")
				.studentId(1L)
				.build();
		
		Student student2 = Student.builder()
				.firstName(studentDto1.getFirstName())
				.lastName(studentDto1.getLastName())
				.address(studentDto1.getAddress())
				.className(studentDto1.getClassName())
				.section(studentDto1.getSection())
				.dateOfAdmission(studentDto1.getDateOfAdmission())
				.dateOfBirth(studentDto1.getDateOfBirth())
				.email(studentDto1.getEmail())
				.mobileNumber(studentDto1.getMobileNumber())
				.fees(studentDto1.getFees())
				.gender(studentDto1.getGender())
				.studentId(studentDto1.getStudentId())
				.build();
		when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));
		when(studentRepository.save(student2)).thenReturn(student2);
		
		Student result = studentService.updateStudent(student2);
		assertEquals(result, student2);
	}
	
	
}

