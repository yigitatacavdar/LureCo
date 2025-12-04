package com.app.LureCo.bean;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

import com.app.LureCo.dao.OfficeDAO;
import com.app.LureCo.entity.Office;

@Named("officeBean")
@SessionScoped
public class OfficeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private OfficeDAO dao = new OfficeDAO();
    
    private Office newOffice = new Office();
    
    private int id;

    public List<Office> getOffices() {
        return dao.getAll();
    }
    

    // Form submission method
    public String addOffice() {
        dao.save(newOffice);
        newOffice = new Office(); // reset the form
        return null; // stay on the same page
    }
    
    public String deleteOffice() {
    	dao.delete(id);
    	id = 0;
    	return null;
    }

    // Getter and setter for the form object
    public Office getNewOffice() {
        return newOffice;
    }

    public void setNewEmployee(Office newOffice) {
        this.newOffice = newOffice;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
