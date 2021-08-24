package com.khodko.RoyalHotel.dao.querydsl.where;

public class Where {
	
	private Where() {
		
	}
	
	public static BaseWhere br(BaseWhere where) {
		return new Braces(where);
	}
	
	public static BaseWhere isNull(String column) {
		return new IsNull(column);
	}
	
	public static BaseWhere isNotNull(String column) {
		return new IsNotNull(column);
	}
	
	public static BaseWhere eqc(String column1, String column2) {
		return new EqualColumns(column1, column2);
	}
	
	public static BaseWhere eq(String column, Object parameter) {
		return new Equal(column, parameter);
	}
	
	public static BaseWhere ne(String column, Object parameter) {
		return new NotEqual(column, parameter);
	}
	
	public static BaseWhere lt(String column, Object parameter) {
		return new LessThan(column, parameter);				
	}
	
	public static BaseWhere gt(String column, Object parameter) {
		return new GrateThan(column, parameter);				
	}
	
	public static BaseWhere le(String column, Object parameter) {
		return new LessEqual(column, parameter);				
	}
	
	public static BaseWhere ge(String column, Object parameter) {
		return new GrateEqual(column, parameter);				
	}
	
	public static BaseWhere btwn(String column, Object parameter1, Object parameter2) {
		return new Between(column, parameter1, parameter2);				
	}
	
	public static BaseWhere nbtwn(String column, Object parameter1, Object parameter2) {
		return new NotBetween(column, parameter1, parameter2);				
	}
	
	public static BaseWhere pbtwn(Object parameter, String column1, String column2) {
		return new ParameterBetween(parameter, column1, column2);				
	}
	
	public static BaseWhere npbtwn(Object parameter, String column1, String column2) {
		return new NotParameterBetween(parameter, column1, column2);				
	}
	
	public static BaseWhere in(String column, Object... parameters) {
		return new In(column, parameters);				
	}
	
	public static BaseWhere nin(String column, Object... parameters) {
		return new NotIn(column, parameters);				
	}
	
	public static BaseWhere and(BaseWhere where1, BaseWhere where2) {
    	return where1 != null ? where1.and(where2) : where2;
    }
	
	public static BaseWhere or(BaseWhere where1, BaseWhere where2) {
    	return where1 != null ? where1.or(where2) : where2;
    }
}
