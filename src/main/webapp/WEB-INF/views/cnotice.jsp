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
					<li role="presentation"  class="active"><a href="#">小区公告</a></li>
					<li role="presentation"><a href="${APP_PATH }/cmsg">信息管理</a></li>
					<li role="presentation"><a href="${APP_PATH }/cmsgs">信息查询</a></li>
					<li role="presentation"><a href="${APP_PATH }/csystem">系统功能</a></li>
				</ul>
			</div>
			<div class="col-xs-9" id = "main">
				<!--小区公告-->
				<div class="col-xs-3">
					<br/>
					<ul class=" nav-pills nav-stacked " role="tablist" id="aa">
						
					</ul>
				</div>
				<div class="col-xs-9" >
					<!-- Tab panes -->
					<div class="tab-content" id ="bb">
						
					</div>
				</div>
				
			</div>
		</div>
		<!-- 显示分页信息 -->
				<div class="row">

				<!-- 分页文字信息 -->
				<div class="col-md-2 col-md-offset-5" id="page_info_area"></div>
	
				<!-- 分页条信息 -->
				<div class="col-md-4 col-md-offset-1" id="page_nav_area"></div>
				</div>
	</div>
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="${APP_PATH }/static/js/jquery-3.3.1.js"></script>
	<script src="${APP_PATH }/static/Bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(function() {
		//去首页
		to_page(1);
	});

	function to_page(pn) {
		$.ajax({
			url: "${APP_PATH }/notices",
			data: "pn=" + pn,
			type: "GET",
			success: function(result) {
				//console.log(result);
				//1.解析并显示员工数据
				build_adms_table(result);
				//2.解析分页信息数据
				build_page_info(result);
				//解析分页条数据
				build_page_nav(result);
			}
		});
	}

	function build_adms_table(result) {
		//清空table表
		$("#aa").empty();
		$("#bb").empty();
		console.log(result);
		var adms = result.extend.pageInfo.list;
		$.each(adms, function(index, item) {
			
			var noticeTitle =item.title;
			var noticeContent =item.content;
			
			if (index == 0){
				var aa = $("<li></li>").addClass("active").append($("<a></a>")
						.attr("href","#"+"c"+index)
						.attr("aria-controls","c"+index)
						.attr("role","tab")
						.attr("data-toggle","tab")
						.append(noticeTitle));
				var bb = $("<div></div>")
						.addClass("tab-pane active")
						.attr("role","tabpanel")
						.attr("id", "c"+index)
						.append(noticeContent);
			}else{
				var aa = $("<li></li>").append($("<a></a>")
						.attr("href","#"+"c"+index)
						.attr("aria-controls","c"+index)
						.attr("role","tab")
						.attr("data-toggle","tab")
						.append(noticeTitle));
				var bb = $("<div></div>")
						.addClass("tab-pane")
						.attr("role","tabpanel")
						.attr("id", "c"+index)
						.append(noticeContent);
			}

			aa.append("<br/>").appendTo("#aa");
			bb.append("<br/>").appendTo("#bb");
		});
	}

	//分页信息数据
	function build_page_info(result) {
		//清空
		$("#page_info_area").empty();

		$("#page_info_area").append(
			"当前" + result.extend.pageInfo.pageNum + "页,总" +
					 result.extend.pageInfo.pages + "页 ,总" +
					 result.extend.pageInfo.total + "条记录")
		
		totalRecord = result.extend.pageInfo.total;
		currentPag = result.extend.pageInfo.pageNum;
	}

	//解析显示分页条,点击动作
	function build_page_nav(result) {
		//清空
		$("#page_nav_area").empty();

		//page_nav_area
		var ul = $("<ul></ul>").addClass("pagination");

		//构建元素
		var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
		var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href", "#"));
		if(result.extend.pageInfo.hasPreviousPage == false) {
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		} else {
			//为元素添加点击翻页事件
			//首页
			firstPageLi.click(function() {
				to_page(1);
			});
			//上一页
			prePageLi.click(function() {
				to_page(result.extend.pageInfo.pageNum - 1);
			});
		}

		var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href", "#"));
		var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
		if(result.extend.pageInfo.hasNextPage == false) {
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		} else {
			//下一页
			nextPageLi.click(function() {
				to_page(result.extend.pageInfo.pageNum + 1);
			});
			//末页
			lastPageLi.click(function() {
				to_page(result.extend.pageInfo.pages);
			});
		}

		//下一页
		nextPageLi.click(function() {
			to_page(result.extend.pageInfo.pageNum + 1);
		});
		//末页
		lastPageLi.click(function() {
			to_page(result.extend.pageInfo.pages);
		});

		//添加首页和前一页的提示
		ul.append(firstPageLi).append(prePageLi);

		//遍历给ul添加页码提示
		$.each(result.extend.pageInfo.navigatepageNums, function(index,
			item) {
			var numLi = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
			if(result.extend.pageInfo.pageNum == item) {
				numLi.addClass("active");
			}
			numLi.click(function() {
				to_page(item);
			});
			ul.append(numLi);
		});

		//添加下一页和末页的提示
		ul.append(nextPageLi).append(lastPageLi);

		//把ul加入到nav中
		var navEle = $("<nav></nav>").append(ul);
		navEle.appendTo("#page_nav_area");
	}
	</script>
</body>
</html>