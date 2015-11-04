package com.tarena.bigdata.cloudnote.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthHandler extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	/**
	 * 设置服务端token判断结果
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object object, ModelAndView mv)
			throws Exception {
	}

	/**
	 * 验证用户请求的token值是否合法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		// 获得用户请求头

		// "Basic DGFSRED%$E%%R^&TDGGFDRES"
		// "DGFSRED%$E%%R^&TDGGFDRES"
		// "username:token"
		String header = request.getHeader("Authorization");
		if (StringUtils.isNotEmpty(header)) {
			String[] headers = header.split(" ");
			if (headers.length == 2) {
				String con[] = new String (Base64.decodeBase64(headers[1].getBytes("utf-8"))).split(":");
//				String[] dec = Base64.decodeBase64(headers[1]).toString()
//						.split(":");
				if (con.length == 2) {
					String token = (String) request.getSession().getAttribute("userToken");
					// 获取用户session中存放的token值
					if (StringUtils.isNotEmpty(token)) {
						// 两个值相等则表示认证通过
						if (con[1].equals(token)) {
							return true;
						}
					}
				}
			}
		}

		response.setStatus(401);
		String result = "{'status':-1,'message':'用户token与服务端不一致！'}";
		response.getWriter().write(result);

		return false;
	}


}
