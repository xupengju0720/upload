package utils;

import java.util.HashMap;
import java.util.Map;



public class SeachInfo implements Cloneable {
	int a,b;
//查询处理
String col;
String value;
String ex;
String table;
String sort="";
String where="";
//换页处理
int pageno=1;
int last_pageno=1;
int next_pageno=1;
int pro_pageno=1;
int first_pageno=1;
int size=0;
int maxcount=8;
String limit=" limit 0,"+maxcount;

int mark=100;//处理标示    附带权限处理


public Map<String,Object> values=new HashMap<String, Object>();
public Map<String, Object> getValues() {
	return values;
}


public void setValues(Map<String, Object> values) {
	this.values = values;
}

public SeachInfo() {
	where="";
	canpage=true;
}

public SeachInfo(int pageno) {
	super();
	setPageno(pageno);
}
public SeachInfo(boolean canpage) {
	where="";
	this.canpage=canpage;
}

public SeachInfo(String where) {
	super();
	this.where = where;
}


public SeachInfo( String table,String value, int mark) {
	super();
	this.col = value;
	this.table = table;
	this.mark = mark;
}


public SeachInfo(String where, boolean canpage) {
	super();
	this.where = where;
	this.canpage = canpage;
	if(!canpage) limit="";
}


public SeachInfo(String where, String sort, String limit, boolean canpage) {
	super();
	this.where = where;
	this.sort = sort;
	this.limit = limit;
	this.canpage = canpage;
}


public boolean canpage=true;
public void setCanpage(boolean canpage) {
	this.canpage = canpage;
	if(!canpage) limit=""; 
}

public String getSort() {
	return sort;
}
public void setSort(String sort) {
	this.sort = sort;
}


public String getLimit() {
	return limit;
}
public void setLimit(String limit) {
	this.limit = limit;
}
public int getLast_pageno() {
	return last_pageno;
}
public void setLast_pageno(int last_pageno) {
	this.last_pageno = last_pageno;
}
public int getNext_pageno() {
	return next_pageno;
}
public void setNext_pageno(int next_pageno) {
	this.next_pageno = next_pageno;
}
public int getPro_pageno() {
	return pro_pageno;
}
public void setPro_pageno(int pro_pageno) {
	this.pro_pageno = pro_pageno;
}
public int getFirst_pageno() {
	return first_pageno;
}
public void setFirst_pageno(int first_pageno) {
	this.first_pageno = first_pageno;
}




public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
	last_pageno=(size+maxcount-1)/maxcount;
	next_pageno=pageno<last_pageno?pageno+1:last_pageno;
	if(last_pageno<first_pageno){
		last_pageno=first_pageno;
		next_pageno=first_pageno;
	}
}

public int getPageno() {
	return pageno;
}

public void setPageno(Integer pageno) {
	this.pageno = pageno;
	pro_pageno=pageno==1?1:pageno-1;
	int row=(pageno-1)*maxcount;
	limit = " limit "+row+" ,"+maxcount;
}

public String getTable() {
	return table;
}

public void setTable(String table) {
	this.table = table;
}

public String getCol() {
	return col;
}

public void setCol(String col) {
	this.col = col;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
	
	String fh=" = ";
	if(col.equals("all")) return;
	if(value.length()==0) return;
	try{
		String t1=table;
		
		if("like".equalsIgnoreCase(ex)){
			value="%"+value+"%";
			fh=" like ";
		}
	Class<?> cls=Class.forName("entity."+t1);
	if(!cls.getDeclaredField(col).getType().equals(int.class))value="'"+value+"'";
	}catch (Exception e) {
		e.printStackTrace();
	}
	where=" where "+table+"."+col+fh+value;
}

public String getWhere() {
	return where.replace("~", "%").replace("where and", "where ");
}
public String getWhereA() {
	return where.replace("where", " and ").replace("~", "%").replace("where and", "where ");
}
public void setWhere(String where) {
	this.where = where;
}


public int getMark() {
	return mark;
}


public String getEx() {
	return ex;
}


public void setEx(String ex) {
	this.ex = ex;
}


public void setMark(int mark) {
	this.mark = mark;
}


public int getA() {
	return a;
}


public void setA(int a) {
	this.a = a;
}


public int getB() {
	return b;
}


public void setB(int b) {
	this.b = b;
}


public void addWhere(String string) {
	if(where.indexOf("where")!=-1) where+=" and "+string;
	else where =" where "+string;
}




}
