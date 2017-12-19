/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model.dao;

import com.facom.csrepo.model.Conference;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author jose
 */
public class ConferenceDao extends GenericDao<Conference>{

    @Override
    public void insert(Conference conference){
        openCurrentSessionWithTransaction();
        getCurrentSession().save(conference);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Conference conference) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(conference);
        closeCurrentSessionWithTransaction();
    }
    
    @Override
    public void deleteById(Integer id) {
        Conference conference = findById(id);
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(conference);
        closeCurrentSessionWithTransaction();
    }
    
    @Override
    public void update(Conference conference) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(conference);
        closeCurrentSessionWithTransaction();
    }
    
    @Override
    public List<Conference> findAll() {
        openCurrentSession();
        List<Conference> conferences = getCurrentSession().createQuery("FROM Conference").list();
        closeCurrentSession();
        return conferences;
    }

    @Override
    public Conference findById(Integer id) {
        openCurrentSession();
        Query query = getCurrentSession().createQuery("FROM Conference WHERE id_Conference = :id");
        Conference conference = (Conference)query.setParameter("id", id).list().get(0);
        closeCurrentSession();
        
        return conference;
    }
    
    @Override
    public List<Conference> findByName(String name){
        openCurrentSession();
        Query query = getCurrentSession().createQuery("FROM Conference WHERE lower(name) LIKE lower(:name)");
        List<Conference> conferences = query.setParameter("name", "%" + name + "%").list();
        closeCurrentSession();
        
        return conferences;
    }
}