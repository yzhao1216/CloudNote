package com.tarena.bigdata.cloudnote.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tarena.bigdata.cloudnote.entity.Response;
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-mvc.xml","classpath*:applicationContext.xml" })
@ActiveProfiles("test")
public class NoteControllerTest extends AbstractTransactionalJUnit4SpringContextTests{

	private MockMvc mockMvc;
	
	@Resource
	private NoteController noteController;
	
	@Before
    public void setUp(){
    	mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }
	
	/**
	 * 获得普通笔记本下的笔记列表
	 * @throws Exception
	 */
	@Test
	public void getNormalNoteListTest() throws Exception{
		MvcResult andReturn = mockMvc.perform(get("/{loginUserId}/note/getNormalNoteList/{noteBookId}", "39295a3d-cc9b-42b4-b206-a2e7fab7e77c","6d763ac9-dca3-42d7-a2a7-a08053095c08")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)//设置请求
				)//发送请求
				.andExpect(status().isOk())//添加断言，相应200
				//其他断言 。。。
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		Assert.assertNotNull(andReturn);//非空断言
		
		String json = new String(andReturn.getResponse().getContentAsByteArray());
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = objectMapper.readValue(json, Response.class);
		Assert.assertEquals(1, response.getStatus());//判断返回状态，项目中返回1则说明返回状态成功
		//其他断言
		System.out.println("andReturn =="+andReturn.getResponse().getContentAsString());
		System.out.println("getNormalNoteListTest ok");
	}
	
	/**
	 * 创建笔记
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void createNormalNoteTest()throws Exception{
		MvcResult andReturn = mockMvc.perform(
				post("/{loginUserId}/note/createNormalNote/{noteBookId}","39295a3d-cc9b-42b4-b206-a2e7fab7e77c","6d763ac9-dca3-42d7-a2a7-a08053095c08")//模拟请求
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)//设置请求
				.content("{\"cnNoteTitle\":\"junit\",\"cnNoteBody\":\"撒打发斯蒂芬\"}")//json内容
				.characterEncoding("utf-8")
				)//发送请求
				.andExpect(status().isOk())//添加断言，相应200
				//其他断言。。。
		        .andDo(MockMvcResultHandlers.print())//打印返回结果
		        .andReturn();
		Assert.assertNotNull(andReturn);//非空断言
		
		String json = new String(andReturn.getResponse().getContentAsByteArray());
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = objectMapper.readValue(json, Response.class);
		Assert.assertEquals(1, response.getStatus());//判断返回状态，项目中返回1则说明返回状态成功
		//其他断言
		System.out.println("andReturn =="+andReturn.getResponse().getContentAsString());
		System.out.println("createNormalNoteTest ok");
	}
	
	
	
	
	
	/**
	 * 更新
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void updateNormalNoteTest()throws Exception{
		MvcResult andReturn = mockMvc.perform(
				put("/{loginUserId}/note/updateNormalNote/{noteId}","39295a3d-cc9b-42b4-b206-a2e7fab7e77c","8d3763b2-8e01-48d4-a338-730b02ded9c9")//模拟请求
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)//设置请求
				.content("{\"cnNoteTitle\":\"junit11111\",\"cnNoteBody\":\"12313123123123\"}")//json内容
				.characterEncoding("utf-8")
				)//发送请求
				.andExpect(status().isOk())//添加断言，相应200
		        .andDo(MockMvcResultHandlers.print())//打印返回结果
		        .andReturn();
		
		
		Assert.assertNotNull(andReturn);//非空断言
		
		String json = new String(andReturn.getResponse().getContentAsByteArray());
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = objectMapper.readValue(json, Response.class);
		Assert.assertEquals(1, response.getStatus());//判断返回状态，项目中返回1则说明返回状态成功
		//其他断言
		System.out.println("andReturn =="+andReturn.getResponse().getContentAsString());
		System.out.println("updateNormalNoteTest ok");
	}
	
	
	
	/**
	 * 删除笔记
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void deleteNormalTest()throws Exception{
		MvcResult andReturn = mockMvc.perform(
				delete("/{loginUserId}/note/deleteNormalNote/{noteId}","39295a3d-cc9b-42b4-b206-a2e7fab7e77c","8d3763b2-8e01-48d4-a338-730b02ded9c9")//模拟请求
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)//设置请求
				.content("{\"cnNoteTitle\":\"junit11111\",\"cnNoteBody\":\"12313123123123\"}")//json内容
				.characterEncoding("utf-8")
				)//发送请求
				.andExpect(status().isOk())//添加断言，相应200
		        .andDo(MockMvcResultHandlers.print())//打印返回结果
		        .andReturn();
		
		
		Assert.assertNotNull(andReturn);//非空断言
		
		String json = new String(andReturn.getResponse().getContentAsByteArray());
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = objectMapper.readValue(json, Response.class);
		Assert.assertEquals(1, response.getStatus());//判断返回状态，项目中返回1则说明返回状态成功\
		//其他断言
		System.out.println("andReturn =="+andReturn.getResponse().getContentAsString());
		System.out.println("deleteNormalTest ok");
	}
	
	
	
}
