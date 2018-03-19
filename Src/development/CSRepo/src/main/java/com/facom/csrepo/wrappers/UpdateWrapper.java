/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.wrappers;

import com.facom.csrepo.model.Conference;
import com.facom.csrepo.model.dao.ConferenceDao;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Renato Gouvea
 */
public class UpdateWrapper {

    Timer timer;

    public UpdateWrapper() {
        timer = new Timer();
        //Temp
//        timer.schedule(new RemindTask(), fiveMinutes());
        //Temp
        scheduleNextUpdate();
    }
    
    public void scheduleNextUpdate(){
        Date date = getNextMonth();
        //Date date = fiveMinutes();
        timer.schedule(new RemindTask(), date);
    }
    
    public Date fiveMinutes(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+3);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTime());
        
        Date date = calendar.getTime();
        
        return date;
    }
    
    public Date getNextMonth(){
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.set(Calendar.MONTH, nextMonth.get(Calendar.MONTH)+1);
        nextMonth.set(Calendar.DAY_OF_MONTH, 1);
        nextMonth.set(Calendar.HOUR, 0);
        nextMonth.set(Calendar.MINUTE, 0);
        nextMonth.set(Calendar.SECOND, 0);
        Date date = nextMonth.getTime();
        System.out.println(nextMonth.getTime());
        
        return date;
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            List<Conference> listConferences = getConferences();
            new WrapperIEEE(listConferences);
            scheduleNextUpdate();
            //timer.cancel(); //Terminate the timer thread
        }
        
        private List<Conference> getConferences(){
            ConferenceDao conferenceDao = new ConferenceDao();
            
            conferenceDao.openCurrentSession();
            List<Conference> conferences = conferenceDao.findAll();
            conferenceDao.closeCurrentSession();
            
            return conferences;
        }
    }
}