package com.app.LureCo.bean;

import jakarta.inject.Named;


import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

import com.app.LureCo.dao.EmployeeDAO;
import com.app.LureCo.entity.Employee;

@Named("searchBean")
@SessionScoped
public class SearchBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private EmployeeDAO dao = new EmployeeDAO();
    
    private String department;
    
    private String office;
    
    private String role;
    
    private List<Employee> employeesFiltered;

    public void searchEmployees() {
        employeesFiltered = dao.search(department, office, role);
    }
    
    public List<Employee> getEmployeesFiltered() {
        return employeesFiltered;
    }
    
	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getOffice() {
		return office;
	}


	public void setOffice(String office) {
		this.office = office;
	}
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
    
    
    
    

}
