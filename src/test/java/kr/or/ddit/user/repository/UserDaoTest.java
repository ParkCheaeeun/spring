package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
									"classpath:kr/or/ddit/config/spring/context-root.xml",
									"classpath:kr/or/ddit/config/spring/context-datasource-test.xml",
									"classpath:kr/or/ddit/config/spring/context-transaction.xml"})
public class UserDaoTest extends RootTestConfig {
	//userDao를 테스트 하기 위해 필요한거
	//db 연결, 트랜잭션, dao
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	private String userId = "brownTest";

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
		assertEquals("xuserid22", userList.get(0).getUserId());
	}
	
	@Test
	public void getUserTotalCountTest() {
		/***Given***/
		
		/***When***/
		int totalCnt = userDao.getUserTotalCount();
		
		/***Then***/
		assertEquals(105, totalCnt);
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
	   public void insertUserTest() {
	      /***Given***/
	      User user = new User();
	      user.setUserId(userId);
	      user.setUserNm("브라운테스트");
	      user.setAlias("곰테스트");
	      user.setPass("brownTest1234");
	      user.setAddr1("대전광역시 중구 중앙로 중앙로 76");
	      user.setAddr2("영민빌딩 2층 DDIT");
	      user.setZipcode("34940");
	         try {
				user.setReg_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-08"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	      
	      /***When***/
	      
	      int insertCnt = userDao.insertUser(user);
	      
	      /***Then***/
	      assertEquals(1, insertCnt);
	   }
	
	@Test
	public void userUpdateTest() {
		/***Given***/
		User userVO = new User();
		userVO.setUserId("xuserid1");
		userVO.setUserNm("테스트");
		userVO.setAlias("테스트별명");
		userVO.setAddr1("대흥동");
		userVO.setAddr2("영민빌딩");
		userVO.setZipcode("34340");
		
		/***When***/
		int updateCnt = userDao.updateUser(userVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}

}
