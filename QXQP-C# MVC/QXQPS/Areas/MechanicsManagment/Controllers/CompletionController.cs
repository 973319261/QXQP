using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.MechanicsManagment.Controllers
{
    public class CompletionController : Controller
    {
        // GET: MechanicsManagment/Completion
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Completion()//完工确认
        {
            return View();
        }
        public ActionResult SelectReception(BsgridPage bsgridPage, string StartDate, string EndDate, string ToCompletion)//查询客户接待单据信息
        {
            var lingItem = (from tbReception in myModels.PW_Reception
                            join tbRepair in myModels.SYS_Repair on tbReception.RepairID equals tbRepair.RepairID
                            join tbDocumentState in myModels.SYS_DocumentState on tbReception.DocumentStateID equals tbDocumentState.DocumentStateID
                            join tbBalanceState in myModels.SYS_BalanceState on tbReception.BalanceStateID equals tbBalanceState.BalanceStateID
                            join tbCustomerSou in myModels.SYS_CustomerSou on tbReception.CustomerSouID equals tbCustomerSou.CustomerSouID
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
                                BalanceDateTo = tbReception.BalanceDate,//结算日期（判断）
                                Amount = tbReception.Amount,//总金额
                                AmountPaid = tbReception.AmountPaid,//应收金额
                                Describe = tbReception.Describe.Trim(),//描述
                                ToAudit = tbReception.ToAudit,//审核否
                                CompletionDates = tbReception.CompletionDate.ToString() != null ? tbReception.CompletionDate.ToString() : "0000-00-00",//完工日期
                                ToSendWork = tbReception.ToSendWork,//派工状态
                                CollageState = tbReception.CollageState.Trim(),//领料状态
                                MaintenAmount = tbReception.MaintenAmount,//维修总费
                                ToCompletion=tbReception.ToCompletion,//完工否
                            }).ToList();
            #region 拼接条件
            if (StartDate != "" && EndDate != "")
            {
                DateTime startDate = Convert.ToDateTime(StartDate);
                DateTime endDate = Convert.ToDateTime(EndDate);
                lingItem = lingItem.Where(m => m.BalanceDateTo >= startDate.Date && m.BalanceDateTo <= endDate.Date).ToList();
            }
            if (StartDate != "" && EndDate == "")
            {
                DateTime startDate = Convert.ToDateTime(StartDate);
                lingItem = lingItem.Where(m => m.BalanceDateTo >= startDate.Date).ToList();
            }
            if (StartDate == "" && EndDate != "")
            {
                DateTime endDate = Convert.ToDateTime(EndDate);
                lingItem = lingItem.Where(m => m.BalanceDateTo <= endDate.Date).ToList();
            }
            if (!string.IsNullOrEmpty(ToCompletion))
            {
                if (ToCompletion == "true")
                {
                    lingItem = lingItem.Where(m => m.ToCompletion == true).ToList();
                }
                else {
                    lingItem = lingItem.Where(m => m.ToCompletion == false).ToList();
                }
            }
            #endregion
            int count = lingItem.Count();
            List<ReceptionVo> listReception = lingItem.OrderBy(m => m.ToAudit).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<ReceptionVo> bsgrid = new Bsgrid<ReceptionVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listReception
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public class Reception
        {
            public int ReceptionID { get; set; }
        }
        public ActionResult CompletionTrue(List<Reception> ReceptionID)//完工状态修改
        {
            try
            {
                foreach (var item in ReceptionID)
                {
                    var listReception = myModels.PW_Reception.Where(m => m.ReceptionID == item.ReceptionID).Single();
                    listReception.ToCompletion = true;
                    listReception.CompletionDate = DateTime.Now;
                    listReception.DocumentStateID = 2;
                    myModels.Entry(listReception).State = System.Data.Entity.EntityState.Modified;
                    myModels.SaveChanges();
                }
               
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult CompletionFalse(List<Reception> ReceptionID)//完工状态修改
        {
            try
            {
                foreach (var item in ReceptionID)
                {
                    var listReception = myModels.PW_Reception.Where(m => m.ReceptionID == item.ReceptionID).Single();
                    listReception.ToCompletion = false;
                    listReception.CompletionDate = null;
                    listReception.DocumentStateID = 1;
                    myModels.Entry(listReception).State = System.Data.Entity.EntityState.Modified;
                    myModels.SaveChanges();
                }

            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
    }
}