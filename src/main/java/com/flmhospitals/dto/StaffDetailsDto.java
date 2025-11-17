package com.flmhospitals.dto;

import java.time.LocalDate;

import com.flmhospitals.enums.Specialization;
import com.flmhospitals.enums.StaffType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDetailsDto {
	
	private String staffId;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String role;
	
	private String gender;
	
	private LocalDate dateOfJoining;
	
	private int experienceInYears;
	
	private String aadharNumber;
	
	private String email;
	
	private Specialization specialization;
	
	private StaffType staffType;
	
	private StaffAddressDto staffAddressDto;
	

}
