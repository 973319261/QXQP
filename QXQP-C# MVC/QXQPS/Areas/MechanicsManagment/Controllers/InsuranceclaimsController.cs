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
    public class InsuranceclaimsController : Controller
    {
        // GET: MechanicsManagment/Insuranceclaims
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Insurance()//保险理赔结算
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
        [HttpPost]
        public ActionResult Insurance(int ReceptionID)//保险理赔结算
        {
            Session["ReceptionID"] = ReceptionID;
            return View();
        }
        public ActionResult ClearInsurance()//
        {
            Session["ReceptionID"] = null;
            return Json("",JsonRequestBehavior.AllowGet);
        }
        public ActionResult InsuranceNum()//生成预约单号
        {
            string InsuranceNum = "";
            var date = DateTime.Now.ToString("yyyyMMdd");
            var datetime = DateTime.Now.ToString("yyyyMMddHHmmss");
            try
            {
                var list = myModels.PW_Insurance.Where(m => m.InsuranceNum.Contains(date)).OrderBy(m => m.InsuranceNum).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().InsuranceNum.Trim().Substring(10)) + 1;
                    if (num < 10)
                    {
                        InsuranceNum = "LP" + date + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        InsuranceNum = "LP" + date + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        InsuranceNum = "LP" + date + "0" + num;
                    }
                }
                else {
                    InsuranceNum = "LP" + date + "0001";
                }
            }
            catch (Exception)
            {
                return Json(InsuranceNum, JsonRequestBehavior.AllowGet);
            }
            return Json(InsuranceNum, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectDanHao(BsgridPage bsgridPage, string MaintenanceNum, string CarNum, int DocumentStateID, int BalanceStateID, string ToAudit)//维修单号查询
        {
            try
            {
                var lingItem = (from tbInsuranceDetail in myModels.SYS_InsuranceDetail
                                join tbReception in myModels.PW_Reception on tbInsuranceDetail.ReceptionID equals tbReception.ReceptionID
                                join tbInsuranceCom in myModels.SYS_InsuranceCom on tbInsuranceDetail.InsuranceComID equals tbInsuranceCom.InsuranceComID
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
                                    InsuranceMoney = (decimal)tbInsuranceDetail.InsuranceMoney,//索赔金额
                                    InsuranceDetailID = tbInsuranceDetail.InsuranceDetailID,//索赔明细ID
                                    InsuranceComID = tbInsuranceCom.InsuranceComID,//索赔公司ID
                                    ReportNum = tbInsuranceDetail.ReportNum,//报案编号
                                    PolicyNum = tbInsuranceDetail.PolicyNum,//保单号
                                    PolicyMoney = (decimal)tbInsuranceDetail.PolicyMoney,//保单金额
                                }).ToList();
                #region 拼接条件
                if (!string.IsNullOrEmpty(MaintenanceNum))
                {
                    lingItem = lingItem.Where(m => m.MaintenanceNum.Contains(MaintenanceNum)).ToList();
                }
                if (!string.IsNullOrEmpty(CarNum))
                {
                    lingItem = lingItem.Where(m => m.CarNum == CarNum).ToList();
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
                if (DocumentStateID > 0)
                {
                    lingItem = lingItem.Where(m => m.DocumentStateID == DocumentStateID).ToList();
                }
                if (BalanceStateID > 0)
                {
                    lingItem = lingItem.Where(m => m.BalanceStateID == BalanceStateID).ToList();
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
            catch (Exception)
            {

                return Json("", JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult SelectInsurance(BsgridPage bsgridPage, string MaintenanceNum, string CarNum, int DocumentStateID, int BalanceStateID, string ToAudit)//单据查询
        {
            var lingItem = (from tbInsurance in myModels.PW_Insurance
                            join tbInsuranceDetail in myModels.SYS_InsuranceDetail on tbInsurance.InsuranceDetailID equals tbInsuranceDetail.InsuranceDetailID
                            join tbReception in myModels.PW_Reception on tbInsuranceDetail.ReceptionID equals tbReception.ReceptionID
                            join tbInsuranceCom in myModels.SYS_InsuranceCom on tbInsuranceDetail.InsuranceComID equals tbInsuranceCom.InsuranceComID
                            join tbRepair in myModels.SYS_Repair on tbReception.RepairID equals tbRepair.RepairID
                            join tbDocumentState in myModels.SYS_DocumentState on tbReception.DocumentStateID equals tbDocumentState.DocumentStateID
                            join tbBalanceState in myModels.SYS_BalanceState on tbReception.BalanceStateID equals tbBalanceState.BalanceStateID
                            join tbCustomerSou in myModels.SYS_CustomerSou on tbReception.CustomerSouID equals tbCustomerSou.CustomerSouID
                            select new ReceptionVo
                            {
                                CustomerNum = tbReception.CustomerNum.Trim(),//客户编号
                                ReceptionID = tbReception.ReceptionID,//客户接待ID
                                DocumentStateID = tbDocumentState.DocumentStateID,//单据状态ID
                                DocumentState = tbDocumentState.DocumentState.Trim(),//单据状态名称
                                BalanceStateID = tbBalanceState.BalanceStateID,//结算状态ID
                                BalanceState = tbBalanceState.BalanceState.Trim(),//结算状态名称
                                CarNum = tbReception.CarNum.Trim(),//车牌
                                VehicleType = tbReception.VehicleType.Trim(),//车型
                                MaintenanceNum = tbReception.MaintenanceNum.Trim(),//维修单号
                                Owner = tbReception.Owner.Trim(),//车主
                                OpenDates = tbInsurance.OpenDate.ToString(),//开单日期
                                balanceDates = tbInsurance.BalanceDate.ToString(),//结算日期
                                ToAudit = tbInsurance.ToAudit,//审核否
                                InsuranceID = tbInsurance.InsuranceID,//索赔ID
                                InsuranceDetailID = tbInsuranceDetail.InsuranceDetailID,//索赔明细ID
                                InsuranceComID = tbInsuranceCom.InsuranceComID,//索赔厂家ID
                                ClaimStaff = tbInsurance.ClaimsStaff.Trim(),//索赔员工
                                ToTicket = tbInsurance.ToTicket != true ? "false" : "true",//是否开票
                                InsuranceNum = tbInsurance.InsuranceNum.Trim(),//索赔单号
                                ReportNum = tbInsuranceDetail.ReportNum,//报案编号
                                PolicyNum = tbInsuranceDetail.PolicyNum,//保单号
                                PolicyMoney = (decimal)tbInsuranceDetail.PolicyMoney,//保单金额
                                Amount = tbInsurance.Amount,//总金额
                                Describe = tbInsurance.Remark.Trim(),//备注

                            }).ToList();
            #region 拼接条件
            if (!string.IsNullOrEmpty(MaintenanceNum))
            {
                lingItem = lingItem.Where(m => m.MaintenanceNum.Contains(MaintenanceNum)).ToList();
            }
            if (!string.IsNullOrEmpty(CarNum))
            {
                lingItem = lingItem.Where(m => m.CarNum == CarNum).ToList();
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
            if (DocumentStateID > 0)
            {
                lingItem = lingItem.Where(m => m.DocumentStateID == DocumentStateID).ToList();
            }
            if (BalanceStateID > 0)
            {
                lingItem = lingItem.Where(m => m.BalanceStateID == BalanceStateID).ToList();
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
        public ActionResult SelectInsuranceData(int InsuranceDetailID)//判断是否存在索赔单据
        {
            try
            {
                var lingItem = (from tbInsurance in myModels.PW_Insurance
                                join tbInsuranceDetail in myModels.SYS_InsuranceDetail on tbInsurance.InsuranceDetailID equals tbInsuranceDetail.InsuranceDetailID
                                join tbReception in myModels.PW_Reception on tbInsuranceDetail.ReceptionID equals tbReception.ReceptionID
                                join tbInsuranceCom in myModels.SYS_InsuranceCom on tbInsuranceDetail.InsuranceComID equals tbInsuranceCom.InsuranceComID
                                join tbRepair in myModels.SYS_Repair on tbReception.RepairID equals tbRepair.RepairID
                                join tbDocumentState in myModels.SYS_DocumentState on tbReception.DocumentStateID equals tbDocumentState.DocumentStateID
                                join tbBalanceState in myModels.SYS_BalanceState on tbReception.BalanceStateID equals tbBalanceState.BalanceStateID
                                join tbCustomerSou in myModels.SYS_CustomerSou on tbReception.CustomerSouID equals tbCustomerSou.CustomerSouID
                                where tbInsurance.InsuranceDetailID == InsuranceDetailID
                                select new ReceptionVo
                                {
                                    CustomerNum = tbReception.CustomerNum.Trim(),//客户编号
                                    ReceptionID = tbReception.ReceptionID,//客户接待ID
                                    DocumentStateID = tbDocumentState.DocumentStateID,//单据状态ID
                                    DocumentState = tbDocumentState.DocumentState.Trim(),//单据状态名称
                                    BalanceStateID = tbBalanceState.BalanceStateID,//结算状态ID
                                    BalanceState = tbBalanceState.BalanceState.Trim(),//结算状态名称
                                    CarNum = tbReception.CarNum.Trim(),//车牌
                                    VehicleType = tbReception.VehicleType.Trim(),//车型
                                    MaintenanceNum = tbReception.MaintenanceNum.Trim(),//维修单号
                                    Owner = tbReception.Owner.Trim(),//车主
                                    OpenDates = tbInsurance.OpenDate.ToString(),//开单日期
                                    balanceDates = tbInsurance.BalanceDate.ToString(),//结算日期
                                    ToAudit = tbInsurance.ToAudit,//审核否
                                    InsuranceID = tbInsurance.InsuranceID,//三包ID
                                    InsuranceDetailID = tbInsuranceDetail.InsuranceDetailID,//三包明细ID
                                    InsuranceComID = tbInsuranceCom.InsuranceComID,//索赔厂家ID
                                    ClaimStaff = tbInsurance.ClaimsStaff.Trim(),//索赔员工
                                    ToTicket = tbInsurance.ToTicket != true ? "false" : "true",//是否开票
                                    InsuranceNum = tbInsurance.InsuranceNum.Trim(),//索赔单号
                                    ReportNum = tbInsuranceDetail.ReportNum,//报案编号
                                    PolicyNum = tbInsuranceDetail.PolicyNum,//保单号
                                    PolicyMoney = (decimal)tbInsuranceDetail.PolicyMoney,//保单金额
                                    Amount = tbInsurance.Amount,//总金额
                                    Describe = tbInsurance.Remark.Trim(),//备注

                                }).Single();
                return Json(lingItem, JsonRequestBehavior.AllowGet);
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult SelectInsurances(int ReceptionID)
        {
            try
            {
                var lingItem = (from tbInsuranceDetail in myModels.SYS_InsuranceDetail
                                join tbReception in myModels.PW_Reception on tbInsuranceDetail.ReceptionID equals tbReception.ReceptionID
                                join tbInsuranceCom in myModels.SYS_InsuranceCom on tbInsuranceDetail.InsuranceComID equals tbInsuranceCom.InsuranceComID
                                join tbRepair in myModels.SYS_Repair on tbReception.RepairID equals tbRepair.RepairID
                                join tbDocumentState in myModels.SYS_DocumentState on tbReception.DocumentStateID equals tbDocumentState.DocumentStateID
                                join tbBalanceState in myModels.SYS_BalanceState on tbReception.BalanceStateID equals tbBalanceState.BalanceStateID
                                join tbCustomerSou in myModels.SYS_CustomerSou on tbReception.CustomerSouID equals tbCustomerSou.CustomerSouID
                                where tbReception.ReceptionID==ReceptionID
                                select new ReceptionVo
                                {
                                    CustomerNum = tbReception.CustomerNum.Trim(),//客户编号
                                    ReceptionID = tbReception.ReceptionID,//客户接待ID
                                    DocumentStateID = tbDocumentState.DocumentStateID,//单据状态ID
                                    DocumentState = tbDocumentState.DocumentState.Trim(),//单据状态名称
                                    BalanceStateID = tbBalanceState.BalanceStateID,//结算状态ID
                                    BalanceState = tbBalanceState.BalanceState.Trim(),//结算状态名称
                                    CarNum = tbReception.CarNum.Trim(),//车牌
                                    VehicleType = tbReception.VehicleType.Trim(),//车型
                                    MaintenanceNum = tbReception.MaintenanceNum.Trim(),//维修单号
                                    Owner = tbReception.Owner.Trim(),//车主
                                    InsuranceDetailID = tbInsuranceDetail.InsuranceDetailID,//索赔明细ID
                                    InsuranceComID = tbInsuranceCom.InsuranceComID,//索赔厂家ID
                                    ReportNum = tbInsuranceDetail.ReportNum,//报案编号
                                    PolicyNum = tbInsuranceDetail.PolicyNum,//保单号
                                    PolicyMoney = (decimal)tbInsuranceDetail.PolicyMoney,//保单金额
                                }).SingleOrDefault();
                return Json(lingItem, JsonRequestBehavior.AllowGet);
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult SelectReceptionDetail(int ReceptionID)//查询索赔明细信息
        {
            ArrayList lists = new ArrayList();
            try
            {
                //SYS_RecRepairItemDetail修理项目明细表
                var listRecRepairItemDetail = myModels.SYS_RecRepairItemDetail.Where(m => m.ReceptionID == ReceptionID && m.MaintainabilityID == 6).ToList();
                //SYS_RecProductDetail产品明细表
                var listCollageDetai = (from tbReception in myModels.PW_Reception
                                        join tbCollage in myModels.PW_Collage on tbReception.ReceptionID equals tbCollage.ReceptionID
                                        join tbCollageDetai in myModels.SYS_CollageDetai on tbCollage.CollageID equals tbCollageDetai.CollageID
                                        where tbReception.ReceptionID == ReceptionID && tbCollageDetai.MaintainabilityID == 6
                                        select tbCollageDetai).ToList();
                lists.Add(listRecRepairItemDetail);
                lists.Add(listCollageDetai);
            }
            catch (Exception)
            {
                throw;
            }
            return Json(lists, JsonRequestBehavior.AllowGet);
        }
        public ActionResult BavaInsurance(List<PW_Insurance> listInsurance, List<SYS_InsuranceDetail> listInsuranceDetail)//保存索赔表
        {
            var InsuranceID = 0;
            try
            {
                if (listInsurance[0].InsuranceID == 0)
                {
                    myModels.PW_Insurance.Add(listInsurance[0]);
                }
                else
                {
                    myModels.Entry(listInsurance[0]).State = System.Data.Entity.EntityState.Modified;
                }
                myModels.Entry(listInsuranceDetail[0]).State = System.Data.Entity.EntityState.Modified;
                myModels.SaveChanges();
                InsuranceID = listInsurance[0].InsuranceID;
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(InsuranceID, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ToAudit(int InsuranceID)//审核预约单
        {
            try
            {
                var list = myModels.PW_Insurance.Where(m => m.InsuranceID == InsuranceID).Single();
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
        public ActionResult ToNotAudit(int InsuranceID)//反审核预约单
        {
            try
            {
                var list = myModels.PW_Insurance.Where(m => m.InsuranceID == InsuranceID).Single();
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
        public ActionResult DelectInsurance(int InsuranceID)//删除索赔表
        {
            try
            {
                var list = myModels.PW_Insurance.Where(m => m.InsuranceID == InsuranceID).Single();
                myModels.PW_Insurance.Remove(list);
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