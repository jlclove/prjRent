<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/_includes.jsp" %>
<div class="">
	<div class="footer">
		<div class="text-center gray width-fixed">
	    	<p class="copyright">
                COPYRIGHT © SHANGHAI JIAOGEIWO, ALL RIGHTS RESERVED.
            </p>
	    </div>
	</div>
</div>
</body>
</html>
<script type="text/javascript">
    var version = '${config.version}';
</script>
<script type="text/javascript" src="/static/vendor/requirejs/require${suffix}.js?version=${config.version}"></script>
<script type="text/javascript" src="/static/js/config${suffix}.js?version=${config.version}"></script>
<c:if test="${ not empty param.vendorJs}">
    <c:forTokens var="vendorJs" items="${param.vendorJs}" delims="|," >
        <c:if test="${not empty vendorJs}">
            <script type="text/javascript" src="/static/vendor/${vendorJs}${suffix}.js?version=${config.version}"></script>
        </c:if>
    </c:forTokens>
</c:if>
<c:if test="${ not empty param.js}">
    <c:forTokens var="js" items="${param.js}" delims="|," >
        <c:if test="${not empty js}">
            <script type="text/javascript" src="/static/js/${js}${suffix}.js?version=${config.version}"></script>
        </c:if>
    </c:forTokens>
</c:if>