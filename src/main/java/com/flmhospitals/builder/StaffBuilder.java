package com.flmhospitals.builder;

import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffAddressDto;
import com.flmhospitals.model.Staff;
import com.flmhospitals.model.StaffAddress;
import com.flmhospitals.model.StaffDetails;

public class StaffBuilder {

	public static Staff buildStaffFromRegisterStaffDto(RegisterStaffDto registerStaffDto){
		
		return Staff.builder()
		.firstName(registerStaffDto.getFirstName())
		.lastName(registerStaffDto.getLastName())
		.phoneNumber(String.valueOf(registerStaffDto.getPhoneNumber()))
		.gender(registerStaffDto.getGender())
		.staffType(registerStaffDto.getStaffType())
		.specialization(registerStaffDto.getSpecialization())
		.experienceInYears(registerStaffDto.getExperienceInYears())
		.staffAddress(buildStaffAdddressFromStaffAddressDto(registerStaffDto.getStaffAddressDto()))
		.staffDetails(buildStaffDetailsFromStaffDetailsDto(registerStaffDto.getEmail()))
		
		
		.build();
		
		
	}
	
	public static StaffAddress buildStaffAdddressFromStaffAddressDto(StaffAddressDto staffAddressDto) {
		
		return StaffAddress.builder()
				.street(staffAddressDto.getStreet())
				.city(staffAddressDto.getCity())
				.state(staffAddressDto.getState())
				.country(staffAddressDto.getCountry())
				.pinCode(staffAddressDto.getPinCode())
				.build();
				
				
	}
	
	public static StaffDetails buildStaffDetailsFromStaffDetailsDto(String email) {
		
		return StaffDetails.builder()
				           .email(email)
				           .build();
	}
}
