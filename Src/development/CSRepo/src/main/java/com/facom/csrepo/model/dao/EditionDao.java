/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model.dao;

import com.facom.csrepo.model.Edition;
import com.facom.csrepo.model.Paper;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author jose
 */
public class EditionDao extends GenericDao<Edition>{
    
    @Override
    public void insert(Edition edition){
        getCurrentSession().save(edition);
    }
    
    @Override
    public void delete(Edition edition) {
        getCurrentSession().delete(edition);
    }

    @Override
    public void deleteById(Integer id) {
        Edition edition = findById(id);
        getCurrentSession().delete(edition);
    }
    
    @Override
    public void update(Edition edition) {
        getCurrentSession().update(edition);
    }
    
    @Override
    public List<Edition> findAll() {
        List<Edition> editions = getCurrentSession().createQuery("FROM Edition").list();

        return editions;
    }

    @Override
    public Edition findById(Integer id) {
        Query query = getCurrentSession().createQuery("FROM Edition WHERE id = :id");
        Edition edition = (Edition)query.setParameter("id", id).list().get(0);
        
        return edition;
    }
   
    @Override
    public List<Edition> findByName(String name) {
        Query query = getCurrentSession().createQuery("FROM Edition WHERE name = :name");
        List<Edition> editions = query.setParameter("name", name).list();
        
        return editions;
    }
    
    public List<Edition> findByConferenceId(int conferenceId){
        Query query = getCurrentSession().createQuery("FROM Edition WHERE conference_edition = :id");
        List<Edition> editions = query.setParameter("id", conferenceId).list();
        
        return editions;
    }
}