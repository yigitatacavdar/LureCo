package com.app.LureCo.bean;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

import com.app.LureCo.dao.DepartmentDAO;
import com.app.LureCo.entity.Department;

@Named("departmentBean")
@SessionScoped
public class DepartmentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private DepartmentDAO dao = new DepartmentDAO();
    
    private Department newDepartment = new Department();
    
    private int id;

    public List<Department> getDepartments() {
        return dao.getAll();
    }
    

    // Form submission method
    public String addDepartment() {
        dao.save(newDepartment);
        newDepartment = new Department(); // reset the form
        return null; // stay on the same page
    }
    
    public String deleteDepartment() {
    	dao.delete(id);
    	id = 0;
    	return null;
    }

    // Getter and setter for the form object
    public Department getNewDepartment() {
        return newDepartment;
    }

    public void setNewEmployee(Department newDepartment) {
        this.newDepartment = newDepartment;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
