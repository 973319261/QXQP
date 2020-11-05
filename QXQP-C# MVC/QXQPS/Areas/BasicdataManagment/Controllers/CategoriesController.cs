using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.BasicdataManagment.Controllers
{
    public class CategoriesController : Controller
    {
        // GET: BasicdataManagment/Categories
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Accessories()//配件大类设置
        {
            return View();
        }
        /// <summary>
        /// 查询配件大类
        /// </summary>
        /// <returns></returns>
        public ActionResult SelectAccessories()
        {
            var list = from tbAccessories in myModels.SYS_FittingsType
                       select tbAccessories;
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 新增
        /// </summary>
        /// <param name="FittingsType"></param>
        /// <returns></returns>
        public ActionResult InsertFittingsType(SYS_FittingsType FittingsType)
        {
            string strMsg = "failed";
            try
            {
                //判断配件大类表中是否已经存在新增的配件大类信息
                var SelectCount = (from tbFittingsType in myModels.SYS_FittingsType
                                   where tbFittingsType.FittingsTypeID == FittingsType.FittingsTypeID ||
                                   tbFittingsType.FittingsTypeNum == FittingsType.FittingsTypeNum &&
                                   tbFittingsType.FittingsTypeName == FittingsType.FittingsTypeName
                                   select tbFittingsType).Count();
                if (SelectCount == 0)
                {
                    myModels.SYS_FittingsType.Add(FittingsType);
                    myModels.SaveChanges();
                    strMsg = "success";
                }
                else
                {
                    strMsg = "该配件大类已经存在，不需要重复输入数据！";
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
        /// <param name="FittingsType"></param>
        /// <returns></returns>
        public ActionResult SelectFittingsTypeByID(int FittingsTypeID)
        {
            var list = myModels.SYS_FittingsType.Where(m => m.FittingsTypeID == FittingsTypeID).Select(m => new
            {
                FittingsTypeID = m.FittingsTypeID,//配件大类ID
                FittingsTypeNum = m.FittingsTypeNum,//配件大类编号
                FittingsTypeName = m.FittingsTypeName,//配件大类名称
                Remark = m.Remark,//备注
            });
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="FittingsType"></param>
        /// <returns></returns>
        public ActionResult UpdateFittingsType(SYS_FittingsType FittingsType)
        {
            var strMsg = "failed";
            try
            {
                //判断配件大类表中是否已经存在新增的配件大类信息
                var oldFittingsTypeRow = (from tbFittingsType in myModels.SYS_FittingsType
                                          where tbFittingsType.FittingsTypeID != FittingsType.FittingsTypeID &&
                                          (tbFittingsType.FittingsTypeNum == FittingsType.FittingsTypeNum)
                                          // && tbFittingsType.FittingsTypeName == FittingsType.FittingsTypeName
                                          select tbFittingsType).Count();
                if (oldFittingsTypeRow == 0)
                {
                    myModels.Entry(FittingsType).State = System.Data.Entity.EntityState.Modified;
                    //保存数据库
                    myModels.SaveChanges();
                    strMsg = "success";
                }
                else
                {
                    strMsg = "该配件大类已经存在，不需要重复输入数据！";
                }
            }
            catch (Exception)
            {
                strMsg = "failed";
            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }
    }
}