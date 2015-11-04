package com.tarena.bigdata.cloudnote.service;


import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.tarena.bigdata.cloudnote.entity.User;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@ActiveProfiles("test")
public class AccountServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private AccountService accountService;
	
	@Test
//	@Transactional   //标明此方法需使用事务  
//	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getActionNoteListByCodeTest(){
		User findUserByLoginName = accountService.findUserByLoginName("test1");
		System.out.println("findUserByLoginName=" + findUserByLoginName);
		//非空断言
		Assert.assertNotNull(findUserByLoginName);
	}
	
}
