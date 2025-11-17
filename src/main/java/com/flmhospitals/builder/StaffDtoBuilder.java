package com.flmhospitals.builder;

import org.springframework.beans.BeanUtils;
import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffAddressDto;
import com.flmhospitals.dto.StaffDetailsDto;
import com.flmhospitals.model.Staff;
import com.flmhospitals.model.StaffAddress;

public class StaffDtoBuilder {

	public static StaffDetailsDto buildStaffDetailsDto(Staff staff) {

		return StaffDetailsDto
				.builder()
				.staffId(staff.getStaffId())
				.firstName(staff.getFirstName())
				.lastName(staff.getLastName())
				.phoneNumber(staff.getPhoneNumber())
				.role(staff.getRole())
				.gender(staff.getGender())
				.experienceInYears(staff.getExperienceInYears())
				.email(staff.getStaffDetails().getEmail())
				.specialization(staff.getSpecialization())
				.staffType(staff.getStaffType())
				.staffAddressDto(buildAddressDto(staff.getStaffAddress()))
				.build();


	}

	public static StaffAddressDto buildAddressDto(StaffAddress staffAddress) {

		StaffAddressDto staffAddressDto = new StaffAddressDto();

		BeanUtils.copyProperties(staffAddress, staffAddressDto);

		return staffAddressDto;
	}
	
	public static void updateStaffEntity(Staff staff, RegisterStaffDto dto) {

        if (dto.getFirstName() != null) staff.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) staff.setLastName(dto.getLastName());
        if (dto.getPhoneNumber() != null) staff.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getRole() != null) staff.setRole(dto.getRole());
        if (dto.getGender() != null) staff.setGender(dto.getGender());
        if (dto.getExperienceInYears() > 0) staff.setExperienceInYears(dto.getExperienceInYears());
        if (dto.getAadharNumber() != null) staff.setAadharNumber(dto.getAadharNumber());
        if (dto.getEmail() != null) staff.getStaffDetails().setEmail(dto.getEmail());
        if (dto.getSpecialization() != null) staff.setSpecialization(dto.getSpecialization());
        if (dto.getStaffType() != null) staff.setStaffType(dto.getStaffType());

        if (dto.getStaffAddressDto() != null) {
            StaffAddress address = staff.getStaffAddress();
            StaffAddressDto addressDto = dto.getStaffAddressDto();
            if (addressDto.getLandmark() != null) address.setLandmark(addressDto.getLandmark());
            if (addressDto.getCity() != null) address.setCity(addressDto.getCity());
            if (addressDto.getState() != null) address.setState(addressDto.getState());
            if (addressDto.getCountry() != null) address.setCountry(addressDto.getCountry());
            if (addressDto.getPinCode() != null) address.setPinCode(addressDto.getPinCode());
        }
    
	}

}
