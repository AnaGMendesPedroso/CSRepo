/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model.dao;

import com.facom.csrepo.model.Publisher;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author jose
 */
public class PublisherDao extends GenericDao<Publisher>{

    @Override
    public void insert(Publisher publisher) {
        getCurrentSession().save(publisher);
    }

    @Override
    public void delete(Publisher publisher) {
        getCurrentSession().delete(publisher);
    }

    @Override
    public void update(Publisher publisher) {
        getCurrentSession().update(publisher);
    }

    @Override
    public void deleteById(Integer id) {
        Publisher publisher = findById(id);
        getCurrentSession().delete(id);
    }

    @Override
    public List<Publisher> findAll() {
        List<Publisher> publishers = getCurrentSession().createQuery("FROM publisher").list();
        
        return publishers;
    }

    @Override
    public Publisher findById(Integer id) {
        Query query = getCurrentSession().createQuery("FROM publisher WHERE id = :id");
        Publisher publisher = (Publisher)query.setParameter("id", id).list().get(0);
        
        return publisher;
    }

    @Override
    public List<Publisher> findByName(String name) {
        Query query = getCurrentSession().createQuery("FROM publisher WHERE name = :name");
        List<Publisher> publishers = query.setParameter("name", name).list();
        
        return publishers;
    }
    
    public List<Publisher> findByAcronym(String acronym){
        Query query = getCurrentSession().createQuery("FROM Publisher WHERE acronym = :acronym");
        List<Publisher> publishers = query.setParameter("acronym", acronym).list();
        
        return publishers;
    }
}