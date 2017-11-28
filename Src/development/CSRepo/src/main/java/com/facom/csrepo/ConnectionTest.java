/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo;

import com.facom.csrepo.model.dao.ConferenceDao;
import com.facom.csrepo.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jose
 */
public class ConnectionTest {
    public static void main(String[] args) throws SQLException{
        Connection con = new ConnectionFactory().getConnection();
        
        //ConferenceDao dao = new ConferenceDao();
        
        //System.out.println(dao.searchName("est").getName());
        
        if(con != null){
            System.out.println("Conectado");
        } else
            System.out.println("Falhouuu");
    }
}
