<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>Dispatching</title>
    <link href="Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" />
    <link href="Content/Main/css/main.css" rel="stylesheet" />
    <link href="Content/Main/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" />
    <link href="Content/Main/tabnav/css/element.min.css" rel="stylesheet" />
    <link href="Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" />
    <link href="Content/bootstrap-3.3.7-dist/css/bootstrapal.css" rel="stylesheet" />
   <!--  @*CSS样式(包含默认皮肤样式)*@ -->
    <link href="Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.css" rel="stylesheet" />
    <!-- @*CSS皮肤(需引用于bsgrid.all.min.css之后)*@ -->
    <link href="Content/jquery.bsgrid-1.37/css/skins/grid_gray.min.css" rel="stylesheet" />
    <link href="Content/layui/css/modules/laydate/default/laydate.css" rel="stylesheet" />
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
            height: 32px;
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
        #WeiXiuBox table tr th, #WeiXiuBox table tr td {
            padding: 5px;
            width: 300px;
            font-size:14px;
            text-align: center;
            border: 1px solid #e2e2e2;
            border-collapse: collapse;
        }

        #WeiXiuBox table {
            border: 1px;
            width: 99.5%;
        }

           #WeiXiuBox table tr th {
                background-color: #f5f5f5;
                /*border-bottom: 1px solid gray;*/
            }

            /*table tr td {
                background-color: #f5f5f5;
            }*/

           #WeiXiuBox table input,#WeiXiuBox table select {
                width: 100px;
                margin: 0px;
                padding:0px;
                border: none;
            }

           #WeiXiuBox table select {
                width: 100%;
            }

        .Boxs {
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
            height: 80%;
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
            height: 80%;
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
        .form-control[disabled] {
        background-color:ghostwhite;
        }
    </style>
</head>
<body>
    <!-- @*导航栏*@ -->
    <nav class="breadcrumb" style="margin:0px">
        <i class="Hui-iconfont"></i> <a href=""> 首 页 </a> <span class="c-gray en">&gt;</span> <a href=""> 汽 修 管 理 </a>
        <span class="c-gray en">&gt;</span><a href=""> 派 工 单 </a>
        <a class="btn btn-success radius r" style="line-height:1.6em;top:0px;position:absolute;right:20px" href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont"></i>
        </a>
    </nav>
    <!-- @*按钮栏*@ -->
    <div class="cl pd-5 bg-1 bk-gray mt-20" style="padding:10px;margin:0px">
        <button class="btn btn-primary"onclick="addOne()"><i class="Hui-iconfont">&#xe604;</i> 派 工</button>
        <button class="btn btn-info "onclick="btnMainSava()"><i class="Hui-iconfont">&#xe632;</i> 保 存</button>
        <button class="btn btn-info "onclick="AddPaiGong()"><i class="Hui-iconfont">&#xe692;</i> 多次派工</button>
        <button class="btn btn-danger "onclick="deleteOne()"><i class="Hui-iconfont">&#xe609;</i> 删 除</button>
       <button class="btn btn-success " onclick="ShowDanJu()"><i class="Hui-iconfont">&#xe676;</i> 查询单据</button>
    </div>
  <!--   @*控件栏*@ -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-weight:inherit">维 修 派 工 单</legend>
        <div class="layui-field-box">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <form class="form-horizontal" role="form" style="margin-top:2px;"id="fromReception">
                        <input id="ReceptionID" hidden value="${ReceptionID }"/>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车牌:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled id="CarNum" name="CarNum">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车型:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled id="VehicleType" name="VehicleType">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">接车人:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled id="Carder" >
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">联系人:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled id="Connection">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label" style="text-shadow: 1px 1px 5px gray;">维修单号:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <p style="text-decoration:underline;padding:6px;font-size:16px"id="MaintenanceNum"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车主:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled id="Owner" name="Owner">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">联系电话:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled id="OwnerTele" name="OwnerTele">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">客户地址:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled  id="Address" name="Address">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">进厂里程:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <div class="input-group" style="width:104%">
                                    <input type="text" class="form-control " disabled id="Mileage" name="Mileage">
                                    <span class="input-group-btn">
                                        <button class="btn btn-info" type="button" disabled>K M</button>
                                    </span>
                                </div>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label" style="text-shadow: 1px 1px 5px gray;">开单日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <p style="text-decoration:underline;padding:6px;font-size:16px"id="OpenDate"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">发动机号码:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled id="EngineNum" name="EngineNum">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车架号码:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled id="FrameNum" name="FrameNum">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">工作号:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="SelfCoding" name="SelfCoding">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">修理类别:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <select class="form-control " disabled id="RepairID" name="RepairID"></select>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label" style="text-shadow: 1px 1px 5px gray;">进厂日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <p style="text-decoration:underline;padding:6px;font-size:16px"id="FactoryDate"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">结算日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" disabled id="BalanceDate" name="BalanceDates">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">维修总费:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control "disabled id="MaintenAmount" name="MaintenAmount">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label" style="position:relative;bottom:5px;left:30px">已派工:
                                  <input type="checkbox"id="ToSendWork" disabled  style="position:relative;top:4px;left:10px;width:20px;height:20px" />
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </fieldset>
    <!-- @*表格*@ -->
    <div id="WeiXiuBox" class="Boxs">
        <table id="tbWeiXiu">
            <tbody>
                <tr>
                    <th width='10%' height='20px'>序号</th>
                    <th width='25%' height='20px'>修理项目</th>
                    <th width='10%' height='20px'>维修工种</th>
                    <th width='10%' height='20px'>修理组</th>
                    <th width='25%' height='20px'>修理工</th>
                    <th width='10%' height='20px'>维修费</th>
                    <th width='10%' height='20px'>折扣</th>
                    <th width='10%' height='20px'>实收金额</th>
                    <th width='10%' height='20px'>派工工时</th>
                    <th width='10%' height='20px'>派工金额</th>
                    <th width='30%' height='20px'>完工期限</th>
                    <th width='10%' height='20px'>维修性质</th>
                    <th width='25%' height='20px'>备注</th>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td>合计：</td>
                    <td></td>
                    <td></td>
                    <td ></td>
                    <td></td>
                    <td id="WXiuLiFei">0.00</td>
                    <td></td>
                    <td id="WShiShou">0.00</td>
                    <td></td>
                    <td id="WPaiGong">0.00</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tfoot>
        </table>
    </div>
    <!-- @*选择单据模态框*@ -->
    <div class="modal fade col-lg-12" id="DanJuModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        记录定位
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="navsearch" style="height:90px">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <form class="form-horizontal" role="form" style="margin-top:2px;">
                                <div class="form-group">
                                    <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                        <label class="control-label">单 号</label>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                        <input type="text" class="form-control" id="DMaintenanceNum" onkeyup="" />
                                    </div>
                                    <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                        <label class="control-label">车 牌</label>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                        <input type="text" class="form-control" id="DCarNum" onkeyup="" />
                                    </div>
                                    <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                        <label class="control-label">单据状态</label>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                        <select class="form-control"id="DDocumentStateID"></select>
                                    </div>
                                </div>
                                <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                    <label class="control-label">付款状态</label>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                    <select class="form-control"id="DBalanceStateID"></select>
                                </div>
                                <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                    <label class="control-label">审核状态</label>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                    <select class="form-control" id="DToAudit">
                                        <option value="">全部</option>
                                        <option value="0">未审核</option>
                                        <option value="1">已审核</option>
                                    </select>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-12 ">
                                    <button type="button" class="btn btn-search" onclick="SeleceReception()"><i class="glyphicon glyphicon-search"></i>查 询</button>
                                    <button type="button" class="btn btn-info" id=""><i class="glyphicon glyphicon-ok-sign"></i>选 中</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>关 闭</button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div style="height:500px;width:950px;overflow:auto; box-shadow: 0 0 20px 5px #ddd;">
                        <table id="tbDanJu" style="width:1500px">
                            <tr>
                                <th w_render="ToSendWork">派工状态</th>
                                <th w_index="toSendWork" w_hidden="true">派工状态</th>
                                <th w_index="documentState">单据状态</th>
                                <th w_index="completionDate">完工日期</th>
                                <th w_index="maintenanceNum">单号</th>
                                <th w_index="owner">车主</th>
                                <th w_index="carNum">车牌</th>
                                <th w_index="vehicleType">车型</th>
                                <th w_index="selfCoding">工作卡</th>
                                <th w_index="amount">总金额</th>
                                <th w_index="openDate">开单日期</th>
                                <th w_index="carder">接车人</th>
                                <th w_index="repairName">修理类别</th>
                                <th w_index="oilQuantity">油量</th>
                                <th w_index="address">客户地址</th>
                            </tr>
                        </table>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <script src="Content/bootstrap-3.3.7-dist/js/jquery-2.0.3.min.js"></script>
    <script src="Content/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="Content/jquery.bsgrid-1.37/js/lang/grid.zh-CN.min.js"></script>
    <script src="Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.js"></script>
    <script src="Content/Main/tabnav/js/vue.min.js"></script>
    <script src="Content/Main/tabnav/js/element.min.js"></script>
    <script src="Content/Main/lib/layer/2.4/layer.js"></script>
    <script src="Content/layui/laydate/laydate.js"></script>
    <script src="Content/js/jquery.form.js"></script>
    <script src="Content/js/customfunction.js"></script>
    <script src="Content/js/combobox.js"></script>
    <script>
        //初始化表格
        var ReceptionID=$("#ReceptionID").val();
        var tbDanJu;
        var Numbers = /^[-\d.]+$/; //价格
        var ArrRepairItemDate = [], ArrMaintenanceDate = [], ArrMaintenanceCrew = [], ArrRepairMan = [], ArrMaintainData = [];
        $(function () {
            create();
            tbDanJu = $.fn.bsgrid.init('tbDanJu', {
                url: '${cxt}/customerController/selectReception.action',
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
                        dblclick: function (record, rowIndex) {
                            ReceptionData(record);
                        }
                    }
                }
            })
            if (!ReceptionID > 0){
               ShowDanJu();
            };
            if(ReceptionID>0){
            	console.log(ReceptionID)
               ReceptionDetailDate()
            }
            SeleceReception();
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
        //添加维修项目行
        function addOne() {
            var tbWeiXiu = $("#tbWeiXiu");
            var length = $("#tbWeiXiu tbody tr").length;
            var tr = $("<tr><td>" + length + "</td><td><select id=WA" + length + "></select></td><td><select id=WB" + length + " disabled ></select></td>"
                            + "<td><select id=WC" + length + " class='xlz'></select></td><td><select id=WD" + length + " class='xlg'></select></td>"
                            + "<td onclick='tdclick(this)' id=WE" + length + ">0.00</td><td onclick='tdclick(this)' id=WF" + length + ">100</td>"
                            + "<td  id=WG" + length + "> 0.00</td><td onclick='tdclick(this)' id=WH" + length + ">0</td>"
                            + "<td onclick='tdclick(this)' id=WI" + length + ">0.00</td><td ><input id=WJ" + length + " class='Date'></td>"
                            + "<td><select id=WK" + length + " disabled ></select></td><td onclick='tdclick(this)' id=WL" + length + "></td>"
                            + "<td hidden id=WM" + length + "></td></tr>");
            tbWeiXiu.append(tr);
            var length = $("#tbWeiXiu tbody tr").last().prevObject.length - 1;
            selectcreate("WA" + length, ArrRepairItemDate);
            selectcreate("WB" + length, ArrMaintenanceDate);
            selectcreate("WC" + length, ArrMaintenanceCrew);
            selectcreate("WD" + length, ArrRepairMan);
            selectcreate("WK" + length, ArrMaintainData);
            var RepairItemID = $("#WA" + length).val();
           $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + RepairItemID, function (data) {
                $("#WB" + length).val(data.maintenanceId);
                $("#WE" + length).text(data.repairCharge + ".00");
                $("#WG" + length).text(data.repairCharge + ".00");
            })
            $("#WA" + length).change(function () {
                var RepairItemID = $("#WA" + length).val();
                if (RepairItemID != 0) {
                   $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + RepairItemID, function (data) {
                        $("#WB" + length).val(data.maintenanceId);
                        $("#WE" + length).text(data.repairCharge + ".00");
                        $("#WG" + length).text(data.repairCharge + ".00");
                    })
                }
            })
            DetailEvent(length);
        }
        //删除维修项目行
        function deleteOne() {
            var length = $("#tbWeiXiu tbody").find("tr").length;
            layer.confirm('确定删除一行数据？', {
                btn: ['确定', '取消'] //按钮
            }, function (layerIndex) {
                layer.close(layerIndex);
                if (length > 1) {
                    $("#tbWeiXiu tbody").find("tr").last().remove();//找到最后一个选项然后删除
                    ClearDetailToT();
                    DetailEvent(length);
                }
            })
        }
        //绑定下拉框
        function create() {
            $.ajaxSettings.async = false;
            createSelect("RepairID", "${cxt}/commonController/selectAppendOption.action?type=SYS_Carder");//接车人下拉框
            appendOption("DDocumentStateID", "${cxt}/commonController/selectAppendOption.action?type=SYS_DocumentState");//单据状态下拉框
            appendOption("DBalanceStateID", "${cxt}/commonController/selectAppendOption.action?type=SYS_BalanceState");//结算状态下拉框

            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_Maintainability", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArrMaintainData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_RepairItem", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArrRepairItemDate.push(data[i]);
                }
            })
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_Maintenance", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArrMaintenanceDate.push(data[i]);
                }
            })
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_MaintenanceCrew", function (data) {
                ArrMaintenanceCrew.push({id:"0",name:"--请选择--"});
                for (var i = 0; i < data.length; i++) {
                    ArrMaintenanceCrew.push(data[i]);
                }
            })
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_RepairMan", function (data) {
                ArrRepairMan.push({id:"0",name:"--请选择--"});
                for (var i = 0; i < data.length; i++) {
                    ArrRepairMan.push(data[i]);
                }
            })
        }
        //明细表事件
        function DetailEvent(length) {
            var Price = 0;
            $.post("${cxt}/dispatchingController/selectDispatch.action?dispatchID=" + 1, function (data) {
                Price = data;
            })
            Datetime();
            if ($("#WH" + length).text() == "null") $("#WH" + length).text("0");
            if ($("#WI" + length).text() == "null") $("#WI" + length).text("0.00");
            if ($("#WJ" + length).val() == "null") $("#WJ" + length).val("");;
            if ($("#WL" + length).text() == "null") $("#WL" + length).text("无");
            WeiXiu();
            function WeiXiu() {
                var WXiuLiFei = 0, WPaiGong = 0, WShiShou = 0;
                for (var i = 1; i < length + 1; i++) {
                    if ($("#WE" + i).text() != "") {
                        WXiuLiFei = parseFloat(WXiuLiFei) + parseFloat($("#WE" + i).text());
                        $("#WXiuLiFei").text(parseFloat(WXiuLiFei));
                    }
                    if ($("#WG" + i).text() != "") {
                        WShiShou = parseFloat(WShiShou) + parseFloat($("#WG" + i).text());
                        $("#WShiShou").text(parseFloat(WShiShou));
                    }
                    if ($("#WI" + i).text() != "") {
                        WPaiGong = parseFloat(WPaiGong) + parseFloat($("#WI" + i).text());
                        $("#WPaiGong").text(parseFloat(WPaiGong));
                    }
                }
            }
            $("#tbWeiXiu").click(function () {
                var RepairCharge = $("#WE" + length).text();
                var Discount = $("#WF" + length).text();
                var Cal = $("#WE" + length).text() * $("#WF" + length).text() / 100;
                $("#WG" + length).text(parseFloat(Cal));
                $("#WI" + length).text(parseFloat($("#WH" + length).text() * Price));//派工金额
                if (!Numbers.test(RepairCharge)) {
                    layer.tips('只能输入数字!', '#WE' + length);
                } else if (!Numbers.test(Discount)) {
                    layer.tips('只能输入数字!', '#WF' + length);
                } else if (parseInt(Discount) > 100 || parseInt(Discount) < 0) {
                    layer.tips('数据超出有效长度!', '#WF' + length);
                } else if (!Numbers.test($("#WH" + length).text())) {
                    layer.tips('只能输入数字!', '#WH' + length);
                } else if ($("#WL" + length).text().length > 20) {
                    layer.tips('数据超出有效长度!', '#WL' + length);
                }
                WeiXiu();
            })

        }
        //明细表合计初始化
        function ClearDetailToT() {
            $("#WXiuLiFei").text("0.00");
            $("#WShiShou").text("0.00");
            $("#WPaiGong").text("0.00");
        }
        //转派工单回填信息内容
        function ReceptionDetailDate()
        {
            $.ajaxSettings.async = false;
            $.getJSON("${cxt}/customerController/selectReceptionDetail.action?receptionID=" +ReceptionID, function (data) {
                ReceptionDetail(data);
            })
            $.post("${cxt}/dispatchingController/clearDispatching.action");
            $.getJSON("${cxt}/dispatchingController/selectReception.action?receptionID=" + ReceptionID, function (record) {
                record=record[0];
                $("#ReceptionID").val(record.receptionId);
                $("#CarNum").val(record.carNum);
                $("#VehicleType").val(record.vehicleType);
                $("#Carder").val(record.carder);
                $("#Connection").val(record.owner);
                $("#Owner").val(record.owner);
                $("#OwnerTele").val(record.ownerTele);
                $("#Address").val(record.address);
                $("#Mileage").val(record.mileage);
                $("#EngineNum").val(record.engineNum);
                $("#FrameNum").val(record.frameNum);
                $("#SelfCoding").val(record.selfCoding);
                $("#RepairID").val(record.repairID);
                $("#BalanceDate").val(record.balanceDate);
                $("#FactoryDate").text(record.factoryDate);
                $("#OpenDate").text(record.openDate);
                $("#MaintenanceNum").text(record.maintenanceNum);
                $("#MaintenAmount").val(record.maintenAmount);
            })
        }
        function ReceptionDetail(data) {
        	console.log(data)
            $.each(data[0], function (i) {
                var CompletionDate = "";
                var str = data[0][i].completionDate;
                if (str != null) {
                    var time = new Date(parseInt(data[0][i].completionDate.replace(/\/Date\((-?\d+)\)\//, '$1')));
                    var year = time.getFullYear();
                    var month = time.getMonth() + 1;
                    var date = time.getDate();//系统时间月份中的日
                    var hour = time.getHours();
                    var minutes = time.getMinutes();
                    var seconds = time.getSeconds();
                    if (month < 10) {
                        month = "0" + month;
                    }
                    if (date < 10) {
                        date = "0" + date;
                    }
                    if (hour < 10) {
                        hour = "0" + hour;
                    }
                    if (minutes < 10) {
                        minutes = "0" + minutes;
                    }
                    if (seconds < 10) {
                        seconds = "0" + seconds;
                    }
                    CompletionDate = year + "-" + month + "-" + date + " " + hour + ":" + minutes + ":" + seconds;
                }
                var tbWeiXiu = $("#tbWeiXiu");
                var length = $("#tbWeiXiu tbody tr").last().prevObject.length;
                var tr = $("<tr><td>" + length + "</td><td><select id=WA" + length + "></select></td><td><select id=WB" + length + " disabled ></select></td>"
                        + "<td><select id=WC" + length + " class='xlz'></select></td><td><select id=WD" + length + " class='xlg'></select></td>"
                        + "<td onclick='tdclick(this)' id=WE" + length + ">" + data[0][i].repairCharge + ".00" + "</td><td onclick='tdclick(this)' id=WF" + length + ">" + data[0][i].discount + "</td>"
                        + "<td  id=WG" + length + ">" + data[0][i].amountPaid + ".00" + "</td><td onclick='tdclick(this)' id=WH" + length + ">" + data[0][i].jobHours + "</td>"
                        + "<td onclick='tdclick(this)' id=WI" + length + ">" + data[0][i].jobAmount + "</td><td ><input id=WJ" + length + " class='Date' value=" + formatToDate(data[0][i].completionDate) + "></td>"
                        + "<td><select id=WK" + length + " disabled ></select></td><td onclick='tdclick(this)' id=WL" + length + ">" + data[0][i].remark + "</td>"
                        + "<td hidden id=WM" + length + ">" + data[0][i].recRepairItemDetailId + "</td></tr>");
                tbWeiXiu.append(tr);
                selectcreate("WA" + length, ArrRepairItemDate);
                selectcreate("WB" + length, ArrMaintenanceDate);
                selectcreate("WC" + length, ArrMaintenanceCrew);
                selectcreate("WD" + length, ArrRepairMan);
                selectcreate("WK" + length, ArrMaintainData);
                var RepairItemID = $("#WA" + length).val();
                $("#WA" + length).change(function () {
                    var RepairItemID = $("#WA" + length).val();
                    if (RepairItemID != 0) {
                        $.ajaxSettings.async = false;
                        $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + RepairItemID, function (data) {
                            $("#WB" + length).val(data.maintenanceId);
                            $("#WE" + length).text(data.repairCharge + ".00");
                            $("#WG" + length).text(data.repairCharge + ".00");
                        })
                    }
                })
                DetailEvent(length);
                $("#WA" + length).val(data[0][i].repairItemId);
                 $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + data[0][i].repairItemId, function (data) {
                     $("#WB" + length).val(data.maintenanceId);
                })
                var MaintenanceCrewID = data[0][i].maintenanceCrewId != null ? data[0][i].maintenanceCrewId : 0;
                var RepairManID = data[0][i].repairManId != null ? data[0][i].repairManId : 0;
                $("#WC" + length).val(MaintenanceCrewID);
                $("#WD" + length).val(RepairManID);
                $("#WK" + length).val(data[0][i].maintainabilityId);
                var RepairItemID = $("#WA" + length).val();
                $.ajaxSettings.async = false;
                 $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + RepairItemID, function (data) {
                    $("#WB" + length).val(data.maintenanceId);
                })
            })
        }

        
        /********************主页面信息---开始*******************************/
        //主页面保存
        function btnMainSava() {
            var Wshishou = 0;
            var ArrRecRepairItem = [];
            var WeiXiulength = $("#tbWeiXiu tbody tr").length;
            var SelfCoding = $("#SelfCoding").val();//工作号
            var ReceptionID = $("#ReceptionID").val();
            Wshishou = parseFloat($("#WShiShou").text());
            $("#MaintenAmount").val(Wshishou);
            for (var i = 1; i < WeiXiulength; i++) {//修理项目构造函数
                var e = new RecRepairItem();//构造函数
                e.RepairItemID = $("#WA" + i).val();
                e.MaintenanceCrewID = $("#WC" + i).val();
                e.RepairManID = $("#WD" + i).val();
                e.RepairCharge = $("#WE" + i).text();
                e.Discount = $("#WF" + i).text();
                e.AmountPaid = $("#WG" + i).text();
                e.JobHours = $("#WH" + i).text();
                e.JobAmount = $("#WI" + i).text();
                e.CompletionDate = $("#WJ" + i).val();
                e.MaintainabilityID = $("#WK" + i).val();
                e.Remark = $("#WL" + i).text().trim();
                e.RecRepairItemDetailID = $("#WM" + i).text();
                ArrRecRepairItem.push(e);
            }
            if (WeiXiulength > 1) {
                $.each($(".xlz"), function (i) {
                    if ($(this).val() == 0) {
                        layer.alert('请选择修理组!');
                        return false;
                    } else {
                        $.each($(".xlg"), function (i) {
                            if ($(this).val() == 0) {
                                layer.alert('请选择修理工!');
                                return false;
                            } else {
                                layer.confirm('是否保存当前信息？', {
                                    btn: ['保存', '取消'] //按钮
                                }, function () {
                                    $.post("${cxt}/dispatchingController/updateListRepairItemDetail.action", {
                                        sysRecRepairItemDetail: JSON.stringify(ArrRecRepairItem), maintenAmount: $("#MaintenAmount").val(), selfCoding: SelfCoding, receptionID: ReceptionID, toSendWork: true
                                    }, function (data) {
                                        SeleceReception();
                                        if (data>0) {
                                            layer.msg('数 据 保 存 成 功 ！', {
                                                time: 3000, //3s后自动关闭
                                                btn: ['知道了']
                                            });

                                        } else {
                                            layer.msg('数 据 保 存 失 败！', {
                                                time: 3000, //3s后自动关闭
                                            });
                                        }
                                    })
                                })
                            }
                        })
                    }
                })
            } else {
                layer.msg("没有明细不能保存！")
            }
        }
        //多次派工
        function AddPaiGong()
        {
            var ArrAddPaiGong = [];
            ArrAddPaiGong.push($("#WA1").val(), $("#WB1").val(), $("#WC1").val(), $("#WD1").val(), $("#WE1").text(), $("#WF1").text(),
                $("#WG1").text(), $("#WH1").text(), $("#WI1").text(), $("#WJ1").val(), $("#WK1").val(), $("#WL1").text());
            if (ArrAddPaiGong[10] != undefined) {
                var tbWeiXiu = $("#tbWeiXiu");
                var length = $("#tbWeiXiu tbody tr").length;
                var tr = $("<tr><td>" + length + "</td><td><select id=WA" + length + "></select></td><td><select id=WB" + length + " disabled ></select></td>"
                                + "<td><select id=WC" + length + " class='xlz'></select></td><td><select id=WD" + length + " class='xlg'></select></td>"
                                + "<td onclick='tdclick(this)' id=WE" + length + ">" + ArrAddPaiGong[4] + "</td>"
                                + "<td onclick='tdclick(this)' id=WF" + length + ">" + ArrAddPaiGong[5] + "</td>"
                                + "<td  id=WG" + length + ">" + ArrAddPaiGong[6] + "</td><td onclick='tdclick(this)' id=WH" + length + ">" + ArrAddPaiGong[7] + "</td>"
                                + "<td onclick='tdclick(this)' id=WI" + length + ">" + ArrAddPaiGong[8] + "</td><td ><input id=WJ" + length + " class='Date' value=" + ArrAddPaiGong[9] + "></td>"
                                + "<td><select id=WK" + length + " disabled ></select></td><td onclick='tdclick(this)' id=WL" + length + ">" + ArrAddPaiGong[11] + "</td>"
                                + "<td hidden id=WM" + length + "></td></tr>");
                tbWeiXiu.append(tr);
                selectcreate("WA" + length, ArrRepairItemDate);
                selectcreate("WB" + length, ArrMaintenanceDate);
                selectcreate("WC" + length, ArrMaintenanceCrew);
                selectcreate("WD" + length, ArrRepairMan);
                selectcreate("WK" + length, ArrMaintainData);
                $("#WA" + length).val(ArrAddPaiGong[0]);
                $("#WB" + length).val(ArrAddPaiGong[1]);
                $("#WC" + length).val(ArrAddPaiGong[2]);
                $("#WD" + length).val(ArrAddPaiGong[3]);
                $("#WK" + length).val(ArrAddPaiGong[10]);
                var RepairItemID = $("#WA" + length).val();
                $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + RepairItemID, function (data) {
                    $("#WB" + length).val(data.maintenanceId);
                })
                $("#WA" + length).change(function () {
                    var RepairItemID = $("#WA" + length).val();
                    if (RepairItemID != 0) {
                        $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + RepairItemID, function (data) {
                            $("#WB" + length).val(data.maintenanceId);
                            $("#WE" + length).text(data.repairCharge + ".00");
                            $("#WG" + length).text(data.repairCharge + ".00");
                        })
                    }
                })
                DetailEvent(length);
            } else {
                layer.alert("尚未派工，请先派工！");
            }
        }
        //主页面选择单据
        function ShowDanJu() {
            $("#DanJuModal").modal("show");
        }

        /********************主页面信息---结束*******************************/

        /********************单据模态框---开始*******************************/
        //单据表格查询
        function SeleceReception() {
            var DMaintenanceNum = $("#DMaintenanceNum").val();
            var DCarNum = $("#DCarNum").val();
            var DDocumentStateID = $("#DDocumentStateID").val();
            var DBalanceStateID = $("#DBalanceStateID").val();
            var DToAudit = $("#DToAudit").val();
            if (DToAudit == undefined || DToAudit == null) DToAudit = "";
            if (DMaintenanceNum == undefined) DMaintenanceNum = "";
            if (DCarNum == undefined) DCarNum = "";
            if (DDocumentStateID == "" || DDocumentStateID == null) DDocumentStateID = 0;
            if (DBalanceStateID == "" || DBalanceStateID == null) DBalanceStateID = 0;
            tbDanJu.search({
                maintenanceNum: DMaintenanceNum, toAudit: DToAudit, balanceStateID: DBalanceStateID,
                carNum: DCarNum, documentStateID: DDocumentStateID,
            });
        }
        //选择单据回填信息
        function ReceptionData(record) {
            $("#fromReception").resetForm();
            $("#tbWeiXiu tbody tr td").parent("tr").remove();
            $.getJSON("${cxt}/customerController/selectReceptionDetail.action?receptionID=" + record.receptionId, function (datas) {
                $("#DanJuModal").modal("hide");
                ReceptionDetail(datas);
            })
            //主页面回填
            $("#ReceptionID").val(record.receptionId);
            $("#CarNum").val(record.carNum);
            $("#VehicleType").val(record.vehicleType);
            $("#Carder").val(record.carder);
            $("#Connection").val(record.owner);
            $("#Owner").val(record.owner);
            $("#OwnerTele").val(record.ownerTele);
            $("#Address").val(record.address);
            $("#Mileage").val(record.mileage);
            $("#EngineNum").val(record.engineNum);
            $("#FrameNum").val(record.frameNum);
            $("#SelfCoding").val(record.selfCoding);
            $("#RepairID").val(record.repairID);
            $("#BalanceDate").val(record.balanceDate);
            $("#FactoryDate").text(record.factoryDate);
            $("#OpenDate").text(record.openDate);
            $("#MaintenanceNum").text(record.maintenanceNum);
            $("#MaintenAmount").val(record.maintenAmount);
            if (record.toSendWork == true) {
                $("#ToSendWork").attr("checked",true);
            } else {
                $("#ToSendWork").removeAttr("checked");
            }
        }
        //选择单据自定义事件 创建是否审核
        function ToSendWork(record) {
            var ToSendWork = record.toSendWork;
            if (ToSendWork == true || ToSendWork == "true") {
                return "<span style='color:red;'>✔ 已派工</span>";
            } else {
                return "<span style='color:blue;'>✘ 未派工</span>";
            }
        }

        /********************单据模态框---结束*******************************/
        //修理项目构造函数
        function RecRepairItem(RecRepairItemDetailID, RepairItemID, MaintenanceCrewID, RepairManID, RepairCharge, Discount, AmountPaid,
            JobHours, JobAmount, CompletionDate, MaintainabilityID, Remark) {
            this.RecRepairItemDetailID = RecRepairItemDetailID;//修理明细ID
            this.RepairItemID = RepairItemID;//修理项目ID
            this.MaintenanceCrewID = MaintenanceCrewID;//修理组
            this.RepairManID = RepairManID;//修理工
            this.RepairCharge = RepairCharge;//修理费
            this.Discount = Discount;//折扣
            this.AmountPaid = AmountPaid;//实收金额
            this.JobHours = JobHours;//派工工时
            this.JobAmount = JobAmount;//派工金额
            this.CompletionDate = CompletionDate;//完工期限
            this.MaintainabilityID = MaintainabilityID;//维修性质
            this.Remark = Remark;//备注
        }
    </script>
</body>
</html>