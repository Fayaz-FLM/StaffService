package com.flmhospitals.service;


import java.util.List;
import org.springframework.http.ResponseEntity;
import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffDetailsDto;
import com.flmhospitals.model.Staff;


public interface StaffService {
	
	 Staff getStaffByStaffId(String staffId);

	 public ResponseEntity<List<StaffDetailsDto>> searchByStaffFirstNameOrLastName(String name);
	 
	 StaffDetailsDto updateStaff(String staffId,RegisterStaffDto dto);
	
	 StaffDetailsDto registerStaffDeatils(RegisterStaffDto registerStaffDto);

}
