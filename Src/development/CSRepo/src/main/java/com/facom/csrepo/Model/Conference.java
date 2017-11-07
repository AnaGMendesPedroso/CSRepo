/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model;

/**
 *
 * @author jose
 */
public class Conference {
    private Long id;
    private String acronym;
    private String name;
    
    public Conference(){
        
    }
    
    public Conference(String acronym, String name){
        this.acronym = acronym;
        this.name = name;
    }
    
    public Conference(Long id, String acronym, String name){
        this.id = id;
        this.acronym = acronym;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
