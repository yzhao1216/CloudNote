package com.tarena.bigdata.cloudnote.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tarena.bigdata.cloudnote.entity.User;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@ActiveProfiles("test")
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private UserService userService;
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getActionNoteListByCodeTest(){
		User userByLoginNamePsd = userService.getUserByLoginNamePsd("test1", "111111");
		System.out.println("userByLoginNamePsd=" + userByLoginNamePsd);
		//非空断言
		Assert.assertNotNull(userByLoginNamePsd);
	}
	
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void findUserByLoginNameTest(){
		User findUserByLoginName = userService.findUserByLoginName("test1");
		System.out.println("findUserByLoginName=" + findUserByLoginName);
		//非空断言
		Assert.assertNotNull(findUserByLoginName);
		
		User findUserByLoginName1 = userService.findUserByLoginName("test1323");
		//空断言
		Assert.assertNull(findUserByLoginName1);
		System.out.println("ok");
		
	}
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void createUserTest(){
		User user = new User();
		user.setCnUserId("qwerqwerqwer");
		user.setCnUserName("testuser");
		user.setCnUserPassword("111111");
		user.setCnUserToken("111111");
		userService.createUser(user);
		User findUserByLoginName = userService.findUserByLoginName("testuser");
		//非空断言
		Assert.assertNotNull(findUserByLoginName);

		System.out.println("createUser ok");
		
	}
	
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void resetPwdTest(){

		userService.resetPwd("333c6d0b-e4a2-4596-9902-a5d98c2f665a","123456");//修改密码
		User userByLoginNamePsd = userService.getUserByLoginNamePsd("test1","123456");//修改完毕后，用新密码查询
		//非空断言
		Assert.assertNotNull(userByLoginNamePsd);
		
		System.out.println("resetPwdTest ok");
		
	}
	
	
}
