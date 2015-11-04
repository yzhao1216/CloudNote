package com.tarena.bigdata.cloudnote.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tarena.bigdata.cloudnote.dao.UserMybatisDao;
import com.tarena.bigdata.cloudnote.entity.User;
import com.tarena.bigdata.cloudnote.util.Md5Utils;
import com.tarena.bigdata.cloudnote.util.UUIDUtil;

/**
 * 用户管理业务类.
 * @author YHT
 */
@Component("userService")
@Transactional
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Resource(name = "userMybatisDao")
	private UserMybatisDao userMybatisDao;

	/**
	 * 用户名密码验证
	 * @Title: getUserByLoginNamePsd 
	 * @Time：2014年6月11日 上午10:47:57 YHT
	 * @return User
	 */
	public User getUserByLoginNamePsd(String loginName, String pwd) {
		Map<String, Object> param = new HashMap<String, Object>();//封装dao层参数
		param.put("cnUserName", loginName);//登录名
		param.put("cnUserPassword", Md5Utils.md5(pwd));//MD5加密的密码
		logger.debug("验证用户["+param+"]");
		User nowUser = null;//用于封装返回值
			nowUser = userMybatisDao.getUserByLoginNamePsd(param);//通过用户名密码查询用户信息
		return nowUser;
	}
	/**
	 * 根据用户名查询用户（登陆名检查重复）
	 * @Title: findUserByLoginName 
	 * @Time：2014年6月11日 上午10:47:11 YHT
	 * @return User
	 */
	public User findUserByLoginName(String loginName) {
		User nowUser = userMybatisDao.getUserByLoginName(loginName);//通过登录名查询用户信息
		return nowUser;
	}

	
	/**
	 * 用户注册
	 * @Title: createUser 
	 * @Time：2014年6月11日 上午10:46:58 YHT
	 * @return void
	 */
	public void createUser(User user) {
			user.setCnUserId(UUIDUtil.getUId());//添加userId
			user.setCnUserPassword(Md5Utils.md5(user.getCnUserPassword()));//密码MD5加密
			userMybatisDao.createUser(user);
	}
	

	/**
	 * 用户重置密码
	 * @Title: resetPwd 
	 * @Time：2014年6月11日 上午10:46:36 YHT
	 * @return void
	 */
	public void resetPwd (String userId, String pwd) {
			User user = new User();//封装dao层参数
			user.setCnUserId(userId);
			user.setCnUserPassword(Md5Utils.md5(pwd));
			userMybatisDao.updatePassword(user);//根据用户id重置密码
	}
	
}
