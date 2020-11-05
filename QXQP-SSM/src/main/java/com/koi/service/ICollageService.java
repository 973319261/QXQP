package com.koi.service;

import java.util.List;

import com.koi.po.PwCollage;
import com.koi.po.PwUser;
import com.koi.po.SysCollageDetai;
import com.koi.vo.CollageDetaiVo;
import com.koi.vo.CollageVo;
import com.koi.vo.ProductVo;
import com.koi.vo.ReceptionVo;
import com.koi.vo.ReturnJson;

public interface ICollageService {
	/**
	 * 查询客户接待单据信息
	 * 
	 * @param StartDate
	 * @param EndDate
	 * @param CollageState
	 * @param CarNum
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Object selectReception(String startDate, String endDate,
			String collageState, String carNum, Long curPage, Long pageSize);

	/**
	 * 查询领料主信息
	 * 
	 * @param receptionID
	 * @return
	 */
	public Object arrlistToCollage(int receptionID);

	/**
	 * 查询领料主信息
	 * 
	 * @param ReceptionID
	 * @return
	 */
	/**
	 */
	public Object selectCollageData(int receptionID);

	/**
	 * 查询领料导入配件信息
	 * 
	 * @param ReceptionID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Object selectDaoRuPeiJian(int receptionID, Long curPage,
			Long pageSize);


	/**
	 * 保存领料主页面
	 * 
	 * @param listCollage
	 * @param listCollageDetai
	 * @return
	 */
	public int updateListCollage(String pwCollage, String sysCollageDetai,
			PwUser user);

	/**
	 * 查询领料单据信息
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Object selectCollage(Long curPage, Long pageSize);

	/**
	 * 查询领料明细信息
	 * 
	 * @param collageID
	 * @return
	 */
	public List<SysCollageDetai> selectCollageDetai(int collageID);

	/**
	 * 查询退料主信息
	 * 
	 * @param receptionID
	 * @return
	 */
	public Object selectRestock(int receptionID);

	/**
	 * 查询可退料明细信息
	 * 
	 * @param collageID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Object selectTableCollageDetai(int collageID, Long curPage,
			Long pageSize);

	/**
	 * 判断领料数量
	 * 
	 * @param listRestockDetai
	 * @return
	 */
	public ReturnJson judgingQuantity(String restockDetail);

	/**
	 * 保存退料主页面
	 * 
	 * @param pwRestock
	 * @param restockDetail
	 * @return
	 */
	public int updateListRestock(String pwRestock, String restockDetail);

	/**
	 * ReceptionID查询CollageID 退料模态框
	 * 
	 * @param receptionID
	 * @return
	 */
	public int selectCollageToID(int receptionID);
}
