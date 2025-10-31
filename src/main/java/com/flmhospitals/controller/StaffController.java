package com.flmhospitals.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffDetailsDto;
import com.flmhospitals.service.StaffService;

@RestController
public class StaffController {
	
	
	private final StaffService staffService;
	
	public StaffController(StaffService staffService)  {
		this.staffService = staffService;
	}

	@PutMapping("/update/{staffId}")
	public ResponseEntity<StaffDetailsDto> updateStaff(@PathVariable String staffId,@RequestBody RegisterStaffDto staffDetailsDto) {
		StaffDetailsDto updatedStaff = staffService.updateStaff(staffId, staffDetailsDto);
		return ResponseEntity.ok(updatedStaff);
	}
}
