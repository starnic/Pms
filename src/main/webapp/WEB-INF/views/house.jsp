<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>admin page</title>

<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- web路径
		不以/开始的相对路径，找资源，以当前资源为基准，经常容易出错
		以/开始的相对路径，找资源，以服务器的路径为基准(http://localhost:3306); 需要加上项目名
		http://localhost:3306/crud

-->

<!-- Bootstrap -->
<link href="${APP_PATH }/static/Bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${APP_PATH }/static/style.css"
rel="stylesheet">
	<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
	<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
	<!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  	<![endif]-->
</head>

<body>
	<div class="container-fluid">
		<nav class="navbar navbar-inverse text-center ">
			<h2 style="color: whitesmoke">物业管理<small>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;${sessionScope.LOGIN_ADMIN.name}, 你好</small></h2>
		</nav>
		<br />

		<div class="row">
			<!-- Nav tabs -->
			<div class="col-xs-3">
				<ul class="nav nav-pills nav-stacked list-group" role="tablist">
					<li role="presentation"><a href="${APP_PATH }/admin">管理员信息管理</a></li>
					<li role="presentation" class="active"><a href="#">物业管理</a></li>
					<li role="presentation"><a href="${APP_PATH }/maintain">报修管理</a></li>
					<li role="presentation"><a href="${APP_PATH }/sgc">保卫绿化</a></li>
					<li role="presentation"><a href="${APP_PATH }/notice">公告管理</a></li>
					<li role="presentation"><a href="${APP_PATH }/system">系统功能</a></li>
				</ul>
			</div>
			<div class="col-xs-9">
				<!-- Tab panes -->
				<div class="tab-content">
						<div>
							<!-- Nav tabs -->
							<ul class="nav nav-pills nav-justified" role="tablist">
								<li role="presentation" class="active"><a href="#">房产信息</a></li>
								<li role="presentation"><a href="${APP_PATH }/custom">业主信息</a></li>
							</ul>
							<!-- Tab panes -->
							<div class="tab-content">
									<%@ include file="houseList.jsp"%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="${APP_PATH }/static/js/jquery-3.3.1.js"></script>
	<script src="${APP_PATH }/static/Bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
      if($.trim("${sessionScope.LOGIN_ADMIN}") == "")
      {
            top.location.href="${APP_PATH}/index.jsp";
      }
	</script>
</body>
</html>