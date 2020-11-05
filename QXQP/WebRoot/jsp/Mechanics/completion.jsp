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
 <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width" />
    <title>Completion</title>
    <link href="${cxt}/Content/layui/css/modules/laydate/default/laydate.css" rel="stylesheet" />
    <link href="${cxt}/Content/layui/css/layui.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/css/main.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/tabnav/css/element.min.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" />
    <link href="${cxt}/Content/bootstrap-3.3.7-dist/css/bootstrapal.css" rel="stylesheet" />
   <!--  @*CSS样式(包含默认皮肤样式)*@ -->
    <link href="${cxt}/Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.css" rel="stylesheet" />
  <!--   @*CSS皮肤(需引用于bsgrid.all.min.css之后)*@ -->
    <link href="${cxt}/Content/jquery.bsgrid-1.37/css/skins/grid_gray.min.css" rel="stylesheet" />
    <style>
        .form-group {
            margin: 0px;
        }

        a {
            color: #808080;
        }

            a:hover {
                text-decoration: none;
                color: burlywood;
            }

      
        .modal .control-label {
            width: 100px;
            padding-top: 0px;
        }

        .modal .form-group {
            margin: 1px;
        }

        .modal .col-lg-3 {
            width: 16%;
        }

        .modal-body {
            padding-bottom: 0;
        }

        .modal .navsearch {
            height: 50px;
            width: 950px;
            background: #f5f5f5;
            padding: 5px 0;
            border-radius: 3px;
        }

        .navsearch input, .navsearch select {
            width: 150px;
            height: 26px;
        }

        .navsearch .col-lg-3 {
            width: 150px;
            margin-right: 80px;
        }

        .navsearch button {
        }

        .modal button {
            width: 79px;
        }

            .modal button i {
                margin-right: 5px;
            }

        .modal label {
            width: 100%;
            margin-top: 10px;
            text-align: right;
        }

        label {
            width: 100%;
            text-align: right;
        }

        #tbDanJu table tr th, #tbDanJu table tr td {
            padding: 5px;
            width: 300px;
            text-align: center;
            border: 1px solid #e2e2e2;
            border-collapse: collapse;
        }

        #tbDanJu table {
            border: 1px;
            width: 99.5%;
        }

           #tbDanJu table tr th {
                background-color: #f5f5f5;
                /*border-bottom: 1px solid gray;*/
            }

            /*table tr td {
                background-color: #f5f5f5;
            }*/

           #tbDanJu table input,#tbDanJu table select {
                width: 100px;
                margin: 0px;
                padding:0px;
                border: none;
            }

           #tbDanJu table select {
                width: 100%;
            }

        .tabl {
            height: 500px;
            width: 950px;
            overflow: auto;
            box-shadow: 0 0 20px 5px #ddd;
        }

        .Box {
            height: 350px;
            overflow: auto;
        }

        .box {
            border: solid 1px gray;
            width: 100px;
            display: none;
            position: absolute;
            background-color: ghostwhite;
            margin: 0;
            padding: 0;
            list-style-type: none;
            box-shadow: #808080 2px 2px;
        }

            .box > li {
                border-bottom: dashed 1px gray;
                cursor: pointer;
                text-align: center;
                padding: 3px;
            }

                .box > li:hover {
                    background-color: #eee;
                }

        .el-tabs__content {
            height: 350px;
        }



        .modal-content {
            width: 985px;
            height: 50%;
            left: -200px;
        }

        .modOne {
            width: 320px;
            height: 485px;
            float: left;
            overflow: auto;
            margin-right: 30px;
            box-shadow: 0 0 20px 1px #eee;
        }

        .modTow {
            width: 600px;
            height: 50%;
            float: left;
            padding: 10px;
            border: 3px solid #f5f5f5;
            box-shadow: 0 0 20px 1px #eee;
        }

        .ClaimBox {
            width: 480px;
            height: 320px;
            float: left;
            margin-right: 38px;
            box-shadow: 0 0 20px 1px #ddd;
            border-radius: 5px;
        }

            .ClaimBox label {
                width: 120px;
            }

            .ClaimBox .form-group {
                margin-bottom: 15px;
            }
    </style>
</head>
<body>
    <!-- @*导航栏*@ -->
    <nav class="breadcrumb" style="margin:0px">
        <i class="Hui-iconfont"></i> <a href=""> 首 页 </a> <span class="c-gray en">&gt;</span> <a href=""> 汽 修 管 理 </a>
        <span class="c-gray en">&gt;</span><a href=""> 完 工 确 认 </a>
        <a class="btn btn-success radius r" style="line-height:1.6em;top:0px;position:absolute;right:20px" href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont"></i>
        </a>
    </nav>
   <!--  @*按钮栏*@ -->
    <div class="cl pd-5 bg-1 bk-gray mt-20" style="padding:10px;margin:0px">
        <button class="btn btn-primary" onclick="CompletionTrue()"><i class="Hui-iconfont">&#xe604;</i> 完工</button>
        <button class="btn btn-success" onclick="CompletionFalse()"><i class="Hui-iconfont">&#xe676;</i> 取消完工</button>
        <button class="btn btn-info " onclick="ShowDanJu()"><i class="Hui-iconfont">&#xe692;</i> 过滤单据</button>
    </div>
   <!--  @*控件栏*@ -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-weight:inherit">完 工 确 认</legend>
        <div class="layui-field-box">
            <div class="row">
                <div class="col-lg-10 col-md-10 col-sm-12">
                    <form class="form-horizontal" role="form" style="margin-top:2px;">
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">开单日期</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control Date"id="StartDate">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12" style="width:30px">
                                <label class="control-label">至</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control Date"id="EndDate">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">完工状态:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <select class="form-control" id="ToCompletion">
                                    <option value="">全部</option>
                                    <option value="true">已完工</option>
                                    <option value="false">未完工</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-success " style="margin-left:100px" onclick="SelectReception()">
                                 <i class="Hui-iconfont">&#xe676;</i> 查询单据
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </fieldset>
   <!--  @*表格*@ -->
    <div style="height:500px;width:100%;overflow:auto; box-shadow: 0 0 20px 5px #ddd;">
        <table id="tbReception" style="width:120%">
            <tr>
                <th  w_check="true">选择</th>
                <th w_index="completionDate">完工日期</th>
                <th w_render="DocumentState">单据状态</th>
                <th w_index="documentState" w_hidden="true">单据状态</th>
                <th w_render="BalanceState">结算状态</th>
                <th w_index="balanceState" w_hidden="true">结算状态</th>
                <th w_index="balanceDate">日期</th>
                <th w_index="maintenanceNum">单号</th>
                <th w_index="carNum">车牌</th>
                <th w_index="vehicleType">车型</th>
                <th w_index="owner">车主</th>
                <th w_index="amount">总金额</th>
                <th w_index="repairman">联系人</th>
                <th w_index="repairmanTele">联系电话</th>
                <th w_index="repairName">修理类别</th>
                <th w_index="address">客户地址</th>
                <th w_index="carder">接车人</th>
                <th w_index="selfCoding">工作卡</th>
                <th w_index="engineNum">发动机号码</th>
                <th w_index="frameNum">车架号码</th>
                <th w_index="factoryDate">进厂日期</th>
                <th w_index="mileage">进厂里程</th>
                <th w_index="oilQuantity">油量</th>
                <th w_index="balanceDate">结算日期</th>
                <th w_index="">出厂日期</th>
                <th w_index="customerSou">客户来源</th>
            </tr>
        </table>
    </div>
   <!--  @*过滤单据模态框*@ -->
    <div class="modal fade col-lg-12" id="DanJuModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        自定义查询过滤条件
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="navsearch">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <form class="form-horizontal" role="form" style="margin-top:2px;">
                                <div class="col-lg-6 col-md-6 col-sm-12 ">
                                    <button type="button" class="btn btn-primary " onclick="addOne()"><i class="Hui-iconfont">&#xe604;</i> 新 增 </button>
                                    <button type="button" class="btn btn-danger " onclick="deleteOne()"><i class="Hui-iconfont">&#xe609;</i> 删 除</button>
                                    <button type="button" class="btn btn-info" id=""><i class="glyphicon glyphicon-ok-sign"></i>选 中</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>取 消</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div class="tabl">
                        <table id="tbDanJu">
                            <tr>
                                <th width='10%' height='20px'>序号</th>
                                <th width='10%' height='20px'>查询内容</th>
                                <th width='25%' height='20px'>操作</th>
                                <th width='10%' height='20px'>取值范围</th>
                                <th width='10%' height='20px'>逻辑关系</th>
                            </tr>
                        </table>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <script src="${cxt}/Content/bootstrap-3.3.7-dist/js/jquery-2.0.3.min.js"></script>
    <script src="${cxt}/Content/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="${cxt}/Content/jquery.bsgrid-1.37/js/lang/grid.zh-CN.min.js"></script>
    <script src="${cxt}/Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.js"></script>
    <script src="${cxt}/Content/Main/lib/layer/2.4/layer.js"></script>
    <script src="${cxt}/Content/layui/laydate/laydate.js"></script>
    <script src="${cxt}/Content/js/customfunction.js"></script>
    <script src="${cxt}/Content/js/combobox.js"></script>
    <script>
        //初始化表格
        var tbReception;
        $(function () {
            tbReception = $.fn.bsgrid.init('tbReception', {
                url: '${cxt}/servlet/CompletionServlet?fun=selectReception',
                autoLoad: false,//自动加载
                stripeRows: true,//隔行变色
                rowHoverColor: true,//划过行变色
                displayBlankRows: false,//是否显示空白行, 默认值true
                pageSize: 12,//分页大小, 如果设置值小于1则不分页且展示全部数据(即自动将pageAll设置为true), 默认值20
                pageSizeSelect: true,// 是否显示分页大小选择下拉框, 配合参数pageSizeForGrid, 默认值false
                pagingLittleToolbar: true,//是否显示精简的图标按钮分页工具条, 默认值false
                pageIncorrectTurnAlert: false,
                displayPagingToolbarOnlyMultiPages: true,
                event: {
                    customRowEvents: {
                        click: function (record, rowIndex) {
                            Check("#tbReception tr", rowIndex + 1);//复选框勾选
                        }
                    }
                }
            })
            Datetime();
            SelectReception();
        })
        //同时绑定多个时间插件
        function Datetime() {
            lay('.Date').each(function () {
                laydate.render({
                    elem: this
                  , trigger: 'click',
                    type: 'date'
                });
            });
        }
        //多条件查询
        function SelectReception() {
            var StartDate = $("#StartDate").val();
            var EndDate = $("#EndDate").val();
            var ToCompletion = $("#ToCompletion").val();
            if (StartDate == null || StartDate == undefined) StartDate = "";
            if (EndDate == null || EndDate == undefined) EndDate = "";
            if (ToCompletion == null || ToCompletion == undefined) CollageState = "";
            tbReception.search({ StartDate: StartDate, EndDate: EndDate, ToCompletion: ToCompletion });
        }
        //自定义事件 创建单据状态
        function DocumentState(record) {
            var DocumentState = record.documentState;
            if (DocumentState == "未结算") {
                return "<span style='color:red;'>未结算</span>";
            } else if (DocumentState == "完工未结算") {
                return "<span style='color:blue;'>完工未结算</span>";
            } else if (DocumentState == "完工已结算") {
                return "<span  style='color:coral;'>完工已结算</span>";
            } else {
                return "<span  style='color:orangered;'>未结算和完工未结算</span>";
            }
        }
        //自定义事件 创建单据状态
        function BalanceState(record) {
            var BalanceState = record.balanceState;
            if (BalanceState == "未付款") {
                return "<span style='color:red;'>未付款</span>";
            } else if (BalanceState == "挂账") {
                return "<span style='color:blue;'>挂账</span>";
            } else {
                return "<span  style='color:coral;'>付讫</span>";
            } 
        }
        //完工
        function CompletionTrue()
        {
            var ReceptionID = [];
            var record = tbReception.getCheckedRowsRecords();
            for (var i = 0; i < record.length; i++) {
                if (record[i].documentStateID != 3) {
                    var e = new Reception();
                    e.ReceptionID = record[i].receptionID;
                    ReceptionID.push(e);
                }
            }
            if (record.length > 0) {
                $.post("${cxt}/servlet/CompletionServlet?fun=updateCompletionTrue", { ReceptionVo: JSON.stringify(ReceptionID) }, function (data) {
                    if (data>0) {
                        SelectReception()
                        layer.msg("修 改 成 功 !")
                    } else {
                        layer.msg("该单据已完工结算，不需要重复操作!")
                    }
                })
            } else {
                layer.msg("请 选 择 需 要 操 作 的 数 据 !")
            }
        }
        //取消完工
        function CompletionFalse()
        {
            var ReceptionID = [];
            var record = tbReception.getCheckedRowsRecords();
            var DocumentState = record.documentState;
            for (var i = 0; i < record.length; i++) {
                if (record[i].DocumentStateID != 3) {
                    var e = new Reception();
                    e.ReceptionID = record[i].receptionID;
                    ReceptionID.push(e);
                } 
            }
            if (record.length > 0) {
            if (DocumentState != "完工已结算") {
            $.post("${cxt}/servlet/CompletionServlet?fun=updateCompletionFalse", { ReceptionVo: JSON.stringify(ReceptionID) }, function (data) {
                    if (data>0) {
                        SelectReception()
                        layer.msg("修 改 成 功 !")
                    }
                })
            } else {
                        layer.msg("已 结 算 不 能 取 消 完 工 !")
                    }
                
            } else {
                layer.msg("请 选 择 需 要 操 作 的 数 据 !")
            }
            
        }
        //添加条件单据行
        function addOne() {
            var tbDanJu = $("#tbDanJu");
            var length = $("#tbDanJu tr").length;
            var tr = $("<tr><td>" + length + "</td><td><select id=WA" + length + "></select></td><td onclick='tdclick(this)' id=WB" + length + "></td>"
                + "<td onclick='tdclick(this)' id=WC" + length + "></td><td><select id=WD" + length + "></select></td></tr>");
            tbDanJu.append(tr);
        }
        //删除添加单据行
        function deleteOne() {
            var length = $("#tbDanJu").find("tr").length;
            if (length > 1) {
                $("#tbDanJu").find("tr").last().remove();//找到最后一个选项然后删除
            }
        }
        //选择选择单据按钮弹出模态框
        function ShowDanJu() {
            $("#DanJuModal").modal("show");
        }
        function Reception(ReceptionID)
        {
            this.ReceptionID = ReceptionID;
        }
    </script>
</body>
</html>