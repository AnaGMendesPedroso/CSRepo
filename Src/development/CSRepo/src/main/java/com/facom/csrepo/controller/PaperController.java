package com.facom.csrepo.controller;

import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.dao.PaperDao;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 *
 */
@ManagedBean(name = "paperController")
@SessionScoped
public class PaperController implements Serializable {

    private PaperDao paperDao = null;

    @ManagedProperty(value = "#{editionController.selected.papers}")
    private List<Paper> items = null;
    private Paper selected;

    public PaperController() {
        paperDao = new PaperDao();
    }

    public Paper getSelected() {
        return selected;
    }

    public void setSelected(Paper selected) {
        this.selected = selected;
    }

    public List<Paper> getItems() {
        if (items == null) {
            items = paperDao.findAll();
        }

        return items;
    }
    
    public void setItems(List<Paper> items) {
        this.items = items;
    }
}
