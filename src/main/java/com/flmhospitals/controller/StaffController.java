package com.flmhospitals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffDetailsDto;
import com.flmhospitals.service.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {

	private final StaffService staffService;

	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}

	@GetMapping("/searchByStaffName")
	public ResponseEntity<List<StaffDetailsDto>> searchByStaffFirstNameOrLastName(
			@RequestParam(name = "name", required = true) String name) {
		return staffService.searchByStaffFirstNameOrLastName(name);

	}

	@PutMapping("/update/{staffId}")
	public ResponseEntity<StaffDetailsDto> updateStaff(@PathVariable String staffId,@RequestBody RegisterStaffDto staffDetailsDto) {
		StaffDetailsDto updatedStaff = staffService.updateStaff(staffId, staffDetailsDto);
		return ResponseEntity.ok(updatedStaff);
		
	}

}
