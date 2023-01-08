package com.mjubilee.managepersonpapimsjv.model;


import java.time.LocalDate;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Person {
	private Long id;
	
	@Size(min=3, message="First name must be minimum 3 characters.")
	private String firstName;

	@Size(min=3, message="Last name must be minimum 3 characters.")
	private String lastName;
	
	@Past(message="Date of birth can not be today or in the future")
	private LocalDate dob;

	private String email;

	public Person(Long id, String firstName, String lastName, LocalDate dob, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
