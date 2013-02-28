<%@ include file="../include/header.jsp" %>
<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<script> 
    var eventJSON = <%=EventBean.loadEvents() %>;
</script>

<script src="/frcscout/js/layout/indexLayout.js"></script> 

<%@ include file="../include/footer.jsp" %>