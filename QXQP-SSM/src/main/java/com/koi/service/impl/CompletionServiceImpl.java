package com.koi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koi.mapper.CommonDao;
import com.koi.mapper.CompletionDao;
import com.koi.po.PwReception;
import com.koi.service.ICompletionService;
import com.koi.util.Bsgrid;
import com.koi.util.Json;
import com.koi.util.Util;
import com.koi.vo.ReceptionVo;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
@Transactional
@Service("completionService")
public class CompletionServiceImpl implements ICompletionService {
	@Autowired
	private CompletionDao completionDao;
	@Autowired
	private CommonDao commonDao;
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
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
		int i = 0;
		for (ReceptionVo item : listReception) {
			PwReception list = commonDao.selectReceptionById(item.getReceptionId());
			list.setToCompletion(true);
			list.setCompletionDate(format.format(new Date()));
			list.setDocumentStateId(2);
			i = commonDao.updateReception(list);
		}
		return i;
	}

	@Override
	public int updateCompletionFalse(String reception) {
		List<ReceptionVo> listReception=Util.jsonToVo(reception, ReceptionVo.class);
		int i = 0;
		for (ReceptionVo item : listReception) {
			PwReception list = commonDao.selectReceptionById(item.getReceptionId());
			list.setToCompletion(false);
			list.setCompletionDate(null);
			list.setDocumentStateId(1);
			i = commonDao.updateReception(list);
		}
		return i;
	}
}
