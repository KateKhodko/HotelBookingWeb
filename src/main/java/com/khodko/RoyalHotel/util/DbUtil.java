package com.khodko.RoyalHotel.util;

import java.util.List;

import com.khodko.RoyalHotel.model.IdEntity;

public class DbUtil {
	
	public static <T> List<T> joinLists(List<T> list, List<T> joinList) {
		for (T item : joinList) {
    		if (list.contains(item)) {
    			list.set(list.indexOf(item), item);
    		}
    	}
    	return list;
	}
	
	public static <T extends IdEntity> Object[] getIds(List<T> list) {
		Object[] ids = new Integer[list.size()];
    	for (int i = 0, length = ids.length; i < length; i++) {
    		ids[i] = list.get(i).getId();
    	}
    	return ids;
	}
	
}
