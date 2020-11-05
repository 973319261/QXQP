package com.koi.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.dao.ICollageDao;
import com.koi.po.PwCollage;
import com.koi.po.PwReception;
import com.koi.po.PwRestock;
import com.koi.po.PwUser;
import com.koi.po.SysCollageDetai;
import com.koi.service.ICollageService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.CollageDetaiVo;
import com.koi.vo.CollageVo;
import com.koi.vo.ProductVo;
import com.koi.vo.ReceptionVo;
import com.koi.vo.RestockDetailVo;
import com.koi.vo.RestockVo;
import com.koi.vo.ReturnJson;

@Transactional
@Service("collageService")
public class CollageServiceImpl implements ICollageService {
	@Autowired
	private ICollageDao collageDao;

	@Override
	public Object selectReception(String startDate, String endDate,
			String collageState, String carNum, Long curPage, Long pageSize) {
		Long totalRows = collageDao.selectReceptionCount(startDate, endDate,
				collageState, carNum);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ReceptionVo> list = collageDao.selectReception(startDate, endDate,
				collageState, carNum, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getJson(json, curPage, totalRows);
		return obj;
	}

	@Override
	public Object arrlistToCollage(int receptionID) {
		PwReception list = collageDao.arrlistToCollage(receptionID);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(list, jsonConfig);
		return jsonArr;
	}

	@Override
	public Object selectCollageData(int receptionID) {
		PwCollage list = collageDao.selectCollageData(receptionID);
		if (list != null) {
			JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd HH:mm:ss");
			Object obj = JSONArray.fromObject(list, jsonConfig);
			return obj;
		}
		return list;
	}

	@Override
	public Object selectDaoRuPeiJian(int receptionID, Long curPage,
			Long pageSize) {
		Long totalRows = collageDao.selectDaoRuPeiJianCount(receptionID);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<ProductVo> list = collageDao.selectDaoRuPeiJian(receptionID,
				startIndex, pageSize);
		Object obj = Bsgrid.getList(list, curPage, totalRows);
		return obj;
	}

	@Override
	public int updateListCollage(String pwCollage, String sysCollageDetai,
			PwUser user) {
		List<PwCollage> listCollage = Util.jsonToVo(pwCollage, PwCollage.class);
		List<SysCollageDetai> listCollageDetai = Util.jsonToVo(sysCollageDetai,
				SysCollageDetai.class);
		for (int i = 0; i < listCollage.size(); i++) {
			listCollage.get(i).setAuditor(user.getUserName());
			listCollage.get(i).setOperator(user.getUserName());
			listCollage.get(i)
					.setAuditDate(new Timestamp(new Date().getTime()));
			listCollage.get(i).setToAudit(true);
		}
		return collageDao.updateListCollage(listCollage, listCollageDetai);
	}

	@Override
	public Object selectCollage(Long curPage, Long pageSize) {
		Long totalRows = collageDao.selectCollageCount();
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<CollageVo> list = collageDao.selectCollage(startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getJson(json, curPage, totalRows);
		return obj;
	}

	@Override
	public List<SysCollageDetai> selectCollageDetai(int collageID) {
		// TODO Auto-generated method stub
		return collageDao.selectCollageDetai(collageID);
	}

	@Override
	public Object selectRestock(int receptionID) {
		RestockVo restockVo = collageDao.selectRestock(receptionID);
		restockVo.setStrAudit(restockVo.getToAudit() != true ? "未审核" : "已审核");
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray jsonArr = JSONArray.fromObject(restockVo, jsonConfig);
		return jsonArr;
	}

	@Override
	public Object selectTableCollageDetai(int collageID, Long curPage,
			Long pageSize) {
		Long totalRows = collageDao.selectTableCollageDetaiCount(collageID);
		Long startIndex = 1l;// 开始索引
		if (curPage > 1) {
			startIndex += pageSize;
		}
		pageSize *= curPage;// 页面大小
		List<CollageDetaiVo> list = collageDao.selectTableCollageDetai(
				collageID, startIndex, pageSize);
		Object obj = Bsgrid.getList(list, curPage, totalRows);
		return obj;
	}

	@Override
	public ReturnJson judgingQuantity(String restockDetail) {
		List<RestockDetailVo> listRestockDetai = Util.jsonToVo(restockDetail,
				RestockDetailVo.class);
		ReturnJson returnJson = collageDao.judgingQuantity(listRestockDetai);
		return returnJson;
	}

	@Override
	public int updateListRestock(String pwRestock, String restockDetail) {
		List<PwRestock> listRestock = Util.jsonToVo(pwRestock, PwRestock.class);
		List<RestockDetailVo> listRestockDetai = Util.jsonToVo(restockDetail,
				RestockDetailVo.class);
		return collageDao.updateListRestock(listRestock, listRestockDetai);
	}

	@Override
	public int selectCollageToID(int receptionID) {
		// TODO Auto-generated method stub
		return collageDao.selectCollageToID(receptionID);
	}
}
