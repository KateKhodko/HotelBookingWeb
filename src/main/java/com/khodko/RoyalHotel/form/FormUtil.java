package com.khodko.RoyalHotel.form;

public class FormUtil {

    public static String trim(String str) {
        return (str != null) ? str.trim() : "";
    }

    public static int parseUnsignedInt(String str) {
        int result = 0;
        try {
            result = Integer.parseInt(str);
            if (result < 0) {
                result = 0;
            }
        } catch (NumberFormatException ex) {
        }
        return result;
    }
       
}
