package com.khodko.RoyalHotel.controller;


import com.khodko.RoyalHotel.command.admin.AdminCommandFactory;

/**
 * Opened only for users with role = "admin".
 */
public class AdminController extends BaseController {	
       
    public AdminController() {
        super(new AdminCommandFactory());
    }	

}
