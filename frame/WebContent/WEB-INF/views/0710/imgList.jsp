<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach begin="0" end="${list.size()-1 }" step="1" var="i">
	<c:set var="vo" value="${list[i] }"/>
	<img src="/frame/imgs/${vo.newname}" width="50px"/>${vo.writer} 
	<button onclick="window.location='download.do?newname=${vo.newname}'">�ٿ�ε�</button>
    <button onclick="window.location='delete.do?newname=${vo.newname}'">����</button>
	<c:if test="${i !=0 }">
	<c:if test="${i % 5 == 4 }">
	
		<br>
	</c:if>
	</c:if>
</c:forEach>
