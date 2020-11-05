using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.MechanicsManagment.Controllers
{
    public class RapidrepairController : Controller
    {
        // GET: MechanicsManagment/Rapidrepair
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Rapidrepair()//快速修车
        {
                return View();
        }
        [HttpPost]
        public ActionResult Rapidrepair(int ReceptionID)//快速修车
        {
            Session["receptionID"] = ReceptionID;
            return View();
        }
        public ActionResult ClearReceptions()//快速修车
        {
            Session["receptionID"] = null;
            return Json("", JsonRequestBehavior.AllowGet);
        }
        public ActionResult Collage()//快速领料
        {
            try
            {
                ViewBag.ReceptionID = Session["ReceptionID"];
                return View();
            }
            catch (Exception)
            {
                //无法获取session 重定向到登录界面 重新登录
                return Redirect("/Home/Login");
            }
        }
        [HttpPost]
        public ActionResult Collage(int ReceptionID)//快速领料
        {
            Session["ReceptionID"] = ReceptionID;
            return View();
        }
        public ActionResult ClearReception()//快速修车
        {
            Session["receptionID"] = null;
            return Json("", JsonRequestBehavior.AllowGet);
        }


        public ActionResult MaintenanceNum()//生成预约单号
        {
            string MaintenanceNum = "";
            var date = "K" + DateTime.Now.ToString("yyyyMMdd");
            try
            {
                var list = myModels.PW_Reception.Where(m => m.MaintenanceNum.Contains(date)).OrderBy(m => m.MaintenanceNum).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().MaintenanceNum.Trim().Substring(9)) + 1;
                    if (num < 10)
                    {
                        MaintenanceNum = date + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        MaintenanceNum = date + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        MaintenanceNum = date + "0" + num;
                    }
                }
                else {
                    MaintenanceNum = date + "0001";
                }
            }
            catch (Exception)
            {
                return Json(MaintenanceNum, JsonRequestBehavior.AllowGet);
            }
            return Json(MaintenanceNum, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ListReceptione(List<PW_Reception> listReception, List<SYS_RecRepairItemDetail> listRecRepairItem, List<SYS_CollageDetai> listCollageDetai,
              List<SYS_RecOtherCostDetail> listRecOtherCost, List<SYS_InsuranceDetail> listArrInsuranceMoney, List<SYS_ThreePacksDetail> listThreePacksDetail)//保存主页面信息
        {
            var ReceptionID = 0;
            try
            { //预约表
                ReceptionID = listReception[0].ReceptionID;
                if (listRecRepairItem != null)
                {
                    listReception[0].ToSendWork = true;//派工状态
                }
                else {
                    listReception[0].ToSendWork = false;//派工状态
                }
                if (ReceptionID == 0)
                {
                    listReception[0].DocumentStateID = 1; //未结算
                    listReception[0].BalanceStateID = 3;//未付款
                    listReception[0].CollageState = "未领料";//领料状态
                    listReception[0].ToCompletion = false;//领料状态
                    myModels.PW_Reception.Add(listReception[0]);
                }
                else
                {
                    var list = myModels.PW_Reception.Where(m => m.ReceptionID == ReceptionID).Select(m => new
                    {
                        MaintenAmount = m.MaintenAmount,//维修总额
                        DocumentStateID = m.DocumentStateID,//单据状态
                        BalanceStateID = m.BalanceStateID,//
                        CollageState = m.CollageState,//领料状态
                        ToCompletion = m.ToCompletion,
                    }).Single();
                    listReception[0].MaintenAmount = list.MaintenAmount;
                    listReception[0].DocumentStateID = list.DocumentStateID;
                    listReception[0].BalanceStateID = list.BalanceStateID;
                    listReception[0].CollageState = list.CollageState;
                    listReception[0].ToCompletion = list.ToCompletion;
                    myModels.Entry(listReception[0]).State = System.Data.Entity.EntityState.Modified;
                }
                if (myModels.SaveChanges() > 0)
                {
                    ReceptionID = listReception[0].ReceptionID;
                    if (listRecRepairItem != null)
                    {  //维修明细表
                        List<int> oldID = new List<int>();//原来ID
                        List<int> newID = new List<int>();//新ID
                        List<int> listdelectID = new List<int>();//需要删除的ID集合
                        var list = myModels.SYS_RecRepairItemDetail.Where(m => m.ReceptionID == ReceptionID).Select(m => new { m.RecRepairItemDetailID }).ToList();
                        foreach (var item in list)
                        {
                            oldID.Add(item.RecRepairItemDetailID);
                        }
                        for (int i = 0; i < listRecRepairItem.Count; i++)
                        {
                            listRecRepairItem[i].ReceptionID = ReceptionID;
                            var RecRepairItemDetailID = listRecRepairItem[i].RecRepairItemDetailID;
                            if (RecRepairItemDetailID == 0)
                            {
                                myModels.SYS_RecRepairItemDetail.Add(listRecRepairItem[i]);//新增
                            }
                            else
                            {
                                newID.Add(listRecRepairItem[i].RecRepairItemDetailID);
                                myModels.Entry(listRecRepairItem[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_RecRepairItemDetail.Where(m => m.RecRepairItemDetailID == item).Single();//删除
                            myModels.SYS_RecRepairItemDetail.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_RecRepairItemDetail.Where(m => m.ReceptionID == ReceptionID).ToList();//删除全部
                        myModels.SYS_RecRepairItemDetail.RemoveRange(listdelect);
                    }
                    if (listCollageDetai != null)
                    {
                        for (int i = 0; i < listCollageDetai.Count; i++)
                        {
                            var CollageDetaiID = listCollageDetai[i].CollageDetaiID;
                            var MaintainabilityID = listCollageDetai[i].MaintainabilityID;
                            var list = myModels.SYS_CollageDetai.Where(m => m.CollageDetaiID == CollageDetaiID).SingleOrDefault();
                            list.MaintainabilityID = MaintainabilityID;
                            myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;//修改
                        }
                    }
                    if (listRecOtherCost != null)
                    {
                        //配件明细表
                        List<int> oldID = new List<int>();//原来ID
                        List<int> newID = new List<int>();//新ID
                        List<int> listdelectID = new List<int>();//需要删除的ID集合
                        var list = myModels.SYS_RecOtherCostDetail.Where(m => m.ReceptionID == ReceptionID).Select(m => new { m.RecOtherCostDetailID }).ToList();
                        foreach (var item in list)
                        {
                            oldID.Add(item.RecOtherCostDetailID);
                        }
                        for (int i = 0; i < listRecOtherCost.Count; i++)
                        {
                            listRecOtherCost[i].ReceptionID = ReceptionID;
                            if (listRecOtherCost[i].RecOtherCostDetailID == 0)
                            {
                                myModels.SYS_RecOtherCostDetail.Add(listRecOtherCost[i]);//新增
                            }
                            else
                            {
                                newID.Add(listRecOtherCost[i].RecOtherCostDetailID);
                                myModels.Entry(listRecOtherCost[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_RecOtherCostDetail.Where(m => m.RecOtherCostDetailID == item).Single();//删除
                            myModels.SYS_RecOtherCostDetail.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_RecOtherCostDetail.Where(m => m.ReceptionID == ReceptionID).ToList();//删除全部
                        myModels.SYS_RecOtherCostDetail.RemoveRange(listdelect);
                    }
                    if (listArrInsuranceMoney != null)
                    {
                        //保险理赔明细
                        listArrInsuranceMoney[0].ReceptionID = ReceptionID;
                        if (listArrInsuranceMoney[0].InsuranceDetailID == 0)
                        {
                            myModels.SYS_InsuranceDetail.Add(listArrInsuranceMoney[0]);
                        }
                        else
                        {
                            myModels.Entry(listArrInsuranceMoney[0]).State = System.Data.Entity.EntityState.Modified;//修改
                        }
                    }
                    if (listThreePacksDetail != null)
                    {
                        //三包索赔明细
                        listThreePacksDetail[0].ReceptionID = ReceptionID;
                        if (listThreePacksDetail[0].ThreePacksDetailID == 0)
                        {
                            myModels.SYS_ThreePacksDetail.Add(listThreePacksDetail[0]);
                        }
                        else
                        {
                            myModels.Entry(listThreePacksDetail[0]).State = System.Data.Entity.EntityState.Modified;//修改
                        }
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
            return Json(ReceptionID, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectReception(int ReceptionID)//查询客户接待单据信息
        {
            var lingItem = (from tbReception in myModels.PW_Reception
                            join tbRepair in myModels.SYS_Repair on tbReception.RepairID equals tbRepair.RepairID
                            join tbDocumentState in myModels.SYS_DocumentState on tbReception.DocumentStateID equals tbDocumentState.DocumentStateID
                            join tbBalanceState in myModels.SYS_BalanceState on tbReception.BalanceStateID equals tbBalanceState.BalanceStateID
                            join tbCustomerSou in myModels.SYS_CustomerSou on tbReception.CustomerSouID equals tbCustomerSou.CustomerSouID
                            where tbReception.ReceptionID==ReceptionID
                            select new ReceptionVo
                            {
                                ReceptionID = tbReception.ReceptionID,//客户接待ID
                                RepairID = tbReception.RepairID,//修理类别ID
                                RepairName = tbRepair.RepairName.Trim(),//修理类别名称
                                CustomerSouID = tbReception.CustomerSouID,//客户来源ID
                                CustomerSou = tbCustomerSou.CustomerSou.Trim(),//客户来源名称
                                DocumentStateID = tbDocumentState.DocumentStateID,//单据状态ID
                                DocumentState = tbDocumentState.DocumentState.Trim(),//单据状态名称
                                BalanceStateID = tbBalanceState.BalanceStateID,//结算状态ID
                                BalanceState = tbBalanceState.BalanceState.Trim(),//结算状态名称
                                CarNum = tbReception.CarNum.Trim(),//车牌
                                CustomerNum = tbReception.CustomerNum.Trim(),//客户编号
                                VehicleType = tbReception.VehicleType.Trim(),//车型
                                Carder = tbReception.Carder.Trim(),//接车人名称
                                Mileage = tbReception.Mileage.Trim(),//进厂里程
                                MaintenanceNum = tbReception.MaintenanceNum.Trim(),//维修单号
                                Owner = tbReception.Owner.Trim(),//车主
                                Address = tbReception.Address.Trim(),//地址
                                OilQuantity = tbReception.OilQuantity.Trim(),//油量
                                OwnerTele = tbReception.OwnerTele,//车主电话
                                EngineNum = tbReception.EngineNum.Trim(),//发动机号码
                                Repairman = tbReception.Repairman.Trim(),//送修人
                                SelfCoding = tbReception.SelfCoding.Trim(),//自编号
                                FrameNum = tbReception.FrameNum,//车架号码
                                RepairmanTele = tbReception.RepairmanTele,//送修人电话
                                OpenDates = tbReception.OpenDate.ToString(),//开单日期
                                FactoryDates = tbReception.FactoryDate.ToString(),//进厂日期
                                BalanceDates = tbReception.BalanceDate.ToString(),//结算日期
                                openDates = tbReception.OpenDate.ToString(),//开单日期
                                factoryDates = tbReception.FactoryDate.ToString(),//进厂日期
                                balanceDates = tbReception.BalanceDate.ToString(),//结算日期
                                Amount = tbReception.Amount,//总金额
                                AmountPaid = tbReception.AmountPaid,//应收金额
                                Describe = tbReception.Describe.Trim(),//描述
                                ToAudit = tbReception.ToAudit,//审核否
                                CompletionDates = tbReception.CompletionDate.ToString() != null ? tbReception.CompletionDate.ToString() : "0000-00-00",//完工日期
                                ToSendWork = tbReception.ToSendWork,//派工状态
                                CollageState = tbReception.CollageState.Trim(),//领料状态
                                MaintenAmount = tbReception.MaintenAmount,//维修总费
                            }).SingleOrDefault();
            return Json(lingItem, JsonRequestBehavior.AllowGet);
        }
    }
}