package com.khodko.RoyalHotel.form;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.khodko.RoyalHotel.util.DateUtil;

public class OrdersFilterForm extends BaseForm {
		
	public final static String arrivalDateBeginName = "arrivalDateBegin";
	public final static String arrivalDateEndName = "arrivalDateEnd";
	public final static String departDateBeginName = "departDateBegin";
	public final static String departDateEndName = "departDateEnd";
	public final static String filterBtnName = "filterBtn";
	public final static String resetBtnName = "resetBtn";

	private final String arrivalDateBeginValue;
	private final String arrivalDateEndValue;
	private final String departDateBeginValue;
	private final String departDateEndValue;
	private final String filterBtnValue;
	private final String resetBtnValue;
	
    public OrdersFilterForm(HttpServletRequest request) {   	
    	arrivalDateBeginValue = FormUtil.trim(request.getParameter(arrivalDateBeginName));
    	arrivalDateEndValue = FormUtil.trim(request.getParameter(arrivalDateEndName));
    	departDateBeginValue = FormUtil.trim(request.getParameter(departDateBeginName));
    	departDateEndValue = FormUtil.trim(request.getParameter(departDateEndName));  
    	filterBtnValue = request.getParameter(filterBtnName);
    	resetBtnValue = request.getParameter(resetBtnName);
    }
        
    @Override
    protected void runValidation() {

    }

	public String getArrivalDateBeginValue() {
		return arrivalDateBeginValue;
	}
	
	public Optional<Date> getArrivalDateBegin() {
		return DateUtil.strToOptDate(arrivalDateBeginValue);
	}

	public String getArrivalDateEndValue() {
		return arrivalDateEndValue;
	}
	
	public Optional<Date> getArrivalDateEnd() {
		return DateUtil.strToOptDate(arrivalDateEndValue);
	}
	
	public String getDepartDateBeginValue() {
		return departDateBeginValue;
	}
	
	public Optional<Date> getDepartDateBegin() {
		return DateUtil.strToOptDate(departDateBeginValue);
	}

	public String getDepartDateEndValue() {
		return departDateEndValue;
	}
	
	public Optional<Date> getDepartDateEnd() {
		return DateUtil.strToOptDate(departDateEndValue);
	}
			
	public boolean isFilter() {
		return filterBtnValue != null;
	}
	
	public boolean isReset() {
		return resetBtnValue != null;
	}

}
