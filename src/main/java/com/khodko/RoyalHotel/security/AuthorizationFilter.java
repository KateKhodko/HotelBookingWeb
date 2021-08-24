package com.khodko.RoyalHotel.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khodko.RoyalHotel.config.Config;
import com.khodko.RoyalHotel.config.Pages;
import com.khodko.RoyalHotel.config.Path;
import com.khodko.RoyalHotel.model.User;

import java.io.IOException;


public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
                        
        User user = Auth.getUser(request); 
        request.setAttribute(Config.USER_REQUEST_ATTR, user);
               
        boolean authorized = user != null;  
        
        String role = authorized ?  user.getRole() : "";
              
        switch (request.getServletPath()) {
        
        	case Path.ADMIN_PATH:
				authorized = Config.ADMIN_ROLE.equals(role);	
				break;
				
        	case Path.USER_PATH:        		
        		break; 
        		
        	case Path.PUBLIC_PATH:
				authorized = true;
				break;	
							
			default:
				request.getRequestDispatcher(Pages.ERROR_NOT_FOUND).forward(request, response);
				return;
		}
              
        if (!authorized) {     
        	Auth.rememberRequestedPage(request);
        	response.sendRedirect(request.getContextPath() + Path.PUBLIC_SHOW_LOGIN);
        	return;
        }
        //todo: clear rememberRequestedPage???

        filterChain.doFilter(servletRequest, servletResponse);
    }
       
}
