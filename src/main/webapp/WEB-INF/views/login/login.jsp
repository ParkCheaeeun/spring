<%@page import="kr.or.ddit.user.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/js.cookie.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function(){
    	
    	var userId = Cookies.get("userId");
    	if(userId != undefined){
    		$("#userId").val(userId);
			$("#rememberMe").prop("checked", true);
			$('#pass').focus();
    	}
    	
    	//signin btn 클릭 이벤트 핸들러
    	$('#signinBtn').on('click', function(){
    		/* console.log("signinBtn Click");
    		
    		//remember me check 되어 있는가
    		//체크되어있으면
    		// userId 쿠키를 생성하고 값은 userID input의 값을 쿠키 값으로 설정
    		//체크 안되어있으면
    		// 기존에 사용자가 아이디를 쿠키에 저장하는 기능을 사용하다가 더이상 안하는 경우
    		// 처음부터 아이디 쿠키 저장 기능 사용 안함
    		// ==> userId 쿠키를 삭제
    		if($('#rememberMe').prop('checked')){
    			Cookies.set("userId", $('#userId').val(), {expires : 30});
    		}else{
    			Cookies.remove("userId");
    		}
    		
    		$('#frm').submit(); */
    	});
    	
    })
    	function getCookie(cookieId){
    		var cookies = document.cookie.split('; ');
    		
    		for(var i=0; i<cookies.length; i++){
    			var cookie = cookies[i];
    			var cookieNmVal = cookie.split("=");
    			
    			if(cookieId == cookieNmVal[0]){
    				return cookieNmVal[1];
    			}
    		}
    		return "";
    	}
    	
    	function setCookie(cookieNm, cookieValue, expires){
    		var dt = new Date();
    		dt.setDate(dt.getDate() + Number(expires));
    		
    		document.cookie = cookieNm + "=" + cookieValue + "; path=/; expires="
    						  + dt.toGMTString();
    	}
    	
    	function deleteCookie(cookieName){
    		setCookie(cookieName, "", -1);
    	}
    	
    </script>
  </head>

  <body>
	userId : ${userId }
	
    <div class="container">
    <%
    	HttpSession httpSession = request.getSession();
    		User userVo = (User) httpSession.getAttribute("S_USERVO");
    		String userName = "";
    		userName = userVo == null ? "" : userVo.getUserNm();
    %>
	
      <form id="frm" class="form-signin" action="<%= request.getContextPath()%>/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userId" class="sr-only">User Id</label>
        
        <% 
        	String userId = request.getParameter("userId");
        		
        	userId = userId == null ? "" : userId;
        %>
        
        <input type="text" id="userId" name="userId" class="form-control" 
        	   placeholder="Email address" required autofocus value="">
        <label for="pass" class="sr-only">Password</label>
        <input type="password" id="pass" name="pass" value="brown1234"
        	   class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" name="rememberMe" value="remember-me" id="rememberMe"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" id="signinBtn">Sign in</button>
      </form>
	
    </div> <!-- /container -->

  </body>
</html>
