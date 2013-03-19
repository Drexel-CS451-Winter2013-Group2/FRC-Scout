<%@ include file="../include/header.jsp" %>
<script src="/frcscout/js/layout/viewMatchRecordLayout.js"></script>
<jsp:useBean id="MatchRecordBean" class="com.frcscout.scout.MatchRecordBean" scope="page"/>
<jsp:useBean id="EventBean" class="com.frcscout.admin.EventBean" scope="page"/>
<%MatchRecordBean.loadMatchRecord(request.getParameter("id"));%>
<%EventBean.loadEvent(Integer.toString(MatchRecordBean.getEventId()));%>
<table>
    <tr>
        <td class="td-bold">Team:</td><td> <jsp:getProperty name="MatchRecordBean" property="teamId"/> </td>
        <td class="td-bold">Auton Top:</td><td> <jsp:getProperty name="MatchRecordBean" property="autonTop"/> </td>
        <td class="td-bold">Teleop Top:</td><td> <jsp:getProperty name="MatchRecordBean" property="teleopTop"/> </td>
        <td class="td-bold">Confidence:</td><td> <jsp:getProperty name="MatchRecordBean" property="confidence"/> </td>
    </tr>
    <tr>
        <td class="td-bold">Event:</td><td> <jsp:getProperty name="EventBean" property="name"/> </td>
        <td class="td-bold">Auton Middle:</td><td> <jsp:getProperty name="MatchRecordBean" property="autonMiddle"/> </td>
        <td class="td-bold">Teleop Middle:</td><td> <jsp:getProperty name="MatchRecordBean" property="teleopMiddle"/> </td>
        <td class="td-bold">Ability:</td><td> <jsp:getProperty name="MatchRecordBean" property="ability"/> </td>
        
    </tr>
    <tr>
        <td class="td-bold">Match Number:</td><td> <jsp:getProperty name="MatchRecordBean" property="matchId"/> </td>
        <td class="td-bold">Auton Bottom:</td><td> <jsp:getProperty name="MatchRecordBean" property="autonBottom"/> </td>
        <td class="td-bold">Teleop Bottom:</td><td> <jsp:getProperty name="MatchRecordBean" property="teleopBottom"/> </td>
        <td class="td-bold">Fouls:</td><td> <jsp:getProperty name="MatchRecordBean" property="fouls"/> </td>
        
        
    </tr>
    <tr>
        <td class="td-bold">Color:</td><td> <jsp:getProperty name="MatchRecordBean" property="color"/> </td>
        <td></td><td></td>
        <td class="td-bold">Teleop Pyramid:</td><td> <jsp:getProperty name="MatchRecordBean" property="teleopPyramid"/> </td>
        <td class="td-bold">Technical Fouls:</td><td> <jsp:getProperty name="MatchRecordBean" property="technicalFouls"/> </td>
    </tr>
    <tr>
        <td class="td-bold">Play Style:</td><td> <jsp:getProperty name="MatchRecordBean" property="playStyle"/> </td>
    </tr>
    <tr>
        <td class="td-bold">Comments:</td><td><p><jsp:getProperty name="MatchRecordBean" property="comments"/></p></td>
    </tr>
</table>

<input type="button" value="Return" onclick="window.location.href='/frcscout/' + getURLParameter('return') + '&teamtab=' + getURLParameter('teamtab')" />

<%@ include file="../include/footer.jsp" %>