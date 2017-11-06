/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo;

import com.facom.csrepo.Model.ConnectionFactory;
import java.sql.Connection;

/**
 *
 * @author jose
 */
public class ConnectionTest {
    public static void main(String[] args){
        Connection con = new ConnectionFactory().getConnection();
        
        if(con != null){
            System.out.println("Conectado");
        } else
            System.out.println("Falhouuu");
    }
}
