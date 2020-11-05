using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.BasicdataManagment.Controllers
{
    public class WarehouseController : Controller
    {
        // GET: BasicdataManagment/Warehouse
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Warehouse()//仓库信息设置
        {
            return View();
        }
        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="bsgridpage"></param>
        /// <returns></returns>
        /// 
        public ActionResult SelectWarehouse(BsgridPage bsgridPage)
        {
            List<SYS_Warehouse> listWarehouse = myModels.SYS_Warehouse.OrderByDescending(m => m.WarehouseID)
                .Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            int totalRow = myModels.SYS_Warehouse.Count();
            Bsgrid<SYS_Warehouse> bsgrid = new Bsgrid<SYS_Warehouse>()
            {
                success = true,
                totalRows = totalRow,
                curPage = bsgridPage.curPage,
                data = listWarehouse
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 新增
        /// </summary>
        /// <param name="Warehouse"></param>
        /// <returns></returns>
        public ActionResult InsertWarehouse(SYS_Warehouse Warehouse)
        {
            //定义返回
            string strMsg = "fail";
            try
            {
                //名称不能为空
                if (!string.IsNullOrEmpty(Warehouse.WarehouseNum) && !string.IsNullOrEmpty(Warehouse.WarehouseName))
                {
                    //查询是否已存在该仓库
                    var oldCount = (from tbWarehouse in myModels.SYS_Warehouse
                                    where tbWarehouse.WarehouseID == Warehouse.WarehouseID ||
                                    tbWarehouse.WarehouseNum == Warehouse.WarehouseNum ||
                                    tbWarehouse.WarehouseName == Warehouse.WarehouseName
                                    select tbWarehouse).Count();
                    if (oldCount == 0)
                    {

                        //新增仓库
                        myModels.SYS_Warehouse.Add(Warehouse);
                        if (myModels.SaveChanges() > 0)
                        {
                            strMsg = "success";
                        }
                    }
                    else
                    {
                        strMsg = "exist";
                    }
                }
                else
                {
                    strMsg = "nofull";
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="Warehouse"></param>
        /// <returns></returns>
        public ActionResult SelectWarehouseByID(int WarehouseID)
        {
            var academe = myModels.SYS_Warehouse.Where(m => m.WarehouseID == WarehouseID).ToList();
            return Json(academe, JsonRequestBehavior.AllowGet);
        }
        public ActionResult UpdateWarehouse(SYS_Warehouse Warehouse)
        {
            //定义返回
            string strMsg = "fail";
            try
            {
                //判断仓库名称是否为空
                if (!string.IsNullOrEmpty(Warehouse.WarehouseNum) && !string.IsNullOrEmpty(Warehouse.WarehouseName))
                {
                    //查询是否已存在该仓库
                    //var oldCount = (from tbWarehouse in myModels.SYS_Warehouse
                    //                where tbWarehouse.WarehouseID == Warehouse.WarehouseID ||
                    //                tbWarehouse.WarehouseNum == Warehouse.WarehouseNum ||
                    //                tbWarehouse.WarehouseName == Warehouse.WarehouseName
                    //                select tbWarehouse).Count();
                    //if (oldCount == 0)
                    //{
                    //修改
                    myModels.Entry(Warehouse).State = System.Data.Entity.EntityState.Modified;
                    if (myModels.SaveChanges() > 0)
                    {
                        strMsg = "success";
                    }
                    //}
                    //else
                    //{
                    //    strMsg = "exist";
                    //}
                }
                else
                {
                    strMsg = "nofull";
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);

            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="WarehouseID"></param>
        /// <returns></returns>
        public ActionResult DeleteWarehouse(int WarehouseID)
        {
            try
            {
                var listWarehouse = myModels.SYS_Warehouse
                    .Where(m => m.WarehouseID == WarehouseID).Single();
                myModels.SYS_Warehouse.Remove(listWarehouse);
                if (myModels.SaveChanges() > 0)
                {
                    return Json(true, JsonRequestBehavior.AllowGet);
                }
                else
                {
                    return Json(false, JsonRequestBehavior.AllowGet);
                }
            }
            catch (Exception E)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
    }
}