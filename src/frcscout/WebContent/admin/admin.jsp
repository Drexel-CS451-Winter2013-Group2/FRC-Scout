<%@ include file="../include/header.jsp" %>
<jsp:useBean id="TeamBean" class="com.frcscout.admin.TeamBean" scope="page"/>
<jsp:useBean id="UserAccountBean" class="com.frcscout.admin.UserAccountBean" scope="page"/>
<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<script> 
    var eventJSON = <%=EventBean.loadEvents() %>;
    var teamJSON = <%=TeamBean.loadTeams() %>;
    var userJSON = <%=UserAccountBean.loadUsers() %>;
</script>
<script src="/frcscout/js/layout/adminLayout.js"></script> 
<%@ include file="../include/footer.jsp" %>