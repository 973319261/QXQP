using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.AccessoriessalesManagment.Controllers
{
    public class CargosettlementController : Controller
    {
        // GET: AccessoriessalesManagment/Cargosettlement

        QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Cargosettlement()//货商结算
        {
            try
            {
                ViewBag.UserName = Session["UserName"].ToString().Trim();//登录用户
                return View();
            }
            catch (Exception)
            {
                return Redirect("/Home/Login");
            }
        }

        public ActionResult MaintenanceNum()//结算单号
        {
            string MaintenanceNum = "";
            var date = "P" + DateTime.Now.ToString("yyyyMMdd");
            try
            {
                var list = myModels.PW_ProBalance.Where(m => m.Receipt.Contains(date)).OrderBy(m => m.Receipt).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().Receipt.Trim().Substring(9)) + 1;
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
                else
                {
                    MaintenanceNum = date + "0001";
                }
            }
            catch (Exception)
            {
                return Json("", JsonRequestBehavior.AllowGet);
            }
            return Json(MaintenanceNum, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SelectProBalancetDetail(int SuppliersID)//查询供应商账单
        {
            ArrayList list = new ArrayList();
            try
            {
                var listIncoming = (from tbIncoming in myModels.PW_Incoming
                                    join tbSuppliers in myModels.SYS_Suppliers on tbIncoming.SuppliersID equals tbSuppliers.SuppliersID
                                    join tbDocumentsType in myModels.SYS_DocumentsType on tbIncoming.DocumentsTypeID equals tbDocumentsType.DocumentsTypeID
                                    join tbBalanceState in myModels.SYS_BalanceState on tbIncoming.BalanceStateID equals tbBalanceState.BalanceStateID
                                    where tbIncoming.SuppliersID == SuppliersID && tbBalanceState.BalanceStateID == 2 && tbIncoming.ToAudit == true
                                    select new GppVo
                                    {
                                        SingleNumber=tbIncoming.IncomingNumber,//单号
                                        DocumentsTypeID=tbDocumentsType.DocumentsTypeID,
                                        BalanceStateID = tbBalanceState.BalanceStateID,
                                        DocumentsDate = tbIncoming.BilDate.ToString(),//单据日期
                                        ShouldAmount = tbIncoming.Aamount.ToString(),//总金额
                                        DocumentsType = tbDocumentsType.DocumentsType,//单据类型
                                        BalanceState = tbBalanceState.BalanceState//付款状态
                                    }).ToList();
                var listReturnForm = (from tbReturnForm in myModels.PW_ReturnForm
                                      join tbSuppliers in myModels.SYS_Suppliers on tbReturnForm.SuppliersID equals tbSuppliers.SuppliersID
                                      join tbDocumentsType in myModels.SYS_DocumentsType on tbReturnForm.DocumentsTypeID equals tbDocumentsType.DocumentsTypeID
                                      join tbBalanceState in myModels.SYS_BalanceState on tbReturnForm.BalanceStateID equals tbBalanceState.BalanceStateID
                                      where tbReturnForm.SuppliersID == SuppliersID && tbReturnForm.BalanceStateID == 2 && tbReturnForm.ToAudit == true
                                      select new GppVo
                                      {
                                          SingleNumber=tbReturnForm.ReturnNumber,//单号
                                          DocumentsTypeID = tbDocumentsType.DocumentsTypeID,
                                          BalanceStateID = tbBalanceState.BalanceStateID,
                                          DocumentsDate = tbReturnForm.SheetDate.ToString(),//单据日期
                                          ShouldAmount = tbReturnForm.Amount.ToString(),//总金额
                                          DocumentsType = tbDocumentsType.DocumentsType,//单据类型
                                          BalanceState = tbBalanceState.BalanceState//付款状态
                                      }).ToList();
                list.AddRange(listIncoming);
                list.AddRange(listReturnForm);
            }
            catch (Exception)
            {
                return Json("", JsonRequestBehavior.AllowGet);
            }
            return Json(list, JsonRequestBehavior.AllowGet);

        }
        public ActionResult SelectProBalance(BsgridPage bsgridPage/*, string ReceiptNum, string ToAudit*/)
        {
            var listProBalance = (from tbProBalance in myModels.PW_ProBalance
                                  join tbSuppli in myModels.SYS_Suppliers on tbProBalance.SuppliersID equals tbSuppli.SuppliersID
                                  join tbPaymen in myModels.SYS_Payment on tbProBalance.PaymentID equals tbPaymen.PaymentID
                                  select new ProBalanceVo
                                  {
                                      ProBalanceID = tbProBalance.ProBalanceID,
                                      SuppliersID = tbSuppli.SuppliersID,
                                      PaymentID = tbPaymen.PaymentID,
                                      SuppliersName = tbSuppli.SuppliersName.Trim(),
                                      SuppliersFirm = tbSuppli.SuppliersFirm.Trim(),
                                      Address = tbSuppli.Address.Trim(),
                                      OpenBank = tbSuppli.OpenBank.Trim(),
                                      TaxNumber = tbSuppli.TaxNumber.Trim(),
                                      PaymentName = tbPaymen.PaymentName,
                                      Receipt = tbProBalance.Receipt,
                                      AccountNumber = tbProBalance.AccountNumber != null ? tbProBalance.AccountNumber : "",
                                      ReceiptDates = tbProBalance.ReceiptDate.ToString(),
                                      ReceiptDate = tbProBalance.ReceiptDate,
                                      Operator = tbProBalance.Operator,
                                      CurrentMany = tbProBalance.CurrentMany,
                                      Auditor = tbProBalance.Auditor,
                                      AuditDates = tbProBalance.AuditDate.ToString(),
                                      AuditDate = tbProBalance.AuditDate,
                                      ToAudit = tbProBalance.ToAudit,
                                      Remark = tbProBalance.Remark != null ? tbProBalance.Remark : "",
                                  }).ToList();
            //if (!string.IsNullOrEmpty(ReceiptNum))
            //{
            //    listProBalance = listProBalance.Where(m => m.Receipt.Contains(ReceiptNum)).ToList();//单号查询
            //}
            //if (ToAudit == "1")
            //{
            //    listProBalance = listProBalance.Where(m => m.ToAudit == true).ToList();// 审核
            //}
            //else if (ToAudit == "2")
            //{
            //    listProBalance = listProBalance.Where(m => m.ToAudit == false).ToList();
            //}
            int count = listProBalance.Count();
            List<ProBalanceVo> listPro = listProBalance.Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<ProBalanceVo> bsgrid = new Bsgrid<ProBalanceVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listPro,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult listProBalant(List<PW_ProBalance>listProBalance, List<SYS_ProBalancetDetail> listProBalancetDetail)
        {
            Session["listProBalancetDetail"] = listProBalancetDetail;
            var ProBalanceID = 0;
            try
            {
                if (listProBalance[0].ProBalanceID == 0)
                {
                    myModels.PW_ProBalance.Add(listProBalance[0]);
                }
                else
                {
                    myModels.Entry(listProBalance[0]).State = System.Data.Entity.EntityState.Modified;
                }
                if (myModels.SaveChanges() > 0)
                {
                    ProBalanceID = listProBalance[0].ProBalanceID;
                    for (int i = 0; i < listProBalancetDetail.Count(); i++)
                    {
                        listProBalancetDetail[i].ProBalanceID = ProBalanceID;
                        var ProBalancetDetailID = listProBalancetDetail[i].ProBalancetDetailID;
                        if (ProBalancetDetailID == 0)
                        {
                            myModels.SYS_ProBalancetDetail.Add(listProBalancetDetail[i]);
                        }
                        else
                        {
                            myModels.Entry(listProBalancetDetail[i]).State = System.Data.Entity.EntityState.Modified;
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
            return Json(ProBalanceID, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SelectProBalanceDetail(int ProBalanceID)//查询结算明细数据
        {
            var listBalanDetail = (from tbProcurDetail in myModels.SYS_ProBalancetDetail
                                   join tbBalanceState in myModels.SYS_BalanceState on tbProcurDetail.BalanceStateID equals tbBalanceState.BalanceStateID
                                   join tbDocumentsType in myModels.SYS_DocumentsType on tbProcurDetail.DocumentsTypeID equals tbDocumentsType.DocumentsTypeID
                                   where tbProcurDetail.ProBalanceID == ProBalanceID
                                   select new ProBalatDetailVo
                                   {
                                       ProBalancetDetailID = tbProcurDetail.ProBalancetDetailID,
                                       BalanceStateID = tbBalanceState.BalanceStateID,
                                       DocumentsTypeID = tbDocumentsType.DocumentsTypeID,
                                       BalanceState = tbBalanceState.BalanceState,
                                       DocumentsType = tbDocumentsType.DocumentsType,
                                       ToCleared = tbProcurDetail.ToCleared,
                                       SingleNumber = tbProcurDetail.SingleNumber,
                                       DocumentsDates = tbProcurDetail.DocumentsDate.ToString(),
                                       DocumentsDate = tbProcurDetail.DocumentsDate,
                                       ShouldAmount = tbProcurDetail.ShouldAmount,
                                       PaidAmount = tbProcurDetail.PaidAmount,
                                       SuperioAmountr = tbProcurDetail.SuperioAmountr,
                                       ThisSuperioAmountr = tbProcurDetail.ThisSuperioAmountr,
                                       ThisShouldAmount = tbProcurDetail.ThisShouldAmount,
                                       Remark = tbProcurDetail.Remark,
                                   }).ToList();
            return Json(listBalanDetail, JsonRequestBehavior.AllowGet);
    }

        public ActionResult DeleteProBalance(int ProBalanceID)//删除采购订单
        {
            try
            {
                var listProcur = myModels.PW_ProBalance.Where(m => m.ProBalanceID == ProBalanceID).Single();
                var listproDetail = myModels.SYS_ProBalancetDetail.Where(m => m.ProBalanceID == ProBalanceID).ToList();
                myModels.PW_ProBalance.Remove(listProcur);
                myModels.SYS_ProBalancetDetail.RemoveRange(listproDetail);
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