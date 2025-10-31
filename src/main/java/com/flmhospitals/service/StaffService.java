package com.flmhospitals.service;

import java.util.List;
import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffDetailsDto;
import org.springframework.http.ResponseEntity;


public interface StaffService {

	public ResponseEntity<List<StaffDetailsDto>> searchByStaffFirstNameOrLastName(String name);
	
	 StaffDetailsDto updateStaff(String staffId,RegisterStaffDto dto);

}
