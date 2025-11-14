package com.flmhospitals.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffAddressDto {
	
	private String landmark;

	private String city;

	private String state;

	private String country;

	private String pinCode;

}
