<%@ include file="../include/header.jsp" %>

<jsp:useBean id="MatchRecordBean" class="com.frcscout.scout.MatchRecordBean" scope="page"/>
<script> 
	//TODO should we also fetch the real match numbers, not just the id?
    var matchRecordJSON = <%=MatchRecordBean.loadMatchRecords() %>;
</script>

<script src="/frcscout/js/layout/scoutLayout.js"></script> 
<%@ include file="../include/footer.jsp" %>