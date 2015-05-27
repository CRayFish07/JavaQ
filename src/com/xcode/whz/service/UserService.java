package com.xcode.whz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xcode.whz.bean.User;
import com.xcode.whz.mapper.UserMapper;


@Service("userService")
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public User selectUser(String phoneno){
		User user = userMapper.selectUser(phoneno);
		return user;
	}
	public List selectAllUser(){
		return userMapper.selectAllUser();
	}
	
	public int insertUser(User user){
		 userMapper.insertUser(user);
		return 1;
	}
	public User checkPwd(String phoneno,String password){
		User newuser = userMapper.checkPwd(phoneno,password);
		return newuser;
	}
}
