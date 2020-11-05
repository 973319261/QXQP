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
    public class ClaimsettlementController : Controller
    {
        // GET: MechanicsManagment/Claimsettlement
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Claimset()//三包索赔结算
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
        public ActionResult Claimset(int ReceptionID)//保险理赔结算
        {
            Session["ReceptionID"] = ReceptionID;
            return View();
        }
        public ActionResult ClearClaimset()//
        {
            Session["ReceptionID"] = null;
            return Json("", JsonRequestBehavior.AllowGet);
        }
        public ActionResult InsuranceNum()//生成预约单号
        {
            string InsuranceNum = "";
            var date = DateTime.Now.ToString("yyyyMMdd");
            var datetime = DateTime.Now.ToString("yyyyMMddHHmmss");
            try
            {
                var list = myModels.PW_ThreePacks.Where(m => m.InsuranceNum.Contains(date)).OrderBy(m => m.InsuranceNum).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().InsuranceNum.Trim().Substring(10)) + 1;
                    if (num < 10)
                    {
                        InsuranceNum = "SP" + date + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        InsuranceNum = "SP" + date + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        InsuranceNum = "SP" + date + "0" + num;
                    }
                }
                else {
                    InsuranceNum = "SP" + date + "0001";
                }
            }
            catch (Exception)
            {
                return Json(InsuranceNum, JsonRequestBehavior.AllowGet);
            }
            return Json(InsuranceNum, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectDanHao(BsgridPage bsgridPage, string MaintenanceNum,string CarNum,int DocumentStateID,int BalanceStateID,string ToAudit)//维修单号查询
        {
            var lingItem = (from tbThreePacksDetail in myModels.SYS_ThreePacksDetail
                            join tbReception in myModels.PW_Reception on tbThreePacksDetail.ReceptionID equals tbReception.ReceptionID
                            join tbClaimCom in myModels.SYS_ClaimCom on tbThreePacksDetail.ClaimComID equals tbClaimCom.ClaimComID
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
                                ClaimMoney = (decimal)tbThreePacksDetail.ClaimMoney,
                                ThreePacksDetailID = tbThreePacksDetail.ThreePacksDetailID,//三包明细ID
                                ClaimComID=tbClaimCom.ClaimComID,//索赔厂家ID
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
        public ActionResult SelectThreePacks(BsgridPage bsgridPage, string MaintenanceNum, string CarNum, int DocumentStateID, int BalanceStateID, string ToAudit)//单据查询
        {
            var lingItem = (from tbThreePacks in myModels.PW_ThreePacks
                            join tbThreePacksDetail in myModels.SYS_ThreePacksDetail on tbThreePacks.ThreePacksDetailID equals tbThreePacksDetail.ThreePacksDetailID
                            join tbReception in myModels.PW_Reception on tbThreePacksDetail.ReceptionID equals tbReception.ReceptionID
                            join tbClaimCom in myModels.SYS_ClaimCom on tbThreePacksDetail.ClaimComID equals tbClaimCom.ClaimComID
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
                                OpenDates = tbThreePacks.OpenDate.ToString(),//开单日期
                                balanceDates = tbThreePacks.BalanceDate.ToString(),//结算日期
                                ToAudit = tbThreePacks.ToAudit,//审核否
                                ThreePacksID=tbThreePacks.ThreePacksID,//三包ID
                                ThreePacksDetailID = tbThreePacksDetail.ThreePacksDetailID,//三包明细ID
                                ClaimComID = tbClaimCom.ClaimComID,//索赔厂家ID
                                ClaimStaff=tbThreePacks.ClaimsStaff.Trim(),//索赔员工
                                SelfCoding=tbThreePacks.SelfCoding.Trim(),//自编号
                                ToTicket = tbThreePacks.ToTicket != true ? "false" : "true",//是否开票
                                InsuranceNum =tbThreePacks.InsuranceNum.Trim(),//索赔单号
                                Amount=tbThreePacks.Amount,//总金额
                                Operator=tbThreePacks.Operator.Trim(),//操作人
                                Describe=tbThreePacks.Remark.Trim(),//备注

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
        public ActionResult SelectThreePacksData(int ThreePacksDetailID)//判断是否存在三包单据
        {
            try
            {
                var lingItem = (from tbThreePacks in myModels.PW_ThreePacks
                                join tbThreePacksDetail in myModels.SYS_ThreePacksDetail on tbThreePacks.ThreePacksDetailID equals tbThreePacksDetail.ThreePacksDetailID
                                join tbReception in myModels.PW_Reception on tbThreePacksDetail.ReceptionID equals tbReception.ReceptionID
                                join tbClaimCom in myModels.SYS_ClaimCom on tbThreePacksDetail.ClaimComID equals tbClaimCom.ClaimComID
                                join tbRepair in myModels.SYS_Repair on tbReception.RepairID equals tbRepair.RepairID
                                join tbDocumentState in myModels.SYS_DocumentState on tbReception.DocumentStateID equals tbDocumentState.DocumentStateID
                                join tbBalanceState in myModels.SYS_BalanceState on tbReception.BalanceStateID equals tbBalanceState.BalanceStateID
                                join tbCustomerSou in myModels.SYS_CustomerSou on tbReception.CustomerSouID equals tbCustomerSou.CustomerSouID
                                where tbThreePacks.ThreePacksDetailID == ThreePacksDetailID
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
                                    OpenDates = tbThreePacks.OpenDate.ToString(),//开单日期
                                    balanceDates = tbThreePacks.BalanceDate.ToString(),//结算日期
                                    ToAudit = tbThreePacks.ToAudit,//审核否
                                    ThreePacksID = tbThreePacks.ThreePacksID,//三包ID
                                    ThreePacksDetailID = tbThreePacksDetail.ThreePacksDetailID,//三包明细ID
                                    ClaimComID = tbClaimCom.ClaimComID,//索赔厂家ID
                                    ClaimStaff = tbThreePacks.ClaimsStaff.Trim(),//索赔员工
                                    SelfCoding = tbThreePacks.SelfCoding.Trim(),//自编号
                                    ToTicket = tbThreePacks.ToTicket != true ? "false" : "true",//是否开票
                                    InsuranceNum = tbThreePacks.InsuranceNum.Trim(),//索赔单号
                                    Amount = tbThreePacks.Amount,//总金额
                                    Operator = tbThreePacks.Operator.Trim(),//操作人
                                    Describe = tbThreePacks.Remark.Trim(),//备注

                                }).Single();
                return Json(lingItem, JsonRequestBehavior.AllowGet);
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult SelectThreePackss(int ReceptionID)
        {
            try
            {
                var lingItem = (from tbThreePacksDetail in myModels.SYS_ThreePacksDetail 
                                join tbReception in myModels.PW_Reception on tbThreePacksDetail.ReceptionID equals tbReception.ReceptionID
                                join tbClaimCom in myModels.SYS_ClaimCom on tbThreePacksDetail.ClaimComID equals tbClaimCom.ClaimComID
                                join tbRepair in myModels.SYS_Repair on tbReception.RepairID equals tbRepair.RepairID
                                join tbDocumentState in myModels.SYS_DocumentState on tbReception.DocumentStateID equals tbDocumentState.DocumentStateID
                                join tbBalanceState in myModels.SYS_BalanceState on tbReception.BalanceStateID equals tbBalanceState.BalanceStateID
                                join tbCustomerSou in myModels.SYS_CustomerSou on tbReception.CustomerSouID equals tbCustomerSou.CustomerSouID
                                where tbReception.ReceptionID == ReceptionID
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
                                    ThreePacksDetailID = tbThreePacksDetail.ThreePacksDetailID,//三包明细ID
                                    ClaimComID = tbClaimCom.ClaimComID,//索赔厂家ID

                                }).SingleOrDefault();
                return Json(lingItem, JsonRequestBehavior.AllowGet);
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult SelectReceptionDetail(int ReceptionID)//查询三包明细信息
        {
            ArrayList lists = new ArrayList();
            try
            {
                //SYS_RecRepairItemDetail修理项目明细表
                var listRecRepairItemDetail = myModels.SYS_RecRepairItemDetail.Where(m => m.ReceptionID == ReceptionID && (m.MaintainabilityID==3 || m.MaintainabilityID == 4)).ToList();
                //SYS_RecProductDetail产品明细表
                var listCollageDetai = (from tbReception in myModels.PW_Reception
                                       join tbCollage in myModels.PW_Collage on tbReception.ReceptionID equals tbCollage.ReceptionID
                                       join tbCollageDetai in myModels.SYS_CollageDetai on tbCollage.CollageID equals tbCollageDetai.CollageID
                                       where tbReception.ReceptionID == ReceptionID && (tbCollageDetai.MaintainabilityID == 3 || tbCollageDetai.MaintainabilityID == 4)
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
        public ActionResult BavaThreePacks(List<PW_ThreePacks> listThreePacks,int ClaimComID)//保存三包表
        {
            var ThreePacksID = 0;
            try
            {
                if (listThreePacks[0].ThreePacksID == 0)
                {
                    myModels.PW_ThreePacks.Add(listThreePacks[0]);
                }
                else {
                    myModels.Entry(listThreePacks[0]).State = System.Data.Entity.EntityState.Modified;
                }
                var ThreePacksDetailID = listThreePacks[0].ThreePacksDetailID;
                var listThreePacksDetail = myModels.SYS_ThreePacksDetail.Where(m => m.ThreePacksDetailID == ThreePacksDetailID).Single();
                listThreePacksDetail.ClaimComID = ClaimComID;
                myModels.Entry(listThreePacksDetail).State = System.Data.Entity.EntityState.Modified;
                myModels.SaveChanges();
                ThreePacksID = listThreePacks[0].ThreePacksID;
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(ThreePacksID, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ToAudit(int ThreePacksID)//审核预约单
        {
            try
            {
                var list = myModels.PW_ThreePacks.Where(m => m.ThreePacksID == ThreePacksID).Single();
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
        public ActionResult ToNotAudit(int ThreePacksID)//反审核预约单
        {
            try
            {
                var list = myModels.PW_ThreePacks.Where(m => m.ThreePacksID == ThreePacksID).Single();
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
        public ActionResult DelectThreePacks(int ThreePacksID)//删除三包表
        {
            try
            {
                var list = myModels.PW_ThreePacks.Where(m => m.ThreePacksID == ThreePacksID).Single();
                myModels.PW_ThreePacks.Remove(list);
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectBalance(string BusinessNum)//查询单据付款状态
        {
            ReturnJson returnJson = new ReturnJson();
            try
            {
                var list = myModels.PW_Balance.Where(m => m.BusinessNum == BusinessNum).SingleOrDefault();
                if (list != null)
                {
                    if (list.BalanceStateID == 1)
                    {
                        returnJson.State = false;
                        returnJson.Text = "您和该用户没有付款信息，或你们往来账已平衡！";
                        returnJson.ObjData = "该单已有付款记录，不能反审核，如要更改费用，请到财务管理客户费用中进行平衡处理。";
                    }
                    else if (list.BalanceStateID == 2)
                    {
                        returnJson.State = true;
                        returnJson.States = "A";
                        returnJson.Text = list.BalanceID.ToString();
                        returnJson.ObjData = "该单已有付款记录，不能反审核，如要更改费用，请到财务管理客户费用中进行平衡处理。";
                    }
                }
                else {
                    returnJson.State = true;
                }
            }
            catch (Exception)
            {
                returnJson.State = false;
                returnJson.Text = "数据异常!";
            }
            return Json(returnJson, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectBalances(int BalanceID)//查询付款数据
        {
            try
            {
                var list = myModels.PW_Balance.Where(m => m.BalanceID == BalanceID).SingleOrDefault();
                return Json(list, JsonRequestBehavior.AllowGet);
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult BaveBalance(List<PW_Balance> listBalance)//保存结算单
        {
            try
            {
                var BalanceID = listBalance[0].BalanceID;
                var ReceptionID = listBalance[0].ReceptionID;
                var ShouldAmount = listBalance[0].ShouldAmount;//应收金额

                if (BalanceID != 0)
                {
                    var oldBalance = myModels.PW_Balance.Where(m => m.BalanceID == BalanceID).Single();//原来的结算单
                    oldBalance.OptimalAmount+= listBalance[0].OptimalAmount;//优惠金额
                    oldBalance.OptimalAmount+= listBalance[0].CollectionAmount;//收款金额
                    if (ShouldAmount <= oldBalance.OptimalAmount + oldBalance.CollectionAmount)
                    {
                        oldBalance.BalanceStateID = 1;//付讫（已清）
                    }
                    else
                    {
                        oldBalance.BalanceStateID = 2;//挂账（部分）
                    }
                    myModels.Entry(oldBalance).State = System.Data.Entity.EntityState.Modified;
                }
                else {
                    var OptimalAmount = listBalance[0].OptimalAmount;//优惠金额
                    var CollectionAmount = listBalance[0].CollectionAmount;//收款金额
                    listBalance[0].DocumentsTypeID = 2;
                    if (ShouldAmount <= OptimalAmount + CollectionAmount)
                    {
                        listBalance[0].BalanceStateID = 1;//付讫（已清）
                    }
                    else
                    {
                        listBalance[0].BalanceStateID = 2;//挂账（部分）
                    }
                    myModels.PW_Balance.Add(listBalance[0]);
                }
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