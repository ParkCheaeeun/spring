package kr.or.ddit.lprod.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;

public class LprodControllerTest extends WebTestConfig {

	
	@Test
	public void lprodListtest() throws Exception {
		/***Given***/
		

		/***When***/
		mockMvc.perform(get("/lprodList"))
		.andExpect(model().attributeExists("lprodList"))
		.andExpect(view().name("lprod/lprodList"));

		/***Then***/
	}
	
	@Test
	public void lprodPagingList() throws Exception {
		/***Given***/
		

		/***When***/
		mockMvc.perform(get("/lprodPagingList"))
		.andExpect(model().attributeExists("pageVo"))
		.andExpect(view().name("lprod/lprodPagingList"));

		/***Then***/
	}

}
