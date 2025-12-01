package com.app.LureCo.entity;

import jakarta.persistence.*;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String department;
	private String projectHead;
	
	public Project() {}
	public Project(String name, String department, String projectHead) {
		this.name = name;
		this.department = department;
		this.projectHead = projectHead;

	}
	
	public Long getId() {return id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getDepartment() {return department;}
	public void setdepartment(String department) {this.department = department;}
	
	public String getProjectHead() {return projectHead;}
	public void setProjectHead(String projectHead) {this.projectHead = projectHead;}
}
