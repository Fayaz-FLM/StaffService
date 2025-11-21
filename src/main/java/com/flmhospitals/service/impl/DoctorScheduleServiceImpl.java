package com.flmhospitals.service.impl;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flmhospitals.dao.DoctorScheduleRepository;
import com.flmhospitals.dao.StaffRepository;
import com.flmhospitals.exception.DoctorUnavailableException;
import com.flmhospitals.exception.StaffNotFoundException;
import com.flmhospitals.model.DoctorSchedule;
import com.flmhospitals.model.Staff;
import com.flmhospitals.service.DoctorScheduleService;
import com.flmhospitals.service.StaffService;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

	private final DoctorScheduleRepository doctorScheduleRepository;

	private final StaffRepository staffRepository;

	private final StaffService staffService;

	public DoctorScheduleServiceImpl(DoctorScheduleRepository doctorScheduleRepository, StaffRepository staffRepository,
			StaffService staffService) {

		this.doctorScheduleRepository = doctorScheduleRepository;

		this.staffRepository = staffRepository;

		this.staffService = staffService;
	}

	@Override
	public boolean isDoctorAvailable(String staffId, LocalDate date) {

		Staff staff = staffService.getStaffByStaffId(staffId);

		boolean isAvailable = !doctorScheduleRepository.existsByStaffStaffIdAndUnavailableDate(staffId, date);

		return isAvailable;

	}

	@Override
	public List<LocalDate> markDoctorUnavailable(String doctorId, List<LocalDate> listOfUnavailabeDates) {

		Staff staff = staffRepository.findById(doctorId)
				.orElseThrow(() -> new StaffNotFoundException("No Doctor found with Id " + doctorId));

		List<DoctorSchedule> listOfSchedules = new ArrayList<>();

		for (LocalDate unavailableDate : listOfUnavailabeDates) {

			if (validateUnavailableDate(unavailableDate)
					&& !checkUnavailableEntryExistsOrNot(doctorId, unavailableDate)) {

				DoctorSchedule schedule = DoctorSchedule.builder().staff(staff).unavailableDate(unavailableDate)
						.build();

				listOfSchedules.add(schedule);
			}
		}

		if (listOfSchedules.isEmpty()) {
			throw new DoctorUnavailableException(
					"No new unavailable dates/Invalid Dates were added for doctor " + doctorId);
		}

		doctorScheduleRepository.saveAll(listOfSchedules);

		return listOfSchedules.stream().map(DoctorSchedule::getUnavailableDate).toList();
	}

	public boolean validateUnavailableDate(LocalDate date) {

		return !date.isBefore(LocalDate.now());
	}

	public boolean checkUnavailableEntryExistsOrNot(String doctorId, LocalDate date) {

		return doctorScheduleRepository.existsByStaffStaffIdAndUnavailableDate(doctorId, date);

	}

}
