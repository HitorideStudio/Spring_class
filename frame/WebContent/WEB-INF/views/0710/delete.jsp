<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>사진삭제</h1>

<c:if test="${check==1 }">
	삭제완료
	<button onclick="location.href='imgList.do'">사진보관함으로 고고!</button>
	<button onclick="location.href='uploadForm.do'">업로드 고고!
	</button>
</c:if>
<c:if test="${check!=1}">
	뭔가 잘못된 것 같다....
</c:if>