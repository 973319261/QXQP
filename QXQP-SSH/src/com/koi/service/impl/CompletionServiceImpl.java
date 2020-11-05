package com.koi.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.dao.ICompletionDao;
import com.koi.service.ICompletionService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.ReceptionVo;
@Transactional
@Service("completionService")
public class CompletionServiceImpl implements ICompletionService {
	@Autowired
	private ICompletionDao completionDao;

	@Override
	public Object selectReception(String startDate, String endDate,
			String toCompletion, Long curPage, Long pageSize) {
		Long totalRows = completionDao.selectReceptionCount(startDate, endDate, toCompletion);
		Long startIndex =  1l ;//开始索引
		if(curPage>1){
			startIndex+=pageSize;
		}
		pageSize*=curPage;// 页面大小
		List<ReceptionVo> list = completionDao.selectReception(startDate, endDate, toCompletion, startIndex, pageSize);
		JsonConfig jsonConfig = Json.getJsontoDate("yyyy-MM-dd");
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		Object obj = Bsgrid.getJson(json, curPage, totalRows);
		return obj;
	}

	@Override
	public int updateCompletionTrue(String reception) {
		List<ReceptionVo> listReception=Util.jsonToVo(reception, ReceptionVo.class);
		return completionDao.updateCompletionTrue(listReception);
	}

	@Override
	public int updateCompletionFalse(String reception) {
		List<ReceptionVo> listReception=Util.jsonToVo(reception, ReceptionVo.class);
		return completionDao.updateCompletionFalse(listReception);
	}
}
