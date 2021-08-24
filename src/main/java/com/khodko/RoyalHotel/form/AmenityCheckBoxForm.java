package com.khodko.RoyalHotel.form;

import javax.servlet.http.HttpServletRequest;

public class AmenityCheckBoxForm extends BaseForm {

    public static final String amenitiesName = "amenities";

    private final String[] amenitiesIdValues;
    private int[] idValues;

    public AmenityCheckBoxForm(HttpServletRequest request) {
        amenitiesIdValues = request.getParameterValues(amenitiesName);
    }

    @Override
    protected void runValidation() {
        if (amenitiesIdValues == null) return;
        int length = amenitiesIdValues.length;
        idValues = new int[length];
        try {
            for (int i = 0; i < length; i++) {
                idValues[i] = Integer.parseInt(amenitiesIdValues[i]);
            }
        } catch (NumberFormatException ignored) {
            messages.add("locale.message.invalidamenityid");
        }
    }

    public int[] getIdValues() {
        return idValues;
    }
}
