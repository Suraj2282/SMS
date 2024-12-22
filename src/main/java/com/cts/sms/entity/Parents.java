package com.cts.sms.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parents {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="ParentSequence", allocationSize = 1, sequenceName = "ParentSequence")
	private Long parentId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String occupation;
	
	@Embedded
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "studentId", referencedColumnName = "studentId")
	private Student student;
	
	

}
