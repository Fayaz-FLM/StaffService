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
	

	public StaffServiceImpl(StaffRepository staffRepository) {
		
		this.staffRepository = staffRepository;
		
	}

 @Override
	public Staff getStaffByStaffId(String staffId) {
		return staffRepository.findById(staffId)
				.orElseThrow(() -> new StaffNotFoundException("Staff with ID :"+ staffId+" not found"));
  }

	@Override
	public ResponseEntity<List<StaffDetailsDto>> searchByStaffFirstNameOrLastName(String name) {

		List<Staff> staffs = staffRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);

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
	public StaffDetailsDto registerStaffDeatils(RegisterStaffDto registerStaffDto) {
		// TODO Auto-generated method stub
		
		Staff staff =  StaffBuilder.buildStaffFromRegisterStaffDto(registerStaffDto);
		
		boolean roleFlag = registerStaffDto.getStaffType().equals(StaffType.DOCTOR);
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


	@Override
	public String deleteStaff(String staffId) {
		
		Staff staff = staffRepository.findById(staffId)
		.orElseThrow(()-> new StaffNotFoundException("No staff Found with the Id :"+staffId));

		staff.setEmployeeActive(false);
		
		staffRepository.save(staff);
		
		return staff.getFirstName()+" "+staff.getLastName();
	}

		
	
	
}
