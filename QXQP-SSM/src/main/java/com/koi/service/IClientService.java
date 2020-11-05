package com.koi.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.koi.po.SysMaintenanceCus;
import com.koi.vo.FittingsInfoVo;
import com.koi.vo.JsonReturn;
import com.koi.vo.OrderDetail;
import com.koi.vo.Pagination;
import com.koi.vo.PredateVo;

public interface IClientService {
	/**
	 * 根据手机号查询车主信息
	 * @param mobilePhone
	 * @return
	 */
	public JsonReturn selectMaintenanceCus(String mobilePhone,String password);
	/**
	 * 用户注册
	 * @param maintenanceCus
	 * @param smsValidCode
	 * @param request
	 * @return
	 */
	public JsonReturn register(String maintenanceCusStr, String smsValidCode, HttpServletRequest request);
	/**
	 * 获取验证码
	 * @param phone
	 * @return
	 */
	public JsonReturn sendSmsValid(String phone,HttpServletRequest request);
	/**
	 * 选择列表
	 * @return
	 */
	public Map<String,Object> choiceList();
	/**
	 * 通过车牌和审核状态来查询客户订单
	 * @param licenseCode
	 * @param toAudit
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public Pagination<PredateVo> getOrder(String phone,Integer toAudit,Integer pageSize,Integer currentPage);
	/**
	 * 查询订单明细
	 * @param predateID
	 * @return
	 */
	public OrderDetail selectOrderDetail(int predateID);
	/**
	 * 查询配件信息
	 * @param value
	 * @return
	 */
	public Pagination<FittingsInfoVo> getFittingsInfo(String value,Integer pageSize,
			Integer currentPage);
	/**
	 * 添加预约订单（保存订单）
	 * @param predate
	 * @param preRepairItem
	 * @param preProduct
	 * @param preOtherCost
	 * @return
	 */
	public JsonReturn savePredate(String predate,
			String preRepairItem,
			String preProduct,
			String preOtherCost);
}
