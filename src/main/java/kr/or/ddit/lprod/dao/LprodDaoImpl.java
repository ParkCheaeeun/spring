package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVo;

@Repository
public class LprodDaoImpl implements ILprodDao {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate session;
	
	@Override
	public List<LprodVo> selectAll() {
		return session.selectList("lprod.selectAll");
	}

	@Override
	public List<LprodVo> getLprodPagingList(Page page) {
		return session.selectList("lprod.getLprodPagingList", page);
	}

	@Override
	public int getLprodTotalCnt() {
		return session.selectOne("lprod.getLprodTotalCnt");
	}

	

}
