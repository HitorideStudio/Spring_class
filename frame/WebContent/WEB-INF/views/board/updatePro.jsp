<%@ page contentType="text/html;charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/color2.jsp"%>



<c:if test="${check==1 }">
	  <meta http-equiv="Refresh" content="0;url=list.do?pageNum=${pageNum}" />
</c:if>
<c:if test="${check!=1 }">
      <script>            
        alert("��й�ȣ�� ���� �ʽ��ϴ�");
        history.go(-1);
     </script>
</c:if>
 