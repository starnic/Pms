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
<link href="${APP_PATH }/static/cstyle.css" rel="stylesheet">
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
			<h2 style="color: whitesmoke">物业管理<small>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;${sessionScope.LOGIN_CUSTOM.name}, 你好</small></h2>
		</nav>
		<br />

		<div class="row">
			<!-- Nav tabs -->
			<div class="col-xs-3">
				<ul class="nav nav-pills nav-stacked list-group" role="tablist">
					<li role="presentation"><a href="${APP_PATH }/cnotice">小区公告</a></li>
					<li role="presentation" class="active"><a href="#">信息管理</a></li>
					<li role="presentation"><a href="${APP_PATH }/cmsgs">信息查询</a></li>
					<li role="presentation"><a href="${APP_PATH }/csystem">系统功能</a></li>
				</ul>
			</div>
			<div class="col-xs-9">
				<!-- Tab panes -->
				<div class="tab-content">
					<div>
						<!-- Nav tabs -->
						<ul class="nav nav-pills nav-justified" role="tablist">
							<li role="presentation" class="active"><a href="#repairs"
								aria-controls="repairs" role="tab" data-toggle="tab">添加报修</a></li>
							<li role="presentation"><a href="#srepairs"
								aria-controls="srepairs" role="tab" data-toggle="tab">报修查询</a></li>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="repairs">
								<form class="form-horizontal">
								<br/>
									<div class="form-group">
										<label for="oldpasswd" class="col-sm-2 control-label">报修物品</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="thing" id="oldpasswd"
												placeholder="报修物品">
										</div>
									</div>
									<div class="form-group">
										<label for="newpasswd" class="col-sm-2 control-label">房门号</label>
										<div class="col-sm-7 ">
											<input type="text" class="form-control" name="homesbumber" id="newpasswd"
												placeholder="房门号">
										</div>
									</div>
									<div class="form-group">
										<label for="newpasswd1" class="col-sm-2 control-label">报修时间</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="sdate" id="newpasswd1" placeholder="时间 如(2015/2/25)">
										</div>
									</div>
									<div class="form-group">
										<label for="newpasswd1" class="col-sm-2 control-label">状态</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="status" id="newpasswd1" placeholder="未处理">
										</div>
									</div>
								
									<div class="form-group">
										<label for="newpassw" class="col-sm-2 control-label">备注</label>
										<div class="col-sm-7">
											<textarea class="form-control" name="smemo" id="newpassw"
												placeholder="..."></textarea>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2">
											<button type="button" class="btn btn-default" id="main_save_btn">提交</button>
											&ensp;
											<button type="reset" class="btn btn-default">重置</button>
										</div>

									</div>
								</form>

							</div>
							<div role="tabpanel" class="tab-pane" id="srepairs">
								<%@ include file="cmsgList.jsp"%>
							</div>
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
		if ($.trim("${sessionScope.LOGIN_CUSTOM}") == "") {
			top.location.href = "${APP_PATH}/index.jsp";
		}
	</script>
	<script type="text/javascript">
	$("#main_save_btn").click(function() {
			
			//发送ajax保存
			$.ajax({
				url: "${APP_PATH}/maintain",
				type: "POST",
				data: $("#repairs form").serialize(),
				success: function(result) {
					//

					if(result.code == 100) {
						//保存成功
						//1.关闭模态框
						alert(result.msg);
						$("#repairs form")[0].reset();
						//2.来到最后一页,显示保存的数据
						//发送ajax请求显示最后一页数据即可
						//总记录数作为页码
					} else {
						//显示失败信息
						//console.log(result);
						alert(result.msg);
					}
				}
			});
		});
	</script>
</body>
</html>