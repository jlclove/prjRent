<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/_includes.jsp"%>
<jsp:include page="/WEB-INF/jsp/common/_head.jsp">
	<jsp:param name="title" value="分享立即看" />
	<jsp:param name="vendorCss" value="bootstrap/dist/css/bootstrap" />
	<jsp:param name="css" value="main,detail" />
</jsp:include>
<div class="detail-main" style="min-height:550px;">
	<div class="detail-container container">
		<h3>分享实景高清图片</h3>
		<div class="clearfix mg_t20" style="padding-top:20px; margin-left: auto; margin-right:auto;">
			<p><strong>你可以通过短信、微信或邮件等方式把下面链接发送给客户：</strong></p>
			<div
				style="border: 1px solid #eee; background: #fafafa; -webkit-border-radius: 5px; border-radius: 5px; padding: 22px 12px;font-size:22px;color:#2d8cf8;">
				http://jiaogeiwo.com/${lijikanId } </div>
			<div style="padding:40px 0;">
				<img src="/getBarCode?content=http://jiaogeiwo.com/${lijikanId }" style="float:left;"/>
				<div style="margin-left:12px;float:left;">
					<h3>扫一扫</h3>
					<p>
						无需输入，直接将链接"搬到"手机
					</p>
				</div> 
				<div style="clear:both;"></div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/common/_foot.jsp"></jsp:include>