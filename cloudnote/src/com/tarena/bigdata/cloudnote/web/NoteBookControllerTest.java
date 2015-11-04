package com.tarena.bigdata.cloudnote.web;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import com.tarena.bigdata.cloudnote.service.NoteBookService;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-mvc.xml","classpath*:applicationContext.xml"})
@ActiveProfiles("test")
@WebAppConfiguration
public class NoteBookControllerTest extends AbstractTransactionalJUnit4SpringContextTests{
	

	
	


}
