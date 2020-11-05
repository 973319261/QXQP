package com.gx.service.impl;

import java.util.List;

import com.gx.dao.ICollageDao;
import com.gx.dao.impl.CollageDaoImpl;
import com.gx.pojo.PW_Collage;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.PW_Restock;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.service.ICollageService;
import com.gx.vo.CollageDetaiVo;
import com.gx.vo.CollageVo;
import com.gx.vo.ProductVo;
import com.gx.vo.ReceptionVo;
import com.gx.vo.RestockDetail;
import com.gx.vo.ReturnJson;

public class CollageServiceImpl implements ICollageService {
	private ICollageDao collageDao = new CollageDaoImpl();

	@Override
	public List<ReceptionVo> selectReception(String startDate, String endDate,
			String collageState, String carNum, int startIndex, int pageSize) {
		return collageDao.selectReception(startDate, endDate, collageState,
				carNum, startIndex, pageSize);
	}

	@Override
	public int selectReceptionCount(String startDate, String endDate,
			String collageState, String carNum) {
		return collageDao.selectReceptionCount(startDate, endDate, collageState, carNum);
	}

	@Override
	public CollageVo selectCollageData(int receptionID) {
		return collageDao.selectCollageData(receptionID);
	}

	@Override
	public List<ProductVo> selectDaoRuPeiJian(int receptionID, int startIndex,
			int pageSize) {
		return collageDao.selectDaoRuPeiJian(receptionID, startIndex, pageSize);
	}

	@Override
	public int selectDaoRuPeiJianCount(int receptionID) {
		return collageDao.selectDaoRuPeiJianCount(receptionID);
	}
	
	@Override
	public int updateListCollage(List<PW_Collage> listCollage,
			List<SYS_CollageDetai> listCollageDetai) {
		return collageDao.updateListCollage(listCollage, listCollageDetai);
	}

	@Override
	public List<CollageVo> selectCollage(int startIndex, int pageSize) {
		return collageDao.selectCollage(startIndex, pageSize);
	}

	@Override
	public int selectCollageCount() {
		return collageDao.selectCollageCount();
	}
	
	@Override
	public List<SYS_CollageDetai> selectCollageDetai(int collageID) {
		return collageDao.selectCollageDetai(collageID);
	}

	@Override
	public CollageVo selectRestock(int receptionID) {
		return collageDao.selectRestock(receptionID);
	}

	@Override
	public List<CollageDetaiVo> selectTableCollageDetai(int collageID,
			int startIndex, int pageSize) {
		return collageDao.selectTableCollageDetai(collageID, startIndex, pageSize);
	}
	
	@Override
	public int selectTableCollageDetaiCount(int collageID) {
		return collageDao.selectTableCollageDetaiCount(collageID);
	}
	
	@Override
	public ReturnJson judgingQuantity(List<RestockDetail> listRestockDetai) {
		return collageDao.judgingQuantity(listRestockDetai);
	}

	@Override
	public int updateListRestock(List<PW_Restock> listRestock,
			List<RestockDetail> listRestockDetai) {
		return collageDao.updateListRestock(listRestock, listRestockDetai);
	}

	@Override
	public int selectCollageToID(int receptionID) {
		return collageDao.selectCollageToID(receptionID);
	}

	@Override
	public PW_Reception arrlistToCollage(int receptionID) {
		return collageDao.arrlistToCollage(receptionID);
	}

	
}
