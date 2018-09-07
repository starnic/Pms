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
	<title>maintainList page</title>

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
	<div class="modal fade" id="maintainUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
							<label for="maintainName_update_static" class="col-sm-2 control-label">报修物品</label>
							<div class="col-sm-8 ">
								 <p class="form-control-static" id="maintainName_update_static"></p>
							</div>
						</div>

						<div class="form-group">
							<label for="maintainHomeber" class="col-sm-2 control-label">门牌号</label>
							<div class="col-sm-8">
								 <p class="form-control-static" id="maintainHomeber"></p>
							</div>
						</div>
						
						<div class="form-group">
							<label for="status" class="col-sm-2 control-label">报修状态</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="status" id="status" placeholder="保修单状态">
							</div>
						</div>
						<div class="form-group">
							<label for="rdate" class="col-sm-2 control-label">维修时间</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="rdate" id="rdate" placeholder="维修时间(年月日以斜杠隔开)">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="maintain_update_butn">更新</button>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-md-offset-6">
				<form class="navbar-form navbar-left" action="#">
					<button type="button" class="btn btn-danger" id="maintain_delete_all_btn">删除</button> &nbsp;&nbsp;&nbsp;
					<div class="form-group">
						<input style="display:none">
						<input type="text" class="form-control" placeholder="输入姓名" id="search_text">
					</div>
					<button type="button" class="btn btn-default" id="maintain_search_btn">搜索</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="adm_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>编号</th>
							<th>报修物品</th>
							<th>保修单状态</th>
							<th>房门号</th>
							<th>报修时间</th>
							<th>维修时间</th>
							<th>预计花费</th>
							<th>实际花费</th>
							<th>报修人</th>
							<th>报修详情</th>
							<th>操作</th>
							
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
			to_page(1);
		});

		function to_page(pn) {
			$.ajax({
				url: "${APP_PATH }/maintains",
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
			$("#adm_table tbody").empty();

			var maintains = result.extend.pageInfo.list;
			$.each(maintains, function(index, item) {
				
				var checkBox = $("<td><input type='checkbox' class='check_item'/></tb>");
				var maintainId = $("<td></td>").append(item.id);
				var maintainThing = $("<td></td>").append(item.thing);
				var maintainStatus = $("<td></td>").append(item.status);
				var maintainHomesbumber = $("<td></td>").append(item.homesbumber);
				
				var time = Number(item.sdate);
				var transTime = new Date(time);
				var stime = transTime.toLocaleDateString();
				var maintainSdate = $("<td></td>").append(stime);
				
				var time = Number(item.rdate);
				var transTime1 = new Date(time);
				var rtime = transTime1.toLocaleDateString();
				var maintainRdate = $("<td></td>").append(rtime);
				
				var maintainTcost = $("<td></td>").append(item.tcost);
				var maintainScost = $("<td></td>").append(item.scost);
				var maintainMaintainer = $("<td></td>").append(item.maintainer);
				var maintainSmemo = $("<td></td>").append(item.smemo);

				//构建按钮
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
					.append($("<span></span>")
					.addClass("glyphicon glyphicon-pencil")).append("编辑");
				//为编辑按钮添加一个自定义的属性，来表示当前管理员的id
				editBtn.attr("edit-id", item.id);
				
				var delBtn = $("<button></button>")
					.addClass("btn btn-danger btn-sm delete_btn")
					.append($("<span></span>")
					.addClass("glyphicon glyphicon-trash")).append("删除");
				//为删除按钮添加一个自定义的属性，来表示当前管理员的id
				delBtn.attr("del-id", item.id);
				
				var btnTd = $("<td></td>")
					.append(editBtn)
					.append(" ")
					.append(delBtn);

				$("<tr></tr>").append(checkBox)
				.append(maintainId)
				.append(maintainThing)
				.append(maintainStatus)
				.append(maintainHomesbumber)
				.append(maintainSdate)
				.append(maintainRdate)
				.append(maintainTcost)
				.append(maintainScost)
				.append(maintainMaintainer)
				.append(maintainSmemo)
				.append(btnTd)
				.appendTo("#adm_table tbody");
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

		//重置表单内容及样式
		function reset_form(ele) {
			//清除表单数据 表单重置
			$(ele)[0].reset();
			//重置表单的样式
			$(ele).find("*").removeClass("has-success has-error");
			$(ele).find(".help-block").text("");
		}

		
		//1.我们是按钮创建之前绑定了click，所有绑不上
		//1）,在创建按钮的时候绑定。 2). 绑定.live(),使用on替代
		
		//修改按钮添加点击事件
		$(document).on("click",".edit_btn",function() {
			//alert("edit");
			//查出管理员信息
			getMaintain($(this).attr("edit-id"));
			
			//把id传递给模态框的更新按钮
			$("#maintain_update_butn").attr("edit-id", $(this).attr("edit-id"));
			
			//打开模态框
			$("#maintainUpdateModal").modal({
				backdrop: "static"
			});
			
		});
		
		//信息回显
		function getMaintain(id) {
			$.ajax({
				url:"${APP_PATH}/maintain/" +id,
				type:"GET", 
				success:function(result) {
					//console.log(result);
					var maintainData = result.extend.maintain;
					$("#maintainName_update_static").text(maintainData.thing);
					$("#maintainHomeber").text(maintainData.homesbumber);
					$("#status").val(maintainData.status);
				}
			});
		}
		
		//点击更新   更新管理员
		$("#maintain_update_butn").click(function() {
			//验证
			/* alert($("#maintainUpdateModal form").serialize()); */
			// 发送ajax请求，保存更新
			$.ajax({
				url:"${APP_PATH}/maintain/"+$(this).attr("edit-id"),
				type:"PUT",
				data:$("#maintainUpdateModal form").serialize(),
				success:function(result){
					//alert(result.msg);
					
					//关闭对话框
					$("#maintainUpdateModal").modal("hide");
					
					//回到本页面
					to_page(currentPag);
				}
			});
		});
		
		//单个删除按钮添加点击事件
		$(document).on("click",".delete_btn",function(){
			//弹出是否确认删除对话框
			var maintainName = $(this).parents("tr").find("td:eq(2)").text();
			var admid = $(this).attr("del-id");
			if(confirm("确认删除【" + maintainName + "】吗？")) {
				$.ajax({
					url:"${APP_PATH}/maintain/"+admid,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						//回到本页
						to_page(currentPag);
					}
				});
			}
		});
		
		//完成全选 /全不选
		$("#check_all").click(function() {
			//attr获取checked是undefined
			//attr获取自定义属性的值
			//prop修改和读取dom原生属性的值
			$(".check_item").prop("checked", $(this).prop("checked"));
		});
		
		$(document).on("click", ".check_item",function(){
			var flag = $(".check_item:checked").length == $(".check_item").length ;
			$("#check_all").prop("checked", flag);
		});
		
		//点击全部删除，批量删除
		//maintain_delete_all_btn
		$("#maintain_delete_all_btn").click(function(){
			var maintainName = "";
			var del_idstr = "";
			$.each($(".check_item:checked"), function(){
				//组装name字符串
				maintainName += $(this).parents("tr").find("td:eq(2)").text() + ",";
				//组装id字符串
				del_idstr += $(this).parents("tr").find("td:eq(1)").text() + "-";
			});
			//去除maintainName 多余的','
			maintainName = maintainName.substring(0, maintainName.length-1);
			//去除maintainName 多余的'-'
			del_idstr = del_idstr.substring(0, del_idstr.length-1);
			
			if(confirm("确认删除【" + maintainName +"】吗？")) {
				//发送ajax请求
				$.ajax({
					url:"${APP_PATH}/maintain/"+del_idstr,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						//回到本页
						to_page(currentPag);
					}
				});
			}
		});
		
		//搜索框点击事件
		$("#maintain_search_btn").click(function(){
			var name = $("#search_text").val();
			$.ajax({
				url: "${APP_PATH }/maintains/"+ name,
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
		});
		
	</script>
</body>

</html>