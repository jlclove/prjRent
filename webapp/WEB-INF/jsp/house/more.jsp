<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/_includes.jsp"%>
<jsp:include page="/WEB-INF/jsp/common/_head.jsp">
	<jsp:param name="title" value="更多在租房源" />
	<jsp:param name="vendorCss" value="bootstrap/dist/css/bootstrap" />
	<jsp:param name="css" value="main,detail" />
</jsp:include>
<div class="detail-main">
	<div class="detail-container container">
	<h2><b>最新在租房源，点击查看下载实景高清图片</b></h2>
	<table cellspacing="0" cellpadding="0" style="margin-top:30px;">
		<tr><th>编号</th><th style="padding:10px 20px;">标题</th><th>发布人</th></tr>
		<c:forEach items="${messageList }" var="message">
			<tr>
				<td>${message.id }</td>
				<td style="padding:10px 20px; width:400px;">
					<a href="/${message.id}" target="_blank">
					<c:choose>
						<c:when test="${not empty message.title }">
							${message.title}
						</c:when>
						<c:otherwise>
						${message.propertyName }，${message.room }房${message.hall }厅，${message.price }${message.unit }
						</c:otherwise>
					</c:choose>
					</a>
				</td>
				<td>${message.userName }</td>
			</tr>
		</c:forEach>
	</table>
	<div style="margin-top:30px;">
		共${totalCount }条记录，当前第${houseMessage.pageNo }页  <a href="/?pageNo=${houseMessage.pageNo<=1?1:houseMessage.pageNo-1 }" style="margin-right:15px;margin-left:15px;">上一页</a><a href="/more?pageNo=${houseMessage.pageNo<1?2:houseMessage.pageNo+1 }">下一页</a>
	</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/common/_foot.jsp">
	<jsp:param name="vendorJs" value="galleria/src/galleria,bootstrap/dist/js/bootstrap.min"/>
</jsp:include>