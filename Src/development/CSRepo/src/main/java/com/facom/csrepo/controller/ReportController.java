package com.facom.csrepo.controller;

import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.Edition;
import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.Report;
import com.facom.csrepo.model.dao.ReportDao;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author karolina
 */
@ManagedBean(name = "reportController")
@SessionScoped
public class ReportController implements Serializable {

    private Report report;
    private ReportDao reportDao = null;

    //@ManagedProperty(value = "#{paperController.selectedPapers.get(0)}")
    private Paper reportPaper;

    //@ManagedProperty(value = "#{conferenceController.selectedConferences.get(0)}")
    private Conference reportConference;

    //@ManagedProperty(value = "#{editionController.selected}")
    private Edition reportEdition;

    public ReportController() {
        reportDao = new ReportDao();
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Paper getReportPaper() {
        return reportPaper;
    }

    public void setReportPaper(Paper reportPaper) {
        this.reportPaper = reportPaper;
    }

    public Conference getReportConference() {
        return reportConference;
    }

    public void setReportConference(Conference reportConference) {
        this.reportConference = reportConference;
    }

    public Edition getReportEdition() {
        return reportEdition;
    }

    public void setReportEdition(Edition reportEdition) {
        this.reportEdition = reportEdition;
    }

    public void save() {
        reportDao.insert(report);
    }
}
