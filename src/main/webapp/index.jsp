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

<%   
  session.invalidate();   
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

	<!-- Modal 业主登录 -->
	<div class="modal fade" id="customLogin" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" >
				<div class="modal-header" >
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">登录</h4>
				</div>
				<div class="modal-body">
					<%-- <div class="form-group" class="col-sm-12">
							<img src="${APP_PATH }/static/img/she.jpg" alt="..." style="height: 320px;width: 568px" class="img-rounded">
					</div> --%>
					<br/>
					<form class="form-horizontal">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">姓名:</label>
							<div class="col-sm-9 ">
								<input type="text" class="form-control" name="name" id="cname"
									placeholder="姓名"><span class="help-block"></span>

							</div>
						</div>
						<div class="form-group">
							<label for="cpassword" class="col-sm-2 control-label">密码:</label>
							<div class="col-sm-9">
								<input type="password" name="password" class="form-control"
									id="cpassword" placeholder="密码"><span class="help-block"></span>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取&ensp;&ensp;消</button>
					<button type="button" class="btn btn-info" id="custom_login_butn">登&ensp;&ensp;录</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal 管理员登录 -->
	<div class="modal fade" id="adminLogin" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">登录</h4>
				</div>
				<div class="modal-body">
					<%-- <div class="form-group" class="col-sm-12">
							<img src="${APP_PATH }/static/img/you.jpg" alt="..." style="height: 320px;width: 568px" class="img-rounded">
					</div> --%>
					<br/>
					<form class="form-horizontal">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">姓名:</label>
							<div class="col-sm-9 ">
								<input type="text" class="form-control" name="name" id="name"
									placeholder="姓名"><span class="help-block"></span>

							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">密码:</label>
							<div class="col-sm-9">
								<input type="password" name="password" class="form-control"
									id="password" placeholder="密码"><span class="help-block"></span>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取&ensp;&ensp;消</button>
					<button type="button" class="btn btn-info" id="admin_login_butn">登&ensp;&ensp;录</button>
				</div>
			</div>
		</div>
	</div>

	<br>
	<div class="page-header">
		<h1>
			&ensp;物业管理系统 <small>登录</small>
		</h1>
	</div>
	<br>
	<br>
	<div class="row">
	  <div class="col-sm-6 col-md-5 col-md-offset-1" >
	    <div class="thumbnail" id="k1">
	      <img src="${APP_PATH }/static/img/she.jpg" alt="..."/>
	      <div class="caption">
	        <h3>&ensp;业主登录</h3>
	        <br/>
	        <p>&ensp;&ensp;<a href="#" class="btn btn-info" role="button" id="customs_button">登&ensp;&ensp;录</a></p>
	      </div>
	    </div>
	  </div>
	  <div class="col-sm-6 col-md-5" >
	    <div class="thumbnail" id="k2">
	      <img src="${APP_PATH }/static/img/you.jpg" alt="..."/>
	      <div class="caption">
	        <h3>&ensp;管理登录</h3>
	        <br/>
	        <p>&ensp;&ensp;<a href="#" class="btn btn-info" role="button" id="admin_button">登&ensp;&ensp;录</a></p>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- <div class="row container">
		<div class="col-md-6">
			<a href="#" id="admin_btn"><button type="button" class="btn btn-primary" id="admin_button">管理登陆</button></a>
		</div>
  		<div class="col-md-6">
  			<a href="#"> <button type="button" class="btn btn-success" id="customs_button">业主登</button> </a>
  		</div>
		
	</div> -->

	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="${APP_PATH }/static/js/jquery-3.3.1.js"></script>
	<script src="${APP_PATH }/static/Bootstrap/js/bootstrap.min.js"></script>
	<SCRIPT type="text/javascript">
		//重置表单内容及样式
		function reset_form(ele) {
			//清除表单数据 表单重置
			$(ele)[0].reset();
			//重置表单的样式
			$(ele).find("*").removeClass("has-success has-error");
			$(ele).find(".help-block").text("");
		}

		//点击登录按钮
		$("#admin_button").click(function() {

			reset_form("#adminLogin form");
			//弹出模态框
			$("#adminLogin").modal({
				backdrop : "static"
			});
		});

		//校验抽取
		function show_validate_msg(ele, status, msg) {
			//清除当前元素的校验状态
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text("");
			if ("success" == status) {
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);

			} else if ("error" == status) {
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}
		
		
		//管理员登录
		//绑定一个change事件，姓名改变验证
		$("#name").change(
				function() {
					//发送ajax请求校验是否姓名是否可用
					var name = this.value;
					$
							.ajax({
								url : "${APP_PATH}/checkLogin",
								data : "name=" + name,
								type : "POST",
								success : function(result) {
									if (result.code == 100) {
										show_validate_msg("#name", "success",
												"用户名正确");
										$("#admin_login_butn").attr("ajax-va",
												"success");
									} else {
										show_validate_msg("#name", "error",
												"用户名错误");
										$("#admin_login_butn").attr("ajax-va",
												"error");
									}
								}

							});

				});

		//保存按钮绑定单击事件，点击保存，保存管理员
		$("#admin_login_butn").click(function() {
			//模态框填写的表单数据提交给服务器进行保存

			//先对要提交给服务器的数据进行校验。
			/* if (!validate_add_form()) {
				return false;
			}
			; */

			//判断之前的ajax用户名校验是否成功
			if ($(this).attr("ajax-va") == "error") {
				return false;
			}

			//发送ajax保存
			$.ajax({
				url : "${APP_PATH}/adminLogin",
				type : "POST",
				data : $("#adminLogin form").serialize(),
				success : function(result) {

					if (result.code == 100) {
						//保存成功
						//1.关闭模态框
						$("#adminLogin").modal('hide');

						//2.来到最后一页,显示保存的数据
						//发送ajax请求显示最后一页数据即可
						//总记录数作为页码
						window.location.href = "admin";
					} else {
						//显示失败信息
						console.log(result);
						show_validate_msg("#password", "error", "密码错误");
					}
				}
			});
		});
		
		
		//业主登录 验证
		$("#customs_button").click(function() {

			reset_form("#customLogin form");
			//弹出模态框
			$("#customLogin").modal({
				backdrop : "static"
			});
		});
		//绑定一个change事件，姓名改变验证
		$("#cname").change(
				function() {
					//发送ajax请求校验是否姓名是否可用
					var name = this.value;
					$
							.ajax({
								url : "${APP_PATH}/checkLoginc",
								data : "name=" + name,
								type : "POST",
								success : function(result) {
									if (result.code == 100) {
										show_validate_msg("#cname", "success",
												"用户名正确");
										$("#custom_login_butn").attr("ajax-va",
												"success");
									} else {
										show_validate_msg("#cname", "error",
												"用户名不存在");
										 $("#custom_login_butn").attr("ajax-va",
												"error");
									}
								}

							});

				});

		//保存按钮绑定单击事件，点击保存，保存管理员
		$("#custom_login_butn").click(function() {
			//模态框填写的表单数据提交给服务器进行保存

			//先对要提交给服务器的数据进行校验。
			/* if (!validate_add_form()) {
				return false;
			}
			; */

			//判断之前的ajax用户名校验是否成功
			if ($(this).attr("ajax-va") == "error") {
				return false;
			}

			//发送ajax保存
			$.ajax({
				url : "${APP_PATH}/customLogin",
				type : "POST",
				data : $("#customLogin form").serialize(),
				success : function(result) {

					if (result.code == 100) {
						//保存成功
						//1.关闭模态框
						$("#customLogin").modal('hide');

						//2.来到最后一页,显示保存的数据
						//发送ajax请求显示最后一页数据即可
						//总记录数作为页码
						window.location.href = "csystem";
					} else {
						//显示失败信息
						console.log(result);
						show_validate_msg("#cpassword", "error", "密码错误");
					}
				}
			});
		});
	</SCRIPT>
</body>
</html>