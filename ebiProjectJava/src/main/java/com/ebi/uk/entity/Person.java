package com.ebi.uk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Persons")

public class Person implements Serializable{

/**
	 * 
	 */
	
	public Person(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	public Person() {
		
	}
	private static final long serialVersionUID = -2080712303193207032L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "first_name")
	
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "favorite_color")
	private String favoriteColor;
	private int age;
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
	public String getFavoriteColor() {
		return favoriteColor;
	}
	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
