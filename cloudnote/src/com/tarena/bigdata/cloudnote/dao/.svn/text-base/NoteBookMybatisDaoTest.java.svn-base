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

import com.tarena.bigdata.cloudnote.entity.Notebook;
import com.tarena.bigdata.cloudnote.entity.NotebookType;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@ActiveProfiles("test")
public class NoteBookMybatisDaoTest {
	
	@Resource
	private NotebookMybatisDao notebookMybatisDao;
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getNotebookListTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("cnNotebookTypeCode", "push");
		params.put("cnUserId", "39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		List<Notebook> notebookList = notebookMybatisDao.getNotebookList(params);
		
		//非空断言
		Assert.assertNotNull(notebookList);
		System.out.println("getNotebookListTest ok " + notebookList.size());
	}
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void createNoteBookTest(){
		Notebook notebook = new Notebook();
		notebook.setCnNotebookId("131214543");
		notebook.setCnNotebookName("笔记本Junit——test");
		notebook.setCnNotebookTypeId("3");
		notebookMybatisDao.createNoteBook(notebook);
		
		Notebook noteBook = notebookMybatisDao.getNoteBookById("131214543");
		Assert.assertNotNull(noteBook);//添加后，查询出来，进行非空断言
		System.out.println("createNoteBookTest success");
	}
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void updateNoteBookTest(){
		Notebook notebook = new Notebook();
		notebook.setCnNotebookId("131214543");
		notebook.setCnNotebookName("笔记本Junit——test");
		notebook.setCnNotebookTypeId("3");
		notebookMybatisDao.updateNoteBook(notebook);
		
		Notebook noteBook = notebookMybatisDao.getNoteBookById("131214543");
		Assert.assertNotNull(noteBook);//添加后，查询出来，进行非空断言
		Assert.assertEquals("笔记本Junit——test", noteBook.getCnNotebookName());//新对象的笔记本名称为新修改的，则通过断言
		Assert.assertEquals("3", noteBook.getCnNotebookTypeId());//新对象的笔记本Type为新修改的，则通过断言
		
		System.out.println("updateNoteBookTest success");
	}
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void deleteNoteBookTest(){
		notebookMybatisDao.deleteNoteBook("131214543");
		Notebook noteBook = notebookMybatisDao.getNoteBookById("131214543");
		Assert.assertNull(noteBook);//查询出来，进行空断言
		System.out.println("delete success");
	}
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getNotebookTypeTest(){
		NotebookType notebookType = notebookMybatisDao.getNotebookType("favorites");
		Assert.assertNotNull(notebookType);//进行非空断言
		System.out.println("getNotebookTypeTest ok" + notebookType);
	}
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getNotebookTypeListTest(){
		List<NotebookType> notebookTypeList = notebookMybatisDao.getNotebookTypeList();
		Assert.assertNotNull(notebookTypeList);//进行非空断言
		System.out.println("getNotebookTypeListTest ok" + notebookTypeList);
	}
	
	
	
	@Test
	@Transactional   //标明此方法需使用事务  
	@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void getNoteBookByIdTest(){
		Notebook noteBook = notebookMybatisDao.getNoteBookById("131214543");
		Assert.assertNotNull(noteBook);//进行非空断言
		System.out.println("getNoteBookByIdTest ok " + noteBook);
	}
	
	
}
