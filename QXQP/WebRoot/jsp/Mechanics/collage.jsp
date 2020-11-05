<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="cxt"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>Collage</title>
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
            height: 32px;
        }

        .navsearch .col-lg-3 {
            width: 150px;
            margin-right: 80px;
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
        #tbPeiJian tr th, #tbPeiJian tr td {
            padding: 5px;
            width: 300px;
            text-align: center;
            font-size: 14px;
            border: 1px solid #e2e2e2;
            border-collapse: collapse;
        }

        #tbPeiJian {
            border: 1px;
            width: 99.5%;
        }

           #tbPeiJian tr th {
                background-color: #f5f5f5;
                /*border-bottom: 1px solid gray;*/
            }

            /*table tr td {
                background-color: #f5f5f5;
            }*/

           #tbPeiJian input,#tbPeiJian select {
                width: 100px;
                margin: 0px;
                padding:0px;
                border: none;
            }

           #tbPeiJian select {
                width: 100%;
            }

        .Boxs {
            height: 450px;
            overflow: auto;
            box-shadow: 0 0 20px 1px #ddd;
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
        .form-control[disabled] {
        background-color:ghostwhite;
        }
    </style>
</head>
<body>
    <!-- @*导航栏*@ -->
    <nav class="breadcrumb" style="margin:0px">
        <i class="Hui-iconfont"></i> <a href=""> 首 页 </a> <span class="c-gray en">&gt;</span> <a href=""> 汽 修 管 理 </a>
        <span class="c-gray en">&gt;</span><a href=""> 维 修 领 料 </a><span class="c-gray en">&gt;</span><a href=""> 领 料</a>
        <a class="btn btn-success radius r" style="line-height:1.6em;top:0px;position:absolute;right:20px" href="javascript:location.replace(location.href);" title="刷新">
            <i class="Hui-iconfont"></i>
        </a>
    </nav>
   <!--  @*按钮栏*@ -->
    <div class="cl pd-5 bg-1 bk-gray mt-20" style="padding:10px;margin:0px">
        <button class="btn btn-primary "onclick="ShowPeiJian()"><i class="Hui-iconfont">&#xe604;</i> 导入估计配件 </button>
        <button class="btn btn-info "onclick="BaveMianIofo()"><i class="Hui-iconfont">&#xe632;</i> 保 存</button>
        <button class="btn btn-success " onclick="ShowDanJu()"><i class="Hui-iconfont">&#xe676;</i> 查询单据</button>
        <button class="btn btn-pink"><i class="Hui-iconfont">&#xe652;</i> 打 印</button>
        <button class="btn btn-danger "onclick="Close()"><i class="Hui-iconfont">&#xe6a6;</i> 返 回</button>
    </div>
   <!--  @*控件栏*@ -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-weight:inherit">维 修 领 料 单</legend>
        <div class="layui-field-box">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <form class="form-horizontal" role="form" style="margin-top:2px;"id="formCollage">
                        <input type="text" id="CollageID"hidden />
                        <input type="text" id="ReceptionID" hidden value="${receptionID }" />
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车牌号码:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled"disabled id="CarNum">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">领料员:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12" style="height:45px;">
                                <select class="form-control" id="ForeManID"></select>
                                <input type="text" style="" class="seinput" id="ForeMan">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">操作人:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" value="" class="form-control disabled"id="Operator" disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">客户名称:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled" id="Owner"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label" style="text-shadow: 1px 1px 5px gray;">结算单号:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <p style="text-decoration:underline;padding:6px;font-size:16px" id="MaintenanceNum"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">领料状态:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled" id="CollageState" disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">总金额:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled" id="Amount"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">审核人:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" value="" class="form-control disabled" id="Auditor"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">审核时间:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled" id="AuditDate"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label" style="text-shadow: 1px 1px 5px gray;">领料日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <p style="text-decoration:underline;padding:6px;font-size:16px" id="CollageDate"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">备注:</label>
                            </div>
                            <div class="col-lg-4 col-md-4 col-xs-12">
                                <input type="text" class="form-control"style="width:95%"id="Remark">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label" style="position:relative;bottom:5px;left:0px">是否审核:
                                   <input type="checkbox" style="position:relative;top:4px;left:10px;width:20px;height:20px"id="ToAudit" />
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </fieldset>
   <!--  @*表格*@ -->
    <div id="PeiJian" class="Boxs">
        <table id="tbPeiJian">
            <tbody>
                <tr>
                    <th width='10%' height='20px'>序号</th>
                    <th width='25%' height='20px'>配件编码</th>
                    <th width='10%' height='20px'>配件名称</th>
                    <th width='10%' height='20px'>规格</th>
                    <th width='25%' height='20px'>维修性质</th>
                    <th width='10%' height='20px'>仓库</th>
                    <th width='10%' height='20px'>车型</th>
                    <th width='10%' height='20px'>单位</th>
                    <th width='10%' height='20px'>数量</th>
                    <th width='10%' height='20px'>单价</th>
                    <th width='10%' height='20px'>金额</th>
                    <th width='10%' height='20px'>仓位</th>
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
                   <td></td>
                   <td></td>
                   <td></td>
                   <td id="PJinE">0.00</td>
                   <td></td>
               </tr>
           </tfoot>
        </table>
    </div>
   <!--  @*右击菜单*@ -->
    <ul id="menuOne" class="box">
        <li onclick="ShowShangPin()"><i class="glyphicon glyphicon-plus"></i> 添 加</li>
        <li onclick="deleteOne()"><i class="glyphicon glyphicon-trash"></i> 删 除</li>
    </ul>
   <!--  @*导入配件模态框*@ -->
    <div class="modal fade col-lg-12" id="PeiJianModal">
        <div class="modal-dialog">
            <div class="modal-content"style="width:880px;height:60%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        选 择 单 据
                    </h4>
                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div style="height:400px;width:850px;overflow:auto; box-shadow: 0 0 20px 5px #ddd;">
                        <table id="tbPeiJianMo" style="width:1350px">
                            <tr>
                                <th w_index="recProductDetailID"w_check="true">选择</th>
                                <th  w_num="line">序号</th>
                                <th w_index="fittingsCode">产品编号</th>
                                <th w_index="fittingsName">名称</th>
                                <th w_index="systemUnit">单位</th>
                                <th w_index="quantity">数量</th>
                                <th w_index="unitPrice">开单价</th>
                                <th w_index="amount">金额</th>
                                <th w_index="maintainabilityName">维修性质</th>
                                <th w_index="remark">备注</th>
                            </tr>
                        </table>
                    </div>
                </div>
                <div style="margin-left:78%;margin-bottom:15px">
                    <button type="button" class="btn btn-info " onclick="DblPeiJianData()"><i class="Hui-iconfont">&#xe632;</i> 确 认</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>退出</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
   <!--  @*选择单据模态框*@ -->
    <div class="modal fade col-lg-12" id="DanJuModal">
        <div class="modal-dialog">
            <div class="modal-content"style="width:630px;height: 50%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        记录定位
                    </h4>
                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div style="height:300px;width:600px;overflow:auto; box-shadow: 0 0 20px 5px #ddd;">
                        <table id="tbDanJu" style="width:100%">
                            <tr>
                                <th w_index="maintenanceNum">单号</th>
                                <th w_index="owner">客户名称</th>
                                <th w_index="auditDate">日期</th>
                                <th w_index="amount">总金额</th>
                                <th w_render="CollageState">领料状态</th>
                                <th w_index="collageState" w_hidden="true">领料状态</th>
                            </tr>
                        </table>
                    </div>
                </div>
                <div style="margin-left:70%;margin-bottom:15px">
                    <button type="button" class="btn btn-info " onclick="DanJuData()"><i class="Hui-iconfont">&#xe632;</i> 确 认</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>退出</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <!-- @*新增产品产品模态框*@ -->
    <div class="modal fade col-lg-12" id="ShangPinModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        商品类别查找(多选)
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="navsearch">
                        <div class="col-lg-12 col-md-12 col-sm-12">
                            <form class="form-horizontal" role="form">
                                <div class="col-lg-1 col-md-1 col-sm-2 text-right">
                                    <label class="control-label">快速查找 :</label>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-right:10px">
                                    <select class="form-control" id="selectInfo">
                                        <option value="1">商品类别</option>
                                        <option value="2">商品编码</option>
                                        <option value="3">商品名称</option>
                                        <option value="4">所属车型</option>
                                    </select>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-12 reset" style="margin-right:170px">
                                    <input type="text" class="form-control" id="Info" onkeyup="" />
                                </div>
                                <button type="button" class="btn btn-search" onclick="SeleceFittingsInfo()"><i class="glyphicon glyphicon-search"></i>查 询</button>
                                <button type="button" class="btn btn-info" onclick="btnFittingSelect()"><i class="glyphicon glyphicon-ok-sign"></i>选 中</button>
                                <button type="button" class="btn btn-success" onclick="ShowAddPeiJian()"><i class="glyphicon glyphicon-plus"></i>新 增</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>关 闭</button>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div>
                        <div class="modOne" id="FittingsType" style="width:280px">
                            <ul>
                                <li onclick="SeleceFittingsInfo()">
                                    <i style="background: url('../../../../Content/Main/images/smiley_sleep.png') no-repeat 0px 5px;">
                                    </i> 货品分类 &nbsp;共有<span></span>个类别
                                </li>
                            </ul>
                        </div>
                        <div class="modTow col-lg-12 col-md-12 col-sm-12" style="width:640px;height:570px">
                            <table id="tbShangPin" style="width:1200px">
                                <tr>
                                    <th w_index="fittingsInfoID" w_check="true">选择</th>
                                    <th w_index="fittingsTypeName">商品类别</th>
                                    <th w_index="fittingsCode">配件编码</th>
                                    <th w_index="fittingsName">配件名称</th>
                                    <th w_index="specification">配件规格</th>
                                    <th w_index="vehicleType">所属车型</th>
                                    <th w_index="systemUnit">单位</th>
                                    <th w_index="intake">进价</th>
                                    <th w_index="salesPrice">销售价格</th>
                                    <th w_index="suppliersName">供应商</th>
                                    <th w_index="remark">备注</th>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
   <!--  @*配件基本资料添加模态框*@ -->
    <div class="modal fade col-lg-12" id="AddPeiJianModal">
        <div class="modal-dialog">
            <div class="modal-content" style="width:850px;left:-133px;top:60px;height: 67%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        ×
                    </button>
                    <h4 class="modal-title">
                        配件基本资料添加
                    </h4>
                </div>
                <div class="modal-footer" style="border-top:none;">
                    <div>
                        <fieldset class="layui-elem-field layui-field-box" style="margin-top: 0px;">
                            <legend style="font-size:16px;margin-left:35px;text-shadow: 5px 5px 5px gray;font-weight:inherit">双击框体，弹出路径，选择对应图片保存即可</legend>
                            <div class="col-lg-12 col-md-12 col-sm-12" style="width:102%">
                                <form class="form-horizontal" role="form" id="formFittings"
                                      action="${cxt}/servlet/CommonServlet?fun=updateFittingsInfo" method="post">
                                    <div style="float:left;width:250px;height:300px;">
                                        <div class="img-box text-center">
                                            <img src="" alt="" id="imgCarPicture" />
                                            <input type="file" class="hidden" style="margin-left:3px;margin-top:10px;font-size:10px" name="fileCarPicture" id="CarPicture" />
                                        </div>

                                    </div>
                                    <div style="float:left;width:500px;height:300px;margin-left:30px">
                                        <div class="form-group">
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">配件编码:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="text" class="form-control" id="PFittingsCode" name="FittingsCode" maxlength="5">
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">商品类别:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <select class="form-control" id="PFittingsTypeID" name="FittingsTypeID"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">配件名称:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="text" class="form-control" id="PFittingsName" name="FittingsName" maxlength="10">
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">条码:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="text" class="form-control" id="PBarcode" name="Barcode" maxlength="10">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">配件规格:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="text" class="form-control" id="PSpecification" name="Specification" maxlength="10">
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">品牌:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="text" class="form-control" id="PBrand" name="Brand" maxlength="5">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">单位:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <select class="form-control" id="PSystemUnitID" name="SystemUnitID"></select>
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">所属车型:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <select class="form-control" id="PVehicleTypeID" name="VehicleTypeID"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">进价:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="number" class="form-control" id="PIntake" name="Intake">
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">销售价格:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="number" class="form-control" id="PSalesPrice" name="SalesPrice">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">批发价:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="number" class="form-control" id="PWholesalePrice" name="WholesalePrice">
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">库存上限:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="number" class="form-control" id="PInventoryMax" name="InventoryMax">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">库存下限:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="number" class="form-control" id="PInventoryMin" name="InventoryMin">
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-xs-12">
                                                <label class="control-label">开单价:</label>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-xs-12">
                                                <input type="number" class="form-control" id="POpenPrice" name="OpenPrice">
                                            </div>
                                        </div>

                                    </div>
                                    <div class="form-group" style="padding-top:30px">
                                        <div class="col-lg-3 col-md-3 col-xs-12" style="width:60px">
                                            <label class="control-label">供应商:</label>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-xs-12">
                                            <select class="form-control" id="PSuppliersID" name="SuppliersID"></select>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-xs-12" style="width:140px">
                                            <label class="control-label">备注:</label>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-xs-12">
                                            <input type="text" class="form-control" style="width:300px" id="PRemark" name="Remark" maxlength="20">
                                        </div>
                                    </div>
                                    <p style="text-align:left;line-height:26px;color:orangered;margin:0">说明：系统根据配件编码，名称和规格来确定产品</p>
                                </form>
                            </div>
                        </fieldset>
                        <button type="button" class="btn btn-info " onclick="btnFittingsSava()"><i class="Hui-iconfont">&#xe632;</i> 保 存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose"><i class="glyphicon glyphicon-remove"></i>关 闭</button>
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
    <script src="${cxt}/Content/layui/lay/modules/laydate.js"></script>
    <script src="${cxt}/Content/js/jquery.form.js"></script>
    <script src="${cxt}/Content/js/customfunction.js"></script>
    <script src="${cxt}/Content/js/combobox.js"></script>
    <script>
        //初始化表格
        var tbPeiJianMo, tbDanJu, tbShangPin;
        var ArrMaintainData = [], ArrWarehouseData = [];
        var Numbers = /^[-\d.]+$/; //价格
        $(function () {
            create();
            tbPeiJianMo = $.fn.bsgrid.init('tbPeiJianMo', {
                url: '${cxt}/servlet/Collage?fun=selectDaoRuPeiJian',
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
                            Check("#tbPeiJianMo tr", rowIndex + 1);//复选框勾选
                        }
                    }
                }
            })
            tbDanJu = $.fn.bsgrid.init('tbDanJu', {
                url: '${cxt}/servlet/Collage?fun=selectCollage',
                autoLoad: true,//自动加载
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
                            Check("#tbDanJu tr", rowIndex + 1);//复选框勾选
                        }
                    }
                }
            })
            tbShangPin = $.fn.bsgrid.init('tbShangPin', {
                url: '${cxt}/servlet/CommonServlet?fun=seleceFittingsInfo',
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
            LoadInfo($("#ReceptionID").val());
            run();
            SelectPeiJian();
            SeleceFittingsInfo();
        })

        //文本框与下拉框合并
        $("#ForeManID").on("change", function () {
            var text = $(this).find("option:selected").text();
            $("#ForeMan").val(text)
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
            })
            //阻止浏览器默认右键点击事件
            document.oncontextmenu = function() {
                return false;
            }
            var resOne = document.getElementById('menuOne');   //找到id为box的div
            document.getElementById("PeiJian").onmouseup = function (e) {   //在body里点击触发事件
                if (e.button === 2) {    //如果button=1（鼠标左键），button=2（鼠标右键），button=0（鼠标中间键）
                    resOne.style.top = e.clientY + 'px';   //鼠标点击时给div定位Y轴
                    resOne.style.left = e.clientX + 'px';  //鼠标点击时给div定位X轴
                    resOne.style.display = 'block';    //显示div盒子
                } else {
                    resOne.style.display = 'none';     //否则不显示div盒子
                }
            }
        })
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
            var DateTime = year + "-" + month + "-" + date + " " + hour + ":" + minutes + ":" + seconds;
            var Dates = year + "-" + month + "-" + date;
            $("#CollageDate").text(Dates);
        }
        //删除配件行
        function deleteOne() {
            var length = $("#tbPeiJian tbody").find("tr").length;
            if (length > 1) {
                $("#tbPeiJian tbody").find("tr").last().remove();//找到最后一个选项然后删除
            }
            $("#PJinE").text("0.00");
            DetailEvent(length);
        }
        //绑定下拉框
        function create() {
            $.ajaxSettings.async = false;
            createSelect("ForeManID", "${cxt}/servlet/CommonServlet?t=SYS_ForeMan");//领料员下拉框

            createSelect("PFittingsTypeID", "${cxt}/servlet/CommonServlet?t=SYS_FittingsType");//商品类别下拉框
            createSelect("PSystemUnitID", "${cxt}/servlet/CommonServlet?t=SYS_SystemUnit");//系统单位下拉框
            createSelect("PVehicleTypeID", "${cxt}/servlet/CommonServlet?t=SYS_VehicleType");//车型下拉框
            createSelect("PSuppliersID", "${cxt}/servlet/CommonServlet?t=SYS_Suppliers");//供应商下拉框

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
        }
        //明细表事件
        function DetailEvent(length) {
            selectcreate("CD" + length, ArrMaintainData);
            selectcreate("CE" + length, ArrWarehouseData);
            $("#CD" + length).val(2);
            if ($("#CC" + length).text() == "null") $("#CC" + length).text("");
            if ($("#CK" + length).text() == "null") $("#CK" + length).text("无");
            ChanPin();
            function ChanPin() {
                var PJinE = 0;
                for (var i = 1; i < length + 1; i++) {
                    if ($("#CJ" + i).text() != "") {
                        PJinE = parseFloat(PJinE) + parseFloat($("#CJ" + i).text());
                        $("#PJinE").text(parseFloat(PJinE));
                    }
                }
            }
            $("#tbPeiJian").click(function () {
                var Quantity = $("#CH" + length).text();
                var SalesPrice = $("#CI" + length).text();
                var Cal = Quantity * SalesPrice;
                $("#CJ" + length).text(parseFloat(Cal));
                if (($("#CF" + length).text().length > 11)) {
                    layer.tips('数据超出有效长度!', '#CF' + length);
                } else if (!Numbers.test(Quantity)) {
                    layer.tips('只能输入数字!', '#CH' + length);
                } else if (!Numbers.test(SalesPrice)) {
                    layer.tips('只能输入数字!', '#CI' + length);
                } else if ($("#CK" + length).text().length > 6) {
                    layer.tips('数据超出有效长度!', '#CK' + length);
                }
                ChanPin();
            })
        }
        //回填主页面数据
        function LoadInfo(ID) {
           
            $("#formCollage").resetForm();
            $("#CollageDate").text("");
            $("#MaintenanceNum").text("");
            $("#tbPeiJian tbody tr td").parent("tr").remove();
            $.ajaxSettings.async = false;
            $("#ReceptionID").val(ID);
             $.getJSON("${cxt}/servlet/Collage?fun=arrlistToCollage&ReceptionID=" + ID, function (data) {
                data=data.list[0];
                $("#CarNum").val(data.carNum);
                $("#MaintenanceNum").text(data.maintenanceNum);
                $("#Owner").val(data.owner);
                $("#CollageState").val(data.collageState);
            })
            $.getJSON("${cxt}/servlet/Collage?fun=selectCollageData&ReceptionID=" + ID, function (data) {
                data=data.list[0];
                if(data!=null){
                $("#CollageID").val(data.collageID);
                $("#ForeMan").val(data.foreMan);
                $("#CollageDate").text(data.collageDate);
                $("#Amount").val(data.amount);
                $("#ToAudit").val(data.toAudit);
                $("#Operator").val(data.operator);
                $("#AuditDate").val(data.auditDate);
                $("#Auditor").val(data.auditor);
                $("#Remark").val(data.remark);
                }
            })
            CollageID = $("#CollageID").val();
            if (CollageID > 0) {
                CollageDetaiData(CollageID);
            }
        }
        //主页面保存
        function BaveMianIofo()
        {
            var  ArrCollage=[],ArrCollageDetai = [];
            var PeiJianlength = $("#tbPeiJian tbody tr").length;
            var Amount = parseFloat($("#PJinE").text());
            $("#Amount").val(parseFloat(Amount));//总金额
            var e = new Collage();
            e.CollageID = $("#CollageID").val();
            e.ForeMan = $("#ForeMan").val();
            e.ReceptionID =  $("#ReceptionID").val();
            e.CollageDate = $("#CollageDate").text();
            e.Amount = $("#Amount").val();
            e.Remark = $("#Remark").val();
            ArrCollage.push(e)
            for (var i = 1; i < PeiJianlength; i++) {//修理项目构造函数
                var e = new CollageDetai();//构造函数
                e.FittingsCode = $("#CA" + i).text();
                e.FittingsName = $("#CB" + i).text();
                e.FittingsSpec = $("#CC" + i).text();
                e.MaintainabilityID = $("#CD" + i).val();
                e.WarehouseID = $("#CE" + i).val();
                e.VehicleType = $("#CF" + i).text();
                e.SystemUnit = $("#CG" + i).text();
                e.Quantity = $("#CH" + i).text();
                e.UnitPrice = $("#CI" + i).text();
                e.Amount = $("#CJ" + i).text();
                e.Position = $("#CK" + i).text();
                e.CollageDetaiID = $("#CL" + i).text();
                ArrCollageDetai.push(e);
            }
            if ($("#ForeMan").val() == "") {
                layer.alert('领料员不能为空!');
            }else if(ArrCollageDetai.length==0)
                layer.alert('明细表中没有数据，不能进行保存!');
            else {
                $.post("${cxt}/servlet/Collage?fun=updateListCollage", { PW_Collage: JSON.stringify(ArrCollage), SYS_CollageDetai: JSON.stringify(ArrCollageDetai) }, function (data) {
                    if (data != false) {
                        $("#Auditor").val(data[0]);
                        $("#Operator").val(data[1]);
                        $("#AuditDate").val(data[2]);
                        $("#CollageState").val(data[3]);
                        layer.msg("已减配件库存，领料成功！");
                        LoadInfo($("#ReceptionID").val());
                         window.location.href = "${cxt}/jsp/Mechanics/draftback.jsp";
                    } else {
                        layer.msg("领 料 失 败 ！")
                    }
                })
            }
        }

        /********************选择单据模态框---开始*******************************/
        //选择选择单据按钮弹出模态框
        function ShowDanJu() {
            $("#DanJuModal").modal("show");
        }
        function CollageDetaiData(CollageID)
        {
            var tbPeiJian = $("#tbPeiJian");
            $.getJSON("${cxt}/servlet/Collage?fun=selectCollageDetai&CollageID=" + CollageID, function (data) {
                data=data.list;
                $.each(data, function (i) {
                    var length = $("#tbPeiJian tbody tr").last().prevObject.length;
                    var tr = $("<tr><td>" + length + "</td><td id=CA" + length + ">" + data[i].fittingsCode + "</td>"
                    + "<td id=CB" + length + ">" + data[i].fittingsName + "</td><td onclick='tdclick(this)' id=CC" + length + ">" + data[i].fittingsSpec + "</td>"
                    + "<td><select id=CD" + length + "></select></td><td><select id=CE" + length + "></select></td>"
                    + "<td onclick='tdclick(this)' id=CF" + length + ">" + data[i].vehicleType + "</td>"
                    + "<td  id=CG" + length + ">" + data[i].systemUnit + "</td><td onclick='tdclick(this)' id=CH" + length + ">" + data[i].quantity + "</td>"
                    + "<td onclick='tdclick(this)' id=CI" + length + ">" + data[i].unitPrice + ".00" + "</td><td id=CJ" + length + ">"
                    + data[i].amount + ".00" + "</td><td onclick='tdclick(this)' id=CK" + length + ">" + data[i].position + "</td><td hidden id=CL" + length + ">" + data[i].collageDetaiID + "</td></tr>");
                    tbPeiJian.append(tr);
                    DetailEvent(length);
                    $("#CD" + length).val(data[i].maintainabilityID);
                    $("#CE" + length).val(data[i].warehouseID);
                })
            })
        }
        //确认回填信息
        function DanJuData()
        {
            $("#formCollage").resetForm();
            $("#CollageDate").text("");
            $("#MaintenanceNum").text("");
            $("#tbPeiJian tbody tr td").parent("tr").remove();
            var tbPeiJian = $("#tbPeiJian");
            var rowIndex = tbDanJu.getSelectedRowIndex();
            var Records = tbDanJu.getRecord(rowIndex);
            var CollageID = Records.collageID;
            CollageDetaiData(CollageID);
            $("#CarNum").val(Records.carNum);
            $("#ForeMan").val(Records.foreMan);
            $("#Operator").val(Records.operator);
            $("#Owner").val(Records.owner);
            $("#MaintenanceNum").text(Records.maintenanceNum);
            $("#CollageState").val(Records.collageState);
            $("#Amount").val(Records.amount);
            $("#Auditor").val(Records.auditor);
            $("#AuditDate").val(Records.auditDate);
            $("#CollageDate").text(Records.collageDate);
            $("#Remark").val(Records.remark);
            $("#ToAudit").val(Records.toAudit);
            $("#CollageID").val(Records.collageID);
            $("#ReceptionID").val(Records.receptionID);
            $("#DanJuModal").modal("hide");
            SelectPeiJian();
        }
        //自定义事件 创建领料状态
        function CollageState(record) {
            var CollageState = record.collageState;
            if (CollageState == "未领料") {
                return "<span style='color:red;'>未领料</span>";
            } else if (CollageState == "已领料") {
                return "<span style='color:blue;'>已领料</span>";
            } else {
                return "<span >已退料</span>";
            }
        }
        /********************选择单据模态框---结束*******************************/

        /********************导入配件模态框---开始*******************************/
        //弹出导入配件模态框
        function ShowPeiJian() {
            $("#PeiJianModal").modal("show");
        }
        //表格查询
        function SelectPeiJian() {
            tbPeiJianMo.search({ ReceptionID: $("#ReceptionID").val() });
        }
        //双击回填配件
        function DblPeiJianData()
        {
            var tbPeiJian = $("#tbPeiJian");
            var Records = tbPeiJianMo.getCheckedRowsRecords();
            $("#PeiJianModal").modal("hide");
            $.each(Records, function (i) {
                var length = $("#tbPeiJian tbody tr").last().prevObject.length;
                var tr = $("<tr><td>" + length + "</td><td id=CA" + length + ">" + Records[i].fittingsCode + "</td>"
                + "<td id=CB" + length + ">" + Records[i].fittingsName + "</td><td onclick='tdclick(this)' id=CC" + length + ">" + Records[i].fittingsSpec + "</td>"
                + "<td><select id=CD" + length + "></select></td><td><select id=CE" + length + "></select></td>"
                + "<td onclick='tdclick(this)' id=CF" + length + ">" + Records[i].vehicleType + "</td>"
                + "<td  id=CG" + length + ">" + Records[i].systemUnit + "</td><td onclick='tdclick(this)' id=CH" + length + ">" + Records[i].quantity + "</td>"
                + "<td onclick='tdclick(this)' id=CI" + length + ">" + Records[i].unitPrice + ".00" + "</td><td id=CJ" + length + ">"
                + Records[i].amount + ".00" + "</td><td onclick='tdclick(this)' id=CK" + length + "></td><td hidden id=CL" + length + "></td></tr>");
                tbPeiJian.append(tr);
                DetailEvent(length);
                $("#CD" + length).val(Records[i].maintainabilityID);
                SelectPeiJian();
            })

        }

        /********************导入配件模态框---结束*******************************/

        /********************商品模态框---开始*******************************/

        //弹出产品材料模态框
        function ShowShangPin() {
            var InfoOne = "", InfoTow = "", InfoThree = "", InfoFour = "", FittingsTypeID = 0;
            $.getJSON("${cxt}/servlet/CommonServlet?fun=selectFittingsType", function (data) {
                data=data.list;
                $("#FittingsType span").html(data.length);
                $("#FittingsType ul .lishow").html("");
                $.each(data, function (i) {
                    $("#FittingsType ul").append('<li id=' + data[i].fittingsTypeID + ' class="lishow"><i class="diai"></i>' +
                        data[i].fittingsTypeName + ' (' + data[i].fittingsTypeNum + ')</li>');
                    FittingsTypeID = data[i].fittingsTypeID;
                    $("#" + FittingsTypeID).click(function () {
                        $("#FittingsType li").removeClass("active");
                        $(this).addClass("active");
                        tbShangPin.search({
                            InfoOne: InfoOne, InfoTow: InfoTow, InfoThree: InfoThree,
                            InfoFour: InfoFour, FittingsTypeID: this.id
                        });
                    })
                })
            })
            tbShangPin.refreshPage();
            $("#ShangPinModal").modal("show");
        }
        //商品表格查询
        function SeleceFittingsInfo() {
            var InfoOne = "", InfoTow = "", InfoThree = "", InfoFour = "", FittingsTypeID = 0;
            var Info = $("#Info").val();
            var selectInfo = $("#selectInfo").val();
            if (selectInfo == "1") InfoOne = Info;
            if (selectInfo == "2") InfoTow = Info;
            if (selectInfo == "3") InfoThree = Info;
            if (selectInfo == "4") InfoFour = Info;
            tbShangPin.search({
                InfoOne: InfoOne, InfoTow: InfoTow, InfoThree: InfoThree,
                InfoFour: InfoFour, FittingsTypeID: FittingsTypeID
            });
        }
        //商品材料选中
        function btnFittingSelect() {
            var tbPeiJian = $("#tbPeiJian");
            var rowRecords = tbShangPin.getCheckedRowsRecords();
            $("#ShangPinModal").modal("hide");
            $.each(rowRecords, function (i) {
                var length = $("#tbPeiJian tbody tr").last().prevObject.length;
                var tr = $("<tr><td>" + length + "</td><td id=CA" + length + ">" + rowRecords[i].fittingsCode + "</td>"
                + "<td id=CB" + length + ">" + rowRecords[i].fittingsName + "</td><td onclick='tdclick(this)' id=CC" + length + ">" + rowRecords[i].specification + "</td>"
                + "<td><select id=CD" + length + "></select></td><td><select id=CE" + length + "></select></td>"
                + "<td onclick='tdclick(this)' id=CF" + length + ">" + rowRecords[i].vehicleType + "</td>"
                + "<td onclick='tdclick(this)' id=CG" + length + ">" + rowRecords[i].systemUnit + "</td><td onclick='tdclick(this)' id=CH" + length + ">1.00</td>"
                + "<td onclick='tdclick(this)' id=CI" + length + ">" + rowRecords[i].salesPrice + ".00" + "</td><td id=CJ" + length + ">"
                + rowRecords[i].salesPrice + ".00" + "</td><td onclick='tdclick(this)' id=CK" + length + "></td><td hidden id=CL" + length + "></td></tr>");
                tbPeiJian.append(tr);
                DetailEvent(length);
            })
        }
        //弹出新增配件模态框
        function ShowAddPeiJian() {
            $("#AddPeiJianModal").modal("show");
        }
        //配件基本资料添加保存
        function btnFittingsSava() {
            var FittingsCode = $("#PFittingsCode").val();
            var FittingsName = $("#PFittingsName").val();
            var Intake = $("#PIntake").val();
            var FittingsTypeID = $("#PFittingsTypeID").val();
            var SystemUnitID = $("#PSystemUnitID").val();
            var VehicleTypeID = $("#PVehicleTypeID").val();
            var SuppliersID = $("#PSuppliersID").val();
            if (FittingsCode == "") {
                layer.tips('配件编码不能为空!', '#PFittingsCode');
            } else if (FittingsTypeID == 0) {
                layer.tips('请选择配件类型!', '#PFittingsTypeID');
            } else if (FittingsName == "") {
                layer.tips('配件名称不能为空!', '#PFittingsName');
            } else if (SystemUnitID == 0) {
                layer.tips('请选择单位!', '#PSystemUnitID');
            }
            else if (VehicleTypeID == 0) {
                layer.tips('请选择车型!', '#PVehicleTypeID');
            }
            else if (SuppliersID == 0) {
                layer.tips('请选择供应商!', '#PSuppliersID');
            }
            else {
                if (FittingsCode!="") {
                    $.post("${cxt}/servlet/CommonServlet?fun=judgingFittingsCode&FittingsCode=" + FittingsCode, function (data) {
                        if (data=="true") {
                            $("#InfoForm").ajaxSubmit(function (data) {
                                if (data>0) {
                                    layer.msg("保 存 成 功 ！");
                                    $("#InfoForm").resetForm();
                                    $("#imgCarPicture").attr('src', "");
                                    SeleceFittingsInfo();
                                } else {
                                    layer.msg("保 存 失 败 ！");
                                }
                            })
                        } else {
                            layer.tips('配件编码已存在!', '#PFittingsCode');
                        }
                    })
                }
            }
        }

        /********************商品模态框---结束*******************************/
        //返回按钮
        function Close() {
            window.location.href = "${cxt}/jsp/Mechanics/draftback.jsp";
        }
        //主信息
        function Collage(CollageID, ForeMan, ReceptionID, CollageDate, Amount, Remark) {
            this.CollageID = CollageID;//
            this.ForeMan = ForeMan;//
            this.ReceptionID = ReceptionID;//
            this.CollageDate = CollageDate;//
            this.Amount = Amount;//
            this.Remark = Remark;//
        }
        //产品材料构造函数
        function CollageDetai(CollageDetaiID, MaintainabilityID, WarehouseID, FittingsName, FittingsCode, FittingsSpec,
            Quantity, Position, VehicleType, SystemUnit, UnitPrice) {
            this.CollageDetaiID = CollageDetaiID;//
            this.MaintainabilityID = MaintainabilityID;//
            this.WarehouseID = WarehouseID;//
            this.FittingsName = FittingsName;//
            this.FittingsCode = FittingsCode;//
            this.FittingsSpec = FittingsSpec;//
            this.Quantity = Quantity;//
            this.UnitPrice = UnitPrice;//
            this.Amount = Amount;//
            this.Position = Position;//
            this.VehicleType = VehicleType;//
            this.SystemUnit = SystemUnit;//
        }
    </script>
</body>
</html>