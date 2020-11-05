package com.koi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koi.po.PwBalance;
import com.koi.po.PwThreePacks;
import com.koi.po.SysCollageDetai;
import com.koi.po.SysRecRepairItemDetail;
import com.koi.po.SysThreePacksDetail;
import com.koi.vo.ThreePacksDetailVo;
import com.koi.vo.ThreePacksVo;



public interface ClaimsetDao{
	/**
	 * 生成预约单号
	 * 
	 * @param d
	 * @return
	 */
	public int insuranceNum(String d);
	/**
	 * 维修单号查询
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ThreePacksDetailVo> selectDanHao(@Param("maintenanceNum")String maintenanceNum,@Param("carNum") String carNum,
			@Param("documentStateID")int documentStateID, @Param("balanceStateID")int balanceStateID, @Param("toAudit")String toAudit,
			@Param("startIndex")Long startIndex, @Param("pageSize")Long pageSize);
	/**
	 * 维修单号总数
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @return
	 */
	public Long selectDanHaoCount(@Param("maintenanceNum")String maintenanceNum, @Param("carNum")String carNum,
			@Param("documentStateID")int documentStateID, @Param("balanceStateID")int balanceStateID, @Param("toAudit")String toAudit);
	/**
	 * 三包单据查询
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ThreePacksVo> selectThreePacks(@Param("maintenanceNum")String maintenanceNum, @Param("carNum")String carNum,
			@Param("documentStateID")int documentStateID,@Param("balanceStateID") int balanceStateID,@Param("toAudit") String toAudit,
			@Param("startIndex")Long startIndex, @Param("pageSize")Long pageSize);
	/**
	 * 单据查询总数
	 * @param maintenanceNum
	 * @param carNum
	 * @param documentStateID
	 * @param balanceStateID
	 * @param toAudit
	 * @return
	 */
	public Long selectThreePacksCount(@Param("maintenanceNum")String maintenanceNum, @Param("carNum")String carNum,
			@Param("documentStateID")int documentStateID, @Param("balanceStateID")int balanceStateID, @Param("toAudit")String toAudit);
	/**
	 * 查询是否存在索赔单据
	 * @param threePacksDetailID
	 * @return
	 */
	public ThreePacksVo selectThreePacksData(Long threePacksDetailID);
	/**
	 * 
	 * @param receptionID
	 * @return
	 */
	public ThreePacksDetailVo selectThreePackss(int receptionID);
	/**
	 * 查询索赔明细信息
	 * @param receptionID
	 * @return
	 */
	public List<SysRecRepairItemDetail> selectReceptionDetail(int receptionID);
	public List<SysCollageDetai> selectCollageDetai(int receptionID);
;
	/**
	 * 三包索赔表操作
	 * @return
	 */
    public PwThreePacks selectThreePacksById(int threePacksID);
	public int insertThreePacks(PwThreePacks listThreePacks);
	public int updateThreePacks(PwThreePacks listThreePacks);
	public boolean delectThreePacks(int threePacksID);
}
