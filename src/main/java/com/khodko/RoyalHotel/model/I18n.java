package com.khodko.RoyalHotel.model;

public class I18n extends IdEntity {
	
    private String locale;
    private String name;
    
    public I18n() {
  	
    }
     
	public I18n(String locale, String name) {
		super();
		this.locale = locale;
		this.name = name;
	}
    
	public I18n(Integer id, String locale, String name) {
		this(locale, name);
		this.id = id;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "I18n [id=" + id + ", locale=" + locale + ", name=" + name + "]";
	}
	
}
