package com.app.LureCo.entity;

import jakarta.persistence.*;

@Entity
public class Office {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String location;
	private String maxCapacity;
	private String phone;
	
	public Office() {}
	public Office(String name, String location, String maxCapacity, String phone) {
		this.name = name;
		this.location = location;
		this.maxCapacity = maxCapacity;
		this.phone = phone;
	}
	
	public Long getId() {return id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getLocation() {return location;}
	public void setLocation(String location) {this.location = location;}
	
	public String getMaxCapacity() {return maxCapacity;}
	public void setMaxCapacity(String maxCapacity) {this.maxCapacity = maxCapacity;}
	
	public String getPhone() {return phone;}
	public void setPhone(String phone) {this.phone = phone;}
}
