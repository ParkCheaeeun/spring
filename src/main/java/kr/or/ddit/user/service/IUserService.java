package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;

public interface IUserService {
	
	/**
	* Method : getUserList
	* 작성자 : PC-09
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	List<User> getUserList();
	
	/**
	* Method : getUser
	* 작성자 : PC-09
	* 변경이력 :
	 * @param sqlSession 
	* @param userId
	* @return
	* Method 설명 : 사용자 상세 조회
	*/
	User getUser(String userId);
	
	/**
	* Method : getUserHalfList
	* 작성자 : PC-09
	* 변경이력 :
	 * @param sqlSession 
	* @return
	* Method 설명 : 
	*/
	List<User> getUserHalfList();
	
	
	/**
	* Method : getUserPagingList
	* 작성자 : PC-09
	* 변경이력 :
	* @param sqlSession
	* @param page
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	Map<String, Object> getUserPagingList(Page page);
	
	
	/**
	* Method : insertUser
	* 작성자 : SEM-PC
	* 변경이력 :
	* @param sqlSession
	* @param user
	* @return
	* Method 설명 : 사용자 등록
	*/
	int insertUser(User user);
	
	/**
	* Method : deleteUser
	* 작성자 : SEM-PC
	* 변경이력 :
	* @param sqlSession
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	int deleteUser(String userId);
	
	int updateUser(User user);

}