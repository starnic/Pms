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
<title>custom page</title>

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
					<li role="presentation"><a href="${APP_PATH }/cmsg">信息管理</a></li>
					<li role="presentation" class="active"><a href="#">信息查询</a></li>
					<li role="presentation"><a href="${APP_PATH }/csystem">系统功能</a></li>
				</ul>
			</div>
			<div class="col-xs-9">
				<!-- Tab panes -->
				<div class="tab-content">
					<div class="row">
						<div class="col-md-12">
							<table class="table table-hover" id="house_table">
								<thead>
									<tr>
										<th>账号编号</th>
										<th>用户名</th>
										<th>业主编号</th>
										<th>车牌号</th>
										<th>房产</th>
									</tr>
								</thead>
								<tbody>

								</tbody>

							</table>
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
		var id;
		//1.页面加载完成以后，直接发送一个ajax请求，要到分页数据
		$(function() {
			//去首页
			id = ${sessionScope.LOGIN_CUSTOM.id};
			to_page(1);
		});
	
		function to_page(pn) {
			$.ajax({
				url: "${APP_PATH }/customss/" + id,
				data: "pn=" + pn,
				type: "GET",
				success: function(result) {
					//console.log(result);
					//1.解析并显示员工数据
					build_house_table(result);
					//2.解析分页信息数据
					/* build_page_info(result); */
					//解析分页条数据
					/* build_page_nav(result); */
				}
			});
		}
	
		function build_house_table(result) {
			//清空table表
			$("#house_table tbody").empty();
	
			var adms = result.extend.pageInfo.list;
			$.each(adms, function(index, item) {
				var customId = $("<td></td>").append(item.id);
				var customname = $("<td></td>").append(item.name);
				var customOwnerid = $("<td></td>").append(item.ownerid);
				var customcarId = $("<td></td>").append(item.carid);
				
				var oid = item.ownerid;
				//查出房产
				$.ajax({
					url:"${APP_PATH}/hous/" +oid,
					type:"GET", 
					success:function(result) {
						console.log(result.extend.house);
						houseData = result.extend.house;
						console.log(houseData[0].num);
						var custom = houseData[0].num;
						var customHouse = $("<td></td>").append(custom);
						
						$("<tr></tr>").append(customId)
						.append(customname)
						.append(customOwnerid)
						.append(customcarId)
						.append(customHouse)
						.appendTo("#house_table tbody");
						
					}
				});
				
			});
		}
	</script>
</body>
</html>