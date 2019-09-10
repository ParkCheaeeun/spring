package kr.or.ddit.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.User;

@Repository
public class UserDao implements IUserDao{

	/**
	* Method : getUserList
	* 작성자 : PC-09
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		userList.add(new User("brown"));
		userList.add(new User("cony"));
		userList.add(new User("sally"));
		
		return userList;
	}

}
