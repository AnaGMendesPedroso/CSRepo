package com.facom.csrepo.controller;

import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.Edition;
import com.facom.csrepo.model.dao.EditionDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author karolina
 */
@ManagedBean(name = "editionController")
@RequestScoped
public class EditionController implements Serializable {

    private EditionDao editionDao = null;

    private Conference selectedConference;

    private List<Edition> items = null;
    private Edition selected;

    public EditionController() {
        editionDao = new EditionDao();
    }

    public List<Edition> getItems() {
        editionDao.openCurrentSession();
        if (selectedConference != null) {
            items = editionDao.findByConferenceId(selectedConference.getId());
        } else {
            items = editionDao.findAll();
        }
        editionDao.closeCurrentSession();

        return items;
    }

    public void setItems(List<Edition> items) {
        this.items = items;
    }

    public Edition getSelected() {
        return selected;
    }

    public void setSelected(Edition selected) {
        this.selected = selected;
    }

    public Conference getSelectedConference() {
        return selectedConference;
    }

    public void setSelectedConference(Conference selectedConference) {
        this.selectedConference = selectedConference;
    }
}
