/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jose
 */
@Entity
@Table(name = "Paper")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paper.findAll", query = "SELECT p FROM Paper p")
    , @NamedQuery(name = "Paper.findByid", query = "SELECT p FROM Paper p WHERE p.id = :id")
    , @NamedQuery(name = "Paper.findBytitle", query = "SELECT p FROM Paper p WHERE p.title = :title")
    , @NamedQuery(name = "Paper.findBypages", query = "SELECT p FROM Paper p WHERE p.pages = :pages")
    , @NamedQuery(name = "Paper.findByyearPublication", query = "SELECT p FROM Paper p WHERE p.yearPublication = :yearPublication")
    , @NamedQuery(name = "Paper.findByfirstPage", query = "SELECT p FROM Paper p WHERE p.firstPage = :firstPage")
    , @NamedQuery(name = "Paper.findBylastPage", query = "SELECT p FROM Paper p WHERE p.lastPage = :lastPage")})
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paper")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title_paper")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pages_paper")
    private int pages;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year_Publication_paper")
    private int yearPublication;
    @Basic(optional = false)
    @NotNull
    @Column(name = "first_Page_paper")
    private int firstPage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_Page_paper")
    private int lastPage;
    
    @ManyToOne
    private Edition edition;
    
    @ManyToOne
    @JoinColumn(name = "id_conference")
    private Conference conference;
    
    @ManyToOne
    @JoinColumn(name = "id_publisher")
    private Publisher publisher;
    
    @ManyToMany(mappedBy="papers", cascade=CascadeType.MERGE)
    private List<Author> authors = new ArrayList<>();

    public Paper() {
    }

    public Paper(Integer id) {
        this.id = id;
    }

    public Paper(Integer id, String title, int pages, int yearPublication, int firstPage, int lastPage) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.yearPublication = yearPublication;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paper)) {
            return false;
        }
        Paper other = (Paper) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.facom.csrepo.model.Paper[ id=" + id + " ]";
    }
    
}
