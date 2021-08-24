package com.khodko.RoyalHotel.pagination;

import com.khodko.RoyalHotel.form.FormUtil;

import javax.servlet.http.HttpServletRequest;

public class InputForm {

    public static final String PAGE = "page";
    public static final String LIMIT = "limit";

    private int pageValue;
    private int limitValue;

    public InputForm(HttpServletRequest request, int defaultLimit) {
        pageValue = FormUtil.parseUnsignedInt(request.getParameter(PAGE));
        if (pageValue == 0) {
            pageValue = 1;
        }
        limitValue = FormUtil.parseUnsignedInt(request.getParameter(LIMIT));
        if (limitValue == 0) {
            limitValue = defaultLimit;
        }
    }

    public int getPageValue() {
        return pageValue;
    }

    public int getLimitValue() {
        return limitValue;
    }
}
