<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/color2.jsp"%>
<html>
<head><title>로그인</title>
<link href="style.css" rel="stylesheet" type="text/css">

   <script language="javascript">
  
   </script>
</head>
<BODY onload="begin()" bgcolor="${bodyback_c}">
<form name="myform" action="/frame/member/loginPro.do" method="post" onSubmit="return checkIt()">
<TABLE cellSpacing=1 cellPadding=1 width="260" border=1 align="center" >
  
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="${title_c}"><STRONG>회원로그인</STRONG></TD></TR>
  
  <TR height="30">
    <TD width="110" bgcolor="${title_c}" align=center>아이디</TD>
    <TD width="150" bgcolor="${value_c}" align=center>
       <INPUT type="text" name="id" size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD width="110" bgcolor="${title_c}" align=center>비밀번호</TD>
    <TD width="150" bgcolor="${value_c}" align=center>
      <INPUT type=password name="passwd"  size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="${title_c}" >
      <INPUT type=submit value="로그인"> 
      <INPUT type=reset value="다시입력">
      <input type="button" value="회원가입" onclick="javascript:window.location='inputForm.do'"></TD></TR>
</TABLE>
</form>

</BODY>
</HTML>