package com.tarena.bigdata.cloudnote.service;

import java.io.UnsupportedEncodingException;
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

import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;
import com.tarena.bigdata.cloudnote.entity.Notebook;
import com.tarena.bigdata.cloudnote.entity.Share;
import com.tarena.bigdata.cloudnote.util.SysEnv;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@ActiveProfiles("test")
public class NoteServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Resource
	private NoteService noteService;
	
	@Resource
	private NoteBookService noteBookService;
	
	
	
	/**
	 *获取笔记本详情
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void getNoteDetailTest(){

		
		Note noteDetail = noteService.getNoteDetail("b6f39eed-7d6f-42a5-af4f-53b3a1d12451");
		
		Assert.assertNotNull(noteDetail);

		System.out.println("getNoteDetailTest ok");
		
	}
	
	
	
	
	/**
	 * 根据笔记本id获得笔记列表
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void getNoteListByBookIdTest(){
		List<Note> noteListByBookId = noteService.getNoteListByBookId("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");
		
		Assert.assertNotNull(noteListByBookId);
		Assert.assertEquals(10 , noteListByBookId.size());//先从数据库中查出数目
		System.out.println("getNoteListByBookIdTest ok");
		
	}
	
	
	
	
	/**
	 * 在指定用户的指定笔记本的下创建一个新笔记
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void createNormalNoteTest() {
		Note note = new Note();
		
		note.setCnNoteBody("笔记笔记。。。");
		
		note.setCnNotebookId("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");

		note.setCnNoteTitle("title1...");

		noteService.createNormalNote(note);//add

		Note noteDetail = noteService.getNoteDetail(note.getCnNoteId());
		
		Assert.assertNotNull(noteDetail);
		Assert.assertEquals("笔记笔记。。。" ,noteDetail.getCnNoteBody());
		Assert.assertEquals("516f6f4f-eaa3-4c76-84ff-530b92c7f64d" , noteDetail.getCnNotebookId());
		Assert.assertEquals("title1..." , noteDetail.getCnNoteTitle());
		System.out.println("createNormalNoteTest ok");
		
	}
	
	
	
	
	
	
	/**
	 * 修改指定用户的指定笔记
	 * 
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void updateNormalNoteTest() {
		Note note = new Note();
		
		note.setCnNoteBody("笔记笔记111。。。");
		
		note.setCnNotebookId("516f6f4f-eaa3-4c76-84ff-530b92c7f64d");

		note.setCnNoteTitle("title11231...");

		note.setCnNoteId("051538a6-0f8e-472c-8765-251a795bc88f");
		
		noteService.updateNormalNote(note);//update
		
		Note noteDetail = noteService.getNoteDetail(note.getCnNoteId());
		
		Assert.assertNotNull(noteDetail);
		Assert.assertEquals("笔记笔记111。。。" , noteDetail.getCnNoteBody());
		Assert.assertEquals("516f6f4f-eaa3-4c76-84ff-530b92c7f64d" , noteDetail.getCnNotebookId());
		Assert.assertEquals("title11231..." , noteDetail.getCnNoteTitle());
		System.out.println("updateNormalNoteTest ok");
		
	}
	
	
	
	
	
	/**
	 * 彻底删除指定用户中回收站的指定笔记
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void deleteNoteTest() {

		noteService.deleteNote("01da5d69-89d5-4140-9585-b559a97f9cb0");//update
		
		Note noteDetail = noteService.getNoteDetail("01da5d69-89d5-4140-9585-b559a97f9cb0");
		
		Assert.assertNull(noteDetail);

		System.out.println("deleteNoteTest ok");
		
	}
	
	
	/**
	 * 移动笔记到指定的笔记本
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void moveNoteTest() {

		noteService.moveNote("01da5d69-89d5-4140-9585-b559a97f9cb0","0b11444a-a6d6-45ff-8d46-282afaa6a655");//move
		
		Note noteDetail = noteService.getNoteDetail("01da5d69-89d5-4140-9585-b559a97f9cb0");
		
		Assert.assertNotNull(noteDetail);
		Assert.assertEquals("0b11444a-a6d6-45ff-8d46-282afaa6a655", noteDetail.getCnNotebookId());
		System.out.println("moveNoteTest ok");
		
	}
	
	
	
	/**
	 * 移动笔记到指定的笔记本(适用于将笔记移动到回收站)
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void moveNoteTest1() {

		noteService.moveNote("39295a3d-cc9b-42b4-b206-a2e7fab7e77c","01da5d69-89d5-4140-9585-b559a97f9cb0",SysEnv.NOTEBOOK_RECYCLE);//move
		
		List<Notebook> notebookList = noteBookService.getNotebookList("39295a3d-cc9b-42b4-b206-a2e7fab7e77c", SysEnv.NOTEBOOK_RECYCLE);
		
		Assert.assertNotNull(notebookList);
		
		Note noteDetail = noteService.getNoteDetail("01da5d69-89d5-4140-9585-b559a97f9cb0");
		
		Assert.assertNotNull(noteDetail);
		Assert.assertEquals(notebookList.get(0).getCnNotebookId(), noteDetail.getCnNotebookId());
		System.out.println("moveNoteTest1 ok");
		
	}
	
	
	
	
	
	
	/**
	 * 将指定用户、指定笔记进行共享
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void shareNoteTest() {

		noteService.shareNote("0c8d8b44-7109-4df1-8432-c03526f5c87a");//分享

		System.out.println("shareNoteTest ok");
		
	}
	
	
	
	
	/**
	 * 将查询所有共享笔记
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void searchShareNoteTest() {

		List<Share> searchShareNote = noteService.searchShareNote("1",0,10);
		
		Assert.assertNotNull(searchShareNote);
		
		Assert.assertEquals(10, searchShareNote.size());//先查询数据库，得出数据再对比
		
		System.out.println("searchShareNoteTest ok");
		
	}
	
	
	
	
	
	
	/**
	 * 查询共享笔记详情
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void getShareNoteDetailTest() {

		Share shareNoteDetail = noteService.getShareNoteDetail("2233a28f-074f-46fe-ab22-a4cbd7879418");
		
		Assert.assertNotNull(shareNoteDetail);
		
		System.out.println("getShareNoteDetailTest ok");
		
	}
	
	
	/**
	 * 查询活动笔记详情
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void getNoteActivityDetailTest() {

		NoteActivity noteActivityDetail = noteService.getNoteActivityDetail("60aadac1-6e35-4b2e-a5c4-3cd430a7803a");
		
		Assert.assertNotNull(noteActivityDetail);
		
		System.out.println("getNoteActivityDetailTest ok");
		
	}
	
}
