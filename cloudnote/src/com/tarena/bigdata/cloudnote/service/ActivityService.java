package com.tarena.bigdata.cloudnote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tarena.bigdata.cloudnote.dao.ActivityMybatisDao;
import com.tarena.bigdata.cloudnote.dao.NoteMybatisDao;
import com.tarena.bigdata.cloudnote.entity.Activity;
import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;
import com.tarena.bigdata.cloudnote.entity.Notebook;
import com.tarena.bigdata.cloudnote.util.SysEnv;
import com.tarena.bigdata.cloudnote.util.UUIDUtil;

/**
 * 活动模块管理逻辑层
 * @author YHT
 */
@Component("activityService")
@Transactional
public class ActivityService {
	
	@Resource(name="activityMybatisDao")
	private ActivityMybatisDao activityMybatisDao;

	@Resource(name="noteMybatisDao")
	private NoteMybatisDao noteMybatisDao;

	@Resource(name="noteBookService")
	private NoteBookService noteBookService;
	
	/**
	 * 获取活动列表
	 * @Title: getAllActivity 
	 * @Time：2014年6月11日 下午3:57:17 YHT
	 * @return List<Activity>
	 */
	public List<Activity> getAllActivity(){
		List<Activity> list = this.activityMybatisDao.getAllActivityList();//获取所有活动列表
		return list;
	}
	

	/**
	 * 获得一个用户指定笔记本下的笔记列表
	 * @Title: getActionNoteListById 
	 * @Time：2014年6月11日 下午3:49:12 YHT
	 * @return List<Note>
	 */
	public List<Note> getActionNoteListById(String noteBookId){
		List<Note> list = this.activityMybatisDao.getActionNoteListById(noteBookId);//获取指定用户的活动笔记本的笔记列表
		return list;
	}
	
	
	/**
	 * 获得一个活动下的所有人参加的笔记列表
	 * @Title: getActivityNoteListByActivityId 
	 * @Time：2014年6月11日 下午3:49:12 YHT
	 * @return List<NoteActivity>
	 */
	public List<NoteActivity> getActivityNotesByActivityId(String activityId,String beginIndex,String endIndex){
		int limit = 10;
		if(beginIndex != null && endIndex != null){
			limit = Integer.parseInt(endIndex) - Integer.parseInt(beginIndex);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cnActivityId", activityId);
		params.put("begin", Integer.parseInt(beginIndex));
		params.put("limit", limit);
		List<NoteActivity> list = this.activityMybatisDao.getAllActivityNoteList(params);//获取指定活动下的所有笔记
		return list;
	}
	
	/**
	 * 创建活动笔记
	 * @Title: createNoteActivity
	 * @Time：2014年6月11日 下午3:57:17 YHT
	 * @return String
	 */
	public String createNoteActivity(String userId, String activityId, String noteId){
		String noteActivityId = "";
			// 获得要参加活动的笔记
			Note note = noteMybatisDao.getNoteDetail(noteId);
			if(note != null){
				noteActivityId = UUIDUtil.getUId();// 获得UUID
				NoteActivity na = new NoteActivity();// 添加记录到活动笔记表
				na.setCnActivityId(activityId);
				na.setCnNoteActivityBody(note.getCnNoteBody());
				na.setCnNoteActivityId(noteActivityId);
				na.setCnNoteActivityTitle(note.getCnNoteTitle());
				na.setCnNoteId(note.getCnNoteId());
				this.activityMybatisDao.activityNote(na);//将指定用户、指定笔记进行活动
				List<Notebook> notebookList = this.noteBookService.getNotebookList(userId, SysEnv.NOTEBOOK_ACTION);// 查询用户的活动笔记本
				if(notebookList != null && notebookList.size() > 0){// 如果用户的活动笔记本存在，则执行添加操作
					// 新建note的快照记录到active 类型下
					note.setCnNoteId(UUIDUtil.getUId());
					note.setCnNoteCreateTime(System.currentTimeMillis());
					note.setCnNoteLastModifyTime(System.currentTimeMillis());
					note.setCnNotebookId(notebookList.get(0).getCnNotebookId());
					this.noteMybatisDao.createNormalNote(note);//在指定用户的指定笔记本的下创建一个新笔记
				}
			}
		return noteActivityId;
	}
	
	
	/**
	 * 查找参加活动笔记
	 * @param noteActivityId
	 * @return
	 */
	public NoteActivity findNoteActivityById(String noteActivityId){
		NoteActivity noteActivity = activityMybatisDao.findNoteActivityById(noteActivityId);//根据参加活动笔记的id查找参加活动笔记
		return noteActivity;
	}
}
