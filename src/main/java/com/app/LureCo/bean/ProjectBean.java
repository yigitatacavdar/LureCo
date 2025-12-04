package com.app.LureCo.bean;

import jakarta.inject.Named;


import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

import com.app.LureCo.dao.ProjectDAO;
import com.app.LureCo.entity.Project;

@Named("projectBean")
@SessionScoped
public class ProjectBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProjectDAO dao = new ProjectDAO();
    
    private Project newProject = new Project();
    
    private int id;

    public List<Project> getProjects() {
        return dao.getAll();
    }
    

    // Form submission method
    public String addProject() {
        dao.save(newProject);
        newProject = new Project(); // reset the form
        return null; // stay on the same page
    }
    
    public String deleteProject() {
    	dao.delete(id);
    	id = 0;
    	return null;
    }

    // Getter and setter for the form object
    public Project getNewProject() {
        return newProject;
    }

    public void setNewProject(Project newProject) {
        this.newProject = newProject;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
