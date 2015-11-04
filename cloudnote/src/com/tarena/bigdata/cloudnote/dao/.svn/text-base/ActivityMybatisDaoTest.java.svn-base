package com.tarena.bigdata.cloudnote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.tarena.bigdata.cloudnote.dao.ActivityMybatisDao;
import com.tarena.bigdata.cloudnote.entity.Activity;
import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
//@ContextConfiguration(locations={"file:WebContent/WEB-INF/springMVCForm-servlet.xml"}) 配置文件在WBE-INF下
@ActiveProfiles("test")
public class ActivityMybatisDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	
	@Resource
	private ActivityMybatisDao activityMybatisDao;
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getActionNoteListByCodeTest(){
		List<Note> actionNoteListByCode = activityMybatisDao.getActionNoteListByCode("6666666666666666666666666666666666");
		Assert.assertNotNull(actionNoteListByCode);
		System.out.println("getActionNoteListByCodeTest ok" + actionNoteListByCode.size());
	}
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getActionNoteListByIdTest(){
		List<Note> actionNoteListById = activityMybatisDao.getActionNoteListById("a248decc-0fb6-402c-83e9-ea33a276968a");
		Assert.assertNotNull(actionNoteListById);
		System.out.println("getActionNoteListByIdTest ok" + actionNoteListById.size());
	}
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getAllActivityListTest(){
		List<Activity> allActivityList = activityMybatisDao.getAllActivityList();
		Assert.assertNotNull(allActivityList);
		System.out.println("getAllActivityListTest ok" + allActivityList.size());
	}
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getAllActivityNoteListTest(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cnActivityId", "1");
		params.put("begin", 0);
		params.put("limit", 10);
		List<NoteActivity> allActivityNoteList = activityMybatisDao.getAllActivityNoteList(params);
		Assert.assertNotNull(allActivityNoteList);
		System.out.println("getAllActivityNoteListTest ok" + allActivityNoteList.size());
	}
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void activityNoteTest(){
		NoteActivity noteActivity = new NoteActivity();
		noteActivity.setCnActivityId("11122231231");
		noteActivity.setCnNoteActivityBody("阿斯顿福建收到了空间发啊死啦款到即发阿斯利康京东方");
		noteActivity.setCnNoteActivityId("1111223213123123adf1");//如果@Rollback 为false则插入数据库，不删除，每次主键要变化，否则重复执行会报主键重复异常
		noteActivity.setCnNoteActivityTitle("junit test");
		noteActivity.setCnNoteId("1231312312313");
		activityMybatisDao.activityNote(noteActivity);//参加活动
		
		//参加活动后，在活动中查找到，非空断言通过，则证明参加活动成功
		NoteActivity findNoteActivityById = activityMybatisDao.findNoteActivityById("1111223213123123adf");
		Assert.assertNotNull(findNoteActivityById);
		System.out.println("activityNoteTest success");
	}
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void findNoteActivityByIdTest(){

		NoteActivity findNoteActivityById = activityMybatisDao.findNoteActivityById("1111223213123123adf");
		//非空断言
		Assert.assertNotNull(findNoteActivityById);
		System.out.println("findNoteActivityByIdTest ok " + findNoteActivityById);
	}
	
}