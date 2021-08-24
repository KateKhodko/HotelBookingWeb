package com.khodko.RoyalHotel.dao.querydsl;

public class OrderBy {
	
	private String query = "";
	
	public OrderBy() {
		
	}
	
	public OrderBy(boolean asc, String... columns) {
		if (columns == null || columns.length == 0 || columns[0] == null) {
			query = "";
		} else {
			String columnsStr = String.join(" ,", columns);
			String order = asc ? " ASC" : " DESC";
			query = " ORDER BY " + columnsStr + order;
		}			
	}
		
	public String getQuery() {
		return query;
	}
		
}
