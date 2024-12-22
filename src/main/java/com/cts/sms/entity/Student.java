package com.cts.sms.entity;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="student", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"email"}),
		@UniqueConstraint(columnNames = {"mobileNumber"})
})
@Builder
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "StudentId", allocationSize = 1, sequenceName = "StudentId")
	private Long studentId;
	private String firstName;
	private String lastName;
	private String gender;
	private String className;
	private String mobileNumber;
	private String email;
	private String section;
	private LocalDate dateOfBirth;
	private LocalDate dateOfAdmission;
	private Double fees;
	
	
	
	@Embedded
	private Address address;
	
	
	
	

}
