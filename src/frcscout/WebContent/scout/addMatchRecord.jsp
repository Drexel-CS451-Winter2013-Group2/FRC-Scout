<%@ include file="../include/header.jsp" %>
<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<jsp:useBean id="TeamBean" class="com.frcscout.admin.TeamBean" scope="page"/>
<script> 
    var eventJSON = <%=EventBean.loadEvents() %>;
    var teamJSON = <%=TeamBean.loadTeams() %>;
</script>
<script src="/frcscout/js/scout/addMatchRecord.js"></script> 

<%@ include file="../include/footer.jsp" %>