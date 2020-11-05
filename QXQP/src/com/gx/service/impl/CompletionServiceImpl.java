package com.gx.service.impl;

import java.util.List;

import com.gx.dao.ICompletionDao;
import com.gx.dao.impl.CompletionDaoImpl;
import com.gx.service.ICompletionService;
import com.gx.vo.ReceptionVo;

public class CompletionServiceImpl implements ICompletionService {
	private ICompletionDao completionDao=new CompletionDaoImpl();

	@Override
	public List<ReceptionVo> selectReception(String startDate, String endDate,
			String toCompletion, int startIndex, int pageSize) {
		return completionDao.selectReception(startDate, endDate, toCompletion, startIndex, pageSize);
	}

	@Override
	public int selectReceptionCount(String startDate, String endDate,
			String toCompletion) {
		return completionDao.selectReceptionCount(startDate, endDate, toCompletion);
	}

	@Override
	public int updateCompletionTrue(List<ReceptionVo> listReception) {
		return completionDao.updateCompletionTrue(listReception);
	}

	@Override
	public int updateCompletionFalse(List<ReceptionVo> listReception) {
		return completionDao.updateCompletionFalse(listReception);
	}
}
