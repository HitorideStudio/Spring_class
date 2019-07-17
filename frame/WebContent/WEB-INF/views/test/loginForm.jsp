<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    <form action="loginPro.do" method="post">
    	id:<input type="text" name ="id"/>
    	pw:<input type="password" name="pw"/>
    	<input type="submit" value="로그인"/> 
    </form>
    <button onclick="location.href='form.do'">가입하기</button>