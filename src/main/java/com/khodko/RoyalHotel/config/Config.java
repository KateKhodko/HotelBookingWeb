package com.khodko.RoyalHotel.config;


public class Config {
	
	public static final String ADMIN_ROLE = "admin";
	
	public final static int TOKEN_EXPIRE =  60 * 60 * 24 * 14; // 14 days
	
	//public final static int EXPIRE_COOKIE =  60 * 60 * 24 * 365; // 1 year
	
	public final static String SECRET = "secret";
	
    public static final String COOKIE_LANG = "cookieLang";
    
    public static final String COOKIE_I18N = "cookieI18n";
	
	public final static String TOKEN_COOKIE = "hashedToken";
	
	public static final String USER_REQUEST_ATTR = "authUser";

	public static final String LANG_REQUEST_ATTR = "lang";

}
