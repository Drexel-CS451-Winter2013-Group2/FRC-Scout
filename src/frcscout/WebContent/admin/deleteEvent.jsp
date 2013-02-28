<%@ include file="../include/header.jsp" %>

<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<jsp:setProperty name="EventBean" property="id" value="${param.id}"/>
<%EventBean.deleteEvent();%> 

<%response.sendRedirect("/frcscout/admin/admin.jsp?tab=2"); %>

<%@ include file="../include/footer.jsp" %>