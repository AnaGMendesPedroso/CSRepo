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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jose
 */
@Entity
@Table(name = "Edition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Edition.findAll", query = "SELECT e FROM Edition e")
    , @NamedQuery(name = "Edition.findByid", query = "SELECT e FROM Edition e WHERE e.id = :id")
    , @NamedQuery(name = "Edition.findByyear", query = "SELECT e FROM Edition e WHERE e.year = :year")})
public class Edition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_edition")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "year_edition")
    private int year;

    @JoinColumn(name = "conference_edition", referencedColumnName = "id_conference")
    @ManyToOne(optional = false)
    private Conference conferenceEdition;

    @JoinColumn(name = "publisher_edition", referencedColumnName = "id_publisher")
    @ManyToOne
    private Publisher publisher;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_edition")
    private List<Paper> papers = new ArrayList<>();

    public Edition() {
    }

    public Edition(int year) {
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public void addPaper(Paper paper) {
        this.papers.add(paper);
    }

    @XmlTransient
    public Conference getConferenceEdition() {
        return conferenceEdition;
    }

    public void setConferenceEdition(Conference conferenceEdition) {
        this.conferenceEdition = conferenceEdition;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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
        if (!(object instanceof Edition)) {
            return false;
        }
        Edition other = (Edition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.facom.csrepo.model.Edition[ id=" + id + " ]";
    }

}
