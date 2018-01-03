package com.facom.csrepo.controller;

import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.dao.ConferenceDao;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 *
 */
@ManagedBean(name = "conferenceController")
@SessionScoped
public class ConferenceController implements Serializable {

    private ConferenceDao conferenceDao = null;

    private Conference selected;
    private List<Conference> items = null;
    private List<Conference> selectedConferences = null;
    private List<Conference> filteredConferences = null;
    
    public ConferenceController() {
        conferenceDao = new ConferenceDao();
        items = conferenceDao.findAll();
    }

    public Conference getSelected() {
        return selected;
    }

    public void setSelected(Conference selected) {
        this.selected = selected;
    }
    
    public List<Conference> getSelectedConferences() {
        return selectedConferences;
    }

    public void setSelectedConferences(List<Conference> selectedConferences) {
        this.selectedConferences = selectedConferences;
    }

    public List<Conference> getFilteredConferences() {
        return filteredConferences;
    }

    public void setFilteredConferences(List<Conference> filteredConferences) {
        this.filteredConferences = filteredConferences;
    }
    
    public List<Conference> getItems() {
        if (items == null) {
            items = conferenceDao.findAll();
        }

        return items;
    }
}
