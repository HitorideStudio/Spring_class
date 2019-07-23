<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="t" value="<%=new java.util.Date() %>"/>

<ftm:formatDate value="${t}" type="both"/>
