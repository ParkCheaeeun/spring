package kr.or.ddit.lprod;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;

public class LprodServiceTest extends RootTestConfig {
	
	@Resource(name="lprodServiceImpl")
	ILprodService service;
	
	@Test
	public void getLprodTest() {
		/***Given***/
		
		
		/***When***/
		List<LprodVo> list = service.selectAll();
		
		/***Then***/
		assertEquals(14, list.size());
		
	}
	
	@Test
	public void getLprodPagingListTest() {
		/***Given***/
		Page page = new Page(1, 5);

		/***When***/
		Map<String, Object> map = service.getLprodPagingList(page);
		List<LprodVo> list = (List<LprodVo>) map.get("lprodList");
		int cnt = (int) map.get("totalCnt");
		
		
		/***Then***/
		assertEquals(5, list.size());
		assertEquals(3, cnt);

	}

}
