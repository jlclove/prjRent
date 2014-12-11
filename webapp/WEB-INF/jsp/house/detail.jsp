<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/_includes.jsp"%>
<c:set var="title">
	${houseMessage.propertyName }，${houseMessage.decoration }，<fmt:formatNumber
		value="${houseMessage.acreage }" type="number" pattern="0.00" />平米，
	${houseMessage.price }${houseMessage.unit }
</c:set>
<jsp:include page="/WEB-INF/jsp/common/_head.jsp">
	<jsp:param name="title" value="${title}" />
	<jsp:param name="vendorCss" value="bootstrap/dist/css/bootstrap" />
	<jsp:param name="css" value="main,detail" />
</jsp:include>
<div class="detail-main">
	<div class="detail-container container">
		<h3>${houseMessage.title}
		<a type="button" class="btn pull-right" style="padding:0;font-weight:bold;" data-toggle="modal" data-target="#myModal" onclick="$('#login').attr('tar','/add')" target="_blank">新增房源</a>
		</h3>
		<div class="mg_t20 row">
			<div class="col-md-8 detail-wrap mg_b20">
				<div class="box">
					<ul id="mytab" class="nav nav-tabs text-center">
						<li class="active"><a href="javascript:void(0)"
							data-index="0">照片</a></li>
						<li><a href="javascript:void(0)"
							data-index="${fn:length(houseMessage.housePictureList)}">地图</a></li>
					</ul>
					<div class="tab-content">
						<div id="photo" class="visible">
							<img style="display:none" alt="" src="<app:PhotoFullPath url="${houseMessage.housePictureList[0].pictureUrl }" size="ORIGINAL"/>">
							<div class="galleria" id="photo-slide">
								<c:forEach var="photo" items="${houseMessage.housePictureList}">
									<a
										href="<app:PhotoFullPath url="${photo.pictureUrl }" size="ORIGINAL"/>"
										title="${photo.remark }"> <img
										src="<app:PhotoFullPath url="${photo.pictureUrl }" size="ORIGINAL"/>"
										alt="${photo.remark }" />
									</a>
								</c:forEach>
							</div>
						</div>
						<div class="mg_t20 sm house-info clearfix">
							<div class="row">
								<div class="col-md-4"><span class="font999">租金</span></span><strong class="mg_l15">${houseMessage.price }${houseMessage.unit }</strong></div>
								<div class="col-md-4"><span class="font999">户型</span><strong class="mg_l15">${houseMessage.room }室${houseMessage.hall }厅${houseMessage.toilet }卫</strong></div>
								<div class="col-md-4"><span class="font999">面积</span><strong class="mg_l15"><fmt:formatNumber value="${houseMessage.acreage }" type="number" pattern="0.00" />平米</strong></div>
							</div>
							<div class="row">
								<div class="col-md-4"><span class="font999">楼层</span><strong class="mg_l15">${houseMessage.floorPosition } / 总${houseMessage.totalFloor }层</strong></div>
								<div class="col-md-4"><span class="font999">朝向</span><strong class="mg_l15">${houseMessage.direction}</strong></div>
								<div class="col-md-4"><span class="font999">装修</span><strong class="mg_l15">${houseMessage.decoration }</strong></div>
							</div>
							<div class="row">
								<div class="col-md-4"><span class="font999">小区</span><strong class="mg_l15">${houseMessage.propertyName }</strong></div>
							</div>
						</div>
					</div>
				</div>
				<div class="box sm mg_t20">
					<div class="desc-wrap house-info pd_t10">
						<h5 class="font-title mg_l20 mg_t5">
							<strong>小区信息</strong>
						</h5>
						<div class="row">
							<div class="col-md-6"><span class="font999">小区</span><strong class="mg_l15">${houseMessage.propertyName }</strong></div>
							<div class="col-md-6"><span class="font999">地址</span><strong class="mg_l15">${houseMessage.address}</strong></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="detail-side mg_b20  pull-left shareDiv">
					<div class="side-bar box">
						<div class="clearfix">
							<span>约看房，或了解更多信息...</span>
						</div>
						<div style="margin-top:25px;">
							<p class="ellipsis">
								<b style="color:#333;font-size:16px;">${houseMessage.userName }</b><small class="gray mg_l10">此房业主</small>
							</p>
							<button type="button" class="btn btn-success"
								style="width: 100%;">
								<span class="glyphicon glyphicon-earphone"></span>
					 			<a href="tel:${houseMessage.mobilePhone }" class="mg_l10" style="text-decoration:none; font-size:16px;color:#FFF;" >${houseMessage.mobilePhone }</a>
							</button>
						</div>
					</div>
				</div>
				<div class="detail-side mg_b20  pull-left">
					<div class="side-bar send box dropdown">
						<div class="clearfix">
							<span>分享信息给他人...</span>
						</div>
						<div class="dropdown">
							<a data-toggle="dropdown" href="#">
								<a type="button" class="btn btn-agent mg_t10" href="/share/${houseMessage.id }">更换联系人并转发给朋友</a>
							</a>
						</div>
					</div>
				</div>
				<div class="detail-side mg_b20  pull-left">
					<div class="side-bar box">
						<div class="clearfix">
							<span>查看与下载高清图片包</span>
						</div>
						<a type="button" class="btn btn-agent mg_t10"
							data-toggle="modal" href="/static/rars/${houseMessage.attachName }" target="_blank">点击下载</a>
					</div>
				</div>
				<div class="detail-side mg_b20  pull-left">
					<div class="side-bar box">
						<a type="button" class="btn btn-agent mg_t10"
							data-toggle="modal" data-target="#myModal" target="_blank" onclick="$('#login').attr('tar','/share/${houseMessage.id }')">编辑信息</a>
					</div>
				</div>
				<div class="detail-side mg_b20  pull-left">
					<div class="side-bar box">
						<div class="clearfix">
							<span>其他类似房源</span>
						</div>
						<ul class="detail-similarHouse">
							<c:forEach items="${similars }" var="house">
							<li class="ellipsis"><a href="/${house.id }" title="${house.propertyName }，${house.room }房${house.hall }厅，${house.price }${house.unit }">
							<c:choose>
								<c:when test="${not empty house.title }">
									${house.title }
								</c:when>
								<c:otherwise>
									${house.propertyName }，${house.room }房${house.hall }厅，${house.price }${house.unit }
								</c:otherwise>
							</c:choose>
							</a></li>
							</c:forEach>
							<li class="ellipsis" style="text-align:right;"><a href="/more">查看更多</a></li>
						</ul>
					</div>
				</div>
				
				<div class="detail-side mg_b20  pull-left">
					<div class="side-bar box">
						<div class="clearfix">
							<span>加微信关注更多房源</span>
						</div>
						<img alt="加微信关注更多房源" src="/static/images/adwx.png" width="100%">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">口令登录</h4>
      </div>
      <div class="modal-body">
        <p class="ellipsis">
			 <input type="password" class="form-control" id="inputPassword" placeholder="输入操作密码">
		</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" id="login" tar="/share/${houseMessage.id }">登录</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	var latitude = '${houseMessage.latitude}';
	var longitude = '${houseMessage.longitude}';
	var houseid = '${houseMessage.houseCode}';
</script>
<jsp:include page="/WEB-INF/jsp/common/_foot.jsp">
	<jsp:param name="vendorJs" value="galleria/src/galleria,bootstrap/dist/js/bootstrap.min"/>
	<jsp:param name="js" value="detail"/>
</jsp:include>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-46981520-5', 'auto');
  ga('send', 'pageview');

</script>
