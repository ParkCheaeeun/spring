package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
									"classpath:kr/or/ddit/config/spring/context-root.xml",
									"classpath:kr/or/ddit/config/spring/context-datasource-test.xml",
									"classpath:kr/or/ddit/config/spring/context-transaction.xml"})
public class UserServiceTest extends RootTestConfig{

	@Resource(name="userService")
	private IUserService userService;
	
	
	private String userId = "brownTest";
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	/**
	 * Method : getUserTest 작성자 : PC-09 변경이력 : Method 설명 :사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/*** Given ***/
		String userId = "brown";

		/*** When ***/
		User userVo = userService.getUser(userId);

		/*** Then ***/
		assertEquals("브라운", userVo.getUserNm());
		assertEquals("c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44", userVo.getPass());

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
	      
	      int insertCnt = userService.insertUser(user);
	      
	      /***Then***/
	      assertEquals(1, insertCnt);
	   }
	
	
	@Test
	public void getUserListtest() {
		/*** Given ***/

		/*** When ***/
		List<User> userList = userService.getUserList();
		List<User> HalfuserList = userService.getUserHalfList();

		/*** Then ***/
		assertEquals(108, userList.size());
		assertEquals(50, HalfuserList.size());
	}
	
	@Test
	public void updateUserTest() {
		/***Given***/
		User userVO = new User();
		userVO.setUserId("xuserid1");
		userVO.setUserNm("테스트");
		userVO.setAlias("테스트별명");
		userVO.setAddr1("대흥동");
		userVO.setAddr2("영민빌딩");
		userVO.setZipcode("34340");

		/***When***/
		int updateCnt = userService.updateUser(userVO);

		/***Then***/
		assertEquals(updateCnt, 1);
	}
	
	@Test
	public void getUserList() {
		/***Given***/
		

		/***When***/
		List<User> userList = userService.getUserList();

		/***Then***/
		assertTrue(userList.size() >= 105);
		
	}
	
	@Test
	public void getUserPagingListTest() {
		/***Given***/
		Page page = new Page();
		page.setPage(3);
		page.setPagesize(10);

		/***When***/
		Map<String, Object> resultMap = userService.getUserPagingList(page);
		List<User> userList = (List<User>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		/***Then***/
		assertEquals(10, userList.size());
		assertEquals("xuserid22", userList.get(0).getUserId());
		assertEquals(11, paginationSize);
	}

}
