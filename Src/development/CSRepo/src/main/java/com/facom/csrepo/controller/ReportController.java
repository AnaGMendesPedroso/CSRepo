package com.facom.csrepo.controller;

import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.Edition;
import com.facom.csrepo.model.Paper;
import com.facom.csrepo.model.Report;
import com.facom.csrepo.model.dao.ReportDao;
import java.io.Serializable;
import java.util.Calendar;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author karolina
 */
@ManagedBean(name = "reportController")
@RequestScoped
public class ReportController implements Serializable {

    private Report report;
    private String reportType;
    private boolean disabled = false;
    private ReportDao reportDao = null;

    private Paper reportPaper;

    private Conference reportConference;

    private Edition reportEdition;

    public ReportController() {
        report = new Report();
        reportDao = new ReportDao();
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
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

    public boolean isDisabled() {
        return disabled;
    }

    public void showConferenceErrorReport(Conference reportConference) {
        this.disabled = true;
        this.reportType = "Conference";
        this.report = new Report();
        this.reportConference = reportConference;
        this.reportEdition = null;
        this.reportPaper = null;
    }

    public void showEditionErrorReport(Edition reportEdition) {
        this.disabled = true;
        this.reportType = "Edition";
        this.report = new Report();        
        this.reportConference = null;
        this.reportEdition = reportEdition;
        this.reportPaper = null;
    }

    public void showPaperErrorReport(Paper reportPaper) {
        this.disabled = true;
        this.reportType = "Paper";
        this.report = new Report();        
        this.reportConference = null;
        this.reportEdition = null;
        this.reportPaper = reportPaper;
    }

    public void save() {
        switch (reportType) {
            case "Conference":
                report.setError_type(1);
                break;
            case "Edition":
                report.setError_type(2);
                break;
            case "Paper":
                report.setError_type(3);
                break;
            default:
                report.setError_type(0);
                break;
        }
        
        report.setCreated_on(Calendar.getInstance());
        reportDao.insert(report);
    }
}
