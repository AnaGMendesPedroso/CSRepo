package com.facom.csrepo.controller;

import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.dao.ConferenceDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author karolina
 */
@ManagedBean(name = "conferenceController")
@RequestScoped
public class ConferenceController implements Serializable {

    private ConferenceDao conferenceDao = null;

    private Conference selected;
    private List<Conference> items = null;
    private List<Conference> filteredConferences = null;
    
    public ConferenceController() {
        conferenceDao = new ConferenceDao();
        
        conferenceDao.openCurrentSession();
        items = conferenceDao.findAll();
        conferenceDao.closeCurrentSession();
    }

    public Conference getSelected() {
        return selected;
    }

    public void setSelected(Conference selected) {
        this.selected = selected;
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
