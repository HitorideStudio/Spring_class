<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>��������</h1>

<c:if test="${check==1 }">
	�����Ϸ�
	<button onclick="location.href='imgList.do'">�������������� ���!</button>
	<button onclick="location.href='uploadForm.do'">���ε� ���!
	</button>
</c:if>
<c:if test="${check!=1}">
	���� �߸��� �� ����....
</c:if>