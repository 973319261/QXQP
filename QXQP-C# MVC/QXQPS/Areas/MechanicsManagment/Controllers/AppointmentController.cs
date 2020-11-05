using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.MechanicsManagment.Controllers
{
    public class AppointmentController : Controller
    {
        // GET: MechanicsManagment/Appointment
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Appointment()//预约安排
        {
            try
            {
                ViewBag.UserName = Session["UserName"].ToString().Trim();//ViewBag：获取动态视图数据字典
                return View();
            }
            catch (Exception)
            {

                //无法获取session 重定向到登录界面 重新登录
                return Redirect("/Home/Login");
            }
        }
        public ActionResult PredateNum()//生成预约单号
        {
            string PredateNum = "";
            var date = DateTime.Now.ToString("yyyyMMdd");
            var datetime = DateTime.Now.ToString("yyyyMMddHHmmss");
            try
            {
                var list = myModels.PW_Predate.Where(m => m.PredateNum.Contains(date)).OrderBy(m=>m.PredateNum).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().PredateNum.Trim().Substring(15)) + 1;
                    if (num < 10)
                    {
                        PredateNum = "BJ" + datetime + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        PredateNum = "BJ" + datetime + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        PredateNum = "BJ" + datetime + "0" + num;
                    }
                }
                else {
                    PredateNum = "BJ" + datetime + "0001";
                }
            }
            catch (Exception)
            {
                return Json(PredateNum, JsonRequestBehavior.AllowGet);
            }
            return Json(PredateNum, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ListPredate(List<PW_Predate> listPredate, List<SYS_PreRepairItemDetail> listPreRepairItem,List<SYS_PreProductDetail> listPreProduct,
          List<SYS_PreOtherCostDetail> listPreOtherCost )//保存主页面信息
        {
            var PredateID = 0;
            try
            {
                //预约表
                if (listPredate[0].PredateID == 0)
                {
                    myModels.PW_Predate.Add(listPredate[0]);
                }
                else
                {
                    myModels.Entry(listPredate[0]).State = System.Data.Entity.EntityState.Modified;
                }
                if (myModels.SaveChanges() > 0)
                {
                    PredateID = listPredate[0].PredateID;
                    if (listPreRepairItem != null)
                    {   //维修明细表
                        List<int> oldID = new List<int>();//原来ID
                        List<int> newID = new List<int>();//新ID
                        List<int> listdelectID = new List<int>();//需要删除的ID集合
                        var list = myModels.SYS_PreRepairItemDetail.Where(m => m.PredateID == PredateID).Select(m => new { m.PreRepairItemDetailID }).ToList();
                        foreach (var item in list)
                        {
                            oldID.Add(item.PreRepairItemDetailID);
                        }
                        for (int i = 0; i < listPreRepairItem.Count; i++)
                        {
                            listPreRepairItem[i].PredateID = PredateID;
                            if (listPreRepairItem[i].PreRepairItemDetailID == 0)
                            {
                                myModels.SYS_PreRepairItemDetail.Add(listPreRepairItem[i]);//新增
                            }
                            else
                            {
                                newID.Add(listPreRepairItem[i].PreRepairItemDetailID);
                                myModels.Entry(listPreRepairItem[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_PreRepairItemDetail.Where(m => m.PreRepairItemDetailID == item).Single();//删除
                            myModels.SYS_PreRepairItemDetail.Remove(listdelect);
                        }
                    }
                    else {
                        var listdelect = myModels.SYS_PreRepairItemDetail.Where(m => m.PredateID == PredateID).ToList();//删除全部
                        myModels.SYS_PreRepairItemDetail.RemoveRange(listdelect);
                    }
                    if (listPreProduct != null)
                    { 
                        //配件明细表
                        List<int> oldID = new List<int>();//原来ID
                        List<int> newID = new List<int>();//新ID
                        List<int> listdelectID = new List<int>();//需要删除的ID集合
                        var list = myModels.SYS_PreProductDetail.Where(m => m.PredateID == PredateID).Select(m => new { m.PreProductDetailID }).ToList();
                        foreach (var item in list)
                        {
                            oldID.Add(item.PreProductDetailID);
                        }
                        for (int i = 0; i < listPreProduct.Count; i++)
                        {
                            listPreProduct[i].PredateID = PredateID;
                            if (listPreProduct[i].PreProductDetailID == 0)
                            {
                                myModels.SYS_PreProductDetail.Add(listPreProduct[i]);//新增
                            }
                            else
                            {
                                newID.Add(listPreProduct[i].PreProductDetailID);
                                myModels.Entry(listPreProduct[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_PreProductDetail.Where(m => m.PreProductDetailID == item).Single();//删除
                            myModels.SYS_PreProductDetail.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_PreProductDetail.Where(m => m.PredateID == PredateID).ToList();//删除全部
                        myModels.SYS_PreProductDetail.RemoveRange(listdelect);
                    }
                    if (listPreOtherCost != null)
                    {
                        //费用明细表
                        List<int> oldID = new List<int>();//原来ID
                        List<int> newID = new List<int>();//新ID
                        List<int> listdelectID = new List<int>();//需要删除的ID集合
                        var list = myModels.SYS_PreOtherCostDetail.Where(m => m.PredateID == PredateID).Select(m => new { m.PreOtherCostDetailID }).ToList();
                        foreach (var item in list)
                        {
                            oldID.Add(item.PreOtherCostDetailID);
                        }
                        for (int i = 0; i < listPreOtherCost.Count; i++)
                        {
                            listPreOtherCost[i].PredateID = PredateID;
                            if (listPreOtherCost[i].PreOtherCostDetailID == 0)
                            {
                                myModels.SYS_PreOtherCostDetail.Add(listPreOtherCost[i]);//新增
                            }
                            else
                            {
                                newID.Add(listPreOtherCost[i].PreOtherCostDetailID);
                                myModels.Entry(listPreOtherCost[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_PreOtherCostDetail.Where(m => m.PreOtherCostDetailID == item).Single();//删除
                            myModels.SYS_PreOtherCostDetail.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_PreOtherCostDetail.Where(m => m.PredateID == PredateID).ToList();//删除全部
                        myModels.SYS_PreOtherCostDetail.RemoveRange(listdelect);
                    }
                    myModels.SaveChanges();
                }
                else
                {
                    return Json(false, JsonRequestBehavior.AllowGet);
                }
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(PredateID, JsonRequestBehavior.AllowGet);
        }
        public ActionResult DelectPredate(int PredateID)//删除预约单据信息
        {
            try
            {
                var list = myModels.PW_Predate.Where(m => m.PredateID == PredateID).Single();
                var listPreProductDetail = myModels.SYS_PreProductDetail.Where(m => m.PredateID == PredateID).ToList();
                var listPreRepairItemDetail = myModels.SYS_PreRepairItemDetail.Where(m => m.PredateID == PredateID).ToList();
                var listPreOtherCostDetail = myModels.SYS_PreOtherCostDetail.Where(m => m.PredateID == PredateID).ToList();
                myModels.PW_Predate.Remove(list);
                myModels.SYS_PreProductDetail.RemoveRange(listPreProductDetail);
                myModels.SYS_PreRepairItemDetail.RemoveRange(listPreRepairItemDetail);
                myModels.SYS_PreOtherCostDetail.RemoveRange(listPreOtherCostDetail);
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ToAudit(int PredateID)//审核预约单
        {
            try
            {
                var list = myModels.PW_Predate.Where(m => m.PredateID == PredateID).Single();
                list.ToAudit = true;
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                myModels.SaveChanges();
            }
            catch (Exception)
            {

                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ToNotAudit(int PredateID)//反审核预约单
        {
            try
            {
                var list = myModels.PW_Predate.Where(m => m.PredateID == PredateID).Single();
                list.ToAudit = false;
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                myModels.SaveChanges();
            }
            catch (Exception)
            {

                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectPredate(BsgridPage bsgridPage,string ToAudit,string PredateNum)//查询预约单据信息
        {
            var lingItem = (from tbPredate in myModels.PW_Predate
                            join tbCarder in myModels.SYS_Carder on tbPredate.CarderID equals tbCarder.CarderID
                            join tbRepair in myModels.SYS_Repair on tbPredate.RepairID equals tbRepair.RepairID
                            select new PredateVo
                            {
                                PredateID = tbPredate.PredateID,//预约维修ID
                                CarderID = tbCarder.CarderID,//接车人ID
                                CarderName = tbCarder.CarderName.Trim(),//接车人名称
                                VehicleType = tbPredate.VehicleType.Trim(),//车型
                                CustomerNum = tbPredate.CustomerNum.Trim(),//车型
                                RepairID = tbRepair.RepairID,//修理类别ID
                                RepairName = tbRepair.RepairName.Trim(),//修理类别名称
                                PredateNum = tbPredate.PredateNum.Trim(),//预约单号
                                OpenDates = tbPredate.OpenDate.ToString(),//开单日期
                                MaintenanceNum = tbPredate.MaintenanceNum.Trim(),//维修单号
                                MaintainDatas=tbPredate.MaintainData.ToString(),//维修日期
                                CarNum = tbPredate.CarNum.Trim(),//车牌
                                Owner = tbPredate.Owner.Trim(),//车主
                                CarMasterPhone = tbPredate.CarMasterPhone,//车主电话
                                Contacts = tbPredate.Contacts.Trim(),//联系人
                                Telephone = tbPredate.Telephone.Trim(),//联系电话
                                Remark = tbPredate.Remark.Trim(),//备注
                                ToAudit = tbPredate.ToAudit,//审核否
                                Amount = tbPredate.Amount,//总金额
                                Receivable = tbPredate.Receivable,//应收金额
                                Describe = tbPredate.Describe.Trim(),//描述
                                ToTransferOrder = tbPredate.ToTransferOrder,//转单否
                            }).ToList();
            #region 拼接条件
            if (!string.IsNullOrEmpty(PredateNum))
            {
                lingItem = lingItem.Where(m => m.PredateNum.Contains(PredateNum)).ToList();
            }
            if (!string.IsNullOrEmpty(ToAudit))
            {
                if (ToAudit == "true")
                {
                    lingItem = lingItem.Where(m => m.ToAudit == true).ToList();

                }
                else
                {
                    lingItem = lingItem.Where(m => m.ToAudit == false).ToList();
                }
            }
           
            #endregion
            int count = lingItem.Count();
            List<PredateVo> listPredate = lingItem.OrderBy(m => m.ToAudit).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<PredateVo> bsgrid = new Bsgrid<PredateVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listPredate,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectPredateDetail(int PredateID)//查询预约明细信息
        {
            ArrayList lists = new ArrayList();
            try
            {
                //SYS_PreRepairItemDetail修理项目明细表
                var listPreRepairItemDetail = myModels.SYS_PreRepairItemDetail.Where(m => m.PredateID == PredateID).ToList();
                //SYS_PreProductDetail产品明细表
                var listPreProductDetail = myModels.SYS_PreProductDetail.Where(m => m.PredateID == PredateID).ToList();
                //SYS_PreOtherCostDetail其他费用明细表
                var listPreOtherCostDetail = myModels.SYS_PreOtherCostDetail.Where(m => m.PredateID == PredateID).ToList();
                lists.Add(listPreRepairItemDetail);
                lists.Add(listPreProductDetail);
                lists.Add(listPreOtherCostDetail);
            }
            catch (Exception)
            {

                throw;
            }
            return Json(lists, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectToMainten(int PredateID,string MaintenanceNum)//转单成功
        {
            try
            {
                var list = myModels.PW_Predate.Where(m => m.PredateID == PredateID).Single();
                list.ToTransferOrder = true;
                list.MaintenanceNum = MaintenanceNum.Trim();
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                myModels.SaveChanges();
            }
            catch (Exception)
            {

                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
    }
}