package kr.or.ddit.prod;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.model.ProdVo;


public class ProdDaoTest extends RootTestConfig {
	
	@Resource(name="prodDaoImpl")
	private IProdDao dao;
	
	@Test
	public void prodDaoTest() {
		
		List<ProdVo> list = dao.getProd("P202");
		assertEquals(42, list.size());
		
	}

}
