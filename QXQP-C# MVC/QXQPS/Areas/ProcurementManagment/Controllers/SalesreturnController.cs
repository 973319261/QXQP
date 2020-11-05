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
    public class SalesreturnController : Controller
    {
        QXQPEntities myModels = new QXQPEntities();
        // GET: ProcurementManagment/Salesreturn
        public ActionResult Salesreturn()//销售退货
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
        public ActionResult Oddseats()//生成退货单号
        {
            string Oddseats = "";
            var date = DateTime.Now.ToString("yyyyMMdd");
            try
            {
                var list = myModels.PW_SalesReturnl.Where(m => m.Oddseats.Contains(date)).OrderBy(m => m.Oddseats).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().Oddseats.Trim().Substring(12)) + 1;
                    if (num < 10)
                    {
                        Oddseats = "LST" + date + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        Oddseats = "LST" + date + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        Oddseats = "LST" + date + "0" + num;
                    }
                }
                else
                {
                    Oddseats = "LST" + date + "0001";
                }
            }
            catch (Exception)
            {
                return Json(Oddseats, JsonRequestBehavior.AllowGet);
            }
            return Json(Oddseats, JsonRequestBehavior.AllowGet);
        }
        public ActionResult InsertSalesReturnl(List<PW_SalesReturnl> ListSalesReturnl, List<SYS_SalesReturnDetails> listSalesReturnDetails)//新增&修改退货单
        {
            var SalesReturnlID = ListSalesReturnl[0].SalesReturnlID;
            try
            {
                //退货表
                if (SalesReturnlID == 0)
                {
                    //新增          
                    ListSalesReturnl[0].DocumentsTypeID = 5;//单据类型
                    ListSalesReturnl[0].BalanceStateID = 3;//结算状态（入账状况）
                    myModels.PW_SalesReturnl.Add(ListSalesReturnl[0]);
                }
                else
                {
                    //修改
                    var list = myModels.PW_SalesReturnl.Where(m => m.SalesReturnlID == SalesReturnlID).Select(m=>new {
                        m.DocumentsTypeID,
                        m.BalanceStateID
                    }).SingleOrDefault();
                    ListSalesReturnl[0].DocumentsTypeID = list.BalanceStateID;//单据类型
                    ListSalesReturnl[0].BalanceStateID = list.DocumentsTypeID;//结算状态（入账状况）
                    myModels.Entry(ListSalesReturnl[0]).State = System.Data.Entity.EntityState.Modified;
                }
                //退货明细表
                if (myModels.SaveChanges() > 0)
                {
                    SalesReturnlID = ListSalesReturnl[0].SalesReturnlID;
                    //声明列表 
                    List<int> oldID = new List<int>(); //原ID
                    List<int> newID = new List<int>(); //新ID
                    List<int> listdelectID = new List<int>(); //需要删除的ID集合
                    var list = myModels.SYS_SalesReturnDetails.Where(m => m.SalesReturnlID == SalesReturnlID).Select(m => new { m.SalesReturnDetailsID}).ToList();
                    //遍历列表
                    foreach (var item in list)
                    {
                        oldID.Add(item.SalesReturnDetailsID);
                    }
                    if (listSalesReturnDetails != null)
                    {
                        for (int i = 0; i < listSalesReturnDetails.Count; i++)
                        {
                            listSalesReturnDetails[i].SalesReturnlID = ListSalesReturnl[0].SalesReturnlID;  //退货ID
                            if (listSalesReturnDetails[i].SalesReturnDetailsID == 0)
                            {
                                myModels.SYS_SalesReturnDetails.Add(listSalesReturnDetails[i]); //新增
                            }
                            else
                            {
                                newID.Add(listSalesReturnDetails[i].SalesReturnDetailsID);
                                myModels.Entry(listSalesReturnDetails[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项(差集) 
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_SalesReturnDetails.Where(m => m.SalesReturnDetailsID == item).SingleOrDefault();//删除
                            myModels.SYS_SalesReturnDetails.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_SalesReturnDetails.Where(m => m.SalesReturnlID == SalesReturnlID).ToList();//删除全部
                        myModels.SYS_SalesReturnDetails.RemoveRange(listdelect);
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
            return Json(SalesReturnlID, JsonRequestBehavior.AllowGet);
        }
        public ActionResult DelectSalesReturnl(int SalesReturnlID)//删除单
        {
            try
            {
                var listSalesReturnl = myModels.PW_SalesReturnl.Where(m => m.SalesReturnlID == SalesReturnlID).Single();//出货表
                var listSalesReturnDetails = myModels.SYS_SalesReturnDetails.Where(m => m.SalesReturnlID == SalesReturnlID).ToList();//出货明细表
                myModels.PW_SalesReturnl.Remove(listSalesReturnl);
                myModels.SYS_SalesReturnDetails.RemoveRange(listSalesReturnDetails);
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ToReviewwhet(int SalesReturnlID, string Stockremoval)//审核退货单
        {
            ArrayList lists = new ArrayList();
            try
            {  //审核         
                var Auditor = Session["UserName"].ToString().Trim();//审核人             
                var AuditDate = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");//审核时间             
                lists.Add(Auditor);
                lists.Add(AuditDate.ToString());


                var list = myModels.PW_SalesReturnl.Where(m => m.SalesReturnlID == SalesReturnlID).Single();
                list.SalesReturnlID = list.SalesReturnlID;
                list.ToAudit = true;//审核否
                list.Auditor = Session["UserName"].ToString();//审核人
                list.AuditDate = DateTime.Now;//审核时间
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;

                //已退货
                if (myModels.SaveChanges() > 0)
                {
                    if (Stockremoval != "")
                    {
                        var Salesorde = myModels.PW_Outbound.Where(m => m.Stockremoval == Stockremoval).SingleOrDefault();
                        Salesorde.DocuState = "已退货";
                        myModels.Entry(Salesorde).State = System.Data.Entity.EntityState.Modified;
                        myModels.SaveChanges();
                    };
                }              
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(lists, JsonRequestBehavior.AllowGet);
        }
        public ActionResult NoToReviewwhet(int SalesReturnlID, string Stockremoval)//反审核退货单
        {
            try
            {
                var list = myModels.PW_SalesReturnl.Where(m => m.SalesReturnlID == SalesReturnlID).Single();
                list.SalesReturnlID = list.SalesReturnlID;
                list.ToAudit = false;//审核否
                list.Auditor = "";//审核人    
                list.AuditDate = null; //审核时间             
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                //未退货
                if (myModels.SaveChanges() > 0)
                {
                    if (Stockremoval != null)
                    {
                        var Salesorde = myModels.PW_Outbound.Where(m => m.Stockremoval == Stockremoval).SingleOrDefault();
                        Salesorde.DocuState = "未退货";
                        myModels.Entry(Salesorde).State = System.Data.Entity.EntityState.Modified;
                    };
                }
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectSalesReturnl(BsgridPage bsgriPage, string StarAuditdate, string EndAuditdate, string Oddseats, string ToAudit)//查询单据
        {
            var listSalesReturnl = (from tbSalesReturnl in myModels.PW_SalesReturnl
                                    join tbSalesCustomer in myModels.SYS_SalesCustomer on tbSalesReturnl.SalesCustomerID equals tbSalesCustomer.SalesCustomerID //销售客户  
                                    orderby tbSalesReturnl.SalesReturnlID descending
                                    select new SalesReturnlVo
                                    {
                                        SalesReturnlID = tbSalesReturnl.SalesReturnlID,//退货ID
                                        SalesCustomerID = tbSalesCustomer.SalesCustomerID,//客户ID
                                        CustomerCode = tbSalesCustomer.CustomerCode,//客户编码
                                        CustomerName = tbSalesCustomer.CustomerName,//客户名称
                                        Oddseats = tbSalesReturnl.Oddseats,//退货单号
                                        Stockremoval = tbSalesReturnl.Stockremoval,//出货单号
                                        OpenDate = tbSalesReturnl.OpenDate,//开单日期
                                        OpenDates = tbSalesReturnl.OpenDate.ToString(),//开单日期(格式转换)
                                        Returnmode = tbSalesReturnl.Returnmode,//退货方式
                                        Operator = tbSalesReturnl.Operator,//操作人
                                        ToAudit = tbSalesReturnl.ToAudit,//审核否
                                        Auditor = tbSalesReturnl.Auditor,//审核人
                                        Theamount = tbSalesReturnl.Theamount,//总金额
                                        AuditDate = tbSalesReturnl.AuditDate,//审核日期
                                        AuditDates = tbSalesReturnl.AuditDate.ToString(),//审核日期(格式转换)
                                        Remark = tbSalesReturnl.Remark,//备注
                                    }).ToList();
            #region 多条件查询
            // 时间（范围查询）
            if (StarAuditdate != "" && EndAuditdate != "")
            {
                DateTime starAuditdate = Convert.ToDateTime(StarAuditdate);
                DateTime endAuditdate = Convert.ToDateTime(EndAuditdate);
                listSalesReturnl = listSalesReturnl.Where(m => m.AuditDate >= starAuditdate && m.AuditDate <= endAuditdate).ToList();
            }
            if (StarAuditdate != "" && EndAuditdate == "")
            {
                DateTime starAuditdate = Convert.ToDateTime(StarAuditdate);
                listSalesReturnl = listSalesReturnl.Where(m => m.AuditDate >= starAuditdate).ToList();
            }
            if (StarAuditdate == "" && EndAuditdate != "")
            {
                DateTime endAuditdate = Convert.ToDateTime(EndAuditdate);
                listSalesReturnl = listSalesReturnl.Where(m => m.AuditDate <= endAuditdate).ToList();
            }

            // 单号
            if (!string.IsNullOrEmpty(Oddseats))
            {
                listSalesReturnl = listSalesReturnl.Where(m => m.Oddseats.Contains(Oddseats)).ToList();
            }

            // 审核
            if (ToAudit == "1")
            {
                listSalesReturnl = listSalesReturnl.Where(m => m.ToAudit == true).ToList();
            }
            else if (ToAudit == "2")
            {
                listSalesReturnl = listSalesReturnl.Where(m => m.ToAudit == false).ToList();
            }
            #endregion

            //获取当前查询出来的数据的条数
            var totalCount = listSalesReturnl.Count();
            List<SalesReturnlVo> listItem = listSalesReturnl
                                          .Skip(bsgriPage.GetStartIndex()) //Skip取数据的位置
                                          .Take(bsgriPage.pageSize)  //Take取数据的条数
                                          .ToList();
            Bsgrid<SalesReturnlVo> bsgrid = new Bsgrid<SalesReturnlVo>()   //实例化对象
            {
                success = true,
                totalRows = totalCount,
                curPage = bsgriPage.curPage,
                data = listSalesReturnl
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectSalesReturnDetails( int SalesReturnlID)//查询退货明细
        { 
            var listSalesReturnDetails =(from tbSalesReturnDetails in myModels.SYS_SalesReturnDetails
                                         join tbFittingsInfo in myModels.SYS_FittingsInfo on tbSalesReturnDetails.FittingsInfoID equals tbFittingsInfo.FittingsInfoID//配件    
                                         join tbVehicleType in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleType.VehicleTypeID //车型
                                         join tbSystemUnit in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnit.SystemUnitID //系统单位
                                         join tbWarehouse in myModels.SYS_Warehouse on tbSalesReturnDetails.WarehouseID equals tbWarehouse.WarehouseID //仓库
                                         orderby tbSalesReturnDetails.SalesReturnDetailsID descending
                                         where tbSalesReturnDetails.SalesReturnlID == SalesReturnlID
                                         select new SalesReturnDetailsVo
                                         {
                                             SalesReturnlID = tbSalesReturnDetails.SalesReturnlID,//出库ID
                                             SalesReturnDetailsID = tbSalesReturnDetails.SalesReturnDetailsID,//出库明细ID
                                             WarehouseID = tbWarehouse.WarehouseID,//仓库ID
                                             SystemUnitID = tbFittingsInfo.SystemUnitID, //系统单位ID
                                             SystemUnit = tbSystemUnit.SystemUnit,//单位
                                             VehicleType = tbVehicleType.VehicleType,//车型
                                             FittingsInfoID = tbFittingsInfo.FittingsInfoID,//配件信息ID
                                             FittingsCode = tbFittingsInfo.FittingsCode,//配件编码
                                             FittingsName = tbFittingsInfo.FittingsName,//配件名称
                                             Specification = tbFittingsInfo.Specification,//配件规格
                                             Quantity = tbSalesReturnDetails.Quantity,//数量
                                             Unitcoster = tbSalesReturnDetails.Unitcoster,//单价
                                             Discounts = tbSalesReturnDetails.Discounts,//折扣
                                             Amount = tbSalesReturnDetails.Amount,//金额
                                           // Userdefined = tbSalesReturnDetails.Userdefined,//自定义                                           
                                             Userdefined = tbSalesReturnDetails.Userdefined != null ? tbSalesReturnDetails.Userdefined : "无"
                                         }).ToList();          
            return Json(listSalesReturnDetails, JsonRequestBehavior.AllowGet);
        }


    }
}