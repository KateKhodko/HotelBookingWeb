package com.khodko.RoyalHotel.command;

import javax.servlet.http.HttpServletRequest;


public class CommandResults {
		
    private String page;
    private boolean isRedirect;

    private CommandResults(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public CommandResults(){}

    public static CommandResults forward(String page) {
        return new CommandResults(page, false);
    }

    public static CommandResults redirectUri(String uri) {
        return new CommandResults(uri, true);
    }

    public static CommandResults redirectPath(HttpServletRequest request, String path) {
        return new CommandResults(request.getContextPath() + path, true);
    }
    
    public static CommandResults redirectPath(HttpServletRequest request, String path, String[] params) {
        String fullpath = request.getContextPath() + path;
        if (params != null && params.length > 0) {
        	fullpath += "/" + String.join("/", params);
        }
    	return new CommandResults(fullpath, true);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
