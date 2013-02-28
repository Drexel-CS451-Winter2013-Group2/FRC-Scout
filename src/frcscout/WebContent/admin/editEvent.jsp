<%@ include file="../include/header.jsp" %>
<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<%EventBean.loadEvent(request.getParameter("id"));%>

<script>
var name = "<%=EventBean.getName() %>";
var loc = "<%=EventBean.getLocation() %>";
var startDate = "<%=EventBean.getStartDate() %>";
var endDate = "<%=EventBean.getEndDate() %>";
</script>

<script src="/frcscout/js/admin/editEvent.js"></script>

<%@ include file="../include/footer.jsp" %>