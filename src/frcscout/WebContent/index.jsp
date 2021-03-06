<%@ include file="../include/header.jsp" %>
<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<jsp:useBean id="TeamBean" class="com.frcscout.admin.TeamBean" scope="page"/>
<jsp:useBean id="GroupByEventBean" class="com.frcscout.teammember.GroupByEventBean" scope="session"/>
<jsp:useBean id="GroupByTeamBean" class="com.frcscout.teammember.GroupByTeamBean" scope="session"/>
<jsp:setProperty name="GroupByEventBean" property="selectedEvent" value="${param.event}"/>
<jsp:setProperty name="GroupByEventBean" property="selectedTeam" value="${param.team}"/>
<jsp:setProperty name="GroupByEventBean" property="selectedMatch" value="${param.match}"/>

<jsp:setProperty name="GroupByTeamBean" property="selectedTeam" value="${param.teamteam}"/>

<script> 
    var selectedEvent = <%=GroupByEventBean.getSelectedEvent() %>;
    var selectedMatch = <%=GroupByEventBean.getSelectedMatch() %>;
    var selectedTeam = <%=GroupByEventBean.getSelectedTeam() %>;
    var eventJSON = <%=EventBean.loadEvents() %>;
    var teamJSON = <%=TeamBean.loadTeams() %>;
    var matchJSON = <%=GroupByEventBean.loadMatches() %>;
    var overviewChartJSON = <%=GroupByEventBean.getOverviewChart() %>;
    var overviewTableJSON = <%=GroupByEventBean.getOverviewTable() %>;
    var redTeamTableJSON = <%=GroupByEventBean.getRedTeamTable() %>;
    var blueTeamTableJSON = <%=GroupByEventBean.getBlueTeamTable() %>;
    var teamDataJSON = <%=GroupByEventBean.getTeamData() %>;
    var teamPieChartJSON = <%=GroupByEventBean.getTeamPieChart() %>;
    
    var selectedTeamTeam = <%=GroupByTeamBean.getSelectedTeam() %>;
    var teamAveragesJSON = <%=GroupByTeamBean.getEventAveragesTable() %>;
    var teamChartJSON = <%=GroupByTeamBean.getEventsLineChart() %>;
    var teamRadarChartJSON = <%=GroupByTeamBean.getTeamRadarChart() %>;
    var teamMatchJSON = <%=GroupByTeamBean.getTeamMatchTable() %>;

</script>

<script src="/frcscout/js/layout/groupByEventLayout.js"></script> 
<script src="/frcscout/js/layout/groupByTeamLayout.js"></script>  
<script src="/frcscout/js/layout/indexLayout.js"></script>

<%@ include file="../include/footer.jsp" %>