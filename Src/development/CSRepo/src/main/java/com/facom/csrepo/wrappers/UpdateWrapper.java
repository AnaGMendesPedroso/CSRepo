/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.wrappers;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Renato Gouvea
 */
public class UpdateWrapper {

    Timer timer;

    public UpdateWrapper(Date date) {
        timer = new Timer();
        timer.schedule(new RemindTask(), date);
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("YYYYYYYYYYYYYYYYY");
            WrapperIEEE wIEEE = new WrapperIEEE();
            timer.cancel(); //Terminate the timer thread
        }
    }
}
