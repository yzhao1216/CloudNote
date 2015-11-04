package com.tarena.bigdata.cloudnote.service;

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

import com.tarena.bigdata.cloudnote.entity.Notebook;
import com.tarena.bigdata.cloudnote.entity.NotebookType;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@ActiveProfiles("test")
public class NoteBookserviceTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource
	private NoteBookService noteBookService;
	
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void getNotebookByIdTest(){
		
		Notebook notebookById = noteBookService.getNotebookById("1755013b-05fc-4218-83cf-956287a81b49");
		
		Assert.assertNotNull(notebookById);
		Assert.assertEquals("1755013b-05fc-4218-83cf-956287a81b49", notebookById.getCnNotebookId());
		
		System.out.println("getNotebookByIdTest ok");
	}
	
	
	/***
	 * 获取指定用户的笔记本列表 测试
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void getNotebookListTest(){
		List<Notebook> notebookList = noteBookService.getNotebookList("333c6d0b-e4a2-4596-9902-a5d98c2f665a" , "favorites");
		Assert.assertNotNull(notebookList);
		Assert.assertEquals(1, notebookList.size());//某用户的收藏夹笔记只有一条
		System.out.println("getNotebookListTest ok" + notebookList.size());
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void getSpecialListTest(){
		Map<String, Notebook> specialList = noteBookService.getSpecialList("333c6d0b-e4a2-4596-9902-a5d98c2f665a");
		Assert.assertNotNull(specialList);
		Assert.assertEquals(4, specialList.size());//特殊笔记4条
		System.out.println("getSpecialListTest ok" + specialList.size());
	}
	
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void getSpecialTest(){
		Notebook special = noteBookService.getSpecial("333c6d0b-e4a2-4596-9902-a5d98c2f665a","push");
		Assert.assertNotNull(special);
		System.out.println("getSpecialTest ok");
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void createNoteBookTest(){
		
		Notebook notebook = new Notebook();
		notebook.setCnNotebookId("zxcvzxcvzxv111");
		notebook.setCnNotebookName("bookname111111");
		notebook.setCnNotebookTypeId("1");
		String createNoteBookId = noteBookService.createNoteBook(notebook, "action");
		
		Assert.assertNotNull(createNoteBookId);
		
		System.out.println("createNoteBookTest ok");
	}
	
	
	/**
	 * 更新笔记本测试
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void updateNoteBookTest(){
		
		Notebook notebook = new Notebook();
		notebook.setCnNotebookId("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		notebook.setCnNotebookName("bookname111111");
		notebook.setCnNotebookTypeId("1");
		
		noteBookService.updateNoteBook(notebook);
		
		Notebook notebookById = noteBookService.getNotebookById("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		
		Assert.assertNotNull(notebookById);
		Assert.assertEquals("bookname111111", notebookById.getCnNotebookName()); 
		
		System.out.println("updateNoteBookTest ok");
	}
	

	/**
	 * 删除测试 
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void deleteNoteBookTest(){
		noteBookService.deleteNoteBook("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		
		Notebook notebookById = noteBookService.getNotebookById("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		
		Assert.assertNull(notebookById);
		
		System.out.println("deleteNoteBookTest ok");
	}
	
	
	
	/**
	 * 初始化用户特殊笔记本测试 
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void initNotebooksTest(){
		noteBookService.initNotebooks("48595f52-b22c-4485-9244-f4004255b972");//参数userId
		
		Map<String, Notebook> specialList = noteBookService.getSpecialList("48595f52-b22c-4485-9244-f4004255b972");
		Assert.assertNotNull(specialList);
		Assert.assertEquals(4 , specialList.size());//每个用户初始化4个特殊笔记本
		System.out.println("initNotebooksTest ok");
	}
	
	/**
	 * 初始化参数测试 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void getNotebookTypeListTest(){
		noteBookService.getNotebookTypeList();
		
		Map<String, NotebookType> types = NoteBookService.types;
		
		Assert.assertNotNull(types);
		Assert.assertEquals(5 , types.size());//每个用户总共有5个
		System.out.println("getNotebookTypeListTest ok");
	}
}
