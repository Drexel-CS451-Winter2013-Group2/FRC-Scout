<%@ include file="../include/header.jsp" %>

<jsp:useBean id="MatchRecordBean" class="com.frcscout.scout.MatchRecordBean" scope="page"/>
<c:if test="${pageContext.request.isUserInRole('scout')}">
     <script> 
    var matchRecordJSON = <%=MatchRecordBean.loadMatchRecords(request.getRemoteUser()) %>;
</script>
</c:if>
<c:if test="${pageContext.request.isUserInRole('administrator')}">
     <script> 
    var matchRecordJSON = <%=MatchRecordBean.loadMatchRecords() %>;
</script>
</c:if>

<script src="/frcscout/js/layout/scoutLayout.js"></script> 
<%@ include file="../include/footer.jsp" %>