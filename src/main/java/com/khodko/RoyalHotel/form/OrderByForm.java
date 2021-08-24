package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;

public class OrderByForm extends BaseForm {
		
	public final static String orderByName = "orderBy";
	
	private final String columnValue;
    private boolean asc = true;
    
    public OrderByForm(HttpServletRequest request) {
    	columnValue = FormUtil.trim(request.getParameter(orderByName));     
    }

	@Override
	protected void runValidation() {
		
	}

	public String getColumnValue() {
		return columnValue;
	}

	public boolean isAsc() {
		return asc;
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
       
}
