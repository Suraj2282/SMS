package com.cts.sms.entity;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(sequenceName = "teacherId", name = "teacherId", initialValue = 1, allocationSize = 1)
	private Long id;
	private String firstName;
	private String lastName;
	private String qualification;
	private String specialization;
	private LocalDate dob;
	private LocalDate doj;
	private String email;
	private String mobileNumber;
	
	@Embedded
	private Address address;
	
	
	
	

}
