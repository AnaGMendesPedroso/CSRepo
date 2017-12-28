/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model.dao;

import com.facom.csrepo.model.Paper;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author jose
 */
public class PaperDao extends GenericDao<Paper>{

    @Override
    public void insert(Paper paper) {
        getCurrentSession().save(paper);
    }
    
    @Override
    public void delete(Paper paper) {
        getCurrentSession().delete(paper);
    }

    @Override
    public void deleteById(Integer id) {
        Paper paper = findById(id);
        getCurrentSession().delete(paper);
    }
    
    @Override
    public void update(Paper paper) {
        getCurrentSession().update(paper);
    }
    
    @Override
    public List<Paper> findAll() {
        List<Paper> papers = getCurrentSession().createQuery("FROM Paper").list();
        
        return papers;
    }

    @Override
    public Paper findById(Integer id) {
        Query query = getCurrentSession().createQuery("FROM Paper WHERE id = :id");
        Paper paper = (Paper)query.setParameter("id", id).list().get(0);
        
        return paper;
    }

    @Override
    public List<Paper> findByName(String title) {
        Query query = getCurrentSession().createQuery("FROM Paper WHERE title = :title");
        List<Paper> papers = query.setParameter("title", title).list();
        
        return papers;
    }
}