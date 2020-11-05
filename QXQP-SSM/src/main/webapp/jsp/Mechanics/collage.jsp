<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="cxt"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="viewport" content="width=device-width" />
<title>Collage</title>
<link
	href="${cxt}/Content/layui/css/modules/laydate/default/laydate.css"
	rel="stylesheet" />
<link href="${cxt}/Content/layui/css/layui.css" rel="stylesheet" />
<link href="${cxt}/Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css"
	rel="stylesheet" />
<link href="${cxt}/Content/Main/css/main.css" rel="stylesheet" />
<link href="${cxt}/Content/Main/static/h-ui.admin/css/H-ui.admin.css"
	rel="stylesheet" />
<link href="${cxt}/Content/Main/tabnav/css/element.min.css"
	rel="stylesheet" />
<link href="${cxt}/Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css"
	rel="stylesheet" />
<link href="${cxt}/Content/bootstrap-3.3.7-dist/css/bootstrapal.css"
	rel="stylesheet" />
<!-- @*CSS样式(包含默认皮肤样式)*@ -->
<link href="${cxt}/Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.css"
	rel="stylesheet" />
<!-- @*CSS皮肤(需引用于bsgrid.all.min.css之后)*@ -->
<link
	href="${cxt}/Content/jquery.bsgrid-1.37/css/skins/grid_gray.min.css"
	rel="stylesheet" />
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

.navsearch input,.navsearch select {
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

#tbPeiJian tr th,#tbPeiJian tr td {
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
	padding: 0px;
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

.box>li {
	border-bottom: dashed 1px gray;
	cursor: pointer;
	text-align: center;
	padding: 3px;
}

.box>li:hover {
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

#imgCarPicture {
	display: block;
	width: 250px;
	height: 295px;
	border-radius: 5px;
	box-shadow: 0 0 20px 3px #eee;
}

.seinput {
	position: relative;
	bottom: 41px;
	width: 78%;
	border: none;
	left: 12px;
	height: 66%;
}

#FittingsType {
	padding: 15px;
}

#FittingsType ul {
	text-align: left;
}

#FittingsType li {
	text-align: left;
	line-height: 32px;
	font-size: 18px;
}

#FittingsType li:hover {
	background: #eee;
	cursor: alias;
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
	background-color: ghostwhite;
}
</style>
</head>
<body>
	<!-- @*导航栏*@ -->
	<nav class="breadcrumb" style="margin:0px"> <i
		class="Hui-iconfont"></i> <a href=""> 首 页 </a> <span
		class="c-gray en">&gt;</span> <a href=""> 汽 修 管 理 </a> <span
		class="c-gray en">&gt;</span> <a href=""> 维 修 领 料 </a> <span
		class="c-gray en">&gt;</span> <a href=""> 领 料</a> <a
		class="btn btn-success radius r"
		style="line-height:1.6em;top:0px;position:absolute;right:20px"
		href="javascript:location.replace(location.href);" title="刷新"> <i
		class="Hui-iconfont"></i> </a> </nav>
	<!--  @*按钮栏*@ -->
	<div class="cl pd-5 bg-1 bk-gray mt-20" style="padding:10px;margin:0px">
		<button class="btn btn-primary " onclick="ShowPeiJian()">
			<i class="Hui-iconfont">&#xe604;</i> 导入估计配件
		</button>
		<button class="btn btn-info " onclick="BaveMianIofo()">
			<i class="Hui-iconfont">&#xe632;</i> 保 存
		</button>
		<button class="btn btn-danger " onclick="Close()">
			<i class="Hui-iconfont">&#xe6a6;</i> 返 回
		</button>
	</div>
	<!--  @*控件栏*@ -->
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend
			style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-weight:inherit">维
			修 领 料 单</legend>
		<div class="layui-field-box">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form class="form-horizontal" role="form" style="margin-top:2px;"
						id="formCollage">
						<input type="text" id="CollageID" hidden /> <input type="text"
							id="ReceptionID" hidden value="${receptionID }" />
						<div class="form-group">
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">车牌号码:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12">
								<input type="text" class="form-control disabled" disabled
									id="CarNum">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">领料员:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12" style="height:45px;">
								<select class="form-control" id="ForeManID"></select> <input
									type="text" style="" class="seinput" id="ForeMan">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">操作人:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12">
								<input type="text" value="" class="form-control disabled"
									id="Operator" disabled>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">客户名称:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12">
								<input type="text" class="form-control disabled" id="Owner"
									disabled>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label"
									style="text-shadow: 1px 1px 5px gray;">结算单号:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12">
								<p style="text-decoration:underline;padding:6px;font-size:16px"
									id="MaintenanceNum"></p>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">领料状态:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12">
								<input type="text" class="form-control disabled"
									id="CollageState" disabled>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">总金额:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12">
								<input type="text" class="form-control disabled" id="Amount"
									disabled>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">审核人:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12">
								<input type="text" value="" class="form-control disabled"
									id="Auditor" disabled>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">审核时间:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12">
								<input type="text" class="form-control disabled" id="AuditDate"
									disabled>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label"
									style="text-shadow: 1px 1px 5px gray;">领料日期:</label>
							</div>
							<div class="col-lg-2 col-md-3 col-xs-12">
								<p style="text-decoration:underline;padding:6px;font-size:16px"
									id="CollageDate"></p>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">备注:</label>
							</div>
							<div class="col-lg-4 col-md-4 col-xs-12">
								<input type="text" class="form-control" style="width:95%"
									id="Remark">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label"
									style="position:relative;bottom:5px;left:0px">是否审核: <input
									type="checkbox"
									style="position:relative;top:4px;left:10px;width:20px;height:20px"
									id="ToAudit" /> </label>
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
		<li onclick="ShowShangPin()"><i class="glyphicon glyphicon-plus"></i>
			添 加</li>
		<li onclick="deleteOne()"><i class="glyphicon glyphicon-trash"></i>
			删 除</li>
	</ul>
	<!--  @*导入配件模态框*@ -->
	<div class="modal fade col-lg-12" id="PeiJianModal">
		<div class="modal-dialog">
			<div class="modal-content" style="width:880px;height:60%">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">选 择 单 据</h4>
				</div>
				<div class="modal-footer" style="border-top:none;">
					<div
						style="height:400px;width:850px;overflow:auto; box-shadow: 0 0 20px 5px #ddd;">
						<table id="tbPeiJianMo" style="width:1350px">
							<tr>
								<th w_index="recProductDetailID" w_check="true">选择</th>
								<th w_num="line">序号</th>
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
					<button type="button" class="btn btn-info "
						onclick="DblPeiJianData()">
						<i class="Hui-iconfont">&#xe632;</i> 确 认
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="btnClose">
						<i class="glyphicon glyphicon-remove"></i>退出
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
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
	</div>
	
	<script
		src="${cxt}/Content/bootstrap-3.3.7-dist/js/jquery-2.0.3.min.js"></script>
	<script src="${cxt}/Content/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script
		src="${cxt}/Content/jquery.bsgrid-1.37/js/lang/grid.zh-CN.min.js"></script>
	<script
		src="${cxt}/Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.js"></script>
	<script src="${cxt}/Content/Main/lib/layer/2.4/layer.js"></script>
	<script src="${cxt}/Content/layui/lay/modules/laydate.js"></script>
	<script src="${cxt}/Content/js/jquery.form.js"></script>
	<script src="${cxt}/Content/js/customfunction.js"></script>
	<script src="${cxt}/Content/js/combobox.js"></script>
	<script>
        //初始化表格
        var tbPeiJianMo, tbDanJu;
        var ArrMaintainData = [], ArrWarehouseData = [];
        var Numbers = /^[-\d.]+$/; //价格
        $(function () {
            create();
            tbPeiJianMo = $.fn.bsgrid.init('tbPeiJianMo', {
                url: '${cxt}/collageController/selectDaoRuPeiJian.action',
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
          
           
            LoadInfo($("#ReceptionID").val());
            run();
            SelectPeiJian();
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
            createSelect("ForeManID", "${cxt}/commonController/selectAppendOption.action?type=SYS_ForeMan");//领料员下拉框
            appendOption("PFittingsTypeID", "${cxt}/commonController/selectAppendOption.action?type=SYS_FittingsType");//商品类别下拉框
            appendOption("PSystemUnitID", "${cxt}/commonController/selectAppendOption.action?type=SYS_SystemUnit");//系统单位下拉框
            appendOption("PVehicleTypeID", "${cxt}/commonController/selectAppendOption.action?type=SYS_VehicleType");//车型下拉框
            appendOption("PSuppliersID", "${cxt}/commonController/selectAppendOption.action?type=SYS_Suppliers");//供应商下拉框

            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_Maintainability", function (data) {
                for (var i = 0; i < data.length; i++) {
                    ArrMaintainData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/commonController/selectAppendOption.action?type=SYS_Warehouse", function (data) {
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
             $.getJSON("${cxt}/collageController/arrlistToCollage.action?receptionID=" + ID, function (data) {
             data=data[0];
                $("#CarNum").val(data.carNum);
                $("#MaintenanceNum").text(data.maintenanceNum);
                $("#Owner").val(data.owner);
                $("#CollageState").val(data.collageState);
            })
            $.getJSON("${cxt}/collageController/selectCollageData.action?receptionID=" + ID, function (data) {
                if(data!=null){
                data=data[0];
                $("#CollageID").val(data.collageId);
                $("#ForeMan").val(data.foreMan);
                $("#CollageDate").text(data.collageDate);
                $("#Amount").val(data.amount);
                $("#ToAudit").val(data.toAudit);
                $("#Operator").val(data.operator);
                $("#AuditDate").val(data.collageDate);
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
                $.post("${cxt}/collageController/updateListCollage.action", { pwCollage: JSON.stringify(ArrCollage), sysCollageDetai: JSON.stringify(ArrCollageDetai) }, function (data) {
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
        
        function CollageDetaiData(CollageID)
        {
            var tbPeiJian = $("#tbPeiJian");
            $.getJSON("${cxt}/collageController/selectCollageDetai.action?collageID=" + CollageID, function (data) {
                $.each(data, function (i) {
                    var length = $("#tbPeiJian tbody tr").last().prevObject.length;
                    var tr = $("<tr><td>" + length + "</td><td id=CA" + length + ">" + data[i].fittingsCode + "</td>"
                    + "<td id=CB" + length + ">" + data[i].fittingsName + "</td><td onclick='tdclick(this)' id=CC" + length + ">" + data[i].fittingsSpec + "</td>"
                    + "<td><select id=CD" + length + "></select></td><td><select id=CE" + length + "></select></td>"
                    + "<td onclick='tdclick(this)' id=CF" + length + ">" + data[i].vehicleType + "</td>"
                    + "<td  id=CG" + length + ">" + data[i].systemUnit + "</td><td onclick='tdclick(this)' id=CH" + length + ">" + data[i].quantity + "</td>"
                    + "<td onclick='tdclick(this)' id=CI" + length + ">" + data[i].unitPrice + ".00" + "</td><td id=CJ" + length + ">"
                    + data[i].amount + ".00" + "</td><td onclick='tdclick(this)' id=CK" + length + ">" + data[i].position + "</td><td hidden id=CL" + length + ">" + data[i].collageDetaiId + "</td></tr>");
                    tbPeiJian.append(tr);
                    DetailEvent(length);
                    $("#CD" + length).val(data[i].maintainabilityId);
                    $("#CE" + length).val(data[i].warehouseId);
                })
            })
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
            tbPeiJianMo.search({ receptionID: $("#ReceptionID").val() });
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
                $("#CD" + length).val(Records[i].maintainabilityId);
                SelectPeiJian();
            })

        }

        /********************导入配件模态框---结束*******************************/

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