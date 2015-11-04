package com.tarena.bigdata.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tarena.bigdata.cloudnote.entity.Activity;
import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;

@MyBatisRepository
public interface ActivityMybatisDao {
	//获取指定用户的活动笔记本的笔记列表
	public List<Note> getActionNoteListById(String notebookId);
	//获取指定用户的活动笔记本的笔记列表
	public List<Note> getActionNoteListByCode(String cnNotebookTypeCode);
	//获取所有活动列表
	public List<Activity> getAllActivityList();
	//获取指定活动下的所有笔记
	public List<NoteActivity> getAllActivityNoteList(Map<String, Object> params);
	//将指定用户、指定笔记进行活动
	public void activityNote(NoteActivity noteActivity);
	//根据参加活动笔记的id查找参加活动笔记
	public NoteActivity findNoteActivityById(String noteActivityId);
}
