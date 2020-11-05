using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.BasicdataManagment.Controllers
{
    public class InformationController : Controller
    {
        // GET: BasicdataManagment/Information
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Spare()//配件信息设置
        {
            return View();
        }
        /// <summary>
        /// 查询商品类别
        /// </summary>
        /// <returns></returns>
        public ActionResult SelectFittingsType()
        {
            var listFittingsType = from tbFittingsType in myModels.SYS_FittingsType
                                   select tbFittingsType;
            return Json(listFittingsType, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="bsgridPage"></param>
        /// <returns></returns>
        public ActionResult SelectSpare(BsgridPage bsgridPage, int FittingsTypeID)
        {
            var listSpare = (from tbSpare in myModels.SYS_FittingsInfo
                             join tbFittingsType in myModels.SYS_FittingsType on tbSpare.FittingsTypeID equals tbFittingsType.FittingsTypeID
                             join tbSystemUnit in myModels.SYS_SystemUnit on tbSpare.SystemUnitID equals tbSystemUnit.SystemUnitID
                             join tbVehicleType in myModels.SYS_VehicleType on tbSpare.VehicleTypeID equals tbVehicleType.VehicleTypeID
                             join tbSuppliers in myModels.SYS_Suppliers on tbSpare.SuppliersID equals tbSuppliers.SuppliersID
                             select new SpareVo
                             {
                                 FittingsInfoID = tbSpare.FittingsInfoID,//配件信息ID
                                 FittingsTypeID = tbFittingsType.FittingsTypeID,//商品类别ID
                                 FittingsTypeName = tbFittingsType.FittingsTypeName,//商品类别
                                 Barcode = tbSpare.Barcode,//条码
                                 FittingsCode = tbSpare.FittingsCode,//邮件编码
                                 FittingsName = tbSpare.FittingsName,//配件名称
                                 Specification = tbSpare.Specification,//配件规格
                                 SystemUnit = tbSystemUnit.SystemUnit,//单位
                                 VehicleType = tbVehicleType.VehicleType,//车型
                                 Brand = tbSpare.Brand,//品牌
                                 Intake = tbSpare.Intake,//进价
                                 OpenPrice = tbSpare.OpenPrice,//开单价
                                 SalesPrice = tbSpare.SalesPrice,//销售价格
                                 WholesalePrice = tbSpare.WholesalePrice,//批发价格
                                 InventoryMax = tbSpare.InventoryMax,//库存上限
                                 InventoryMin = tbSpare.InventoryMin,//库存下限
                                 SuppliersName = tbSuppliers.SuppliersName,//供应商
                                 Remark = tbSpare.Remark,//备注
                             }).ToList();
            if (FittingsTypeID > 0)
            {
                listSpare = listSpare.Where(m => m.FittingsTypeID == FittingsTypeID).ToList();
            }
            int count = listSpare.Count();
            List<SpareVo> listFittingsType = listSpare.OrderByDescending(m => FittingsTypeID).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<SpareVo> bsgrid = new Bsgrid<SpareVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listFittingsType,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);

        }
        /// <summary>
        /// 新增
        /// </summary>
        /// <param name="FittingsInfo"></param>
        /// <returns></returns>
        public ActionResult InsertSpare(SYS_FittingsInfo FittingsInfo)
        {
            string strMsg = "fail";
            try
            {
                //判断配件信息表是否存在新增的配件信息
                var SelectCount = (from tbFittingsInfo in myModels.SYS_FittingsInfo
                                   where tbFittingsInfo.FittingsInfoID == FittingsInfo.FittingsInfoID ||
                                   tbFittingsInfo.FittingsName == FittingsInfo.FittingsName
                                   select tbFittingsInfo).Count();
                if (SelectCount == 0)
                {
                    myModels.SYS_FittingsInfo.Add(FittingsInfo);
                    myModels.SaveChanges();
                    strMsg = "success";
                }
                else
                {
                    strMsg = "该配件信息已存在，不需要重复输入数据！";
                }
            }
            catch (Exception e)
            {
                strMsg = "failed";
            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="FittingsInfoID"></param>
        /// <returns></returns>
        public ActionResult SelectSpareByID(int FittingsInfoID)
        {
            var list = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).Select(m => new
            {
                FittingsInfoID = m.FittingsInfoID,//配件信息ID
                Picture = m.Picture,//图片
                FittingsCode = m.FittingsCode,//配件编码
                FittingsTypeID = m.FittingsTypeID,//商品类别
                FittingsName = m.FittingsName,//配件名称
                Barcode = m.Barcode,// 条码
                Specification = m.Specification,// 配件规格
                Brand = m.Brand,//品牌
                SystemUnitID = m.SystemUnitID,//单位
                VehicleTypeID = m.VehicleTypeID,//所属车型
                Intake = m.Intake,//进价
                SalesPrice = m.SalesPrice,//销售价格
                WholesalePrice = m.WholesalePrice,//批发价
                OpenPrice = m.OpenPrice,//开单价
                InventoryMax = m.InventoryMax,//库存上限
                InventoryMin = m.InventoryMin,//库存下限
                SuppliersID = m.SuppliersID,//供应商
                Remark = m.Remark//备注
            });
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult UpdateSpare(SYS_FittingsInfo FittingsInfo)
        {
            var strMsg = "failed";
            try
            {
                //判断修改后的数据是否与数据库重复
                var oldFittingsInfoRow = (from tbFittingsInfo in myModels.SYS_FittingsInfo
                                          where tbFittingsInfo.FittingsInfoID == FittingsInfo.FittingsInfoID &&
                                          (tbFittingsInfo.FittingsName == FittingsInfo.FittingsName ||
                                          tbFittingsInfo.FittingsCode == FittingsInfo.FittingsCode)
                                          select tbFittingsInfo).Count();
                if (oldFittingsInfoRow == 0)
                {
                    myModels.Entry(FittingsInfo).State = System.Data.Entity.EntityState.Modified;
                    //保存数据库
                    myModels.SaveChanges();
                    strMsg = "success";

                    //myModels.Entry(FittingsInfo).State = System.Data.Entity.EntityState.Modified;
                    ////保存数据库
                    //if (myModels.SaveChanges() > 0)
                    //{
                    //    strMsg = "success";
                    //}

                }
                else
                {
                    strMsg = "配件名称已存在！";
                }

            }
            catch (Exception)
            {
                strMsg = "修改失败";
            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="FittingsInfoID"></param>
        /// <returns></returns>
        public ActionResult DeleteSpare(int FittingsInfoID)
        {
            try
            {
                var listSpare = myModels.SYS_FittingsInfo
                    .Where(m => m.FittingsInfoID == FittingsInfoID).Single();
                myModels.SYS_FittingsInfo.Remove(listSpare);
                if (myModels.SaveChanges() > 0)
                {
                    return Json(true, JsonRequestBehavior.AllowGet);
                }
                else
                {
                    return Json(false, JsonRequestBehavior.AllowGet);
                }
            }
            catch (Exception e)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
    }
}