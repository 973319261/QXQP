<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cxt" scope="page"></c:set>
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
<title>Appointment</title>
<link href="Content/layui/css/layui.css" rel="stylesheet" />
<link href="Content/Main/lib/Hui-iconfont/1.0.8/iconfont.css"
	rel="stylesheet" />
<link href="Content/Main/css/main.css" rel="stylesheet" />
<link href="Content/Main/static/h-ui.admin/css/H-ui.admin.css"
	rel="stylesheet" />
<link href="Content/Main/tabnav/css/element.min.css" rel="stylesheet" />
<link href="Content/bootstrap-3.3.7-dist/css/bootstrapal.css"
	rel="stylesheet" />
<!--  @*CSS样式(包含默认皮肤样式)*@ -->
<link href="Content/jquery.bsgrid-1.37/merged/bsgrid.all.min.css"
	rel="stylesheet" />
<!--  @*CSS皮肤(需引用于bsgrid.all.min.css之后)*@ -->
<link href="Content/jquery.bsgrid-1.37/css/skins/grid_gray.min.css"
	rel="stylesheet" />
<link href="Content/layui/css/modules/laydate/default/laydate.css"
	rel="stylesheet" />
<style>
a {
	color: #808080;
}

a:hover {
	text-decoration: none;
	color: burlywood;
}

.modal input[type="name"],.modal select {
	height: 32px;
}

.modal .control-label {
	width: 100px;
	padding-top: 0px;
}

.form-group {
	margin-bottom: 0px;
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

#myVuetable tr th,#myVue table tr td:hover { /*background: #ffffff;*/
	
}

#myVue table tr th,#myVue table tr td {
	padding: 5px;
	width: 300px;
	text-align: center;
	border: 1px solid #e2e2e2;
	font-size: 14px;
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
	border: none;
}

#myVue table select {
	width: 100%;
}

.Boxs {
	height: 350px;
	overflow: auto
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
	padding: 3px
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
	width: 320px;
	height: 570px;
	float: left;
	overflow: auto;
	margin-right: 30px;
	box-shadow: 0 0 20px 1px #eee;
}

.modTow {
	width: 600px;
	height: 80%;
	float: left;
	padding: 0px;
	border: 3px solid #f5f5f5;
	box-shadow: 0 0 20px 1px #eee;
	overflow: auto;
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

.form-control[disabled],html input[disabled],textarea {
	background: ghostwhite;
}
</style>
</head>
<body>
	<!--  @*导航栏*@ -->
	<nav class="breadcrumb" style="margin:0px"> <i
		class="Hui-iconfont"></i> <a href=""> 首 页 </a> <span
		class="c-gray en">&gt;</span> <a href=""> 汽 修 管 理 </a> <span
		class="c-gray en">&gt;</span>
	<a href=""> 预 约 安 排 </a> <a class="btn btn-success radius r"
		style="line-height:1.6em;top:0px;position:absolute;right:20px"
		href="javascript:location.replace(location.href);" title="刷新"> <i
		class="Hui-iconfont"></i>
	</a> </nav>
	<!--  @*按钮栏*@ -->
	<div class="cl pd-5 bg-1 bk-gray mt-20" style="padding:10px;margin:0px">
		<button class="btn btn-primary" onclick="btnMainInsert(0)">
			<i class="Hui-iconfont" id="btnInsert">&#xe604;</i> 新 增
		</button>
		<button class="btn btn-danger " onclick="btnMainDelect()"
			id="btnDelect">
			<i class="Hui-iconfont">&#xe609;</i> 删 除
		</button>
		<button class="btn btn-info " onclick="btnMainSava()" id="btnBave">
			<i class="Hui-iconfont">&#xe632;</i> 保 存
		</button>
		<button class="btn btn-pink">
			<i class="Hui-iconfont">&#xe652;</i> 打印
		</button>
		<button class="btn btn-success " onclick="ShowDanJu()">
			<i class="Hui-iconfont">&#xe676;</i> 选择单据
		</button>
		<button class="btn btn-warning " onclick="ToMainten()">
			<i class="Hui-iconfont">&#xe634;</i> 转维修单
		</button>
		<button class="btn btn-primary " onclick="ToAudit()" id="btnAudit">
			<i class="Hui-iconfont">&#xe6e1;</i> 审核
		</button>
		<button class="btn btn-warning" onclick="ToNotAudit()"
			id="btnNotAudit">
			<i class="Hui-iconfont">&#xe6dd;</i> 反审核
		</button>
	</div>
	<!--  @*控件栏*@ -->
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend
			style="margin-left:50px;text-shadow: 5px 5px 5px gray;font-weight:inherit">预
			约 安 排 单</legend>
		<div class="layui-field-box">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<form class="form-horizontal" role="form" style="margin-top:2px;"
						id="formPredate" action="">
						<input type="text" class="form-control" hidden
							id="ToTransferOrder" name="ToTransferOrder" value="false">
						<!-- @*转单否*@ -->
						<input type="text" id="PredateID" hidden>
						<!-- @*预约维修ID*@ -->
						<input type="text" class="form-control" hidden id="CustomerNum"
							name="CustomerNum" value="false">
						<!-- @*客户编码*@ -->
						<div class="form-group">
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">预约单号:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control Disabled"
									id="PredateNums"> <input type="text"
									class="form-control" hidden id="PredateNum" name="PredateNum">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">维修单号:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control Disabled"
									id="MaintenanceNum" name="MaintenanceNum">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">开单日期:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control Datetime" id="OpenDate"
									name="OpenDate">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">维修日期:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control Datetime"
									id="MaintainData" name="MaintainData">
							</div>

						</div>
						<div class="form-group">
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">车牌:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<div class="input-group" style="width:104%">
									<input type="text" class="form-control" id="CarNum"
										name="CarNum" maxlength="7"> <span
										class="input-group-btn">
										<button class="btn btn-primary" type="button"
											onclick="ShowCarNum()">
											<i class="Hui-iconfont">&#xe70f;</i>
										</button> </span>
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">车型:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12" style="height:45px;">
								<select class="form-control" id="VehicleTypeID"></select> <input
									type="text" style="" class="seinput" id="VehicleType"
									name="VehicleType" maxlength="10">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">车主:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control" id="Owner" name="Owner"
									maxlength="5">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">车主电话:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control" id="CarMasterPhone"
									name="CarMasterPhone" maxlength="11">
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">联系人:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control" id="Contacts"
									name="Contacts" maxlength="5">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">联系电话:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control" id="Telephone"
									name="Telephone" maxlength="11">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">接车人:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<select class="form-control" id="CarderID" name="CarderID"></select>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">修理类别:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<select class="form-control" id="RepairID" name="RepairID"></select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">备注:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control" id="Remark"
									name="Remark" maxlength="20">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">总金额:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control" hidden id="Amount">
								<input type="text" class="form-control Disabled" id="Amounts"
									name="Amount">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">应收金额:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<input type="text" class="form-control" id="Receivable"
									name="Receivable">
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12">
								<label class="control-label">已审核:</label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12">
								<label class="checkbox" style="text-align:left;"> <input
									type="checkbox" value="false"
									style="height: 20px;width:20px;position:relative;bottom:3px;"
									class="" id="ToAudit" hidden /> <input type="checkbox"
									id="ToAudits" disabled
									style="height: 20px;width:20px;position:relative;bottom:3px;"
									class="" /> </label>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</fieldset>
	<!--  @*切换卡*@ -->
	<div class="col-lg-12" id="myVue" style="margin:15px">
		<el-tabs type="border-card"> <el-tab-pane label="维修项目">
		<div id="WeiXiuBox" class="Boxs">
			<table id="tbWeiXiu">
				<tbody>
					<tr>
						<th width='25%' height='20px'>修理项目</th>
						<th width='10%' height='20px'>维修工艺</th>
						<th width='10%' height='20px'>修理费</th>
						<th width='10%' height='20px'>折扣(%)</th>
						<th width='10%' height='20px'>实收金额</th>
						<th width='10%' height='20px'>维修性质</th>
						<th width='25%' height='20px'>备注</th>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>合计：</td>
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
		</el-tab-pane> <el-tab-pane label="产品材料">
		<div id="ChanPinBox" class="Boxs">
			<table id="tbChanPin">
				<tbody>
					<tr>
						<th width='25%' height='20px'>配件编号</th>
						<th width='10%' height='20px'>配件名称</th>
						<th width='10%' height='20px'>车型</th>
						<th width='10%' height='20px'>数量</th>
						<th width='10%' height='20px'>单位</th>
						<th width='10%' height='20px'>单价</th>
						<th width='10%' height='20px'>折扣(%)</th>
						<th width='10%' height='20px'>金额</th>
						<th width='10%' height='20px'>维修性质</th>
						<th width='10%' height='20px'>备注</th>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>合计：</td>
						<td></td>
						<td></td>
						<td id="CShuLiang">0.00</td>
						<td></td>
						<td id="CDanJia">0.00</td>
						<td></td>
						<td id="CJinE">0.00</td>
						<td></td>
						<td></td>
					</tr>
				</tfoot>
			</table>
		</div>
		</el-tab-pane> <el-tab-pane label="其他费用">
		<div id="FeiYong" style="" class="Boxs">
			<table id="tbFeiYong">
				<tbody>
					<tr>
						<th width='25%' height='20px'>费用名称</th>
						<th width='10%' height='20px'>金额</th>
						<th width='10%' height='20px'>折扣(%)</th>
						<th width='10%' height='20px'>实收金额</th>
						<th width='25%' height='20px'>备注</th>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>合计：</td>
						<td id="QJinE">0.00</td>
						<td></td>
						<td id="QShiShou">0.00</td>
						<td></td>
					</tr>
				</tfoot>
			</table>
		</div>
		</el-tab-pane> <el-tab-pane label="故障现象描述"> <textarea
			style="width:100%;height:342px;border:none"
			placeholder="请输入汽车的故障信息..." id="Describe"></textarea> </el-tab-pane> </el-tabs>
	</div>
	<!-- @*右击菜单*@ -->
	<ul id="menuOne" class="box">
		<li onclick="addOne()"><i class="glyphicon glyphicon-plus"></i> 添
			加</li>
		<li onclick="deleteOne()"><i class="glyphicon glyphicon-trash"></i>
			删 除</li>
	</ul>
	<ul id="menuTow" class="box">
		<li onclick="ShowShangPin()"><i class="glyphicon glyphicon-plus"></i>
			添 加</li>
		<li onclick="deleteTow()"><i class="glyphicon glyphicon-trash"></i>
			删 除</li>
	</ul>
	<ul id="menuThree" class="box">
		<li onclick="addThree()"><i class="glyphicon glyphicon-plus"></i>
			添 加</li>
		<li onclick="deleteThree()"><i class="glyphicon glyphicon-trash"></i>
			删 除</li>
	</ul>
	<!--  @*车辆模态框*@ -->
	<div class="modal fade col-lg-12" id="CarNumModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">车辆选择</h4>
				</div>
				<div class="modal-body">
					<div class="navsearch">
						<div class="col-lg-6 col-md-6 col-sm-6">
							<form class="form-horizontal" role="form" style="margin-top:2px;">
								<div class="col-lg-2 col-md-2 col-sm-2 text-right">
									<label class="control-label">车 牌 :</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-12 reset"
									style="margin-top:3px">
									<input type="text" class="form-control" id="LicenseCode"
										onkeyup="" />
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 text-right"
									style="margin-left:20px">
									<button type="button" class="btn btn-search"
										onclick="SeleceMaintenanceCus()">
										<i class="glyphicon glyphicon-search"></i>查 询
									</button>
								</div>
							</form>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6">
							<button type="button" class="btn btn-info" onclick="Dblclick()">
								<i class="glyphicon glyphicon-ok-sign"></i>选 中
							</button>
							<button type="button" class="btn btn-success"
								onclick="ClearValue()">
								<i class="glyphicon glyphicon-plus"></i>新 增
							</button>
							<button type="button" class="btn btn-warning" onclick="btnSave()">
								<i class="glyphicon glyphicon-file"></i>保 存
							</button>
							<button type="button" class="btn btn-primary" id="">
								<i class="glyphicon glyphicon-refresh"></i>刷 新
							</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="btnClose">
								<i class="glyphicon glyphicon-remove"></i>关 闭
							</button>
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
								action="${cxt}/servlet/CommonServlet?fun=updateMaintenanceCus"
								method="post">
								<input id="KMaintenanceCusID" type="text"
									name="MaintenanceCusID" hidden />
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">车主:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KOwner"
											name="Owner" maxlength="5">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">生日:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control Date" id="KBirthday"
											name="Birthday">
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">身份证号:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KIdNumber"
											name="IdNumber" maxlength="18">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">车主手机:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KMobilePhone"
											name="MobilePhone" maxlength="15">
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">车牌号码:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KLicenseCode"
											name="LicenseCode" maxlength="7">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">车型:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12" style="height:36px">
										<select class="form-control" id="KVehicleTypeID"
											name="VehicleTypeID"></select> <input type="text"
											style="bottom:36px;left:-20px;height:66%" class="seinput"
											id="KVehicleType" name="VehicleType" maxlength="10">
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">发动机号:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KEngineNum"
											name="EngineNum" maxlength="15">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">车架号:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KFrameNum"
											name="FrameNum" maxlength="15">
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">送修人:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KRepairMan"
											name="RepairMan" maxlength="5">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">送修人电话:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KRepairTele"
											name="RepairTele" maxlength="15">
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">保险公司:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<select class="form-control" id="KInsuranceComID"
											name="InsuranceComID"></select>
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">保险种类:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<select class="form-control" id="KInsuranceSpeID"
											name="InsuranceSpeID"></select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">保险起始日:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control Date"
											id="KInitialStartDate" name="InitialStartDate">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">保险终止日:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control Date"
											id="KInitialEndDate" name="InitialEndDate">
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">地址:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KAddress"
											name="Address" maxlength="15">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">客户编号:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KCustomerNum"
											name="CustomerNum" disabled maxlength="20">
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">行驶证年审:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control Date" id="KDriveDate"
											name="DriveDate">
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
										<select class="form-control" id="KDepartmentID"
											name="DepartmentID"></select>
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
										<select class="form-control" id="KCustomerLevelID"
											name="CustomerLevelID"></select>
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">客户来源:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<select class="form-control" id="KCustomerSouID"
											name="CustomerSouID"></select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">客户类别:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<select class="form-control" id="KCustomerTypeID"
											name="CustomerTypeID"></select>
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">驾驶证年审:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control Date" id="KDrivingDate"
											name="DrivingDate">
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label">录入人:</label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<input type="text" class="form-control" id="KInputPerson"
											name="InputPerson" maxlength="5"
											value="${user.getUserName()}">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12">
										<label class="control-label"
											style="position:relative;bottom:20px;left:20px">修改: <input
											type="checkbox" style="position:relative;top:9px;left:10px"
											id="check" /> </label>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- @*选择单据模态框*@ -->
	<div class="modal fade col-lg-12" id="DanJuModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">记录定位</h4>
				</div>
				<div class="modal-body">
					<div class="navsearch">
						<div class="col-lg-8 col-md-8 col-sm-8">
							<form class="form-horizontal" role="form" style="margin-top:2px;">
								<div class="col-lg-1 col-md-1 col-sm-1 text-right">
									<label class="control-label">单 号</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-12 reset"
									style="margin-top:3px">
									<input type="text" class="form-control" id="DPredateNum"
										onkeyup="" />
								</div>
								<div class="col-lg-1 col-md-1 col-sm-1 text-right">
									<label class="control-label">审 核</label>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-12 reset"
									style="margin-top:3px">
									<select class="form-control" id="DToAudit">
										<option value="">全部</option>
										<option value="0">未审核</option>
										<option value="1">已审核</option>
									</select>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 text-right"
									style="margin-left:20px">
									<button type="button" class="btn btn-search"
										onclick="SelecePredate()">
										<i class="glyphicon glyphicon-search"></i>查 询
									</button>
								</div>

							</form>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4">
							<button type="button" class="btn btn-info" id=""
								style="margin-left:120px;">
								<i class="glyphicon glyphicon-ok-sign"></i>选 中
							</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="btnClose">
								<i class="glyphicon glyphicon-remove"></i>关 闭
							</button>
						</div>
					</div>

				</div>
				<div class="modal-footer" style="border-top:none;">
					<div
						style="height:500px;width:950px;overflow:auto; box-shadow: 0 0 20px 5px #ddd;">
						<table id="tbDanJu" style="width:100%">
							<tr>
								<th w_render="SetState">审核状态</th>
								<th w_index="toAudit" w_hidden="true">审核状态</th>
								<th w_index="predateNum">单号</th>
								<th w_index="openDate">开单日期</th>
								<th w_index="maintainData">预约日期</th>
								<th w_index="carNum">车牌</th>
								<th w_index="vehicleType">车型</th>
								<th w_index="owner">车主</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!--  @*新增产品产品模态框*@ -->
	<div class="modal fade col-lg-12" id="ShangPinModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">商品类别查找(多选)</h4>
				</div>
				<div class="modal-body">
					<div class="navsearch">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<form class="form-horizontal" role="form">
								<div class="col-lg-1 col-md-1 col-sm-2 text-right">
									<label class="control-label">快速查找 :</label>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-12 reset"
									style="margin-right:10px">
									<select class="form-control" id="selectInfo">
										<option value="1">商品类别</option>
										<option value="2">商品编码</option>
										<option value="3">商品名称</option>
										<option value="4">所属车型</option>
									</select>
								</div>
								<div class="col-lg-3 col-md-3 col-sm-12 reset"
									style="margin-right:170px">
									<input type="text" class="form-control" id="Info" onkeyup="" />
								</div>
								<button type="button" class="btn btn-search"
									onclick="SeleceFittingsInfo()">
									<i class="glyphicon glyphicon-search"></i>查 询
								</button>
								<button type="button" class="btn btn-info"
									onclick="btnFittingSelect()">
									<i class="glyphicon glyphicon-ok-sign"></i>选 中
								</button>
								<button type="button" class="btn btn-success"
									onclick="ShowAddPeiJian()">
									<i class="glyphicon glyphicon-plus"></i>新 增
								</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal" id="btnClose">
									<i class="glyphicon glyphicon-remove"></i>关 闭
								</button>
							</form>
						</div>
					</div>

				</div>
				<div class="modal-footer" style="border-top:none;">
					<div>
						<div class="modOne" id="FittingsType" style="width:280px">
							<ul>
								<li onclick="SeleceFittingsInfo()"><i
									style="background: url('${cxt}/Content/Main/images/smiley_sleep.png') no-repeat 0px 5px;">
								</i> 货品分类 &nbsp;共有<span></span>个类别</li>
							</ul>
						</div>
						<div class="modTow col-lg-12 col-md-12 col-sm-12"
							style="width:640px;height:570px">
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
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- @*配件基本资料添加模态框*@ -->
	<div class="modal fade col-lg-12" id="AddPeiJianModal">
		<div class="modal-dialog">
			<div class="modal-content" style="width:850px;left:-133px;top:60px;height: 67%">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">配件基本资料添加</h4>
				</div>
				<div class="modal-footer" style="border-top:none;">
					<div>
						<fieldset class="layui-elem-field layui-field-box"
							style="margin-top: 0px;">
							<legend
								style="font-size:16px;margin-left:35px;text-shadow: 5px 5px 5px gray;font-weight:inherit">双击框体，弹出路径，选择对应图片保存即可</legend>
							<div class="col-lg-12 col-md-12 col-sm-12" style="width:102%">
								<form class="form-horizontal" id=InfoForm
									action="${cxt}/servlet/CommonServlet?fun=updateFittingsInfo"
									enctype="multipart/form-data" method="post">
									<div style="float:left;width:250px;height:300px;">
										<div class="img-box text-center">
											<img src="" alt="" id="imgCarPicture" /> <input type="file"
												class="hidden"
												style="margin-left:3px;margin-top:10px;font-size:10px"
												name="Picture" id="CarPicture" />
										</div>
									</div>
									<input type="hidden" name="InvenQuan" vaule="0.0" />
									<div
										style="float:left;width:500px;height:300px;margin-left:30px">
										<div class="form-group">
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">配件编码:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="text" class="form-control" id="PFittingsCode"
													name="FittingsCode" maxlength="5">
											</div>
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">商品类别:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<select class="form-control" id="PFittingsTypeID"
													name="FittingsTypeID"></select>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">配件名称:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="text" class="form-control" id="PFittingsName"
													name="FittingsName" maxlength="10">
											</div>
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">条码:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="text" class="form-control" id="PBarcode"
													name="Barcode" maxlength="10">
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">配件规格:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="text" class="form-control" id="PSpecification"
													name="Specification" maxlength="10">
											</div>
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">品牌:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="text" class="form-control" id="PBrand"
													name="Brand" maxlength="5">
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">单位:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<select class="form-control" id="PSystemUnitID"
													name="SystemUnitID"></select>
											</div>
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">所属车型:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<select class="form-control" id="PVehicleTypeID"
													name="VehicleTypeID"></select>
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">进价:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="number" class="form-control" id="PIntake"
													name="Intake">
											</div>
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">销售价格:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="number" class="form-control" id="PSalesPrice"
													name="SalesPrice">
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">批发价:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="number" class="form-control"
													id="PWholesalePrice" name="WholesalePrice">
											</div>
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">库存上限:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="number" class="form-control" id="PInventoryMax"
													name="InventoryMax">
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">库存下限:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="number" class="form-control" id="PInventoryMin"
													name="InventoryMin">
											</div>
											<div class="col-lg-3 col-md-3 col-xs-12">
												<label class="control-label">开单价:</label>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-12">
												<input type="number" class="form-control" id="POpenPrice"
													name="OpenPrice">
											</div>
										</div>
									</div>
									<div class="form-group" style="padding-top:30px">
										<div class="col-lg-3 col-md-3 col-xs-12" style="width:60px">
											<label class="control-label">供应商:</label>
										</div>
										<div class="col-lg-4 col-md-4 col-xs-12">
											<select class="form-control" id="PSuppliersID"
												name="SuppliersID"></select>
										</div>
										<div class="col-lg-3 col-md-3 col-xs-12" style="width:140px">
											<label class="control-label">备注:</label>
										</div>
										<div class="col-lg-4 col-md-4 col-xs-12">
											<input type="text" class="form-control" style="width:300px"
												id="PRemark" name="Remark" maxlength="20">
										</div>
									</div>
									<p
										style="text-align:left;line-height:26px;color:orangered;margin:0">说明：系统根据配件编码，名称和规格来确定产品</p>
								</form>
							</div>
						</fieldset>
						<button type="button" class="btn btn-info "
							onclick="btnFittingsSava()">
							<i class="Hui-iconfont">&#xe632;</i> 保 存
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="btnClose">
							<i class="glyphicon glyphicon-remove"></i>关 闭
						</button>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
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
	<script src="Content/js/combobox.js"></script>
	<script src="Content/js/customfunction.js"></script>
	<script>
        $.ajaxSettings.async = false;
        var tbKeHu, tbShangPin, tbDanJu;
        var ArrMaintainData = [], ArrExpensesData = [], ArrRepairItemDate = [], ArrMaintenanceDate = [];
        var Chinese = /^[\u0391-\uFFE5]+$/;//只能输入中文
        var Number = /^[0-9]+$/; //只能输入数字
        var Numbers = /^[-\d.]+$/; //价格
        var Pattern = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;//车牌号码京K39006
        var Identity = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;//18位身份证号码
        var Phone = /^[1][0-9][0-9]{9}$/;//11位手机号码
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
        //页面加载事件
        $(function () {
            btnMainInsert(0), create(), ClearValue();
            tbKeHu = $.fn.bsgrid.init('tbKeHu', {
                url: '${cxt}/servlet/CommonServlet?fun=seleceMaintenanceCus',
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
                url: '${cxt}/servlet/AppointmentServlet?fun=selectPredate',
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
                           PredateData(record);
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
            SeleceFittingsInfo();
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

        //文本框与下拉框合并
        $("#VehicleTypeID").on("change", function () {
            var text = $(this).find("option:selected").text();
            $("#VehicleType").val(text)
        })
        $("#KVehicleTypeID").on("change", function () {
            var text = $(this).find("option:selected").text();
            $("#KVehicleType").val(text)
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
        }
        //右击事件
        $(function () {
            $("body").click(function () {
                resOne.style.display = 'none';
                resTow.style.display = 'none';
                resThree.style.display = 'none';
            })
            //阻止浏览器默认右键点击事件
            document.oncontextmenu = function() {
                return false;
            }
            var resOne = document.getElementById('menuOne');   //找到id为box的div
            var resTow = document.getElementById('menuTow');
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
            document.getElementById("ChanPinBox").onmouseup = function (e) {   //在body里点击触发事件
                if (e.button === 2) {    //如果button=1（鼠标左键），button=2（鼠标右键），button=0（鼠标中间键）
                    resTow.style.top = e.clientY + 'px';   //鼠标点击时给div定位Y轴
                    resTow.style.left = e.clientX + 'px';  //鼠标点击时给div定位X轴
                    resTow.style.display = 'block';    //显示div盒子
                } else {
                    resTow.style.display = 'none';     //否则不显示div盒子
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
            var tr = $("<tr><td><select id=WA" + length + "></select></td><td><select id=WB" + length + " disabled='disabled'></select></td>"
                + "<td onclick='tdclick(this)' id=WC" + length + ">0.00</td><td onclick='tdclick(this)' id=WD" + length + " >100</td>"
                + "<td  id=WE" + length + ">0.00</td><td><select id=WF" + length + "></select></td>"
                + "<td onclick='tdclick(this)' id=WG" + length + "></td><td hidden id=WH" + length + "></td></tr>");
            tbWeiXiu.append(tr);
            //绑定表格下拉框
            var length = $("#tbWeiXiu tbody tr").last().prevObject.length - 1;
            selectcreate("WA" + length, ArrRepairItemDate);
            selectcreate("WB" + length, ArrMaintenanceDate);
            selectcreate("WF" + length, ArrMaintainData);
            var RepairItemID = $("#WA" + length).val();
            if (RepairItemID != 0) {
                $.getJSON("${cxt}/servlet/CommonServlet?fun=selectRepairItemChange&RepairItemID=" + RepairItemID, function (data) {
                    data=data.list;
                    $("#WB" + length).val(data.maintenanceID);
                    $("#WC" + length).text(data.repairCharge + ".00");
                    $("#WE" + length).text(data.repairCharge + ".00");
                })
            }
            DetailEvent(length,1);
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
        //删除产品材料行
        function deleteTow() {
            var length = $("#tbChanPin tbody").find("tr").length;
            if (length > 1) {
                $("#tbChanPin tbody").find("tr").last().remove();//找到最后一个选项然后删除
            }
            ClearDetailToT();
            DetailEvent(length, 2);

        }
        //添加其他费用行
        function addThree() {
            var tbFeiYong = $("#tbFeiYong");
            var length = $("#tbFeiYong tbody tr").length;
            var tr = $("<tr><td><select id=QA" + length + "></select></td><td onclick='tdclick(this)' id=QB" + length + ">0.00</td>"
                + "<td onclick='tdclick(this)' id=QC" + length + ">100</td><td onclick='tdclick(this)' id=QD" + length + ">0.00</td>"
                + "<td onclick='tdclick(this)' id=QE" + length + "></td><td hidden id=QF" + length + "></td></tr>");
            tbFeiYong.append(tr);
            var length = $("#tbFeiYong tbody tr").last().prevObject.length - 1;
            selectcreate("QA" + length, ArrExpensesData);
            var ExpensesID = $("#QA" + length).val();
            $.getJSON("${cxt}/servlet/CommonServlet?fun=selectExpensesChange&ExpensesID=" + ExpensesID, function (data) {
                data=data.list;
                $("#QB" + length).text(data.cost + ".00");
                $("#QD" + length).text(data.cost + ".00");
                $("#QE" + length).text();
            })
            DetailEvent(length,3);
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
        //生成预约单号
        function PredateNum() {
            $.post("${cxt}/servlet/AppointmentServlet?fun=predateNum", function (data) {
                $("#PredateNum").val(data);
                $("#PredateNums").val(data);
            })
        }
        //绑定下拉框
        function create() {
            $.ajaxSettings.async = false;
            createSelect("VehicleTypeID", "${cxt}/servlet/CommonServlet?t=SYS_VehicleType");//车型下拉框
            createSelect("CarderID", "${cxt}/servlet/CommonServlet?t=SYS_Carder");//接车人下拉框
            createSelect("RepairID", "${cxt}/servlet/CommonServlet?t=SYS_Repair");//车型下拉框

            createSelect("KVehicleTypeID", "${cxt}/servlet/CommonServlet?t=SYS_VehicleType");//车型下拉框
            appendOption("KInsuranceComID", "${cxt}/servlet/CommonServlet?t=SYS_InsuranceCom");//保险公司下拉框
            appendOption("KInsuranceSpeID", "${cxt}/servlet/CommonServlet?t=SYS_InsuranceSpe");//保险种类下拉框
            appendOption("KDepartmentID", "${cxt}/servlet/CommonServlet?t=SYS_Department");//部门下拉框
            appendOption("KCarderID", "${cxt}/servlet/CommonServlet?t=SYS_Carder");//接车人下拉框
            appendOption("KCustomerLevelID", "${cxt}/servlet/CommonServlet?t=SYS_CustomerLevel");//客户等级下拉框
            appendOption("KCustomerTypeID", "${cxt}/servlet/CommonServlet?t=SYS_CustomerType");//客户类型下拉框
            appendOption("KRegionID", "${cxt}/servlet/CommonServlet?t=SYS_Region");//区域下拉框
            appendOption("KCustomerSouID", "${cxt}/servlet/CommonServlet?t=SYS_CustomerSou");//区域下拉框

            appendOption("PFittingsTypeID", "${cxt}/servlet/CommonServlet?t=SYS_FittingsType");//商品类别下拉框
            appendOption("PSystemUnitID", "${cxt}/servlet/CommonServlet?t=SYS_SystemUnit");//系统单位下拉框
            createSelect("PVehicleTypeID", "${cxt}/servlet/CommonServlet?t=SYS_VehicleType");//车型下拉框
            appendOption("PSuppliersID", "${cxt}/servlet/CommonServlet?t=SYS_Suppliers");//供应商下拉框

            $.getJSON("${cxt}/servlet/CommonServlet?t=SYS_Maintainability", function (data) {
                data=data.list;
                for (var i = 0; i < data.length; i++) {
                    ArrMaintainData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/servlet/CommonServlet?t=SYS_Expenses", function (data) {
             data=data.list;
                for (var i = 0; i < data.length; i++) {
                    ArrExpensesData.push(data[i]);
                }
            })
            $.getJSON("${cxt}/servlet/CommonServlet?t=SYS_RepairItem", function (data) {
                data=data.list;
                for (var i = 0; i < data.length; i++) {
                    ArrRepairItemDate.push(data[i]);
                }
            })
            $.getJSON("${cxt}/servlet/CommonServlet?t=SYS_Maintenance", function (data) {
                 data=data.list;
                for (var i = 0; i < data.length; i++) {
                    ArrMaintenanceDate.push(data[i]);
                }
            })
        }
        //明细表事件
        function DetailEvent(length,Num)
        {
            if (Num == 1) {
                selectcreate("WA" + length, ArrRepairItemDate);
                selectcreate("WB" + length, ArrMaintenanceDate);
                selectcreate("WF" + length, ArrMaintainData);
                var RepairItemID = $("#WA" + length).val();
                $("#WA" + length).change(function () {
                    var RepairItemID = $("#WA" + length).val();
                    if (RepairItemID != 0) {
                        $.getJSON("${cxt}/servlet/CommonServlet?fun=selectRepairItemChange&RepairItemID=" + RepairItemID, function (data) {
                            data=data.list;
                            $("#WB" + length).val(data.maintenanceID);
                            $("#WC" + length).text(data.repairCharge + ".00");
                            $("#WE" + length).text(data.repairCharge + ".00");
                        })
                    }
                })
                if ($("#WG" + length).text() == "null")  $("#WG" + length).text("无");
                WeiXiu();
                function WeiXiu()
                {
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
                    } else if ($("#WF" + length).val() == 0) {
                        layer.tips('请选择维修性质!', '#WF' + length);
                    } else if ($("#WG" + length).text().length > 20) {
                        layer.tips('数据超出有效长度!', '#WG' + length);
                    }
                    WeiXiu();
                })
            }
            if (Num == 2) {
                createSelect("CI" + length, "${cxt}/servlet/CommonServlet?t=SYS_Maintainability");
                if ($("#CJ" + length).text() == "null") $("#CJ" + length).text("无");
                $("#CI" + length).val(2);
                ChanPin();
                function ChanPin() {
                    var CShuLiang = 0, CDanJia = 0, CJinE = 0;
                    for (var i = 1; i < length + 1; i++) {
                        if ($("#CD" + i).text() != "") {
                            CShuLiang = parseFloat(CShuLiang) + parseFloat($("#CD" + i).text());
                            $("#CShuLiang").text(parseFloat(CShuLiang));
                        }
                        if ($("#CF" + i).text() != "") {
                            CDanJia = parseFloat(CDanJia) + parseFloat($("#CF" + i).text());
                            $("#CDanJia").text(parseFloat(CDanJia));
                        }
                        if ($("#CH" + i).text() != "") {
                            CJinE = parseFloat(CJinE) + parseFloat($("#CF" + i).text());
                            $("#CJinE").text(parseFloat(CJinE));
                        }
                    }
                }
                $("#tbChanPin").click(function () {
                    var Quantity = $("#CD" + length).text();
                    var SalesPrice = $("#CF" + length).text();
                    var Discount = $("#CG" + length).text();
                    var Cal = Quantity * SalesPrice * Discount / 100;
                    $("#CH" + length).text(parseFloat(Cal));
                    if ($("#CA" + length).text().length > 5) {
                        layer.tips('数据超出有效长度!', '#CA' + length);
                    } else if ($("#CA" + length).text() == "") {
                        layer.tips('配件编号不能为空!', '#CA' + length);
                    } else if (($("#CB" + length).text().length > 10)) {
                        layer.tips('数据超出有效长度!', '#CB' + length);
                    } else if (($("#CC" + length).text().length > 10)) {
                        layer.tips('数据超出有效长度!', '#CC' + length);
                    } else if (!Numbers.test(Quantity)) {
                        layer.tips('只能输入数字!', '#CD' + length);
                    } else if (($("#CE" + length).text().length > 5)) {
                        layer.tips('数据超出有效长度!', '#CE' + length);
                    } else if (!Numbers.test(SalesPrice)) {
                        layer.tips('只能输入数字!', '#CF' + length);
                    } else if (!Numbers.test(Discount)) {
                        layer.tips('只能输入数字!', '#CG' + length);
                    } else if (parseInt(Discount) > 100 || parseInt(Discount) < 0) {
                        layer.tips('数据超出有效长度!', '#CG' + length);
                    } else if ($("#CJ" + length).text().length > 20) {
                        layer.tips('数据超出有效长度!', '#CJ' + length);
                    }
                    ChanPin();
                })
            }
            if (Num == 3) {
                var ExpensesID = $("#QA" + length).val();
                $("#QA" + length).change(function () {
                    var ExpensesID = $("#QA" + length).val();
                    if (ExpensesID != 0) {
                        $.getJSON("${cxt}/servlet/CommonServlet?fun=selectExpensesChange&ExpensesID=" + ExpensesID, function (data) {
                            data=data.list;
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
        function ClearDetailToT()
        {
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
        function btnMainInsert(data)
        {
            $("#myVue tbody tr td").parent("tr").remove();
            $("#Describe").val("");
            ClearDetailToT();
            $("#formPredate").resetForm();
            $("#ToAudit").val("false");
            if (data==0) {
                PredateNum(), Datetime(), NotAuditEvent();
            }
        }
        //主页面删除按钮
        function btnMainDelect()
        {
            var PredateID = $("#PredateID").val();
            if (PredateID != "") {
                $.post("${cxt}/servlet/AppointmentServlet?fun=deleteListPredate&PredateID=" + PredateID, function (data) {
                    if (data) {
                        layer.msg("数 据 删 除 成 功 ！");
                        btnMainInsert(0);
                    } else {
                        layer.msg("数 据 删 除 失 败 ！")
                    }
                })
            } else {
                layer.alert('请选择数据再进行删除操作！', {
                    skin: 'layui-layer-lan' //样式类名
                      , closeBtn: 0, anim: 4 //动画类型
                });
            }
        }
        //主页面保存
        function btnMainSava() {
            var CarNum = $("#CarNum").val();
            var VehicleType = $("#VehicleType").val();
            var Owner = $("#Owner").val();
            var Telephone = $("#Telephone").val();
            var Contacts = $("#Contacts").val();
            var CarMasterPhone = $("#CarMasterPhone").val();
            var CarderID = $("#CarderID").val();
            var RepairID = $("#RepairID").val();
            var ArrPredate = [], ArrPreRepairItem = [], ArrPreProduct = [], ArrPreOtherCost = [];
            Arrlist(ArrPredate, ArrPreRepairItem, ArrPreProduct, ArrPreOtherCost);
            if (CarNum == "") {
                layer.alert('车牌号码不能为空!');
            } else if (VehicleType == "") {
                layer.alert('车型不能为空!');
            } else if (Owner == "") {
                layer.alert('车主姓名不能为空!');
            } else if (CarMasterPhone == "") {
                layer.alert('车主电话不能为空!');
            } else if (Contacts == "") {
                layer.alert('联系人不能为空!');
            } else if (Telephone == "") {
                layer.alert('联系人电话不能为空!');
            } else if (CarderID == 0) {
                layer.alert('请选择接车人!');
            } else if (RepairID == 0) {
                layer.alert('请选择修理类别!');
            } else if (!Pattern.test(CarNum)) {
                layer.alert('请输入正确的车牌号码!');
            } else if (!Phone.test(Telephone)) {
                layer.alert('请输入正确的手机号码!');
            } else if (!Phone.test(CarMasterPhone)) {
                layer.alert('请输入正确的手机号码!');
            } else {
                layer.confirm('是否保存当前信息？', {
                    btn: ['保存', '取消'] //按钮
                }, function () {
                    var index = layer.load(0); //0代表加载的风格，支持0-2
                    $.ajax({
			                type: "POST",
			                url: "${cxt}/servlet/AppointmentServlet?fun=updateListPredate",
			                data: {PW_Predate: JSON.stringify(ArrPredate), SYS_PreRepairItemDetail: JSON.stringify(ArrPreRepairItem),
                                   SYS_PreProductDetail: JSON.stringify(ArrPreProduct) , SYS_PreOtherCostDetail: JSON.stringify(ArrPreOtherCost)},
			                traditional:true,//阻止jquery对数组序列化
			                dataType:"text",
                            contentType:"application/x-www-form-urlencoded",
			                success: function (data) {
			                     $("#PredateID").val(data);
			                     layer.msg("保存成功",{icon: 6}); 
			                     layer.closeAll('loading'); //关闭loading
			                },
			                error: function (data) {
			                    layer.msg("保存失败",{icon: 7}); 
			                    layer.closeAll('loading'); //关闭loading
			               }
                        });
                })
            }
        }
        //主页面选择单据
        function ShowDanJu() {
            SelecePredate();
            $("#DanJuModal").modal("show");
        }
        //转维修单
        function ToMainten()
        {
            var  ArrPredate = [], ArrPreRepairItem = [], ArrPreProduct = [], ArrPreOtherCost = [];
            Arrlist(ArrPredate, ArrPreRepairItem, ArrPreProduct, ArrPreOtherCost);
            var PredateID = $("#PredateID").val();
            var ToAudit = $("#ToAudit").val();
            var ToTransferOrder = $("#ToTransferOrder").val();
            var MaintenanceNum = $("#MaintenanceNum").val();
            if (ToAudit != "false") {
                if (ToTransferOrder == "false") {
                     $.ajax({
			                type: "POST",
			                url: "${cxt}/servlet/CustomerServlet?fun=customer",
			                data: {PW_Predate: JSON.stringify(ArrPredate), SYS_PreRepairItemDetail: JSON.stringify(ArrPreRepairItem),
                                   SYS_PreProductDetail: JSON.stringify(ArrPreProduct) , SYS_PreOtherCostDetail: JSON.stringify(ArrPreOtherCost)},
			                traditional:true,//阻止jquery对数组序列化
			                dataType:"text",
                            contentType:"application/x-www-form-urlencoded",
			                success: function (data) {
			                    window.location.href = "${cxt}/jsp/Mechanics/customer.jsp";
			                },
			                error: function (data) {
			                    layer.msg("转单失败",{icon: 7}); 
			               }
                        });
                } else {
                    layer.alert('已转维修单' + MaintenanceNum + '无需重复转单，请核实！', {
                        skin: 'layui-layer-molv' //样式类名
                 , closeBtn: 0
                    });
                }
            } else {
                layer.alert('必须审核后才能转维修单,请核实！', {
                    skin: 'layui-layer-lan' //样式类名
                 , closeBtn: 0, anim: 4 //动画类型
                });
            }
        }
        //主页面信息
        function Arrlist(ArrPredate, ArrPreRepairItem, ArrPreProduct, ArrPreOtherCost)
        {
            var Wshishou = 0, Cjine = 0;
            var WeiXiulength = $("#tbWeiXiu tbody tr").length;
            var ChanPinlength = $("#tbChanPin tbody tr").length;
            var FeiYonglength = $("#tbFeiYong tbody tr").length;
            var Amounts = parseFloat($("#WShiShou").text()) + parseFloat($("#CJinE").text()) + parseFloat($("#QShiShou").text());
            $("#Amounts").val(parseFloat(Amounts));//总金额
            $("#Amount").val(parseFloat(Amounts));//总金额
            for (var i = 1; i < WeiXiulength; i++) {//修理项目构造函数
                var e = new PreRepairItem();//构造函数
                e.RepairItemID = $("#WA" + i).val();
                e.RepairCharge = $("#WC" + i).text();
                e.Discount = $("#WD" + i).text();
                e.AmountPaid = $("#WE" + i).text();
                e.MaintainabilityID = $("#WF" + i).val();
                e.Remark = $("#WG" + i).text();
                e.PreRepairItemDetailID = $("#WH" + i).text();
                ArrPreRepairItem.push(e);
                if ($("#WF" + i).val() == "3" || $("#WF" + i).val() == "4") {
                    Wshishou = parseFloat(Wshishou) + parseFloat($("#WE" + i).text());
                }
            }
            for (var i = 1; i < ChanPinlength; i++) {//产品材料构造函数
                var e = new PreProduct();//构造函数
                e.FittingsCode = $("#CA" + i).text();
                e.FittingsName = $("#CB" + i).text();
                e.VehicleType = $("#CC" + i).text();
                e.Quantity = $("#CD" + i).text();
                e.SystemUnit = $("#CE" + i).text();
                e.UnitPrice = $("#CF" + i).text();
                e.Discount = $("#CG" + i).text();
                e.Amount = $("#CH" + i).text();
                e.MaintainabilityID = $("#CI" + i).val();
                e.Remark = $("#CJ" + i).text();
                e.PreProductDetailID = $("#CK" + i).text();
                e.FittingsSpec = $("#CL" + i).text();
                ArrPreProduct.push(e);
                if ($("#CI" + i).val() == "3" || $("#CI" + i).val() == "4") {
                    Cjine = parseFloat(Cjine) + parseFloat($("#CH" + i).text());
                }
            }
            for (var i = 1; i < FeiYonglength; i++) {//产品材料构造函数
                var e = new PreOtherCost();//构造函数
                e.ExpensesID = $("#QA" + i).val();
                e.Amount = $("#QB" + i).text();
                e.Discount = $("#QC" + i).text();
                e.AmountPaid = $("#QD" + i).text();
                e.Remark = $("#QE" + i).text();
                e.PreOtherCostDetailID = $("#QF" + i).text();
                ArrPreOtherCost.push(e);
            }
            var Receivable = (Amounts) - ((Wshishou) + (Cjine));
            $("#Receivable").val((Receivable));//应收金额(四舍五入)
            var e = new Predate();
            e.PredateID = $("#PredateID").val();//预约ID
            e.PredateNum = $("#PredateNum").val();//预约编号
            e.MaintenanceNum = $("#MaintenanceNum").val();//维修单号
            e.OpenDate = $("#OpenDate").val();//开单日期
            e.MaintainData = $("#MaintainData").val();//维修日期
            e.CarNum = $("#CarNum").val();//车牌
            e.CustomerNum = $("#CustomerNum").val();//客户编码
            e.VehicleType = $("#VehicleType").val();//车型
            e.Owner = $("#Owner").val();//车主
            e.CarMasterPhone = $("#CarMasterPhone").val();//车主电话
            e.Contacts = $("#Contacts").val();//联系人
            e.Telephone = $("#Telephone").val();//联系人电话
            e.CarderID = $("#CarderID").val();//接车人
            e.RepairID = $("#RepairID").val();//修理类别
            e.Remark = $("#Remark").val();//备注
            e.Amount = $("#Amount").val();//总金额
            e.Receivable = $("#Receivable").val();//应收金额
            e.ToAudit = $("#ToAudit").val();//审核否
            e.Describe = $("#Describe").val();//描述
            e.ToTransferOrder = $("#ToTransferOrder").val();//转单否
            ArrPredate.push(e);
        }
        //主页面审核
        function ToAudit() {
            var PredateID = $("#PredateID").val();
            if (PredateID != "") {
                $("#ToAudit").val("true");

                $.post("${cxt}/servlet/AppointmentServlet?fun=toAudit&PredateID=" + $("#PredateID").val(), function (data) {
                    if (data) {
                        layer.alert('审核成功，数据不能调整！', {
                            skin: 'layui-layer-molv' //样式类名
                      , closeBtn: 0
                        });
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
            $.post("${cxt}/servlet/AppointmentServlet?fun=toNotAudit&PredateID=" + $("#PredateID").val(), function (data) {
                if (data) {
                    layer.alert('已取消审核，可以进行数据修改啦！', {
                        skin: 'layui-layer-molv' //样式类名
                  , closeBtn: 0
                    });
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
        function AuditEvent()
        {
            $("#btnDelect").attr("disabled", true);
            $("#btnBave").attr("disabled", true);
            $("#btnAudit").attr("disabled", true);
            $("#btnNotAudit").attr("disabled", false);
            $("#formPredate input").attr("disabled", true);
            $("#formPredate select").attr("disabled", true); 
            $("#myVue select").attr("disabled", true); 
            $("#Describe").attr("disabled", true);
            $("#myVue tbody td").attr("onclick", "");
            $("#ToAudits").attr("checked", true);
        }
        //反审核按钮状态
        function NotAuditEvent() {
            $("#btnDelect").attr("disabled", false);
            $("#btnBave").attr("disabled", false);
            $("#btnAudit").attr("disabled", false);
            $("#btnNotAudit").attr("disabled", true);
            $("#formPredate input").attr("disabled", false);
            $("#formPredate select").attr("disabled", false);
            $("#myVue select").attr("disabled", false);
            $("#Describe").attr("disabled", false);
            $("#myVue tbody td").attr("onclick", "tdclick(this)");
            $("select").parent("td").attr("onclick", "");
            $(".Disabled").attr("disabled", true);
            $("#ToAudits").removeAttr("checked");

        }
        //判断是审核否
        function ToFales()
        {
            if ($("#PredateID").val() != 0) {
                if ($("#ToAudit").val() == "false"||$("#ToAudit").val() ==false) {
                     NotAuditEvent();
                } else {
                    AuditEvent();
                }
            }
        }
        
        /********************主页面信息---结束*******************************/

        /********************车牌模态框---开始*******************************/

        //选择车牌按钮弹出维修客户模态框
        function ShowCarNum() {
            SeleceMaintenanceCus();
            CustomerNumData();
            $("#CarNumModal").modal("show");
        }
        //客户表格条件查询
        function SeleceMaintenanceCus() {
            var LicenseCode = $("#LicenseCode").val();
            if (LicenseCode == null || LicenseCode == undefined) LicenseCode = "";
            tbKeHu.search({ LicenseCode: LicenseCode });

        }
        //客户编码回填
        function CustomerNumData()
        {
            $.post("${cxt}/servlet/CommonServlet?fun=customerNum", function (data) {
                $("#KCustomerNum").val(data);
            })
        }
        //维修客户信息回填
        function MaintenanceCusData(recoed) {
            $("#KMaintenanceCusID").val(recoed.maintenanceCusID)
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
            $("#KInsuranceComID").val(recoed.insuranceComID);
            $("#KInsuranceSpeID").val(recoed.insuranceSpeID);
            $("#KInitialStartDate").val(recoed.initialStartDate);
            $("#KInitialEndDate").val(recoed.initialEndDate);
            $("#KAddress").val(recoed.address);
            $("#KCustomerNum").val(recoed.customerNum);
            $("#KDriveDate").val(recoed.driveDate);
            $("#KRegionID").val(recoed.regionID);
            $("#KDepartmentID").val(recoed.departmentID);
            $("#KCarderID").val(recoed.carderID);
            $("#KCustomerLevelID").val(recoed.customerLevelID);
            $("#KCustomerSouID").val(recoed.customerSouID);
            $("#KCustomerTypeID").val(recoed.customerTypeID);
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
                    if (data>0) {
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
                $("#CustomerNum").val(record.customerNum);
                $("#VehicleType").val(record.vehicleType);
                $("#Owner").val(record.owner);
                $("#Telephone").val(record.mobilePhone);
                $("#Contacts").val(record.owner);
                $("#CarMasterPhone").val(record.mobilePhone);
            } else {
                layer.msg("如果需要选择一行数据，请进行操作！")
            }
        }
        //清空汽车模态框文本框值
        function ClearValue() {
            $("#formMaintenanceCus").resetForm();
            $("#formMaintenanceCus select").attr("disabled", false);
            $("#formMaintenanceCus input[type='text']").attr("disabled", false);
            CustomerNumData();
        }

        /********************车牌模态框---结束******************************/

        /********************单据模态框---开始*******************************/

       
        //单据表格查询
        function SelecePredate() {
            var DToAudit = $("#DToAudit").val();
            var DPredateNum = $("#DPredateNum").val();
            if (DToAudit == "0") InfoOne = Info;
            if (DToAudit == undefined || DToAudit == null) DToAudit = "";
            if (DPredateNum == undefined || DPredateNum == null) DPredateNum = "";
            tbDanJu.search({ PredateNum: DPredateNum, ToAudit: DToAudit });
        }
        //选择单据自定义事件 创建是否审核
        function SetState(record) {
            var ToAudit = record.toAudit;
            if (ToAudit == true || ToAudit == "true") {
                return "<span style='color:red;'>✔ 已审核</span>";
            } else {
                return "<span style='color:blue;'>✘ 未审核</span>";
            }
        }
        //选择单据回填信息
        function PredateData(record)
        {
            var index = layer.load(0); //0代表加载的风格，支持0-2
            btnMainInsert(1);
            $.getJSON("${cxt}/servlet/AppointmentServlet?fun=selectPredateDetail&PredateID=" + record.predateID, function (data) {
                data=data.list;
                $("#DanJuModal").modal("hide");
                $.each(data[0],function(i)
                {
                    var tbWeiXiu = $("#tbWeiXiu");
                    var length = $("#tbWeiXiu tbody tr").last().prevObject.length;
                    var tr = $("<tr><td><select id=WA" + length + " ></select></td><td><select id=WB" + length + " disabled='disabled' ></select></td>"
                             + "<td onclick='tdclick(this)' id=WC" + length + ">" + data[0][i].repairCharge +".00"+ "</td>"
                             + "<td onclick='tdclick(this)' id=WD" + length + " >" + data[0][i].discount + "</td>"
                             + "<td  id=WE" + length + ">" + data[0][i].amountPaid + ".00" + "</td><td><select id=WF" + length + " >" + data[0][i].maintainabilityID + "</select></td>"
                             + "<td onclick='tdclick(this)' id=WG" + length + ">" + data[0][i].remark + "</td><td hidden id=WH" + length + ">" + data[0][i].preRepairItemDetailID + "</td></tr>");
                    tbWeiXiu.append(tr);
                    DetailEvent(length,1);
                    $("#WA" + length).val(data[0][i].repairItemID);
                    $("#WB" + length).val(data[0][i].maintenanceID); 
                    $("#WF" + length).val(data[0][i].maintainabilityID);
                    var RepairItemID = $("#WA" + length).val();
                    $.ajaxSettings.async = false;
                    $.getJSON("${cxt}/servlet/CommonServlet?fun=selectRepairItemChange&RepairItemID=" + RepairItemID, function (data) {
                        data=data.list;
                        $("#WB" + length).val(data.maintenanceID);
                    })
                })
                setTimeout(function () {
                $.each(data[1], function (i) {
                    var tbChanPin = $("#tbChanPin");
                    var length = $("#tbChanPin tbody tr").last().prevObject.length;
                    var tr = $("<tr><td onclick='tdclick(this)' id=CA" + length + ">" + data[1][i].fittingsCode + "</td>"
                        + "<td onclick='tdclick(this)' id=CB" + length + ">" + data[1][i].fittingsName + "</td>"
                        + "<td onclick='tdclick(this)' id=CC" + length + ">" + data[1][i].vehicleType + "</td>"
                        + "<td onclick='tdclick(this)' id=CD" + length + ">" + data[1][i].quantity + ".00" + "</td><td onclick='tdclick(this)' id=CE" + length + ">"
                        + data[1][i].systemUnit + "</td><td onclick='tdclick(this)' id=CF" + length + ">" + data[1][i].unitPrice + ".00" + "</td>"
                        + "<td onclick='tdclick(this)' id=CG" + length + ">" + data[1][i].discount + "</td><td onclick='tdclick(this)'id=CH" + length + ">"
                        + data[1][i].amount + ".00" + "</td><td><select id=CI" + length + " >" + data[1][i].maintainabilityID + "</select></td>"
                        + "<td onclick='tdclick(this)' id=CJ" + length + ">" + data[1][i].remark + "</td><td hidden id=CK" + length + ">" + data[1][i].preProductDetailID + "</td></tr>");
                    tbChanPin.append(tr);
                    DetailEvent(length, 2);
                    selectcreate("CI" + length, ArrMaintainData);
                    $("#CI" + length).val(data[1][i].maintainabilityID);
                })
                $.each(data[2], function (i) {
                    var tbFeiYong = $("#tbFeiYong");
                    var length = $("#tbFeiYong tbody tr").last().prevObject.length;
                    var tr = $("<tr><td><select id=QA" + length + "></select></td><td onclick='tdclick(this)' id=QB" + length + ">" + data[2][i].amount + ".00" + "</td>"
                            + "<td onclick='tdclick(this)' id=QC" + length + ">" + data[2][i].discount + "</td>"
                            + "<td onclick='tdclick(this)' id=QD" + length + ">" + data[2][i].amountPaid + ".00" + "</td>"
                            + "<td onclick='tdclick(this)' id=QE" + length + ">" + data[2][i].remark + "</td>"
                            + "<td hidden id=QF" + length + ">" + data[2][i].preOtherCostDetailID + "</td></tr>");
                    tbFeiYong.append(tr);
                    selectcreate("QA" + length, ArrExpensesData);
                    DetailEvent(length,3);
                    $("#QA" + length).val(data[2][i].expensesID);
                })
                }, 500)
                layer.close(index);
            })
            $("#PredateID").val(record.predateID);
            $("#PredateNum").val(record.predateNum);
            $("#PredateNums").val(record.predateNum);
            $("#MaintenanceNum").val(record.maintenanceNum);
            $("#OpenDate").val(record.openDate);
            $("#MaintainData").val(record.maintainData);
            $("#CarNum").val(record.carNum); 
            $("#VehicleType").val(record.vehicleType);
            $("#CustomerNum").val(record.customerNum);
            $("#Owner").val(record.owner);
            $("#CarMasterPhone").val(record.carMasterPhone);
            $("#Contacts").val(record.contacts);
            $("#Telephone").val(record.telephone);
            $("#CarderID").val(record.carderID);
            $("#RepairID").val(record.repairID);
            $("#Remark").val(record.remark);
            $("#Amounts").val(record.amount+".00");
            $("#Receivable").val(record.receivable + ".00"  );
            $("#Describe").val(record.describe);
            $("#ToAudit").val(record.toAudit);
            $("#ToTransferOrder").val(record.toTransferOrder);
            ToFales();
            if (record.toAudit == true) {
                $("#ToAudits").attr("checked", true);
            } else {
                $("#ToAudits").removeAttr("checked");
            }
        }

        /********************单据模态框---结束*******************************/

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
        function btnFittingSelect()
        {
            var tbChanPin = $("#tbChanPin");
            var rowRecords = tbShangPin.getCheckedRowsRecords();
            $.each (rowRecords, function(i) {
                var length = $("#tbChanPin tbody tr").last().prevObject.length;
                var tr = $("<tr><td onclick='tdclick(this)' id=CA" + length + ">" + rowRecords[i].fittingsCode + "</td>"
                + "<td onclick='tdclick(this)' id=CB" + length + ">" + rowRecords[i].fittingsName + "</td>"
                + "<td onclick='tdclick(this)' id=CC" + length + ">" + rowRecords[i].vehicleType + "</td>"
                + "<td onclick='tdclick(this)' id=CD" + length + ">1.00</td><td onclick='tdclick(this)' id=CE" + length + ">"
                + rowRecords[i].systemUnit + "</td><td onclick='tdclick(this)' id=CF" + length + ">" + rowRecords[i].salesPrice +".00"+ "</td>"
                + "<td onclick='tdclick(this)' id=CG" + length + ">100</td><td id=CH" + length + ">"
                + rowRecords[i].salesPrice + ".00" + "</td><td><select id=CI" + length + "></select></td></td>"
                + "<td onclick='tdclick(this)' id=CJ" + length + "></td><td hidden id=CK" + length + "></td><td hidden id=CL" + length + ">" + rowRecords[i].specification + "</td></tr>");
                tbChanPin.append(tr);
                $("#ShangPinModal").modal("hide");
                DetailEvent(length,2);
            })
            ToFales();
        }
        //弹出新增配件模态框
        function ShowAddPeiJian() {
            $("#AddPeiJianModal").modal("show");
        }
        //配件基本资料添加保存
        function btnFittingsSava()
        {
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
       //主页面
        function Predate(PredateID,PredateNum,MaintenanceNum,OpenDate,MaintainData,CarNum,VehicleType,Owner,
            CarMasterPhone, Contacts, Telephone, CarderID, RepairID, Remark, Amount, Receivable, ToAudit, Describe, ToTransferOrder, CustomerNum) {
            this.PredateID = PredateID;//预约ID
            this.PredateNum = PredateNum;//预约编号
            this.MaintenanceNum = MaintenanceNum;//维修单号
            this.OpenDate = OpenDate;//开单日期
            this.MaintainData = MaintainData;//维修日期
            this.CarNum = CarNum;//车牌
            this.CustomerNum = CustomerNum;//客户编码
            this.VehicleType = VehicleType;//车型
            this.Owner = Owner;//车主
            this.CarMasterPhone = CarMasterPhone;//车主电话
            this.Contacts = Contacts;//联系人
            this.Telephone = Telephone;//联系人电话
            this.CarderID = CarderID;//接车人
            this.RepairID = RepairID;//修理类别
            this.Remark = Remark;//备注
            this.Amount = Amount;//总金额
            this.Receivable = Receivable;//应收金额
            this.ToAudit = ToAudit;//审核否
            this.Describe = Describe;//描述
            this.ToTransferOrder = ToTransferOrder;//转单否
        }
        //修理项目构造函数
        function PreRepairItem(PreRepairItemDetailID,RepairItemID, RepairCharge, Discount,
            MaintenanceID, AmountPaid, MaintainabilityID, Remark)
        {
            this.PreRepairItemDetailID = PreRepairItemDetailID;//修理明细ID
            this.RepairItemID = RepairItemID;//修理项目ID
            this.RepairCharge = RepairCharge;//修理费
            this.Discount = Discount;//折扣
            this.AmountPaid = AmountPaid;//实收金额
            this.MaintainabilityID = MaintainabilityID;//维修性质
            this.Remark = Remark;//备注
        }
        //产品材料构造函数
        function PreProduct(PreProductDetailID,FittingsCode, FittingsName,FittingsSpec, VehicleType, Quantity,
            SystemUnit, UnitPrice, Discount, Amount, MaintainabilityID, Remark) {
            this.PreProductDetailID = PreProductDetailID;//产品明细ID
            this.FittingsCode = FittingsCode;//配件编号
            this.FittingsName = FittingsName;//配件名称
            this.FittingsSpec = FittingsSpec;//配件规格
            this.VehicleType = VehicleType;//车型
            this.Quantity = Quantity;//数量
            this.SystemUnit = SystemUnit;//单位
            this.UnitPrice = UnitPrice;//单价
            this.Discount = Discount;//折扣
            this.Amount = Amount;//金额
            this.MaintainabilityID = MaintainabilityID;//维修性质ID
            this.Remark = Remark;//备注
        }
        //其他费用构造函数
        function PreOtherCost(PreOtherCostDetailID, ExpensesID, Amount, Discount, AmountPaid, Remark) {
                this.PreOtherCostDetailID = PreOtherCostDetailID;//费用明细ID
                this.ExpensesID = ExpensesID;//其他费用ID
                this.Amount = Amount;//金额
                this.Discount = Discount;//折扣
                this.AmountPaid = AmountPaid;//实收金额
                this.Remark = Remark;//备注
            }
    </script>
</body>
</html>
