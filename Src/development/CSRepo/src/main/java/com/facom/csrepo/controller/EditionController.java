package com.facom.csrepo.controller;

import com.facom.csrepo.model.Edition;
import com.facom.csrepo.model.dao.EditionDao;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * 
 */
@ManagedBean(name = "editionController")
@SessionScoped
public class EditionController implements Serializable {

    private EditionDao editionDao = null;

    @ManagedProperty(value = "#{conferenceController.selected.editions}")
    private List<Edition> items = null;
    private Edition selected;

    public EditionController() {
        editionDao = new EditionDao();
    }

    public List<Edition> getItems() {
        if (items == null) {
            items = editionDao.findAll();
        }
        
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
}
