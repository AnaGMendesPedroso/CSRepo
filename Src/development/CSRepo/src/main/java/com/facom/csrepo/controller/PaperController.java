package com.facom.csrepo.controller;

import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.dao.PaperDao;
import com.facom.csrepo.util.JsfUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author karolina
 */
@ManagedBean(name = "paperController")
@RequestScoped
public class PaperController implements Serializable {

    private String content;
    private PaperDao paperDao = null;
    private StreamedContent file = null;

    @ManagedProperty(value = "#{editionController.selected.papers}")
    private List<Paper> items = null;
    
    private Paper selected;
    private List<Paper> selectedPapers = new ArrayList<>();
    private List<Paper> filteredPapers = new ArrayList<>();

    public PaperController() {
        paperDao = new PaperDao();
    }

    public Paper getSelected() {
        return selected;
    }

    public void setSelected(Paper selected) {
        this.selected = selected;
    }
    
    public List<Paper> getSelectedPapers() {
        return selectedPapers;
    }

    public void setSelectedPapers(List<Paper> selectedPapers) {
        this.selectedPapers = selectedPapers;
    }

    public List<Paper> getFilteredPapers() {
        return filteredPapers;
    }

    public void setFilteredPapers(List<Paper> filteredPapers) {
        this.filteredPapers = filteredPapers;
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

    public String getContent() {
        content = "";
        if (selectedPapers.isEmpty()) {
            selectedPapers = items;
        }
        
        for (Paper p : selectedPapers) {
            content = "@article{" + p.getId() + ",\n"
                    + "author = ";

            for (int i = 0; i < p.getAuthors().size(); i++) {
                content += "{" + p.getAuthors().get(i).getName() + "}";

                if (i + 1 < p.getAuthors().size()) {
                    content += " and";
                }

                content += "\n";
            }

            content += "title = {" + p.getTitle() + "},\n"
                    + "journal = {" + p.getConference().getName() + "},\n"
                    + "year = " + p.getYearPublication() + ",\n"
                    + "pages = {" + p.getFirstPage() + "-" + p.getLastPage() + "}\n"
                    + "}\n";
        }

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StreamedContent getFile() {
        try {
            ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
            bOutput.write(getContent().getBytes());
            ByteArrayInputStream bInput = new ByteArrayInputStream(bOutput.toByteArray());
            file = new DefaultStreamedContent(bInput, "text/plain", "ref.bib");

        } catch (IOException ex) {
            JsfUtil.addErrorMessage(ex, "Could not load the paper.");
            Logger.getLogger(PaperController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return file;
    }
}
