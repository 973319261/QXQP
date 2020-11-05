package com.koi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koi.po.PwCollage;
import com.koi.po.PwRestock;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysFittingsInfo;
import com.koi.po.SysInventory;
import com.koi.vo.CollageDetaiVo;
import com.koi.vo.CollageVo;
import com.koi.vo.FittingsInfoVo;
import com.koi.vo.ProductVo;
import com.koi.vo.ReceptionVo;
import com.koi.vo.RestockDetailVo;
import com.koi.vo.RestockVo;
import com.koi.vo.ReturnJson;


public interface CollageDao{
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
	public List<ReceptionVo> selectReception(@Param("startDate")String startDate, @Param("endDate")String endDate,
			@Param("collageState")String collageState, @Param("carNum")String carNum,@Param("startIndex")Long startIndex, 
			@Param("pageSize")Long pageSize);

	/**
	 * 查询客户接待单据信息总数
	 * 
	 * @param startDate
	 * @param endDate
	 * @param collageState
	 * @param carNum
	 * @return
	 */
	public Long selectReceptionCount(@Param("startDate")String startDate, @Param("endDate")String endDate,
			@Param("collageState")String collageState, @Param("carNum")String carNum);
	/**
	 * 查询领料导入配件信息
	 * 
	 * @param ReceptionID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ProductVo> selectDaoRuPeiJian(@Param("receptionID")int receptionID,@Param("startIndex") Long startIndex,
			@Param("pageSize")Long pageSize);
	/**
	 * 查询领料导入配件信息总数
	 * @param receptionID
	 * @return
	 */
	public Long selectDaoRuPeiJianCount(int receptionID);
	/**
	 * 查询领料
	 * @param pwCollage
	 * @return
	 */
	public int insertCollage(PwCollage pwCollage);
	/**
	 * 修改领料
	 * @param pwCollage
	 * @return
	 */
	public int updateCollage(PwCollage pwCollage);
	/**
	 * 查询库存信息
	 * @param fittingsCode
	 * @param warehouseID
	 * @return
	 */
	public SysInventory selectInventory(@Param("fittingsCode")String fittingsCode,@Param("warehouseID") int warehouseID);
	/**
	 * 新增库存
	 * @param sysInventory
	 * @return
	 */
	public int insertInventory(SysInventory sysInventory);
	/**
	 * 修改库存
	 * @param sysInventory
	 * @return
	 */
	public int updateInventory(SysInventory sysInventory);
	/**
	 * 查询配件信息
	 * @param fittingsCode
	 * @return
	 */
	public FittingsInfoVo selectFittingsInfoVo(String fittingsCode);

	/**
	 * 查询领料单据信息
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<CollageVo> selectCollage(@Param("startIndex")Long startIndex,@Param("pageSize") Long pageSize);
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
	public List<CollageDetaiVo> selectTableCollageDetai(@Param("collageID")int collageID,@Param("startIndex")Long startIndex,@Param("pageSize")Long pageSize);
	/**
	 * 查询可退料明细信息总数
	 * @param collageID
	 * @return
	 */
	public Long selectTableCollageDetaiCount(int collageID);
	
	/**
	 * 查询领料主信息
	 * @param receptionID
	 * @return
	 */
	public PwCollage selectCollageById(int receptionID);

}
