/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.service;

import com.facom.csrepo.wrappers.UpdateWrapper;
import com.facom.csrepo.wrappers.WrapperIEEE;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author jose
 */
public class ServletListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        Calendar scheduleUpdate = Calendar.getInstance();
        scheduleUpdate.set(Calendar.MINUTE, 37);
        scheduleUpdate.set(Calendar.SECOND, 0);
        System.out.println(scheduleUpdate.getTime());
        Date data = scheduleUpdate.getTime();
        
        UpdateWrapper uw = new UpdateWrapper(data);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
