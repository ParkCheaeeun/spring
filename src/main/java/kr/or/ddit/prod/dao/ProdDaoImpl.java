package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.prod.model.ProdVo;

@Repository
public class ProdDaoImpl implements IProdDao {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate session;
	
	@Override
	public List<ProdVo> getProd(String prod_lgu) {
		List<ProdVo> list = session.selectList("prod.getProd", prod_lgu);
		return list;
	}

}
