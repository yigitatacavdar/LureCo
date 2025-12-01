package com.app.LureCo.dao;


import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.LureCo.util.HibernateUtil;
import com.app.LureCo.entity.Office;

public class OfficeDAO {
    public void save(Office office) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(office);
            tx.commit();
        }
    }
    
    public List<Office> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Office", Office.class).list();
        }
    }
    
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Office employee = session.get(Office.class, id);
            if (employee != null) {
                session.remove(employee);
                System.out.println("Deleted office with id:" + id);
            } else {
                System.out.println("office with id " + id + " not found.");
            }

            transaction.commit();
        }
    }
}