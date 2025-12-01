package com.app.LureCo.entity;

import jakarta.persistence.*;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String departmentHead;
	
	public Department() {}
	public Department(String name, String departmentHead) {
		this.name = name;
		this.departmentHead = departmentHead;
	}
	
	public Long getId() {return id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getDepartmentHead() {return departmentHead;}
	public void setDepartmentHead(String departmentHead) {this.departmentHead = departmentHead;}	
}
