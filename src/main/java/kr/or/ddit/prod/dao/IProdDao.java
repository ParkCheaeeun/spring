package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.model.ProdVo;


public interface IProdDao {
	public List<ProdVo> getProd(String prod_lgu);
}
