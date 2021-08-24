package com.khodko.RoyalHotel.config;

import com.khodko.RoyalHotel.command.admin.AdminCommandFactory;
import com.khodko.RoyalHotel.command.open.PublicCommandFactory;
import com.khodko.RoyalHotel.command.user.UserCommandFactory;

public class Path {

    public static final String ADMIN_PATH = "/admin";
    public static final String USER_PATH = "/user";
    public static final String PUBLIC_PATH = "/public";
    
    public final static String LOGOUT_INCL = "/WEB-INF/include/logout_incl.jsp";
    public final static String MESSAGES_INCL = "/WEB-INF/include/messages_incl.jsp";
    public final static String USER_MENU_INCL = "/WEB-INF/include/user_menu_incl.jsp";
    public final static String ADMIN_MENU_INCL = "/WEB-INF/include/admin_menu_incl.jsp";
    public final static String LOCALE_MENU_INCL = "/WEB-INF/include/locale_menu_incl.jsp";
    public final static String LOCALE_HEADER_INCL = "/WEB-INF/include/locale_header_incl.jsp";
    public final static String HEADER_INCL = "/WEB-INF/include/header_incl.jsp";
    public final static String FOOTER_INCL = "/WEB-INF/include/footer_incl.jsp";
    
    public final static String PAGINATION_SELECT_INCL = "/WEB-INF/pagination/pagination_select_incl.jsp";
    public final static String PAGINATION_NAV_INCL = "/WEB-INF/pagination/pagination_nav_incl.jsp";
    
    public final static String ADMIN_MAIN = ADMIN_PATH + "/" + AdminCommandFactory.SHOW_ADMIN;
    public final static String ADMIN_AMENITY_I18N = ADMIN_PATH + "/" + AdminCommandFactory.SHOW_AMENITY_I18N;
    public final static String ADMIN_SAVE_AMENITY_I18N = ADMIN_PATH + "/" + AdminCommandFactory.SAVE_AMENITY_I18N;
    public final static String ADMIN_AMENITIES = ADMIN_PATH + "/" + AdminCommandFactory.SHOW_AMENITIES;
    public final static String ADMIN_DELETE_AMENITY = ADMIN_PATH + "/" + AdminCommandFactory.DELETE_AMENITY;
    public final static String ADMIN_SAVE_AMENITY = ADMIN_PATH + "/" + AdminCommandFactory.SAVE_AMENITY;
    public final static String ADMIN_ROOMTYPE = ADMIN_PATH + "/" + AdminCommandFactory.SHOW_ROOMTYPE;
    public final static String ADMIN_SAVE_ROOMTYPE = ADMIN_PATH + "/" + AdminCommandFactory.SAVE_ROOMTYPE;
    public final static String ADMIN_DELETE_ROOMTYPE = ADMIN_PATH + "/" + AdminCommandFactory.DELETE_ROOMTYPE;
    public final static String ADMIN_ROOMTYPES = ADMIN_PATH + "/" + AdminCommandFactory.SHOW_ROOMTYPES;
    public final static String ADMIN_ORDERS = ADMIN_PATH + "/" + AdminCommandFactory.ORDERS;
    public final static String ADMIN_ORDER = ADMIN_PATH + "/" + AdminCommandFactory.ORDER;
    public final static String ADMIN_DELETE_ORDER = ADMIN_PATH + "/" + AdminCommandFactory.DELETE_ORDER;
    public final static String ADMIN_ROOMTYPE_I18N = ADMIN_PATH + "/" + AdminCommandFactory.SHOW_ROOMTYPE_I18N;
    public final static String ADMIN_SAVE_ROOMTYPE_I18N = ADMIN_PATH + "/" + AdminCommandFactory.SAVE_ROOMTYPE_I18N;
    public final static String ADMIN_LANGS = ADMIN_PATH + "/" + AdminCommandFactory.SHOW_LANGS;
    public final static String ADMIN_SAVE_LANG = ADMIN_PATH + "/" + AdminCommandFactory.SAVE_LANG;
    public final static String ADMIN_DELETE_LANG = ADMIN_PATH + "/" + AdminCommandFactory.DELETE_LANG;
    
    public final static String USER_PROFILE = USER_PATH + "/" + UserCommandFactory.PROFILE;
    public final static String USER_SAVE_PROFILE = USER_PATH + "/" + UserCommandFactory.SAVE_PROFILE;
    public final static String USER_ROOMTYPE_DETAILS = USER_PATH + "/" + UserCommandFactory.ROOMTYPE_DETAILS;
    public final static String USER_BOOKING = USER_PATH + "/" + UserCommandFactory.BOOKING;
    public final static String USER_PAYMENT = USER_PATH + "/" + UserCommandFactory.PAYMENT;
    public final static String USER_CONFIRM_PAYMENT = USER_PATH + "/" + UserCommandFactory.CONFIRM_PAYMENT;
    public final static String USER_ORDER = USER_PATH + "/" + UserCommandFactory.ORDER;
    public final static String USER_ORDERS = USER_PATH + "/" + UserCommandFactory.ORDERS;

    public final static String PUBLIC_HOME = PUBLIC_PATH + "/" + PublicCommandFactory.HOME;
    public final static String PUBLIC_SHOW_SIGNUP = PUBLIC_PATH + "/" + PublicCommandFactory.SHOW_SIGNUP;
    public final static String PUBLIC_SIGNUP = PUBLIC_PATH + "/" + PublicCommandFactory.SIGNUP;
    public final static String PUBLIC_SHOW_LOGIN = PUBLIC_PATH + "/" + PublicCommandFactory.SHOW_LOGIN;
    public final static String PUBLIC_LOGIN = PUBLIC_PATH + "/" + PublicCommandFactory.LOGIN;
    public final static String PUBLIC_LOGOUT = PUBLIC_PATH + "/" + PublicCommandFactory.LOGOUT;
    public final static String PUBLIC_ROOMTYPES = PUBLIC_PATH + "/" + PublicCommandFactory.SHOW_ROOMTYPES;

}
