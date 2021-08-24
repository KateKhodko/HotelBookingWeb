package com.khodko.RoyalHotel.dao.querydsl.where;

import java.util.Arrays;

class In extends BaseWhere {
	
	In(String column, Object... parameter) {
		parameters.addAll(Arrays.asList(parameter));		
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < parameter.length; i++) {
			sb.append(" ?,");
		}
		int lastIndex = sb.lastIndexOf(",");
		if (lastIndex != -1) {
			sb.deleteCharAt(lastIndex);
		}				
		queryBuilder.append(" ").append(column).append(" IN ( ").append(sb).append(" )");
	}

}
