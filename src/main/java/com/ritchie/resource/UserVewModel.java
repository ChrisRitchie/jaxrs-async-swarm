package com.ritchie.resource;

public class UserVewModel {

	private Integer id;
	private String firstName;
	private String surname;

	@SuppressWarnings("unused")
	private UserVewModel() {

	}

	public UserVewModel(Integer id, String firstName, String surname) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

}
