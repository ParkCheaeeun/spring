package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.User;

@Service
public class UserService implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	//class 명에서 맨 첫글자를 소문자로 변경한 문자열이 스프링 빈 이름으로 기본 설정됨
	// 다른 이름의 스프링 빈이름으로 등록을 하려면 속성 설정
	@Resource(name="userDao")
	private IUserDao userDao;
	
	public UserService() {
		
	}
	
	public UserService(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	* Method : getUserList
	* 작성자 : PC-09
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<User> getUserList() {
		logger.debug("getUserLIst()");
		return userDao.getUserList();
	}
	
	@Override
	public List<User> getUserHalfList() {
		List<User> list = userDao.getUserHalfList();
		return list;
	}

	@Override
	public User getUser(String userId) {
		User user = userDao.getUser(userId);
		return user;
	}

	@Override
	public Map<String, Object> getUserPagingList(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<User> userList = userDao.getUserPagingList(page);
		int totalCnt = userDao.getUserTotalCount();
		
		map.put("userList", userList);
		map.put("paginationSize", (int) Math.ceil((double)totalCnt/page.getPagesize()));
		
		return map;
	}
	
	/**
	* Method : insertUser
	* 작성자 : SEM-PC
	* 변경이력 :
	* @param user
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(User user) {
		// 선언적 트랜잭션
		// .예외가 발생했을 때 정상적으로 rollback 되는지
		// .예외가 발생하지 않고 정상적으로 코드가 실행되었을 때 명시적인  commit은 없는데 commit이 정상적으로 되는지
		
		int insertCnt = userDao.insertUser(user);
		
		return insertCnt;
	}

	/**
	* Method : deleteUser
	* 작성자 : SEM-PC
	* 변경이력 :
	* @param userId
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		int deleteCnt = userDao.deleteUser(userId);
		
		return deleteCnt;
	}

	@Override
	public int updateUser(User user) {
		int updateCnt = userDao.updateUser(user);
		
		return updateCnt;
	}

}
