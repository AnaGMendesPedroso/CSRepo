/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo;

import com.facom.csrepo.model.Author;
import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.Edition;
import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.dao.AuthorDao;
import com.facom.csrepo.model.dao.ConferenceDao;
import com.facom.csrepo.model.dao.EditionDao;
import com.facom.csrepo.model.dao.PaperDao;
import java.util.List;

/**
 *
 * @author jose
 */
public class TestDao {
    public static void main(String[] args){
        Paper paper = new Paper("The title of the work", 37, 2015, 90, 127);
        Author author = new Author("Peter Adams");
       
        author.addPaper(paper);
       
        Conference conference = new Conference("Mostratec", "Mostratec");
        Edition edition = new Edition(2015);
        
        conference.getEditions().add(edition);
        edition.addPaper(paper);
        
        AuthorDao aDao = new AuthorDao();
        PaperDao pDao = new PaperDao();
        ConferenceDao cDao = new ConferenceDao();
        EditionDao eDao = new EditionDao();
        
        if (aDao.findByName(author.getName()).isEmpty()) {
            aDao.insert(author);
        }
        
        if (pDao.findByName(paper.getTitle()).isEmpty()) {
            pDao.insert(paper);
        }
        
        if (cDao.findByName(conference.getName()).isEmpty()) {
            cDao.insert(conference);
        }
        
        if (eDao.findByNameAndYear(conference.getName(), edition.getYear()).isEmpty()) {
            eDao.insert(edition);
        } 
                
    }
}