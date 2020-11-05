package com.gx.dao;

import java.util.List;

import com.gx.pojo.PW_Collage;
import com.gx.pojo.PW_Reception;
import com.gx.pojo.PW_Restock;
import com.gx.pojo.SYS_CollageDetai;
import com.gx.vo.CollageDetaiVo;
import com.gx.vo.CollageVo;
import com.gx.vo.ProductVo;
import com.gx.vo.ReceptionVo;
import com.gx.vo.RestockDetail;
import com.gx.vo.ReturnJson;

public interface ICollageDao {
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
			String collageState, String carNum, int startIndex, int pageSize);

	/**
	 * 查询客户接待单据信息总数
	 * 
	 * @param startDate
	 * @param endDate
	 * @param collageState
	 * @param carNum
	 * @return
	 */
	public int selectReceptionCount(String startDate, String endDate,
			String collageState, String carNum);
	/**
	 * 查询领料主信息
	 * @param receptionID
	 * @return
	 */
	public PW_Reception arrlistToCollage(int receptionID);
	/**
	 * 查询领料主信息
	 * 
	 * @param ReceptionID
	 * @return
	 */
	/**
	 */
	public CollageVo selectCollageData(int receptionID);

	/**
	 * 查询领料导入配件信息
	 * 
	 * @param ReceptionID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ProductVo> selectDaoRuPeiJian(int receptionID, int startIndex,
			int pageSize);
	/**
	 * 查询领料导入配件信息总数
	 * @param receptionID
	 * @return
	 */
	public int selectDaoRuPeiJianCount(int receptionID);
	/**
	 * 保存领料主页面
	 * @param listCollage
	 * @param listCollageDetai
	 * @return
	 */
	public int updateListCollage(List<PW_Collage> listCollage, List<SYS_CollageDetai> listCollageDetai);
	/**
	 * 查询领料单据信息
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<CollageVo> selectCollage(int startIndex, int pageSize);
	/**
	 * 查询领料单据信息
	 * @return
	 */
	public int selectCollageCount();
	/**
	 * 查询领料明细信息
	 * @param collageID
	 * @return
	 */
	public List<SYS_CollageDetai> selectCollageDetai(int collageID);
	/**
	 * 查询退料主信息
	 * @param receptionID
	 * @return
	 */
	public CollageVo selectRestock(int receptionID);
	/**
	 * 查询可退料明细信息
	 * @param collageID
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<CollageDetaiVo> selectTableCollageDetai(int collageID,int startIndex,int pageSize);
	/**
	 * 查询可退料明细信息总数
	 * @param collageID
	 * @return
	 */
	public int selectTableCollageDetaiCount(int collageID);
	/**
	 * 判断领料数量
	 * @param listRestockDetai
	 * @return
	 */
	public ReturnJson judgingQuantity(List<RestockDetail> listRestockDetai);
	
	/**
	 * 保存退料主页面
	 * @param listRestock
	 * @param listRestockDetai
	 * @return
	 */
	public int updateListRestock(List<PW_Restock> listRestock, List<RestockDetail> listRestockDetai);
	/**
	 * ReceptionID查询CollageID 退料模态框
	 * @param receptionID
	 * @return
	 */
	public int selectCollageToID(int receptionID);
	
}
