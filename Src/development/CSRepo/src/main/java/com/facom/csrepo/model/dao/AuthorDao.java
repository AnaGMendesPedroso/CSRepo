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
        getCurrentSession().persist(author);
    }
    
    @Override
    public void delete(Author author){
        getCurrentSession().delete(author);
    }

    @Override
    public void deleteById(Integer id) {
        Author author = findById(id);
        getCurrentSession().delete(author);
    }
    
    @Override
    public List<Author> findAll() {
        List<Author> authors = getCurrentSession().createQuery("FROM Author").list();
        return authors;
    }

    @Override
    public Author findById(Integer id) {
        Query query = getCurrentSession().createQuery("FROM Paper WHERE id = :id");
        Author author = (Author)query.setParameter("id", id).list().get(0);
        
        return author;
    }

    @Override
    public List<Author> findByName(String name) {
        Query query = getCurrentSession().createQuery("FROM Author WHERE name = :name");
        List<Author> authors = query.setParameter("name", name).list();
        
        return authors;
    }
}
