package com.khodko.RoyalHotel.form;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseForm implements Validatable {

    protected List<String> messages = new ArrayList<>();
    
    protected abstract void runValidation();
    
    public List<String> validate() {
    	runValidation();
    	return messages;
    }
      
}
