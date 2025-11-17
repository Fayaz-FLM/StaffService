package com.flmhospitals.service.impl;



import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.flmhospitals.builder.StaffBuilder;
import com.flmhospitals.builder.StaffDtoBuilder;
import com.flmhospitals.dao.StaffRepository;
import com.flmhospitals.dto.RegisterStaffDto;
import com.flmhospitals.dto.StaffDetailsDto;
import com.flmhospitals.enums.StaffType;
import com.flmhospitals.exception.StaffNotFoundException;
import com.flmhospitals.model.Staff;
import com.flmhospitals.service.StaffService;
import com.flmhospitals.utils.StaffIdGenerator;

@Service
public class StaffServiceImpl implements StaffService {

	private final StaffRepository staffRepository;
	private final StaffIdGenerator staffIdGenerator;

	public StaffServiceImpl(StaffRepository staffRepository, StaffIdGenerator staffIdGenerator) {
		this.staffRepository = staffRepository;
		this.staffIdGenerator = staffIdGenerator;
	}

 @Override
	public Staff getStaffByStaffId(String staffId) {
		return staffRepository.findById(staffId)
				.orElseThrow(() -> new StaffNotFoundException("Staff with ID :"+ staffId+" not found"));
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

	@Override
	public StaffDetailsDto registerStaffDeatils(RegisterStaffDto registerStaffDto) {
	
		Staff staff =  StaffBuilder.buildStaffFromRegisterStaffDto(registerStaffDto);
		boolean roleFlag = registerStaffDto.getStaffType().equals(StaffType.DOCTOR);//
		if (roleFlag) {
			staff.setRole("Admin");
			staff.setCanLogin(true);
		}
		else {
			staff.setRole("Non-Admin");
		}
		staff.setEmployeeActive(true);
		
		 Staff registerdStaff =  staffRepository.save(staff);
		 
		return StaffDtoBuilder.buildStaffDetailsDto(registerdStaff);
	}
		
	
	

}
