package com.xcode.whz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xcode.whz.bean.User;
import org.springframework.stereotype.Repository;
@Repository 
public interface UserMapper {
	
	public User selectUser(String phoneno);
	public List selectAllUser();
	
	@Select("select * from user where phoneno=#{phoneno} and password=#{password}") 
	public User checkPwd(@Param("phoneno") String username,@Param("password") String password);
	
	public int insertUser(User user);
}
