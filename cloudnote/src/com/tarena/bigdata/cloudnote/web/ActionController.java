package com.tarena.bigdata.cloudnote.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.bigdata.cloudnote.entity.Activity;
import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;
import com.tarena.bigdata.cloudnote.entity.Response;
import com.tarena.bigdata.cloudnote.service.ActivityService;


/**
 * 活动业务控制
 * @author YHT
 */
@Controller
@RequestMapping("/{loginUserId}/action")
public class ActionController {
	
	@Resource(name = "activityService")
	private ActivityService activityService;
	
	/**
	 * 获得活动列表
	 * @Title: getActionList 
	 * @Time：2014年6月11日 下午4:46:41 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/getActionList", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getActionList( HttpServletRequest request, HttpServletResponse response) {
		Response message = new Response(); 
		try{
			List<Activity> list = activityService.getAllActivity();
			message.setResource(list);
		}catch(Exception e){
			message.setMessage("获取活动列表异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	
	/**
	 * 用户笔记参加活动
	 * @Title: createNoteActivity 
	 * @Time：2014年6月11日 下午5:00:18 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/createNoteActivity/{noteId}/to/{activityId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response createNoteActivity(
			@PathVariable("loginUserId") String userId,
			@PathVariable("noteId") String noteId,
			@PathVariable("activityId") String activityId,
			HttpServletRequest request,
			HttpServletResponse response) {
		Response message = new Response();
		try{
			String noteActivityId = this.activityService.createNoteActivity(userId,activityId, noteId);
			message.setResource(noteActivityId);
		}catch(Exception e){
			message.setMessage("参加活动操作异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	

	/**
	 * 查询指定用户活动笔记本的笔记列表
	 * @Title: getActionNoteList 
	 * @Time：2014年6月11日 下午5:00:18 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/getActionNoteList/{noteBookId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getActionNoteList(
			@PathVariable("noteBookId") String noteBookId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			// 查询活动笔记列表
			List<Note> list = this.activityService.getActionNoteListById(noteBookId);
			message.setResource(list);
		}catch(Exception e){
			message.setMessage("查询活动笔记列表异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	
	

	/**
	 * 查询指定活动下已参加活动的笔记列表
	 * @Title: getActionNotesByActivityId 
	 * @Time：2014年6月11日 下午5:00:18 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/getActionNotesByActivityId/{activityId}/{beginIndex}/{endIndex}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getActionNotesByActivityId(
			@PathVariable("activityId") String activityId,
			@PathVariable("beginIndex") String beginIndex,
			@PathVariable("endIndex") String endIndex,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			// 查询活动笔记列表
			List<NoteActivity> list = this.activityService.getActivityNotesByActivityId(activityId,beginIndex,endIndex);
			message.setResource(list);
		}catch(Exception e){
			message.setMessage("查询活动笔记列表异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	
	/**
	 * 查询指定活动id的
	 * @Title: getActionNotesByActivityId 
	 * @Time：2014年6月11日 下午5:00:18 zhoujia
	 * @return Response
	 */
	@RequestMapping(value = "/findNoteActivityById/{noteActivityId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response findNoteActivityById(
			@PathVariable("noteActivityId") String noteActivityId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			// 查询活动笔记列表
			NoteActivity noteActivity = this.activityService.findNoteActivityById(noteActivityId);
			message.setResource(noteActivity);
		}catch(Exception e){
			message.setMessage("查询活动笔记列表异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	
}
