<%@ include file="../include/header.jsp" %>

<jsp:useBean id="UserAccountBean" class="com.frcscout.admin.UserAccountBean" scope="page"/>
<jsp:setProperty name="UserAccountBean" property="email" value="${param.email}"/>
<jsp:setProperty name="UserAccountBean" property="password" value="${param.password}"/>
<jsp:setProperty name="UserAccountBean" property="firstName" value="${param.firstName}"/>
<jsp:setProperty name="UserAccountBean" property="lastName" value="${param.lastName}"/>
<jsp:setProperty name="UserAccountBean" property="role" value="${param.role}"/>
<%UserAccountBean.insertUser();%> 

<%response.sendRedirect("/frcscout/admin/admin.jsp?tab=0"); %>

<%@ include file="../include/footer.jsp" %>