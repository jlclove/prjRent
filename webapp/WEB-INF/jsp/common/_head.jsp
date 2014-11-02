<%@ include file="/WEB-INF/jsp/common/_includes.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js">
<head>
	<title>${param.title }-交给我资产管理有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:if test="${ not empty param.vendorCss}">
        <c:forTokens var="vendorCss" items="${param.vendorCss}" delims="|," >
            <c:if test="${not empty vendorCss}">
                <link rel="stylesheet" href="/static/vendor/${vendorCss}${suffix}.css?version=${config.version}"/>
            </c:if>
        </c:forTokens>
    </c:if>
    <c:if test="${ not empty param.css}">
        <c:forTokens var="css" items="${param.css}" delims="|," >
            <c:if test="${not empty css}">
                <link rel="stylesheet" href="/static/css/${css}${suffix}.css?version=${config.version}"/>
            </c:if>
        </c:forTokens>
    </c:if>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <script type="text/javascript" src="/static/vendor/jquery/dist/jquery.min.js?version=${config.version}"></script>
</head>
<body data-spy="scroll" data-target="#category" data-offset="100">
    <div class="dooio-header pd_l20 pd_r20">
	    <div class="width-fixed clearfix">
	        <div class="pull-left"  style="height:44px;color:#fff;font-size:20px; line-height: 44px;">
                   <a href="/">首页</a>
	        </div>
	    </div>
    </div>