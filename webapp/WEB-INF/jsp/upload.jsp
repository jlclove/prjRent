<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/_includes.jsp"%>
<!DOCTYPE  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript"
	src="/static/vendor/jquery/dist/jquery.min.js?version=${config.version}"></script>
<body style="margin: 0; padding: 0; overflow: hidden; margin-top: 4px;">
	<form id="fileForm" enctype="multipart/form-data" action="/upload?type=${type }" method="post">
		<input type="file" name="pic" style="width:175px;"/><c:if test="${not empty newFileName }"><span style="font-size:12px;color:green;">已上传文件:${newFileName }</span></c:if><input type="hidden"
			name="messageId">
	</form>
</body>
</html>

<script type="text/javascript">
$(function(){
	$('[name=pic]').on('change',function(){
		$('#fileForm').submit();
	});
	if('${newFileName}'!=''){
		if('${type}'=='img'){
			window.parent.addPic('${newFileName}');
		}else if('${type}'=='rar'){
			window.parent.addRar('${newFileName}');
		}
	}
});
</script>