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
    <title>Insurance</title>
    <link href="${cxt}/Content/layui/css/layui.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/css/main.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/tabnav/css/element.min.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" />
    <link href="${cxt}/Content/bootstrap-3.3.7-dist/css/bootstrapal.css" rel="stylesheet" />
   <!--  @*CSS样式(包含默认皮肤样式)*@ -->
    <link href="${cxt}/Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.css" rel="stylesheet" />
   <!--  @*CSS皮肤(需引用于bsgrid.all.min.css之后)*@ -->
    <link href="${cxt}/Content/jquery.bsgrid-1.37/css/skins/grid_gray.min.css" rel="stylesheet" />
    <link href="${cxt}/Content/layui/css/modules/laydate/default/laydate.css" rel="stylesheet" />
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

        #myVue table tr th, #myVue table tr td {
            padding: 5px;
            width: 300px;
            text-align: center;
            border: 1px solid #e2e2e2;
            font-size:14px;
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
            width: 280px;
            height: 485px;
            float: left;
            overflow: auto;
            margin-right: 30px;
            box-shadow: 0 0 20px 1px #eee;
        }

        .modTow {
            width: 640px;
            height: 80%;
            float: left;
            padding: 10px;
            border: 3px solid #f5f5f5;
            box-shadow: 0 0 20px 1px #eee;
            overflow: auto;
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

        .legend {
            font-size: 12px;
        }

        #JieSuanModal .span {
            display: inline-block;
            width: 150px;
            margin-right: 20px;
        }

        #JieSuanModal label {
            width: 105px;
        }
          .seinput {
        position:relative;bottom:41px;width:78%;border:none;left:12px;height:66%;
        }
          .form-control[disabled] ,html input[disabled],textarea{
        background:#fff;
        }
    </style>
</head>
<body>
   <!--  @*导航栏*@ -->
    <nav class="breadcrumb" style="margin:0px">
        <i class="Hui-iconfont"></i> <a href=""> 首 页 </a> <span class="c-gray en">&gt;</span> <a href=""> 汽 修 管 理 </a>
        <span class="c-gray en">&gt;</span><a href="">保 险 理 赔 结 算</a>
        <a class="btn btn-success radius r" style="line-height:1.6em;top:0px;position:absolute;right:20px" href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont"></i>
        </a>
    </nav>
    <!-- @*按钮栏*@ -->
    <div class="cl pd-5 bg-1 bk-gray mt-20" style="padding:10px;margin:0px">
        <button class="btn btn-primary" onclick="Clear(0)"><i class="Hui-iconfont" id="btnInsert">&#xe604;</i> 新 增 </button>
        <button class="btn btn-info " onclick="BavaMian()" id="btnBave"><i class="Hui-iconfont">&#xe632;</i> 保 存</button>
        <button class="btn btn-danger " onclick="DelectInsurance()" id="btnDelect"><i class="Hui-iconfont">&#xe609;</i> 删 除</button>
        <button class="btn btn-success " onclick="ShowDanJu()"><i class="Hui-iconfont">&#xe676;</i> 查询单据</button>
        <button class="btn btn-info " onclick="ShowJieSuan()" id="btnColle"><i class="Hui-iconfont">&#xe692;</i> 收 款</button>
        <button class="btn btn-warning " onclick="TOAudit()" id="btnAudit"><i class="Hui-iconfont">&#xe634;</i> 审 核</button>
        <button class="btn btn-warning " onclick="TONotAudit()" id="btnNotAudit"><i class="Hui-iconfont">&#xe634;</i> 反 审 核</button>
        <button class="btn btn-pink"><i class="Hui-iconfont">&#xe652;</i> 打 印</button>
    </div>
    <!-- @*控件栏*@ -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-weight:inherit">保 险 理 赔 结 算</legend>
        <div class="layui-field-box">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <form class="form-horizontal" role="form" style="margin-top:2px;"id="fromInsurance">
                        <input type="text" hidden id="InsuranceMoney" />
                        <input type="text" hidden id="InsuranceID" name="InsuranceID" /><!-- @*索赔ID*@ -->
                        <input type="text" hidden id="InsuranceDetailID" name="InsuranceDetailID" /><!-- @*//索赔明细ID*@ -->
                        <input type="text" id="CustomerNum" hidden><!-- @*客户编码*@ -->
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">理赔单号:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabl"id="InsuranceNum"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label ">开单日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control Datetime " id="OpenDate">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">结算日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control Date"id="BalanceDate">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">维修单号:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <div class="input-group" style="width:104%">
                                    <input type="text" class="form-control disabl" id="MaintenanceNum" disabled>
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="button" onclick="ShowDanHao()"><i class="Hui-iconfont">&#xe70f;</i></button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">保险公司:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <select class="form-control " id="InsuranceComID"></select>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">理赔员工:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control " id="ClaimsStaff">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">报案编号:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control"id="ReportNum">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">保单号:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="PolicyNum">
                            </div>
                        </div>
                        <div class="form-group">
                            
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">保单金额:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control"id="PolicyMoney">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">是否开票:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <select class="form-control" id="ToTicket">
                                    <option value=true>是</option>
                                    <option value=false>否</option>
                                </select>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车牌:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabl" id="CarNum" disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车主:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabl" id="Owner" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车型:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabl" id="VehicleType" disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">备注:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="Remark">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">总金额:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabl" id="Amount" disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <input type="hidden" id="ToAudit" value="false" />
                                <label class="control-label" style="position:relative;bottom:5px;left:30px">已审核:
                                    <input type="checkbox" style="position:relative;top:4px;left:10px;width:20px;height:20px" id="ToAudits" />
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </fieldset>
   <!--  @*切换卡*@ -->
    <div class="col-lg-12" id="myVue" style="margin:15px">
        <el-tabs type="border-card">
            <el-tab-pane label="维修项目">
                <div class="Boxs">
                    <table id="tbWeiXiu" style="width:99.5%">
                        <tbody>
                            <tr>
                                <th>序号</th>
                                <th>维修明细</th>
                                <th>维修工艺</th>
                                <th>修理费</th>
                                <th>折扣</th>
                                <th>实收金额</th>
                                <th>维修性质</th>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>合计：</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td id="WShiShou">0.00</td>
                                <td></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </el-tab-pane>
            <el-tab-pane label="领用配件">
                <div class="Boxs">
                    <table id="tbChanPin" style="width:99.5%">
                        <tbody>
                            <tr>
                                <th>序号</th>
                                <th>配件编码</th>
                                <th>配件名称</th>
                                <th>所在仓库</th>
                                <th>数量</th>
                                <th>单位</th>
                                <th>单价</th>
                                <th>总金额</th>
                                <th>维修性质</th>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>合计：</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td id="CJinE">0.00</td>
                                <td></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </el-tab-pane>
        </el-tabs>
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
                        历史保险单查询
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="navsearch" style="height:100px">
                        <div class="col-lg-12 col-md-12 col-sm-12">
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
                                            <select class="form-control" id="DDocumentStateID"></select>
                                        </div>
                                    </div>
                                    <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                        <label class="control-label">付款状态</label>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                        <select class="form-control" id="DBalanceStateID"></select>
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
                                        <button type="button" class="btn btn-search" onclick="SeleceDanJu()"><i class="glyphicon glyphicon-search"></i>查 询</button>
                                        <button type="button" class="btn btn-info" id=""><i class="glyphicon glyphicon-ok-sign"></i>选 中</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>关 闭</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div style="height:500px;width:950px;overflow:auto; box-shadow: 0 0 20px 5px #ddd;">
                        <table id="tbDanJu" style="width:100%">
                            <tr>
                                <th w_render="ToAudit">是否审核</th>
                                <th w_index="toAudit" w_hidden="true">是否审核</th>
                                <th w_index="insuranceNum">理赔单号</th>
                                <th w_index="maintenanceNum">业务单号</th>
                                <th w_index="openDate">单据日期</th>
                                <th w_index="balanceDate">结算日期</th>
                                <th w_index="customerNum">客户编号</th>
                                <th w_index="owner">客户名称</th>
                                <th w_index="claimStaff">理赔员</th>
                                <th w_index="reportNum">报案编号</th>
                            </tr>
                        </table>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <!-- @*维修单号模态框*@ -->
    <div class="modal fade col-lg-12" id="DanHaoModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        维修单号查询
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="navsearch" style="height:100px">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <form class="form-horizontal" role="form" style="margin-top:2px;">
                                <div class="form-group">
                                    <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                        <label class="control-label">单 号</label>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                        <input type="text" class="form-control" id="HMaintenanceNum" onkeyup="" />
                                    </div>
                                    <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                        <label class="control-label">车 牌</label>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                        <input type="text" class="form-control" id="HCarNum" onkeyup="" />
                                    </div>
                                    <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                        <label class="control-label">单据状态</label>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                        <select class="form-control" id="HDocumentStateID"></select>
                                    </div>
                                </div>
                                <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                    <label class="control-label">付款状态</label>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                    <select class="form-control" id="HBalanceStateID"></select>
                                </div>
                                <div class="col-lg-1 col-md-1 col-sm-1 text-right">
                                    <label class="control-label">审核状态</label>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-top:3px">
                                    <select class="form-control" id="HToAudit">
                                        <option value="">全部</option>
                                        <option value="0">未审核</option>
                                        <option value="1">已审核</option>
                                    </select>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-12 ">
                                    <button type="button" class="btn btn-search" id="" onclick="SeleceDanHao()"><i class="glyphicon glyphicon-search"></i>查 询</button>
                                    <button type="button" class="btn btn-info" id=""><i class="glyphicon glyphicon-ok-sign"></i>选 中</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>关 闭</button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div style="height:500px;width:950px;overflow:auto; box-shadow: 0 0 20px 5px #ddd;">
                        <table id="tbDanHao" style="width:1520px">
                            <tr>
                                <th w_index="documentState">单据状态</th>
                                <th w_index="balanceState">结算状态</th>
                                <th w_index="completionDate">完工日期</th>
                                <th w_index="maintenanceNum">单号</th>
                                <th w_index="carNum">车牌</th>
                                <th w_index="vehicleType">车型</th>
                                <th w_index="owner">车主</th>
                                <th w_index="selfCoding">工作卡</th>
                                <th w_index="amount">总金额</th>
                                <th w_index="claimMoney">索赔金额</th>
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
    <!-- @*结算单模态框*@ -->
    <div class="modal fade col-lg-12" id="JieSuanModal">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 520px;left:0;height: 67%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        客户结算单
                    </h4>
                </div>
                <div class="modal-body" style="padding-top:0">
                    <fieldset class="layui-elem-field layui-field-box" style="margin-top: 20px;">
                        <legend style="font-size:16px">结算单收款说明</legend>
                        <p>温馨提示:如本次收款未收完或未收款，请在‘应收应付’窗口中选择对应单号‘到账’即可</p>
                    </fieldset>
                    <fieldset class="layui-elem-field layui-field-box" style="margin-top: 20px;">
                        <legend style="font-size:16px">客户付款情况</legend>
                        <p style="padding-left:10px">客户编号：<span class="span" id="JCustomerNum"></span>客户姓名：<span id="JOwner"></span></p>
                        <p style="padding-left:10px">单据时间：<span class="span" id="JBalanceDate"></span>业务单号：<span id="JMaintenanceNum"></span></p>
                        <p style="padding-left:10px">应收金额：<span class="span" id="JShouldAmount">0.00</span>已优惠金额：<span id="JOptimalAmount">0.00</span></p>
                        <p style="padding-left:10px">已收金额：<span class="span" id="JCollectionAmount">0.00</span>未收金额：<span id="JNoAmount"></span></p>
                        <form class="form-horizontal" role="form" style="margin-top:2px;" id="formBalance">
                            <input hidden id="ReceptionID"type="text" value="${receptionID }" />
                            <input hidden id="BalanceID"type="text" /><!-- @*结算ID*@ -->
                            <div class="col-lg-8 col-md-8 col-sm-8">
                                <div class="form-group">
                                    <div class="col-lg-5 col-md-5 col-sm-5 ">
                                        <label class="control-label">本次优惠金额：</label>
                                    </div>
                                    <div class="col-lg-7 col-md-7 col-sm-7">
                                        <input type="text" class="form-control" id="OptimalAmount" onKeyUp="value = value.replace(/[^\d.]/g, '')" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-5 col-md-5 col-sm-5 ">
                                        <label class="control-label">本次收款金额：</label>
                                    </div>
                                    <div class="col-lg-7 col-md-7 col-sm-7">
                                        <input type="text" class="form-control" id="CollectionAmount" onkeyup="" onKeyUp="value = value.replace(/[^\d.]/g, '')" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-5 col-md-5 col-sm-5 ">
                                        <label class="control-label">入账方式：</label>
                                    </div>
                                    <div class="col-lg-7 col-md-7 col-sm-7">
                                        <select class="form-control" id="PaymentID"></select>
                                    </div>
                                </div>

                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-4">
                                <p style="line-height:24px;color:orangered">温馨提示：如本次收款未完成或未收款，请在‘财务管理’模块‘客户结算单’窗口中选择对应的客户名称即可</p>
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <div class="form-group">
                                    <div class="col-lg-4 col-md-4 col-sm-4 " style="width:60px">
                                        <label class="control-label">备注：</label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6">
                                        <input type="text" class="form-control" id="Remark" onkeyup="" style="width:205px" />
                                    </div>
                                </div>
                            </div>
                        </form>
                    </fieldset>
                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div class="col-lg-12 col-md-12 col-sm-12 ">
                        <button class="btn btn-info " onclick="btnBaveJieSuan()"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
                        <button class="btn btn-pink"><i class="Hui-iconfont">&#xe652;</i> 打 印</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>取 消</button>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <script src="${cxt}/Content/bootstrap-3.3.7-dist/js/jquery-2.0.3.min.js"></script>
    <script src="${cxt}/Content/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="${cxt}/Content/jquery.bsgrid-1.37/js/lang/grid.zh-CN.min.js"></script>
    <script src="${cxt}/Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.js"></script>
    <script src="${cxt}/Content/Main/tabnav/js/vue.min.js"></script>
    <script src="${cxt}/Content/Main/tabnav/js/element.min.js"></script>
    <script src="${cxt}/Content/Main/lib/layer/2.4/layer.js"></script>
    <script src="${cxt}/Content/layui/laydate/laydate.js"></script>
    <script src="${cxt}/Content/js/jquery.form.js"></script>
    <script src="${cxt}/Content/js/customfunction.js"></script>
    <script>
        //初始化表格
        var tbDanJu, tbDanHao;
        var ArrMaintainData = [], ArrWarehouseData = [], ArrRepairItemData = [], ArrMaintenanceData = [];
        var ReceptionID = $("#ReceptionID").val();
        $(function () {
            $.ajaxSettings.async = false;
            Clear(0), create(), InsuranceNum(), Datetime();
            tbDanJu = $.fn.bsgrid.init('tbDanJu', {
                url: '${cxt}/servlet/InsuranceServlet?fun=selectInsurance',
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
                            DanJuData(record);
                        }
                    }
                }
            })
            tbDanHao = $.fn.bsgrid.init('tbDanHao', {
                url: '${cxt}/servlet/InsuranceServlet?fun=selectDanHao',
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
                            DanHaoData(record);
                        }
                    }
                }
            })
            SeleceDanHao(), SeleceDanJu(),MianDatas();
        })
        //切换卡
        new Vue({
            el: '#myVue',
            data() {
                return {
                    activeName: 'second',
                    activeName2: 'first',
                    tabPosition: 'top',
                    editableTabsValue2: '2',
                    editableTabs2: [{
                        title: 'Tab 1',
                        name: '1',
                        content: 'Tab 1 content'
                    }, {
                        title: 'Tab 2',
                        name: '2',
                        content: 'Tab 2 content'
                    }],
                    tabIndex: 2
                }
            },
            methods: {
                handleClick(tab, event) {
                    console.log(tab, event);
                },
                addTab(targetName) {
                    let newTabName = ++this.tabIndex + '';
                    this.editableTabs2.push({
                        title: 'New Tab',
                        name: newTabName,
                        content: 'New Tab content'
                    });
                    this.editableTabsValue2 = newTabName;
                },
                removeTab(targetName) {
                    let tabs = this.editableTabs2;
                    let activeName = this.editableTabsValue2;
                    if (activeName === targetName) {
                        tabs.forEach((tab, index) => {
                            if (tab.name === targetName) {
                                let nextTab = tabs[index + 1] || tabs[index - 1];
                                if (nextTab) {
                                    activeName = nextTab.name;
                                }
                            }
                        });
                    }

                    this.editableTabsValue2 = activeName;
                    this.editableTabs2 = tabs.filter(tab => tab.name !== targetName);
                }
            }
        })
        //同时绑定多个时间插件
        function Datetime() {
            lay('.Datetime').each(function () {
                run();
                laydate.render({
                    elem: this
                  , trigger: 'click',
                    type: 'datetime'
                });
            });
            lay('.Date').each(function () {
                run();
                laydate.render({
                    elem: this
                  , trigger: 'click',
                    type: 'date'
                });
            });
        }
        //获取系统当前时间
        function run() {
            var time = new Date();//获取系统当前时间
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
            var newDate = year + "-" + month + "-" + date + " " + hour + ":" + minutes + ":" + seconds;
            $(".Datetime").each(function () {
                $(this).val(newDate);
            })
            $(".Date").each(function () {
                $(this).val(year + "-" + month + "-" + date);
            })
        }
        //绑定下拉框
        function create() {
            $.ajaxSettings.async = false;
            appendOption("InsuranceComID", "${cxt}/servlet/CommonServlet?t=SYS_InsuranceCom");//保险公司下拉框

            appendOption("DDocumentStateID", "${cxt}/servlet/CommonServlet?t=SYS_DocumentState");//单据状态下拉框
            appendOption("DBalanceStateID", "${cxt}/servlet/CommonServlet?t=SYS_BalanceState");//结算状态下拉框

            appendOption("HDocumentStateID", "${cxt}/servlet/CommonServlet?t=SYS_DocumentState");//单据状态下拉框
            appendOption("HBalanceStateID", "${cxt}/servlet/CommonServlet?t=SYS_BalanceState");//结算状态下拉框

            appendOption("PaymentID", "${cxt}/servlet/CommonServlet?t=SYS_Payment");//付款方式下拉框
            $.getJSON("${cxt}/servlet/CommonServlet?t=SYS_Maintainability", function (data) {
                data=data.list;
                for (var i = 0; i < data.length; i++) {
                    ArrMaintainData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/servlet/CommonServlet?t=SYS_Warehouse", function (data) {
                data=data.list;
                for (var i = 0; i < data.length; i++) {
                    ArrWarehouseData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/servlet/CommonServlet?t=SYS_RepairItem", function (data) {
                data=data.list;
                for (var i = 0; i < data.length; i++) {
                    ArrRepairItemData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/servlet/CommonServlet?t=SYS_Maintenance", function (data) {
                data=data.list;
                for (var i = 0; i < data.length; i++) {
                    ArrMaintenanceData.push(data[i]);
                }
            })
        }
        //生成索赔单号
        function InsuranceNum() {
            $.post("${cxt}/servlet/InsuranceServlet?fun=insuranceNum", function (data) {
                $("#InsuranceNum").val(data);
            })
        }
        //明细表事件
        function DetailEvent(length, Num) {
            if (Num == 1) {
                selectcreate("WA" + length, ArrRepairItemData);
                selectcreate("WB" + length, ArrMaintenanceData);
                selectcreate("WF" + length, ArrMaintainData);
                if ($("#WG" + length).text() == "null") $("#WG" + length).text("无");
                var WShiShou = 0;
                for (var i = 1; i < length + 1; i++) {
                    if ($("#WE" + i).text() != "") {
                        WShiShou = parseFloat(WShiShou) + parseFloat($("#WE" + i).text());
                        $("#WShiShou").text(parseFloat(WShiShou));
                    }
                }
            }
            if (Num == 2) {
                selectcreate("CC" + length, ArrWarehouseData);
                selectcreate("CH" + length, ArrMaintainData);
                if ($("#CF" + length).text() == "null") $("#CE" + length).text("");
                var CJinE = 0;
                for (var i = 1; i < length + 1; i++) {
                    if ($("#CG" + i).text() != "") {
                        CJinE = parseFloat(CJinE) + parseFloat($("#CG" + i).text());
                        $("#CJinE").text(parseFloat(CJinE));
                    }
                }
            }
        }
        //重置
        function Clear(data) {
            $("#JieSuanModal span").text("");
            $("#formBalance").resetForm();
            $("#fromInsurance").resetForm();
            InsuranceNum(), Datetime();
            $("#WShiShou").text("0.00");
            $("#CJinE").text("0.00");
            $("#myVue tbody tr td").parent("tr").remove();
            if (data == 0) {
                NotAuditEvent();
            }
        }
        //夸页面回填数据
        function MianDatas()
        {
            if (ReceptionID != "") {
                $.getJSON("${cxt}/servlet/InsuranceServlet?fun=selectInsurances&ReceptionID=" + ReceptionID, function (data) {
                    data=data.list[0];
                    DanHaoData(data);
                })
            } else {
                SeleceDanJu();
            }
            $.post("${cxt}/servlet/InsuranceServlet?fun=clearInsurance");
        }
        /********************主页面---开始******************************/
        //删除数据
        function DelectInsurance() {
            var InsuranceID = $("#InsuranceID").val();
            if (InsuranceID != 0) {
                $.post("${cxt}/servlet/InsuranceServlet?fun=delectInsurance&InsuranceID=" + InsuranceID, function (data) {
                    if (data) {
                        layer.msg("删 除 成 功 ！");
                        SeleceDanJu();
                        Clear();
                    } else {
                        layer.msg("删 除 失 败 ！");
                    }
                })
            } else {
                layer.alert('没有数据可删除！', {
                    skin: 'layui-layer-lan' //样式类名
                          , closeBtn: 0, anim: 4 //动画类型
                });
            }
        }
        //保存主信息
        function BavaMian() {
            var ArrInsurance = [], ArrInsuranceDetail = [];
            var MaintenanceNum = $("#MaintenanceNum").val();
            var OpenDate = $("#OpenDate").val();
            var BalanceDate = $("#BalanceDate").val();
            var InsuranceComID = $("#InsuranceComID").val();
            var e = new Insurance();
            e.InsuranceID = $("#InsuranceID").val();
            e.InsuranceDetailID = $("#InsuranceDetailID").val();
            e.InsuranceNum = $("#InsuranceNum").val();
            e.OpenDate = $("#OpenDate").val();
            e.BalanceDate = $("#BalanceDate").val();
            e.ClaimsStaff = $("#ClaimsStaff").val();
            e.ToTicket = $("#ToTicket").val();
            e.Remark = $("#Remark").val();
            e.Amount = $("#Amount").val();
            e.ToAudit = $("#ToAudit").val();
            ArrInsurance.push(e);
            var i = new InsuranceDetail();
            i.InsuranceDetailID = $("#InsuranceDetailID").val();
            i.ReceptionID = $("#ReceptionID").val();
            i.InsuranceComID = $("#InsuranceComID").val();
            i.ReportNum = $("#ReportNum").val();
            i.PolicyNum = $("#PolicyNum").val();
            i.PolicyMoney = $("#PolicyMoney").val();
            i.InsuranceMoney = $("#InsuranceMoney").val();
            ArrInsuranceDetail.push(i);
            if (MaintenanceNum == "") {
                layer.alert("维修单号不能为空！")
            } else if (OpenDate == "") {
                layer.alert("开单日期不能为空！")
            } else if (BalanceDate == "") {
                layer.alert("结算日期不能为空！")
            } else if (InsuranceComID == 0) {
                layer.alert("请选择索赔厂家！")
            } else {
                $.post("${cxt}/servlet/InsuranceServlet?fun=bavaInsurance", { PW_Insurance: JSON.stringify(ArrInsurance), SYS_InsuranceDetail: JSON.stringify(ArrInsuranceDetail) }, function (data) {
                    if (data >0 ) {
                        SeleceDanJu()
                        $("#InsuranceID").val(data);
                        if (data != false) {
                            layer.confirm('保存成功！是否立即审核该单据？', {
                                btn: ['是', '否'] //按钮
                            }, function () {
                                TOAudit();//审核方法
                            }, function () {
                                layer.msg('数 据 保 存 成 功 ！', {
                                    time: 20000, //20s后自动关闭
                                    btn: ['知道了']
                                });
                            });
                        } else {
                            layer.msg('数 据 保 存 失 败！', {
                                time: 2000, //20s后自动关闭
                            });
                        }
                    }
                })
            }
        }
        //主页面审核
        function TOAudit() {
            var InsuranceID = $("#InsuranceID").val();
            if (InsuranceID != "") {
                $("#ToAudit").val("true");
                $.post("${cxt}/servlet/InsuranceServlet?fun=toAudit&InsuranceID=" + InsuranceID, function (data) {
                    if (data > 0) {
                        layer.alert('审核成功，数据不能调整！', {
                            skin: 'layui-layer-molv' //样式类名
                      , closeBtn: 0
                        });
                        SeleceDanJu();
                        AuditEvent();
                    } else {
                        layer.alert('审核失败，请重新审核！', {
                            skin: 'layui-layer-lan' //样式类名
                      , closeBtn: 0, anim: 4 //动画类型
                        });
                    }
                })
            } else {
                layer.alert('请填写完整信息保存后再进行审核操作！', {
                    skin: 'layui-layer-lan' //样式类名
                      , closeBtn: 0, anim: 4 //动画类型
                });
            }
        }
        //主页面反审核
        function TONotAudit() {
            $("#ToAudit").val("false");
            var InsuranceNum = $("#InsuranceNum").val();
            $.getJSON("${cxt}/servlet/ClaimsetServlet?fun=selectBalance&BusinessNum=" + InsuranceNum, function (data) {
                data=data.list;
                if (data.state && data.states != "A") {
                    $.post("${cxt}/servlet/InsuranceServlet?fun=toNotAudit&InsuranceID=" + $("#InsuranceID").val(), function (data) {
                        if (data > 0) {
                            layer.alert('已取消审核，可以进行数据修改啦！', {
                                skin: 'layui-layer-molv' //样式类名
                          , closeBtn: 0
                            });
                            SeleceDanJu();
                            NotAuditEvent();
                        } else {
                            layer.alert('取消审核失败，请重新操作！', {
                                skin: 'layui-layer-lan' //样式类名
                          , closeBtn: 0, anim: 4 //动画类型
                            });
                        }
                    })
                } else {
                    layer.alert(data.objData, {
                        skin: 'layui-layer-lan' //样式类名
                 , closeBtn: 0, anim: 4 //动画类型
                    });
                }
            })
        }
        //审核按钮状态
        function AuditEvent() {
            $("#btnDelect").attr("disabled", true);
            $("#btnBave").attr("disabled", true);
            $("#btnColle").attr("disabled", false);
            $("#btnAudit").attr("disabled", true);
            $("#btnNotAudit").attr("disabled", false);
            $("#fromInsurance input").attr("disabled", true);
            $("#fromInsurance select").attr("disabled", true);
            $("#fromInsurance button").attr("disabled", true);
        }
        //反审核按钮状态
        function NotAuditEvent() {
            $("#btnDelect").attr("disabled", false);
            $("#btnBave").attr("disabled", false);
            $("#btnColle").attr("disabled", true);
            $("#btnAudit").attr("disabled", false);
            $("#btnNotAudit").attr("disabled", true);
            $("#fromInsurance input").attr("disabled", false);
            $("#fromInsurance select").attr("disabled", false);
            $("#fromInsurance button").attr("disabled", false);
            $(".disabl").attr("disabled", true);

        }
        //判断是审核否
        function ToFales() {
            if ($("#ReceptionID").val() != 0) {
                if ($("#ToAudit").val() == "false" || $("#ToAudit").val() == false) {
                    NotAuditEvent();
                } else {
                    AuditEvent();
                }
            }
        }

        /********************主页面---结束******************************/

        /********************修单号模态框---开始******************************/
        //维修单号模态框
        function ShowDanHao() {
            $("#DanHaoModal").modal("show");
        }
        //维修单号表格查询
        function SeleceDanHao() {
            var HMaintenanceNum = $("#HMaintenanceNum").val();
            var HCarNum = $("#HCarNum").val();
            var HDocumentStateID = $("#HDocumentStateID").val();
            var HBalanceStateID = $("#HBalanceStateID").val();
            var HToAudit = $("#HToAudit").val();
            if (HToAudit == undefined || HToAudit == null) HToAudit = "";
            if (HMaintenanceNum == undefined) HMaintenanceNum = "";
            if (HCarNum == undefined) HCarNum = "";
            if (HDocumentStateID == "" || HDocumentStateID == null) HDocumentStateID = 0;
            if (HBalanceStateID == "" || HBalanceStateID == null) HBalanceStateID = 0;
            tbDanHao.search({
                MaintenanceNum: HMaintenanceNum, ToAudit: HToAudit, BalanceStateID: HBalanceStateID,
                CarNum: HCarNum, DocumentStateID: HDocumentStateID,
            });
        }
        //回填明细表信息
        function MianData(record) {
            Clear();
            $.getJSON("${cxt}/servlet/InsuranceServlet?fun=selectReceptionDetail&ReceptionID=" + record.receptionID, function (data) {
                data=data.list;
                $("#DanHaoModal").modal("hide");
                $.each(data[0], function (i) {
                    var tbWeiXiu = $("#tbWeiXiu");
                    var length = $("#tbWeiXiu tbody tr").last().prevObject.length;
                    var tr = $("<tr><td>" + length + "</td><td><select id=WA" + length + " disabled></select></td><td><select id=WB" + length + " disabled ></select></td>"
                             + "<td id=WC" + length + ">" + data[0][i].repairCharge + ".00" + "</td><td id=WD" + length + " >" + data[0][i].discount + "</td>"
                             + "<td  id=WE" + length + ">" + data[0][i].amountPaid + ".00" + "</td><td><select id=WF" + length + " disabled></select></td></tr>");
                    tbWeiXiu.append(tr);
                    DetailEvent(length, 1);
                    $("#WA" + length).val(data[0][i].repairItemID);
                    $("#WF" + length).val(data[0][i].maintainabilityID);
                    var RepairItemID = $("#WA" + length).val();
                    $.ajaxSettings.async = false;
                    $.getJSON("${cxt}/servlet/CommonServlet?fun=selectRepairItemChange&RepairItemID=" + RepairItemID, function (data) {
                        data=data.list;
                        $("#WB" + length).val(data.maintenanceID);
                    })
                })
                $.each(data[1], function (i) {
                    var tbChanPin = $("#tbChanPin");
                    var length = $("#tbChanPin tbody tr").last().prevObject.length;
                    var tr = $("<tr><td>" + length + "</td><td id=CA" + length + ">" + data[1][i].fittingsCode + "</td>"
                        + "<td id=CB" + length + ">" + data[1][i].fittingsName + "</td><td><select id=CC" + length + " disabled></select></td>"
                        + "<td id=CD" + length + ">" + data[1][i].vehicleType + "</td><td id=CE" + length + ">" + data[1][i].quantity + ".00" + "</td>"
                        + "<td id=CF" + length + ">" + data[1][i].systemUnit + "</td><td id=CG" + length + ">" + data[1][i].unitPrice + ".00" + "</td>"
                        + "<td><select id=CH" + length + " disabled ></select></td></tr>");
                    tbChanPin.append(tr);
                    DetailEvent(length, 2);
                    $("#CH" + length).val(data[1][i].maintainabilityID);
                })
            })
        }
        //单号回填
        function DanHaoData(record) {
           
            var InsuranceDetailID = record.insuranceDetailID;
            $.getJSON("${cxt}/servlet/InsuranceServlet?fun=selectInsuranceData&InsuranceDetailID=" + InsuranceDetailID, function (data) {
                data=data.list[0];
                if (data != null) {
                    DanJuData(data);
                } else {
                    MianData(record);
                    $("#ReceptionID").val(record.receptionID);
                    $("#Owner").val(record.owner);
                    $("#VehicleType").val(record.vehicleType);
                    $("#CarNum").val(record.carNum);
                    $("#MaintenanceNum").val(record.maintenanceNum);
                    $("#InsuranceComID").val(record.insuranceComID);
                    $("#CustomerNum").val(record.customerNum);
                    $("#ReportNum").val(record.reportNum);
                    $("#PolicyNum").val(record.policyNum);
                    $("#PolicyMoney").val(record.policyMoney);
                    $("#InsuranceMoney").val(record.insuranceMoney);
                    $("#InsuranceDetailID").val(record.insuranceDetailID);
                    var WShiShou = $("#WShiShou").text();
                    var CJinE = $("#CJinE").text();
                    $("#Amount").val(parseFloat(WShiShou) + parseFloat(CJinE));
                    $("#DanHaoModal").modal("hide");    
                }
            })
        }
        /********************修单号模态框---结 束******************************/

        /********************单据模态框---开始******************************/
        //选择选择单据按钮弹出模态框
        function ShowDanJu() {
            $("#DanJuModal").modal("show");
        }
        //单据表格查询
        function SeleceDanJu() {
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
                MaintenanceNum: DMaintenanceNum, ToAudit: DToAudit, BalanceStateID: DBalanceStateID,
                CarNum: DCarNum, DocumentStateID: DDocumentStateID,
            });
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
        //选择单据回填
        function DanJuData(record) {
            MianData(record);
            $("#ReceptionID").val(record.receptionID);
            $("#InsuranceID").val(record.insuranceID);
            $("#InsuranceDetailID").val(record.insuranceDetailID);
            $("#InsuranceNum").val(record.insuranceNum);
            $("#OpenDate").val(record.openDate);
            $("#BalanceDate").val(record.balanceDate);
            $("#CustomerNum").val(record.customerNum);
            $("#MaintenanceNum").val(record.maintenanceNum);
            $("#ClaimStaff").val(record.claimStaff);
            $("#CarNum").val(record.carNum);
            $("#Owner").val(record.owner);
            $("#VehicleType").val(record.vehicleType);
            $("#ClaimsStaff").val(record.claimStaff);
            $("#ToTicket").val(record.toTicket==true?"true":"false");
            $("#Remark").val(record.describe);
            $("#Amount").val(record.amount);
            $("#ToAudit").val(record.toAudit);
            $("#InsuranceComID").val(record.insuranceComID);
            $("#ReportNum").val(record.reportNum);
            $("#PolicyNum").val(record.policyNum);
            $("#PolicyMoney").val(record.policyMoney);
            $("#InsuranceMoney").val(record.insuranceMoney);
            $("#DanJuModal").modal("hide");
            ToFales();
            if (record.toAudit == true) {
                $("#ToAudits").attr("checked", true);
            } else {
                $("#ToAudits").removeAttr("checked");
            }
        }
        /********************结算单模态框---开始*******************************/
        function ShowJieSuan() {
            $.getJSON("${cxt}/servlet/ClaimsetServlet?fun=selectBalance&BusinessNum=" + $("#InsuranceNum").val(), function (data) {
                data=data.list;
                if (data.state) {
                    if ($("#Amount").val() < 0) {
                        layer.alert("应收金额小于0,不能继续收款!")
                    }
                    else {
                        $("#BalanceID").val(data.text);
                        JieSuanData();
                        $("#JieSuanModal").modal("show");
                    }
                } else {
                    layer.alert(data.text, {
                        skin: 'layui-layer-lan' //样式类名
                 , closeBtn: 0, anim: 4 //动画类型
                    });
                }
            })
        }
        //数据回填
        function JieSuanData() {
            var BalanceID = $("#BalanceID").val();
            $("#JOwner").text($("#Owner").val());
            $("#JCustomerNum").text($("#CustomerNum").val())
            $("#JBalanceDate").text($("#OpenDate").val());
            $("#JMaintenanceNum").text($("#InsuranceNum").val());
            $("#JShouldAmount").text($("#Amount").val());//应收金额
            $("#JOptimalAmount").text("0.00");//已优惠金额
            $("#JCollectionAmount").text("0.00");//已收金额
            $("#OptimalAmount").val("");
            $("#PaymentID").val(0);
            $("#Remark").val("");
            if (BalanceID != "") {
                $.getJSON("${cxt}/servlet/ClaimsetServlet?fun=selectBalances&BalanceID=" + BalanceID, function (data) {
                    data=data.list;
                    var NoAmount = parseFloat(data.shouldAmount - data.collectionAmount - data.optimalAmount);//未收金额
                    $("#JCollectionAmount").text(data.collectionAmount);//已收金额
                    $("#JOptimalAmount").text(data.optimalAmount);//已优惠金额
                    $("#JNoAmount").text(NoAmount);//已优惠金额
                    $("#CollectionAmount").val(NoAmount);//本次收款金额
                    $("#PaymentID").val(data.paymentID);//付款方式
                    $("#Remark").val(data.remark);//备注
                })
            } else {
                $("#JNoAmount").text(parseFloat($("#Amount").val() - $("#JCollectionAmount").text()));
                $("#CollectionAmount").val($("#Amount").val());
            }
        }
        //优惠金额文本框
        $("#OptimalAmount").keyup(function () {
            var OptimalAmount = $("#OptimalAmount").val();
            var CollectionAmount = $("#CollectionAmount").val();
            if (OptimalAmount == "") OptimalAmount = 0;
            $("#CollectionAmount").val(parseFloat(parseFloat($("#JNoAmount").text()) - parseFloat(OptimalAmount)));
        })
        //结算保存
        function btnBaveJieSuan() {
            var ArrBalance = [];
            var e = new Balance();
            e.BalanceID = $("#BalanceID").val();
            e.PaymentID = $("#PaymentID").val();
            e.ReceptionID = $("#ReceptionID").val();
            e.BusinessNum = $("#InsuranceNum").val();
            e.ShouldAmount = $("#Amount").val();
            e.OptimalAmount = $("#OptimalAmount").val();
            e.CollectionAmount = $("#CollectionAmount").val();
            e.Remark = $("#Remark").val();
            ArrBalance.push(e);
            if ($("#OptimalAmount").val() == "") $("#OptimalAmount").val("0");
            if ($("#PaymentID").val() == 0) {
                layer.tips('请选择入账方式!', '#PaymentID');
            } else {
                $.post("${cxt}/servlet/ClaimsetServlet?fun=baveBalance", { PW_Balance: JSON.stringify(ArrBalance) }, function (data) {
                    if (data>0) {
                        layer.msg("结 算 成 功 !");
                        SeleceDanJu();
                        $("#JieSuanModal").modal("hide");
                    } else {
                        layer.msg("结 算 失 败 !");
                    }
                })
            }
        }
        /********************结算单模态框---结束*******************************/
        //索赔表
        function Insurance(InsuranceID, InsuranceDetailID, InsuranceNum, OpenDate, BalanceDate, ClaimsStaff,
            ToTicket, Remark, Amount, ToAudit) {
            this.InsuranceID = InsuranceID;
            this.InsuranceDetailID = InsuranceDetailID;
            this.InsuranceNum = InsuranceNum;
            this.OpenDate = OpenDate;
            this.BalanceDate = BalanceDate;
            this.ClaimsStaff = ClaimsStaff;
            this.Remark = Remark;
            this.Amount = Amount;
            this.ToAudit = ToAudit;
            this.ToTicket = ToTicket;
        }
        //索赔明细表
        function InsuranceDetail(InsuranceDetailID, ReceptionID, InsuranceComID, ReportNum, PolicyNum, PolicyMoney,
            InsuranceMoney) {
            this.InsuranceDetailID = InsuranceDetailID;
            this.ReceptionID = ReceptionID;
            this.InsuranceComID = InsuranceComID;
            this.ReportNum = ReportNum;
            this.PolicyNum = PolicyNum;
            this.PolicyMoney = PolicyMoney;
            this.InsuranceMoney = InsuranceMoney;
        }
        //结算单
        function Balance(BalanceID, PaymentID, ReceptionID, ShouldAmount, OptimalAmount, CollectionAmount, Remark, BusinessNum) {
            this.BalanceID = BalanceID;//结算ID
            this.PaymentID = PaymentID;//付款方式ID
            this.ReceptionID = ReceptionID;//接待客户ID
            this.ShouldAmount = ShouldAmount;//应收金额
            this.BusinessNum = BusinessNum;//备注
            this.OptimalAmount = OptimalAmount;//优惠金额
            this.CollectionAmount = CollectionAmount;//收款金额
            this.Remark = Remark;//备注
        }
    </script>
</body>
</html>