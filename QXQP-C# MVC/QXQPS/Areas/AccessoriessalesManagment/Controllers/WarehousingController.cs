using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.AccessoriessalesManagment.Controllers
{
    public class WarehousingController : Controller
    {
        QXQPEntities myModels = new Models.QXQPEntities();
        // GET: AccessoriessalesManagment/Warehousing
        public ActionResult Warehousing()//采购入库
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
        
        public ActionResult MaintenanceNum()//入库单号
        {

            string MaintenanceNum = "";
            var date = "R" + DateTime.Now.ToString("yyyyMMdd");
            try
            {
                var list = myModels.PW_Incoming.Where(m => m.IncomingNumber.Contains(date)).OrderBy(m => m.IncomingNumber).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().IncomingNumber.Trim().Substring(9)) + 1;
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
        public ActionResult SelectProcurement(BsgridPage bsgridPage, string IndentNum, string Timeones, string Timetows)//查询已审核的采购订单
        {
            var listProcur = (from tbProcur in myModels.PW_Procurement
                              join tbSuppli in myModels.SYS_Suppliers on tbProcur.SuppliersID equals tbSuppli.SuppliersID
                              join tbWareho in myModels.SYS_Warehouse on tbProcur.WarehouseID equals tbWareho.WarehouseID
                              where tbProcur.ToAudit == true && tbProcur.Documents=="未进货"
                              select new ProcurementVo
                              {
                                  ProcurementID = tbProcur.ProcurementID,
                                  SuppliersID = tbSuppli.SuppliersID,
                                  WarehouseID = tbWareho.WarehouseID,
                                  SuppliersName = tbSuppli.SuppliersName.Trim(),
                                  SuppliersFirm = tbSuppli.SuppliersFirm.Trim(),
                                  MakerDates = tbProcur.MakerDate.ToString(),
                                  MakerDate = tbProcur.MakerDate,
                                  Maker = tbProcur.Maker.Trim(),
                                  IndentNumber = tbProcur.IndentNumber,
                                  DeliveryDeadlines = tbProcur.DeliveryDeadline.ToString(),
                                  WarehouseName = tbWareho.WarehouseName.Trim(),
                                  Aamount = tbProcur.Aamount,
                                  Handsel = tbProcur.Handsel,
                                  ToAudit = tbProcur.ToAudit,
                                  Documents = tbProcur.Documents,
                                  Auditer = tbProcur.Auditer.Trim(),
                                  AuditerDates = tbProcur.AuditerDate.ToString(),
                                  Remark = tbProcur.Remark.Trim(),
                              }).ToList();

            if (!string.IsNullOrEmpty(IndentNum))
            {
                listProcur = listProcur.Where(m => m.IndentNumber.Contains(IndentNum)).ToList();//单号查询
            }

            if (!string.IsNullOrEmpty(Timeones) && !string.IsNullOrEmpty(Timetows))//时间范围查询
            {
                DateTime time1 = Convert.ToDateTime(Timeones);
                DateTime time2 = Convert.ToDateTime(Timetows);
                listProcur = listProcur.Where(m => m.MakerDate >= time1 && m.MakerDate <= time2).ToList();

            }
            int count = listProcur.Count();
            List<ProcurementVo> listPro = listProcur.OrderBy(m => m.ToAudit).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<ProcurementVo> bsgrid = new Bsgrid<ProcurementVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listPro,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SelectIncoming(BsgridPage bsgridPage, string IncomeNum, string Timeone, string Timetow, string ToAudit,int BalanceStateID)//查询单据数据
        {
            var listIncoming = (from tbIncoming in myModels.PW_Incoming
                                join tbSuppli in myModels.SYS_Suppliers on tbIncoming.SuppliersID equals tbSuppli.SuppliersID
                                join tbWareho in myModels.SYS_Warehouse on tbIncoming.WarehouseID equals tbWareho.WarehouseID
                                join tbBalanc in myModels.SYS_BalanceState on tbIncoming.BalanceStateID equals tbBalanc.BalanceStateID
                                join tbUser in myModels.PW_User on tbIncoming.UserID equals tbUser.UserID
                                orderby tbIncoming.IncomingID descending
                                select new IncomeVo
                                {
                                    IncomingID = tbIncoming.IncomingID,
                                    SuppliersID = tbSuppli.SuppliersID,
                                    WarehouseID = tbWareho.WarehouseID,
                                    BalanceStateID = tbBalanc.BalanceStateID,
                                    UserID = tbUser.UserID,
                                    BalanceState = tbBalanc.BalanceState,
                                    SuppliersName = tbSuppli.SuppliersName.Trim(),
                                    SuppliersFirm = tbSuppli.SuppliersFirm.Trim(),
                                    MakerDates = tbIncoming.BilDate.ToString(),
                                    BilDate = tbIncoming.BilDate,
                                    Operator = tbIncoming.Operator.Trim(),
                                    IncomingNumber = tbIncoming.IncomingNumber,
                                    IndentNumber = tbIncoming.IndentNumber,
                                    DeliveryDeadlines = tbIncoming.PaymentDate.ToString(),
                                    WarehouseName = tbWareho.WarehouseName.Trim(),
                                    Aamount = tbIncoming.Aamount,
                                    ToAudit = tbIncoming.ToAudit,
                                    Auditor = tbIncoming.Auditor,
                                    AuditerDates = tbIncoming.AuditDate.ToString(),
                                    Rmark = tbIncoming.Rmark.Trim(),
                                }).ToList();

            if (!string.IsNullOrEmpty(IncomeNum))
            {
                listIncoming = listIncoming.Where(m => m.IncomingNumber.Contains(IncomeNum)).ToList();//单号查询
            }

            if (!string.IsNullOrEmpty(Timeone) && !string.IsNullOrEmpty(Timetow))//时间范围查询
            {
                DateTime Time1 = Convert.ToDateTime(Timeone);
                DateTime Time2 = Convert.ToDateTime(Timetow);
                listIncoming = listIncoming.Where(m => m.BilDate >= Time1 && m.BilDate <= Time2).ToList();
            }
            if (ToAudit == "1")
            {
                listIncoming = listIncoming.Where(m => m.ToAudit == true).ToList();// 审核
            }
            else if (ToAudit == "2")
            {
                listIncoming = listIncoming.Where(m => m.ToAudit == false).ToList();
            }
            if (BalanceStateID > 0)
            {
                listIncoming = listIncoming.Where(m => m.BalanceStateID == BalanceStateID).ToList();
            }
            int count = listIncoming.Count();
            List<IncomeVo> listPro = listIncoming.Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<IncomeVo> bsgrid = new Bsgrid<IncomeVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listPro,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }

        public ActionResult listWarehou(List<PW_Incoming> listIncome, List<SYS_WarehousingDetail> WarehousingDetail)//保存采购入库单
        {
            Session["WarehousingDetail"] = WarehousingDetail;
            var IncomingID = listIncome[0].IncomingID;
            try
            {
                listIncome[0].DocumentsTypeID = 3;//单据类型
                listIncome[0].BalanceStateID = 2;//结算状态
                if (IncomingID == 0)
                {
                    myModels.PW_Incoming.Add(listIncome[0]);
                }
                else
                {
                    myModels.Entry(listIncome[0]).State = System.Data.Entity.EntityState.Modified;
                }
                var list = myModels.PW_Incoming.Where(m => m.IncomingID == IncomingID).Select(m => new { m.WarehouseID }).SingleOrDefault();
                if (myModels.SaveChanges() > 0)
                {
                    
                    var WarehouseID = listIncome[0].WarehouseID;//修改保存后的仓库ID
                    IncomingID = listIncome[0].IncomingID;
                    if (WarehousingDetail != null)
                    {
                        List<int> oldID = new List<int>();//保存原来ID的集合
                        List<int> newID = new List<int>();//保存从页面传来的新ID的集合
                        List<int> DeleteID = new List<int>();//保存要删除的ID的集合，通过 Except() 算出前面两个集合的相同的元素。
                        var ProDetail = myModels.SYS_WarehousingDetail.Where(m => m.IncomingID == IncomingID).Select(m => new { m.WarehousingDetailID }).ToList();
                        foreach (var item in ProDetail)
                        {
                            oldID.Add(item.WarehousingDetailID);
                        }
                        for (int i = 0; i < WarehousingDetail.Count(); i++)
                        {
                            WarehousingDetail[i].IncomingID = IncomingID;
                            var WarehousingDetailID = WarehousingDetail[i].WarehousingDetailID;
                            if (WarehousingDetailID == 0)
                            {
                                myModels.SYS_WarehousingDetail.Add(WarehousingDetail[i]);
                            }
                            else
                            {
                                newID.Add(WarehousingDetail[i].WarehousingDetailID);
                                myModels.Entry(WarehousingDetail[i]).State = System.Data.Entity.EntityState.Modified;
                            }
                        }
                        DeleteID = oldID.Except(newID).ToList();//从某集合中删除与其另一个集合中相同的元素
                        foreach (var item in DeleteID)
                        {
                            var delete = myModels.SYS_WarehousingDetail.Where(m => m.WarehousingDetailID == item).Single();
                            myModels.SYS_WarehousingDetail.Remove(delete);
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
            return Json(IncomingID, JsonRequestBehavior.AllowGet);
        }

        public ActionResult ToAuditIncome(int IncomingID,int NewWarehouseID,string IndentNumber)//审核入库单
        {
            var WarehousingDetail = Session["WarehousingDetail"] as List<SYS_WarehousingDetail>;//入库明细
            try
            {
                var list = myModels.PW_Incoming.Where(m => m.IncomingID == IncomingID).Single();
                list.IncomingID = list.IncomingID;
                list.ToAudit = true;
                list.Auditor = Session["UserName"].ToString();
                list.AuditDate = DateTime.Now;
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                if (myModels.SaveChanges() > 0)
                {
                    if (!string.IsNullOrEmpty(IndentNumber))
                    {
                        var Proc = myModels.PW_Procurement.Where(m => m.IndentNumber == IndentNumber).SingleOrDefault();
                        Proc.Documents = "已进完货";
                        myModels.Entry(Proc).State = System.Data.Entity.EntityState.Modified;
                    }
                    foreach (var item in WarehousingDetail)//遍历明细
                    {
                        var WarehousingDetailID = item.WarehousingDetailID;//明细ID
                        var FittingsInfoID = item.FittingsInfoID;//配件ID
                        var Quantity = item.Quantity;//数量
                        var listFittings = (from tbFittingsInfo in myModels.SYS_FittingsInfo
                                            join tbFittingsType in myModels.SYS_FittingsType on tbFittingsInfo.FittingsTypeID equals tbFittingsType.FittingsTypeID
                                            join tbVehicleType in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleType.VehicleTypeID
                                            join tbSystemUnit in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnit.SystemUnitID
                                            where tbFittingsInfo.FittingsInfoID == FittingsInfoID
                                            select new FittingsInfoVo
                                            {
                                                FittingsCode = tbFittingsInfo.FittingsCode.Trim(),
                                                FittingsName = tbFittingsInfo.FittingsName.Trim(),
                                                VehicleType = tbVehicleType.VehicleType.Trim(),
                                                FittingsTypeName = tbFittingsType.FittingsTypeName.Trim(),
                                                SystemUnit=tbSystemUnit.SystemUnit.Trim()
                                            }).SingleOrDefault();
                        var FittingsCode = listFittings.FittingsCode;//配件编码
                        var listInventorys = myModels.SYS_Inventory.Where(m => m.WarehouseID == NewWarehouseID && m.FittingsCode == FittingsCode).SingleOrDefault();//修改后仓库
                        if (listInventorys != null)//修改库存(库存量)
                        {
                            listInventorys.InvenQuan = listInventorys.InvenQuan + Quantity;
                            myModels.Entry(listInventorys).State = System.Data.Entity.EntityState.Modified;
                        }
                        else
                        {//新增库存
                            SYS_Inventory sys_Inventory = new SYS_Inventory();
                            sys_Inventory.FittingsCode = listFittings.FittingsCode;
                            sys_Inventory.FittingsName = listFittings.FittingsName;
                            sys_Inventory.FittingsType = listFittings.FittingsTypeName;
                            sys_Inventory.VehicleType = listFittings.VehicleType;
                            sys_Inventory.SystemUnit = listFittings.SystemUnit;
                            sys_Inventory.WarehouseID = NewWarehouseID;
                            sys_Inventory.InvenQuan = Quantity;
                            sys_Inventory.SalePrice = item.UnitPrice;
                            myModels.SYS_Inventory.Add(sys_Inventory);
                        }
                        //修改配件信息表库存量
                        var listFit = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).SingleOrDefault();
                        listFit.InvenQuan = listFit.InvenQuan + Quantity;
                        myModels.Entry(listFit).State = System.Data.Entity.EntityState.Modified;
                    }
                }
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }

        public ActionResult NoToAuditIncome(int IncomingID, int NewWarehouseID, string IndentNumber)//反审核入库单
        {
            var listWarehousingDetail = myModels.SYS_WarehousingDetail.Where(m => m.IncomingID == IncomingID).ToList();
            try
            {
                var list = myModels.PW_Incoming.Where(m => m.IncomingID == IncomingID).Single();
                list.IncomingID = list.IncomingID;
                list.ToAudit = false;
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                if (myModels.SaveChanges() > 0)
                {
                    if (!string.IsNullOrEmpty(IndentNumber))
                    {
                        var Proc = myModels.PW_Procurement.Where(m => m.IndentNumber == IndentNumber).SingleOrDefault();
                        Proc.Documents = "未进货";
                        myModels.Entry(Proc).State = System.Data.Entity.EntityState.Modified;
                    }
                    foreach (var item in listWarehousingDetail)
                    {
                        var WarehousingDetailID = item.WarehousingDetailID;//明细ID
                        var FittingsInfoID = item.FittingsInfoID;//配件ID
                        var Quantity = item.Quantity;//数量
                        var listinfor = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).Select(m => new { m.FittingsCode }).SingleOrDefault();
                        var FittingsCode = listinfor.FittingsCode;//配件编码
                        var listInventorys = myModels.SYS_Inventory.Where(m => m.WarehouseID == NewWarehouseID && m.FittingsCode == FittingsCode).SingleOrDefault();//修改后仓库
                        if (listInventorys != null)
                        {
                            listInventorys.InvenQuan = listInventorys.InvenQuan - Quantity;
                            myModels.Entry(listInventorys).State = System.Data.Entity.EntityState.Modified;
                        }
                        //myModels.SaveChanges();
                        //修改配件信息表库存量
                        var listFit = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).SingleOrDefault();
                        listFit.InvenQuan = listFit.InvenQuan - Quantity;
                        myModels.Entry(listFit).State = System.Data.Entity.EntityState.Modified;
                    }
                }
                //myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        
        public ActionResult SelectWareDetail(int IncomingID)//查询入库明细数据
        {
            var listWareho = (from tbWarehousingl in myModels.SYS_WarehousingDetail
                              join tbFittingsInfo in myModels.SYS_FittingsInfo on tbWarehousingl.FittingsInfoID equals tbFittingsInfo.FittingsInfoID
                              join tbFittingsType in myModels.SYS_FittingsType on tbFittingsInfo.FittingsTypeID equals tbFittingsType.FittingsTypeID
                              join tbVehicleTypes in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleTypes.VehicleTypeID
                              join tbSystemUnites in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnites.SystemUnitID
                              where tbWarehousingl.IncomingID == IncomingID
                              select new WarehousinglVo
                              {
                                  WarehousingDetailID = tbWarehousingl.WarehousingDetailID,
                                  FittingsInfoID = tbFittingsInfo.FittingsInfoID,
                                  FittingsCode = tbFittingsInfo.FittingsCode.Trim(),
                                  FittingsTypeName = tbFittingsType.FittingsTypeName.Trim(),
                                  FittingsName = tbFittingsInfo.FittingsName.Trim(),
                                  Specification = tbFittingsInfo.Specification.Trim(),
                                  VehicleType = tbVehicleTypes.VehicleType.Trim(),
                                  SystemUnit = tbSystemUnites.SystemUnit.Trim(),
                                  Quantity = tbWarehousingl.Quantity,
                                  UnitPrice = tbWarehousingl.UnitPrice,
                                  Discount = tbWarehousingl.Discount,
                                  Amount = tbWarehousingl.Amount,
                                  Position = tbWarehousingl.Position!=null? tbWarehousingl.Position:"",
                                  Remark = tbWarehousingl.Remark!=null? tbWarehousingl.Remark:"无"
                              }).ToList();
            return Json(listWareho, JsonRequestBehavior.AllowGet);
        }

        public ActionResult DeleteIncome(int IncomingID)//删除采购入库单
        {
            try
            {
                var listIncom = myModels.PW_Incoming.Where(m => m.IncomingID == IncomingID).Single();
                var listIncomDetail = myModels.SYS_WarehousingDetail.Where(m => m.IncomingID == IncomingID).ToList();
                myModels.PW_Incoming.Remove(listIncom);
                myModels.SYS_WarehousingDetail.RemoveRange(listIncomDetail);
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