package com.khodko.RoyalHotel.dao.querydsl.where;

import java.util.Arrays;

import com.khodko.RoyalHotel.dao.querydsl.Select;

class Compare extends BaseWhere {

	Compare(String sign, String column, Object parameter) {
		if (parameter instanceof Select) {
			Select select = (Select) parameter;
			queryBuilder.append(" ").append(column).append(" ").append(sign)
				.append(" ( ").append(select.getQuery()).append(" ) ");
			parameters.addAll(Arrays.asList(select.getParameters()));
		} else {
			parameters.add(parameter);	
			queryBuilder.append(" ").append(column).append(" ").append(sign).append(" ?");
		}						
	}
	
}
