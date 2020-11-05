package com.koi.dao;

import java.util.List;

import com.koi.po.PwCollage;
import com.koi.po.PwReception;
import com.koi.po.PwRestock;
import com.koi.po.SysCollageDetai;
import com.koi.vo.CollageDetaiVo;
import com.koi.vo.CollageVo;
import com.koi.vo.ProductVo;
import com.koi.vo.ReceptionVo;
import com.koi.vo.RestockDetailVo;
import com.koi.vo.RestockVo;
import com.koi.vo.ReturnJson;


public interface ICollageDao{
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
	public List<ReceptionVo> selectReception(String startDate, String endDate,
			String collageState, String carNum, Long startIndex, Long pageSize);

	/**
	 * 查询客户接待单据信息总数
	 * 
	 * @param startDate
	 * @param endDate
	 * @param collageState
	 * @param carNum
	 * @return
	 */
	public Long selectReceptionCount(String startDate, String endDate,
			String collageState, String carNum);
	/**
	 * 查询领料主信息
	 * @param receptionID
	 * @return
	 */
	public PwReception arrlistToCollage(int receptionID);
	/**
	 * 查询领料主信息
	 * 
	 * @param ReceptionID
	 * @return
	 */
	/**
	 */
	public PwCollage selectCollageData(int receptionID);

	/**
	 * 查询领料导入配件信息
	 * 
	 * @param ReceptionID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ProductVo> selectDaoRuPeiJian(int receptionID, Long startIndex,
			Long pageSize);
	/**
	 * 查询领料导入配件信息总数
	 * @param receptionID
	 * @return
	 */
	public Long selectDaoRuPeiJianCount(int receptionID);
	/**
	 * 保存领料主页面
	 * @param listCollage
	 * @param listCollageDetai
	 * @return
	 */
	public int updateListCollage(List<PwCollage> listCollage, List<SysCollageDetai> listCollageDetai);
	/**
	 * 查询领料单据信息
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<CollageVo> selectCollage(Long startIndex, Long pageSize);
	/**
	 * 查询领料单据信息
	 * @return
	 */
	public Long selectCollageCount();
	/**
	 * 查询领料明细信息
	 * @param collageID
	 * @return
	 */
	public List<SysCollageDetai> selectCollageDetai(int collageID);
	/**
	 * 查询退料主信息
	 * @param receptionID
	 * @return
	 */
	public RestockVo selectRestock(int receptionID);
	/**
	 * 查询可退料明细信息
	 * @param collageID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<CollageDetaiVo> selectTableCollageDetai(int collageID,Long startIndex,Long pageSize);
	/**
	 * 查询可退料明细信息总数
	 * @param collageID
	 * @return
	 */
	public Long selectTableCollageDetaiCount(int collageID);
	/**
	 * 判断领料数量
	 * @param listRestockDetai
	 * @return
	 */
	public ReturnJson judgingQuantity(List<RestockDetailVo> listRestockDetai);
	
	/**
	 * 保存退料主页面
	 * @param listRestock
	 * @param listRestockDetai
	 * @return
	 */
	public int updateListRestock(List<PwRestock> listRestock, List<RestockDetailVo> listRestockDetai);
	/**
	 * ReceptionID查询CollageID 退料模态框
	 * @param receptionID
	 * @return
	 */
	public int selectCollageToID(int receptionID);
}
