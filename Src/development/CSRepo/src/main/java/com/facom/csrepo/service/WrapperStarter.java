/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facom.csrepo.service;

import com.facom.csrepo.wrappers.UpdateWrapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author jose
 */
public class WrapperStarter extends HttpServlet{

    @Override
    public void init() throws ServletException {
        UpdateWrapper updateWrapper = new UpdateWrapper();
    }
}
