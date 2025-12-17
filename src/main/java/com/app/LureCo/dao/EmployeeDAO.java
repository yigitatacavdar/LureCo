package com.app.LureCo.dao;

import java.util.ArrayList;



import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.LureCo.util.HibernateUtil;
import com.app.LureCo.entity.Employee;

public class EmployeeDAO {
    public void save(Employee employee) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(employee);
            tx.commit();
        }
    }
    
    public List<Employee> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }
    
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.remove(employee);
                System.out.println("Deleted employee with id:" + id);
            } else {
                System.out.println("employee with id " + id + " not found.");
            }

            transaction.commit();
        }
    }
    
    public void update(int id, Employee updated) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Employee existing = session.get(Employee.class, id);
            if (existing == null) {
                throw new RuntimeException("Employee not found: " + id);
            }

            if (updated.getName() != null) {
                existing.setName(updated.getName());
            }

            if (updated.getDepartment() != null) {
                existing.setDepartment(updated.getDepartment());
            }

            if (updated.getOffice() != null) {
                existing.setOffice(updated.getOffice());
            }

            if (updated.getRole() != null) {
                existing.setRole(updated.getRole());
            }

            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    
    public List<Employee> search(String department, String office, String role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if (department != null && !department.isBlank()) {
            // case-insensitive
            predicates.add(cb.equal(cb.lower(root.get("department")), department.toLowerCase()));
        }

        if (office != null && !office.isBlank()) {
            predicates.add(cb.equal(cb.lower(root.get("office")), office.toLowerCase()));
        }

        if (role != null && !role.isBlank()) {
            predicates.add(cb.equal(cb.lower(root.get("role")), role.toLowerCase()));
        }


        cq.select(root).where(predicates.toArray(new Predicate[0]));

        List<Employee> results = session.createQuery(cq).getResultList();
        session.close();
        return results;
    }

}