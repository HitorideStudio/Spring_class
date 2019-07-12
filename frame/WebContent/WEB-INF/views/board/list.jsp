
<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/color2.jsp"%>


<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<%
   
%>
<body bgcolor="${bodyback_c}">
<center><b>글목록(전체 글:${count})</b>
<table width="700">
<tr>
    <td align="right" bgcolor="${value_c}">
    <a href="writeForm.do">글쓰기</a>
    </td>
</table>


<c:choose>
<c:when test="${count==0 }">
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
    <td align="center">
    게시판에 저장된 글이 없습니다.
    </td>
</table>
</c:when>
<c:otherwise>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="${value_c}"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
    
    <c:forEach begin="0" end="${articleList.size()-1}" step="1" var="a">
      <c:set var="article" value="${articleList[a]}"/>
   <tr height="30">
    <td align="center"  width="50" > ${number} <c:set var="number" value="${number-1 }"/> </td>
    <td  width="250" >
    <c:choose>
    <c:when test="${article.re_level >0 }">
       <c:set var="wid" value="${10*article.re_level }"/>
     <img src="/frame/images/level.gif" width="${wid }" height="16">
     <img src="/frame/images/re.gif">
   </c:when>
   <c:otherwise>
   <c:set var="wid" value="${article.re_level }"/>
     <img src="/frame/images/level.gif" width="${wid }" height="16">
   </c:otherwise>
   </c:choose>
      <a href="content.do?num=${article.num }&pageNum=${currentPage}">
          ${article.subject}</a> 
          <c:if test="${article.readcount>=20 }">
         <img src="/frame/images/hot.gif" border="0"  height="16">
         </c:if> </td>
    <td align="center"  width="100"> 
       <a href="mailto:${article.email }">${article.writer }</a></td>
    <td align="center"  width="150">${sdf.format(article.getReg_date()) }</td>
    <td align="center"  width="50">${article.readcount }</td>
    <td align="center" width="100" >${article.ip }</td>
  </tr>
     </c:forEach>
</table>
</c:otherwise>
</c:choose>
<c:if test="${count> 0 }">
   <c:if test="${startPage>10}">
       <a href="list.do?pageNum=${startPage - 10 }">[이전]</a>
   </c:if>
   <c:forEach begin="${startPage}"  end="${endPage}" step="1" var="i">
      <a href="list.do?pageNum=${i }">[${i}]</a>
   </c:forEach>
   <c:if test="${endPage < pageCount }">
       <a href="list.do?pageNum=${startPage + 10  }">[다음]</a>
   </c:if>
</c:if>

</body>
</html>