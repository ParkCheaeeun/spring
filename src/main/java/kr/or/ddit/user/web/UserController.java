package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.model.UserValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.util.model.FileInfo;

@RequestMapping("user/")
@Controller
public class UserController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	* Method : userView
	* 작성자 : PC-09
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 상세화면 요청
	*/
	//사용자가 localhost:8081/spring/user/view
	@RequestMapping("view.do")
	public String userView() {
		logger.debug("userController.userView()");
		
		return "user/view";
		
		//prefix + viewName + suffix
		//WEB-INF/views/user/view.jsp
	}
	
	/**
	* Method : userList
	* 작성자 : PC-09
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@RequestMapping(path = "userList", method = RequestMethod.GET)
	public String userList(Model model) {
		//사용자 정보 전체 조회
		model.addAttribute("userList", userService.getUserList());
		
		return "user/userList";
	}
	
	@RequestMapping(path = "userListOnlyHalf", method = RequestMethod.GET)
	public String userListOnlyHalf(Model model) {
		model.addAttribute("userList", userService.getUserHalfList());
		
		return "user/userListOnlyHalf";
	}
	
	@RequestMapping(path = "userPagingList", method = RequestMethod.GET)
	public String userPagingList(Model model,  @RequestParam(name="page", defaultValue = "1") int p,
											  @RequestParam(defaultValue = "10") int pagesize) {
		
		Page page = new Page(p, pagesize);
		model.addAttribute("pageVo", page);
		
		Map<String, Object> resultMap = userService.getUserPagingList(page);
		
		model.addAllAttributes(resultMap);
		
		return "user/userPagingList";
	}
	
	@RequestMapping(path = "user", method = RequestMethod.GET)
	public String user(Model model, String userId) {
		User user = userService.getUser(userId);
		
		model.addAttribute("user", user);
		
		return "user/user";
	}
	
	@RequestMapping("userPicture")
	public void userPicture(String userId, HttpServletResponse response) throws IOException {
		User user = userService.getUser(userId);
		
		if(user.getRealfilename() == "") return;
		
		ServletOutputStream sos = response.getOutputStream();
		
		File picture = new File(user.getRealfilename());
		FileInputStream fis = new FileInputStream(picture);
		
		byte[] buff = new byte[512];
		int len = 0;
		
		while ((len = fis.read(buff, 0, 512)) != -1) {
			sos.write(buff, 0, len);
		}
		fis.close();
		sos.close();
	}
	
	/**
	* Method : userFormView
	* 작성자 : PC-09
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록 화면
	*/
	@RequestMapping(path = "userForm", method = RequestMethod.GET)
	public String userFormView() {
		return "user/userForm";
	}
	
	//사용자 등록 요청
	@RequestMapping(path = "userForm", method = RequestMethod.POST)
	public String userForm(User user, BindingResult result,@RequestPart("picture") MultipartFile picture) {
		
		new UserValidator().validate(user, result);
		if(result.hasErrors()) {
			return "user/userForm";
		}else {
			FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());
			
			//첨부된 파일이 있을 경우에만 업로드 처리
			if(picture.getSize() > 0) {
				try {
					picture.transferTo(fileInfo.getFile());
					user.setFilename(fileInfo.getOriginalFileName());
					user.setRealfilename(fileInfo.getFile().getPath());
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			int insertCnt = userService.insertUser(user);
			if(insertCnt == 1) 
				return "redirect:/user/user?userId="+user.getUserId();
			else 
				return "user/userForm";
		}
	}
	
	@RequestMapping(path = "modify", method = RequestMethod.GET)
	public String modifyView(Model model, String userId) {
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		
		return "user/modifyForm";
	}
	
	@RequestMapping(path = "modify", method = RequestMethod.POST)
	public String modify(User user, @RequestPart("picture") MultipartFile picture, BindingResult result) {
		
		File file = new File(user.getRealfilename());
		User oldUser = userService.getUser(user.getUserId());
		
		new UserValidator().validate(user, result);
		if(result.hasErrors()) {
			return "user/modifyForm";
		}else {
			
			if(picture.getSize() > 0) {
				FileInfo fileInfo  = FileUtil.getFileInfo(picture.getOriginalFilename());
				try {
					picture.transferTo(fileInfo.getFile());
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				user.setRealfilename(fileInfo.getFile().getPath());
			
			}else {
				user.setRealfilename(oldUser.getRealfilename());
			}
		}
		
		int updateCnt = userService.updateUser(user);
		
		if(updateCnt == 1 ) {
			if(file.exists() && picture != null) {
				logger.debug("file.exists() : {}", file.exists());
				file.delete();
			}
			return "user/user";
		}else {
			return "user/modifyForm";
		}
	}
	
}
