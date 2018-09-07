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
					<li role="presentation"><a href="${APP_PATH }/house">物业管理</a></li>
					<li role="presentation"><a href="${APP_PATH }/maintain">报修管理</a></li>
					<li role="presentation"><a href="${APP_PATH }/inspection">保卫绿化</a></li>
					<li role="presentation"><a href="${APP_PATH }/notice">公告管理</a></li>
					<li role="presentation" class="active"><a href="#">系统功能</a></li>
				</ul>
			</div>
			<div class="col-xs-9">
				<!-- Tab panes -->
				<div class="tab-content">
					<div>

						<!-- Nav tabs -->
						<ul class="nav nav-pills nav-justified" role="tablist">
							<li role="presentation" class="active"><a href="#quit"
								aria-controls="quit" role="tab" data-toggle="tab">退出</a></li>
							<li role="presentation"><a href="#upps" aria-controls="upps"
								role="tab" data-toggle="tab">修改密码</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="quit">
								<br /> <br /> <br /> <br /> <br />
								<div class="row text-center">
									<div class="col-md-4 col-md-offset-2">
										<button type="button"
											class="btn btn-success btn-primary btn-lg" id="exit">退出登录</button>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="upps">
								<br /> <br />
								<div class="row">
									<div class="form-group row">
										<label for="oldpwd" class="col-sm-1 col-md-offset-2 control-label">旧密码</label>
										<div class="col-sm-7">
											<input type="password" class="form-control" id="oldpwd"
												placeholder="旧密码" /> <span class="help-block"></span>
										</div>
									</div>
									<div class="form-group row">
										<label for="newpwd" class="col-sm-1 col-md-offset-2 control-label">新密码</label>
										<div class="col-sm-7 ">
											<input type="password" class="form-control" id="newpwd"
												placeholder="新密码" /> <span class="help-block"></span>
										</div>
									</div>
									<form>
										<div class="form-group row">
											<label for="newpwd1" class="col-sm-1 col-md-offset-2 control-label">新密码</label>
											<div class="col-sm-7">
												<input type="password" class="form-control" id="newpwd1"
													name="password" placeholder="新密码" /> <span
													class="help-block"></span>
											</div>
										</div>
									</form>

									<div class="form-group row">
										<div class="col-sm-1 col-sm-offset-4">
											<button type="button" class=" btn btn-success"
												id="admin_update_butn">修改</button>
										</div>
									
									</div>
								</div>
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
			var aid, pwd;
			$(function() {
				aid = ${sessionScope.LOGIN_ADMIN.id};
				getAdmin();
			});
			
			//提交数据 修改密码
			//校验表单数据
			$("#admin_update_butn").click(function() {
				var oldpwd = $("#oldpwd").val();
				var pass1 = $("#newpwd").val();
				var pass2 = $("#newpwd1").val();
				//验证
				// 发送ajax请求，保存更新
				if(oldpwd == ''){
					show_validate_msg("#oldpwd", "error", "密码为空");
					$("#admin_save_butn").attr("ajax-va", "error");
					return false;
				}
				if(pass1 == ''){
					show_validate_msg("#newpwd", "error", "密码为空");
					$("#admin_save_butn").attr("ajax-va", "error");
					return false;
				} 
				if(newpwd1 == ''){
					show_validate_msg("#newpwd1", "error", "密码为空");
					$("#admin_save_butn").attr("ajax-va", "error");
					return false;
				} 
				if (pass1 != pass2 ) {
					
					show_validate_msg("#newpwd1", "error", "请输入相同的密码");
					$("#admin_save_butn").attr("ajax-va", "error");
					return false;
					
				}
				
					/* validate_add_form(pass2); */
				var regName = /(^[a-zA-Z0-9_-]{6,16}$)/
				if (!regName.test(pass1)) {
					//alert("6-16位英文数字的组合");
					show_validate_msg("#newpwd", "error", "密码可以是6-16位英文数字的组合");
					$("#admin_save_butn").attr("ajax-va", "error");
					return false;
				}
				
				if(oldpwd != pwd){
					show_validate_msg("#oldpwd", "error", "密码错误");
					$("#admin_save_butn").attr("ajax-va", "error");
					return false;
				}
				
				if($(this).attr("ajax-va") == "error") {
					return false;
				}
					
					$.ajax({

						url : "${APP_PATH}/admin/" + aid,
						type : "PUT",
						data : $("#upps form").serialize(),

						success : function(result) {
							alert(result.msg);

							//关闭对话框

							//回到本页面
							window.location.href = "${APP_PATH}/index.jsp";
						}
					});
			});
			
			//旧密码判断
			$("#oldpwd").change(function() {

				var oldpwd = $("#oldpwd").val();
				if (pwd != oldpwd) {
					show_validate_msg("#oldpwd", "error", "密码错误");
					$("#admin_save_butn").attr("ajax-vaa", "aerror");
				} else {
					show_validate_msg("#oldpwd", "success", "密码正确");
					$("#admin_save_butn").attr("ajax-vaa", "asuccess");
				}
			});

			//新密码判断 
			$("#newpwd").change(function() {
				var pass = $("#newpwd").val();
				validate_add_form(pass);
			});
			
			//判断新密码符合规则
			function validate_add_form(pass) {
				//拿到要校验的数据，使用正则表达式
				var regName = /(^[a-zA-Z0-9_-]{6,16}$)/
				if (!regName.test(pass)) {
					//alert("6-16位英文数字的组合");
					show_validate_msg("#newpwd", "error", "密码可以是6-16位英文数字的组合");
					$("#admin_save_butn").attr("ajax-va", "error");
					return false;
				} else {
					show_validate_msg("#newpwd", "success", "格式正确");
					$("#admin_save_butn").attr("ajax-va", "success");
					return true;
				}
			}

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

		

			//取出旧密码
			function getAdmin() {
				$.ajax({
					url : "${APP_PATH}/admin/" + aid,
					type : "GET",
					success : function(result) {
						console.log(result);
						var adminData = result.extend.admin;
						pwd = adminData.password;
					}
				});
			}
			
			$("#exit").click(function(){
				
				window.location.href = "${APP_PATH}/index.jsp";
			});
			
		</script>
		<script type="text/javascript">
	      if($.trim("${sessionScope.LOGIN_ADMIN}") == "")
	      {
	            top.location.href="${APP_PATH}/index.jsp";
	      }
		</script>
	</body>
</html>