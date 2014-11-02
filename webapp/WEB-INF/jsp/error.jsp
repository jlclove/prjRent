<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/_includes.jsp" %>
<jsp:include page="/WEB-INF/jsp/common/_head.jsp">
    <jsp:param name="title" value="德佑在线"/>
    <jsp:param name="vendorCss" value="bootstrap/dist/css/bootstrap"/>
    <jsp:param name="css" value="main,error"/>
</jsp:include>
<div class="text-center error-page">
	<h3>房源不存在</h3>
</div>

<jsp:include page="/WEB-INF/jsp/common/_foot.jsp">
    <jsp:param name="foot" value="false"></jsp:param>
</jsp:include>