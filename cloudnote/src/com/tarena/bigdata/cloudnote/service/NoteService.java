package com.tarena.bigdata.cloudnote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tarena.bigdata.cloudnote.dao.NoteMybatisDao;
import com.tarena.bigdata.cloudnote.dao.NotebookMybatisDao;
import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;
import com.tarena.bigdata.cloudnote.entity.Notebook;
import com.tarena.bigdata.cloudnote.entity.Share;
import com.tarena.bigdata.cloudnote.util.UUIDUtil;

/**
 * 用户笔记管理逻辑层
 * @author YHT
 */
@Component("noteService")
@Transactional
public class NoteService {
	@Resource(name="noteMybatisDao")
	private NoteMybatisDao noteMybatisDao;
	
	@Resource(name="noteBookService")
	private NoteBookService noteBookService;
	
	@Resource(name="notebookMybatisDao")
	private NotebookMybatisDao notebookMybatisDao;
	
	
	/**
	 * 根据笔记本id获得笔记列表
	 * @Title: getNoteListByBookId 
	 * @Time：2014年6月6日 下午9:58:14 YHT
	 * @return List<Note>
	 */
	public List<Note> getNoteListByBookId(String notebookId){
		List<Note> list = this.noteMybatisDao.getNormalNoteListById(notebookId);//获取指定用户的指定笔记本的笔记列表，只获取id和name
		return list;
	}
	
//	/**
//	 * 获取指定用户的指定笔记本的笔记列表，只获取id和name
//	 * @Title: getNormalNoteListByCode 
//	 * @Time：2014年6月6日 下午6:39:57 YHT
//	 * @return List<Note>
//	 */
//	public List<Note> getNormalNoteListByCode(String cnNotebookTypeCode){
//	 List<Note>	list = this.noteMybatisDao.getNormalNoteListByCode(cnNotebookTypeCode);//获取指定用户的指定笔记本的笔记列表，只获取id和name
//		return list;
//	}
	
	/**
	 * 在指定用户的指定笔记本的下创建一个新笔记
	 * @param note
	 */
	public void createNormalNote(Note note){
			note.setCnNoteId(UUIDUtil.getUId());//笔记id
			note.setCnNoteCreateTime(System.currentTimeMillis());//创建时间
			note.setCnNoteLastModifyTime(System.currentTimeMillis());//最后操作时间
			this.noteMybatisDao.createNormalNote(note);//在指定用户的指定笔记本的下创建一个新笔记
	}
	
	/**
	 * 修改指定用户的指定笔记
	 * @param note
	 */
	public void updateNormalNote(Note note){
			note.setCnNoteLastModifyTime(System.currentTimeMillis());//最后操作时间
			this.noteMybatisDao.updateNormalNote(note);//修改指定用户的指定笔记
	}
	

	/**
	 * 彻底删除指定用户中回收站的指定笔记
	 * @param noteId
	 */
	public void deleteNote(String noteId){
			this.noteMybatisDao.deleteNormalNote(noteId);////彻底删除指定用户中回收站的指定笔记
	}
	
	/**
	 * 移动笔记到指定的笔记本
	 * @Title: deleteNormalNote 
	 * @Time：2014年6月6日 下午6:32:10 YHT
	 * @return void
	 */
	public void moveNote(String noteId, String toNoteBookId){
			Map<String, String> params = new HashMap<String, String>();//封装dao层参数
			params.put("cnNoteId", noteId);//笔记id
			params.put("cnNotebookId", toNoteBookId);//新笔记本id
			this.noteMybatisDao.moveNoteToOtherNotebook(params);//移动笔记到指定的笔记本
	}

	/**
	 * 移动笔记到指定的笔记本(适用于将笔记移动到回收站)
	 * @param userId：用户id
	 * @param noteId：笔记id
	 * @param toTypeCode：回收站笔记本code
	 */
	public void moveNote(String userId, String noteId, String toTypeCode){
			List<Notebook> list = this.noteBookService.getNotebookList(userId, toTypeCode);//获取回收站笔记本
			if(list != null && list.size() > 0){
				String toNoteBookId = list.get(0).getCnNotebookId();//只能查出一个笔记本，所以取一个笔记本id
				this.moveNote(noteId, toNoteBookId);//把笔记移动到回收站
			}
	}
	
	/**
	 * 获取笔记本详情
	 * @param noteId：笔记id
	 * @return
	 */
	public Note getNoteDetail(String noteId){
		Note note = this.noteMybatisDao.getNoteDetail(noteId);
		return note;
	}
	
	/**
	 * 将指定用户、指定笔记进行共享
	 * @param noteId：笔记id
	 */
	public void shareNote(String noteId){
			Note note = this.noteMybatisDao.getNoteDetail(noteId);//查询笔记
			if(note != null){
				Share share = new Share();//创建分享笔记实体，并封装参数
				
				share.setCnShareBody(note.getCnNoteBody());
				share.setCnShareId(UUIDUtil.getUId());
				share.setCnShareTitle(note.getCnNoteTitle());
				share.setCnNoteId(note.getCnNoteId());
				
				this.noteMybatisDao.shareNote(share);//将指定用户、指定笔记进行共享
			}
	}
	
	/**
	 * 将查询所有共享笔记
	 * @param key:关键字
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Share> searchShareNote(String key,int start, int limit){
			Map<String, Object> params = new HashMap<String, Object>();//封装dao层参数
			params.put("key", key);//关键字
			params.put("start", start);//开始
			params.put("limit", limit);//结束
			List<Share> list = this.noteMybatisDao.searchShareNote(params);//模糊查询
		return list;
	}
	/**
	 * 查询共享笔记详情
	 * @param shareNoteId：共享笔记id
	 * @return
	 */
	public Share getShareNoteDetail(String shareNoteId) {
		Share share = noteMybatisDao.getShareNoteDetail(shareNoteId);//获取共享笔记本详情
		return share;
	}
	
	/**
	 * 查询活动笔记详情
	 * @param noteActivityId：笔记活动id
	 * @return
	 */
	public NoteActivity getNoteActivityDetail(String noteActivityId){
		NoteActivity noteActivity = noteMybatisDao.getNoteActivityDetail(noteActivityId);//获取活动笔记本详情
			return noteActivity;
	}
}
