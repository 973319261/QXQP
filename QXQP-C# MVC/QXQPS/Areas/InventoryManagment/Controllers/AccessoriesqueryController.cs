using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.InventoryManagment.Controllers
{
    public class AccessoriesqueryController : Controller
    {
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        // GET: InventoryManagment/Accessoriesquery
        public ActionResult query()//库存配件查询
        {
            return View();
        }
        public ActionResult SelectWarehouse()//查询仓库
        {
            List<SelectVo> listWarehouse = (from tbWarehouse in myModels.SYS_Warehouse
                                            select new SelectVo
                                            {
                                                id = tbWarehouse.WarehouseID,
                                                text = tbWarehouse.WarehouseName.Trim()
                                            }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listWarehouse = Common.Tools.SetSelectJson(listWarehouse);//设置selectjson
            return Json(listWarehouse, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 查询
        /// </summary>
        /// <returns></returns>
        public ActionResult SelectQuery(BsgridPage bsgridPage, string FittingsCode, string FittingsName, string FittingsType, int WarehouseID)
        {
            var lingItem = (from tbQuery in myModels.SYS_Inventory   
                             join tbWarehouse in myModels.SYS_Warehouse on tbQuery.WarehouseID equals tbWarehouse.WarehouseID
                             select new QueryVo
                             {
                                 InventoryID = tbQuery.InventoryID,//库存ID
                                 WarehouseID = tbWarehouse.WarehouseID,//仓库ID
                                 WarehouseName = tbWarehouse.WarehouseName,//仓库名称
                                 FittingsTypeName = tbQuery.FittingsType.Trim() ,//所属类型
                                 FittingsCode = tbQuery.FittingsCode,//配件编码
                                 FittingsName = tbQuery.FittingsName,//配件名称
                                 VehicleType = tbQuery.VehicleType,//车型
                                 SystemUnit = tbQuery.SystemUnit,//单位
                                 InvenQuan = tbQuery.InvenQuan,//库存量
                                 NewIntake = tbQuery.NewIntake,//最新进价
                                 WholePrice = tbQuery.WholePrice,//批发价
                                 SalePrice = tbQuery.SalePrice,//销售价
                                 MinInventory = tbQuery.MinInventory,//最低库存
                                 Position = tbQuery.Position,//仓位
                             }).ToList();
            #region

            if (!string.IsNullOrEmpty(FittingsCode))
            {
                lingItem = lingItem.Where(m => m.FittingsCode.Contains(FittingsCode)).ToList();//配件编码查询
            }
            if (!string.IsNullOrEmpty(FittingsName))
            {
                lingItem = lingItem.Where(m => m.FittingsName.Contains(FittingsName)).ToList();//配件名称查询
            }
            if (!string.IsNullOrEmpty(FittingsType))
            {
                lingItem = lingItem.Where(m => m.FittingsTypeName.Contains(FittingsType)).ToList();//配件类型查询
            }
            if (WarehouseID > 0)
            {
                lingItem = lingItem.Where(m => m.WarehouseID == WarehouseID).ToList();
            }
            #endregion
            int count = lingItem.Count();
            List<QueryVo> listQuery = lingItem.OrderByDescending(m => m.InventoryID).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<QueryVo> bsgrid = new Bsgrid<QueryVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listQuery,
            };

            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 查询
        /// </summary>
        /// <returns></returns>
        public ActionResult SelectSpareQuery(BsgridPage bsgridPage, string FittingsCode, string FittingsName, string FittingsType)
        {
            var lingItem =  (from tbSpareQuery in myModels.SYS_FittingsInfo
                            join tbFittingsType in myModels.SYS_FittingsType on tbSpareQuery.FittingsTypeID equals tbFittingsType.FittingsTypeID
                            join tbSystemUnit in myModels.SYS_SystemUnit on tbSpareQuery.SystemUnitID equals tbSystemUnit.SystemUnitID
                            join tbVehicleType in myModels.SYS_VehicleType on tbSpareQuery.VehicleTypeID equals tbVehicleType.VehicleTypeID
                            join tbSuppliers in myModels.SYS_Suppliers on tbSpareQuery.SuppliersID equals tbSuppliers.SuppliersID
                            select new SpareVo
                            {
                                FittingsInfoID = tbSpareQuery.FittingsInfoID,//配件信息ID
                                FittingsTypeID = tbFittingsType.FittingsTypeID,//商品类别ID
                                FittingsTypeName = tbFittingsType.FittingsTypeName,//商品类别     
                                Barcode = tbSpareQuery.Barcode,//条码
                                FittingsCode = tbSpareQuery.FittingsCode,//配件编码
                                FittingsName = tbSpareQuery.FittingsName,//配件名称
                                VehicleType = tbVehicleType.VehicleType,//车型
                                SuppliersName = tbSuppliers.SuppliersName,//供应商
                                Brand = tbSpareQuery.Brand,//品牌
                                InvenQuan = tbSpareQuery.InvenQuan,//库存量
                                InventoryMax = tbSpareQuery.InventoryMax,//库存上限
                                InventoryMin = tbSpareQuery.InventoryMin,//库存下限
                                SystemUnit = tbSystemUnit.SystemUnit,//单位
                                Intake = tbSpareQuery.Intake,//进价
                                SalesPrice = tbSpareQuery.SalesPrice,//销售价格
                                Specification = tbSpareQuery.Specification,//配件规格
                            }).ToList();
            #region

            if (!string.IsNullOrEmpty(FittingsCode))
            {
                lingItem = lingItem.Where(m => m.FittingsCode.Contains(FittingsCode)).ToList();//配件编码查询
            }
            if (!string.IsNullOrEmpty(FittingsName))
            {
                lingItem = lingItem.Where(m => m.FittingsName.Contains(FittingsName)).ToList();//配件名称查询
            }
            if (!string.IsNullOrEmpty(FittingsType))
            {
                lingItem = lingItem.Where(m => m.FittingsTypeName.Contains(FittingsType)).ToList();//配件类型查询
            }
           
            #endregion
           int count = lingItem.Count();
            List<SpareVo> listSpare = lingItem.OrderByDescending(m => m.FittingsInfoID).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<SpareVo> bsgrid = new Bsgrid<SpareVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listSpare,
            };

            return Json(bsgrid, JsonRequestBehavior.AllowGet);

        }
    }
}