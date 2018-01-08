/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model.dao;

import com.facom.csrepo.model.Edition;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author jose
 */
public class EditionDao extends GenericDao<Edition> {

    @Override
    public void insert(Edition edition) {
        openCurrentSessionWithTransaction();
        getCurrentSession().save(edition);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Edition edition) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(edition);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        Edition edition = findById(id);
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(edition);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Edition edition) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(edition);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public List<Edition> findAll() {
        openCurrentSession();
        List<Edition> editions = getCurrentSession().createQuery("FROM Edition").list();
//        closeCurrentSession();
        return editions;
    }

    @Override
    public Edition findById(Integer id) {
        openCurrentSession();
        Query query = getCurrentSession().createQuery("FROM Edition WHERE id = :id");
        Edition edition = (Edition) query.setParameter("id", id).list().get(0);
//        closeCurrentSession();

        return edition;
    }

    @Override
    public List<Edition> findByName(String name) {
        openCurrentSession();
        Query query = getCurrentSession().createQuery("FROM Edition WHERE name = :name");
        List<Edition> editions = query.setParameter("name", name).list();
//        closeCurrentSession();

        return editions;
    }
    
    public List<Edition> findByNameAndYear(String name, Integer year) {
        openCurrentSession();
        Query query = getCurrentSession().createQuery("FROM Edition e join e.conferenceEdition c WHERE c.name = :name AND e.year = :year");
        query.setParameter("name", name);
        query.setParameter("year", year);
        List<Edition> editions = query.list();
//        closeCurrentSession();

        return editions;
    }
}
