package kr.or.ddit.lprod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVo;

public interface ILprodDao {
	public List<LprodVo> selectAll();
	
	public List<LprodVo> getLprodPagingList(Page page);
	
	public int getLprodTotalCnt();
}
