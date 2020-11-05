<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cxt"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>Restock</title>
    <link href="${cxt}/Content/Main/css/main.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/tabnav/css/element.min.css" rel="stylesheet" />
    <link href="${cxt}/Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" />
    <link href="${cxt}/Content/bootstrap-3.3.7-dist/css/bootstrapal.css" rel="stylesheet" />
   <!--  @*CSS样式(包含默认皮肤样式)*@ -->
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

        input, select {
            height: 26px;
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
            border: 1px solid #e2e2e2;
            font-size: 14px;
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
            height: 50%;
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
            height: 50%;
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
        .seinput {
           position:relative;bottom:41px;width:78%;border:none;left:12px;height:66%;
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
        <span class="c-gray en">&gt;</span><a href=""> 维 修 领 料 </a><span class="c-gray en">&gt;</span><a href=""> 退 料</a>
    </nav>
  <!--   @*按钮栏*@ -->
    <div class="cl pd-5 bg-1 bk-gray mt-20" style="padding:10px;margin:0px">
        <button class="btn btn-danger " onclick="deleteOne()"><i class="Hui-iconfont">&#xe604;</i> 删除明细 </button>
        <button class="btn btn-info "onclick="BaveRestock()"><i class="Hui-iconfont">&#xe632;</i> 保 存</button>
        <button class="btn btn-danger " onclick="Close()"><i class="Hui-iconfont">&#xe6a6;</i> 返 回</button>
    </div>
   <!--  @*控件栏*@ -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-weight:inherit">维 修 退 料 单</legend>
        <div class="layui-field-box">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <form class="form-horizontal" role="form" style="margin-top:2px;">
                        <input type="text" id="ReceptionID" value="${receptionID }" hidden />
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">车牌号码:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled"id="CarNum"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">退料员:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12" style="height:45px;">
                                <select class="form-control" id="ForeManID"></select>
                                <input type="text" style="" class="seinput" id="RestockMan">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">操作人:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled"id="Operator"value="${user.getUserName() }"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">客户名称:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled"id="Owner"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label" style="text-shadow: 1px 1px 5px gray;">结算单号:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <p style="text-decoration:underline;padding:6px;font-size:16px"id="MaintenanceNum"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">总金额:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled"id="Amount"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">单据状态:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control disabled"id="StrAudit"disabled>
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label">备注:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <input type="text" class="form-control"id="Remark">
                            </div>
                            <div class="col-lg-1 col-md-1 col-xs-12">
                                <label class="control-label" style="text-shadow: 1px 1px 5px gray;">退料日期:</label>
                            </div>
                            <div class="col-lg-2 col-md-3 col-xs-12">
                                <p style="text-decoration:underline;padding:6px;font-size:16px"id="RestockDate"></p>
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
                    <td id="PJinE">0.00</td>
                    <td></td>
                </tr>
            </tfoot>
        </table>
    </div>
    <script src="${cxt}/Content/bootstrap-3.3.7-dist/js/jquery-2.0.3.min.js"></script>
    <script src="${cxt}/Content/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="${cxt}/Content/jquery.bsgrid-1.37/js/lang/grid.zh-CN.min.js"></script>
    <script src="${cxt}/Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.js"></script>
    <script src="${cxt}/Content/Main/lib/layer/2.4/layer.js"></script>
    <script src="${cxt}/Content/js/customfunction.js"></script>
    <script>
        //初始化表格
        var tbPeiJianMo, tbDanJu, tbShangPin;
        var ArrWarehouseData = [];
        var Numbers = /^[-\d.]+$/; //价格
        $(function () {
            create(),run(), RestockData();
        })
        //文本框与下拉框合并
        $("#ForeManID").on("change", function () {
            var text = $(this).find("option:selected").text();
            $("#RestockMan").val(text)
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
            $("#RestockDate").text(Dates);
        }
        //绑定下拉框
        function create() {
            $.ajaxSettings.async = false;
            createSelect("ForeManID", "${cxt}/commonController/selectAppendOption.action?type=SYS_ForeMan");//领料员下拉框
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_Warehouse", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArrWarehouseData.push(data[i]);
                }
            })
        }
        //删除配件行
        function deleteOne() {
            var length = $("#tbPeiJian tbody").find("tr").length;
            var FittingsCode= $("#tbPeiJian tbody").find("tr").last().children("td").eq(1).text();
            if (length > 1) {
                layer.confirm('确定需要删除配件编码为'+FittingsCode+'这一行吗？', {
                    btn: ['是', '否'] //按钮
                }, function (layerIndex) {
                    layer.close(layerIndex);
                    $("#tbPeiJian tbody").find("tr").last().remove();//找到最后一个选项然后删除
                })
            }
            $("#PJinE").text("0.00");
            DetailEvent(length);
        }
        //明细表事件
        function DetailEvent(length) {
            selectcreate("CD" + length, ArrWarehouseData);
            if ($("#CC" + length).text() == "null") $("#CC" + length).text("");
            if ($("#CJ" + length).text() == "null") $("#CJ" + length).text("无");
            ChanPin();
            function ChanPin() {
                var PJinE = 0;
                for (var i = 1; i < length + 1; i++) {
                    if ($("#CI" + i).text() != "") {
                        PJinE = parseFloat(PJinE) + parseFloat($("#CI" + i).text());
                        $("#PJinE").text(parseFloat(PJinE));
                        $("#Amount").val(PJinE+".00");
                    }
                }
            }
            $("#tbPeiJian").click(function () {
                var Quantity = $("#CG" + length).text();
                var SalesPrice = $("#CH" + length).text();
                var Cal = Quantity * SalesPrice;
                $("#CI" + length).text(parseFloat(Cal));
                if (!Numbers.test(Quantity)) {
                    layer.tips('只能输入数字!', '#CG' + length);
                } 
                ChanPin();
            })
        }
        //页面数据回填
        function RestockData()
        {
            $.ajaxSettings.async=false;
            $.getJSON("${cxt}/collageController/selectRestock.action?receptionID=" + $("#ReceptionID").val(), function (data) {
            console.log(data)
                data=data[0]
                $("#CarNum").val(data.carNum);
                $("#MaintenanceNum").text(data.maintenanceNum);
                $("#Owner").val(data.owner);
                $("#StrAudit").val(data.strAudit);
                $("#ReceptionID").val(data.receptionId);
            })
            var tbPeiJian = $("#tbPeiJian");
            $.getJSON("${cxt}/collageController/arrlistToRestock.action", function (data) {
                console.log(data)
                $.each(data, function (i) {
                    var length = $("#tbPeiJian tbody tr").last().prevObject.length;
                    var tr = $("<tr><td>" + length + "</td><td id=CA" + length + ">" + data[i].fittingsCode + "</td>"
                    + "<td id=CB" + length + ">" + data[i].fittingsName + "</td><td  id=CC" + length + ">" + data[i].fittingsSpec + "</td>"
                    + "<td><select id=CD" + length + " disabled></select></td><td id=CE" + length + ">" + data[i].vehicleType + "</td>"
                    + "<td  id=CF" + length + ">" + data[i].systemUnit + "</td><td onclick='tdclick(this)' id=CG" + length + ">" + data[i].quantity + "</td>"
                    + "<td  id=CH" + length + ">" + data[i].unitPrice + ".00" + "</td><td id=CI" + length + ">"
                    + data[i].amount + "</td><td  id=CJ" + length + ">" + data[i].position + "</td><td hidden id=CK" + length + ">" + data[i].collageDetaiId + "</td></tr>");
                    tbPeiJian.append(tr);
                    DetailEvent(length);
                    $("#CD" + length).val(data[i].warehouseId);
                })
                $.post("${cxt}/collageController/cleanArrlist.action");
            })
        }
        //保存信息
        function BaveRestock()
        {
            var ArrRestock=[],ArrRestockDetai = [];
            var PeiJianlength = $("#tbPeiJian tbody tr").length;
            var e=new Restock();
            e.RestockDate=$("#RestockDate").text();
            e.Operator=$("#Operator").val();
            e.Amount=$("#Amount").val();
            e.Remark=$("#Remark").val();
            e.ReceptionID=$("#ReceptionID").val();
            e.RestockMan=$("#RestockMan").val();
            ArrRestock.push(e);
            for (var i = 1; i < PeiJianlength; i++) {//修理项目构造函数
                var e = new RestockDetai();//构造函数
                e.CollageDetaiID = $("#CK" + i).text();
                e.Quantity = $("#CG" + i).text();
                e.FittingsCode=$("#CA"+i).text();
                e.WarehouseID=$("#CD"+i).val();
                ArrRestockDetai.push(e);
            }
            if ($("#RestockMan").val() == "") {
                layer.alert('退料员不能为空!');
            }else if(ArrRestockDetai.length==0)
                layer.alert('明细表中没有数据，不能进行保存!');
            else {
                $.getJSON("${cxt}/collageController/judgingQuantity.action",{restockDetail: JSON.stringify(ArrRestockDetai)},function(data){
                    if (data.state) {
                        $.post("${cxt}/collageController/updateListRestock.action", { pwRestock: JSON.stringify(ArrRestock), restockDetail: JSON.stringify(ArrRestockDetai) }, function (data) {
                            if (data>0) {
                                layer.msg("退 料 成 功 ！")
                            } else {
                                layer.msg("退 料 失 败 ！")
                            }
                        })
                    }else{
                        layer.alert(data.text);
                    }
                })
            }
        }
        //返回按钮
        function Close() {
            window.location.href = "${cxt}/jsp/Mechanics/draftback.jsp";
        }
        //退料明细
        function Restock(RestockDate,Operator,Amount,Remark,ReceptionID,RestockMan) {
            this.RestockDate = RestockDate;//
            this.Operator = Operator;//
            this.Amount = Amount;//
            this.Remark = Remark;//
            this.ReceptionID = ReceptionID;//
            this.RestockMan=RestockMan;
        }
        //退料明细
        function RestockDetai(CollageDetaiID,Quantity,FittingsCode,WarehouseID) {
            this.CollageDetaiID = CollageDetaiID;//明细ID
            this.Quantity = Quantity;//数量
            this.FittingsCode = FittingsCode;//配件编码
            this.WarehouseID = WarehouseID;//仓库ID
        }
    </script>
</body>
</html>