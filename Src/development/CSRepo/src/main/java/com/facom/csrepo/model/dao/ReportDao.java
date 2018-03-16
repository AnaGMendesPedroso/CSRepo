package com.facom.csrepo.model.dao;

import com.facom.csrepo.model.Report;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author karolina
 */
public class ReportDao extends GenericDao<Report> {

    @Override
    public void insert(Report report) {
        openCurrentSessionWithTransaction();
        getCurrentSession().save(report);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Report report) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(report);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Report report) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(report);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteById(Integer id) {
        Report report = findById(id);
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(report);
        closeCurrentSessionWithTransaction();
    }

    @Override
    public List<Report> findAll() {
        openCurrentSession();
        List<Report> reports = getCurrentSession().createQuery("FROM Report").list();
        closeCurrentSession();
        return reports;
    }

    @Override
    public Report findById(Integer id) {
        openCurrentSession();
        Query query = getCurrentSession().createQuery("FROM Report WHERE id = :id");
        Report report = (Report) query.setParameter("id", id).list().get(0);
        closeCurrentSession();
        return report;
    }

    @Override
    public List<Report> findByName(String name) {
        return null;
    }

}
