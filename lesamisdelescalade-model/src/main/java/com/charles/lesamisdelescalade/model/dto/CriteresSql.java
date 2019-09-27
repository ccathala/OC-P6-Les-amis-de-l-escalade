package com.charles.lesamisdelescalade.model.dto;

import java.util.Arrays;

public class CriteresSql {

	private String sql;
	private Object[] criteresSql;
	
	public CriteresSql() {
		super();
	}

	public CriteresSql(String sql, Object[] criteresSql) {
		super();
		this.sql = sql;
		this.criteresSql = criteresSql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getCriteresSql() {
		return criteresSql;
	}

	public void setCriteresSql(Object[] criteresSql) {
		this.criteresSql = criteresSql;
	}

	@Override
	public String toString() {
		return "CriteresSql [sql=" + sql + ", criteresSql=" + Arrays.toString(criteresSql) + "]";
	}
	
	
	
	
}
