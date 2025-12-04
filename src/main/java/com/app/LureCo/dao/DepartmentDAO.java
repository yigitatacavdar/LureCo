package com.app.LureCo.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.LureCo.util.HibernateUtil;
import com.app.LureCo.entity.Department;

public class DepartmentDAO {
    public void save(Department department) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(department);
            tx.commit();
        }
    }
    
    public List<Department> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Department", Department.class).list();
        }
    }
    
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Department employee = session.get(Department.class, id);
            if (employee != null) {
                session.remove(employee);
                System.out.println("Deleted department with id:" + id);
            } else {
                System.out.println("department with id " + id + " not found.");
            }

            transaction.commit();
        }
    }
}