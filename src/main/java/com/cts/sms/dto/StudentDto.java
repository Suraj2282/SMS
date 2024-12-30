package com.cts.sms.dto;


import java.time.LocalDate;

import com.cts.sms.entity.Address;
//import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {
	
	@Valid
	private Long studentId;
	@NotNull(message="First Name is Required")
	@Size(min =2, max = 25, message = "First Name Must be Between 2 to 25")
	private String firstName;
	
	@NotBlank(message = "Last Name is Required")
	@NotNull(message="Last Name is Required")
	@Size(min =2, max = 25, message = "Last Name Must be Between 2 to 25")
	private String lastName;

	@NotNull(message="Gender is Required")
	@Size(min =2, max = 8, message = "Gender must be valid")
	private String gender;
	

	@NotNull(message="Class Name is Required")
	@Size(min =2, max = 25, message = "Class Name Must be Between 2 to 25")
	private String className;
	
	
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Invalid mobile number format")
    @Size(min=10, max=13, message="Enter Valid Mobile Number")
	private String mobileNumber;
	
	@Email(message="Enter Valid Email Address")
	private String email;
	
	
	@NotNull(message="Section is Required")
	@Size(min =1, max = 1, message = "Section Must be 1")
	private String section;
	
	
//	@NotNull(message="Date of Birth is Required")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private LocalDate dateOfBirth;
	
//	@NotBlank(message = "Date of Admission is Required")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private LocalDate dateOfAdmission;
	
	@NotNull(message ="Fees are required")
	private Double fees;
	

	private Address address;
	
	
	
}


