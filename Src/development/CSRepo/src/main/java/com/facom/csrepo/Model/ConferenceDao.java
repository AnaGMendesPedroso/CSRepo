/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.Model;

import java.sql.Connection;

/**
 *
 * @author jose
 */
public class ConferenceDao {
    private Connection connection;
    
    public ConferenceDao(){
        connection = new ConnectionFactory().getConnection();
    }
    
    public void buscar(){
        
    }
}
