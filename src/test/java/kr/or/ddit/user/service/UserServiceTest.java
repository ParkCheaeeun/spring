package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import kr.or.ddit.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
									"classpath:kr/or/ddit/config/spring/context-root.xml",
									"classpath:kr/or/ddit/config/spring/context-datasource.xml",
									"classpath:kr/or/ddit/config/spring/context-transaction.xml"})
public class UserServiceTest {

	@Resource(name="userService")
	private IUserService userService;
	
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
	public void getUserListtest() {
		/*** Given ***/

		/*** When ***/
		List<User> userList = userService.getUserList();
		List<User> HalfuserList = userService.getUserHalfList();

		/*** Then ***/
		assertEquals(113, userList.size());
		assertEquals(50, HalfuserList.size());
	}
	
	@Test
	public void updateUserTest() {
		/***Given***/
		User userVO = new User();
		userVO.setUserId("dsssa");
		userVO.setUserNm("홍길동");
		userVO.setAlias("가나다");
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
		assertEquals("xuserid19", userList.get(0).getUserId());
		assertEquals(11, paginationSize);
	}

}
