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
    <title>Settlement</title>
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
    <!-- @*CSS皮肤(需引用于bsgrid.all.min.css之后)*@ -->
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
            height:32px;
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

        table tr th, table tr td:hover {
            background: #ffffff;
        }

       #myVue table tr th, #myVue table tr td {
            padding: 5px;
            width: 300px;
            text-align: center;
            font-size:14px;
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
            height: 82%;
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
            height: 82%;
            float: left;
            padding: 10px;
            border: 3px solid #f5f5f5;
            box-shadow: 0 0 20px 1px #eee;
            overflow:auto;
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
        font-size:12px;
        }
       #JieSuanModal .span {
            display:inline-block;
            width:150px;
        margin-right:20px
        }
        #JieSuanModal label {
        width:105px
        }
        #imgCarPicture {
            display:block;
            width:250px;
            height:295px;
           border-radius: 5px;
          box-shadow: 0 0 20px 3px #eee;
        }
        .seinput {
        position:relative;bottom:41px;width:78%;border:none;left:12px;height:66%;
        }
          #FittingsType {
            padding:15px;
        }
            #FittingsType ul {
                text-align: left;
            }
        #FittingsType li{
        text-align:left;
        line-height:32px;
        font-size:18px;
        }
            #FittingsType li:hover {
            background:#eee;
            cursor:alias;
            }
            #FittingsType li i {
                display: inline-block;
                background: url('Content/Main/images/smiley_evil.png') no-repeat 0px 5px;
                width: 20px;
                height: 20px;
            }
            #FittingsType li .diai {
                margin-left: 23px;
            }

        .active {
            background: #f5f5f5;
        }
         .form-control[disabled] ,html input[disabled],textarea{
            background:ghostwhite;
        }
    </style>
</head>
<body>
    <!-- @*导航栏*@ -->
    <nav class="breadcrumb" style="margin:0px">
        <i class="Hui-iconfont"></i> <a href=""> 首 页 </a> <span class="c-gray en">&gt;</span> <a href=""> 汽 修 管 理 </a>
        <span class="c-gray en">&gt;</span><a href=""> 结 算 出 厂</a>
        <a class="btn btn-success radius r" style="line-height:1.6em;top:0px;position:absolute;right:20px" href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont"></i>
        </a>
    </nav>
    <!-- @*按钮栏*@ -->
    <div class="cl pd-5 bg-1 bk-gray mt-20" style="padding:10px;margin:0px">
        <button class="btn btn-info "onclick="btnMainSava()"id="btnBave"><i class="Hui-iconfont">&#xe632;</i> 保 存</button>
        <button class="btn btn-danger "onclick="btnMainDelect()" id="btnDelect"><i class="Hui-iconfont">&#xe609;</i> 删 除</button>
        <button class="btn btn-success " onclick="ShowDanJu()"><i class="Hui-iconfont">&#xe676;</i> 查询单据</button>
        <button class="btn btn-info "onclick="ShowJieSuan()"id="btnColle"><i class="Hui-iconfont">&#xe692;</i> 收 款</button>
        <button class="btn btn-warning "onclick="ToAudit()"id="btnAudit"><i class="Hui-iconfont">&#xe634;</i> 审 核</button>
        <button class="btn btn-warning "onclick="ToNotAudit()"id="btnNotAudit"><i class="Hui-iconfont">&#xe634;</i> 反 审 核</button>
    </div>
    <!-- @*控件栏*@ -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-weight:inherit">结 算 出 厂</legend>
        <div class="layui-field-box">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <form class="form-horizontal" role="form" style="margin-top:2px;" id="formReception">
                        <input type="text" class="form-control" hidden id="ToSendWork" name="ToSendWork" value="false"><!-- @*派工否*@ -->
                        <input type="text" id="ReceptionID" hidden><!-- @*客户维修ID*@ -->
                        <input type="text" id="CustomerNum" hidden><!-- @*客户编码*@ -->
                        <input type="text" id="ToCompletion" hidden><!-- @*完工状态*@ -->
                        <input type="text" id="CollageState" hidden><!-- @*领料状态*@ -->
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车牌:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <div class="input-group" style="width:104%">
                                    <input type="text" class="form-control" id="CarNum" name="CarNum" maxlength="7">
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="button" onclick="ShowCarNum()"><i class="Hui-iconfont">&#xe70f;</i></button>
                                    </span>
                                </div>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车型:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12" style="height:45px;">
                                <select class="form-control" id="VehicleTypeID"></select>
                                <input type="text" style="" class="seinput" id="VehicleType" name="VehicleType" maxlength="10">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">接车人:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12" style="height:45px;">
                                <select class="form-control" id="CarderID"></select>
                                <input type="text" style="" class="seinput" id="Carder">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车主:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12" style="height:45px;">
                                <input type="text"class="form-control"  id="Owner" name="Owner" maxlength="5">
                            </div>
                            <div>
                                <div class="col-lg-1 col-md-1 col-xs-12">
                                    <label class="control-label" style="text-shadow: 1px 1px 5px gray;">维修单号:</label>
                                </div>
                                <div class="col-lg-2 col-md-3 col-xs-12">
                                    <p style="text-decoration:underline;padding:6px 0;font-size:16px" id="MaintenanceNum"></p>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">客户地址:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="Address" maxlength="15">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">客户来源:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <select class="form-control" id="CustomerSouID"></select>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车主电话:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="OwnerTele" maxlength="15">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">发动机号码:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="EngineNum" maxlength="15">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">送修人:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="Repairman" maxlength="5">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">送修人电话:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="RepairmanTele" maxlength="15">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">工作卡:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="SelfCoding" maxlength="15">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">修理类别:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <select class="form-control" id="RepairID"></select>
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车架号码:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="FrameNum" maxlength="15">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">进厂里程:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <div class="input-group" style="width:104%">
                                    <input type="text" class="form-control" id="Mileage">
                                    <span class="input-group-btn">
                                        <button class="btn btn-info" type="button" disabled>K M</button>
                                    </span>
                                </div>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">开单日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control Datetime" id="OpenDate">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">进厂日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control Datetime" id="FactoryDate">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">结算日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control Datetime" id="BalanceDate">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">总金额:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control Disabl"  id="Amount" >
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">应收金额:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control" id="AmountPaid">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <input type="checkbox" value="false" style="height: 20px;width:20px;position:relative;bottom:3px;" class="" id="ToAudit" hidden />
                                <label class="control-label"  style="position:relative;bottom:5px;left:30px">审核:<input type="checkbox"  id="ToAudits" style="position:relative;top:4px;left:10px;width:20px;height:20px" /></label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </fieldset>
    <!-- @*切换卡*@ -->
    <div class="col-lg-12" id="myVue" style="margin:15px">
        <el-tabs type="border-card">
            <el-tab-pane label="维修项目">
                <div id="WeiXiuBox" class="Boxs">
                    <table id="tbWeiXiu">
                        <tbody>
                            <tr>
                                <th width='10%' height='20px'>序号</th>
                                <th width='25%' height='20px'>修理项目</th>
                                <th width='10%' height='20px'>维修工艺</th>
                                <th width='10%' height='20px'>修理费</th>
                                <th width='10%' height='20px'>折扣</th>
                                <th width='10%' height='20px'>实收金额</th>
                                <th width='10%' height='20px'>维修性质</th>
                                <th width='25%' height='20px'>备注</th>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>合计：</td>
                                <td></td>
                                <td></td>
                                <td id="WXiuLiFei">0.00</td>
                                <td></td>
                                <td id="WShiShou">0.00</td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </el-tab-pane>
            <el-tab-pane label="估计配件">
                <div id="ChanPinBox" class="Boxs">
                    <table id="tbChanPin">
                        <tbody>
                            <tr>
                                <th width='10%' height='20px'>序号</th>
                                <th width='25%' height='20px'>配件编号</th>
                                <th width='10%' height='20px'>配件名称</th>
                                <th width='10%' height='20px'>数量</th>
                                <th width='10%' height='20px'>单位</th>
                                <th width='10%' height='20px'>单价</th>
                                <th width='10%' height='20px'>总金额</th>
                                <th width='10%' height='20px'>维修性质</th>
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
                                <td id="CJinE">0.00</td>
                                <td></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </el-tab-pane>
            <el-tab-pane label="其他费用">
                <div id="FeiYong" style="" class="Boxs">
                    <table id="tbFeiYong">
                        <tbody>
                            <tr>
                                <th width='10%' height='20px'>序号</th>
                                <th width='25%' height='20px'>费用名称</th>
                                <th width='10%' height='20px'>金额</th>
                                <th width='10%' height='20px'>折扣</th>
                                <th width='10%' height='20px'>实收金额</th>
                                <th width='25%' height='20px'>备注</th>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>合计：</td>
                                <td></td>
                                <td id="QJinE">0.00</td>
                                <td></td>
                                <td id="QShiShou">0.00</td>
                                <td></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </el-tab-pane>
            <el-tab-pane label="本单备注">
                <textarea style="width:99.5%;height:100px;border:none" placeholder="请输入汽车的故障信息..." id="Describe"></textarea>
            </el-tab-pane>
            <el-tab-pane label="保险索赔">
                <div id="Insurance" class="ClaimBox">
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-size:16px">保 险 理 赔</legend>
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <form class="form-horizontal" role="form" style="margin-top:2px;" id="formInsuranceDetail">
                                <input id="InsuranceDetailID" type="hidden" name="InsuranceDetailID" />
                                <div class="form-group">
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <label class="control-label">报案编号:</label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-xs-12">
                                        <input type="text" class="form-control InsuranceDetail" id="ReportNum" name="ReportNum">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <label class="control-label">保险公司:</label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-xs-12">
                                        <select class="form-control InsuranceDetail" id="InsuranceComID" name="InsuranceComID"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <label class="control-label">保单号:</label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-xs-12">
                                        <input type="text" class="form-control InsuranceDetail" id="PolicyNum" name="PolicyNum">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <label class="control-label">保险金额:</label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-xs-12">
                                        <input type="text" class="form-control InsuranceDetail" id="PolicyMoney" name="PolicyMoney">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <label class="control-label">保险理赔金额:</label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-xs-12">
                                        <input type="text" class="form-control Disabl" id="InsuranceMoney" name="InsuranceMoney" >
                                    </div>
                                </div>
                            </form>
                        </div>
                    </fieldset>
                </div>
                <div id="Claim" class="ClaimBox">
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-size:16px">三 包 索 赔</legend>
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <form class="form-horizontal" role="form" style="margin-top:2px;" id="formThreePacksDetail">
                                <input id="ThreePacksDetailID" type="hidden" name="ThreePacksDetailID" />
                                <div class="form-group">
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <label class="control-label">索赔厂家:</label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-xs-12">
                                        <select class="form-control" id="ClaimComID" name="ClaimComID"></select>
                                    </div>
                                </div>
                                <div class="form-group" style="margin-bottom:200px">
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <label class="control-label">三包索赔金额:</label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-xs-12">
                                        <input type="text" class="form-control Disabl" id="ClaimMoney" name="ClaimMoney" >
                                    </div>
                                </div>
                            </form>
                        </div>
                    </fieldset>
                </div>
                <div>
                    <button class="btn btn-info " onclick="InsuranceData()"><i class="Hui-iconfont">&#xe632;</i> 保险理赔收款</button>
                </div>
                <div>
                    <button class="btn btn-info " onclick="ThreePacksData()"><i class="Hui-iconfont">&#xe632;</i> 三包索赔收款</button>
                </div>
            </el-tab-pane>
        </el-tabs>
    </div>
   <!--  @*右击菜单*@ -->
    <ul id="menuOne" class="box">
        <li onclick="addOne()"><i class="glyphicon glyphicon-plus"></i> 添 加</li>
        <li onclick="deleteOne()"><i class="glyphicon glyphicon-trash"></i> 删 除</li>
    </ul>
    <ul id="menuThree" class="box">
        <li onclick="addThree()"><i class="glyphicon glyphicon-plus"></i> 添 加</li>
        <li onclick="deleteThree()"><i class="glyphicon glyphicon-trash"></i> 删 除</li>
    </ul>
   <!--  @*车辆模态框*@ -->
    <div class="modal fade col-lg-12" id="CarNumModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        车辆选择
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="navsearch">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <form class="form-horizontal" role="form" style="margin-top:2px;">
                                <div class="col-lg-2 col-md-2 col-sm-2 text-right">
                                    <label class="control-label">车 牌 :</label>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-12 reset" style="margin-top:3px">
                                    <input type="text" class="form-control" id="LicenseCode" onkeyup="" />
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-2 text-right" style="margin-left:20px">
                                    <button type="button" class="btn btn-search" onclick="SeleceMaintenanceCus()"><i class="glyphicon glyphicon-search"></i>查 询</button>
                                </div>
                            </form>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <button type="button" class="btn btn-info" onclick="Dblclick()"><i class="glyphicon glyphicon-ok-sign"></i>选 中</button>
                            <button type="button" class="btn btn-success" onclick="ClearValue()"><i class="glyphicon glyphicon-plus"></i>新 增</button>
                            <button type="button" class="btn btn-warning" onclick="btnSave()"><i class="glyphicon glyphicon-file"></i>保 存</button>
                            <button type="button" class="btn btn-primary" id=""><i class="glyphicon glyphicon-refresh"></i>刷 新</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>关 闭</button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div>
                        <div class="modOne">
                            <table id="tbKeHu" ondblclick="Dblclick()">
                                <tr>
                                    <th w_num="total_line" w_index="maintenanceCusID">序号</th>
                                    <th w_index="customerNum">客户编号</th>
                                    <th w_index="owner">车主</th>
                                    <th w_index="licenseCode">车牌号码</th>
                                </tr>
                            </table>
                        </div>
                        <div class="modTow col-lg-12 col-md-12 col-sm-12">
                            <form class="form-horizontal" role="form" id="formMaintenanceCus"
                                  action="${cxt}/commonController/updateMaintenanceCus.action" method="post">
                                <input id="KMaintenanceCusID" type="text" name="MaintenanceCusID" hidden />
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">车主:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KOwner" name="Owner" maxlength="5">
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">生日:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control Date" id="KBirthday" name="Birthday">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">身份证号:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KIdNumber" name="IdNumber" maxlength="18">
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">车主手机:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KMobilePhone" name="MobilePhone" maxlength="15">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">车牌号码:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KLicenseCode" name="LicenseCode" maxlength="7">
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">车型:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12" style="height:36px">
                                        <select class="form-control" id="KVehicleTypeID" name="VehicleTypeID"></select>
                                        <input type="text" style="bottom:36px;left:-20px;height:66%" class="seinput" id="KVehicleType" name="VehicleType" maxlength="10">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">发动机号:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KEngineNum" name="EngineNum" maxlength="15">
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">车架号:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KFrameNum" name="FrameNum" maxlength="15">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">送修人:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KRepairMan" name="RepairMan" maxlength="5">
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">送修人电话:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KRepairTele" name="RepairTele" maxlength="15">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">保险公司:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <select class="form-control" id="KInsuranceComID" name="InsuranceComID"></select>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">保险种类:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <select class="form-control" id="KInsuranceSpeID" name="InsuranceSpeID"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">保险起始日:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control Date" id="KInitialStartDate" name="InitialStartDate">
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">保险终止日:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control Date" id="KInitialEndDate" name="InitialEndDate">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">地址:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KAddress" name="Address" maxlength="15">
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">客户编号:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KCustomerNum" name="CustomerNum" maxlength="20"disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">行驶证年审:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control Date" id="KDriveDate" name="DriveDate">
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">所属区域:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <select class="form-control" id="KRegionID" name="RegionID"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">所属部门:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <select class="form-control" id="KDepartmentID" name="DepartmentID"></select>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">所属员工:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <select class="form-control" id="KCarderID" name="CarderID"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">客户等级:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <select class="form-control" id="KCustomerLevelID" name="CustomerLevelID"></select>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">客户来源:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <select class="form-control" id="KCustomerSouID" name="CustomerSouID"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">客户类别:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <select class="form-control" id="KCustomerTypeID" name="CustomerTypeID"></select>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">驾驶证年审:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control Date" id="KDrivingDate" name="DrivingDate">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label">录入人:</label>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-xs-12">
                                        <input type="text" class="form-control" id="KInputPerson" name="InputPerson" maxlength="5">
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-xs-12">
                                        <label class="control-label" style="position:relative;bottom:20px;left:20px">
                                            修改:
                                            <input type="checkbox" style="position:relative;top:9px;left:10px" id="check" />
                                        </label>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
   <!--  @*选择单据模态框*@ -->
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
                                <th w_render="TosAudit">审核状态</th>
                                <th w_index="toAudit" w_hidden="true">审核状态</th>
                                <th w_index="documentState">单据状态</th>
                                <th w_index="balanceState">结算状态</th>
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
   <!--  @*结算模态框*@ -->
    <div class="modal fade col-lg-12" id="JieSuanModal">
        <div class="modal-dialog">
            <div class="modal-content"style="width: 520px;left:0">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        客户结算单
                    </h4>
                </div>
                <div class="modal-body"style="padding-top:0">
                    <fieldset class="layui-elem-field layui-field-box" style="margin-top: 20px;">
                        <legend style="font-size:16px">结算单收款说明</legend>
                        <p >温馨提示:如本次收款未收完或未收款，请在‘应收应付’窗口中选择对应单号‘到账’即可</p>
                    </fieldset>
                        <fieldset class="layui-elem-field layui-field-box" style="margin-top: 20px;">
                            <legend style="font-size:16px">客户付款情况</legend>
                            <p style="padding-left:10px">客户编号：<span class="span" id="JCustomerNum"></span>客户姓名：<span id="JOwner"></span></p>
                            <p style="padding-left:10px">单据时间：<span  class="span"id="JBalanceDate"></span>业务单号：<span id="JMaintenanceNum"></span></p>
                            <p style="padding-left:10px">应收金额：<span  class="span"id="JShouldAmount">0.00</span>已优惠金额：<span id="JOptimalAmount">0.00</span></p>
                            <p style="padding-left:10px">已收金额：<span  class="span"id="JCollectionAmount">0.00</span>未收金额：<span id="JNoAmount"></span></p>
                            <form class="form-horizontal" role="form" style="margin-top:2px;">
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                        <div class="form-group">
                                            <div class="col-lg-5 col-md-5 col-sm-5 ">
                                                <label class="control-label">本次优惠金额：</label>
                                            </div>
                                            <div class="col-lg-7 col-md-7 col-sm-7">
                                                <input type="text" class="form-control" id="OptimalAmount" value="0" onKeyUp="value = value.replace(/[^\d.]/g, '')" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-5 col-md-5 col-sm-5 ">
                                                <label class="control-label">本次收款金额：</label>
                                            </div>
                                            <div class="col-lg-7 col-md-7 col-sm-7">
                                                <input type="text" class="form-control" id="CollectionAmount" onkeyup="" onKeyUp="value = value.replace(/[^\d.]/g, '')"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-5 col-md-5 col-sm-5 ">
                                                <label class="control-label">入账方式：</label>
                                            </div>
                                            <div class="col-lg-7 col-md-7 col-sm-7">
                                                <select class="form-control"id="PaymentID"></select>
                                            </div>
                                        </div>

                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p style="line-height:24px;color:orangered">温馨提示：如本次收款未完成或未收款，请在‘财务管理’模块‘客户结算单’窗口中选择对应的客户名称即可</p>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <div class="form-group">
                                        <div class="col-lg-4 col-md-4 col-sm-4 "style="width:60px">
                                            <label class="control-label">备注：</label>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6">
                                            <input type="text" class="form-control" id="Remark" onkeyup=""style="width:205px" />
                                        </div>
                                    </div>
                                </div>
                            </form>
                    </fieldset>
                </div>
                <div class="modal-footer" style="border-top:none;">
                <div class="col-lg-12 col-md-12 col-sm-12 ">
                    <button class="btn btn-info "onclick="btnBaveJieSuan()"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
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
    <script src="${cxt}/Content/js/combobox.js"></script>
    <script>
        //初始化表格
        var tbKeHu, tbDanJu, tbShangPin;
        var ArrMaintainData = [], ArrExpensesData = [], ArrRepairItemData = [], ArrMaintenanceData = [], ArWarehouseData=[];
        var Chinese = /^[\u0391-\uFFE5]+$/;//只能输入中文
        var Number = /^[0-9]+$/; //只能输入数字
        var Numbers = /^[-\d.]+$/; //价格
        var Pattern = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;//车牌号码京K39006
        var Identity = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;//18位身份证号码
        var Phone = /^[1][0-9][0-9]{9}$/;//11位手机号码  //切换卡
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
        $(function () {
            ShowDanJu(), create();
            tbKeHu = $.fn.bsgrid.init('tbKeHu', {
                url: '${cxt}/commonController/seleceMaintenanceCus.action',
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
                            MaintenanceCusData(record);
                            $("#formMaintenanceCus select").attr("disabled", true);
                            $("#formMaintenanceCus input[type='text']").attr("disabled", true);
                        }
                    }
                }

            })
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
            tbShangPin = $.fn.bsgrid.init('tbShangPin', {
                url: '${cxt}/commonController/seleceFittingsInfo.action',
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
                            Check("#tbShangPin tr", rowIndex + 1);//复选框勾选
                        }
                    }
                }
            })
            SeleceReception();
        })
      
        //文本框与下拉框合并
        $("#VehicleTypeID").on("change", function () {
            var text = $(this).find("option:selected").text();
            $("#VehicleType").val(text)
        })
        $("#CarderID").on("change", function () {
            var text = $(this).find("option:selected").text();
            $("#Carder").val(text)
        })
        $("#KVehicleTypeID").on("change", function () {
            var text = $(this).find("option:selected").text();
            $("#KVehicleType").val(text)
        })
        //选择照片按钮
        $("#imgCarPicture").dblclick(function () {
            $("#CarPicture").click();
        })
        //读取照片
        var imgReader = new FileReader();
        regexImgeFilter = /^(?:image\/bmp|image\/png|image\/jpeg|image\/jpg)$/i;
        imgReader.onload = function (evt) {
            $("#imgCarPicture").attr('src', evt.target.result);
        }
        $("#CarPicture").change(function () {
            var imgfFile = $("#CarPicture").prop('files')[0];
            if (!regexImgeFilter.test(imgfFile.type)) {
                layer.msg('选择的不是一张有效的图片文件', { icon: 0 });
            }
            imgReader.readAsDataURL(imgfFile);
        });
        //右击事件
        $(function () {
            $("body").click(function () {
                resOne.style.display = 'none';
                resThree.style.display = 'none';
            })
            //阻止浏览器默认右键点击事件
            document.oncontextmenu = function () {
                return false;
            }
            var resOne = document.getElementById('menuOne');   //找到id为box的div
            var resThree = document.getElementById('menuThree');
            document.getElementById("WeiXiuBox").onmouseup = function (e) {   //在body里点击触发事件
                if (e.button === 2) {    //如果button=1（鼠标左键），button=2（鼠标右键），button=0（鼠标中间键）
                    resOne.style.top = e.clientY + 'px';   //鼠标点击时给div定位Y轴
                    resOne.style.left = e.clientX + 'px';  //鼠标点击时给div定位X轴
                    resOne.style.display = 'block';    //显示div盒子
                } else {
                    resOne.style.display = 'none';     //否则不显示div盒子
                }
            }
            document.getElementById("FeiYong").onmouseup = function (e) {   //在body里点击触发事件
                if (e.button === 2) {    //如果button=1（鼠标左键），button=2（鼠标右键），button=0（鼠标中间键）
                    resThree.style.top = e.clientY + 'px';   //鼠标点击时给div定位Y轴
                    resThree.style.left = e.clientX + 'px';  //鼠标点击时给div定位X轴
                    resThree.style.display = 'block';    //显示div盒子
                } else {
                    resThree.style.display = 'none';     //否则不显示div盒子
                }
            }
        })

        //添加维修项目行
        function addOne() {
            var tbWeiXiu = $("#tbWeiXiu");
            var length = $("#tbWeiXiu tbody tr").length;
            var tr = $("<tr><td>" + length + "</td><td><select id=WA" + length + "></select></td><td><select id=WB" + length + " disabled='disabled'></select></td>"
                + "<td onclick='tdclick(this)' id=WC" + length + ">0.00</td><td onclick='tdclick(this)' id=WD" + length + " >100</td>"
                + "<td  id=WE" + length + ">0.00</td><td><select id=WF" + length + "></select></td>"
                + "<td onclick='tdclick(this)' id=WG" + length + "></td><td hidden id=WH" + length + "></td></tr>");
            tbWeiXiu.append(tr);
            //绑定表格下拉框
            var length = $("#tbWeiXiu tbody tr").last().prevObject.length - 1;
            selectcreate("WA" + length, ArrRepairItemData);
            selectcreate("WB" + length, ArrMaintenanceData);
            selectcreate("WF" + length, ArrMaintainData);
            var RepairItemID = $("#WA" + length).val();
            if (RepairItemID != 0) {
                $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + RepairItemID, function (data) {
                    $("#WB" + length).val(data.maintenanceID);
                    $("#WC" + length).text(data.repairCharge + ".00");
                    $("#WE" + length).text(data.repairCharge + ".00");
                })
            }
            DetailEvent(length, 1);
            ToFales();
        }
        //删除维修项目行
        function deleteOne() {
            var length = $("#tbWeiXiu tbody").find("tr").length;
            if (length > 1) {
                $("#tbWeiXiu tbody").find("tr").last().remove();//找到最后一个选项然后删除
            }
            ClearDetailToT();
            DetailEvent(length, 1);
        }
        //添加其他费用行
        function addThree() {
            var tbFeiYong = $("#tbFeiYong");
            var length = $("#tbFeiYong tbody tr").length;
            var tr = $("<tr><td>" + length + "</td><td><select id=QA" + length + "></select></td><td onclick='tdclick(this)' id=QB" + length + ">0.00</td>"
                + "<td onclick='tdclick(this)' id=QC" + length + ">100</td><td onclick='tdclick(this)' id=QD" + length + ">0.00</td>"
                + "<td onclick='tdclick(this)' id=QE" + length + "></td><td hidden id=QF" + length + "></td></tr>");
            tbFeiYong.append(tr);
            var length = $("#tbFeiYong tbody tr").last().prevObject.length - 1;
            createSelect("QA" + length, "${cxt}/commonController/selectAppendOption.action?type=SYS_Expenses");
            var ExpensesID = $("#QA" + length).val();
            $.getJSON("${cxt}/commonController/selectExpensesChange.action?expensesID=" + ExpensesID, function (data) {
                $("#QB" + length).text(data.cost + ".00");
                $("#QD" + length).text(data.cost + ".00");
                $("#QE" + length).text();
            })
            DetailEvent(length, 3);
            ToFales()
        }
        //删除其他费用行
        function deleteThree() {
            var length = $("#tbFeiYong tbody").find("tr").length;
            if (length > 1) {
                $("#tbFeiYong tbody").find("tr").last().remove();//找到最后一个选项然后删除
            }
            ClearDetailToT();
            DetailEvent(length, 3);
        }

        //绑定下拉框
        function create() {
            $.ajaxSettings.async = false;
            createSelect("VehicleTypeID", "${cxt}/commonController/selectAppendOption.action?type=SYS_VehicleType");//车型下拉框
            createSelect("CarderID", "${cxt}/commonController/selectAppendOption.action?type=SYS_Carder");//接车人下拉框
            appendOption("RepairID", "${cxt}/commonController/selectAppendOption.action?type=SYS_Repair");//车型下拉框
            appendOption("CustomerSouID", "${cxt}/commonController/selectAppendOption.action?type=SYS_CustomerSou");//客户来源下拉框
            appendOption("InsuranceComID", "${cxt}/commonController/selectAppendOption.action?type=SYS_InsuranceCom");//保险公司下拉框
            appendOption("ClaimComID", "${cxt}/commonController/selectAppendOption.action?type=SYS_ClaimCom");//索赔厂家下拉框

            createSelect("KVehicleTypeID", "${cxt}/commonController/selectAppendOption.action?type=SYS_VehicleType");//车型下拉框
            appendOption("KInsuranceComID", "${cxt}/commonController/selectAppendOption.action?type=SYS_InsuranceCom");//保险公司下拉框
            appendOption("KInsuranceSpeID", "${cxt}/commonController/selectAppendOption.action?type=SYS_InsuranceSpe");//保险种类下拉框
            appendOption("KDepartmentID", "${cxt}/commonController/selectAppendOption.action?type=SYS_Department");//部门下拉框
            appendOption("KCarderID", "${cxt}/commonController/selectAppendOption.action?type=SYS_Carder");//接车人下拉框
            appendOption("KCustomerLevelID", "${cxt}/commonController/selectAppendOption.action?type=SYS_CustomerLevel");//客户等级下拉框
            appendOption("KCustomerTypeID", "${cxt}/commonController/selectAppendOption.action?type=SYS_CustomerType");//客户类型下拉框
            appendOption("KRegionID", "${cxt}/commonController/selectAppendOption.action?type=SYS_Region");//区域下拉框
            appendOption("KCustomerSouID", "${cxt}/commonController/selectAppendOption.action?type=SYS_CustomerSou");//区域下拉框

            appendOption("DDocumentStateID", "${cxt}/commonController/selectAppendOption.action?type=SYS_DocumentState");//单据状态下拉框
            appendOption("DBalanceStateID", "${cxt}/commonController/selectAppendOption.action?type=SYS_BalanceState");//结算状态下拉框
            
            appendOption("PaymentID", "${cxt}/commonController/selectAppendOption.action?type=SYS_Payment");//付款方式下拉框
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_Maintainability", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArrMaintainData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_Expenses", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArrExpensesData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_RepairItem", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArrRepairItemData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_Maintenance", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArrMaintenanceData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_Warehouse", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArWarehouseData.push(data[i]);
                }
            })
        }
        //明细表事件
        function DetailEvent(length, Num) {
            if (Num == 1) {
                selectcreate("WA" + length, ArrRepairItemData);
                selectcreate("WB" + length, ArrMaintenanceData);
                selectcreate("WF" + length, ArrMaintainData);
                var RepairItemID = $("#WA" + length).val();
                $("#WA" + length).change(function () {
                    var RepairItemID = $("#WA" + length).val();
                    if (RepairItemID != 0) {
                       $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + RepairItemID, function (data) {
                            $("#WB" + length).val(data.maintenanceID);
                            $("#WC" + length).text(data.repairCharge + ".00");
                            $("#WE" + length).text(data.repairCharge + ".00");
                        })
                    }
                })
                if ($("#WG" + length).text() == "null") $("#WG" + length).text("无");
                WeiXiu();
                function WeiXiu() {
                    var WXiuLiFei = 0;
                    var WShiShou = 0;
                    for (var i = 1; i < length + 1; i++) {
                        if ($("#WC" + i).text() != "") {
                            WXiuLiFei = parseFloat(WXiuLiFei) + parseFloat($("#WC" + i).text());
                            $("#WXiuLiFei").text(parseFloat(WXiuLiFei));
                        }
                        if ($("#WE" + i).text() != "") {
                            WShiShou = parseFloat(WShiShou) + parseFloat($("#WE" + i).text());
                            $("#WShiShou").text(parseFloat(WShiShou));
                        }
                    }
                }
                $("#tbWeiXiu").click(function () {
                    var RepairCharge = $("#WC" + length).text();
                    var Discount = $("#WD" + length).text();
                    var Cal = $("#WC" + length).text() * $("#WD" + length).text() / 100;
                    $("#WE" + length).text(parseFloat(Cal));
                    if (!Numbers.test(RepairCharge)) {
                        layer.tips('只能输入数字!', '#WC' + length);
                    } else if (!Numbers.test(Discount)) {
                        layer.tips('只能输入数字!', '#WD' + length);
                    } else if (parseInt(Discount) > 100 || parseInt(Discount) < 0) {
                        layer.tips('数据超出有效长度!', '#WD' + length);
                    } else if ($("#WG" + length).text().length > 20) {
                        layer.tips('数据超出有效长度!', '#WG' + length);
                    }
                    WeiXiu();

                })
            }
            if (Num == 2) {
                selectcreate("CC" + length, ArWarehouseData);
                selectcreate("CH" + length, ArrMaintainData);
               
                if ($("#CE" + length).text() == "null") $("#CE" + length).text("");
                var CJinE = 0;
                for (var i = 1; i < length + 1; i++) {
                    if ($("#CG" + i).text() != "") {
                        CJinE = parseFloat(CJinE) + parseFloat($("#CG" + i).text());
                        $("#CJinE").text(parseFloat(CJinE));
                    }
                }
            }
            if (Num == 3) {
                var ExpensesID = $("#QA" + length).val();
                $("#QA" + length).change(function () {
                    var ExpensesID = $("#QA" + length).val();
                    if (ExpensesID != 0) {
                        $.getJSON("${cxt}/commonController/selectExpensesChange.action?expensesID=" + ExpensesID, function (data) {
                            $("#QB" + length).text(data.cost + ".00");
                            $("#QD" + length).text(data.cost + ".00");
                            $("#QE" + length).text();
                        })
                    }
                })
                if ($("#QE" + length).text() == "null") $("#QE" + length).text("无");
                FeiYong();
                function FeiYong() {
                    var QJinE = 0;
                    var QShiShou = 0;
                    for (var i = 1; i < length + 1; i++) {
                        if ($("#QB" + i).text() != "") {
                            QJinE = parseFloat(QJinE) + parseFloat($("#QB" + i).text());
                            $("#QJinE").text(parseFloat(QJinE));
                        }
                        if ($("#QD" + i).text() != "") {
                            QShiShou = parseFloat(QShiShou) + parseFloat($("#QD" + i).text());
                            $("#QShiShou").text(parseFloat(QShiShou));
                        }
                    }
                }
                $("#tbFeiYong").click(function () {
                    var Cost = $("#QB" + length).text();
                    var Discount = $("#QC" + length).text();
                    var Cal = Cost * Discount / 100;
                    $("#QD" + length).text(parseFloat(Cal));
                    if ($("#QA" + length).val() == 0) {
                        layer.tips('请选择其他费用!', '#QA' + length);
                    } else if (!Numbers.test(Cost)) {
                        layer.tips('只能输入数字!', '#QB' + length);
                    } else if (!Numbers.test(Discount)) {
                        layer.tips('只能输入数字!', '#QC' + length);
                    } else if (parseInt(Discount) > 100 || parseInt(Discount) < 0) {
                        layer.tips('数据超出有效长度!', '#QC' + length);
                    } else if (!Numbers.test($("#QD" + length).text())) {
                        layer.tips('只能输入数字!', '#QD' + length);
                    } else if ($("#QE" + length).text().length > 20) {
                        layer.tips('数据超出有效长度!', '#QE' + length);
                    }
                    FeiYong();
                })
            }
        }
        //明细表合计初始化
        function ClearDetailToT() {
            $("#WXiuLiFei").text("0.00");
            $("#WShiShou").text("0.00");
            $("#CShuLiang").text("0.00");
            $("#CDanJia").text("0.00");
            $("#CJinE").text("0.00");
            $("#QJinE").text("0.00");
            $("#QShiShou").text("0.00");
        }
        /********************主页面信息---开始*******************************/
        //主页面新增按钮
        function btnMainInsert() {
            $("#formReception").resetForm();
            $("#MaintenanceNum").text("");
            $("#Describe").val("");
            $("#myVue tbody tr td").parent("tr").remove();
            ClearDetailToT();
            $("#ToAudit").val("false");
        }
        //主页面删除按钮
        function btnMainDelect() {
            var ReceptionID = $("#ReceptionID").val();
            var ToCompletion = $("#ToCompletion").val();
            var CollageState = $("#CollageState").val();
            if (ReceptionID != 0) {
                if (ToCompletion != "true") {
                    if (CollageState != "已领料") {
                        $.post("${cxt}/customerController/delectReception.action?receptionID=" + ReceptionID, function (data) {
                            if (data) {
                                layer.msg("数 据 删 除 成 功 ！");
                                btnMainInsert();
                                SeleceReception();
                            } else {
                                layer.msg("数 据 删 除 失 败 ！")
                            }
                        })
                    } else {
                        layer.alert('该单据已经领料，不能删除！请退料后再删除', {
                            skin: 'layui-layer-lan' //样式类名
                             , closeBtn: 0, anim: 4 //动画类型
                        });
                    }
                } else {
                    layer.alert('该条数据已经完工不能删除！', {
                        skin: 'layui-layer-lan' //样式类名
                          , closeBtn: 0, anim: 4 //动画类型
                    });
                }
            } else {
                layer.alert('该单据已经领料，不能删除！请退料后再删除', {
                    skin: 'layui-layer-lan' //样式类名
                          , closeBtn: 0, anim: 4 //动画类型
                });
            }
        }
        //主页面保存
        function btnMainSava() {
            if ($("#ReceptionID").val() != 0) {
                var Wshishou = 0, Cjine = 0;
                var ArrReception = [], ArrRecRepairItem = [], ArrCollageDetai = [], ArrRecOtherCost = [], ArrInsuranceMoney = [], ArrThreePacksDetail = [];
                var CarNum = $("#CarNum").val();
                var VehicleType = $("#VehicleType").val();
                var Carder = $("#Carder").val();//接车人
                var Owner = $("#Owner").val();
                var CustomerSouID = $("#CustomerSouID").val();//客户来源
                var RepairID = $("#RepairID").val();//修理类别
                var OwnerTele = $("#OwnerTele").val();//车主电话
                var Repairman = $("#Repairman").val();//送修人
                var RepairmanTele = $("#RepairmanTele").val();//送修人电话
                var WeiXiulength = $("#tbWeiXiu tbody tr").length;
                var ChanPinlength = $("#tbChanPin tbody tr").length;
                var FeiYonglength = $("#tbFeiYong tbody tr").length;
                var Amounts = parseFloat($("#WShiShou").text()) + parseFloat($("#CJinE").text()) + parseFloat($("#QShiShou").text());
                $("#Amount").val(parseFloat(Amounts));//总金额
                var Insurance = 0, ThreePacks = 0;;//保险金额//三包金额
                for (var i = 1; i < WeiXiulength; i++) {//修理项目构造函数
                    var e = new RecRepairItem();//构造函数
                    e.RepairItemID = $("#WA" + i).val();
                    e.RepairCharge = $("#WC" + i).text();
                    e.Discount = $("#WD" + i).text();
                    e.AmountPaid = $("#WE" + i).text();
                    e.MaintainabilityID = $("#WF" + i).val();
                    e.Remark = $("#WG" + i).text();
                    e.RecRepairItemDetailID = $("#WH" + i).text();
                    ArrRecRepairItem.push(e);
                    if ($("#WF" + i).val() == "3" || $("#WF" + i).val() == "4" || $("#WF" + i).val() == "6") {
                        Wshishou = parseFloat(Wshishou) + parseFloat($("#WE" + i).text());
                        if ($("#WF" + i).val() == "3" || $("#WF" + i).val() == "4") {
                            ThreePacks = parseFloat(ThreePacks) + parseFloat($("#WE" + i).text());
                        }
                        if ($("#WF" + i).val() == "6") {
                            Insurance = parseFloat(Insurance) + parseFloat($("#WE" + i).text());
                        }
                    }
                }
                for (var i = 1; i < ChanPinlength; i++) {//产品材料构造函数
                    var e = new CollageDetai();//构造函数
                    e.MaintainabilityID = $("#CH" + i).val();
                    e.CollageDetaiID = $("#CI" + i).text();
                    ArrCollageDetai.push(e);
                    if ($("#CH" + i).val() == "3" || $("#CH" + i).val() == "4" || $("#CH" + i).val() == "6") {
                        Cjine = parseFloat(Cjine) + parseFloat($("#CG" + i).text());
                        if ($("#CH" + i).val() == "3" || $("#CH" + i).val() == "4") {
                            ThreePacks = parseFloat(ThreePacks) + parseFloat($("#CG" + i).text());
                        }
                        if ($("#CH" + i).val() == "6") {
                            Insurance = parseFloat(Insurance) + parseFloat($("#CG" + i).text());
                        }
                    }
                }
                for (var i = 1; i < FeiYonglength; i++) {//费用构造函数
                    var e = new RecOtherCost();//构造函数
                    e.ExpensesID = $("#QA" + i).val();
                    e.Amount = $("#QB" + i).text();
                    e.Discount = $("#QC" + i).text();
                    e.AmountPaid = $("#QD" + i).text();
                    e.Remark = $("#QE" + i).text();
                    e.RecOtherCostDetailID = $("#QF" + i).text();
                    ArrRecOtherCost.push(e);
                }
                var AmountPaid = (Amounts) - ((Wshishou) + (Cjine));
                $("#AmountPaid").val((AmountPaid));//应收金额
                var e = new Reception();
                e.ReceptionID = $("#ReceptionID").val();//客户接待ID
                e.CarNum = $("#CarNum").val();//车牌
                e.VehicleType = $("#VehicleType").val();//车型
                e.Carder = $("#Carder").val();//接车人
                e.Mileage = $("#Mileage").val();//进厂里程
                e.MaintenanceNum = $("#MaintenanceNum").text();//维修单号
                e.Owner = $("#Owner").val();//车主
                e.CustomerNum = $("#CustomerNum").val();//客户编码
                e.Address = $("#Address").val();//客户地址
                e.OilQuantity = $("#OilQuantity").val();//油量
                e.CustomerSouID = $("#CustomerSouID").val();//客户来源
                e.OwnerTele = $("#OwnerTele").val();//车主电话
                e.EngineNum = $("#EngineNum").val();//发动机号码
                e.Repairman = $("#Repairman").val();//送修人
                e.SelfCoding = $("#SelfCoding").val();//自编号
                e.RepairID = $("#RepairID").val();//修理类别
                e.FrameNum = $("#FrameNum").val();//车架号码
                e.RepairmanTele = $("#RepairmanTele").val();//送修人电话
                e.OpenDate = $("#OpenDate").val();//开单日期
                e.FactoryDate = $("#FactoryDate").val();//进厂日期
                e.BalanceDate = $("#BalanceDate").val();//结算日期
                e.Amount = $("#Amount").val();//总金额
                e.AmountPaid = $("#AmountPaid").val();//应收金额
                e.Describe = $("#Describe").val();//描述
                ArrReception.push(e);
                $("#InsuranceMoney").val(Insurance);//保险理赔金额
                $("#ClaimMoney").val(ThreePacks);//三包理赔金额
                if (Insurance != 0 || ThreePacks != 0) {
                    var i = new InsuranceDetail(); //保险理赔明细
                    i.InsuranceDetailID = $("#InsuranceDetailID").val();
                    i.InsuranceComID = $("#InsuranceComID").val();
                    i.ReportNum = $("#ReportNum").val();
                    i.PolicyNum = $("#PolicyNum").val();
                    i.PolicyMoney = $("#PolicyMoney").val();
                    i.InsuranceMoney = $("#InsuranceMoney").val();
                    ArrInsuranceMoney.push(i);
                    var t = new ThreePacksDetail();//三包索赔明细
                    t.ThreePacksDetailID = $("#ThreePacksDetailID").val();
                    t.ClaimComID = $("#ClaimComID").val();
                    t.ClaimMoney = $("#ClaimMoney").val();
                    ArrThreePacksDetail.push(t);
                }
                if (CarNum == "") {
                    layer.alert('车牌号码不能为空!');
                } else if (VehicleType == "") {
                    layer.alert('车型不能为空!');
                } else if (Carder == "") {
                    layer.alert('接车人不能为空!');
                } else if (Owner == "") {
                    layer.alert('车主姓名不能为空!');
                } else if (OwnerTele == "") {
                    layer.alert('车主电话不能为空!');
                } else if (Repairman == "") {
                    layer.alert('送修人名称不能为空!');
                } else if (RepairmanTele == "") {
                    layer.alert('送修人电话不能为空!');
                } else if (CustomerSouID == 0) {
                    layer.alert('请选择客户来源!');
                } else if (RepairID == 0) {
                    layer.alert('请选择修理类别!');
                } else if (!Pattern.test(CarNum)) {
                    layer.alert('请输入正确的车牌号码!');
                } else if (!Phone.test(OwnerTele)) {
                    layer.alert('请输入正确的车主手机号码!');
                } else if (!Phone.test(RepairmanTele)) {
                    layer.alert('请输入正确的送修人手机号码!');
                } else {
                    if ($("#InsuranceMoney").val() > 0) {
                        $.each($(".InsuranceDetail"), function (i) {
                            if ($("#InsuranceComID").val() == 0 || $(this).val() == "") {
                                layer.alert('请填写完整保险理赔信息!');
                                return false;
                            } else if ($("#ClaimMoney").val() > 0) {
                                if ($("#ClaimComID").val() == 0) {
                                    layer.alert('请填写完整三包索赔信息!');
                                } else {
                                    Bave();
                                }
                            } else {
                                Bave();
                            }
                        })
                    } else if ($("#ClaimMoney").val() > 0) {
                        if ($("#ClaimComID").val() == 0) {
                            layer.alert('请填写完整三包理赔信息!');
                        } else {
                            Bave();
                        }
                    } else {
                        Bave();
                    }
                }
            } else {
                layer.alert('请选择数据再进行保存操作！', {
                    skin: 'layui-layer-lan' //样式类名
                     , closeBtn: 0, anim: 4 //动画类型
                });
            }
            function Bave()
            {
                layer.confirm('是否保存当前信息？', {
                    btn: ['保存', '取消'] //按钮
                }, function () {
                    $.post("${cxt}/customerController/updateListReceptione.action", {
                        pwReception: JSON.stringify(ArrReception), sysRecRepairItemDetail: JSON.stringify(ArrRecRepairItem),
                        sysRecProductDetail: JSON.stringify(ArrCollageDetai), sysRecOtherCostDetail: JSON.stringify(ArrRecOtherCost),
                        sysInsuranceDetail: JSON.stringify(ArrInsuranceMoney), sysThreePacksDetail: JSON.stringify(ArrThreePacksDetail)
                    }, function (data) {
                        SeleceReception();
                        $("#ReceptionID").val(data);
                        if (data > 0 ) {
                            layer.confirm('保存成功！是否立即审核该单据？', {
                                btn: ['是', '否'] //按钮
                            }, function () {
                                ToAudit();//审核方法
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
                    });
                })
            }
        }
        //主页面选择单据
        function ShowDanJu() {
            $("#DanJuModal").modal("show");
        }
        //主页面审核
        function ToAudit() {
            var ReceptionID = $("#ReceptionID").val();
            if (ReceptionID != "") {
                $("#ToAudit").val("true");
                $.post("${cxt}/settlementController/toAudit.action?receptionID=" + ReceptionID, function (data) {
                    if (data>0) {
                        layer.alert('审核成功，数据不能调整！', {
                            skin: 'layui-layer-molv' //样式类名
                      , closeBtn: 0
                        });
                        SeleceReception();
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
        function ToNotAudit() {
            $("#ToAudit").val("false");
            $.post("${cxt}/settlementController/toNotAudit.action?receptionID=" + $("#ReceptionID").val(), function (data) {
                if (data>0) {
                    layer.alert('已取消审核，可以进行数据修改啦！', {
                        skin: 'layui-layer-molv' //样式类名
                  , closeBtn: 0
                    });
                    SeleceReception();
                    NotAuditEvent();
                } else {
                    layer.alert('取消审核失败，请重新操作！', {
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
            $("#btnAudit").attr("disabled", true);
            $("#btnColle").attr("disabled", false);
            $("#btnNotAudit").attr("disabled", false);
            $("#formReception input").attr("disabled", true);
            $("#formReception select").attr("disabled", true);
            $("#myVue select").attr("disabled", true);
            $("#myVue input").attr("disabled", true);
            $("#Describe").attr("disabled", true);
            $("#myVue tbody td").attr("onclick", "");
        }
        //反审核按钮状态
        function NotAuditEvent() {
            $("#btnDelect").attr("disabled", false);
            $("#btnBave").attr("disabled", false);
            $("#btnAudit").attr("disabled", false);
            $("#btnColle").attr("disabled", true);
            $("#btnNotAudit").attr("disabled", true);
            $("#formReception input").attr("disabled", false);
            $("#formReception select").attr("disabled", false);
            $("#myVue select").attr("disabled", false);
            $("#myVue input").attr("disabled", false);
            $("#Describe").attr("disabled", false);
            $("#myVue tbody td").attr("onclick", "tdclick(this)");
            $("select").parent("td").attr("onclick", "");
            $("#ToAudit").attr("checked", false);
            $(".Disabl").attr("disabled", true);
            $("#ToAudit").checked = false;
            $("#tbChanPin tbody td").attr("onclick", "");
            $("#tbChanPin tbody .disabled").attr("disabled", true);
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
        //保险理赔收款
        function InsuranceData() {
            var ReceptionID = $("#ReceptionID").val();
            if (ReceptionID != "") {
                if ($("#InsuranceMoney").val() > 0) {
                    $.post("${cxt}/insuranceController/insurance.action?receptionID=" + ReceptionID, function (data) {
                        window.location.href = "${cxt}/jsp/Mechanics/insurance.jsp";
                    })
                } else {
                    layer.msg("该单据没有保险理赔项目!")
                }
            } else {
                layer.msg("请保存一条数据后再进行操作!")
            }
        }
        //三包索赔收款
        function ThreePacksData() {
            var ReceptionID = $("#ReceptionID").val();
            if (ReceptionID != "") {
                if ($("#ClaimMoney").val() > 0) {
                    $.post("${cxt}/claimsetController/claimset.action?receptionID=" + ReceptionID, function (data) {
                        window.location.href = "${cxt}/jsp/Mechanics/claimset.jsp";
                    })
                } else {
                    layer.msg("该单据没有三包理赔项目!")
                }
            } else {
                layer.msg("请保存一条数据后再进行操作!")
            }
        }
        /********************主页面信息---结束*******************************/
        /********************车牌模态框---开始*******************************/

        //选择车牌按钮弹出维修客户模态框
        function ShowCarNum() {
            SeleceMaintenanceCus();
            $("#CarNumModal").modal("show");
        }
        //客户表格条件查询
        function SeleceMaintenanceCus() {
            var LicenseCode = $("#LicenseCode").val();
            if (LicenseCode == null || LicenseCode == undefined) LicenseCode = "";
            tbKeHu.search({ licenseCode: LicenseCode });

        }
        //客户编码回填
        function CustomerNumData() {
            $.post("${cxt}/commonController/customerNum.action", function (data) {
                $("#KCustomerNum").val(data);
            })
        }
        //维修客户信息回填
        function MaintenanceCusData(recoed) {
            $("#KMaintenanceCusID").val(recoed.maintenanceCusId)
            $("#KOwner").val(recoed.owner);
            $("#KBirthday").val(recoed.birthday);
            $("#KIdNumber").val(recoed.idNumber);
            $("#KMobilePhone").val(recoed.mobilePhone);
            $("#KLicenseCode").val(recoed.licenseCode);
            $("#KVehicleType").val(recoed.vehicleType);
            $("#KEngineNum").val(recoed.engineNum);
            $("#KFrameNum").val(recoed.frameNum);
            $("#KRepairMan").val(recoed.repairMan);
            $("#KRepairTele").val(recoed.repairTele);
            $("#KInsuranceComID").val(recoed.insuranceComId);
            $("#KInsuranceSpeID").val(recoed.insuranceSpeId);
            $("#KInitialStartDate").val(recoed.initialStartDate);
            $("#KInitialEndDate").val(recoed.initialEndDate);
            $("#KAddress").val(recoed.address);
            $("#KCustomerNum").val(recoed.customerNum);
            $("#KDriveDate").val(recoed.driveDate);
            $("#KRegionID").val(recoed.regionId);
            $("#KDepartmentID").val(recoed.departmentId);
            $("#KCarderID").val(recoed.carderId);
            $("#KCustomerLevelID").val(recoed.customerLevelId);
            $("#KCustomerSouID").val(recoed.customerSouId);
            $("#KCustomerTypeID").val(recoed.customerTypeId);
            $("#KDrivingDate").val(recoed.drivingDate);
            $("#KInputPerson").val(recoed.inputPerson);
        }
        //车辆新增保存
        function btnSave() {
            var Owner = $("#KOwner").val();
            var Birthday = $("#KBirthday").val();
            var IdNumber = $("#KIdNumber").val();
            var MobilePhone = $("#KMobilePhone").val();
            var LicenseCode = $("#KLicenseCode").val();
            var VehicleType = $("#KVehicleType").val();
            var EngineNum = $("#KEngineNum").val();
            var FrameNum = $("#KFrameNum").val();
            var RepairMan = $("#KRepairMan").val();
            var RepairTele = $("#KRepairTele").val();
            var InsuranceComID = $("#KInsuranceComID").val();
            var InsuranceSpeID = $("#KInsuranceSpeID").val();
            var InitialStartDate = $("#KInitialStartDate").val();
            var InitialEndDate = $("#KInitialEndDate").val();
            var Address = $("#KAddress").val();
            var CustomerNum = $("#KCustomerNum").val();
            var DriveDate = $("#KDriveDate").val();
            var RegionID = $("#KRegionID").val();
            var DepartmentID = $("#KDepartmentID").val();
            var CarderID = $("#KCarderID").val();
            var CustomerLevelID = $("#KCustomerLevelID").val();
            var CustomerSouID = $("#KCustomerSouID").val();
            var CustomerTypeID = $("#KCustomerTypeID").val();
            var DrivingDate = $("#KDrivingDate").val();
            var InputPerson = $("#KInputPerson").val();
            if (Owner == "") {
                layer.tips('车主姓名不能为空!', '#KOwner');
            } else if (Birthday == "") {
                layer.tips('车主生日不能为空!', '#KBirthday');
            } else if (IdNumber == "") {
                layer.tips('身份证号码不能为空!', '#KIdNumber');
            } else if (MobilePhone == "") {
                layer.tips('车主手机不能为空!', '#KMobilePhone');
            } else if (LicenseCode == "") {
                layer.tips('车牌号码不能为空!', '#KLicenseCode');
            } else if (VehicleType == "") {
                layer.tips('车型不能为空!', '#KVehicleType');
            } else if (RepairMan == "") {
                layer.tips('送修人名称不能为空!', '#KRepairMan');
            } else if (RepairTele == "") {
                layer.tips('送修人电话不能为空!', '#KRepairTele');
            } else if (InsuranceComID == 0) {
                layer.tips('请选择保险公司!', '#KInsuranceComID');
            } else if (CustomerNum == "") {
                layer.tips('请输入客户编号!', '#KCustomerNum');
            } else if (InsuranceSpeID == 0) {
                layer.tips('请选择保险种类!', '#KInsuranceSpeID');
            } else if (RegionID == 0) {
                layer.tips('请选择所属区域!', '#KRegionID');
            } else if (DepartmentID == 0) {
                layer.tips('请选择所属部门!', '#KDepartmentID');
            } else if (CarderID == 0) {
                layer.tips('请选择所属员工!', '#KCarderID');
            } else if (CustomerLevelID == 0) {
                layer.tips('请选择客户等级!', '#KCustomerLevelID');
            } else if (CustomerSouID == 0) {
                layer.tips('请选择客户来源!', '#KCustomerSouID');
            } else if (CustomerTypeID == 0) {
                layer.tips('请选择客户类别!', '#KCustomerTypeID');
            } else if (InputPerson == "") {
                layer.tips('请输入录入人!', '#KInputPerson');
            } else if (!Identity.test(IdNumber)) {
                layer.tips('请输入有效证件号码', '#KIdNumber');
            } else if (!Phone.test(MobilePhone)) {
                layer.tips('请输入正确的手机号码!', '#KMobilePhone');
            } else if (!Pattern.test(LicenseCode)) {
                layer.tips('请输入正确的车牌号码!', '#KLicenseCode');
            } else if (!Phone.test(RepairTele)) {
                layer.tips('请输入正确的手机号码!', '#KRepairTele');
            }

            else {
                $("#formMaintenanceCus").ajaxSubmit(function (data) {
                    if (data == true) {
                        layer.closeAll('loading');
                        layer.msg("保 存 成 功 ！");
                        SeleceMaintenanceCus();
                    } else {
                        layer.msg("保 存 失 败 ！");
                    }
                })
            }
        }
        //车辆模态框复选框点击事件
        $("#check").click(function () {
            if (this.checked == false) {
                $("#formMaintenanceCus select").attr("disabled", true);
                $("#formMaintenanceCus input[type='text']").attr("disabled", true);
            } else {
                $("#formMaintenanceCus select").attr("disabled", false);
                $("#formMaintenanceCus input[type='text']").attr("disabled", false);
            }
        })
        //选中主页面信息回填
        function Dblclick() {
            var rowIndex = tbKeHu.getSelectedRowIndex();
            var record = tbKeHu.getRecord(rowIndex);
            if (rowIndex > -1) {
                $("#CarNumModal").modal("hide");
                $("#CarNum").val(record.licenseCode);
                $("#VehicleType").val(record.vehicleType);
                $("#Owner").val(record.owner);
                $("#Address").val(record.address);
                $("#CustomerNum").val(record.customerNum);
                $("#CustomerSouID").val(record.customerSouId);
                $("#OwnerTele").val(record.mobilePhone);
                $("#EngineNum").val(record.engineNum);
                $("#Repairman").val(record.repairMan);
                $("#FrameNum").val(record.frameNum);
                $("#RepairmanTele").val(record.repairTele);

            } else {
                layer.msg("如果需要选择一行数据，请进行操作！")
            }
        }
        //清空汽车模态框文本框值
        function ClearValue() {
            $("#formMaintenanceCus").resetForm();
            $("#formMaintenanceCus select").attr("disabled", false);
            $("#formMaintenanceCus input[type='text']").attr("disabled", false);
        }

        /********************车牌模态框---结束******************************/

        /********************单据模态框---开始*******************************/

        //选择选择单据按钮弹出模态框
        function ShowDanJu() {
            $("#DanJuModal").modal("show");
        }
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
                maintenanceNum: DMaintenanceNum, toAudit: DToAudit, balanceStateID: 3,
                carNum: DCarNum, documentStateID: DDocumentStateID,
            });
        }
        //选择单据回填信息
        function ReceptionData(record) {
            btnMainInsert(1);
            $.getJSON("${cxt}/settlementController/selectBalanceDetail.action?receptionID=" + record.receptionId, function (data) {
                $("#DanJuModal").modal("hide");
                $.each(data[0], function (i) {
                    var tbWeiXiu = $("#tbWeiXiu");
                    var length = $("#tbWeiXiu tbody tr").last().prevObject.length;
                    var tr = $("<tr><td>" + length + "</td><td><select id=WA" + length + " ></select></td><td><select id=WB" + length + " disabled='disabled' ></select></td>"
                             + "<td onclick='tdclick(this)' id=WC" + length + ">" + data[0][i].repairCharge + ".00" + "</td>"
                             + "<td onclick='tdclick(this)' id=WD" + length + " >" + data[0][i].discount + "</td>"
                             + "<td  id=WE" + length + ">" + data[0][i].amountPaid + ".00" + "</td><td><select id=WF" + length + " ></select></td>"
                             + "<td onclick='tdclick(this)' id=WG" + length + ">" + data[0][i].remark + "</td><td hidden id=WH" + length + ">" + data[0][i].recRepairItemDetailId + "</td></tr>");
                    tbWeiXiu.append(tr);
                    DetailEvent(length, 1);
                    $("#WA" + length).val(data[0][i].repairItemId);
                    $("#WB" + length).val(1);
                    $("#WF" + length).val(data[0][i].maintainabilityId);
                    var RepairItemID = $("#WA" + length).val();
                    $.ajaxSettings.async = false;
                   $.getJSON("${cxt}/commonController/selectRepairItemChange.action?repairItemID=" + RepairItemID, function (data) {
                        $("#WB" + length).val(data.maintenanceId);
                    })
                })
                $.each(data[1], function (i) {
                        var tbChanPin = $("#tbChanPin");
                        var length = $("#tbChanPin tbody tr").last().prevObject.length;
                        var tr = $("<tr><td>" + length + "</td><td  id=CA" + length + ">" + data[1][i].fittingsCode + "</td>"
                            + "<td  id=CB" + length + ">" + data[1][i].fittingsName + "</td>"
                            + "<td  id=CD" + length + ">" + data[1][i].quantity  + ".00" + "</td><td  id=CE" + length + ">" + data[1][i].systemUnit + "</td>"
                            + "<td  id=CF" + length + ">" + data[1][i].unitPrice + ".00" + "</td><td  id=CG" + length + ">" + data[1][i].amount + ".00" + "</td>"
                            + "<td><select  id=CH" + length + " ></select></td><td hidden id=CI" + length + ">" + data[1][i].collageDetaiId + "</td></tr>");
                        tbChanPin.append(tr);
                        DetailEvent(length, 2);
                        $("#CH" + length).val(data[1][i].maintainabilityId);
                    })
                $.each(data[2], function (i) {
                    var tbFeiYong = $("#tbFeiYong");
                    var length = $("#tbFeiYong tbody tr").last().prevObject.length;
                    var tr = $("<tr><td>" + length + "</td><td><select id=QA" + length + "></select></td>"
                            + "<td onclick='tdclick(this)' id=QB" + length + ">" + data[2][i].amount + ".00" + "</td>"
                            + "<td onclick='tdclick(this)' id=QC" + length + ">" + data[2][i].discount + "</td>"
                            + "<td onclick='tdclick(this)' id=QD" + length + ">" + data[2][i].amountPaid + ".00" + "</td>"
                            + "<td onclick='tdclick(this)' id=QE" + length + ">" + data[2][i].remark + "</td>"
                            + "<td hidden id=QF" + length + ">" + data[2][i].recOtherCostDetailId + "</td></tr>");
                    tbFeiYong.append(tr);
                    selectcreate("QA" + length, ArrExpensesData);
                    DetailEvent(length, 3);
                    $("#QA" + length).val(data[2][i].expensesId);
                })
               if(data[3].length>0){
                   $("#InsuranceDetailID").val(data[3][0].insuranceDetailId);
                   $("#ReportNum").val(data[3][0].reportNum);
                   $("#InsuranceComID").val(data[3][0].insuranceComId);
                   $("#PolicyNum").val(data[3][0].policyNum);
                   $("#PolicyMoney").val(data[3][0].policyMoney);
                   $("#InsuranceMoney").val(data[3][0].insuranceMoney);
                }
                if(data[4].length>0){
                   $("#ThreePacksDetailID").val(data[4][0].threePacksDetailId);
                   $("#ClaimComID").val(data[4][0].claimComId);
                   $("#ClaimMoney").val(data[4][0].claimMoney);
                }
            })
            $("#ReceptionID").val(record.receptionId);
            $("#RepairID").val(record.repairId);
            $("#CustomerSouID").val(record.customerSouId);
            $("#DocumentStateID").val(record.documentStateId);
            $("#BalanceStateID").val(record.balanceStateId);
            $("#CarNum").val(record.carNum);
            $("#VehicleType").val(record.vehicleType);
            $("#Carder").val(record.carder);
            $("#Mileage").val(record.mileage);
            $("#CustomerNum").val(record.customerNum);
            $("#Owner").val(record.owner); 
            $("#Address").val(record.address);
            $("#OilQuantity").val(record.oilQuantity);
            $("#OwnerTele").val(record.ownerTele);
            $("#EngineNum").val(record.engineNum);
            $("#Repairman").val(record.repairman);
            $("#SelfCoding").val(record.selfCoding);
            $("#FrameNum").val(record.frameNum);
            $("#RepairmanTele").val(record.repairmanTele);
            $("#OpenDate").val(record.openDate);
            $("#FactoryDate").val(record.factoryDate);
            $("#BalanceDate").val(record.balanceDate);
            $("#Amount").val(record.amount);
            $("#AmountPaid").val(record.amountPaid);
            $("#Describe").val(record.describe);
            $("#ToAudit").val(record.toAudit);
            $("#ToCompletion").val(record.toCompletion);
            $("#CollageState").val(record.collageState);
            if (record.maintenanceNum == null) {
                MaintenanceNum();
            } else {
                $("#MaintenanceNum").text(record.maintenanceNum);
            }
            if (record.toAudit == true) {
                $("#ToAudits").attr("checked", true);
            } else {
                $("#ToAudits").removeAttr("checked");
            }
            ToFales();
        }
        //选择单据自定义事件 创建是否审核
        function TosAudit(record) {
            var ToAudit = record.toAudit;
            if (ToAudit == true || ToAudit == "true") {
                return "<span style='color:red;'>✔ 已审核</span>";
            } else {
                return "<span style='color:blue;'>✘ 未审核</span>";
            }
        }

        /********************单据模态框---结束*******************************/


       
        /********************结算单模态框---开始*******************************/
        function ShowJieSuan() {
            if ($("#AmountPaid").val() <0) {
                layer.alert("应收金额小于0,不能继续收款!")
            } else {
                $("#JieSuanModal").modal("show");
                JieSuanData();
            }
        }
        //数据回填
        function JieSuanData()
        {
            var BalanceID = $("#BalanceID").val();
            $("#JOwner").text($("#Owner").val());
            $("#JCustomerNum").text($("#CustomerNum").val())
            $("#JBalanceDate").text($("#OpenDate").val());
            $("#JMaintenanceNum").text($("#MaintenanceNum").text());
            $("#JShouldAmount").text($("#AmountPaid").val());//应收金额
            $("#JOptimalAmount").text("0.00");//已优惠金额
            $("#JCollectionAmount").text("0.00");//已收金额
            $("#OptimalAmount").val(0);
            $("#PaymentID").val(0);
            $("#Remark").val("");
            $("#JNoAmount").text(parseFloat($("#AmountPaid").val() - $("#JCollectionAmount").text()));
            $("#CollectionAmount").val($("#AmountPaid").val());
        }
       //优惠金额文本框
        $("#OptimalAmount").keyup(function () {
            var OptimalAmount = $("#OptimalAmount").val();
            var CollectionAmount = $("#CollectionAmount").val();
            if (OptimalAmount == "") OptimalAmount = 0;
            $("#CollectionAmount").val(parseFloat(parseFloat($("#JNoAmount").text()) - parseFloat(OptimalAmount)));
            
        })
        //结算保存
        function btnBaveJieSuan()
        {
            var ArrBalance = [];
            var e = new Balance();
            e.PaymentID=$("#PaymentID").val();
            e.ReceptionID = $("#ReceptionID").val();
            e.ShouldAmount = $("#AmountPaid").val();
            e.BusinessNum = $("#MaintenanceNum").text();
            e.OptimalAmount = $("#OptimalAmount").val();
            e.CollectionAmount = $("#CollectionAmount").val();
            e.Remark = $("#Remark").val();
            ArrBalance.push(e);
            if ($("#OptimalAmount").val() == "") $("#OptimalAmount").val("0");
            if ($("#PaymentID").val() == 0) {
                layer.tips('请选择入账方式!', '#PaymentID');
            } else if($("#OptimalAmount").val() == "") {
                $("#OptimalAmount").val(0);
            }else {
                $.post("${cxt}/settlementController/baveBalance.action",{pwBalance:JSON.stringify(ArrBalance)},function (data) {
                    if (data>0) {
                        layer.msg("结 算 成 功 !");
                        SeleceReception();
                        $("#JieSuanModal").modal("hide");
                        $("#btnNotAudit").attr("disabled", true);
                        $("#btnColle").attr("disabled", true);
                    } else {
                        layer.msg("结 算 失 败 !");
                    }
                })
            }
        }
        /********************结算单模态框---结束*******************************/
       
        //主页面
        function Reception(ReceptionID, CarNum, VehicleType, Carder, Mileage, MaintenanceNum, Owner, Address,
            OilQuantity, CustomerSouID, OwnerTele, EngineNum, Repairman, SelfCoding, RepairID, FrameNum,
            RepairmanTele, OpenDate, FactoryDate, BalanceDate, Amount, AmountPaid, Describe, CustomerNum) {
            this.ReceptionID = ReceptionID;//客户接待ID
            this.CarNum = CarNum;//车主
            this.VehicleType = VehicleType;//车型
            this.Carder = Carder;//接车人
            this.Mileage = Mileage;//进厂里程
            this.MaintenanceNum = MaintenanceNum;//维修编号
            this.Owner = Owner;//车主
            this.CustomerNum = CustomerNum;//客户编码
            this.Address = Address;//客户地址
            this.OilQuantity = OilQuantity;//油量
            this.CustomerSouID = CustomerSouID;//客户来源
            this.OwnerTele = OwnerTele;//车主电话
            this.EngineNum = EngineNum;//发动机号码
            this.Repairman = Repairman;//送修人
            this.SelfCoding = SelfCoding;//自编号
            this.RepairID = RepairID;//修理类别
            this.FrameNum = FrameNum;//车架号码
            this.RepairmanTele = RepairmanTele;//送修人电话
            this.OpenDate = OpenDate;//开单日期
            this.FactoryDate = FactoryDate;//进厂日期
            this.BalanceDate = BalanceDate;//结算日期
            this.Amount = Amount;//总金额
            this.AmountPaid = AmountPaid;//应收金额
            this.Describe = Describe;//描述
        }
        //修理项目构造函数
        function RecRepairItem(RecRepairItemDetailID, RepairItemID, RepairCharge, Discount,
            MaintenanceID, AmountPaid, MaintainabilityID, Remark) {
            this.RecRepairItemDetailID = RecRepairItemDetailID;//修理明细ID
            this.RepairItemID = RepairItemID;//修理项目ID
            this.RepairCharge = RepairCharge;//修理费
            this.Discount = Discount;//折扣
            this.AmountPaid = AmountPaid;//实收金额
            this.MaintainabilityID = MaintainabilityID;//维修性质
            this.Remark = Remark;//备注
        }
        //产品材料构造函数
        function CollageDetai(CollageDetaiID, MaintainabilityID) {
            this.CollageDetaiID = CollageDetaiID;//产品明细ID
            this.MaintainabilityID = MaintainabilityID;//维修性质ID
        }
        //其他费用构造函数
        function RecOtherCost(RecOtherCostDetailID, ExpensesID, Amount, Discount, AmountPaid, Remark) {
            this.RecOtherCostDetailID = RecOtherCostDetailID;//费用明细ID
            this.ExpensesID = ExpensesID;//其他费用ID
            this.Amount = Amount;//金额
            this.Discount = Discount;//折扣
            this.AmountPaid = AmountPaid;//实收金额
            this.Remark = Remark;//备注
        }
        //保险理赔明细构造函数
        function InsuranceDetail(InsuranceDetailID, InsuranceComID, ReportNum, PolicyNum, PolicyMoney, InsuranceMoney) {
            this.InsuranceDetailID = InsuranceDetailID;//保险理赔明细ID
            this.InsuranceComID = InsuranceComID;//保险公司ID
            this.ReportNum = ReportNum;//报案编号
            this.PolicyNum = PolicyNum;//保单号
            this.PolicyMoney = PolicyMoney;//保单金额
            this.InsuranceMoney = InsuranceMoney;//理赔金额
        }
        //三包索赔明细构造函数
        function ThreePacksDetail(ThreePacksDetailID, ClaimComID, ClaimMoney) {
            this.ThreePacksDetailID = ThreePacksDetailID;//三包索赔ID
            this.ClaimComID = ClaimComID;//索赔公司ID
            this.ClaimMoney = ReportNum;//索赔金额
        }
        //结算单
        function Balance(PaymentID, ReceptionID, ShouldAmount, OptimalAmount, CollectionAmount, Remark, BusinessNum)
        {
            this.PaymentID = PaymentID;//付款方式ID
            this.ReceptionID = ReceptionID;//接待客户ID
            this.ShouldAmount = ShouldAmount;//应收金额
            this.OptimalAmount = OptimalAmount;//优惠金额
            this.CollectionAmount = CollectionAmount;//收款金额
            this.Remark = Remark;//备注
            this.BusinessNum = BusinessNum;//业务单号
        }
    </script>
</body>
</html>