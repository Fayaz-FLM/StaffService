package com.flmhospitals.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flmhospitals.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, String>{

}
