<%@ include file="../include/header.jsp" %>
<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<jsp:useBean id="TeamBean" class="com.frcscout.admin.TeamBean" scope="page"/>
<jsp:useBean id="GroupByEventBean" class="com.frcscout.teammember.GroupByEventBean" scope="session"/>
<jsp:setProperty name="GroupByEventBean" property="selectedEvent" value="${param.event}"/>

<script> 
    var selectedEvent = <%=GroupByEventBean.getSelectedEvent() %>;
    var eventJSON = <%=EventBean.loadEvents() %>;
    var teamJSON = <%=TeamBean.loadTeams() %>;
    var overviewChartJSON = <%=GroupByEventBean.getOverviewChart() %>;
    var overviewTableJSON = <%=GroupByEventBean.getOverviewTable() %>;
    var redTeamTableJSON = <%=GroupByEventBean.getRedTeamTable() %>;
    var blueTeamTableJSON = <%=GroupByEventBean.getBlueTeamTable() %>;
    var teamTableJSON = <%=GroupByEventBean.getTeamTable() %>;
    var teamPieChartJSON = <%=GroupByEventBean.getTeamPieChart() %>;
    var teamLineGraphJSON = <%=GroupByEventBean.getTeamLineGraph() %>;

</script>

<script src="/frcscout/js/layout/indexLayout.js"></script> 

<%@ include file="../include/footer.jsp" %>