<%@ include file="../include/header.jsp" %>

<% session.invalidate();
  response.sendRedirect("/frcscout/");
%>

<%@ include file="../include/footer.jsp" %>