package com.tarena.bigdata.cloudnote.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.bigdata.cloudnote.entity.Notebook;
import com.tarena.bigdata.cloudnote.entity.Response;
import com.tarena.bigdata.cloudnote.service.NoteBookService;
import com.tarena.bigdata.cloudnote.util.SysEnv;

/**
 * 笔记本业务管理
 * @author YHT
 */
@Controller
@RequestMapping("/{loginUserId}/noteBook")//controller配置访问url
public class NoteBookController { 
	
	@Resource(name="noteBookService")//注入noteBookService
	private NoteBookService noteBookService;
	
	/**
	 * 获得用户特殊笔记本列表
	 * @Title: getSpecialList 
	 * @Time：2014年6月5日 下午2:55:48 YHT
	 * @return User
	 */
	@RequestMapping(value = "/getSpecialList", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getSpecialList(
			@PathVariable("loginUserId") String userId,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			//从笔记本service中获取特殊笔记本列表
			Map<String, Notebook>  map = this.noteBookService.getSpecialList(userId);
			//封装返回信息到response中
			message.setResource(map);
		}catch(Exception e){
			//封装返回的异常信息
			message.setMessage("获得特殊笔记本列表异常！");
			//异常状态
			message.setStatus(0);
			//打印异常
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	/**
	 * 获得用户普通笔记本列表接口
	 * @Title: getNormalList 
	 * @Time：2014年6月5日 下午2:56:41 YHT
	 * @return Response
	 */
	@RequestMapping(value = "getNormalList", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Response getNormalList(
			@PathVariable("loginUserId") String userId,
			HttpServletRequest request, 
			HttpServletResponse response){
		Response message = new Response(); 
		try{
			//从笔记本service中获取普通笔记本列表
			List<Notebook> list = this.noteBookService.getNotebookList(userId,SysEnv.NOTEBOOK_NORMAL);
			//封装返回对象
			message.setResource(list);
		}catch(Exception e){
			message.setMessage("获得普通笔记本列表异常！");
			message.setStatus(0);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	/**
	 * 添加笔记本
	 * @Title: createNoteBook 
	 * @Time：2014年6月5日 下午2:56:56 YHT
	 * @return User
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "createNoteBook", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Response createNoteBook(
			@PathVariable("loginUserId") String userId,
			@RequestBody Notebook notebook,
			HttpServletRequest request,
			HttpServletResponse response){
		Response message = new Response();
		try{
			if(notebook != null){
				notebook.setCnUserId(userId);
				// 创建普通笔记本
				noteBookService.createNoteBook(notebook,SysEnv.NOTEBOOK_NORMAL);
				// set返回信息
				message.setResource(notebook.getCnNotebookId());
			}
		}catch(Exception e){//异常处理
			message.setMessage("添加笔记本异常！");
			message.setStatus(0);
			e.printStackTrace();
		}finally{
			return message;
		}
	}

	/**
	 * 修改笔记本
	 * @Title: updateNoteBook 
	 * @Time：2014年6月5日 下午2:57:13 YHT
	 * @return Response
	 */
	@RequestMapping(value = "updateNoteBook", method = RequestMethod.PUT, headers = "Accept=application/json")
	@ResponseBody
	public Response updateNoteBook(
			@PathVariable("loginUserId") String loginUserId,
			@RequestBody Notebook notebook,
			HttpServletRequest request,
			HttpServletResponse response) {
		Response message = new Response(); 
		try{
			if(notebook != null){//如果notebook不等于空，则更新笔记本
				this.noteBookService.updateNoteBook(notebook);
			}
		}catch(Exception e){//异常处理
			message.setMessage("修改笔记本异常！");
			message.setStatus(0);
			e.printStackTrace();
		}finally{
			return message;
		}
	}
	
	/**
	 * 删除笔记本
	 * @Title: delleteNoteBook
	 * @Time：2014年6月5日 下午2:57:29 YHT
	 * @return Response
	 */
	@RequestMapping(value = "deleteNoteBook/{noteBookId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	@ResponseBody
	public Response deleteNoteBook(
			@PathVariable("loginUserId") String loginUserName,
			@PathVariable("noteBookId") String noteBookId,
			HttpServletRequest request,
			HttpServletResponse response){
		Response message = new Response(); 
		try{//删除笔记本
			this.noteBookService.deleteNoteBook(noteBookId);
		}catch(Exception e){//异常处理
			message.setMessage("删除笔记本异常！");
			message.setStatus(0);
			e.printStackTrace();
		}finally{
			return message;
		}
	}

}
