<%@ include file="../include/header.jsp" %>

<jsp:useBean id="MatchRecordBean" class="com.frcscout.scout.MatchRecordBean" scope="page"/>
<jsp:setProperty name="MatchRecordBean" property="id" value="${param.id}"/>
<jsp:setProperty name="MatchRecordBean" property="user" value="<%=request.getRemoteUser()%>"/>
<jsp:setProperty name="MatchRecordBean" property="teamId" value="${param.teamId}"/>
<jsp:setProperty name="MatchRecordBean" property="eventId" value="${param.eventId}"/>
<jsp:setProperty name="MatchRecordBean" property="matchId" value="${param.matchId}"/>
<jsp:setProperty name="MatchRecordBean" property="color" value="${param.color}"/>
<jsp:setProperty name="MatchRecordBean" property="autonTop" value="${param.autonTop}"/>
<jsp:setProperty name="MatchRecordBean" property="autonMiddle" value="${param.autonMiddle}"/>
<jsp:setProperty name="MatchRecordBean" property="autonBottom" value="${param.autonBottom}"/>
<jsp:setProperty name="MatchRecordBean" property="teleopTop" value="${param.teleopTop}"/>
<jsp:setProperty name="MatchRecordBean" property="teleopMiddle" value="${param.teleopMiddle}"/>
<jsp:setProperty name="MatchRecordBean" property="teleopBottom" value="${param.teleopBottom}"/>
<jsp:setProperty name="MatchRecordBean" property="teleopPyramid" value="${param.teleopPyramid}"/>
<jsp:setProperty name="MatchRecordBean" property="pyramidLevel" value="${param.pyramidLevel}"/>
<jsp:setProperty name="MatchRecordBean" property="playStyle" value="${param.playStyle}"/>
<jsp:setProperty name="MatchRecordBean" property="confidence" value="${param.confidence}"/>
<jsp:setProperty name="MatchRecordBean" property="ability" value="${param.ability}"/>
<jsp:setProperty name="MatchRecordBean" property="fouls" value="${param.fouls}"/>
<jsp:setProperty name="MatchRecordBean" property="technicalFouls" value="${param.technicalFouls}"/>
<jsp:setProperty name="MatchRecordBean" property="comments" value="${param.comments}"/>
<jsp:setProperty name="MatchRecordBean" property="path" value="${param.path}"/>
<%MatchRecordBean.updateMatchRecord();%> 

<%response.sendRedirect("/frcscout/scout/scout.jsp"); %>

<%@ include file="../include/footer.jsp" %>