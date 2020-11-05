using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.BasicdataManagment.Controllers
{
    public class MaintenanceController : Controller
    {
        // GET: BasicdataManagment/Maintenance
        QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Projects()//维修项目设置
        {
            return View();
        }
        /// <summary>
        /// 查询修理大类
        /// </summary>
        /// <returns></returns>
        public ActionResult SelectRepairClass()
        {
            var listRepairClass = from tbRepairClass in myModels.SYS_RepairClass
                                  select tbRepairClass;
            return Json(listRepairClass, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 新增
        /// </summary>
        /// <param name="RepairClass"></param>
        /// <returns></returns>
        public ActionResult InsertRepairClass(SYS_RepairClass RepairClass)
        {
            //定义返回
            string strMsg = "fail";
            try
            {
                //大类名称不能为空
                if (!string.IsNullOrEmpty(RepairClass.RepairClassNum) && !string.IsNullOrEmpty(RepairClass.RepairClassName))
                {
                    //查询是否已经存在该大类
                    var oldCount = (from tbRepairClass in myModels.SYS_RepairClass
                                    where tbRepairClass.RepairClassID == RepairClass.RepairClassID ||
                                    tbRepairClass.RepairClassNum == RepairClass.RepairClassNum ||
                                    tbRepairClass.RepairClassName == RepairClass.RepairClassName
                                    select tbRepairClass).Count();
                    if (oldCount == 0)
                    {
                        //新增大类
                        myModels.SYS_RepairClass.Add(RepairClass);
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
        /// <param name="RepairClass"></param>
        /// <returns></returns>
        public ActionResult SelectRepairClassByID(int RepairClassID)
        {
            var academe = myModels.SYS_RepairClass.Where(m => m.RepairClassID == RepairClassID).ToList();
            return Json(academe, JsonRequestBehavior.AllowGet);
        }
        public ActionResult UpdateRepairClass(SYS_RepairClass RepairClass)
        {
            //定义返回
            string strMsg = "fail";
            try
            {
                if (!string.IsNullOrEmpty(RepairClass.RepairClassNum) && !string.IsNullOrEmpty(RepairClass.RepairClassName))
                {
                    myModels.Entry(RepairClass).State = System.Data.Entity.EntityState.Modified;
                    if (myModels.SaveChanges() > 0)
                    {
                        strMsg = "success";
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
        //修理项目设置
        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="bsgridPage"></param>
        /// <returns></returns>
        public ActionResult SelectProjects(BsgridPage bsgridPage, int RepairClassID)
        {
            var listProjects = (from tbProjects in myModels.SYS_RepairItem
                                join tbRepairClass in myModels.SYS_RepairClass on tbProjects.RepairClassID equals tbRepairClass.RepairClassID
                                join tbMaintenance in myModels.SYS_Maintenance on tbProjects.MaintenanceID equals tbMaintenance.MaintenanceID
                                select new ProjectsVo
                                {
                                    RepairItemID = tbProjects.RepairItemID,//修理项目ID
                                    RepairClassID = tbRepairClass.RepairClassID,//修理大类ID
                                    RepairClassName = tbRepairClass.RepairClassName,//修理大类名称
                                    RepairClassNum = tbRepairClass.RepairClassNum,//修理大类编号
                                    RepairItemNum = tbProjects.RepairItemNum,//项目编号
                                    RepairItemName = tbProjects.RepairItemName,//修理项目名称
                                    Maintenance = tbMaintenance.MaintenanceName,//维修工艺
                                    RepairCharge = tbProjects.RepairCharge,//修理费
                                    Money = tbProjects.Money,//派工总金额
                                    TimeUnit = tbProjects.TimeUnit,//工时单价
                                    WorkTime = tbProjects.WorkTime,//派工总工时
                                    ToDeactivate = tbProjects.ToDeactivate,//停用标志
                                    PinYinCode = tbProjects.PinYinCode,//简拼码
                                    Remark = tbProjects.Remark//备注
                                }).ToList();
            if (RepairClassID > 0)
            {
                listProjects = listProjects.Where(m => m.RepairClassID == RepairClassID).ToList();
            }
            int count = listProjects.Count();
            List<ProjectsVo> listFittingsInfo = listProjects.OrderByDescending(m => RepairClassID).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<ProjectsVo> bsgrid = new Bsgrid<ProjectsVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listFittingsInfo,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 编号
        /// </summary>
        /// <returns></returns>
        public ActionResult Num()
        {
            string Num = "";
            var Time = DateTime.Now.ToString("yyyyMMdd");
            try
            {
                int Count = myModels.SYS_RepairItem.Where(m => m.RepairItemNum.Contains(Time)).Count() + 1;
                if (Count < 10)
                {
                    Num = "XLXM" + "-" + Time + "000" + Count;
                }
                else if (Count > 9 && Count < 100)
                {
                    Num = "XLXM" + "-" + Time + "-" + "00" + Count;
                }
                else if (Count > 99 && Count < 1000)
                {
                    Num = "XLXM" + "-" + Time + "-" + "0" + Count;
                }
            }
            catch (Exception)
            {
                return Json(Num, JsonRequestBehavior.AllowGet);
            }
            return Json(Num, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 新增
        /// </summary>
        /// <param name="Sales"></param>
        /// <returns></returns>
        public ActionResult InsertProject(SYS_RepairItem RepairItem)
        {
            string strMsg = "failed";
            try
            {
                //判断销售客户表中是否已经存在新增的销售客户信息
                var ProjectCount = (from tbProject in myModels.SYS_RepairItem
                                  where tbProject.RepairItemID == RepairItem.RepairItemID ||
                                  tbProject.RepairItemNum == RepairItem.RepairItemNum
                                  select tbProject).Count();
               
                    if (RepairItem.MaintenanceID != 0)
                    {
                        if (RepairItem.RepairClassID != 0)
                        {
                            if (RepairItem.RepairItemNum != null)
                            {
                                if(RepairItem.RepairItemName != null)
                                {
                                    myModels.SYS_RepairItem.Add(RepairItem);
                                    myModels.SaveChanges();
                                    strMsg = "success";
                                }
                                else
                                {
                                    strMsg = "项目名称不能为空，请输入完整信息";
                                }
                            }
                            else
                            {
                                strMsg = "项目编号不能为空，请输入完整信息";
                            }
                        }
                        else
                        {
                            strMsg = "修理大类不能为空，请输入完整信息";

                        }
                    }
                    else
                    {
                        strMsg = "维修工艺不能为空，请输入完整信息";
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
        /// <param name="SalesCustomerID"></param>
        /// <returns></returns>
        public ActionResult SelectProject(int RepairItemID)
        {
            var list = myModels.SYS_RepairItem.Where(m => m.RepairItemID == RepairItemID).Select(m => new
            {
                RepairItemID = m.RepairItemID,
                RepairClassID = m.RepairClassID,
                RepairItemNum = m.RepairItemNum,
                MaintenanceID = m.MaintenanceID,
                RepairCharge = m.RepairCharge,
                RepairItemName = m.RepairItemName,
                PinYinCode = m.PinYinCode,
                TimeUnit = m.TimeUnit,
                Money = m.Money,
                WorkTime = m.WorkTime,
                ToDeactivate = m.ToDeactivate,
                Remark = m.Remark.Trim(),
                
            });
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult UpdateProject(SYS_RepairItem RepairItem)
            {
            var strMsg = "failed";
            try
            {
                //判断修改后的数据是否与数据库数据重复
                var oldProjectRow = (from tbProject in myModels.SYS_RepairItem
                                     where tbProject.RepairItemID != RepairItem.RepairItemID &&
                                   (tbProject.RepairItemName == RepairItem.RepairItemName ||
                                   tbProject.RepairItemNum == tbProject.RepairItemNum)
                                   select tbProject).Count();
                
                    if (RepairItem.MaintenanceID != 0)
                    {
                        if (RepairItem.RepairClassID != 0)
                        {
                            if (RepairItem.RepairItemNum != null)
                            {
                                if(RepairItem.RepairItemName!= null)
                                {
                                       myModels.Entry(RepairItem).State = System.Data.Entity.EntityState.Modified;
                                //保存数据库
                                myModels.SaveChanges();
                                strMsg = "success";
                                }
                                else
                                {
                                    strMsg = "项目名称不能为空，请输入完整信息";
                                }

                             
                            }
                            else
                            {
                                strMsg = "项目编号不能为空，请输入完整信息";
                            }
                        }
                        else
                        {
                            strMsg = "修理大类不能为空，请输入完整信息";

                        }
                    }
                    else
                    {
                        strMsg = "维修工艺不能为空，请输入完整信息";
                    }
            }
            catch (Exception)
            {
                strMsg = "failed";
            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }


        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="RepairItemID"></param>
        /// <returns></returns>
        public ActionResult DeleteProject(int RepairItemID)
        {
            try
            {
                var listProject = myModels.SYS_RepairItem
                    .Where(m => m.RepairItemID == RepairItemID).Single();
                myModels.SYS_RepairItem.Remove(listProject);
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