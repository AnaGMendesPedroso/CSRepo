/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jose
 * @param <T>
 */
public abstract class GenericDao<T> {
    
    private Session currentSession;
    
    private Transaction currentTransaction;
    
    public Session openCurrentSession(){
        currentSession = getSessionFactory().openSession();
        
        return currentSession;
    }
    
    public Session openCurrentSessionWithTransaction(){
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    
    public void closeCurrentSession(){
        currentSession.close();
    }
    
    public void closeCurrenteSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }
    
    private static SessionFactory getSessionFactory(){
        Configuration config = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        
        SessionFactory sessionFactory = config.buildSessionFactory(builder.build());
        
        return sessionFactory;
    }
    
    public Session getCurrentSession(){
        return currentSession;
    }
    
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
            
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
    
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public abstract void insert(T t);
    
    public abstract void delete(T t);
    
    public abstract void deleteById(Integer id);
    
    public abstract List<T> findAll();
    
    public abstract T findById(Integer id);
    
    public abstract List<T> findByName(String name);    
}
