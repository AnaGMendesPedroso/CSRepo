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
import com.facom.csrepo.model.Publisher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
public class ModelTest {
    public static void main(String[] args) {
        Conference conf = new Conference();
        conf.setName("Javaneiros");
        conf.setAcronym("Javaneiros");
        
        Publisher publisher = new Publisher();
        publisher.setAcronym("IEEE");
        publisher.setName("Institute of Electrical and Electronics Engineers");
        publisher.setLink("http://ieeexploreapi.ieee.org/api/v1/search/articles?");
        
        Paper paper = new Paper();
        paper.setTitle("Paper 1");
        paper.setFirstPage(4);
        paper.setLastPage(8);
        paper.setPages(4);
        paper.setYearPublication(2017);
        List<Paper> listPapers = new ArrayList<>();
        listPapers.add(paper);
        
        Author author = new Author();
        author.setName("Jose Rafael");
        List<Author> listAuthor = new ArrayList<>();
        listAuthor.add(author);
        
        Edition edition = new Edition();
        edition.setYear(2017);
        edition.setConference(conf);
        List<Edition> listEditions = new ArrayList<>();
        listEditions.add(edition);
        
        paper.setEdition(edition);
        paper.setAuthors(listAuthor);
        author.setPapers(listPapers);
        //conf.setEditions(listEditions);
        publisher.setEditions(listEditions);
    }
}
