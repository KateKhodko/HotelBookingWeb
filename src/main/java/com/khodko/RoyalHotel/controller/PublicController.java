package com.khodko.RoyalHotel.controller;


import com.khodko.RoyalHotel.command.open.PublicCommandFactory;

/**
 * Opened for all user.
 */
public class PublicController extends BaseController {	
       
    public PublicController() {
        super(new PublicCommandFactory());
    }	

}
