<%@ include file="../include/header.jsp" %>

<jsp:useBean id="UserAccountBean" class="com.frcscout.admin.UserAccountBean" scope="page"/>
<jsp:setProperty name="UserAccountBean" property="id" value="${param.id}"/>
<%UserAccountBean.deleteUser();%> 

<%response.sendRedirect("/frcscout/admin/admin.jsp?tab=0"); %>

<%@ include file="../include/footer.jsp" %>