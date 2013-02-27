<%@ include file="../include/header.jsp" %>
<jsp:useBean id="TeamBean" class="com.frcscout.admin.TeamBean" scope="page"/>
<%TeamBean.loadTeam(request.getParameter("id"));%>

<script>
var id = "<%=TeamBean.getId() %>";
var name = "<%=TeamBean.getName() %>";
var loc = "<%=TeamBean.getLocation() %>";
</script>

<script src="/frcscout/js/admin/editTeam.js"></script>

<%@ include file="../include/footer.jsp" %>