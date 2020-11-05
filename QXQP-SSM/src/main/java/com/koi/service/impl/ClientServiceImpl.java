package com.koi.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koi.mapper.AppointmentDao;
import com.koi.mapper.ClientDao;
import com.koi.mapper.CommonDao;
import com.koi.po.PwPredate;
import com.koi.po.SysFittingsInfo;
import com.koi.po.SysMaintenanceCus;
import com.koi.po.SysPreOtherCostDetail;
import com.koi.po.SysPreProductDetail;
import com.koi.po.SysPreRepairItemDetail;
import com.koi.service.IAppointmentService;
import com.koi.service.IClientService;
import com.koi.service.ICommonService;
import com.koi.util.Util;
import com.koi.vo.AppendOptionVo;
import com.koi.vo.FittingsInfoVo;
import com.koi.vo.JsonReturn;
import com.koi.vo.MaintenanceCusVo;
import com.koi.vo.OrderDetail;
import com.koi.vo.Pagination;
import com.koi.vo.PreOtherCostDetailVo;
import com.koi.vo.PreProductDetailVo;
import com.koi.vo.PreRepairItemDetailVo;
import com.koi.vo.PredateVo;

import net.sf.json.JSONObject;
@Transactional
@Service("clientService")
public class ClientServiceImpl implements IClientService {
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private AppointmentDao appointmentDao;
	@Autowired
	private ICommonService commonService;
	@Autowired
	private IAppointmentService appointmentService;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	private final String SMS_VALID_PHONE = "smsValidPhone";
	private final String SMS_VALID = "smsValid";
	@Override
	public JsonReturn selectMaintenanceCus(String mobilePhone,String password) {
		JsonReturn jsonReturn=new JsonReturn();
		MaintenanceCusVo maintenanceCus = clientDao.selectMaintenanceCus(mobilePhone);
		if(maintenanceCus!=null) {
			String mobilePhoneStr=maintenanceCus.getMobilePhone().trim();
			String passwordStr = maintenanceCus.getIdNumber().toString().substring(12, 18);
			if(mobilePhone.equals(mobilePhoneStr) && password.equals(passwordStr)){
				jsonReturn.setCode(200);
				jsonReturn.setText("登录成功");
				jsonReturn.setData(maintenanceCus);
			}else {
				jsonReturn.setCode(501);
				jsonReturn.setText("账号或者密码错误");
			}
		}else {
			jsonReturn.setCode(500);
			jsonReturn.setText("该用户不存在");
		}
		return jsonReturn;
	}
	@Override
	public JsonReturn register(String maintenanceCusStr, String smsValidCode, HttpServletRequest request) {
		JsonReturn jsonReturn = new JsonReturn();
	     JSONObject jsonObject=JSONObject.fromObject(maintenanceCusStr);
	     SysMaintenanceCus maintenanceCus=(SysMaintenanceCus)JSONObject.toBean(jsonObject, SysMaintenanceCus.class);
		if (maintenanceCus.getMobilePhone()!=null && maintenanceCus.getIdNumber()!=null) {
			// 获取session中的验证码和手机号
			HttpSession session = request.getSession();
			String sessionValidCode = (String) session.getAttribute(SMS_VALID);
			String sessionValidPhone = (String) session.getAttribute(SMS_VALID_PHONE);
			// 验证手机号和短信验证码
			if (smsValidCode.equals(sessionValidCode) && maintenanceCus.getMobilePhone().trim().equals(sessionValidPhone)) {
				//设置默认值
				maintenanceCus.setVehicleType("");
				maintenanceCus.setInsuranceSpeId(1);
				maintenanceCus.setInsuranceComId(1);
				maintenanceCus.setDepartmentId(2);
				maintenanceCus.setCarderId(1);
				maintenanceCus.setCustomerTypeId(1);
				maintenanceCus.setCustomerLevelId(1);
				maintenanceCus.setRegionId(1);
				maintenanceCus.setCustomerSouId(1);
				maintenanceCus.setOwner("");
				System.out.println(Util.getBirthday(maintenanceCus.getIdNumber().trim()));
				maintenanceCus.setBirthday(Util.getBirthday(maintenanceCus.getIdNumber().trim()));
				maintenanceCus.setLicenseCode("");
				maintenanceCus.setEngineNum("");
				maintenanceCus.setFrameNum("");
				maintenanceCus.setRepairMan("");
				maintenanceCus.setRepairTele("");
				maintenanceCus.setInitialStartDate("");
				maintenanceCus.setInitialEndDate("");
				maintenanceCus.setAddress("");
				maintenanceCus.setCustomerNum(commonService.customerNum());
				maintenanceCus.setDriveDate("");
				maintenanceCus.setDrivingDate("");
				maintenanceCus.setInputPerson("个人注册");
				MaintenanceCusVo maintenanceCusVo;
				maintenanceCusVo = clientDao.selectMaintenanceCus(maintenanceCus.getMobilePhone());
				if (maintenanceCusVo != null) {//直接登录
					// 将memberVo放入session
					session.setAttribute("maintenanceCus", maintenanceCusVo);
					jsonReturn.setCode(200);
					jsonReturn.setText("登录成功");
					jsonReturn.setData(maintenanceCusVo);
				} else {// 添加一个新用户（然后登录）
					int intR = this.commonDao.insertMaintenanceCus(maintenanceCus);
					if (intR > 0) {
						maintenanceCusVo = clientDao.selectMaintenanceCus(maintenanceCus.getMobilePhone());
						jsonReturn.setCode(200);
						jsonReturn.setText("注册成功");
						jsonReturn.setData(maintenanceCus);
					} else {
						jsonReturn.setCode(501);
						jsonReturn.setText("添加新用户失败");
					}
				}
				
			}else {
				jsonReturn.setCode(501);
				jsonReturn.setText("验证码不正确");
			}
		}else {
			jsonReturn.setCode(500);
			jsonReturn.setText("参数异常");
		}
		return jsonReturn;
	}
	@Override
	public JsonReturn sendSmsValid(String phone,HttpServletRequest request) {
		JsonReturn jsonReturn = new JsonReturn();
		if (phone!=null && phone!="" && phone.length() == 11) {
			// 生成6位随机数
			int numcode = (int) ((Math.random() * 9 + 1) * 100000);
			System.out.println("验证码为=   " + numcode);
			HttpSession session = request.getSession();
			session.setAttribute(SMS_VALID, String.valueOf(numcode));
			session.setAttribute(SMS_VALID_PHONE, phone);
			jsonReturn.setCode(200);
			jsonReturn.setText("发送成功，请注意查收");
		}else {
			jsonReturn.setCode(500);
			jsonReturn.setText("手机号不正确");
		}
		return jsonReturn;
	}
	@Override
	public Map<String, Object> choiceList() {
		Map<String, Object> map=new HashMap<String, Object>();
		//SYS_InsuranceCom,SYS_InsuranceSpe,SYS_Department,SYS_Carder,SYS_CustomerType,SYS_CustomerLevel,SYS_Region,SYS_CustomerSou
		List<AppendOptionVo> insuranceCom= commonDao.selectAppendOption(Util.sqlField("SYS_InsuranceCom"));//保险公司
		List<AppendOptionVo> insuranceSpe= commonDao.selectAppendOption(Util.sqlField("SYS_InsuranceSpe"));//保险种类
		List<AppendOptionVo> department= commonDao.selectAppendOption(Util.sqlField("SYS_Department"));//所属部门
		List<AppendOptionVo> carder= commonDao.selectAppendOption(Util.sqlField("SYS_Carder"));//所属员工
		List<AppendOptionVo> customerType= commonDao.selectAppendOption(Util.sqlField("SYS_CustomerType"));//客户类型
		List<AppendOptionVo> customerLevel= commonDao.selectAppendOption(Util.sqlField("SYS_CustomerLevel"));//客户等级
		List<AppendOptionVo> region= commonDao.selectAppendOption(Util.sqlField("SYS_Region"));//所属区域
		List<AppendOptionVo> customerSou= commonDao.selectAppendOption(Util.sqlField("SYS_CustomerSou"));//客户来源
		List<AppendOptionVo> repair= commonDao.selectAppendOption(Util.sqlField("SYS_Repair"));//接车人
		List<AppendOptionVo> repairItem= commonDao.selectAppendOption(Util.sqlField("SYS_RepairItem"));//维修项目
		List<AppendOptionVo> maintenance= commonDao.selectAppendOption(Util.sqlField("SYS_Maintenance"));//维修工艺
		List<AppendOptionVo> maintainability= commonDao.selectAppendOption(Util.sqlField("SYS_Maintainability"));//维修性质
		List<AppendOptionVo> expenses= commonDao.selectAppendOption(Util.sqlField("SYS_Expenses"));//其他费用
		map.put("insuranceCom", insuranceCom);
		map.put("insuranceSpe", insuranceSpe);
		map.put("department", department);
		map.put("carder", carder);
		map.put("customerType", customerType);
		map.put("customerLevel", customerLevel);
		map.put("region", region);
		map.put("customerSou", customerSou);
		map.put("repair", repair);
		map.put("repairItem", repairItem);
		map.put("maintenance", maintenance);
		map.put("maintainability", maintainability);
		map.put("expenses", expenses);
		return map;
	}
	@Override
	public Pagination<PredateVo> getOrder(String phone, Integer toAudit, Integer pageSize,
			Integer currentPage) {
		// 获取该类型的订单总数
		Integer totalRows =  clientDao.selectOrderCount(phone, toAudit);
		Integer startIndex = 1;// 开始索引
		if (currentPage > 1) {
			startIndex += pageSize;
		}
		int endIndex =pageSize* currentPage;// 页面大小
		// 分页查询订单数据
		List<PredateVo>  list= clientDao.selectOrder(phone, toAudit,startIndex, endIndex);
		// 组装分页返回数据
		Pagination<PredateVo> pagination = new Pagination<PredateVo>();
		pagination.setCurrentPage(currentPage);// 当前页数
		pagination.setPageSize(pageSize);// 分页大小
		pagination.setTotalRows(totalRows);// 数据总条数
		pagination.setData(list);// 本页数据
		pagination.setSuccess(true);// 成功
		return pagination;
	}
	@Override
	public OrderDetail selectOrderDetail(int predateID) {
		// TODO Auto-generated method stub
		OrderDetail list=new OrderDetail();
		PredateVo predateVo=clientDao.selectPredate(predateID);
		List<PreRepairItemDetailVo> listPreRepairItemDetail = clientDao.selectPreRepairItemDetail(predateID);
		List<PreProductDetailVo> listPreProductDetail = clientDao.selectPreProductDetail(predateID);
		List<PreOtherCostDetailVo> listPreOtherCostDetail = clientDao.selectPreOtherCostDetail(predateID);
		list.setPredateVo(predateVo);
		list.setPreRepairItemDetailVoList(listPreRepairItemDetail);
		list.setPreProductDetailVoList(listPreProductDetail);
		list.setPreOtherCostDetailVoList(listPreOtherCostDetail);
		return list;
	}
	@Override
	public Pagination<FittingsInfoVo> getFittingsInfo(String value,Integer pageSize,
			Integer currentPage) {
		// 获取该类型的订单总数
		Integer totalRows =  clientDao.selectFittingsInfoCount(value);
		Integer startIndex = 1;// 开始索引
		if (currentPage > 1) {
			startIndex += pageSize;
		}
		int endIndex =pageSize* currentPage;// 页面大小
		// 分页查询订单数据
		List<FittingsInfoVo>  list= clientDao.selectFittingsInfo(value,startIndex, endIndex);
		// 组装分页返回数据
		Pagination<FittingsInfoVo> pagination = new Pagination<FittingsInfoVo>();
		pagination.setCurrentPage(currentPage);// 当前页数
		pagination.setPageSize(pageSize);// 分页大小
		pagination.setTotalRows(totalRows);// 数据总条数
		pagination.setData(list);// 本页数据
		pagination.setSuccess(true);// 成功
		return pagination;
	}
	@Override
	public JsonReturn savePredate(String predate, String preRepairItem, String preProduct, String preOtherCost) {
		PwPredate predateList = Util.jsonToVo(predate, PwPredate.class).get(0);
		List<SysPreRepairItemDetail> listPreRepairItem = Util.jsonToVo(preRepairItem, SysPreRepairItemDetail.class);
		List<SysPreProductDetail> listPreProduct = Util.jsonToVo(preProduct, SysPreProductDetail.class);
		List<SysPreOtherCostDetail> listPreOtherCost = Util.jsonToVo(preOtherCost, SysPreOtherCostDetail.class);
		JsonReturn jsonReturn=new JsonReturn();
		int id = 0;
		if (predateList.getPredateId() == null) {
			//Android端 --开始
			if(predateList.getPredateNum()==null) {//维修单号
				predateList.setPredateNum(appointmentService.predateNum());
			}
			if(predateList.getOpenDate()==null) {//开单日期
				Timestamp d = new Timestamp(System.currentTimeMillis()); 
				predateList.setOpenDate(d);
			}
			if(predateList.getOpenDate()==null) {//开单日期
				Timestamp d = new Timestamp(System.currentTimeMillis()); 
				predateList.setOpenDate(d);
			}
			if(predateList.getToAudit()==null) {
				predateList.setToAudit(false);
			}
			//Android端 --结束
			appointmentDao.insertPredate(predateList);// 新增
		} else {
			appointmentDao.updatePredate(predateList);// 新增// 修改
		}
		id = Integer.valueOf(predateList.getPredateId().toString());
		if (id > 0) {// 主表保存成功
			List<Integer> oldID = new ArrayList<Integer>();// 原来ID
			List<Integer> newID = new ArrayList<Integer>();// 传过来的ID
			if (listPreRepairItem.size() != 0) {// 修理项目明细表有数据
				// 维修明细表
				List<SysPreRepairItemDetail> list = appointmentDao.selectPreRepairItemDetail(id);// 修理项目明细表
				for (SysPreRepairItemDetail item : list) {
					oldID.add(item.getPreRepairItemDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreRepairItem.size(); i++) {// 遍历修理项目明细表
					listPreRepairItem.get(i).setPredateId(id);
					if (listPreRepairItem.get(i).getPreRepairItemDetailId() == null) {
						appointmentDao.insertPreRepairItem(listPreRepairItem.get(i));// 新增
					} else {
						newID.add(listPreRepairItem.get(i)
								.getPreRepairItemDetailId());
						appointmentDao.updatePreRepairItem(listPreRepairItem.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					appointmentDao.deletePreRepairItem(item);
				}
			} else {
				appointmentDao.deletePreRepairItem(id);
			}

			if (listPreProduct.size() != 0) {// /配件明细表有数据
				oldID.clear();
				newID.clear();
				List<SysPreProductDetail> list = (List<SysPreProductDetail>) appointmentDao.selectPreProductDetail(id);// 配件明细表
				for (SysPreProductDetail item : list) {
					oldID.add(item.getPreProductDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreProduct.size(); i++) {// 遍历修理项目明细表
					listPreProduct.get(i).setPredateId(id);
					if (listPreProduct.get(i).getPreProductDetailId() == null) {
						appointmentDao.insertPreProduct(listPreProduct.get(i));// 新增
					} else {
						newID.add(listPreProduct.get(i).getPreProductDetailId());
						appointmentDao.updatePreProduct(listPreProduct.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					appointmentDao.deletePreProduct(item);
				}
			} else {
				appointmentDao.deletePreProduct(id);
			}

			if (listPreOtherCost.size() != 0) {// 费用明细表有数据
				// 维修明细表
				oldID.clear();
				newID.clear();
				List<SysPreOtherCostDetail> list = appointmentDao.selectPreOtherCostDetail(id);// 费用明细表
				for (SysPreOtherCostDetail item : list) {
					oldID.add(item.getPreOtherCostDetailId());// 把原来的明细id放进集合
				}
				for (int i = 0; i < listPreOtherCost.size(); i++) {// 遍历修理项目明细表
					listPreOtherCost.get(i).setPredateId(id);
					if (listPreOtherCost.get(i).getPreOtherCostDetailId() == null) {
						appointmentDao.insertPreOtherCost(listPreOtherCost.get(i));// 新增
					} else {
						newID.add(listPreOtherCost.get(i)
								.getPreOtherCostDetailId());
						appointmentDao.updatePreOtherCost(listPreOtherCost.get(i));// 修改
					}
				}
				oldID.removeAll(newID);// 从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
				for (Integer item : oldID) {
					appointmentDao.deletePreOtherCost(item);
				}
			} else {
				appointmentDao.deletePreOtherCost(id);
			}
			JsonObject jsonObject=new JsonObject();
			jsonObject.add("predateList", gson.toJsonTree(predateList));
			jsonObject.add("listPreRepairItem", gson.toJsonTree(listPreRepairItem));
			jsonObject.add("listPreProduct", gson.toJsonTree(listPreProduct));
			jsonObject.add("listPreOtherCost", gson.toJsonTree(listPreOtherCost));
			jsonReturn.setCode(200);
			jsonReturn.setText("预订成功");
			jsonReturn.setData(jsonObject);//将订单详细 以及明细信息和支付方式返回到App
			
		}else {
			jsonReturn.setCode(500);
			jsonReturn.setText("预订失败，请稍后再试");
		}
		
		return jsonReturn;
	}
}
