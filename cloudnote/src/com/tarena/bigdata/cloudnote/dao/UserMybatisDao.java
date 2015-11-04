package com.tarena.bigdata.cloudnote.dao;

import java.util.Map;

import com.tarena.bigdata.cloudnote.entity.User;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author Meng Fanliang
 */
@MyBatisRepository
public interface UserMybatisDao {
	// 根据用户名密码查询用户信息
	public User getUserByLoginNamePsd(Map<String, Object> params);
	// 根据用户名密码查询用户
	public void createUser(User user);
	// 根据用户id重置密码
	public void updatePassword(User user);
	// 根据登录名查询用户
	public User getUserByLoginName(String cnUserName);

}
