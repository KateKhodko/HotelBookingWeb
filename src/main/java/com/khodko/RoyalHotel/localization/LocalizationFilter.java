package com.khodko.RoyalHotel.localization;

import com.khodko.RoyalHotel.form.FormUtil;
import com.khodko.RoyalHotel.model.I18n;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class LocalizationFilter implements Filter {
	
	private static final String I18NID_PRM = "lang";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        
        Localization localization = new Localization(request, response);
        int i18nId = FormUtil.parseUnsignedInt(servletRequest.getParameter(I18NID_PRM));
        I18n i18n = null;
        if (i18nId > 0) {
        	i18n = localization.getI18n(i18nId);
        }        
        if (i18n == null) {
        	i18n = localization.getI18n();
        }              
        filterChain.doFilter(servletRequest, servletResponse);     
    }
        
}
