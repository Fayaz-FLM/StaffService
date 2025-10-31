package com.flmhospitals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flmhospitals.builder.StaffDtoBuilder;
import com.flmhospitals.dao.StaffRepository;
import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffDetailsDto;
import com.flmhospitals.exception.StaffNotFoundException;
import com.flmhospitals.model.Staff;
import com.flmhospitals.model.StaffAddress;
import com.flmhospitals.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
    private StaffRepository staffRepository;

	@Override
	public StaffDetailsDto updateStaff(String staffId, RegisterStaffDto dto) {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(()->new StaffNotFoundException("Staff ID: " + staffId + " not found" ));
		
		 if (dto.getStaffAddressDto() != null) {
            if (staff.getStaffAddress() == null) {
                staff.setStaffAddress(new StaffAddress());
            }
            StaffAddress address = staff.getStaffAddress();
            if (dto.getStaffAddressDto().getLandmark() != null) 
            	address.setLandmark(dto.getStaffAddressDto().getLandmark());
            if (dto.getStaffAddressDto().getCity() != null) 
            	address.setCity(dto.getStaffAddressDto().getCity());
            if (dto.getStaffAddressDto().getState() != null) 
            	address.setState(dto.getStaffAddressDto().getState());
            if (dto.getStaffAddressDto().getCountry() != null) 
            	address.setCountry(dto.getStaffAddressDto().getCountry());
            if (dto.getStaffAddressDto().getPinCode()!=null)
            	address.setPinCode(dto.getStaffAddressDto().getPinCode());
            	
        }
		
		if(dto.getFirstName() != null)
			staff.setFirstName(dto.getFirstName());
		if(dto.getLastName()!=null)
			staff.setLastName(dto.getLastName());
		if (dto.getPhoneNumber() != null) 
			staff.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getGender() != null) 
        	staff.setGender(dto.getGender());
        if (dto.getRole() != null) 
        	staff.setRole(dto.getRole());
        if (dto.getSpecialization() != null) 
        	staff.setSpecialization(dto.getSpecialization());
        if (dto.getStaffType() != null) 
        	staff.setStaffType(dto.getStaffType());
        if(dto.getEmail()!=null)
        	staff.setEmail(dto.getEmail());
        if (dto.getExperienceInYears() > 0) 
        	staff.setExperienceInYears(dto.getExperienceInYears());
        if(dto.getSpecialization()!=null) 
        	staff.setSpecialization(dto.getSpecialization());
        if(dto.getStaffType()!=null) 
        	staff.setStaffType(dto.getStaffType());
        
        Staff updatedStaff = staffRepository.save(staff);

		return StaffDtoBuilder.buildStaffDetailsDto(updatedStaff);

       

		
	}

}
