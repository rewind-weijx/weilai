/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.iim.entity;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.modules.sys.entity.User;

/**
 * 群组Entity
 * @author lgf
 * @version 2016-08-07
 */
public class LayGroupUser extends DataEntity<LayGroupUser> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户
	private LayGroup group;		// 群组id 父类
	
	public LayGroupUser() {
		super();
	}

	public LayGroupUser(String id){
		super(id);
	}

	public LayGroupUser(LayGroup group){
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="群组id长度必须介于 0 和 64 之间")
	public LayGroup getGroup() {
		return group;
	}

	public void setGroup(LayGroup group) {
		this.group = group;
	}
	
}