<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/color2.jsp"%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>


<body bgcolor="${bodyback_c}">  
<center><b>글내용 보기</b>
<br>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0"  bgcolor="${bodyback_c}" align="center">  
  <tr height="30">
    <td align="center" width="125" bgcolor="${value_c}">글번호</td>
    <td align="center" width="125" align="center">
	     ${article.getNum()}</td>
    <td align="center" width="125" bgcolor="${value_c}">조회수</td>
    <td align="center" width="125" align="center">
	     ${article.getReadcount()}</td>
  </tr>
  <tr height="30">
    <td align="center" width="125" bgcolor="${value_c}">작성자</td>
    <td align="center" width="125" align="center">
	     ${article.getWriter()}</td>
    <td align="center" width="125" bgcolor="${value_c}" >작성일</td>
    <td align="center" width="125" align="center">
	     ${sdf.format(article.getReg_date())}</td>
  </tr>
  <tr height="30">
    <td align="center" width="125" bgcolor="${value_c}">글제목</td>
    <td align="center" width="375" align="center" colspan="3">
	     ${article.getSubject()}</td>
  </tr>
  <tr>
    <td align="center" width="125" bgcolor="${value_c}">글내용</td>
    <td align="left" width="375" colspan="3"><pre>${article.getContent()}<br>
    <img src="/frame/imgs/${newname}" width="150px"/></pre></td>
  </tr>
  <tr height="30">      
    <td colspan="4" bgcolor="${value_c}" align="right" > 
	  <input type="button" value="글수정" 
       onclick="gitcument.location.href='updateForm.git?num=${article.getNum()}&pageNum=${pageNum}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	  <input type="button" value="글삭제" 
       onclick="gitcument.location.href='deleteForm.git?num=${article.getNum()}&pageNum=${pageNum}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="답글쓰기" 
       onclick="gitcument.location.href='writeForm.git?num=${num}&ref=${ref}&re_step=${re_step}&re_level=${re_level}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="글목록" 
       onclick="gitcument.location.href='list.git?pageNum=${pageNum}'">
    </td>
  </tr>
</table>    

</form>      
</body>
</html>      
