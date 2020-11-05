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
    public class SalesorderController : Controller
    {
        QXQPEntities myModels = new QXQPEntities();
        // GET: ProcurementManagment/Salesorder

        #region 销售订货     
        public ActionResult Salesorder()//销售订货
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
        public ActionResult Ordernumber()//生成订单号
        {
            string SalesorderNum = "";
            var date = DateTime.Now.ToString("yyyyMMdd");
            var datetime = DateTime.Now.ToString("yyyyMMddHHmmss");
            try
            {
                var list = myModels.PW_Salesorder.Where(m => m.SalesorderNum.Contains(date)).OrderBy(m => m.SalesorderNum).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().SalesorderNum.Trim().Substring(16)) + 1;
                    if (num < 10)
                    {
                        SalesorderNum = "DD" + datetime + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        SalesorderNum = "DD" + datetime + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        SalesorderNum = "DD" + datetime + "0" + num;
                    }
                }
                else
                {
                    SalesorderNum = "DD" + datetime + "0001";
                }
            }
            catch (Exception)
            {
                return Json(SalesorderNum, JsonRequestBehavior.AllowGet);
            }
            return Json(SalesorderNum, JsonRequestBehavior.AllowGet);
            //string SalesorderNum = "";
            //var date = DateTime.Now.ToString("yyyyMMdd");
            //try
            //{
            //    int Count = myModels.PW_Salesorder.Where(m => m.SalesorderNum.Contains(date)).Count() + 1;
            //    if (Count < 10)
            //    {
            //        SalesorderNum = "DD" + date + "000" + Count;
            //    }
            //    else if (Count > 9 && Count < 100)
            //    {
            //        SalesorderNum = "DD" + date + "00" + Count;
            //    }
            //    else if (Count > 99 && Count < 1000)
            //    {
            //        SalesorderNum = "DD" + date + "0" + Count;
            //    }
            //}
            //catch (Exception)
            //{

            //    return Json(SalesorderNum, JsonRequestBehavior.AllowGet);
            //}
            //return Json(SalesorderNum, JsonRequestBehavior.AllowGet);
        }     
        public ActionResult Selectordergoods(BsgridPage bsgriPage, string CustomerName)//查询客户(条件查询&数据回填)
        {
            var listSalesCustomer = (from tbSalesCustomer in myModels.SYS_SalesCustomer
                                     join tbUser in myModels.PW_User on tbSalesCustomer.UserID equals tbUser.UserID  //用户表
                                     join tbRegion in myModels.SYS_Region on tbSalesCustomer.RegionID equals tbRegion.RegionID  //客户区域表
                                     join tbCustomerSou in myModels.SYS_CustomerSou on tbSalesCustomer.CustomerSouID equals tbCustomerSou.CustomerSouID //客户来源表
                                     join tbCustomerLevel in myModels.SYS_CustomerLevel on tbSalesCustomer.CustomerLevelID equals tbCustomerLevel.CustomerLevelID   //客户等级表
                                     join tbCustomerType in myModels.SYS_CustomerType on tbSalesCustomer.CustomerTypeID equals tbCustomerType.CustomerTypeID //客户类型表               
                                     join tbPayment in myModels.SYS_Payment on tbSalesCustomer.PaymentID equals tbPayment.PaymentID //付款方式表                     
                                     orderby tbSalesCustomer.SalesCustomerID descending
                                     select new SalesCustomerVo
                                     {
                                         SalesCustomerID = tbSalesCustomer.SalesCustomerID,//销售客户ID
                                         UserID = tbUser.UserID,//用户ID
                                         RegionID  = tbRegion.RegionID, //客户区域ID
                                         CustomerSouID = tbCustomerSou.CustomerSouID, //客户来源ID
                                         CustomerLevelID = tbCustomerLevel.CustomerLevelID, //客户等级ID
                                         CustomerTypeID = tbCustomerType.CustomerTypeID,//客户类型ID
                                         PaymentID = tbPayment.PaymentID,//付款方式ID
                                         UserName = tbUser.UserName, //业务员                                         
                                         CustomerCode = tbSalesCustomer.CustomerCode,//客户编号
                                         CustomerName = tbSalesCustomer.CustomerName,//客户
                                         TelePhone = tbSalesCustomer.TelePhone,//电话
                                         MobilePhone = tbSalesCustomer.MobilePhone,//手机
                                         Contacts = tbSalesCustomer.Contacts,//联系人
                                         Address = tbSalesCustomer.Address,//地址   
                                         ZipCode = tbSalesCustomer.ZipCode,//邮编
                                         FullName = tbSalesCustomer.FullName,//全称
                                         PinYinCode = tbSalesCustomer.PinYinCode,//拼音码
                                         IdNumber = tbSalesCustomer.IdNumber,//身份证号
                                         Facsimile = tbSalesCustomer.Facsimile,//传真
                                         Emai = tbSalesCustomer.Emai,//Emai
                                         Remark = tbSalesCustomer.Remark,//备注
                                         InputPerson = tbSalesCustomer.InputPerson,//录入人                                   
                                     }).ToList();
            //条件查询(模糊查询) 
            if (!string.IsNullOrEmpty(CustomerName))
            {
                //listSalesCustomer = listSalesCustomer.Where(m => m.CustomerName== CustomerName).ToList();//条件查询
                listSalesCustomer = listSalesCustomer.Where(m => m.CustomerName.Contains(CustomerName)).ToList();//模糊查询
            }
          
            //获取当前查询出来的数据的条数
            var totalCount = listSalesCustomer.Count();

            List<SalesCustomerVo> listItem = listSalesCustomer
                                          .Skip(bsgriPage.GetStartIndex()) //Skip取数据的位置
                                          .Take(bsgriPage.pageSize)  //Take取数据的条数
                                          .ToList();

            Bsgrid<SalesCustomerVo> bsgrid = new Bsgrid<SalesCustomerVo>()   //实例化对象
            {
                success = true,
                totalRows = totalCount,
                curPage = bsgriPage.curPage,
                data = listItem
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult Newmodification(SYS_SalesCustomer sys_SalesCustomer) //新增&修改客户
        {
            //判断是否为零，零为新增，否则为修改
            int SalesCustomerID = sys_SalesCustomer.SalesCustomerID;
            try
            {
                if (SalesCustomerID == 0)
                {
                    //新增                  
                    myModels.SYS_SalesCustomer.Add(sys_SalesCustomer);
                    myModels.SaveChanges();
                }
                else
                {
                    //修改
                    myModels.Entry(sys_SalesCustomer).
                    State = System.Data.Entity.EntityState.Modified;
                    myModels.SaveChanges();
                }
            }
            catch (Exception)
            {

                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectSalesorder(BsgridPage bsgriPage, int SalesordeID)//查询订单
        {
            var listSalesorder = (from tbSalesorder in myModels.PW_Salesorder
                                  join tbSalesCustomer in myModels.SYS_SalesCustomer on tbSalesorder.SalesCustomerID equals tbSalesCustomer.SalesCustomerID
                                  orderby tbSalesorder.SalesordeID descending
                                  select new SalesorderVo
                                  {
                                      SalesordeID = tbSalesorder.SalesordeID,//订单ID  
                                      SalesCustomerID = tbSalesCustomer.SalesCustomerID,//客户ID                               
                                      CustomerCode = tbSalesCustomer.CustomerCode,//客户编码
                                      SalesorderNum = tbSalesorder.SalesorderNum,//订单号
                                      Invoicedatek = tbSalesorder.Invoicedatek,//开单日期
                                      Invoicno = tbSalesorder.Invoicno,//发票号码
                                      Deliverydate = tbSalesorder.Deliverydate,//交货日期
                                      Operatorf = tbSalesorder.Operatorf,//操作人
                                      Reviewwhether = tbSalesorder.Reviewwhether, //审核否
                                      Thereviewer = tbSalesorder.Thereviewer,//审核人
                                      Auditdate = tbSalesorder.Auditdate,//审核日期
                                      Theamount = tbSalesorder.Theamount,//总金额
                                      Remark = tbSalesorder.Remark,//备注
                                      Documents = tbSalesorder.Documents,//单据状况
                                  }).ToList();
            //获取当前查询出来的数据的条数
            var totalCount = listSalesorder.Count();
            List<SalesorderVo> listItem = listSalesorder
                                          .Skip(bsgriPage.GetStartIndex()) //Skip取数据的位置
                                          .Take(bsgriPage.pageSize)  //Take取数据的条数
                                          .ToList();
            Bsgrid<SalesorderVo> bsgrid = new Bsgrid<SalesorderVo>()   //实例化对象
            {
                success = true,
                totalRows = totalCount,
                curPage = bsgriPage.curPage,
                data = listSalesorder
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult InsertSalesorder(List<PW_Salesorder> ListSalesorder, List<SYS_SalesorderDetail> ListSalesorderDetai)//新增&修改订单
        {
            var SalesordeID = 0;//声明一个变量
            try
            {             
                //订单表
                if (ListSalesorder[0].SalesordeID == 0)
                {
                    //新增
                    ListSalesorder[0].Documents = "未出货";//单据状态
                    myModels.PW_Salesorder.Add(ListSalesorder[0]);
                }
                else
                {
                    //修改
                    ListSalesorder[0].Documents = "未出货";
                    myModels.Entry(ListSalesorder[0]).State = System.Data.Entity.EntityState.Modified;
                }
                //明细表
                if (myModels.SaveChanges() > 0)
                {
                    SalesordeID = ListSalesorder[0].SalesordeID;
                    //声明列表
                    List<int> oldID = new List<int>();//原来ID {1,2,3,4}
                    List<int> newID = new List<int>();//新ID {1,2}
                    List<int> listdelectID = new List<int>();//需要删除的ID集合 {3,4}
                    var list = myModels.SYS_SalesorderDetail.Where(m => m.SalesordeID == SalesordeID).
                                                               Select(m => new
                                                               {
                                                                   m.SalesorderDetailID
                                                               }).ToList();                   
                    foreach (var item in list)
                    {
                        oldID.Add(item.SalesorderDetailID);
                    }

                    if (ListSalesorderDetai != null)
                    {                       
                        for (int i = 0; i < ListSalesorderDetai.Count; i++)
                        {
                            //销售订单ID
                            ListSalesorderDetai[i].SalesordeID = ListSalesorder[0].SalesordeID;
                            if (ListSalesorderDetai[i].SalesorderDetailID == 0)
                            {
                               
                                myModels.SYS_SalesorderDetail.Add(ListSalesorderDetai[i]); //新增
                            }
                            else
                            {
                         
                                newID.Add(ListSalesorderDetai[i].SalesorderDetailID);
                                myModels.Entry(ListSalesorderDetai[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项(差集) 
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_SalesorderDetail.Where(m => m.SalesorderDetailID == item).SingleOrDefault();//删除
                            myModels.SYS_SalesorderDetail.Remove(listdelect);
                        }                    
                    }
                    else
                    {
                        var listdelect = myModels.SYS_SalesorderDetail.Where(m => m.SalesordeID == SalesordeID).ToList();//删除全部
                        myModels.SYS_SalesorderDetail.RemoveRange(listdelect);
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
            return Json(SalesordeID, JsonRequestBehavior.AllowGet);
        }
        public ActionResult DelectSalesorder(int SalesordeID)//删除订单
        {
            try
            {
                var listSalesorder = myModels.PW_Salesorder.Where(m => m.SalesordeID == SalesordeID).Single();//订单表
                var listSalesorderDetail = myModels.SYS_SalesorderDetail.Where(m => m.SalesordeID == SalesordeID).ToList();//订单明细表
                myModels.PW_Salesorder.Remove(listSalesorder);
                myModels.SYS_SalesorderDetail.RemoveRange(listSalesorderDetail);
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ToReviewwhether(int SalesordeID)//审核订单
        {
            ArrayList lists = new ArrayList();
            try
            {
                var Thereviewer = Session["UserName"].ToString().Trim();//审核人             
                var Auditdate = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");//审核时间             
                lists.Add(Thereviewer);
                lists.Add(Auditdate.ToString());           
                var list = myModels.PW_Salesorder.Where(m => m.SalesordeID == SalesordeID).Single();
                list.SalesordeID = list.SalesordeID;
                list.Reviewwhether = true;//审核否
                list.Thereviewer = Session["UserName"].ToString();//审核人
                list.Auditdate = DateTime.Now;//审核时间
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(lists, JsonRequestBehavior.AllowGet);
        }
        public ActionResult NoToReviewwhether(int SalesordeID)//反审核订单
        {
            ReturnJson returnJson = new ReturnJson();
            try
            {

                var list = myModels.PW_Salesorder.Where(m => m.SalesordeID == SalesordeID).Single();
                if (list.Documents.Trim() == "未出货")
                {
                    list.SalesordeID = list.SalesordeID;
                    list.Reviewwhether = false;//审核否
                    list.Thereviewer = "";//审核人    
                    list.Auditdate = null; //审核时间             
                    myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                    myModels.SaveChanges();
                    returnJson.State = true;
                }
                else {
                    returnJson.State = false;
                    returnJson.Text = "该单据已出货，不能反审核！";
                }           
            }
            catch (Exception)
            {
                return Json(returnJson, JsonRequestBehavior.AllowGet);
            }
            return Json(returnJson, JsonRequestBehavior.AllowGet);
        }
        public ActionResult Selectreceipts(BsgridPage bsgriPage, string SalesorderNum, string Reviewwhether, string StarAuditdate, string EndAuditdate, string Documents)//查询单据
        {
            var listSalesorder = (from tbSalesorder in myModels.PW_Salesorder
                                  join tbSalesCustomer in myModels.SYS_SalesCustomer on tbSalesorder.SalesCustomerID equals tbSalesCustomer.SalesCustomerID
                                  orderby tbSalesorder.SalesordeID descending
                                  select new SalesorderVo
                                  {
                                      SalesordeID = tbSalesorder.SalesordeID,//筛选（订单ID）                                 
                                      SalesCustomerID = tbSalesCustomer.SalesCustomerID,////筛选（客户ID）
                                      CustomerCode = tbSalesCustomer.CustomerCode,//客户编码
                                      CustomerName = tbSalesCustomer.CustomerName,//客户名称
                                      SalesorderNum = tbSalesorder.SalesorderNum,//订单号
                                      Invoicedateks = tbSalesorder.Invoicedatek.ToString(),//开单日期(时间格式转换) 
                                      Invoicedatek = tbSalesorder.Invoicedatek,//开单日期 
                                      Invoicno = tbSalesorder.Invoicno, //发票号码
                                      Deliverydates = tbSalesorder.Deliverydate.ToString(),//交货日期(时间格式转换)
                                      Deliverydate = tbSalesorder.Deliverydate,//交货日期
                                      Operatorf = tbSalesorder.Operatorf,//操作人
                                      Reviewwhether = tbSalesorder.Reviewwhether,//审核
                                      Thereviewer = tbSalesorder.Thereviewer,//审核人
                                      Auditdates = tbSalesorder.Auditdate.ToString(),//审核日期(时间格式转换)
                                      Auditdate = tbSalesorder.Auditdate,//审核日期
                                      Theamount = tbSalesorder.Theamount,//总金额
                                      Remark = tbSalesorder.Remark,//备注
                                      Documents = tbSalesorder.Documents,//单据状况
                                  }).ToList();
            #region 多条件查询         
            // 单号
            if (!string.IsNullOrEmpty(SalesorderNum))
            {
                listSalesorder = listSalesorder.Where(m => m.SalesorderNum.Contains(SalesorderNum)).ToList();
            }

            //单据状况
            if (!string.IsNullOrEmpty(Documents))
            {
                listSalesorder = listSalesorder.Where(m => m.Documents == Documents).ToList();
            }

            // 审核
            if (Reviewwhether == "1")
            {
                listSalesorder = listSalesorder.Where(m => m.Reviewwhether == true).ToList();
            }
            else if (Reviewwhether == "2")
            {
                listSalesorder = listSalesorder.Where(m => m.Reviewwhether == false).ToList();
            }

            // 时间（范围查询）
            if (StarAuditdate != "" && EndAuditdate != "")
            {
                DateTime starAuditdate = Convert.ToDateTime(StarAuditdate);
                DateTime endAuditdate = Convert.ToDateTime(EndAuditdate);
                listSalesorder = listSalesorder.Where(m => m.Auditdate >= starAuditdate && m.Auditdate <= endAuditdate).ToList();
            }
            if (StarAuditdate != "" && EndAuditdate == "")
            {
                DateTime starAuditdate = Convert.ToDateTime(StarAuditdate);
                listSalesorder = listSalesorder.Where(m => m.Auditdate >= starAuditdate).ToList();
            }
            if (StarAuditdate == "" && EndAuditdate != "")
            {
                DateTime endAuditdate = Convert.ToDateTime(EndAuditdate);
                listSalesorder = listSalesorder.Where(m => m.Auditdate <= endAuditdate).ToList();
            }
            #endregion

            //获取当前查询出来的数据的条数
            var totalCount = listSalesorder.Count();

            List<SalesorderVo> listItem = listSalesorder
                                          .Skip(bsgriPage.GetStartIndex()) //Skip取数据的位置
                                          .Take(bsgriPage.pageSize)  //Take取数据的条数
                                          .ToList();

            Bsgrid<SalesorderVo> bsgrid = new Bsgrid<SalesorderVo>()   //实例化对象
            {
                success = true,
                totalRows = totalCount,
                curPage = bsgriPage.curPage,
                data = listItem
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectOerderdetail(int SalesordeID) //查询明细
        {        
            var listSalesorderDetail = (from tbSalesorderDetail in myModels.SYS_SalesorderDetail
                                        join tbFittingsInfo in myModels.SYS_FittingsInfo on tbSalesorderDetail.FittingsInfoID equals tbFittingsInfo.FittingsInfoID
                                        join tbVehicleType in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleType.VehicleTypeID //车型
                                        join tbSystemUnit in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnit.SystemUnitID //单位
                                        join tbWarehouse in myModels.SYS_Warehouse on tbSalesorderDetail.WarehouseID equals tbWarehouse.WarehouseID //仓库
                                        orderby tbSalesorderDetail.SalesorderDetailID descending
                                        where tbSalesorderDetail.SalesordeID==SalesordeID
                                        select new SalesorderDetailVo
                                        {
                                            SalesorderDetailID = tbSalesorderDetail.SalesorderDetailID,//明细ID                                   
                                            SalesordeID = tbSalesorderDetail.SalesordeID,//订单ID
                                            SystemUnitID = tbFittingsInfo.SystemUnitID, //系统单位ID
                                            FittingsInfoID = tbFittingsInfo.FittingsInfoID,//配件信息ID
                                            WarehouseID = tbWarehouse.WarehouseID,//仓库ID
                                            FittingsCode = tbFittingsInfo.FittingsCode,//配件编码
                                            FittingsName = tbFittingsInfo.FittingsName,//配件名称
                                            Specification = tbFittingsInfo.Specification,//配件规格                                         
                                            VehicleType = tbVehicleType.VehicleType,//车型
                                            SystemUnit = tbSystemUnit.SystemUnit,//单位
                                            Amount = tbSalesorderDetail.Amount,//数量
                                            Discount = tbSalesorderDetail.Discount,//折扣
                                            Money = tbSalesorderDetail.Money,//金额
                                            Atretail = tbSalesorderDetail.Atretail==null? "无": tbSalesorderDetail.Atretail,//零售单号
                                            Unitcost = tbSalesorderDetail.Unitcost,//单价
                                            Remark = tbSalesorderDetail.Remark != null ? tbSalesorderDetail.Remark : "无"
                                        }).ToList();           
            return Json(listSalesorderDetail, JsonRequestBehavior.AllowGet);      
        }
        #endregion
    }
}