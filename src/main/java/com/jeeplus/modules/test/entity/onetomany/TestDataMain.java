/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.test.entity.onetomany;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;
import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.modules.sys.entity.Area;
import com.jeeplus.modules.sys.entity.Office;
import com.jeeplus.modules.sys.entity.User;

/**
 * 票务代理Entity
 * @author liugf
 * @version 2016-10-06
 */
public class TestDataMain extends DataEntity<TestDataMain> {
	
	private static final long serialVersionUID = 1L;
	private User tuser;		// 归属用户
	private Office office;		// 归属部门
	private Area area;		// 归属区域
	private String name;		// 名称
	private String sex;		// 性别
	private Date inDate;		// 加入日期
	private Date beginInDate;		// 开始 加入日期
	private Date endInDate;		// 结束 加入日期
	private List<TestDataChild> testDataChildList = Lists.newArrayList();		// 子表列表
	private List<TestDataChild2> testDataChild2List = Lists.newArrayList();		// 子表列表
	private List<TestDataChild3> testDataChild3List = Lists.newArrayList();		// 子表列表
	
	public TestDataMain() {
		super();
	}

	public TestDataMain(String id){
		super(id);
	}

	@NotNull(message="归属用户不能为空")
	public User getTuser() {
		return tuser;
	}

	public void setTuser(User tuser) {
		this.tuser = tuser;
	}
	
	@NotNull(message="归属部门不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@NotNull(message="归属区域不能为空")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@NotNull(message="加入日期不能为空")
	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	
	public Date getBeginInDate() {
		return beginInDate;
	}

	public void setBeginInDate(Date beginInDate) {
		this.beginInDate = beginInDate;
	}
	
	public Date getEndInDate() {
		return endInDate;
	}

	public void setEndInDate(Date endInDate) {
		this.endInDate = endInDate;
	}
		
	public List<TestDataChild> getTestDataChildList() {
		return testDataChildList;
	}

	public void setTestDataChildList(List<TestDataChild> testDataChildList) {
		this.testDataChildList = testDataChildList;
	}
	public List<TestDataChild2> getTestDataChild2List() {
		return testDataChild2List;
	}

	public void setTestDataChild2List(List<TestDataChild2> testDataChild2List) {
		this.testDataChild2List = testDataChild2List;
	}
	public List<TestDataChild3> getTestDataChild3List() {
		return testDataChild3List;
	}

	public void setTestDataChild3List(List<TestDataChild3> testDataChild3List) {
		this.testDataChild3List = testDataChild3List;
	}
}