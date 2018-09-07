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
	<title>adminList page</title>

	<%
		pageContext.setAttribute("APP_PATH", request.getContextPath());
	%>
	<!-- web路径
		不以/开始的相对路径，找资源，以当前资源为基准，经常容易出错
		以/开始的相对路径，找资源，以服务器的路径为基准(http://localhost:3306); 需要加上项目名
		http://localhost:3306/crud

-->

	<!-- Bootstrap -->
	<link href="${APP_PATH }/static/Bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
	<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
	<!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  	<![endif]-->
  	
</head>

<body>
	
	<!-- Modal 管理员修改 -->
	<div class="modal fade" id="houseUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
					<h4 class="modal-title">修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="houseNum_update_static" class="col-sm-2 control-label">门牌号</label>
							<div class="col-sm-8 ">
								 <p class="form-control-static" id="houseNum_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label for="update_house_dep" class="col-sm-2 control-label">楼号</label>
							<div class="col-sm-8">
								<input type="text" name="dep" class="form-control" id="update_house_dep" placeholder="楼号"><span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="update_house_type" class="col-sm-2 control-label">类型</label>
							<div class="col-sm-8">
								<input type="text" name="type" class="form-control" id="update_house_type" placeholder="类型"><span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="update_house_area" class="col-sm-2 control-label">地区</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="area" id="update_house_area" placeholder="地区">
							</div>
						</div>
						<div class="form-group">
							<label for="update_house_sell" class="col-sm-2 control-label">出售状态</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="sell" id="update_house_sell" placeholder="出售状态">
							</div>
						</div>
						<div class="form-group">
							<label for="update_house_unit" class="col-sm-2 control-label">单元</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="unit" id="update_house_unit" placeholder="单元">
							</div>
						</div>
						<div class="form-group">
							<label for="update_house_floor" class="col-sm-2 control-label">楼层</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="floor" id="update_house_floor" placeholder="楼层">
							</div>
						</div>
						<div class="form-group">
							<label for="update_house_direction" class="col-sm-2 control-label">朝向</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="direction" id="update_house_direction" placeholder="朝向">
							</div>
						</div>
						<div class="form-group">
							<label for="update_house_ownerid" class="col-sm-2 control-label">业主编号</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="ownerid" id="update_house_ownerid" placeholder="业主编号">
							</div>
						</div>
						<div class="form-group">
							<label for="update_house_memo" class="col-sm-2 control-label">备注</label>
							<div class="col-sm-8">
								<textarea class="form-control" name="memo" id="update_house_memo" placeholder="..."></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="house_update_butn">更新</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal 管理员添加 -->
	<div class="modal fade" id="houseAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
					<h4 class="modal-title" id="myModalLabel">添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="houseNum_add_input" class="col-sm-2 control-label">门牌号</label>
							<div class="col-sm-8 ">
								<input type="text" class="form-control" name="num" id="houseNum_add_input" placeholder="门牌号"><span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="house_dep" class="col-sm-2 control-label">楼号</label>
							<div class="col-sm-8">
								<input type="text" name="dep" class="form-control" id="house_dep" placeholder="楼号"><span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="house_type" class="col-sm-2 control-label">类型</label>
							<div class="col-sm-8">
								<input type="text" name="type" class="form-control" id="house_type" placeholder="类型"><span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="house_area" class="col-sm-2 control-label">地区</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="area" id="house_area" placeholder="地区">
							</div>
						</div>
						<div class="form-group">
							<label for="house_sell" class="col-sm-2 control-label">出售状态</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="sell" id="house_sell" placeholder="出售状态">
							</div>
						</div>
						<div class="form-group">
							<label for="adminPhone" class="col-sm-2 control-label">单元</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="unit" id="house_unit" placeholder="单元">
							</div>
						</div>
						<div class="form-group">
							<label for="house_floor" class="col-sm-2 control-label">楼层</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="floor" id="house_floor" placeholder="楼层">
							</div>
						</div>
						<div class="form-group">
							<label for="house_direction" class="col-sm-2 control-label">朝向</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="direction" id="house_direction" placeholder="朝向">
							</div>
						</div>
						<div class="form-group">
							<label for="house_ownerid" class="col-sm-2 control-label">业主编号</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="ownerid" id="house_ownerid" placeholder="业主编号">
							</div>
						</div>
						<div class="form-group">
							<label for="house_memo" class="col-sm-2 control-label">备注</label>
							<div class="col-sm-8">
								<textarea class="form-control" name="memo" id="house_memo" placeholder="..."></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="house_save_butn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<br/>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="house_table">
					<thead>
						<tr>
							<th>报修号</th>
							<th>房门号</th>
							<th>报修物品</th>
							<th>报修时间</th>
							<th>处理状态</th>
						</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">

			<!-- 分页文字信息 -->
			<div class="col-md-6" id="page_info_area"></div>

			<!-- 分页条信息 -->
			<div class="col-md-5 col-md-offset-1" id="page_nav_area"></div>
		</div>
	</div>
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="${APP_PATH }/static/js/jquery-3.3.1.js"></script>
	<script src="${APP_PATH }/static/Bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
		var totalRecord,currentPag;
		//1.页面加载完成以后，直接发送一个ajax请求，要到分页数据
		$(function() {
			//去首页
			var id = ${sessionScope.LOGIN_CUSTOM.id};
			console.log(id);
			
			$.ajax({
				url: "${APP_PATH }/customss/" + id,
				data: "pn="+"1",
				type: "GET",
				success: function(result) {
					var adms = result.extend.pageInfo.list;
					
					$.each(adms, function(index, item) {
						var oid = item.ownerid;	
						$.ajax({
							url:"${APP_PATH}/hous/" +oid,
							type:"GET", 
							success:function(result) {
								console.log(result.extend.house);
								houseData = result.extend.house;
								console.log(houseData[0].num);
								var homesbumber = houseData[0].num;
								
								to_page(1,homesbumber);
								
								function to_page(pn,homesbumber) {
									$.ajax({
										url: "${APP_PATH }/maintains/"+ homesbumber,
										data: "pn=" + pn,
										type: "GET",
										success: function(result) {
											console.log(result.extend.pageInfo.list);
											//1.解析并显示员工数据
											build_main_table(result);
											//2.解析分页信息数据
											build_page_info(result);
											//解析分页条数据
											build_page_nav(result);
										}
									});
								}

								function build_main_table(result) {
									//清空table表
									$("#house_table tbody").empty();

									var adms = result.extend.pageInfo.list;
									$.each(adms, function(index, item) {
										
										
										var maintainId = $("<td></td>").append(item.id);
										var maintainThing = $("<td></td>").append(item.thing);
										var maintainHomesbumber = $("<td></td>").append(item.homesbumber);
										
										var time = Number(item.sdate);
										var transTime = new Date(time);
										var stime = transTime.toLocaleDateString();
										var maintainRdate = $("<td></td>").append(stime);
										
										var maintainStatus = $("<td></td>").append(item.status);
										$("<tr></tr>").append(maintainId)
										.append(maintainHomesbumber)
										.append(maintainThing)
										.append(maintainRdate)
										.append(maintainStatus)
										.appendTo("#house_table tbody");
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
							}
						});
					});
				}
			});	
			
		});
					
					
		/* function to_page(pn) {
			$.ajax({
				url: "${APP_PATH }/maintains/"+ nid,
				type: "GET",
				success: function(result) {
					//console.log(result);
					//1.解析并显示员工数据
					build_main_table(result);
					//2.解析分页信息数据
					build_page_info(result);
					//解析分页条数据
					build_page_nav(result);
				}
			});
		}

		function build_main_table(result) {
			//清空table表
			$("#house_table tbody").empty();

			var adms = result.extend.pageInfo.list;
			$.each(adms, function(index, item) {
				
				
				var maintainId = $("<td></td>").append(item.id);
				var maintainThing = $("<td></td>").append(item.thing);
				var maintainHomesbumber = $("<td></td>").append(item.homesbumber);
				
				var time = Number(item.sdate);
				var transTime = new Date(time);
				var stime = transTime.toLocaleDateString();
				var maintainRdate = $("<td></td>").append(stime);
				
				var maintainStatus = $("<td></td>").append(item.status);
				$("<tr></tr>").append(maintainId)
				.append(maintainThing)
				.append(maintainHomesbumber)
				.append(maintainRdate)
				.append(maintainStatus)
				.appendTo("#house_table tbody");
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
		} */
	
	</script>
</body>

</html>