package com.facom.csrepo.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Karolina
 */
@Entity
@Table(name = "Report")
@XmlRootElement
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_report")
    private Long id;

    private String name;
    private String email;
    private String description;
    
    private Integer error_type;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created_on;

    @OneToOne
    @JoinColumn(name = "id_conference")
    private Conference conference;

    @OneToOne
    @JoinColumn(name = "id_edition")
    private Edition edition;

    @OneToOne
    @JoinColumn(name = "id_paper")
    private Paper paper;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getError_type() {
        return error_type;
    }

    public void setError_type(Integer error_type) {
        this.error_type = error_type;
    }
    
    public Calendar getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Calendar created_on) {
        this.created_on = created_on;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}
