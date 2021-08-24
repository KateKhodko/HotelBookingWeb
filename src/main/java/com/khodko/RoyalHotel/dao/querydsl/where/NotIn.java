package com.khodko.RoyalHotel.dao.querydsl.where;

import java.util.Arrays;

class NotIn extends BaseWhere {
	
	NotIn(String column, Object... parameter) {
		parameters.addAll(Arrays.asList(parameter));		
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < parameter.length; i++) {
			sb.append(" ?,");
		}
		int lastIndex = sb.lastIndexOf(",");
		if (lastIndex != -1) {
			sb.deleteCharAt(lastIndex);
		}				
		queryBuilder.append(" ").append(column).append(" NOT IN ( ").append(sb).append(" )");
	}

}
