package com.cts.sms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddressDto {
	
	@NotBlank(message = "Street is required")
	private String street;
	
	@NotBlank(message = "City is required")
	private String city;
	
	@NotBlank(message = "State is required")
	private String state;
	
	@NotBlank(message = "Street is required")
	@Pattern(regexp = "[0-9]{5}", message = "Zip code is not in correct format")
	private Long pinCode;

}
