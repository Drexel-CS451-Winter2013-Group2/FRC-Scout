<%@ include file="../include/header.jsp" %>

<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<jsp:setProperty name="EventBean" property="id" value="${param.id}"/>
<jsp:setProperty name="EventBean" property="name" value="${param.name}"/>
<jsp:setProperty name="EventBean" property="location" value="${param.location}"/>
<jsp:setProperty name="EventBean" property="startDate" value="${param.startDate}"/>
<jsp:setProperty name="EventBean" property="endDate" value="${param.endDate}"/>
<%EventBean.updateEvent();%> 

<%response.sendRedirect("/frcscout/admin/admin.jsp?tab=2"); %>

<%@ include file="../include/footer.jsp" %>