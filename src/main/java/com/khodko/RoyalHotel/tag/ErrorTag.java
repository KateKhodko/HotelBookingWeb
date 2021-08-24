package com.khodko.RoyalHotel.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ErrorTag extends SimpleTagSupport {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void doTag() throws JspException, IOException {
        getJspContext()
                .getOut()
                .print("<h1 class=\"profile_label_text\" style=\"font-size: 72px; text-align: center\">" +
                        "ERROR " + code + ".</h1>" +
                        "<h1 class=\"profile_label_text\" style=\"font-size: 58px; text-align: center\">\n" +
                        message +
                        "</h1>");
    }
}

