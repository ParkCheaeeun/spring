package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
									"classpath:kr/or/ddit/config/spring/context-root.xml",
									"classpath:kr/or/ddit/config/spring/context-datasource.xml",
									"classpath:kr/or/ddit/config/spring/context-transaction.xml"})
public class UserDaoTest {
	//userDao를 테스트 하기 위해 필요한거
	//db 연결, 트랜잭션, dao
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	@Test
	public void getUserListTest() {
		/***Given***/
		

		/***When***/
		List<User> userList = userDao.getUserList();

		/***Then***/
		assertTrue(userList.size() > 105);
	}
	
	/**
	* Method : getUserListtest
	* 작성자 : PC-09
	* 변경이력 :
	* Method 설명 : getUserList 테스트
	*/
	@Test
	public void getUserListtest() {
		/***Given***/
		

		/***When***/
		List<User> HalfuserList = userDao.getUserHalfList();
		
		/***Then***/ 
		assertEquals(HalfuserList.size(), 50);
	}
	
	/**
	* Method : getUserTest
	* 작성자 : PC-09
	* 변경이력 :
	* Method 설명 : 사용자 정보 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		User userVo = userDao.getUser(userId);
		
		/***Then***/
		assertEquals("브라운", userVo.getUserNm());
		
	}
	
	@Test
	public void getUserPagingListTest() {
		/***Given***/
		Page page = new Page();
		page.setPage(3);
		page.setPagesize(10);

		/***When***/
		List<User> userList = userDao.getUserPagingList(page);
		
		/***Then***/
		assertEquals(10, userList.size());
		assertEquals("xuserid19", userList.get(0).getUserId());
	}
	
	@Test
	public void getUserTotalCountTest() {
		/***Given***/
		
		/***When***/
		int totalCnt = userDao.getUserTotalCount();
		
		/***Then***/
		assertEquals(110, totalCnt);
	}
	
	@Test
	public void ceilingTest() {
		/***Given***/
		int totalCnt = 105;
		int pagesize = 10;

		/***When***/
		double paginationSize = Math.ceil((double) totalCnt/pagesize);

		/***Then***/
		assertEquals(11, (int)paginationSize);

	}
	
	@Test
	public void userUpdateTest() {
		/***Given***/
		User userVO = new User();
		userVO.setUserId("dddddd");
		userVO.setUserNm("홍길동");
		userVO.setAlias("가나다");
		userVO.setPass("abc");
		
		/***When***/
		int updateCnt = userDao.updateUser(userVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}

}
