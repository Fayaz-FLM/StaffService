package com.flmhospitals.service;

import java.time.LocalDate;
import java.util.List;

public interface DoctorScheduleService {

	public boolean isDoctorAvailable(String staffId, LocalDate date);
	List<LocalDate> markDoctorUnavailable(String doctorId,List<LocalDate> listOfUnavailableDates);
	

}
