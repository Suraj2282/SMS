package com.cts.sms.dto;

import java.time.LocalDate;

import com.cts.sms.entity.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherDto {
	
	@NotBlank(message="First Name is Required")
	private String firstName;
	
	@NotBlank(message="Last Name is Required")
	private String lastName;
	
	@NotBlank(message="Qualification is Required")
	private String qualification;
	
	@NotBlank(message="Specialization is Required")
	private String specialization;
	
	private LocalDate dob;
	private LocalDate doj;
	
	@NotBlank(message="Email Id is Required")
	@Email(message = "Enter Valid Email Id")
	private String email;
	
	@Pattern(regexp = "^\\\\+(?:[0-9] ?){6,14}[0-9]$")
	@Size(min=10, max=13, message="Enter Valid Mobile Number")
	private String mobileNumber;
	
	@NotNull(message="All the fields of Address is required")
	private Address address;
	

}
