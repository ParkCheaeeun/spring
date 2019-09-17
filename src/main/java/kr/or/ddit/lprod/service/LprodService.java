package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.LprodVo;

@Service
public class LprodService implements ILprodService {
	
	@Resource(name="lprodDaoImpl")
	private ILprodDao dao;
	
	@Override
	public List<LprodVo> selectAll() {
		List<LprodVo> list = dao.selectAll();
		return list;
	}
	
	@Override
	public Map<String, Object> getLprodPagingList(Page page) {
		List<LprodVo> list = dao.getLprodPagingList(page);
		int cnt = dao.getLprodTotalCnt();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lprodList", list);
		map.put("totalCnt", (int) Math.ceil((double)cnt/page.getPagesize()));
		
		return map;
	}

}
