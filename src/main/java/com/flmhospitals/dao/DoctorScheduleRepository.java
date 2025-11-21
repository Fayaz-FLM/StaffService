package com.flmhospitals.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flmhospitals.model.DoctorSchedule;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
	
	
	boolean existsByStaffStaffIdAndUnavailableDate(String staffId, LocalDate unavailableDate);

	//boolean existsByStaff_StaffIdAndUnavailableDate(String staffId, LocalDate unavailableDate);

}
