package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;

@Repository
public class UserDao implements IUserDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	/**
	* Method : getUserList
	* 작성자 : PC-09
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<User> getUserList() {
		List<User> userList = sqlSession.selectList("user.getUserList");
		
		userList.add(new User("brown"));
		userList.add(new User("cony"));
		userList.add(new User("sally"));
		
		return userList;
	}
	
	/**
	* Method : getUser
	* 작성자 : PC-09
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : userId를 갖는 사용자 조회
	*/
	@Override
	public User getUser(String userId) {
		User userVo = sqlSession.selectOne("user.getUser", userId);
		
		return userVo;
	}

	@Override
	public List<User> getUserHalfList() {
		List<User> list = sqlSession.selectList("user.getUserListOnlyHalf");
		return list;
	}

	@Override
	public List<User> getUserPagingList(Page page) {
		List<User> list = sqlSession.selectList("user.getUserPagingList", page);
		return list;
	}
	

	/**
	* Method : getUserTotalCount
	* 작성자 : PC-09
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 전체 사용자 건수 조회
	*/
	public int getUserTotalCount() {
		return sqlSession.selectOne("user.getUserTotalCnt");
	}
	
	/**
	* Method : insertUser
	* 작성자 : SEM-PC
	* 변경이력 :
	* @param sqlSession
	* @param user
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(User user) {
		return sqlSession.insert("user.insertUser", user);
	}

	/**
	* Method : deleteUser
	* 작성자 : SEM-PC
	* 변경이력 :
	* @param sqlSession
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		return sqlSession.delete("user.deleteUser", userId);
	}

	@Override
	public int updateUser(User user) {
		return sqlSession.update("user.updateUser", user);
	}

}
