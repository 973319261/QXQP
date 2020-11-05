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
    public class warehousingController : Controller
    {
          QXQPEntities myModels = new QXQPEntities();
        // GET: ProcurementManagment/warehousing
        public ActionResult warehousing()//销售出库
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
        public ActionResult Removal()//生成出库单号
        {
            string Stockremoval = "";
            var date = DateTime.Now.ToString("yyyyMMdd");
            var datetime = DateTime.Now.ToString("yyyyMMddHHmmss");
            try
            {
                var list = myModels.PW_Outbound.Where(m => m.Stockremoval.Contains(date)).OrderBy(m => m.Stockremoval).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().Stockremoval.Trim().Substring(16)) + 1;
                    if (num < 10)
                    {
                        Stockremoval = "XS" + datetime + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        Stockremoval = "XS" + datetime + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        Stockremoval = "XS" + datetime + "0" + num;
                    }
                }
                else
                {
                    Stockremoval = "XS" + datetime + "0001";
                }
            }
            catch (Exception)
            {
                return Json(Stockremoval, JsonRequestBehavior.AllowGet);
            }
            return Json(Stockremoval, JsonRequestBehavior.AllowGet);
        }
        public ActionResult InsertOutbound(List<PW_Outbound> ListOutbound, List<SYS_OutboundDetails> ListutboundDetails)//新增&修改出货单
        {
            //Session["ListutboundDetails"] = ListutboundDetails;
            var OutboundID = 0;
            try
            {            
                //出货表
                if (ListOutbound[0].OutboundID==0)
                {
                    //新增          
                    ListOutbound[0].DocumentsTypeID = 4;//单据类型    
                    ListOutbound[0].BalanceStateID = 2;//结算状态      
                    myModels.PW_Outbound.Add(ListOutbound[0]);
                }
                else
                {
                    //修改
                    myModels.Entry(ListOutbound[0]).State = System.Data.Entity.EntityState.Modified;
                }
                //出货明细表
                if (myModels.SaveChanges() > 0)
                {
                    OutboundID = ListOutbound[0].OutboundID;
                    //声明列表 
                    List<int> oldID = new List<int>(); //原ID
                    List<int> newID = new List<int>(); //新ID
                    List<int> listdelectID = new List<int>(); //需要删除的ID集合
                    var list = myModels.SYS_OutboundDetails.Where(m => m.OutboundID == OutboundID).Select(m => new { m.OutboundDetailsID}).ToList();
                    //遍历列表
                    foreach (var item in list)
                    {
                        oldID.Add(item.OutboundDetailsID);
                    }
                    if (ListutboundDetails != null)
                    {
                        for (int i = 0; i < ListutboundDetails.Count; i++)
                        {                        
                            ListutboundDetails[i].OutboundID = ListOutbound[0].OutboundID;  //出货ID
                            if (ListutboundDetails[i].OutboundDetailsID == 0)
                            {
                                myModels.SYS_OutboundDetails.Add(ListutboundDetails[i]); //新增
                            }
                            else
                            {
                                newID.Add(ListutboundDetails[i].OutboundDetailsID);
                                myModels.Entry(ListutboundDetails[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项(差集) 
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_OutboundDetails.Where(m => m.OutboundDetailsID == item).SingleOrDefault();//删除
                            myModels.SYS_OutboundDetails.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_OutboundDetails.Where(m =>m.OutboundID == OutboundID).ToList();//删除全部
                        myModels.SYS_OutboundDetails.RemoveRange(listdelect);
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
            return Json(OutboundID, JsonRequestBehavior.AllowGet);
        }
        public ActionResult DelectOutbound(int OutboundID)//删除单
        {
            try
            {
                var listOutbound = myModels.PW_Outbound.Where(m => m.OutboundID == OutboundID).Single();//出货表
                var listOutboundDetails = myModels.SYS_OutboundDetails.Where(m => m.OutboundID == OutboundID).ToList();//出货明细表
                myModels.PW_Outbound.Remove(listOutbound);
                myModels.SYS_OutboundDetails.RemoveRange(listOutboundDetails);
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ToReviewwhet(int OutboundID,string SalesorderNum/*,int NewWarehouseID*/)//审核销售单
        {
            //var ListutboundDetails = Session["ListutboundDetails"] as List<SYS_OutboundDetails>;//出库明细
            ArrayList lists = new ArrayList();          
            try
            {  //审核         
                var Therevie = Session["UserName"].ToString().Trim();//审核人             
                var Auditdates = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");//审核时间             
                lists.Add(Therevie);
                lists.Add(Auditdates.ToString());           
                var list = myModels.PW_Outbound.Where(m => m.OutboundID == OutboundID).Single();
                list.OutboundID = list.OutboundID;
                list.Reviewwhet = true;//审核否
                list.Therevie = Session["UserName"].ToString();//审核人
                list.Auditdates = DateTime.Now;//审核时间
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                //已出货
                if (myModels.SaveChanges() > 0)
                {
                    if (SalesorderNum != "")
                    {
                        var Salesorde = myModels.PW_Salesorder.Where(m => m.SalesorderNum == SalesorderNum).SingleOrDefault();
                        Salesorde.Documents = "已出货";
                        myModels.Entry(Salesorde).State = System.Data.Entity.EntityState.Modified;
                        myModels.SaveChanges();
                    }
                    //        foreach (var item in ListutboundDetails) //遍历明细
                    //        {
                    //            var ListutboundDetailsID = item.OutboundDetailsID;//明细ID
                    //            var FittingsInfoID = item.FittingsInfoID;//配件ID
                    //            var Quantity = item.Quantity;//数量
                    //            var listFittings = (from tbFittingsInfo in myModels.SYS_FittingsInfo
                    //                                join tbFittingsType in myModels.SYS_FittingsType on tbFittingsInfo.FittingsTypeID equals tbFittingsType.FittingsTypeID
                    //                                join tbVehicleType in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleType.VehicleTypeID
                    //                                join tbSystemUnit in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnit.SystemUnitID
                    //                                where tbFittingsInfo.FittingsInfoID == FittingsInfoID
                    //                                select new FittingsInfoVo
                    //                                {
                    //                                    FittingsCode = tbFittingsInfo.FittingsCode.Trim(),
                    //                                    FittingsName = tbFittingsInfo.FittingsName.Trim(),
                    //                                    VehicleType = tbVehicleType.VehicleType.Trim(),
                    //                                    FittingsTypeName = tbFittingsType.FittingsTypeName.Trim(),
                    //                                    SystemUnit = tbSystemUnit.SystemUnit.Trim()
                    //                                }).SingleOrDefault();
                    //            var FittingsCode = listFittings.FittingsCode;//配件编码
                    //            var listInventorys = myModels.SYS_Inventory.Where(m => m.WarehouseID == NewWarehouseID && m.FittingsCode == FittingsCode).SingleOrDefault();//修改后仓库
                    //            if (listInventorys != null)//修改库存(库存量)
                    //            {
                    //                listInventorys.InvenQuan = listInventorys.InvenQuan + Quantity;
                    //                myModels.Entry(listInventorys).State = System.Data.Entity.EntityState.Modified;
                    //            }
                    //            else
                    //            {
                    //                //新增库存
                    //                SYS_Inventory sys_Inventory = new SYS_Inventory();
                    //                sys_Inventory.FittingsCode = listFittings.FittingsCode;//配件编码
                    //                sys_Inventory.FittingsName = listFittings.FittingsName;//配件名称
                    //                sys_Inventory.FittingsType = listFittings.FittingsTypeName;
                    //                sys_Inventory.VehicleType = listFittings.VehicleType;//车型
                    //                sys_Inventory.SystemUnit = listFittings.SystemUnit;//单位名称
                    //                sys_Inventory.WarehouseID = NewWarehouseID;                      
                    //                sys_Inventory.InvenQuan = Quantity;//数量   
                    //                sys_Inventory.SalePrice = item.Unitcosts;//单价                  
                    //                myModels.SYS_Inventory.Add(sys_Inventory);
                    //            }
                    //            //修改配件信息表库存量
                    //            var listFit = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).SingleOrDefault();
                    //            listFit.InvenQuan = listFit.InvenQuan + Quantity;
                    //            myModels.Entry(listFit).State = System.Data.Entity.EntityState.Modified;
                    //        }
                    //    }
                    //    myModels.SaveChanges();
                }
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(lists, JsonRequestBehavior.AllowGet);
        }
        public ActionResult NoToReviewwhet(int OutboundID, string SalesorderNum)//反审核销售单
        {
            try
            {
                var list = myModels.PW_Outbound.Where(m => m.OutboundID == OutboundID).Single();
                list.OutboundID = list.OutboundID;
                list.Reviewwhet = false;//审核否
                list.Therevie = "";//审核人    
                list.Auditdates = null; //审核时间             
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                //未出货
                if (myModels.SaveChanges() > 0)
                {
                    if (SalesorderNum != null)
                    {
                        var Salesorde = myModels.PW_Salesorder.Where(m => m.SalesorderNum == SalesorderNum).SingleOrDefault();
                        Salesorde.Documents = "未出货";
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
        public ActionResult SelectSalesorder(BsgridPage bsgriPage,string SalesorderNum, string StarAuditdate, string EndAuditdate)//查询已审核销售订单
        {
            var listSalesorder = (from tbSalesorder in myModels.PW_Salesorder
                                  join tbSalesCustomer in myModels.SYS_SalesCustomer on tbSalesorder.SalesCustomerID equals tbSalesCustomer.SalesCustomerID
                                  orderby tbSalesorder.SalesordeID descending
                                  where tbSalesorder.Reviewwhether == true && tbSalesorder.Documents == "未出货"
                                  select new SalesorderVo
                                  {
                                      SalesordeID = tbSalesorder.SalesordeID,//订单ID  
                                      SalesCustomerID = tbSalesCustomer.SalesCustomerID,//客户ID
                                      CustomerName = tbSalesCustomer.CustomerName,//客户名称
                                      CustomerCode = tbSalesCustomer.CustomerCode,//客户编码
                                      SalesorderNum = tbSalesorder.SalesorderNum,//订单号
                                      Invoicedatek = tbSalesorder.Invoicedatek,//开单日期
                                      Invoicno = tbSalesorder.Invoicno,//发票号码
                                      Deliverydate = tbSalesorder.Deliverydate,//交货日期
                                      Operatorf = tbSalesorder.Operatorf,//操作人
                                      Reviewwhether = tbSalesorder.Reviewwhether, //审核否
                                      Thereviewer = tbSalesorder.Thereviewer,//审核人
                                      Auditdate = tbSalesorder.Auditdate,//审核日期
                                      Auditdates = tbSalesorder.Auditdate.ToString(),//审核日期时间格式转换)
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
                data = listSalesorder
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectOutbound(BsgridPage bsgriPage, string StarAuditdate, string EndAuditdate, string Stockremoval, string Reviewwhet)//查询单据
        {
            var listOutbound = (from tbOutbound in myModels.PW_Outbound
                                join tbSalesCustomer in myModels.SYS_SalesCustomer on tbOutbound.SalesCustomerID equals tbSalesCustomer.SalesCustomerID //销售客户            
                                orderby tbOutbound.OutboundID descending
                                select new OutboundVo
                                {
                                    OutboundID = tbOutbound.OutboundID,//出库ID
                                    SalesCustomerID = tbSalesCustomer.SalesCustomerID,//客户ID
                                    Stockremoval = tbOutbound.Stockremoval,//出库单号
                                    SalesorderNum = tbOutbound.SalesorderNum,//订单号
                                    CustomerCode = tbSalesCustomer.CustomerCode,//客户编码
                                    CustomerName = tbSalesCustomer.CustomerName,//客户名称
                                    OpenDate = tbOutbound.OpenDate,//开单时间  
                                    OpenDates = tbOutbound.OpenDate.ToString(),//开单时间(时间格式转换)                           
                                    Tradeprice = tbOutbound.Tradeprice,//按批发价
                                    Processingman = tbOutbound.Processingman,//操作员
                                    Reviewwhet = tbOutbound.Reviewwhet,//审核否
                                    Therevie = tbOutbound.Therevie,//审核人
                                    Auditdates = tbOutbound.Auditdates,//审核日期 
                                    Auditdate = tbOutbound.Auditdates.ToString(),//审核日期(时间格式转换)                                           
                                    Theamount = tbOutbound.Theamount,//总金额
                                    Remark = tbOutbound.Remark,//备注    
                                    DocuState = tbOutbound.DocuState //单据状态

                                }).ToList();
            #region 多条件查询 
            // 时间（范围查询）
            if (StarAuditdate != "" && EndAuditdate != "")
            {
                DateTime starAuditdate = Convert.ToDateTime(StarAuditdate);
                DateTime endAuditdate = Convert.ToDateTime(EndAuditdate);
                listOutbound = listOutbound.Where(m => m.OpenDate >= starAuditdate && m.OpenDate <= endAuditdate).ToList();
            }
            if (StarAuditdate != "" && EndAuditdate == "")
            {
                DateTime starAuditdate = Convert.ToDateTime(StarAuditdate);
                listOutbound = listOutbound.Where(m => m.OpenDate >= starAuditdate).ToList();
            }
            if (StarAuditdate == "" && EndAuditdate != "")
            {
                DateTime endAuditdate = Convert.ToDateTime(EndAuditdate);
                listOutbound = listOutbound.Where(m => m.OpenDate <= endAuditdate).ToList();
            }

            // 单号
            if (!string.IsNullOrEmpty(Stockremoval))
            {
                listOutbound = listOutbound.Where(m => m.Stockremoval.Contains(Stockremoval)).ToList();
            }

            // 审核
            if (Reviewwhet == "1")
            {
                listOutbound = listOutbound.Where(m => m.Reviewwhet == true).ToList();
            }
            else if (Reviewwhet == "2")
            {
                listOutbound = listOutbound.Where(m => m.Reviewwhet == false).ToList();
            }

            #endregion

            //获取当前查询出来的数据的条数
            var totalCount = listOutbound.Count();
            List<OutboundVo> listItem = listOutbound
                                          .Skip(bsgriPage.GetStartIndex()) //Skip取数据的位置
                                          .Take(bsgriPage.pageSize)  //Take取数据的条数
                                          .ToList();
            Bsgrid<OutboundVo> bsgrid = new Bsgrid<OutboundVo>()   //实例化对象
            {
                success = true,
                totalRows = totalCount,
                curPage = bsgriPage.curPage,
                data = listOutbound
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectOutboundDetails(int OutboundID)//查询出货明细
        {
            var listOutboundDetails = (from tbOutboundDetails in myModels.SYS_OutboundDetails
                                       join tbFittingsInfo in myModels.SYS_FittingsInfo on tbOutboundDetails.FittingsInfoID equals tbFittingsInfo.FittingsInfoID//配件
                                       join tbVehicleType in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleType.VehicleTypeID //车型
                                       join tbSystemUnit in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnit.SystemUnitID //系统单位
                                       join tbWarehouse in myModels.SYS_Warehouse on tbOutboundDetails.WarehouseID equals tbWarehouse.WarehouseID //仓库
                                       orderby tbOutboundDetails.OutboundDetailsID descending
                                       where tbOutboundDetails.OutboundID == OutboundID
                                       select new OutboundDetailsVo
                                       {
                                           OutboundDetailsID = tbOutboundDetails.OutboundDetailsID,//出库明细ID
                                           OutboundID = tbOutboundDetails.OutboundID,//出库ID
                                           WarehouseID = tbWarehouse.WarehouseID,//仓库ID
                                           VehicleType = tbVehicleType.VehicleType,//车型
                                           SystemUnitID = tbFittingsInfo.SystemUnitID, //系统单位ID
                                           SystemUnit = tbSystemUnit.SystemUnit,//单位
                                           FittingsInfoID = tbFittingsInfo.FittingsInfoID,//配件信息ID
                                           FittingsCode = tbFittingsInfo.FittingsCode,//配件编码
                                           FittingsName = tbFittingsInfo.FittingsName,//配件名称
                                           Specification = tbFittingsInfo.Specification,//配件规格  
                                           Quantity = tbOutboundDetails.Quantity,//数量
                                           Discount = tbOutboundDetails.Discount,//折扣
                                           Figure = tbOutboundDetails.Figure,//金额                                         
                                           Unitcosts = tbOutboundDetails.Unitcosts,//单价
                                          // Comments = tbOutboundDetails.Comments,//备注 
                                          Comments = tbOutboundDetails.Comments != null ? tbOutboundDetails.Comments : "无"
                                       }).ToList();
            return Json(listOutboundDetails, JsonRequestBehavior.AllowGet);
        }      
    }
}