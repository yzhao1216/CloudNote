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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;
import com.tarena.bigdata.cloudnote.entity.Share;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@ActiveProfiles("test")
public class NoteMybatisDaoTest {
	
	@Resource
	private NoteMybatisDao noteMybatisDao;
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getNoteDetailTest(){
		Note noteDetail = noteMybatisDao.getNoteDetail("1153295f-d1a5-4a39-b794-980633bdc3d8");
		Assert.assertNotNull(noteDetail);
		System.out.println("getNoteDetailTest  ok " + noteDetail);
		
	}
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getNotebookListTest(){
		List<Note> normalNoteListById = noteMybatisDao.getNormalNoteListById("4acb305b-5def-47ae-8832-c2a188662085");
		//非空断言
		Assert.assertNotNull(normalNoteListById);
		System.out.println("getNotebookListTest  ok " + normalNoteListById.size());
	}
	
	
	

	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void createNormalNoteTest(){
		Note note = new Note();
		note.setCnNoteBody("正文正文正文正文正文正文正文正文");
		note.setCnNotebookId("sfsfa1asdfaf");
		note.setCnNoteCreateTime(1231312313132L);
		note.setCnNoteId("sdsadf1123dsfadf");
		note.setCnNoteLastModifyTime(123123123123L);
		note.setCnNoteTitle("Junit-test 笔记");
		note.setCnUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		noteMybatisDao.createNormalNote(note);
		
		Note noteDetail = noteMybatisDao.getNoteDetail("sdsadf1123dsfadf");
		Assert.assertNotNull(noteDetail);//非空，添加成功
		//比较每个添加的字段
		Assert.assertEquals("正文正文正文正文正文正文正文正文", noteDetail.getCnNoteBody());
		Assert.assertEquals("sfsfa1asdfaf", noteDetail.getCnNotebookId());
		Assert.assertEquals(1231312313132L, noteDetail.getCnNoteCreateTime());
		Assert.assertEquals(123123123123L, noteDetail.getCnNoteLastModifyTime());
		Assert.assertEquals("Junit-test 笔记", noteDetail.getCnNoteTitle());
		Assert.assertEquals("39295a3d-cc9b-42b4-b206-a2e7fab7e77c", noteDetail.getCnUserId());
		
		System.out.println("createNormalNoteTest success" );

	}
	
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void updateNormalNoteTest(){
		Note note = new Note();
		note.setCnNoteBody("正文正文正文正文正文正文正文正文啊啊啊啊");
		note.setCnNotebookId("sfsfa1asdfaf");
		note.setCnNoteCreateTime(1231312313132L);
		note.setCnNoteId("sdsadf1123dsfadf");
		note.setCnNoteLastModifyTime(123123123123L);
		note.setCnNoteTitle("Junit-test 笔记");
		note.setCnUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		noteMybatisDao.updateNormalNote(note);
		
		Note noteDetail = noteMybatisDao.getNoteDetail("sdsadf1123dsfadf");
		Assert.assertNotNull(noteDetail);//非空断言
		//比较每个更新过的的字段
		Assert.assertEquals("正文正文正文正文正文正文正文正文啊啊啊啊", noteDetail.getCnNoteBody());
		Assert.assertEquals("sfsfa1asdfaf", noteDetail.getCnNotebookId());
		Assert.assertEquals(1231312313132L, noteDetail.getCnNoteCreateTime());
		Assert.assertEquals(123123123123L, noteDetail.getCnNoteLastModifyTime());
		Assert.assertEquals("Junit-test 笔记", noteDetail.getCnNoteTitle());
		Assert.assertEquals("39295a3d-cc9b-42b4-b206-a2e7fab7e77c", noteDetail.getCnUserId());
		
		System.out.println("updateNormalNoteTest success" );
	}
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void moveNoteToOtherNotebookTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("cnNoteId", "1153295f-d1a5-4a39-b794-980633bdc3d8");//笔记id
		params.put("cnNotebookId", "a248decc-0fb6-402c-83e9-ea33a276968a");//新笔记本id
		noteMybatisDao.moveNoteToOtherNotebook(params);
		
		Note noteDetail = noteMybatisDao.getNoteDetail("sdsadf1123dsfadf");
		Assert.assertNotNull(noteDetail);//非空断言
		Assert.assertEquals("a248decc-0fb6-402c-83e9-ea33a276968a", noteDetail.getCnNotebookId());
		System.out.println("moveNoteToOtherNotebookTest success");
	}
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void deleteNormalNoteTest(){
		noteMybatisDao.deleteNormalNote("1153295f-d1a5-4a39-b794-980633bdc3d8");
		Note noteDetail = noteMybatisDao.getNoteDetail("1153295f-d1a5-4a39-b794-980633bdc3d8");
		Assert.assertNull(noteDetail);
		System.out.println("deleteNormalNoteTest success");
	}
	

	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void shareNoteTest(){
		Share share = new Share();
		share.setCnNoteId("sdafsfasdf");
		share.setCnShareBody("分享笔记~~");
		share.setCnShareId("adfsdf23234-adfsdf213");
		share.setCnShareTitle("afasdf-23423sdf-asdf23--1234");
		noteMybatisDao.shareNote(share);
		
		Share shareNoteDetail = noteMybatisDao.getShareNoteDetail("adfsdf23234-adfsdf213");
		Assert.assertNotNull(shareNoteDetail);
		
		System.out.println("shareNoteTest success");
	}
	
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void searchShareNoteTest(){
		Map<String, Object> params = new HashMap<String, Object>();//封装dao层参数
		params.put("key", "11");//关键字
		params.put("start", 1);//开始
		params.put("limit", 10);//结束
		List<Share> searchShareNote = noteMybatisDao.searchShareNote(params);
		Assert.assertNotNull(searchShareNote);
		
		System.out.println("searchShareNoteTest ok " + searchShareNote);
		
	}
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getShareNoteDetailTest(){
		Share shareNoteDetail = noteMybatisDao.getShareNoteDetail("1290dce6-d7f8-4418-b417-99f1a1f42b7b");
		
		Assert.assertNotNull(shareNoteDetail);
		
		System.out.println("shareNoteDetail ok " + shareNoteDetail);
	}
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getNoteActivityDetailTest(){
		NoteActivity noteActivityDetail = noteMybatisDao.getNoteActivityDetail("1290dce6-d7f8-4418-b417-99f1a1f42b7b");
		
		Assert.assertNotNull(noteActivityDetail);
		
		System.out.println("noteActivityDetail ok " + noteActivityDetail);
	}
	
}
