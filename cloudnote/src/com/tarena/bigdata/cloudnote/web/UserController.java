package com.tarena.bigdata.cloudnote.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.bigdata.cloudnote.entity.Response;
import com.tarena.bigdata.cloudnote.entity.User;
import com.tarena.bigdata.cloudnote.service.NoteBookService;
import com.tarena.bigdata.cloudnote.service.UserService;
import com.tarena.bigdata.cloudnote.util.Md5Utils;
import com.tarena.bigdata.cloudnote.util.UUIDUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name="noteBookService")
	private NoteBookService noteBookService;
	
	/**
	 * 用户登陆验证
	 * @Title: login 
	 * @Time：2014年6月11日 上午10:50:53 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Response login(HttpServletRequest request, HttpServletResponse response) {
		Response message = new Response();
		message.setStatus(-2);// 用户名或密码错误
		message.setMessage("用户名或密码错误！");
		
		String auth = request.getHeader("Authorization");
//		"Basic DGFSRED%$E%%R^&TDGGFDRES"
//		"DGFSRED%$E%%R^&TDGGFDRES"
//		"username:password"
		User loginUser = new User();
		try{
			if(StringUtils.isNotEmpty(auth)){
				String auths[] = auth.split(" ");
				if(auths.length == 2){
					String dec[] = new String (Base64.decodeBase64(auths[1].getBytes("utf-8"))).split(":");
					logger.debug("解码用户名密码["+dec+"]");
					if(dec.length == 2){
						loginUser = userService.getUserByLoginNamePsd(dec[0], dec[1]);
						if(loginUser != null){
							message.setStatus(1);
							message.setResource(loginUser);
							message.setMessage("用户验证成功！");
						}
					}
				}
			}
			
			// 设置用户请求token
			String tk = UUIDUtil.getUId();
			request.getSession().setAttribute("userToken", tk);
			
			// 设置返回到客户端的header的token值
			response.setHeader("Authorization", "Token "+tk);
		}catch(Exception e){
			e.printStackTrace();
			message.setMessage("用户登录失败！");
			message.setStatus(-1);
		}
		return message;
	}

	/**
	 * 注册
	 * @Title: createUser 
	 * @Time：2014年6月11日 上午10:50:27 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.POST,headers = "Accept=application/json")
	@ResponseBody
	public Response createUser(
			@RequestBody User user,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response();
		try{
			if(user != null){
				userService.createUser(user);
				message.setResource(user);
				
				// 用户注册成功后初始化用户的特殊笔记本
				//（正常：normal，收藏：favorites，回收站：recycle，活动:action，推送:push）
				this.noteBookService.initNotebooks(user.getCnUserId());
			}
		}catch(Exception e){
			e.printStackTrace();
			message.setMessage("用户登录失败！");
			message.setStatus(-1);
		}
		return message;
	}
	
	/**
	 * 检查用户名
	 * @Title: checkUserName 
	 * @Time：2014年6月11日 上午10:49:52 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/checkUserName/{userName}", method = RequestMethod.POST,headers = "Accept=application/json")
	@ResponseBody
	public Response checkUserName(
			@PathVariable String userName,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response();
		try{
			// 判断用户是否存在
			if(StringUtils.isNotEmpty(userName)){
				User user = userService.findUserByLoginName(userName);
				if(user == null){
					message.setMessage("应户名可用");
				}else{
					message.setStatus(-2);
					message.setMessage("应户名已占用！");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message.setMessage("验证用户名异常！");
			message.setStatus(-1);
		}
		return message;
	}
	
	
	
	/**
	 * 验证用户密码
	 * @Title: checkUserOldpwd 
	 * @Time：2014年6月11日 上午10:49:52 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/checkUserOldpwd/{userName}/{oldPwd}", method = RequestMethod.POST,headers = "Accept=application/json")
	@ResponseBody
	public Response checkUserOldpwd(
			@PathVariable String userName,
			@PathVariable String oldPwd,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response();
		try{
			// 判断用户是否存在
			if(StringUtils.isNotEmpty(oldPwd)){
				User user = userService.findUserByLoginName(userName);
				if(user != null && Md5Utils.md5(oldPwd).equals(user.getCnUserPassword())){
					message.setStatus(1);
					message.setMessage("密码正确");
				}else{
					message.setStatus(-2);
					message.setMessage("密码错误！");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message.setMessage("用户密码验证异常！");
			message.setStatus(-1);
		}
		return message;
	}
	
	
	
	
	
	/**
	 * 修改密码
	 * @Title: resetPwd 
	 * @Time：2014年6月11日 上午10:49:38 YHT
	 * @return Response
	 */
	@RequestMapping(value = "/resetPwd/{userId}/{pwd}", method = RequestMethod.POST,headers = "Accept=application/json")
	@ResponseBody
	public Response resetPwd(
			@PathVariable String userId,
			@PathVariable String pwd,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Response message = new Response();
		try{
			// 判断用户是否存在
			if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(pwd)){
				userService.resetPwd(userId, pwd);
			}
		}catch(Exception e){
			e.printStackTrace();
			message.setMessage("修改密码操作异常！");
			message.setStatus(-1);
		}
		return message;
	}
}
