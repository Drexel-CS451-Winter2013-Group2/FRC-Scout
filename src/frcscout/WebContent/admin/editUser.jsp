<%@ include file="../include/header.jsp" %>
<jsp:useBean id="UserAccountBean" class="com.frcscout.admin.UserAccountBean" scope="page"/>
<%UserAccountBean.loadUser(request.getParameter("id"));%>

<script>
var email = "<%=UserAccountBean.getEmail() %>";
var firstName = "<%=UserAccountBean.getFirstName() %>"; 
var lastName = "<%=UserAccountBean.getLastName() %>";
var role = "<%=UserAccountBean.getRole() %>";
</script>

<script src="/frcscout/js/admin/editUser.js"></script>

<%@ include file="../include/footer.jsp" %>