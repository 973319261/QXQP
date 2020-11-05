using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.MechanicsManagment.Controllers
{
    public class DispatchingController : Controller
    {
        // GET: MechanicsManagment/Dispatching
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Dispatching()//维修派工
        {
            return View();
        }
        [HttpPost]
        public ActionResult Dispatching(int ReceptionID)//维修派工
        {
            Session["ReceptionID"] = ReceptionID;
            return View();
        }
        public ActionResult ClearDispatching()//维修派工
        {
            Session["ReceptionID"] = null;
            return View();
        }
        public ActionResult SelectReception(int ReceptionID)//查询主页面信息
        {
            var lingItem = (from tbReception in myModels.PW_Reception
                            join tbRepair in myModels.SYS_Repair on tbReception.RepairID equals tbRepair.RepairID
                            join tbDocumentState in myModels.SYS_DocumentState on tbReception.DocumentStateID equals tbDocumentState.DocumentStateID
                            join tbBalanceState in myModels.SYS_BalanceState on tbReception.BalanceStateID equals tbBalanceState.BalanceStateID
                            join tbCustomerSou in myModels.SYS_CustomerSou on tbReception.CustomerSouID equals tbCustomerSou.CustomerSouID
                            where tbReception.ReceptionID== ReceptionID
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
                                CompletionDates = tbReception.CompletionDate.ToString(),//完工日期
                                ToSendWork = tbReception.ToSendWork,//派工状态
                                MaintenAmount = tbReception.MaintenAmount,//维修总费
                          }).Single();
            return Json(lingItem, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ListRepairItemDetail( List<SYS_RecRepairItemDetail> listRecRepairItem,decimal MaintenAmount,string SelfCoding,int ReceptionID, bool ToSendWork)//保存主页面信息
        {
            try
            {
                var listReception = myModels.PW_Reception.Where(m => m.ReceptionID == ReceptionID).Single();
                listReception.MaintenAmount = MaintenAmount;
                listReception.SelfCoding = SelfCoding;
                listReception.ToSendWork = ToSendWork;
                myModels.Entry(listReception).State = System.Data.Entity.EntityState.Modified;
                if (myModels.SaveChanges()>0)
                {  
                    //配件明细表
                    if (listRecRepairItem != null)
                    {
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
                            if (listRecRepairItem[i].RecRepairItemDetailID == 0)
                            {
                                myModels.SYS_RecRepairItemDetail.Add(listRecRepairItem[i]);
                            }
                            else
                            {
                                newID.Add(listRecRepairItem[i].RecRepairItemDetailID);
                                myModels.Entry(listRecRepairItem[i]).State = System.Data.Entity.EntityState.Modified;
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
                    myModels.SaveChanges();
                }
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectDispatch(int DispatchID)//查询派工结算方式金额
        {
            var Price = 0;
            try
            {
                var list = myModels.SYS_Dispatch.Where(m => m.DispatchID == DispatchID).Select(m => new {
                    Price = m.Price
                }).Single();
                Price = (int)list.Price;
            }
            catch (Exception)
            {

                throw;
            }
            return Json(Price, JsonRequestBehavior.AllowGet);
        }
    }
}