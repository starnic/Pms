<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<head>
<meta charset="utf-8">
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
			<h2 style="color: whitesmoke">物业管理</h2>
		</nav>
		<br />
		<div class="row">
			<!-- Nav tabs -->
			<div class="col-xs-3">
				<ul class="nav nav-pills nav-stacked list-group" role="tablist">
					<li role="presentation" class="active"><a href="#admin"
						aria-controls="admin" role="tab" data-toggle="tab">管理员信息管理</a></li>
					<li role="presentation"><a href="#pm" aria-controls="pm"
						role="tab" data-toggle="tab">物业管理</a></li>
					<li role="presentation"><a href="#twm" aria-controls="twm"
						role="tab" data-toggle="tab">报修管理</a></li>
					<li role="presentation"><a href="#dg" aria-controls="dg"
						role="tab" data-toggle="tab">保卫绿化</a></li>
					<li role="presentation"><a href="#announcement"
						aria-controls="announcement" role="tab" data-toggle="tab">公告管理</a>
					</li>
					<li role="presentation"><a href="#system"
						aria-controls="system" role="tab" data-toggle="tab">系统功能</a></li>
				</ul>
			</div>

			<div class="col-xs-9">
				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="admin">
						<!--管理员信息栏-->
						<div>
							<!-- Nav tabs -->
							<ul class="nav nav-pills nav-justified" role="tablist">
								<li role="presentation" class="active"><a href="#adminlist"
									aria-controls="adminlist" role="tab" data-toggle="tab">管理员信息</a>
								</li>
								<li role="presentation"><a href="#sele"
									aria-controls="sele" role="tab" data-toggle="tab">查询管理员信息</a></li>
								<li role="presentation"><a href="#add" aria-controls="add"
									role="tab" data-toggle="tab">增加管理员</a></li>
							</ul>
							<!-- Tab panes -->
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="adminlist">
									<br />
									<div class="row">
										<div class="col-md-6 col-md-offset-6">
											<form class="navbar-form navbar-left">
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="输入管理员信息">
												</div>
												<button type="submit" class="btn btn-default">
													<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
													搜索
												</button>
											</form>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<table class="table table-hover">
												<tr>
													<th>编号</th>
													<th>姓名</th>
													<th>性别</th>
													<th>年龄</th>
													<th>电话</th>
													<th>地址</th>
													<th>操作</th>
												</tr>

												<c:forEach items="${pageInfo.list }" var="adm">
													<tr>
														<th>${adm.getId() }</th>
														<th>${adm.getName() }</th>
														<th>${adm.getGender() }</th>
														<th>${adm.getAge() }</th>
														<th>${adm.getTel() }</th>
														<th>${adm.getAddr() }</th>
														<th>
															<button class="btn btn-primary btn-sm">
																<span class=" glyphicon glyphicon-pencil"
																	aria-hidden="true"></span> 编辑
															</button>
															<button class="btn btn-danger btn-sm">
																<span class=" glyphicon glyphicon-trash"
																	aria-hidden="true"></span> 删除
															</button>
														</th>
													</tr>
												</c:forEach>
											</table>
										</div>
									</div>
									<!-- 显示分页信息 -->
									<div class="row">
										<!-- 分页文字信息 -->
										<div class="col-md-6">当前${pageInfo.pageNum }页,总${pageInfo.pages }页
											,总${pageInfo.total }条记录</div>
										<!-- 分页条信息 -->
										<div class="col-md-6">
											<nav aria-label="Page navigation">
												<ul class="pagination">
													<li><a href="${APP_PATH }/admin?pn=1">首页</a></li>
													<c:if test="${pageInfo.hasPreviousPage }">
														<li><a
															href="${APP_PATH }/admin?pn=${pageInfo.pageNum-1 }"
															aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
														</a></li>
													</c:if>
													<c:forEach items="${pageInfo.navigatepageNums }"
														var="page_Num">
														<c:if test="${page_Num == pageInfo.pageNum }">
															<li class="active"><a href="#">${page_Num }</a></li>
														</c:if>
														<c:if test="${page_Num != pageInfo.pageNum }">
															<li><a href="${APP_PATH }/admin?pn=${page_Num }">${page_Num }</a></li>
														</c:if>
													</c:forEach>
													<c:if test="${pageInfo.hasNextPage }">
														<li><a href="${APP_PATH }/admin?pn=${pageInfo.pageNum+1}" aria-label="Next"> <span
																aria-hidden="true">&raquo;</span>
														</a></li>
													</c:if>
													<li><a href="${APP_PATH }/admin?pn=${pageInfo.pages}">末页</a></li>
												</ul>
											</nav>
										</div>
									</div>
								</div>

								<div role="tabpanel" class="tab-pane" id="sele">
									<br />
									<div class="row">
										<div class="col-md-6 col-md-offset-6">
											<form class="navbar-form navbar-left">
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="输入管理员信息">
												</div>
												<button type="submit" class="btn btn-default">搜索</button>
											</form>
										</div>
									</div>
								</div>

								<div role="tabpanel" class="tab-pane" id="add">
									<br /> <br />
									<form class="form-horizontal">
										<div class="form-group">
											<label for="newpasswd" class="col-sm-2 control-label">姓名</label>
											<div class="col-sm-7 ">
												<input type="text" class="form-control" id="newpasswd"
													placeholder="姓名">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">密码</label>
											<div class="col-sm-7">
												<input type="password" class="form-control" id="newpasswd1"
													placeholder="密码">
											</div>
										</div>
										<div class="form-group">
											<label for="num" class="col-sm-2 control-label">性别</label>
											<div class="col-sm-7">
												<label class="radio-inline"> <input type="radio"
													name="inlineRadioOptions" id="inlineRadio1" value="option1">
														男 </label> <label class="radio-inline"> <input
													type="radio" name="inlineRadioOptions" id="inlineRadio2"
													value="option2"> 女 </label>
											</div>
										</div>

										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">年龄</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="年龄">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">电话</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="电话">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">手机</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="手机">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">地址</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="地址">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">备注</label>
											<div class="col-sm-7">
												<textarea class="form-control" id="newpasswd1"
													placeholder="..."></textarea>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2">
												<button type="submit" class="btn btn-default">提交</button>
												&ensp;
												<button type="reset" class="btn btn-default">重置</button>
											</div>

										</div>
									</form>
								</div>
							</div>

						</div>

					</div>
					<div role="tabpanel" class="tab-pane" id="pm">
						<!--物业管理栏-->
						<div>
							<!-- Nav tabs -->
							<ul class="nav nav-pills nav-justified" role="tablist">
								<li role="presentation" class="active"><a href="#house"
									aria-controls="house" role="tab" data-toggle="tab">房产信息</a></li>
								<li role="presentation"><a href="#owner"
									aria-controls="owner" role="tab" data-toggle="tab">业主信息</a></li>
							</ul>
							<!-- Tab panes -->
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="house">
									<br />
									<div class="row">
										<div class="col-md-6 col-md-offset-6">
											<form class="navbar-form navbar-left">
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="输入房产信息">
												</div>
												<button type="submit" class="btn btn-default">搜索</button>
											</form>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<table class="table table-hover">
												<tr>
													<th>编号</th>
													<th>门牌号</th>
													<th>楼号</th>
													<th>类型</th>
													<th>地区</th>
													<th>出售状况</th>
													<th>单元</th>
													<th>楼层</th>
													<th>朝向</th>
													<th>备注</th>
													<th>业主编号</th>
													<th>操作</th>
												</tr>
												
												<tr>
													<th>#</th>
													<th>empName</th>
													<th>gender</th>
													<th>email</th>
													<th>deptName</th>
													<th>操作</th>
													<th>#</th>
													<th>empName</th>
													<th>gender</th>
													<th>修改</th>
													<th>修改</th>
												</tr>
												<tr>
													<th>#</th>
													<th>empName</th>
													<th>gender</th>
													<th>email</th>
													<th>deptName</th>
													<th>操作</th>
													<th>#</th>
													<th>empName</th>
													<th>gender</th>
													<th>email</th>
													<th>修改</th>
												</tr>
												<tr>
													<th>#</th>
													<th>empName</th>
													<th>gender</th>
													<th>email</th>
													<th>deptName</th>
													<th>操作</th>
													<th>#</th>
													<th>empName</th>
													<th>gender</th>
													<th>email</th>
													<th>修改</th>
												</tr>
												<tr>
													<th>#</th>
													<th>empName</th>
													<th>gender</th>
													<th>email</th>
													<th>deptName</th>
													<th>操作</th>
													<th>#</th>
													<th>empName</th>
													<th>gender</th>
													<th>email</th>
													<th>修改</th>
												</tr>
											</table>
										</div>
									</div>
								</div>

								<div role="tabpanel" class="tab-pane" id="owner">
									<br />
									<div class="row">
										<div class="col-md-6 col-md-offset-6">
											<form class="navbar-form navbar-left">
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="输入业主信息">
												</div>
												<button type="submit" class="btn btn-default">搜索</button>
											</form>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<table class="table table-hover">
												<tr>
													<th>账户编号</th>
													<th>用户名</th>
													<th>业主编号</th>
													<th>车牌号</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>账户编号</th>
													<th>用户名</th>
													<th>业主编号</th>
													<th>车牌号</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>账户编号</th>
													<th>用户名</th>
													<th>业主编号</th>
													<th>车牌号</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>账户编号</th>
													<th>用户名</th>
													<th>业主编号</th>
													<th>车牌号</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>账户编号</th>
													<th>用户名</th>
													<th>业主编号</th>
													<th>车牌号</th>
													<th>操作</th>
												</tr>

											</table>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="twm">
						<!--报修管理-->
						<div>
							<!-- Nav tabs -->
							<ul class="nav nav-pills nav-justified" role="tablist">
								<li role="presentation" class="active"><a href="#tregister"
									aria-controls="tregister" role="tab" data-toggle="tab">报修登记</a>
								</li>
								<li role="presentation"><a href="#tsele"
									aria-controls="tsele" role="tab" data-toggle="tab">报修查询</a></li>
								<li role="presentation"><a href="#tplan"
									aria-controls="tplan" role="tab" data-toggle="tab">安排维修</a></li>
								<li role="presentation"><a href="#tresult"
									aria-controls="tresult" role="tab" data-toggle="tab">维修结果</a></li>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content ">
								<div role="tabpanel" class="tab-pane active" id="tregister">
									<br /> <br />
									<form class="form-horizontal">
										<div class="form-group">
											<label for="newpasswd" class="col-sm-2 control-label">报修物品</label>
											<div class="col-sm-7 ">
												<input type="text" class="form-control" id="newpasswd"
													placeholder="报修物品">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">保修单状态</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="保修单状态">
											</div>
										</div>

										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">房门号</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="房门号">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">报修时间</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="报修时间">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">维修时间</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="维修时间">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">预计花费</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="预计花费">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">实际花费</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="实际花费">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">报修人</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="newpasswd1"
													placeholder="报修人">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">报修详情</label>
											<div class="col-sm-7">
												<textarea class="form-control" id="newpasswd1"
													placeholder="..."></textarea>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2">
												<button type="submit" class="btn btn-default">提交</button>
												&ensp;
												<button type="reset" class="btn btn-default">重置</button>
											</div>

										</div>
									</form>
								</div>
								<div role="tabpanel" class="tab-pane" id="tsele">
									<br />
									<div class="row">
										<div class="col-md-6 col-md-offset-6">
											<form class="navbar-form navbar-left">
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="输入报修单号">
												</div>
												<button type="submit" class="btn btn-default">搜索</button>
											</form>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane" id="tplan">...</div>
								<div role="tabpanel" class="tab-pane" id="tresult">...</div>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="dg">
						<!--保卫绿化-->
						<div>
							<!-- Nav tabs -->
							<ul class="nav nav-pills nav-justified" role="tablist">
								<li role="presentation" class="active"><a href="#patrol"
									aria-controls="patrol" role="tab" data-toggle="tab">巡查表信息</a></li>
								<li role="presentation"><a href="#sgc" aria-controls="sgc"
									role="tab" data-toggle="tab">保安/保洁员信息</a></li>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="patrol">
									<br />
									<div class="row">
										<div class="col-md-8 col-md-offset-3">
											<form class="navbar-form navbar-left">
												&ensp;&ensp;&ensp;&ensp;&ensp;
												<button type="button" class="btn btn-primary">新增</button>
												&ensp;
												<button type="button" class="btn btn-danger">删除</button>
												&ensp;&ensp;&ensp;
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="输入巡查信息">
												</div>
												<button type="submit" class="btn btn-default">搜索</button>
											</form>
										</div>
									</div>
									<br />
									<div class="row">
										<div class="col-md-12">
											<table class="table table-hover">
												<tr>
													<th>编号</th>
													<th>巡查人</th>
													<th>类型</th>
													<th>时间</th>
													<th>处理人</th>
													<th>当事人</th>
													<th>巡查结果</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>编号</th>
													<th>巡查人</th>
													<th>类型</th>
													<th>时间</th>
													<th>处理人</th>
													<th>当事人</th>
													<th>巡查结果</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>编号</th>
													<th>巡查人</th>
													<th>类型</th>
													<th>时间</th>
													<th>处理人</th>
													<th>当事人</th>
													<th>巡查结果</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>编号</th>
													<th>巡查人</th>
													<th>类型</th>
													<th>时间</th>
													<th>处理人</th>
													<th>当事人</th>
													<th>巡查结果</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>编号</th>
													<th>巡查人</th>
													<th>类型</th>
													<th>时间</th>
													<th>处理人</th>
													<th>当事人</th>
													<th>巡查结果</th>
													<th>操作</th>
												</tr>

											</table>
										</div>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="sgc">
									<br />
									<div class="row">
										<div class="col-md-8 col-md-offset-3">
											<form class="navbar-form navbar-left">
												&ensp;&ensp;&ensp;&ensp;&ensp;
												<button type="button" class="btn btn-primary">新增</button>
												&ensp;
												<button type="button" class="btn btn-danger">删除</button>
												&ensp;&ensp;&ensp;
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="输入保安/保洁员信息">
												</div>
												<button type="submit" class="btn btn-default">搜索</button>
											</form>
										</div>
									</div>
									<br />
									<div class="row">
										<div class="col-md-12">
											<table class="table table-hover">
												<tr>
													<th>编号</th>
													<th>姓名</th>
													<th>性别</th>
													<th>年龄</th>
													<th>类型</th>
													<th>电话</th>
													<th>地址</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>编号</th>
													<th>巡查人</th>
													<th>类型</th>
													<th>时间</th>
													<th>处理人</th>
													<th>当事人</th>
													<th>巡查结果</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>编号</th>
													<th>巡查人</th>
													<th>类型</th>
													<th>时间</th>
													<th>处理人</th>
													<th>当事人</th>
													<th>巡查结果</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>编号</th>
													<th>巡查人</th>
													<th>类型</th>
													<th>时间</th>
													<th>处理人</th>
													<th>当事人</th>
													<th>巡查结果</th>
													<th>操作</th>
												</tr>
												<tr>
													<th>编号</th>
													<th>巡查人</th>
													<th>类型</th>
													<th>时间</th>
													<th>处理人</th>
													<th>当事人</th>
													<th>巡查结果</th>
													<th>操作</th>
												</tr>

											</table>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

					<div role="tabpanel" class="tab-pane" id="announcement">
						<!--公告管理-->
						<div>

							<!-- Nav tabs -->
							<ul class="nav nav-pills nav-justified" role="tablist">
								<li role="presentation" class="active"><a href="#annlist"
									aria-controls="annlist" role="tab" data-toggle="tab">公告列表</a></li>
								<li role="presentation"><a href="#annadd"
									aria-controls="annadd" role="tab" data-toggle="tab">新增公告</a></li>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="annlist">
									<br />
									<div class="row">
										<div class="col-md-6 col-md-offset-6">
											<form class="navbar-form navbar-left">
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="输入公告信息">
												</div>
												<button type="submit" class="btn btn-default">搜索</button>
											</form>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12">
											<ul>
												<li>1</li>
												<li>2</li>
												<li>3</li>
												<li>4</li>
												<li>5</li>
												<li>6</li>
												<li>7</li>
												<li>8</li>
												<li>9</li>
												<li>10</li>
											</ul>
										</div>
									</div>

								</div>
								<div role="tabpanel" class="tab-pane" id="annadd">
									<br /> <br />
									<form class="form-horizontal">
										<div class="form-group">
											<label for="oldpasswd" class="col-sm-2 control-label">公告主题</label>
											<div class="col-sm-7">
												<input type="email" class="form-control" id="oldpasswd"
													placeholder="公告主题">
											</div>
										</div>
										<div class="form-group">
											<label for="oldpasswd" class="col-sm-2 control-label">时间</label>
											<div class="col-sm-7">
												<input type="time" class="form-control" id="oldpasswd">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">公告内容</label>
											<div class="col-sm-7">
												<textarea class="form-control" id="newpasswd1"
													placeholder="..."></textarea>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2">
												<button type="submit" class="btn btn-default">发布</button>
												&ensp;
												<button type="reset" class="btn btn-default">重置</button>
											</div>

										</div>
									</form>
								</div>
							</div>

						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="system">
						<div>

							<!-- Nav tabs -->
							<ul class="nav nav-pills nav-justified" role="tablist">
								<li role="presentation" class="active"><a href="#quit"
									aria-controls="quit" role="tab" data-toggle="tab">退出</a></li>
								<li role="presentation"><a href="#upps"
									aria-controls="upps" role="tab" data-toggle="tab">修改密码</a></li>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="quit">
									<br /> <br /> <br /> <br /> <br />
									<div class="row text-center">
										<div class="col-md-4 col-md-offset-1">
											<button type="button"
												class="btn btn-success btn-primary btn-lg">退出登录</button>
										</div>
										<div class="col-md-4">
											<button type="button" class="btn btn-info btn-primary btn-lg">重新登录</button>
										</div>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane" id="upps">
									<br /> <br />
									<form class="form-horizontal">
										<div class="form-group">
											<label for="oldpasswd" class="col-sm-2 control-label">旧密码</label>
											<div class="col-sm-7">
												<input type="email" class="form-control" id="oldpasswd"
													placeholder="旧密码">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd" class="col-sm-2 control-label">新密码</label>
											<div class="col-sm-7 ">
												<input type="password" class="form-control" id="newpasswd"
													placeholder="新密码">
											</div>
										</div>
										<div class="form-group">
											<label for="newpasswd1" class="col-sm-2 control-label">新密码</label>
											<div class="col-sm-7">
												<input type="password" class="form-control" id="newpasswd1"
													placeholder="新密码">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2">
												<button type="submit" class="btn btn-default">修改</button>
												&ensp;
												<button type="reset" class="btn btn-default">重置</button>
											</div>

										</div>
									</form>
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
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="${APP_PATH }/static/Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>