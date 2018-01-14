/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.service;

import com.facom.csrepo.model.Author;
import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.dao.AuthorDao;
import com.facom.csrepo.model.dao.ConferenceDao;
import com.facom.csrepo.model.dao.PaperDao;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.hibernate.Query;

/**
 * REST Web Service
 *
 * @author jose
 */
@Path("api")
public class CSRepoWebService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CSRepoWebService
     */
    public CSRepoWebService() {
    }

    /**
     * Retrieves representation of an instance of com.facom.csrepo.service.CSRepoWebService
     * @param titlePaper
     * @param nameAuthor
     * @param nameConference
     * @param yearPaper
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("paper")
    public String getPaper(@QueryParam("title") String titlePaper,
                            @QueryParam("conference") String nameConference,
                            @QueryParam("author") String nameAuthor,
                            @QueryParam("year") Integer yearPaper){
        
        Author author = null;
        Conference conference = null;
        JsonObject resultJson = new JsonObject();
        JsonArray papersJson = new JsonArray();
        
        if(!nameConference.equals("")){
            ConferenceDao conferenceDao = new ConferenceDao();
            
            conferenceDao.openCurrentSession();
            conference = conferenceDao.findByName(nameConference).get(0);
            conferenceDao.closeCurrentSession();
        }
        
        if(!nameAuthor.equals("")){
            AuthorDao authorDao = new AuthorDao();
            
            authorDao.openCurrentSession();
            author = authorDao.findByName(nameAuthor).get(0);
            authorDao.closeCurrentSession();
        }
//SELECT p.id, p.title, p.pages, p.yearPublication, p.firstPage, p.lastPage, p.conference, p.publisher, p.edition
        String sql = " "
                + "FROM Paper p "
                + "JOIN p.authors a"
                + " WHERE ((:title_paper IS NULL) OR title_paper LIKE :title_paper) AND"
                + "((:year IS NULL) OR year_publication_paper = :year) AND"
                + "((:conference_id IS NULL) OR conference_paper = :conference_id) AND"
                + "((:author_id IS NULL) OR a.id = :author_id)";
        
        PaperDao paperDao = new PaperDao();
        paperDao.openCurrentSession();
        
        Query query = paperDao.getCurrentSession().createQuery(sql);
        query.setParameter("year", yearPaper);
        
        if(!titlePaper.equals(""))
            query.setParameter("title_paper", "%"+titlePaper+"%");
        else
            query.setParameter("title_paper", null);
        if(conference != null)
            query.setParameter("conference_id", conference.getId());
        else
            query.setParameter("conference_id", null);
        if(author != null)
            query.setParameter("author_id", author.getId());
        else
            query.setParameter("author_id", null);
        
        List<Object> result = (List<Object>)query.list();
        
        resultJson.addProperty("totalFound", result.size());
        
        Iterator itr = result.iterator();
        
        while(itr.hasNext()){
            Object[] obj = (Object[])itr.next();
            
            Integer id = Integer.parseInt(String.valueOf(obj[0]).split(" ")[1].split("=")[1]);
            Paper paper = paperDao.findById(id);
            List<Author> authors = paper.getAuthors();
            
            JsonObject paperJson = new JsonObject();
            JsonArray authorsJson = new JsonArray();
            
            for(Author tmpAuthor : authors){
                JsonObject singleAuthor = new JsonObject();
                singleAuthor.addProperty("name", tmpAuthor.getName());
                
                authorsJson.add(singleAuthor);
            }
            
            paperJson.addProperty("title", paper.getTitle());
            paperJson.addProperty("pages", paper.getPages());
            paperJson.addProperty("first_page", paper.getFirstPage());
            paperJson.addProperty("last_page", paper.getLastPage());
            paperJson.addProperty("year", paper.getYearPublication());
            paperJson.addProperty("conference", paper.getConference().getName());
            paperJson.addProperty("publisher", paper.getPublisher().getAcronym());
            paperJson.add("authors", authorsJson);
            
            papersJson.add(paperJson);
        }
        
        resultJson.add("papers", papersJson);
        
        paperDao.closeCurrentSession();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        
        return gson.toJson(resultJson);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("conference/get/{name}")
    public String getConference(@PathParam("name") String name) throws SQLException{
        
        ConferenceDao dao = new ConferenceDao();
        dao.openCurrentSession();
        List<Conference> confs = dao.findByName(name);
        dao.closeCurrentSession();
        
        Gson gson = new Gson();
        return gson.toJson(confs);
//        return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("conference/list")
    public String listConference() throws SQLException{
        
        ConferenceDao dao = new ConferenceDao();
        List<Conference> listConferences = dao.findAll();
        //List<Conference> listConferences = dao.search();
        
        Gson gson = new Gson();
        return gson.toJson(listConferences);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("conference/insert")
    public String insertConference(@QueryParam("acronym") String acronym,
                                    @QueryParam("name") String name) throws SQLException{
        
        ConferenceDao dao = new ConferenceDao();
        dao.openCurrentSessionWithTransaction();
        dao.insert(new Conference(acronym, name));
        dao.closeCurrentSessionWithTransaction();
        
        return "";
    }

    /**
     * PUT method for updating or creating an instance of CSRepoWebService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}