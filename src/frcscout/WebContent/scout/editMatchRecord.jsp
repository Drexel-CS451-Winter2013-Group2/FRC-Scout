<%@ include file="../include/header.jsp" %>
<jsp:useBean id="MatchRecordBean" class="com.frcscout.scout.MatchRecordBean" scope="page"/>
<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<jsp:useBean id="TeamBean" class="com.frcscout.admin.TeamBean" scope="page"/>
<%MatchRecordBean.loadMatchRecord(request.getParameter("id"));%>
<script> 
    var eventJSON = <%=EventBean.loadEvents() %>;
    var teamJSON = <%=TeamBean.loadTeams() %>;
    var id = <%=request.getParameter("id")%>;
    var teamId = <%=MatchRecordBean.getTeamId()%>;
    var eventId = <%=MatchRecordBean.getEventId()%>;
    var matchId = <%=MatchRecordBean.getMatchId()%>;
    var color = "<%=MatchRecordBean.getColor()%>";
    var autonTop = <%=MatchRecordBean.getAutonTop()%>;
    var autonMiddle = <%=MatchRecordBean.getAutonMiddle()%>;
    var autonBottom = <%=MatchRecordBean.getAutonBottom()%>;
    var teleopTop = <%=MatchRecordBean.getTeleopTop()%>;
    var teleopMiddle = <%=MatchRecordBean.getTeleopMiddle()%>;
    var teleopBottom = <%=MatchRecordBean.getTeleopBottom()%>;
    var teleopPyramid = <%=MatchRecordBean.getTeleopPyramid()%>;
    var pyramidLevel = <%=MatchRecordBean.getPyramidLevel()%>;
    var playStyle = "<%=MatchRecordBean.getPlayStyle()%>";
    var confidence = <%=MatchRecordBean.getConfidence()%>;
    var ability = <%=MatchRecordBean.getAbility()%>;
    var fouls = <%=MatchRecordBean.getFouls()%>;
    var technicalFouls = <%=MatchRecordBean.getTechnicalFouls()%>;
    var comments = "<%=MatchRecordBean.getComments()%>";
    var path = "<%=MatchRecordBean.getPath()%>";
</script>
<script src="/frcscout/js/scout/editMatchRecord.js"></script> 

<%@ include file="../include/footer.jsp" %>