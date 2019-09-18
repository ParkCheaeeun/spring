<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#pathBtn").on('click', function(){
		$('#frm').attr('action','${cp}/mvc/path/'+$('input[name=path]:checked').val());
		$('#frm').submit();
	})
})
</script>
</head>
<body>
	<h2>mvc/view.jsp</h2>

<h3>spring validator</h3>
<form action="${cp }/mvc/jsr303">
	<input type="text" name="userId" value="brown"><br>
	<input type="text" name="userNm" value="브라운"><br>
	<input type="submit" value="전송"><form:errors path="user.pass"/>
</form>

<h3>spring validator</h3>
<form action="${cp }/mvc/validator">
	<input type="text" name="userId" value="brown"><form:errors path="user.userId"/><br>
	<input type="text" name="userNm" value="브라운"><br>
	<input type="submit" value="전송">
</form>

<h3>redirect Attribute</h3>
<form action="${cp }/mvc/redirect" >
	<input type="text" name="userId" value="brown">
	<input type="submit" value="전송">
</form>
	
<h3>hmm</h3>
<form action="${cp }/mvc/requestParam" method="post">
	userId : <input type="text" name="userId" value="sally"/><br>
	<input type="submit" value="전송"/>
</form>

<h3>path variable</h3>
<form action="" id="frm">
	brown <input type="radio" name="path" value="brown" checked="checked"> <br>
	sally <input type="radio" name="path" value="sally">
	<input type="button" id="pathBtn" value="전송">
</form>

<h3>requestPart</h3>
<form action="${cp }/mvc/upload" id="frmPart" enctype="multipart/form-data" method="post">
	<input type="text" name="userId" value="brown"/> <br>
	<input type="file" name="picture"/><br>
	<input type="submit" value="전송">
</form>

<h3>multi parameter</h3>
<form action="${cp }/mvc/multiParameter" method="post" >
	<input type="text" name="userId" value="brown"><br>
	<input type="text" name="userId" value="sally"><br>
	<input type="text" name="addr.addr1" value="대전시 중구 중앙로 76"><br>
	<input type="text" name="addr.addr2" value="DDIT"><br>
	
	<input type="text" name="addrList[0].addr1" value="대전시 중구 중앙로 76 list1"><br>
	<input type="text" name="addrList[0].addr2" value="DDIT list1"><br>
	
	<input type="text" name="addrList[1].addr1" value="대전시 중구 중앙로 76 list2"><br>
	<input type="text" name="addrList[1].addr2" value="DDIT list2"><br>
	<input type="submit" value="전송">
</form>
</body>
</html>