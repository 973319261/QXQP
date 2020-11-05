package com.koi.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.koi.po.PwUser;
import com.koi.po.SysCollageDetai;
import com.koi.service.ICollageService;
import com.koi.util.Util;

public class CollageAction implements ServletRequestAware {
	@Autowired
	private ICollageService collageService;
	// 接收参数
	private Integer receptionID;
	private Integer collageID;
	private Long curPage;
	private Long pageSize;
	private String startDate;
	private String endDate;
	private String collageState;
	private String carNum;
	private String pwRestock;
	private String pwCollage;
	private String restockDetail;
	private String sysCollageDetai;
	// 返回参数
	private Object result;// 返回json值
	private HttpServletRequest request;

	// 查询领料主信息
	public String arrlistToCollage() {
		result = collageService.arrlistToCollage(receptionID);
		return "result";
	}

	// 保存退料主页面
	public String updateListRestock() {
		result = collageService.updateListRestock(pwRestock, restockDetail);
		return "result";
	}

	// 判断领料数量
	public String judgingQuantity() {
		result = collageService.judgingQuantity(restockDetail);
		return "result";
	}

	// 查询退料主信息
	public String selectRestock() {
		result = collageService.selectRestock(receptionID);
		return "result";
	}

	// 查询可退料明细信息
	public String selectTableCollageDetai() {
		result = collageService.selectTableCollageDetai(collageID, curPage,
				pageSize);
		return "result";
	}

	// 清除session中的退料信息
	public void cleanArrlist() {
		request.getSession().removeAttribute("restock");
		request.getSession().removeAttribute("receptionID");
	}

	// 从session中获取退料信息
	public String arrlistToRestock() {
		result = request.getSession().getAttribute("restock");
		return "result";
	}

	// 保存退料信息到session
	public void restock() {
		List<SysCollageDetai> list = Util.jsonToVo(sysCollageDetai,
				SysCollageDetai.class);
		request.getSession().setAttribute("restock", list);
	}

	// ReceptionID查询CollageID 退料模态框
	public String selectCollageToID() {
		request.getSession().setAttribute("receptionID", receptionID);// 保存receptionID到session中
		result = collageService.selectCollageToID(receptionID);
		return "result";
	}

	// 领料
	public void collage() {
		request.getSession().setAttribute("receptionID", receptionID);
	}

	// 查询领料明细信息
	public String selectCollageDetai() {
		result = collageService.selectCollageDetai(collageID);
		return "result";
	}

	// 查询领料单据信息
	public String selectCollage() {
		result = collageService.selectCollage(curPage, pageSize);
		return "result";
	}

	// 保存领料主页面
	public String updateListCollage() {
		PwUser user = (PwUser) request.getSession().getAttribute("user");
		result = collageService.updateListCollage(pwCollage, sysCollageDetai,
				user);
		return "result";
	}

	// 查询领料导入配件信息
	public String selectDaoRuPeiJian() {
		result = collageService.selectDaoRuPeiJian(receptionID, curPage,
				pageSize);
		return "result";
	}

	// 查询领料主信息
	public String selectCollageData() {
		result = collageService.selectCollageData(receptionID);
		return "result";
	}

	// 查询客户接待单据信息
	public String selectReception() {
		result = collageService.selectReception(startDate, endDate,
				collageState, carNum, curPage, pageSize);
		return "result";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Object getResult() {
		return result;
	}

	public void setReceptionID(Integer receptionID) {
		this.receptionID = receptionID;
	}

	public void setCollageID(Integer collageID) {
		this.collageID = collageID;
	}

	public void setCurPage(Long curPage) {
		this.curPage = curPage;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setCollageState(String collageState) {
		this.collageState = collageState;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public void setPwRestock(String pwRestock) {
		this.pwRestock = pwRestock;
	}

	public void setPwCollage(String pwCollage) {
		this.pwCollage = pwCollage;
	}

	public void setRestockDetail(String restockDetail) {
		this.restockDetail = restockDetail;
	}

	public void setSysCollageDetai(String sysCollageDetai) {
		this.sysCollageDetai = sysCollageDetai;
	}

}
