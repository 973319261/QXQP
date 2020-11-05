<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cxt" value="${pageContext.request.contextPath }"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>Welcome</title>
    <link href="${cxt}/Content/Main/css/style.css" rel="stylesheet" />
</head>
<body style="background:url('${cxt}/Content/Main/images/qiche1.jpg');background-attachment:fixed!important;background-size:100% 100% !important">
    <div class="wel" id="box">
        <div class="box-1 lefp"></div>
        <div class="box-1">
            <div class="righp"></div>
        </div>
    </div>
    <!--荧光点点-->
    <div class="wel" id="git"></div>
    <p  style="line-height:650px;text-align:center;font-size:50px;font-style:unset;font-weight:100"id="blink">
        欢 迎 登 录 智 百 盛 汽 修 汽 配 管 理 系 统
    </p>
    <script language="javascript"> 
function changeColor()
{ 
    var color = "#f00|#0f0|#00f|#880|#808|chartreuse|yellow|green|deepskyblue|gray";
    color=color.split("|"); 
    document.getElementById("blink").style.color=color[parseInt(Math.random() * color.length)]; 
} 
setInterval("changeColor()",200); 
    </script>
</body>
</html>
