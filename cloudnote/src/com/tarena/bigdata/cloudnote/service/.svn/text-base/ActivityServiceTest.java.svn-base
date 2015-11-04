package com.tarena.bigdata.cloudnote.service;

import java.util.List;

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

import com.tarena.bigdata.cloudnote.entity.Activity;
import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@ActiveProfiles("test")
public class ActivityServiceTest  extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private ActivityService activityService;
	
	
	
	/**
	 * 获取活动列表
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void getAllActivityTest(){
		List<Activity> allActivity = activityService.getAllActivity();
		Assert.assertNotNull(allActivity);
		Assert.assertEquals(9, allActivity.size());
		System.out.println("getAllActivityTest ok");
	}
	
	
	
	
	
	/**
	 * 获得一个用户指定笔记本下的笔记列表
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void getActionNoteListByIdTest(){
		List<Note> actionNoteListById = activityService.getActionNoteListById("6d763ac9-dca3-42d7-a2a7-a08053095c08");
		Assert.assertNotNull(actionNoteListById);
		Assert.assertEquals(46, actionNoteListById.size());
		System.out.println("getActionNoteListByIdTest ok");
	}
	
	
	
	
	/**
	 *获得一个活动下的所有人参加的笔记列表
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void getActivityNotesByActivityIdTest(){
		List<NoteActivity> activityNotesByActivityId = activityService.getActivityNotesByActivityId("1","0","10");
		Assert.assertNotNull(activityNotesByActivityId);
		Assert.assertEquals(10, activityNotesByActivityId.size());
		System.out.println("getActivityNotesByActivityIdTest ok");
	}
	
	
	
	/**
	 *获得一个活动下的所有人参加的笔记列表
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void createNoteActivityTest(){
		String createNoteActivity = activityService.createNoteActivity("39295a3d-cc9b-42b4-b206-a2e7fab7e77c","3","046b0110-67f9-48c3-bef3-b0b23bda9d4e");
		Assert.assertNotNull(createNoteActivity);
		System.out.println("createNoteActivityTest ok");
	}
	
	/**
	 *查找参加活动笔记
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void findNoteActivityByIdTest(){
		NoteActivity findNoteActivityById = activityService.findNoteActivityById("9a0512d2-e8e3-431b-9a2b-c010e01b8cf0");
		Assert.assertNotNull(findNoteActivityById);
		System.out.println("findNoteActivityByIdTest ok");
	}
	
}
