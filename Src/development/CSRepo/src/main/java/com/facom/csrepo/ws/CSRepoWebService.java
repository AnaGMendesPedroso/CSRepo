/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.ws;

import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.ConferenceDao;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

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
     * Retrieves representation of an instance of com.facom.csrepo.ws.CSRepoWebService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Paper/{name}")
    public String getPaper(@PathParam("name") String name) {
        return "Paper " + name;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Conference/get/{name}")
    public String getConference(@PathParam("name") String name) throws SQLException{
        
        ConferenceDao dao = new ConferenceDao();
        Conference conf = dao.searchName(name);
        
        Gson gson = new Gson();
        return gson.toJson(conf);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Conference/list")
    public String listConference() throws SQLException{
        ConferenceDao dao = new ConferenceDao();
        List<Conference> listConferences = dao.search();
        
        Gson gson = new Gson();
        return gson.toJson(listConferences);
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
