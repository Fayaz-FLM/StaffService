package com.flmhospitals.service.impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.flmhospitals.builder.StaffDtoBuilder;
import com.flmhospitals.dao.StaffRepository;
import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffDetailsDto;
import com.flmhospitals.exception.StaffNotFoundException;
import com.flmhospitals.model.Staff;
import com.flmhospitals.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	private final StaffRepository staffRepository;

	public StaffServiceImpl(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}

	@Override
	public ResponseEntity<List<StaffDetailsDto>> searchByStaffFirstNameOrLastName(String name) {

		List<Staff> staffs = staffRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name,
				name);

		List<StaffDetailsDto> staffDetailsDtoList = new ArrayList<>();

		if (staffs.isEmpty()) {
			throw new StaffNotFoundException("No staff found with name : " + name);
		}

		for (Staff staff : staffs) {
			staffDetailsDtoList.add(StaffDtoBuilder.buildStaffDetailsDto(staff));
		}

		return ResponseEntity.ok(staffDetailsDtoList);
	}


	@Override
	public StaffDetailsDto updateStaff(String staffId, RegisterStaffDto dto) {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new StaffNotFoundException("Staff ID: " + staffId + " not found"));
		
		StaffDtoBuilder.updateStaffEntity(staff, dto);
		Staff updatedStaff = staffRepository.save(staff);
		return StaffDtoBuilder.buildStaffDetailsDto(updatedStaff);

	}

}
