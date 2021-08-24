package com.khodko.RoyalHotel.controller;


import com.khodko.RoyalHotel.command.user.UserCommandFactory;

/**
 * Opened only for authorized users.
 */
public class UserController extends BaseController {	
       
    public UserController() {
        super(new UserCommandFactory());
    }	

}
