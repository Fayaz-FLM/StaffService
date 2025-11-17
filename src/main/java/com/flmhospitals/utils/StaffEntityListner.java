package com.flmhospitals.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flmhospitals.model.Staff;

import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;

@Component
public class StaffEntityListner {

	public static StaffIdGenerator staffIdGenerator;

	@Autowired
	public void init(StaffIdGenerator staffIdGenerator) {

		this.staffIdGenerator = staffIdGenerator;
	}
	
	 @PrePersist
	    public void generateStaffId(Staff staff) {
	        if (staff.getStaffId()==null || staff.getStaffId().isEmpty()) {
	           staff.setStaffId(staffIdGenerator.generateNextStaffId()); 
	        }
	 }
	    
}
