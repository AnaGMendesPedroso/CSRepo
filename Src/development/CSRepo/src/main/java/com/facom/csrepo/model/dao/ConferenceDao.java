/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.model.dao;

import com.facom.csrepo.model.Conference;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jose
 */
public class ConferenceDao {
    
    private Session currentSession;
    
    private Transaction currentTransaction;
    
    public Session openCurrentSession(){
        currentSession = getSessionFactory().openSession();
        
        return currentSession;
    }
    
    public Session openCurrentSessionWithTransaction(){
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    
    public void closeCurrentSession(){
        currentSession.close();
    }
    
    public void closeCurrenteSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }
    
    private static SessionFactory getSessionFactory(){
        Configuration config = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        
        SessionFactory sessionFactory = config.buildSessionFactory(builder.build());
        
        return sessionFactory;
    }
    
    public Session getCurrentSession(){
        return currentSession;
    }
    
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
            
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
    
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(Conference conference){
        getCurrentSession().persist(conference);
    }
    
    public List<Conference> findAll(){
        List<Conference> conferences = getCurrentSession().createQuery("FROM Conference").list();
        return conferences;
    }
    
//    private static ConferenceDao instance;
//    protected EntityManager entityManager;
//    private Connection connection;
//    
//    public static ConferenceDao getInstance(){
//        if(instance == null)
//            instance = new ConferenceDao();
//        return instance;
//    }
//    
//    private ConferenceDao(){
//        entityManager = getEntityManager();
//    }
//    
//    private EntityManager getEntityManager(){
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CSRepoPU");
//        if(entityManager == null){
//            entityManager = factory.createEntityManager();
//        }
//        
//        return entityManager;
//    }
//    
//    public Conference getConferenceByAcronym(String acronym){
//        return entityManager.find(Conference.class, acronym);
//    }
//    
//    public List<Conference> findAll(){
//        return entityManager.createQuery("FROM " + Conference.class.getName()).getResultList();
//    }
    
//    public List<Conference> search() throws SQLException{
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM \"Conference\"");
//        ResultSet rs = stmt.executeQuery();
//        
//        List<Conference> listConferences = new ArrayList<>();
//        
//        while(rs.next()){
//            Conference conf = new Conference();
//            conf.setId(rs.getInt("id_Conference"));
//            conf.setAcronym(rs.getString("acronym_Conference"));
//            conf.setName(rs.getString("name_Conference"));
//            
//            listConferences.add(conf);
//        }
//        
//        return listConferences;
//    }
//    
//    public Conference searchName(String name) throws SQLException{
//        String sql = "SELECT * FROM \"Conference\" WHERE \"name_Conference\" ILIKE '%" + name +
//                "%' ORDER BY \"name_Conference\"";
//        PreparedStatement stmt = connection.prepareStatement(sql);
//        ResultSet rs = stmt.executeQuery();
//        
//        rs.next();
//        Conference conf = new Conference();
//        conf.setId(rs.getInt("id_Conference"));
//        conf.setAcronym(rs.getString("acronym_Conference"));
//        conf.setName(rs.getString("name_Conference"));
//        
//        rs.close();
//        stmt.close();
//        
//        return conf;
//    }
}
