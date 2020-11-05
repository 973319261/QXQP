<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cxt" scope="page"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width" />
<title>登录界面</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no">
<link href="Content/Main/css/style.css" rel="stylesheet" />
<link href="Content/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="Content/bootstrap-3.3.7-dist/iconfont/style.css"
	rel="stylesheet" />
<style>
body {
	color: #fff;
	font-family: "微软雅黑";
	font-size: 14px;
}

.wrap1 {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin: auto
}

.main_content {
	width: 450px;
	background: rgba(255, 255, 255, .6);
	margin-left: auto;
	margin-right: auto;
	text-align: left;
	float: none;
	border-radius: 8px;
	padding: 30px
}

.form-group {
	position: relative;
}

.login_btn {
	display: block;
	background: #3872f6;
	color: #fff;
	font-size: 15px;
	width: 100%;
	line-height: 50px;
	border-radius: 3px;
	border: none;
}

.login_input {
	width: 100%;
	border: 1px solid #3872f6;
	border-radius: 3px;
	line-height: 40px;
	padding: 2px 5px 2px 30px;
	background: none;
}

.icon_font {
	position: absolute;
	bottom: 15px;
	left: 10px;
	font-size: 18px;
	color: #3872f6;
}

.font16 {
	font-size: 16px;
}

.mg-t20 {
	margin-top: 20px;
}

#grad {
	background: -webkit-linear-gradient(#4990c1, #52a3d2, #6186a3);
	background: -o-linear-gradient(#4990c1, #52a3d2, #6186a3);
	/* Opera 11.1 - 12.0 */
	background: -moz-linear-gradient(#4990c1, #52a3d2, #6186a3);
	/* Firefox 3.6 - 15 */
	background: linear-gradient(#4990c1, #52a3d2, #6186a3);
}

#ValidateCode {
	cursor: pointer;
	font-size: 18px;
	width: 100px;
	line-height: 45px;
	position: absolute;
	top: 0px;
	right: 0px;
	background: #52a3d2;
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
	text-align: center;
}
</style>
</head>
<body
	style="background:url('Content/Main/images/preview-1.jpg');background-attachment:fixed!important;background-size:100% 100% !important">
	<div class="wel" id="box">
		<div class="box-1 lefp"></div>
		<div class="box-1">
			<div class="righp"></div>
		</div>
	</div>
	<!--荧光点点-->
	<div class="wel" id="git"></div>
	<div class="container wrap1" style="height:450px;">
		<h2 class="mg-b20 text-center">智 百 盛 汽 修 汽 配 登 录</h2>
		<div
			class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content flrg">
			<p class="text-center font16" style="color:chocolate;font-size:16px">用
				户 登 录</p>
			<form id="fmLogin" onkeydown="onreturn()">
				<div class="form-group mg-t20">
					<i class="icon-user icon_font"></i> <input style="color: black;"
						type="email" class="login_input" id="UserNum" placeholder="请输入用户名"
						name="UserNum" value="" />
				</div>
				<div class="form-group mg-t20">
					<i class="icon-lock icon_font"></i> <input style="color: black;"
						type="password" class="login_input" id="Password"
						placeholder="请输入密码" name="Password" value="" />
				</div>
				<div class="form-group mg-t20">
					<i class="icon-ydlist icon_font"></i> <input
						style="color: black;" type="password" class="login_input" id="Validate"
						placeholder="请输入验证码" name="validateCode" />
					<div class="value" id="ValidateCode" style=""></div>
				</div>

				<div class="checkbox mg-b25">
					<label> <input type="checkbox" id="RememberMe" value="true"
						@(ViewBag.isRember ? "checked=\
						"checked\"" : "")  name="RememberMe" /> 记住我的登录信息 </label>
				</div>
				<button type="button" class="login_btn" id="btnSubmit">登 录</button>
			</form>
		</div>
		<!--row end-->
	</div>
	<!--container end-->
	<script src="Content/bootstrap-3.3.7-dist/js/jquery-3.3.1.js"></script>
	<script src="Content/layer/layer.js"></script>

	<script type="text/javascript">
	
		$(function() {
		//嵌套登陆问题
	  if (top != window){
	   top.location.href = window.location.href;  
	  }     
			ValidateCode()
		})
		//回车登录
		function onreturn() {
			if (window.event.keyCode == 13) {
				if (document.all('btnSubmit').click());
			}
		}
		//点击图片刷新验证码
		$("#ValidateCode").click(function() {
			ValidateCode();
		});
		//验证码事件
		function ValidateCode() {
			  $.post("${cxt}/userController/validateCode.action", function (data) {
				  console.log("-----"+data)
			     $("#ValidateCode").text(data);
			  })
		}
		//验证登录
		$("#btnSubmit").click(function() {
			var dataform = $("#fmLogin").serializeArray();//序列化表单
			$.post("${cxt}/userController/login.action", dataform, function(data) {
				console.log(data)
				if (data == "seccess") {
                    var index = layer.load(1, {
                        shade: [0.1, '#fff'] //0.1透明度的白色背景
                    });
                    window.location.href = "${cxt}/jsp/main.jsp"
                } else if (data == "该用户无法登陆！")
                {
                    layer.tips(data, '#btnSubmit', {
                        tips: [1, '#3595CC'],
                        time: 4000
                    });
                }
                else if (data == "对不起，输入密码错误！") {
                    layer.tips(data, '#Password', {
                        tips: [1, '#3595CC'],
                        time: 4000
                    });
                }
                else if (data == "对不起，用户名不存在") {
                    layer.tips(data, '#UserNum', {
                        tips: [1, '#3595CC'],
                        time: 4000
                    });

                }
                else if (data == "对不起，您输入的验证码有误！") {
                    layer.tips(data, '#Validate', {
                        tips: [1, '#3595CC'],
                        time: 4000
                    });
                }else if (data == "请输入验证码！") {
                    layer.tips(data, '#Validate', {
                        tips: [1, '#3595CC'],
                        time: 4000
                    });
                }
            })
		});
	</script>
</body>
</html>