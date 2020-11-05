<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="cxt"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width" />
    <title>DraftBack</title>
    <link href="${cxt}/Content/layui/css/modules/laydate/default/laydate.css" rel="stylesheet" />
    <link href="${cxt}/Content/layui/css/layui.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/css/main.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/tabnav/css/element.min.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" />
    <link href="${cxt}/Content/bootstrap-3.3.7-dist/css/bootstrapal.css" rel="stylesheet" />
    <!-- @*CSS样式(包含默认皮肤样式)*@ -->
    <link href="${cxt}/Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.css" rel="stylesheet" />
    <!--  @*CSS皮肤(需引用于bsgrid.all.min.css之后)*@ -->
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

        #myVue table tr th, #myVue table tr td {
            padding: 5px;
            width: 300px;
            text-align: center;
            border: 1px solid #e2e2e2;
            border-collapse: collapse;
        }

        #myVue table {
            border: 1px;
            width: 99.5%;
        }

           #myVue table tr th {
                background-color: #f5f5f5;
                /*border-bottom: 1px solid gray;*/
            }

            /*table tr td {
                background-color: #f5f5f5;
            }*/

           #myVue table input,#myVue table select {
                width: 100px;
                margin: 0px;
                padding:0px;
                border: none;
            }

           #myVue table select {
                width: 100%;
            }

        .tabl {
           height:500px;width:950px;overflow:auto; box-shadow: 0 0 20px 5px #ddd;
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
   <!--  @*导航栏*@ -->
    <nav class="breadcrumb" style="margin:0px">
        <i class="Hui-iconfont"></i> <a href=""> 首 页 </a> <span class="c-gray en">&gt;</span> <a href=""> 汽 修 管 理 </a>
        <span class="c-gray en">&gt;</span><a href=""> 维 修 领 料 </a>
        <a class="btn btn-success radius r" style="line-height:1.6em;top:0px;position:absolute;right:20px" href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont"></i>
        </a>
    </nav>
    <!-- @*按钮栏*@ -->
    <div class="cl pd-5 bg-1 bk-gray mt-20" style="padding:10px;margin:0px">
        <button class="btn btn-primary"onclick="Collage()"><i class="Hui-iconfont">&#xe604;</i> 领 料</button>
        <button class="btn btn-success"onclick="RestockMol()"><i class="Hui-iconfont">&#xe676;</i> 退料</button>
        <button class="btn btn-info " onclick="ShowDanJu()"><i class="Hui-iconfont">&#xe692;</i> 过滤单据</button>
        <button class="btn btn-pink"><i class="Hui-iconfont">&#xe652;</i> 打印</button>
    </div>
   <!--  @*控件栏*@ -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-weight:inherit">维 修 领 料</legend>
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
                                <label class="control-label">领料状态:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <select class="form-control" id="CollageState">
                                    <option value="">全部</option>
                                    <option value="未领料">未领料</option>
                                    <option value="已领料">已领料</option>
                                    <option value="已退料">已退料</option>
                                </select>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车牌:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control"id="CarNum">
                            </div>
                            <button type="button" class="btn btn-success " style="margin-left:100px"onclick="SelectLingLiao()"><i class="Hui-iconfont">&#xe676;</i> 查询单据</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </fieldset>
   <!--  @*表格*@ -->
    <div style="height:500px;width:100%;overflow:auto; box-shadow: 0 0 20px 5px #ddd;">
        <table id="tbLingLiao" style="width:100%;">
            <tr>
                <th w_render="ToAudit">是否审核</th>
                <th w_index="toAudit" w_hidden="true">是否审核</th>
                <th w_render="CollageState">领料状态</th>
                <th w_index="collageState" w_hidden="true">领料状态</th>
                <th w_render="DocumentState">单据状态</th>
                <th w_index="documentState" w_hidden="true">单据状态</th>
                <th w_index="completionDate">完工日期</th>
                <th w_index="maintenanceNum">单号</th>
                <th w_index="carNum">车牌</th>
                <th w_index="vehicleType">车型</th>
                <th w_index="owner">车主</th>
                <th w_index="selfCoding">工作卡</th>
                <th w_index="amount">总金额</th>
                <th w_index="balanceDate">结算日期</th>
                <th w_index="carder">接车人</th>
                <th w_index="repairName ">修理类别</th>
                <th w_index="oilQuantity">油量</th>
                <th w_index="address">客户地址</th>
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
    <!-- @*退料模态框*@ -->
    <div class="modal fade col-lg-12" id="TuiLiaoModal">
        <div class="modal-dialog">
            <div class="modal-content"style="height: 75%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        维修退料产品选择
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="navsearch">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <form class="form-horizontal" role="form" style="margin-top:2px;">
                                <input id="ReceptionID" hidden />
                                <div class="col-lg-7 col-md-7 col-sm-12 ">
                                   <p style="line-height:40px;font-size:16px;">车牌为:<span style="margin-right:15px;color:red"id="CarNums"></span> 维修单号为：<span id="MaintenanceNum"style="color:orangered"></span> 的当前领料产品</p>
                                </div>
                                <div style="margin-left:80%">
                                    <button type="button" class="btn btn-info" id=""onclick="Restock()"><i class="glyphicon glyphicon-ok-sign"></i>选 中</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>取 消</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div class="tabl">
                        <table id="tbTuiLiao" style="width:1500px">
                            <tr>
                                <th w_check="true"></th>
                                <th w_num="line">序号</th>
                                <th w_index="maintenanceNum">维修单号</th>
                                <th w_index="fittingsCode">配件编码</th>
                                <th w_index="fittingsName">配件名称</th>
                                <th w_index="warehouseName">仓库</th>
                                <th w_index="vehicleType">车型</th>
                                <th w_index="systemUnit">单位</th>
                                <th w_index="quantity">数量</th>
                                <th w_index="unitPrice">单价</th>
                                <th w_index="amount">金额</th>
                                <th w_index="maintainabilityName">维修性质</th>
                                <th w_index="fittingsSpec">规格</th>
                                <th w_index="position">仓位</th>
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
        var tbLingLiao, tbTuiLiao;
        $(function () {
            Datetime();
            tbLingLiao = $.fn.bsgrid.init('tbLingLiao', {
                url: '${cxt}/servlet/Collage?fun=selectReception',
                autoLoad: false,//自动加载
                stripeRows: true,//隔行变色
                rowHoverColor: true,//划过行变色
                displayBlankRows: false,//是否显示空白行, 默认值true
                pageSize: 12,//分页大小, 如果设置值小于1则不分页且展示全部数据(即自动将pageAll设置为true), 默认值20
                pageSizeSelect: true,// 是否显示分页大小选择下拉框, 配合参数pageSizeForGrid, 默认值false
                pagingLittleToolbar: true,//是否显示精简的图标按钮分页工具条, 默认值false
                pageIncorrectTurnAlert: false,
                displayPagingToolbarOnlyMultiPages: true,
            })
            tbTuiLiao = $.fn.bsgrid.init('tbTuiLiao', {
                url: '${cxt}/servlet/Collage?fun=selectTableCollageDetai',
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
                            Check("#tbTuiLiao tr", rowIndex + 1);//复选框勾选
                        }
                    }
                }
            })
            SelectLingLiao();
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
        function SelectLingLiao()
        {
            var StartDate = $("#StartDate").val();
            var EndDate = $("#EndDate").val();
            var CollageState = $("#CollageState").val();
            var CarNum = $("#CarNum").val(); 
            if (StartDate == null || StartDate == undefined) StartDate = "";
            if (EndDate == null || EndDate == undefined) EndDate = "";
            if (CollageState == null || CollageState == undefined) CollageState="";
            if (CarNum == null || CarNum == undefined) CarNum = "";
            tbLingLiao.search({ StartDate: StartDate, EndDate: EndDate, CollageState: CollageState, CarNum: CarNum });
        }
        //选择单据自定义事件 创建是否审核
        function ToAudit(record) {
            var ToAudit = record.toAudit;
            if (ToAudit == true || ToAudit == "true") {

                return "<input type='checkbox' checked disabled>";
            } else {
                return "<input type='checkbox' disabled>";
            }
        }
        //自定义事件 创建领料状态
        function CollageState(record) {
            var CollageState = record.collageState;
            if (CollageState == "未领料" ) {
                return "<span style='color:red;'>未领料</span>";
            } else if (CollageState == "已领料") {
                return "<span style='color:blue;'>已领料</span>";
            } else {
                return "<span >已退料</span>";
            }
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
        //选择选择单据按钮弹出模态框
        function ShowDanJu() {
            $("#DanJuModal").modal("show");
        }
        //领料按钮事件
        function Collage()
        {
            var rowIndex = tbLingLiao.getSelectedRowIndex();
            var record = tbLingLiao.getRecord(rowIndex);
            if (rowIndex > -1) {
                if (record.toAudit != true) {
                    $.post("${cxt}/servlet/Collage?fun=collage&ReceptionID=" + record.receptionID, function (data) {
                        window.location.href = "${cxt}/jsp/Mechanics/collage.jsp";
                    })
                } else {
                    layer.alert('该条记录已经审核不能再领料，如果需要领料请在出厂结算模块中，先反审核后再操作！', {
                        skin: 'layui-layer-molv' //样式类名
                   , closeBtn: 0
                    });
                }
            } else {
                layer.alert('请选择你需要领料的数据！', {
                    skin: 'layui-layer-molv' //样式类名
                    , closeBtn: 0
                });
            }
        }
       
        //退料按钮事件
        function RestockMol() {
            var rowIndex = tbLingLiao.getSelectedRowIndex();
            var record = tbLingLiao.getRecord(rowIndex);
            $("#MaintenanceNum").text(record.maintenanceNum)
            $("#CarNums").text(record.carNum);
            $("#ReceptionID").val(record.receptionID);
            if (rowIndex > -1) {
                if (record.toAudit != true) {
                    if (record.collageState == "未领料") {
                        layer.alert('尚未领料，无法退料！', {
                            skin: 'layui-layer-molv' //样式类名
                         , closeBtn: 0
                        });
                    } else {
                        $("#TuiLiaoModal").modal("show");
                        $.post("${cxt}/servlet/Collage?fun=selectCollageToID&ReceptionID=" + record.receptionID, function (data) {
                            tbTuiLiao.search({ CollageID: data })
                        })
                    }
                } else {
                    layer.alert('该条记录已经审核不能再退，如果需要退料请在出厂结算模块中，先反审核后再操作！', {
                        skin: 'layui-layer-molv' //样式类名
                   , closeBtn: 0
                    });
                }
            } else {
                layer.alert('请选择你需要退料的数据！', {
                    skin: 'layui-layer-molv' //样式类名
                    , closeBtn: 0
                });
            }
        }
        //退料页面
        function Restock() {
            var ArrcheckedRow = [];
            var checkedRows = tbTuiLiao.getCheckedRowsRecords();
            $.post("${cxt}/servlet/Collage?fun=restock", { SYS_CollageDetai: JSON.stringify(checkedRows) }, function (data) {
                window.location.href ="${cxt}/jsp/Mechanics/restock.jsp";
            })
        }
    </script>
</body>
</html>