package kr.or.ddit.login.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.config.test.WebTestConfig;

public class LoginControllerTest extends WebTestConfig{
	
	
	
	/**
	* Method : loginViewTest
	* 작성자 : PC-09
	* 변경이력 :
	* Method 설명 : 로그인 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void loginViewTest() throws Exception {
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("login/login", mav.getViewName());
	}
	
	/**
	* Method : loginViewTest
	* 작성자 : PC-09
	* 변경이력 :
	* Method 설명 : 로그인 요청처리 테스트
	 * @throws Exception 
	*/
	@Test
	public void loginProcessTest() throws Exception {
		/***Given***/
		MockHttpSession session = new MockHttpSession();
		
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId", "sally").param("pass", "brown1234")
				.session(session))
				.andExpect(status().isOk())
				.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("main", mav.getViewName());
	}
	

}
