<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/color2.jsp"%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<body bgcolor="${bodyback_c}">  
<center><b>글수정</b>
<br>
<form method="post" enctype="multipart/form-data" name="writeform" action="updatePro.do?pageNum=${pageNum}&num=${num}" onsubmit="return writeSave()">
<table width="400" border="1" cellspacing="0" cellpadding="0"  bgcolor="${bodyback_c}" align="center">
  <tr>
    <td  width="70"  bgcolor="${value_c}" align="center">이 름</td>
    <td align="left" width="330">
       <input type="text" size="10" maxlength="10" name="writer" value="${article.getWriter()}">
	   <input type="hidden" name="num" value="${article.getNum()}"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="${value_c}" align="center" >제 목</td>
    <td align="left" width="330">
       <input type="text" size="40" maxlength="50" name="subject" value="${article.getSubject()}"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="${value_c}" align="center">Email</td>
    <td align="left" width="330">
       <input type="text" size="40" maxlength="30" name="email" value="${article.getEmail()}"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="${value_c}" align="center" >내 용</td>
    <td align="left" width="330">
     <textarea name="content" rows="13" cols="40">${article.getContent()}</textarea>
     <img src="/frame/imgs/${article.getNewname()}" width="150px"/></td>
  </tr>
  <tr>
  	<td  width="70"  bgcolor="${value_c}" align="center" >사진업로드</td>
  	<td  width="330" >
    <input type="file" name="save"/></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="${value_c}" align="center" >비밀번호</td>
    <td align="left" width="330" >
     <input type="password" size="8" maxlength="12" name="passwd">
     
	 </td>
  </tr>
  <tr>      
   <td colspan=2 bgcolor="${value_c}" align="center"> 
     <input type="submit" value="글수정" >  
     <input type="reset" value="다시작성">
     <input type="button" value="목록보기" 
       onclick="document.location.href='list.do?pageNum=${pageNum}'">
   </td>
 </tr>
 </table>
</form>
    
</body>
</html>      