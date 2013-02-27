<%@ include file="../include/header.jsp" %>

<jsp:useBean id="TeamBean" class="com.frcscout.admin.TeamBean" scope="page"/>
<jsp:setProperty name="TeamBean" property="id" value="${param.id}"/>
<jsp:setProperty name="TeamBean" property="name" value="${param.name}"/>
<jsp:setProperty name="TeamBean" property="location" value="${param.location}"/>
<%TeamBean.updateTeam(request.getParameter("number"));%> 

<%response.sendRedirect("/frcscout/admin/admin.jsp?tab=1"); %>

<%@ include file="../include/footer.jsp" %>