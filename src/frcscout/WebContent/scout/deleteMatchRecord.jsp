<%@ include file="../include/header.jsp" %>

<jsp:useBean id="MatchRecordBean" class="com.frcscout.scout.MatchRecordBean" scope="page"/>
<jsp:setProperty name="MatchRecordBean" property="id" value="${param.id}"/>
<%MatchRecordBean.deleteMatchRecord();%> 

<%response.sendRedirect("/frcscout/scout/scout.jsp"); %>

<%@ include file="../include/footer.jsp" %>