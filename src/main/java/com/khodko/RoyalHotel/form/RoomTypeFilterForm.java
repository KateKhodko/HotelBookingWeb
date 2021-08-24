package com.khodko.RoyalHotel.form;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.khodko.RoyalHotel.util.DateUtil;

public class RoomTypeFilterForm extends BaseForm {
		
	public final static String arrivalDateName = "arrivalDate";
	public final static String departDateName = "departDate";
	public final static String filterBtnName = "filterBtn";
	public final static String resetBtnName = "resetBtn";

	private final String arrivalDateValue;
	private final String departDateValue;
	private final String filterBtnValue;
	private final String resetBtnValue;
	
    public RoomTypeFilterForm(HttpServletRequest request) {   	
    	arrivalDateValue = FormUtil.trim(request.getParameter(arrivalDateName));
    	departDateValue = FormUtil.trim(request.getParameter(departDateName));
    	filterBtnValue = request.getParameter(filterBtnName);
    	resetBtnValue = request.getParameter(resetBtnName);
    }
        
    @Override
    protected void runValidation() {

    }

	public String getArrivalDateValue() {
		return arrivalDateValue;
	}
	
	public Optional<Date> getArrivalDate() {
		return DateUtil.strToOptDate(arrivalDateValue);
	}
		
	public String getDepartDateValue() {
		return departDateValue;
	}
	
	public Optional<Date> getDepartDate() {
		return DateUtil.strToOptDate(departDateValue);
	}
			
	public boolean isFilter() {
		return filterBtnValue != null;
	}
	
	public boolean isReset() {
		return resetBtnValue != null;
	}

}
