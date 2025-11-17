package com.flmhospitals.utils;

import org.springframework.stereotype.Component;

import com.flmhospitals.dao.StaffRepository;



@Component
public class StaffIdGenerator {

   
    private final StaffRepository staffRepository;
       
    public StaffIdGenerator(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}



	public  String generateNextStaffId() {
        String lastId = staffRepository.findLastStaffId();
        int nextNumber = 1;

        if (lastId != null && lastId.startsWith("FLM-")) {
            String numberPart = lastId.substring(4);
            nextNumber = Integer.parseInt(numberPart) + 1;
            return String.format("FLM-%05d", nextNumber);
        }

        return String.format("FLM-%05d", nextNumber);
    }

}