package com.app.LureCo.bean;

import jakarta.inject.Named;


import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

import com.app.LureCo.dao.EmployeeDAO;
import com.app.LureCo.entity.Employee;

@Named("employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private EmployeeDAO dao = new EmployeeDAO();
    
    private Employee newEmployee = new Employee();
    
    private int id;

    public List<Employee> getEmployees() {
        return dao.getAll();
    }
    

    // Form submission method
    public String addEmployee() {
        dao.save(newEmployee);
        newEmployee = new Employee(); // reset the form
        return null; // stay on the same page
    }
    
    public String deleteEmployee() {
    	dao.delete(id);
    	id = 0;
    	return null;
    }

    // Getter and setter for the form object
    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
