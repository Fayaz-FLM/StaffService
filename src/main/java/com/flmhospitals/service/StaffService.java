package com.flmhospitals.service;


import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffDetailsDto;


public interface StaffService {
	
	 StaffDetailsDto updateStaff(String staffId,RegisterStaffDto dto);

}
