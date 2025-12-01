package com.app.LureCo.entity;

import jakarta.persistence.*;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String department;
	private String office;
	private String role;
	
	public Employee() {}
	public Employee(String name, String department, String office, String role) {
		this.name = name;
		this.department = department;
		this.office = office;
		this.role = role;
	}
	
	public Long getId() {return id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getDepartment() {return department;}
	public void setDepartment(String department) {this.department = department;}
	
	public String getOffice() {return office;}
	public void setOffice(String office) {this.office = office;}
	
	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}
}

