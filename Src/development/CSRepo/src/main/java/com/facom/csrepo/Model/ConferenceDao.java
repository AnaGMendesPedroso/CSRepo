/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
public class ConferenceDao {
    private Connection connection;
    
    public ConferenceDao(){
        connection = new ConnectionFactory().getConnection();
    }
    
    public List<Conference> buscar() throws SQLException{
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Conference");
        ResultSet rs = stmt.executeQuery();
        
        List<Conference> listConferences = new ArrayList<>();
        
        while(rs.next()){
            Conference conf = new Conference();
            conf.setId(rs.getLong("id_Conference"));
            conf.setAcronym(rs.getString("acronym_Conference"));
            conf.setName(rs.getString("name_Conference"));
            
            listConferences.add(conf);
        }
        
        return listConferences;
    }
    
    public Conference buscarNome(String name) throws SQLException{
        String sql = "SELECT * FROM \"Conference\" WHERE \"name_Conference\" LIKE '%" + name + "%'";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        rs.next();
        Conference conf = new Conference();
        conf.setId(rs.getLong("id_Conference"));
        conf.setAcronym(rs.getString("acronym_Conference"));
        conf.setName(rs.getString("name_Conference"));
        
        rs.close();
        stmt.close();
        
        return conf;
    }
}
