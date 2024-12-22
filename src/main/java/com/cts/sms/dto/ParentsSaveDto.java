package com.cts.sms.dto;

import com.cts.sms.entity.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParentsSaveDto {
	
	
	@NotBlank(message = "First Name is Required")
	@NotNull(message="First Name is Required")
	private String firstName;
	
	@NotBlank(message = "Last Name is Required")
	@NotNull(message="Last Name is Required")
	private String lastName;
	
	@Size(min=10, max=13, message = "Mobile Number is Required")
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Invalid mobile number format")
	private String mobileNumber;
	
	@NotBlank(message = "Occupation is required")
	private String occupation;
	
	@NotBlank(message="Address fields are required.")
	private Address address; 

}
