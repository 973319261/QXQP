package com.koi.web;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koi.po.SysMaintenanceCus;
import com.koi.service.IAppointmentService;
import com.koi.service.IClientService;
import com.koi.util.AppSeting;
import com.koi.vo.FittingsInfoVo;
import com.koi.vo.JsonReturn;
import com.koi.vo.MaintenanceCusVo;
import com.koi.vo.OrderDetail;
import com.koi.vo.Pagination;
import com.koi.vo.PredateVo;
/**
 * app客户端
 * @author ZLY
 *
 */
@Controller
@RequestMapping("/clientController")
public class ClientController {
	@Autowired
	private IClientService clientService;
	@Autowired
	private IAppointmentService appointmentService;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	/**
	 * 登录
	 * @param phone
	 * @param password
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public Object login(String phone,String password,HttpServletRequest request) {
		JsonReturn jsonReturn = clientService.selectMaintenanceCus(phone, password);
		HttpSession session=request.getSession();
		session.setAttribute("maintenanceCus", jsonReturn.getData());
		return gson.toJson(jsonReturn);
	}
	/**
	 * 用户注册
	 * @param maintenanceCus
	 * @param smsValidCode
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
	public Object register(String maintenanceCusStr, String smsValidCode, HttpServletRequest request) {
		JsonReturn jsonReturn = clientService.register(maintenanceCusStr, smsValidCode, request);
		return gson.toJson(jsonReturn);
	}
	/**
	 * 获取验证码
	 * @param phone
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendSmsValid", produces = "application/json;charset=UTF-8")
	public Object sendSmsValid(String phone, HttpServletRequest request) {
		JsonReturn jsonReturn = clientService.sendSmsValid(phone, request);
		return gson.toJson(jsonReturn);
	}
	/**
	 * 查询单选列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/choiceList", produces = "application/json;charset=UTF-8")
	public Object choiceList() {
		Map<String,Object> map=clientService.choiceList();
		return gson.toJson(map);
	}
	/**
	 * 通过账号号码和审核状态来查询客户订单
	 * @param licenseCode
	 * @param toAudit
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrder", produces = "application/json;charset=UTF-8")
	public Object getOrder(String phone,Integer toAudit,Integer pageSize,Integer currentPage) {
		Pagination<PredateVo> pagination=clientService.getOrder(phone, toAudit, pageSize, currentPage);
		return gson.toJson(pagination);
	}
	/**
	 * 查询订单明细信息
	 * @param predateID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderDetail", produces = "application/json;charset=UTF-8")
	public Object getOrderDetail(Integer predateId) {
		OrderDetail list=clientService.selectOrderDetail(predateId);
		return gson.toJson(list);
	}
	/**
	 * 获取配件信息
	 * @param value
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getFittingsInfo", produces = "application/json;charset=UTF-8")
	public Object getFittingsInfo(String value,Integer pageSize,Integer currentPage) {
		Pagination<FittingsInfoVo> pagination=clientService.getFittingsInfo(value, pageSize, currentPage);
		return gson.toJson(pagination);
	}
	/**
	 * 获取配件图片
	 * @param pictureName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getFittingsPicture")
	public ResponseEntity<byte[]> getFittingsPicture(String pictureName) throws IOException {
		if (pictureName!="") {
			// 文件
			File file = new File(AppSeting.UPLOAD__Fittings_PIC_DIR, pictureName);
			if (file.exists()) {// 判断文件是否存在
				// 设置Header
				HttpHeaders headers = new HttpHeaders();
				// 设置为图片类型
				headers.setContentType(MediaType.IMAGE_JPEG);
				headers.setContentDispositionFormData("attachment", pictureName);

				// 返回文件相关数据
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			}
		}
		return null;
	}
	// 保存主页面信息
	@ResponseBody
	@RequestMapping(value = "/savePredate", produces = "application/json;charset=UTF-8")
	public Object savePredate(String pwPredates, String sysPreRepairItemDetail, String sysPreProductDetail,
			String sysPreOtherCostDetail) {
		 JsonReturn jsonReturn= clientService.savePredate(pwPredates,
		 sysPreRepairItemDetail, sysPreProductDetail, sysPreOtherCostDetail);
		return gson.toJson(jsonReturn);
	}
	// 删除订单信息
	@ResponseBody
	@RequestMapping(value = "/deletePredate", produces = "application/json;charset=UTF-8")
	public Object deletePredate(Integer predateID) {
		Boolean flag = appointmentService.deleteListPredate(predateID);
		return gson.toJson(flag);
	}
}
