package com.khodko.RoyalHotel.util;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	public static void regenerateSession(HttpServletRequest request) {	
	    HttpSession oldSession = request.getSession();
	    
	    if (oldSession != null && !oldSession.isNew()) {
		    Enumeration<?> attrNames = oldSession.getAttributeNames();
		    Properties props = new Properties();
	
		    if (attrNames != null) {
		        while (attrNames.hasMoreElements()) {
		            String key = (String) attrNames.nextElement();
		            props.put(key, oldSession.getAttribute(key));
		        }
	
		        //Invalidating previous session
		        oldSession.invalidate();
		        //Generate new session
		        HttpSession newSession = request.getSession(true);
		        attrNames = props.keys();
	
		        while (attrNames.hasMoreElements()) {
		            String key = (String) attrNames.nextElement();
		            newSession.setAttribute(key, props.get(key));
		        }
		    }
	    }
	}
}
