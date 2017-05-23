package com.base.dao;

import java.io.Serializable;

import com.base.entity.Test;

@SuppressWarnings("serial")
public class TestDAO extends BaseDAO<Test> implements Serializable{
	@Override
	protected Class<Test> getEntityClass() {
		return Test.class;
	}
}
