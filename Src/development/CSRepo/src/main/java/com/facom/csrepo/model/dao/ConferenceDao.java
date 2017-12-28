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
        getCurrentSession().save(conference);
    }

    @Override
    public void delete(Conference conference) {
        getCurrentSession().delete(conference);
    }
    
    @Override
    public void deleteById(Integer id) {
        Conference conference = findById(id);
        getCurrentSession().delete(conference);
    }
    
    @Override
    public void update(Conference conference) {
        getCurrentSession().update(conference);
    }
    
    @Override
    public List<Conference> findAll() {
        List<Conference> conferences = getCurrentSession().createQuery("FROM Conference").list();
        
        return conferences;
    }

    @Override
    public Conference findById(Integer id) {
        Query query = getCurrentSession().createQuery("FROM Conference WHERE id_Conference = :id");
        Conference conference = (Conference)query.setParameter("id", id).list().get(0);
        
        return conference;
    }
    
    @Override
    public List<Conference> findByName(String name){
        Query query = getCurrentSession().createQuery("FROM Conference WHERE lower(name) LIKE lower(:name)");
        List<Conference> conferences = query.setParameter("name", "%" + name + "%").list();
        
        return conferences;
    }
}