/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo;

import com.facom.csrepo.model.Author;
import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.dao.AuthorDao;
import com.facom.csrepo.model.dao.ConferenceDao;
import java.util.List;

/**
 *
 * @author jose
 */
public class TestDao {
    public static void main(String[] args){
        /*Paper paper = new Paper("Teste Paperr", 37, 2015, 90, 127);
        Author author = new Author("Vsandis Aaafdreia");
       
        //author.addPaper(paper);
       
        AuthorDao aDao = new AuthorDao();
        
        List<Author> listAuthor = aDao.findByName(author.getName());
        //List<Paper> listPaper = 
        Author author1 = listAuthor.get(0);
        author1.addPaper(paper);
        
        if(listAuthor.size() > 0){
            aDao.update(author1);
        }else{
            aDao.insert(author);
        }*/
        
        ConferenceDao conferenceDao = new ConferenceDao();
        List<Conference> conferencias = conferenceDao.findAll();
        
        for (Conference c: conferencias) {
            System.out.println(c.getAcronym() + " -> " + c.getName());
        }
        
    }
}