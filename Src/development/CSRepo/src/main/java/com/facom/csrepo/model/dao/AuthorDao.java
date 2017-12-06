/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model.dao;

import com.facom.csrepo.model.Author;
import com.facom.csrepo.model.Paper;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author jose
 */
public class AuthorDao extends GenericDao<Author>{

    @Override
    public void insert(Author author) {
        openCurrentSessionWithTransaction();
        getCurrentSession().save(author);
        closeCurrenteSessionWithTransaction();
    }
    
    @Override
    public void delete(Author author){
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(author);
        closeCurrenteSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        Author author = findById(id);
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(author);
        closeCurrenteSessionWithTransaction();
    }
    
    @Override
    public List<Author> findAll() {
        openCurrentSession();
        List<Author> authors = getCurrentSession().createQuery("FROM Author").list();
        closeCurrentSession();
        return authors;
    }

    @Override
    public Author findById(Integer id) {
        openCurrentSession();
        Query query = getCurrentSession().createQuery("FROM Paper WHERE id = :id");
        Author author = (Author)query.setParameter("id", id).list().get(0);
        closeCurrentSession();
        
        return author;
    }

    @Override
    public List<Author> findByName(String name) {
        openCurrentSession();
        Query query = getCurrentSession().createQuery("FROM Author WHERE name = :name");
        List<Author> authors = query.setParameter("name", name).list();
        closeCurrentSession();
        
        return authors;
    }
}
