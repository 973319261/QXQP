<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cxt" scope="page"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width" />
    <title>Main</title>
    <link href="Content/Main/static/h-ui/css/H-ui.min.css" rel="stylesheet" />
    <link href="Content/Main/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" />
    <link href="Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" />
    <link href="Content/Main/static/h-ui.admin/skin/default/skin.css" rel="stylesheet" />
    <link href="Content/Main/static/h-ui.admin/css/style.css" rel="stylesheet" />
</head>
<body class="">
    <header class="navbar-wrapper">
        <div class="navbar navbar-fixed-top">
            <div class="container-fluid cl">
                <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">智百盛汽修汽配管理系统</a>
                <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a>
                <span class="logo navbar-slogan f-l mr-10 hidden-xs">v7.5</span>
                <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;"></a>      
                <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                    <ul class="cl">
                        <li>${user.getUserTypeName() } </li>
                        <li class="dropDown dropDown_hover">
                            <a href="#" class="dropDown_A"> ${user.getUserName() } <i class="Hui-iconfont"></i></a>
                            <ul class="dropDown-menu menu radius box-shadow">
                                <li class=""><a href="#">个人信息</a></li>
                                <li class=""><a href="${cxt }/jsp/login.jsp">切换账户</a></li>
                                <li class=""><a href="${cxt }/jsp/login.jsp">退出</a></li>
                            </ul>
                        </li>                                                       
                        <li id="Hui-msg"> <a href="#" title="消息"><i class="Hui-iconfont" style="font-size:18px"></i></a> </li>
                       
                        <li id="Hui-skin" class="dropDown right dropDown_hover">
                            <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px"></i></a>
                            <ul class="dropDown-menu menu radius box-shadow">
                                <li class=""><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                                <li class=""><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                                <li class=""><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                                <li class=""><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                                <li class=""><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                                <li class=""><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
    <aside class="Hui-aside">
        <div class="menu_dropdown bk_2">
            <!-- @*系统首页*@ -->
            <dl id="menu-article">
                <dt class=""><i class="Hui-iconfont">&#xe625;</i> 我的桌面</dt>
            </dl>
          
            <!-- @*汽修管理*@ -->
            <dl id="menu-article">
                <dt class=""><i class="Hui-iconfont">&#xe63c;</i> 汽修管理<i class="Hui-iconfont menu_dropdown-arrow"></i></dt>
                <dd style="display: none;">
                    <ul>
                        <li><a data-href="${cxt}/jsp/Mechanics/appointment.jsp" data-title="预约安排" href="javascript:void(0)">预约安排</a></li>
                        <li><a data-href="${cxt}/jsp/Mechanics/customer.jsp" data-title="客户接待" href="javascript:void(0)">客户接待</a></li>
                        <li><a data-href="${cxt}/jsp/Mechanics/dispatching.jsp" data-title="维修派工" href="javascript:void(0)">维修派工</a></li>
                        <li><a data-href="${cxt}/jsp/Mechanics/draftback.jsp" data-title="维修领料" href="javascript:void(0)">维修领料</a></li>
                        <li><a data-href="${cxt}/jsp/Mechanics/completion.jsp" data-title="完工确认" href="javascript:void(0)">完工确认</a></li>
                        <li><a data-href="${cxt}/jsp/Mechanics/settlement.jsp" data-title="结算出厂" href="javascript:void(0)">结算出厂</a></li>
                        <li><a data-href="${cxt}/jsp/Mechanics/claimset.jsp" data-title="三包索赔结算" href="javascript:void(0)">三包索赔结算</a></li>
                        <li><a data-href="${cxt}/jsp/Mechanics/insurance.jsp" data-title="保险理赔结算" href="javascript:void(0)">保险理赔结算</a></li>
                        <li><a data-href="${cxt}/jsp/Mechanics/rapidrepair.jsp" data-title="快速修车" href="javascript:void(0)">快速修车</a></li>
                    </ul>
                </dd>
            </dl>
        </div>
    </aside>
   <!--  @*菜单栏隐藏*@ -->
    <div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
    <section class="Hui-article-box">
        <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
            <div class="Hui-tabNav-wp">
                <ul id="min_title_list" class="acrossTab cl">
                    <li class="active">
                        <span title="我的桌面" data-href="">我的桌面</span>
                        <em></em>
                    </li>
                </ul>
            </div>
            <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
        </div>
        <div id="iframe_box" class="Hui-article">
            <div class="show_iframe">
                <div style="display:none" class="loading"></div>
                <iframe scrolling="yes" frameborder="0" src="${cxt}/jsp/Mechanics/welcome.jsp"></iframe>
            </div>
        </div>
    </section>
    <div class="contextMenu" id="Huiadminmenu">
        <ul>
            <li id="closethis">关闭当前 </li>
            <li id="closeall">关闭全部 </li>
        </ul>
    </div>
   <!--  @*外部引用*@ -->
    <script src="Content/Main/lib/jquery/1.9.1/jquery.min.js"></script>
    <script src="Content/Main/lib/layer/2.4/layer.js"></script>
    <script src="Content/Main/static/h-ui/js/H-ui.min.js"></script>
    <script src="Content/Main/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script>
        $(function () {
            var li = $(".Hui-aside li");
            $.each(li, function (i) {
                var num=i+1;
                var Id = "Q"+ num;
                $(this).attr("id", Id);
            })
            /* $.post("SelectJurisdiction?UserID=" + @ViewBag.UserID, function (data) {
                $.each(data, function (i) {
                    var Id=data[i].ModularID;
                    var Bo=data[i].ToJdtion;
                    if (Bo==false) {
                        $("#Q"+Id).remove();;
                    }
                })
            }) */
        })
    </script>
   
</body>
</html>