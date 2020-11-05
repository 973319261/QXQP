using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.ProcurementManagment.Controllers
{
    public class CustomersettleController : Controller
    {
        QXQPEntities myModels = new QXQPEntities();
        // GET: ProcurementManagment/Customersettle
        public ActionResult Customersettle()//客户结算
        {
            try
            {
                ViewBag.UserName = Session["UserName"].ToString().Trim();
                return View();
            }
            catch (Exception)
            {
                //重定向
                return Redirect("/Home/Login");
            }
        }
        public ActionResult Receipt()//生成结算单号
        {
            string Receipt = "";
            var date = DateTime.Now.ToString("yyyyMMdd");
            var datetime = DateTime.Now.ToString("yyyyMMddHHmmss");
            try
            {
                var list = myModels.PW_SalBalance.Where(m => m.Receipt.Contains(date)).OrderBy(m => m.Receipt).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().Receipt.Trim().Substring(19)) + 1;
                    if (num < 10)
                    {
                        Receipt = "AL" + datetime + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        Receipt = "AL" + datetime + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        Receipt = "AL" + datetime + "0" + num;
                    }
                }
                else
                {
                    Receipt = "AL" + datetime + "0001";
                }
            }
            catch (Exception)
            {
                return Json(Receipt, JsonRequestBehavior.AllowGet);
            }
            return Json(Receipt, JsonRequestBehavior.AllowGet);
        }
        public ActionResult Selectencipher(int SalesCustomerID)//查询单据
        {
           ArrayList lists = new ArrayList();
            try
            {
                var listOutbound = (from tbOutbound in myModels.PW_Outbound
                                      join tbSalesCustomer in myModels.SYS_SalesCustomer on tbOutbound.SalesCustomerID equals tbSalesCustomer.SalesCustomerID
                                      join tbDocumentsType in myModels.SYS_DocumentsType on tbOutbound.DocumentsTypeID equals tbDocumentsType.DocumentsTypeID
                                      join tbBalanceState in myModels.SYS_BalanceState on tbOutbound.BalanceStateID equals tbBalanceState.BalanceStateID              
                                      where tbOutbound.SalesCustomerID == SalesCustomerID && tbBalanceState.BalanceStateID == 2 && tbOutbound.Reviewwhet == true
                                    select new BingVo
                                    {
                                        DocumentsTypeID = tbDocumentsType.DocumentsTypeID,
                                        BalanceStateID = tbBalanceState.BalanceStateID,
                                        ReceipstType = tbDocumentsType.DocumentsType,//单据类型
                                        ReceipstDate = tbOutbound.OpenDate.ToString(),//单据日期                                  
                                        Oddnumbers = tbOutbound.Stockremoval,//单号(出库单号)
                                        Paymentstate = tbBalanceState.BalanceState, //结算状态(付款状况)
                                        Totalmoney = tbOutbound.Theamount.ToString(),//总金额                                     
                                    }).ToList();
                var listSalesReturnl = (from tbSalesReturnl in myModels.PW_SalesReturnl
                                        join tbSalesCustomer in myModels.SYS_SalesCustomer on tbSalesReturnl.SalesCustomerID equals tbSalesCustomer.SalesCustomerID
                                        join tbDocumentsType in myModels.SYS_DocumentsType on tbSalesReturnl.DocumentsTypeID equals tbDocumentsType.DocumentsTypeID
                                        join tbBalanceState in myModels.SYS_BalanceState on tbSalesReturnl.BalanceStateID equals tbBalanceState.BalanceStateID
                                        where tbSalesReturnl.SalesCustomerID == SalesCustomerID && tbSalesReturnl.ToAudit == true && tbBalanceState.BalanceStateID == 2 || tbBalanceState.BalanceStateID == 3
                                        select new BingVo
                                        {
                                            DocumentsTypeID = tbDocumentsType.DocumentsTypeID,
                                            BalanceStateID = tbBalanceState.BalanceStateID,
                                            ReceipstType = tbDocumentsType.DocumentsType,//单据类型
                                            ReceipstDate = tbSalesReturnl.OpenDate.ToString(),//单据日期                                       
                                            Oddnumbers = tbSalesReturnl.Oddseats,//单号(退货单号)
                                            Paymentstate = tbBalanceState.BalanceState,//结算状态(付款状况)
                                            Totalmoney = tbSalesReturnl.Theamount.ToString(),//总金额                                         
                                        }).ToList();
                lists.AddRange(listOutbound);
                lists.AddRange(listSalesReturnl);
            }
            catch (Exception)
            {
                return Json("", JsonRequestBehavior.AllowGet);
            }
           
            return Json(lists, JsonRequestBehavior.AllowGet);
        }


        public ActionResult Selectenciphers(BsgridPage bsgriPage, string Receipt, string ToAudit)//查询结算单
        {
            var listSalBalance = (from tbSalBalance in myModels.PW_SalBalance
                                  join tbSalesCustomer in myModels.SYS_SalesCustomer on tbSalBalance.SalesCustomerID equals tbSalesCustomer.SalesCustomerID
                                  orderby tbSalBalance.SalBalanceID descending
                                  select new SalBalanceVo
                                  {
                                      SalBalanceID = tbSalBalance.SalBalanceID,//结算单ID
                                      SalesCustomerID = tbSalesCustomer.SalesCustomerID,//客户ID
                                      CustomerCode = tbSalesCustomer.CustomerCode,//客户编码
                                      CustomerName = tbSalesCustomer.CustomerName,//客户名称
                                      ToAudit = tbSalBalance.ToAudit,//审核
                                      Receipt = tbSalBalance.Receipt,//单号
                                      Operator = tbSalBalance.Operator,//操作人
                                      ReceiptDates = tbSalBalance.ReceiptDate.ToString(),//单据日期
                                      ReceiptDate = tbSalBalance.ReceiptDate,//单据日期   
                                      CurrentMany = tbSalBalance.CurrentMany//收款金额
                                  }).ToList();
            #region 条件查询
            //单号
            if (!string.IsNullOrEmpty(Receipt))
            {

                listSalBalance = listSalBalance.Where(m => m.Receipt.Contains(Receipt)).ToList();//模糊查询
            }
            // 审核
            if (ToAudit == "1")
            {
                listSalBalance = listSalBalance.Where(m => m.ToAudit == true).ToList();
            }
            else if (ToAudit == "2")
            {
                listSalBalance = listSalBalance.Where(m => m.ToAudit == false).ToList();
            }
            #endregion

            //获取当前查询出来的数据的条数
            var totalCount = listSalBalance.Count();
            List<SalBalanceVo> listItem = listSalBalance
                                          .Skip(bsgriPage.GetStartIndex()) //Skip取数据的位置
                                          .Take(bsgriPage.pageSize)  //Take取数据的条数
                                          .ToList();
            Bsgrid<SalBalanceVo> bsgrid = new Bsgrid<SalBalanceVo>()   //实例化对象
            {
                success = true,
                totalRows = totalCount,
                curPage = bsgriPage.curPage,
                data = listItem
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }

        public ActionResult Insertencipher(List<PW_SalBalance> ListSalBalance, List<SYS_SalBalancetDetail> ListSalesorderDetai)//新增结算单
        {
            var SalBalanceID = 0;//声明一个变量    
            var tt = 0;
            try
            {
                //结算单
                if (ListSalBalance[0].SalBalanceID == 0)
                {
                    //新增
                    myModels.PW_SalBalance.Add(ListSalBalance[0]);
                    tt = myModels.SaveChanges();//赋值
                    SalBalanceID = ListSalBalance[0].SalBalanceID;
                }
                else
                {
                    //修改
                    myModels.Entry(ListSalBalance[0]).State = System.Data.Entity.EntityState.Modified;                
                    myModels.SaveChanges();
                }
                if (tt > 0)
                {
                    ListSalesorderDetai[0].SalBalanceID = ListSalBalance[0].SalBalanceID;

                    if (ListSalesorderDetai[0].SalBalancetDetailID == 0)
                    {

                        myModels.SYS_SalBalancetDetail.Add(ListSalesorderDetai[0]); //新增
                        myModels.SaveChanges();
                    }
                    else
                    {                   
                        myModels.Entry(ListSalesorderDetai[0]).State = System.Data.Entity.EntityState.Modified;//修改
                        myModels.SaveChanges();
                    }
                    myModels.SaveChanges();
                }

            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }

            return Json(SalBalanceID, JsonRequestBehavior.AllowGet);
        }

        public ActionResult DelectSalesorder(int SalBalanceID)//删除结算单
        {
            try
            {
                var listSalBalance = myModels.PW_SalBalance.Where(m => m.SalBalanceID == SalBalanceID).Single();//结算表
                var listSalBalancetDetail = myModels.SYS_SalBalancetDetail.Where(m => m.SalBalancetDetailID == SalBalanceID).ToList();//结算明细表
                myModels.PW_SalBalance.Remove(listSalBalance);
                myModels.SYS_SalBalancetDetail.RemoveRange(listSalBalancetDetail);
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }

        //public ActionResult SelectSalesorderDetai()
        //{
        //    var listSalBalancetDetail = (from tbSalBalancetDetail in myModels.SYS_SalBalancetDetail
        //                               join tbBalanceState in myModels.SYS_BalanceState on tbSalBalancetDetail.BalanceStateID equals tbBalanceState.BalanceStateID//单据类型
        //                               join tbDocumentsType in myModels.SYS_DocumentsType on tbSalBalancetDetail.DocumentsTypeID equals tbDocumentsType.DocumentsTypeID//结算状态
        //                                 orderby tbSalBalancetDetail.SalBalancetDetailID descending
        //                                 select new
        //                                 {

        //                                 });



        //    return Json("", JsonRequestBehavior.AllowGet);
        //}


    }
}