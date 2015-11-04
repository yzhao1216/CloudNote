package com.tarena.bigdata.cloudnote.dao;

import java.util.List;
import java.util.Map;

import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;
import com.tarena.bigdata.cloudnote.entity.Share;


@MyBatisRepository
public interface NoteMybatisDao {
	//获取指定用户的指定笔记本的笔记列表，只获取id和name
	public List<Note> getNormalNoteListById(String notebookId);
	//获取指定用户的指定笔记本的笔记列表，只获取id和name
	//public List<Note> getNormalNoteListByCode(String cnNotebookTypeCode);
	//在指定用户的指定笔记本的下创建一个新笔记
	public void createNormalNote(Note note);
	//修改指定用户的指定笔记
	public void updateNormalNote(Note note);
	//移动笔记到指定的笔记本
	public void moveNoteToOtherNotebook(Map<String, String> params);
	//彻底删除指定用户中回收站的指定笔记
	public void deleteNormalNote(String noteId);
	//获取笔记本详情
	public Note getNoteDetail(String noteId);
	//将指定用户、指定笔记进行共享
	public void shareNote(Share share);
	//将查询所有共享笔记
	public List<Share> searchShareNote(Map<String, Object> params);
	//获取共享笔记本详情
	public Share getShareNoteDetail(String shareNoteId);
	//获取活动笔记本详情
	public NoteActivity getNoteActivityDetail(String activityNoteId);
}
