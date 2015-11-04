package com.tarena.bigdata.cloudnote.web;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.bigdata.cloudnote.entity.Note;
import com.tarena.bigdata.cloudnote.entity.NoteActivity;
import com.tarena.bigdata.cloudnote.entity.Notebook;
import com.tarena.bigdata.cloudnote.entity.Response;
import com.tarena.bigdata.cloudnote.entity.Share;
import com.tarena.bigdata.cloudnote.service.NoteBookService;
import com.tarena.bigdata.cloudnote.service.NoteService;
import com.tarena.bigdata.cloudnote.util.SysEnv;
/**
 * 笔记业务控制
 * @author YHT
 */
@Controller
@RequestMapping("/{loginUserId}/note")
public class NoteController {
	//注入笔记service
	@Resource(name = "noteService")
	private NoteService noteService;
	
	//注入笔记本service
	@Resource(name = "noteBookService")
	private NoteBookService noteBookService;
	
	/**
	 * 获得普通笔记本下的笔记列表
	 * @Title: getNormalNoteList
	 * @Time：2-114年6月5日 下午12:-14:36 YHT
	 * @param loginUserName
	 * @return User
	 */
	@RequestMapping(value = "/getNormalNoteList/{noteBookId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getNormalNoteList(
			@PathVariable("loginUserId") String loginUserId,
			@PathVariable("noteBookId") String noteBookId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(StringUtils.isNoneEmpty(noteBookId)){//如果笔记本id不为空，则查询这个笔记本下所有的笔记
				List<Note> list = this.noteService.getNoteListByBookId(noteBookId);
				//封装返回结果
				message.setResource(list);
			}
		}catch(Exception e){
			message.setMessage("查询笔记列表异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}

	/**
	 * 创建笔记
	 * @Title: createNormalNote 
	 * @Time：2014年6月6日 下午6:09:12 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/createNormalNote/{noteBookId}", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Response createNormalNote(
			@PathVariable("loginUserId") String userId,
			@PathVariable("noteBookId") String noteBookId,
			@RequestBody Note note,
			HttpServletRequest request,
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(note != null){//空判断
				note.setCnUserId(userId);
				note.setCnNotebookId(noteBookId);
				//生成新笔记
				this.noteService.createNormalNote(note);
				message.setResource(note.getCnNoteId());
			}
		}catch(Exception e){//异常处理
			message.setMessage("添加笔记异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}

	/**
	 * 更新笔记
	 * @Title: updateNormalNote 
	 * @Time：2014年6月6日 下午5:23:21 YHT
	 * @return Response
	 */
	@RequestMapping(value = "updateNormalNote/{noteId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	@ResponseBody
	public Response updateNormalNote(
			@PathVariable("loginUserId") String loginUserId,
			@PathVariable("noteId") String noteId,
			@RequestBody Note note,
			HttpServletRequest request,
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			note.setCnNoteId(noteId);
			if(note != null && StringUtils.isNotEmpty(note.getCnNoteId())){
				//设置作者
				note.setCnUserId(loginUserId);
				
				//更新笔记
				this.noteService.updateNormalNote(note);
			}else{
				message.setStatus(-1);
				message.setMessage("更新笔记本传入参数无效！");
			}
		}catch(Exception e){
			message.setMessage("更新笔记异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}

	/**
	 * 删除笔记
	 * @Title: delleteNormalNote 
	 * @Time：2014年6月6日 下午5:23:48 YHT
	 * @return Response
	 */
	@RequestMapping(value = "deleteNormalNote/{noteId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	@ResponseBody
	public Response deleteNormalNote(
			@PathVariable("loginUserId") String userId,
			@PathVariable("noteId") String noteId,
			HttpServletRequest request,
			HttpServletResponse response){
		Response message = new Response(); 
		try{
			if(StringUtils.isNotEmpty(noteId)){
				// 将笔记移动到回收站
				this.noteService.moveNote(userId, noteId, SysEnv.NOTEBOOK_RECYCLE);
			}
		}catch(Exception e){
			message.setMessage("删除普通笔记到回收站异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}

	/**
	 * 查询回收站笔记列表
	 * @Title: getRecycleNoteList 
	 * @Time：2014年6月6日 下午5:27:15 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/getRecycleNoteList/{noteBookId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getRecycleNoteList(
			@PathVariable("loginUserId") String userId,
			@PathVariable("noteBookId") String noteBookId,
			HttpServletRequest request,
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			
			if(StringUtils.isNoneEmpty(noteBookId)){//空判断
				//查找回收站笔记
				List<Note> list = this.noteService.getNoteListByBookId(noteBookId);
				//设置返回结果
				message.setResource(list);
			}
		}catch(Exception e){//异常处理
			message.setMessage("查询回收站笔记列表异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}

	/**
	 * 删除回收站笔记
	 * @Title: deleteRecycleNote 
	 * @Time：2014年6月6日 下午5:52:03 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/deleteRecycleNote/{noteId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	@ResponseBody
	public Response deleteRecycleNote(
			@PathVariable("loginUserId") String loginUserName,
			@PathVariable("noteId") String noteId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(StringUtils.isNotEmpty(noteId)){//删除笔记
				this.noteService.deleteNote(noteId);
			}
		}catch(Exception e){
			message.setMessage("删除回收站笔记异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}

	/**
	 * 恢复回收站笔记到指定的笔记本
	 * @Title: updateRecycleNote 
	 * @Time：2014年6月6日 下午5:51:49 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/updateRecycleNote/{noteId}/to/{noteBookId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	@ResponseBody
	public Response updateRecycleNote(
			@PathVariable("loginUserId") String loginUserId,
			@PathVariable("noteId") String noteId,
			@PathVariable("noteBookId") String noteBookId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(StringUtils.isNotEmpty(noteBookId)){
				//查找笔记本
				Notebook notebook = noteBookService.getNotebookById(noteBookId);
				if(StringUtils.isNoneEmpty(noteId) && notebook != null){//如果notebook不为空则移动到这个笔记本
					this.noteService.moveNote(noteId, noteBookId);
				}else{
					message.setMessage("回复到的笔记本不存在！");
					message.setStatus(-1);
				}
			}
		}catch(Exception e){//异常处理
			message.setMessage("恢复回收站笔记到指定的笔记本操作异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	/**
	 * 移动笔记
	 * @Title: moveNote 
	 * @Time：2-114年6月5日 下午5:-18:-1-1 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/moveNote/{noteId}/to/{noteBookId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	@ResponseBody
	public Response moveNote(
			@PathVariable("loginUserId") String userId,
			@PathVariable("noteId") String noteId,
			@PathVariable("noteBookId") String noteBookId,
			HttpServletRequest request,
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(StringUtils.isNotEmpty(noteBookId)){
				//查找笔记本
				Notebook notebook = noteBookService.getNotebookById(noteBookId);
				if(StringUtils.isNoneEmpty(noteId) && notebook != null){
					//移动笔记本
					this.noteService.moveNote(noteId, noteBookId);
				}else{
					message.setMessage("移动到的笔记本不存在！");
					message.setStatus(-1);
				}
			}
		}catch(Exception e){
			message.setMessage("添加笔记本异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	/**
	 * 查询本用户活动笔记列表
	 * @Title: updateRecycleNote 
	 * @Time：2-114年6月5日 下午4:42:57 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/getActionNoteList/{noteBookId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getActionNoteList(
			@PathVariable("loginUserId") String userId,
			@PathVariable("noteBookId") String noteBookId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(StringUtils.isNotEmpty(noteBookId)){
				//查询活动笔记列表
				List<Note> list = this.noteService.getNoteListByBookId(noteBookId);
				//返回结果
				message.setResource(list);
			}
		}catch(Exception e){
			message.setMessage("添加笔记本异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	/**
	 * 查询笔记内容
	 * @Title: getNoteDetail 
	 * @Time：2-114年6月5日 下午4:45:-12 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/getNoteDetail/{noteId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getNoteDetail(
			@PathVariable("loginUserId") String userId,
			@PathVariable("noteId") String noteId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(StringUtils.isNotEmpty(noteId)){
				//查询笔记对象
				Note note = this.noteService.getNoteDetail(noteId);
				//返回结果
				message.setResource(note);
			}
		}catch(Exception e){
			message.setMessage("查询笔记详细内容异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	/**
	 * 共享笔记
	 * @Title: createShareNote 
	 * @Time：2-114年6月5日 下午4:45:45 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/createShareNote/{noteId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response createShareNote(
			@PathVariable("loginUserId") String userId,
			@PathVariable("noteId") String noteId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(StringUtils.isNotEmpty(noteId)){//共享笔记
				this.noteService.shareNote(noteId);
			}
		}catch(Exception e){
			message.setMessage("共享笔记异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	
	
	
	
	/**
	 * 搜索共享笔记列表
	 * @Title: searchShareNote 
	 * @Time：2-114年6月5日 下午4:54:51 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/searchShareNote/{searchKey}/{beginIndex}/{endIndex}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response searchShareNote(
			@PathVariable("loginUserId") String userId,
			@PathVariable("searchKey") String searchKey,
			@PathVariable("beginIndex") String beginIndex,
			@PathVariable("endIndex") String endIndex,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		
		try{
			searchKey=URLDecoder.decode(searchKey,"UTF-8");//解码查询关键字
			if(StringUtils.isNumeric(beginIndex) && StringUtils.isNumeric(endIndex)){
				int start = Integer.valueOf(beginIndex);
				int limit = Integer.valueOf(endIndex)-Integer.valueOf(beginIndex);
				//查询搜索结果
				List<Share> list = this.noteService.searchShareNote(searchKey, start, limit);
				//设置返回值
				message.setResource(list);
			}
		}catch(Exception e){
			message.setMessage("搜索共享笔记列表异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	/**
	 * 查询共享笔记内容
	 * @Title: getNoteDetail 
	 * @Time：2-114年6月5日 下午4:45:-12 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/getShareNoteDetail/{ShareNoteId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getShareNoteDetail(
			@PathVariable("loginUserId") String userId,
			@PathVariable("ShareNoteId") String ShareNoteId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(StringUtils.isNotEmpty(ShareNoteId)){
				//查询共享笔记
				Share share = this.noteService.getShareNoteDetail(ShareNoteId);
				//返回结果
				message.setResource(share);
			}
		}catch(Exception e){
			message.setMessage("查询笔记详细内容异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	
	
	
	/**
	 * 收藏共享/活动笔记
	 * @Title: getNoteDetail 
	 * @Time：2-114年6月5日 下午4:45:-12 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/likeNote/{shareOrActivityId}/{noteType}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response likeNote(
			@PathVariable("loginUserId") String userId,
			@PathVariable("shareOrActivityId") String shareOrActivityId,//分享的笔记id或者活动的笔记id
			@PathVariable("noteType")String noteType,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			//收藏共享的笔记
			if(StringUtils.isNotEmpty(shareOrActivityId) && noteType.equals(SysEnv.NOTEBOOK_FAVORITES)){//共享笔记
				//查询到用户共享笔记本
				Notebook notebook = noteBookService.getSpecial(userId,SysEnv.NOTEBOOK_FAVORITES);
				//查询到这个分享得笔记
				Share share = this.noteService.getShareNoteDetail(shareOrActivityId);
				Note note = new Note();
				note.setCnNotebookId(notebook.getCnNotebookId());
				note.setCnUserId(userId);
				note.setCnNoteTitle(share.getCnShareTitle());
				note.setCnNoteBody(share.getCnShareBody());
				//复制到自己的收藏笔记本中
				noteService.createNormalNote(note);
				message.setResource(note.getCnNoteId());
				message.setStatus(1);
			}else if(StringUtils.isNotEmpty(shareOrActivityId) && noteType.equals(SysEnv.NOTEBOOK_ACTION)){//活动笔记
				//收藏活动的笔记
				Notebook notebook = noteBookService.getSpecial(userId,SysEnv.NOTEBOOK_FAVORITES);
				//查询到这个活动笔记
				NoteActivity noteActivity = this.noteService.getNoteActivityDetail(shareOrActivityId);
				Note note = new Note();
				note.setCnNotebookId(notebook.getCnNotebookId());
				note.setCnUserId(userId);
				note.setCnNoteTitle(noteActivity.getCnNoteActivityTitle());
				note.setCnNoteBody(noteActivity.getCnNoteActivityBody());
				//复制到自己的收藏笔记本中
				noteService.createNormalNote(note);
				message.setResource(note.getCnNoteId());
				message.setStatus(1);
			}
		}catch(Exception e){
			message.setMessage("收藏共享笔记异常！");
			message.setStatus(-1);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
}
