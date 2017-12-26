/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.wrappers;

import java.util.Calendar;
import java.util.Date;
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
        scheduleNextUpdate();
    }
    
    public void scheduleNextUpdate(){
        //Date date = getNextMonth();
        Date date = fiveMinutes();
        timer.schedule(new RemindTask(), date);
    }
    
    public Date fiveMinutes(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+1);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTime());
        
        Date date = calendar.getTime();
        
        return date;
    }
    
    public Date getNextMonth(){
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.set(Calendar.MONTH, nextMonth.get(Calendar.MONTH)+1);
        nextMonth.set(Calendar.DAY_OF_MONTH, 0);
        nextMonth.set(Calendar.HOUR, 0);
        nextMonth.set(Calendar.MINUTE, 0);
        nextMonth.set(Calendar.SECOND, 0);
        Date date = nextMonth.getTime();
        
        return date;
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("RemindTask");
            //WrapperIEEE wIEEE = new WrapperIEEE();
            scheduleNextUpdate();
            //timer.cancel(); //Terminate the timer thread
        }
    }
}