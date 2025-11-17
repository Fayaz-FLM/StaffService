package com.flmhospitals.model;

import java.time.LocalDate;

import com.flmhospitals.enums.Specialization;
import com.flmhospitals.enums.StaffType;
import com.flmhospitals.utils.StaffEntityListner;
import com.flmhospitals.utils.StaffIdGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(value = StaffEntityListner.class)
public class Staff {
	
	@Id
	@Column(name = "staff_id", nullable = false, unique = true)
	private String staffId;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String email;
	
	@Column(length=15,nullable = false )
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StaffType staffType;
	
	@Column(nullable = false)
	private String role;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Specialization specialization;
	
	@Column(nullable = false)
	private LocalDate dateOfJoining;
	
	@Column(nullable = false)
	private int experienceInYears;
	
	@Column(nullable = false)
	private String aadharNumber;
	
	@Column(nullable = false)
	private boolean canLogin;
	
	@Column(nullable = false)
	private boolean isEmployeeActive;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staffAddressId")
	private StaffAddress staffAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staffDetailsId")
	private StaffDetails staffDetails;
	

}
