package com.khodko.RoyalHotel.form;

import com.khodko.RoyalHotel.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

public class BookingForm extends BaseForm {

    public final static String idName = "id";
    public final static String dateName = "date";
    public final static String daysName = "days";
    public final static String adultsName = "adults";
    public final static String childrenName = "children";

    private final int idValue;
    private final String dateValue;
    private final int daysValue;
    private final int adultsValue;
    private final int childrenValue;

    private Date date;

    public BookingForm(HttpServletRequest request) {
        idValue = FormUtil.parseUnsignedInt(request.getParameter(idName));
        dateValue = FormUtil.trim(request.getParameter(dateName));
        daysValue = FormUtil.parseUnsignedInt(request.getParameter(daysName));
        adultsValue = FormUtil.parseUnsignedInt(request.getParameter(adultsName));
        childrenValue = FormUtil.parseUnsignedInt(request.getParameter(childrenName));
    }

    @Override
    protected void runValidation() {
        if (dateValue.isEmpty()) {
            messages.add("locale.message.inputarrival");
        } else {
            try {
                date = DateUtil.strToDate(dateValue);
                if (date.before(new Date())) {
                    messages.add("locale.message.dateafter");
                }
            } catch (ParseException e) {
                messages.add("locale.message.invaliddateformat");
            }
        }
        if (daysValue == 0) {
            messages.add("locale.message.inputdays");
        }
        if (adultsValue == 0) {
            messages.add("locale.message.inputadults");
        }
    }

    public int getIdValue() {
        return idValue;
    }

    public String getDateValue() {
        return dateValue;
    }

    public int getDaysValue() {
        return daysValue;
    }

    public int getAdultsValue() {
        return adultsValue;
    }

    public int getChildrenValue() {
        return childrenValue;
    }

    public Date getDate() {
        return date;
    }
}
