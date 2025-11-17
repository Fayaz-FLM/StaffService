package com.flmhospitals.dto;

import java.time.LocalDate;

import com.flmhospitals.enums.Specialization;
import com.flmhospitals.enums.StaffType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStaffDto {
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String gender;
	
	private String role;
	
	private LocalDate dateOfJoining;
	
	private int experienceInYears;
	
	private String aadharNumber;
	
	private String email;
	
	private StaffAddressDto staffAddressDto;
	
	private Specialization specialization;
	
	private StaffType staffType;
	

}
