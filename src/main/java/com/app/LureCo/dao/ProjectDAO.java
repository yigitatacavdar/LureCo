package com.app.LureCo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.LureCo.util.HibernateUtil;
import com.app.LureCo.entity.Project;

public class ProjectDAO {
    public void save(Project project) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(project);
            tx.commit();
        }
    }
    
    public List<Project> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Project", Project.class).list();
        }
    }
    
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Project employee = session.get(Project.class, id);
            if (employee != null) {
                session.remove(employee);
                System.out.println("Deleted project with id:" + id);
            } else {
                System.out.println("project with id " + id + " not found.");
            }

            transaction.commit();
        }
    }
}