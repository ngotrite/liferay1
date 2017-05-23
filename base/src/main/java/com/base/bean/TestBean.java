package com.base.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.base.dao.TestDAO;
import com.base.entity.Test;

@SuppressWarnings("serial")
@ManagedBean(name = "testBean")
@ViewScoped
public class TestBean extends BaseController implements Serializable {
	private Test test;
	private List<Test> lstTest;
	private TestDAO testDAO;

	@PostConstruct
	public void init() {
		this.test = new Test();
		this.testDAO = new TestDAO();
		this.lstTest = new ArrayList<Test>();
		loadLstTest();
	}
	
	

	public void loadLstTest() {
		lstTest = testDAO.findAll("");
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<Test> getLstTest() {
		return lstTest;
	}

	public void setLstTest(List<Test> lstTest) {
		this.lstTest = lstTest;
	}

}
