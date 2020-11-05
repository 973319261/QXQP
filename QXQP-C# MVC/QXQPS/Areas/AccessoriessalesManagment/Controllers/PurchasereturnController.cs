using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.AccessoriessalesManagment.Controllers
{
    public class PurchasereturnController : Controller
    {
        QXQPEntities myModels = new Models.QXQPEntities();
        // GET: AccessoriessalesManagment/Purchasereturn
        public ActionResult Purchasereturn()//采购退货
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
        
        public ActionResult MaintenanceNums()//退货单号
        {
            string MaintenanceNum = "";
            var date = "T" + DateTime.Now.ToString("yyyyMMdd");
            try
            {
                var list = myModels.PW_ReturnForm.Where(m => m.ReturnNumber.Contains(date)).OrderBy(m => m.ReturnNumber).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().ReturnNumber.Trim().Substring(9)) + 1;
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
        public ActionResult listReturnForm(List<PW_ReturnForm> listReturn, List<SYS_ReturnFormDetail> ReturnFormDetail)//保存采购退货单
        {
            ReturnJson returnJson = new ReturnJson();
            Session["ReturnFormDetail"] = ReturnFormDetail;
            var ReturnFormID = listReturn[0].ReturnFormID;
            var WarehouseID = listReturn[0].WarehouseID;
            var IncomAudit = listReturn[0].IncomingNumber;//入库单号
            var dd=0;
            try
            {
                listReturn[0].DocumentsTypeID = 5;//单据类型
                listReturn[0].BalanceStateID = 2;//结算状态
                if (ReturnFormID == 0)
                {
                    myModels.PW_ReturnForm.Add(listReturn[0]);
                }
                else
                {
                    myModels.Entry(listReturn[0]).State = System.Data.Entity.EntityState.Modified;
                }
                if (myModels.SaveChanges() > 0)
                {
                    //var WarehouseID = listIncome[0].WarehouseID;//修改保存后的仓库ID
                    ReturnFormID = listReturn[0].ReturnFormID;
                    WarehouseID = listReturn[0].WarehouseID;
                    if (ReturnFormDetail != null)
                    {
                        List<int> oldID = new List<int>();//保存原来ID的集合
                        List<int> newID = new List<int>();//保存从页面传来的新ID的集合
                        List<int> DeleteID = new List<int>();//保存要删除的ID的集合，通过 Except() 算出前面两个集合的相同的元素。
                        var RetuDetail = myModels.SYS_ReturnFormDetail.Where(m => m.ReturnFormID == ReturnFormID).Select(m => new { m.ReturnFormDetailID }).ToList();
                        foreach (var item in RetuDetail)
                        {
                            oldID.Add(item.ReturnFormDetailID);
                        }
                        for (int i = 0; i < ReturnFormDetail.Count(); i++)
                        {
                            var FittingsInfoID = ReturnFormDetail[i].FittingsInfoID;
                            var Quantity = ReturnFormDetail[i].Quantity;//明细数量
                            var FittingsInfo = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).SingleOrDefault();//编码
                            var FittingsCode = FittingsInfo.FittingsCode;
                            var B = myModels.SYS_Inventory.Where(m => m.WarehouseID == WarehouseID && m.FittingsCode == FittingsCode).SingleOrDefault();//库存量
                            if (B != null)
                            {
                                var InvenQuan = B.InvenQuan;
                                if (InvenQuan + Quantity >= 0)
                                {
                                    ReturnFormDetail[i].ReturnFormID = ReturnFormID;
                                    var ReturnFormDetailID = ReturnFormDetail[i].ReturnFormDetailID;
                                    if (ReturnFormDetailID == 0)
                                    {
                                        myModels.SYS_ReturnFormDetail.Add(ReturnFormDetail[i]);
                                    }
                                    else
                                    {
                                        myModels.Entry(ReturnFormDetail[i]).State = System.Data.Entity.EntityState.Modified;
                                    }
                                    newID.Add(ReturnFormDetailID);
                                   
                                }
                                else
                                {
                                    returnJson.State = false;
                                    returnJson.Text= "库存数量小于退货数量!";
                                    return Json(returnJson, JsonRequestBehavior.AllowGet);
                                }
                            }
                            else
                            {
                                returnJson.State = false;
                                returnJson.Text = "库存数量小于退货数量!";
                                return Json(returnJson, JsonRequestBehavior.AllowGet);
                            }
                        }
                        DeleteID = oldID.Except(newID).ToList();//从某集合中删除与其另一个集合中相同的元素
                        foreach (var item in DeleteID)
                        {
                            var delete = myModels.SYS_ReturnFormDetail.Where(m => m.ReturnFormDetailID == item).Single();
                            myModels.SYS_ReturnFormDetail.Remove(delete);
                        }
                        dd = myModels.SaveChanges();
                        if (dd > 0)
                        {
                            returnJson.State = true;
                            returnJson.Text = ReturnFormID.ToString();

                        }
                    }
                }
            }
            catch (Exception)
            {
                returnJson.State = false;
                returnJson.Text = "数据异常!";
            }
            return Json(returnJson, JsonRequestBehavior.AllowGet);

        }

        public ActionResult ToAuditReturnForm(int ReturnFormID, int NewWarehouseID)//审核退货单
        {
            var ReturnFormDetail = Session["ReturnFormDetail"] as List<SYS_ReturnFormDetail>;//退货明细
            try
            {
                var list = myModels.PW_ReturnForm.Where(m => m.ReturnFormID == ReturnFormID).Single();
                list.ReturnFormID = list.ReturnFormID;
                list.ToAudit = true;
                list.Auditor = Session["UserName"].ToString();
                list.AuditDate = DateTime.Now;
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                if (myModels.SaveChanges() > 0)
                {
                    if (ReturnFormDetail != null)
                    {
                        foreach (var item in ReturnFormDetail)//遍历明细
                        {
                            var ReturnFormDetailID = item.ReturnFormDetailID;//明细ID
                            var FittingsInfoID = item.FittingsInfoID;//配件ID
                            var Quantity = item.Quantity;//数量
                            var listinfor = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).Select(m => new { m.FittingsCode }).SingleOrDefault();
                            var FittingsCode = listinfor.FittingsCode;//配件编码
                            var listInventorys = myModels.SYS_Inventory.Where(m => m.WarehouseID == NewWarehouseID && m.FittingsCode == FittingsCode).SingleOrDefault();//修改后仓库
                            if (listInventorys != null)//修改库存(库存量)
                            {
                                listInventorys.InvenQuan = listInventorys.InvenQuan + Quantity;
                                myModels.Entry(listInventorys).State = System.Data.Entity.EntityState.Modified;
                            }
                            //修改配件信息表库存量
                            var listFit = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).SingleOrDefault();
                            listFit.InvenQuan = listFit.InvenQuan + Quantity;
                            myModels.Entry(listFit).State = System.Data.Entity.EntityState.Modified;
                        }
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

        public ActionResult NoToAuditReturnForm(int ReturnFormID, int NewWarehouseID)//反审核退货单
        {
            var listReturnFormDetail = myModels.SYS_ReturnFormDetail.Where(m => m.ReturnFormID == ReturnFormID).ToList();
            try
            {
                var list = myModels.PW_ReturnForm.Where(m => m.ReturnFormID == ReturnFormID).Single();
                list.ReturnFormID = list.ReturnFormID;
                list.ToAudit = false;
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                if (myModels.SaveChanges() > 0)
                {
                    foreach (var item in listReturnFormDetail)
                    {
                        var ReturnFormDetailID = item.ReturnFormDetailID;//明细ID
                        var FittingsInfoID = item.FittingsInfoID;//配件ID
                        var Quantity = item.Quantity;//数量
                        var listinfor = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).Select(m => new { m.FittingsCode }).SingleOrDefault();
                        var FittingsCode = listinfor.FittingsCode;//配件编码
                        var listInventorys = myModels.SYS_Inventory.Where(m => m.WarehouseID == NewWarehouseID && m.FittingsCode == FittingsCode).SingleOrDefault();//修改后仓库
                        listInventorys.InvenQuan = listInventorys.InvenQuan - Quantity;
                        myModels.Entry(listInventorys).State = System.Data.Entity.EntityState.Modified;
                        myModels.SaveChanges();
                        //修改配件信息表库存量
                        var listFit = myModels.SYS_FittingsInfo.Where(m => m.FittingsInfoID == FittingsInfoID).SingleOrDefault();
                        listFit.InvenQuan = listFit.InvenQuan - Quantity;
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

        public ActionResult SelectReturnForm(BsgridPage bsgridPage, string ReturnNum, string Timeone, string Timetow, string ToAudit)//查询单据数据
        {
            var listReturnForm = (from tbReturnForm in myModels.PW_ReturnForm
                                  join tbSuppli in myModels.SYS_Suppliers on tbReturnForm.SuppliersID equals tbSuppli.SuppliersID
                                  join tbWareho in myModels.SYS_Warehouse on tbReturnForm.WarehouseID equals tbWareho.WarehouseID
                                  join tbBalanc in myModels.SYS_BalanceState on tbReturnForm.BalanceStateID equals tbBalanc.BalanceStateID
                                  join tbUser in myModels.PW_User on tbReturnForm.UserID equals tbUser.UserID
                                  select new ReturnFormVo
                                  {
                                      ReturnFormID = tbReturnForm.ReturnFormID,
                                      SuppliersID = tbSuppli.SuppliersID,
                                      WarehouseID = tbWareho.WarehouseID,
                                      BalanceStateID = tbBalanc.BalanceStateID,
                                      UserID = tbUser.UserID,
                                      BalanceState = tbBalanc.BalanceState,
                                      SuppliersName = tbSuppli.SuppliersName.Trim(),
                                      SuppliersFirm = tbSuppli.SuppliersFirm.Trim(),
                                      MakerDates = tbReturnForm.SheetDate.ToString(),
                                      SheetDate = tbReturnForm.SheetDate,
                                      Maker = tbReturnForm.Maker.Trim(),
                                      ReturnNumber=tbReturnForm.ReturnNumber,
                                      IncomingNumber = tbReturnForm.IncomingNumber,
                                      DeliveryDeadlines = tbReturnForm.PaymentDate.ToString(),
                                      SingleDates = tbReturnForm.SheetDate.ToString(),
                                      WarehouseName = tbWareho.WarehouseName.Trim(),
                                      Amount = tbReturnForm.Amount,
                                      ToAudit = tbReturnForm.ToAudit,
                                      Auditor = tbReturnForm.Auditor.Trim(),
                                      AuditerDates = tbReturnForm.AuditDate.ToString(),
                                      Remark = tbReturnForm.Remark.Trim(),
                                  }).ToList();

            if (!string.IsNullOrEmpty(ReturnNum))
            {
                listReturnForm = listReturnForm.Where(m => m.ReturnNumber.Contains(ReturnNum)).ToList();//单号查询
            }

            if (!string.IsNullOrEmpty(Timeone) && !string.IsNullOrEmpty(Timetow))//时间范围查询
            {
                DateTime Time1 = Convert.ToDateTime(Timeone);
                DateTime Time2 = Convert.ToDateTime(Timetow);
                listReturnForm = listReturnForm.Where(m => m.SheetDate >= Time1 && m.SheetDate <= Time2).ToList();

            }
            if (ToAudit == "1")
            {
                listReturnForm = listReturnForm.Where(m => m.ToAudit == true).ToList();// 审核
            }
            else if (ToAudit == "2")
            {
                listReturnForm = listReturnForm.Where(m => m.ToAudit == false).ToList();
            }
            int count = listReturnForm.Count();
            List<ReturnFormVo> listReturn = listReturnForm.OrderBy(m => m.ToAudit).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<ReturnFormVo> bsgrid = new Bsgrid<ReturnFormVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listReturn,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }

        public ActionResult Selectincom(BsgridPage bsgridPage, string IncomeNum, string Timeones, string Timetows, string ToAudit)//查询入库单据数据
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

            if (!string.IsNullOrEmpty(Timeones) && !string.IsNullOrEmpty(Timetows))//时间范围查询
            {
                DateTime time1 = Convert.ToDateTime(Timeones);
                DateTime time2 = Convert.ToDateTime(Timetows);
                listIncoming = listIncoming.Where(m => m.BilDate >= time1 && m.BilDate <= time2).ToList();
            }
            if (ToAudit == "1")
            {
                listIncoming = listIncoming.Where(m => m.ToAudit == true).ToList();// 审核
            }
            else if (ToAudit == "2")
            {
                listIncoming = listIncoming.Where(m => m.ToAudit == false).ToList();
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

        public ActionResult SelectReturnDetail(int ReturnFormID)//查询退货明细数据
        {
            var listReturnDetail = (from tbReturnDetail in myModels.SYS_ReturnFormDetail
                                    join tbFittingsInfo in myModels.SYS_FittingsInfo on tbReturnDetail.FittingsInfoID equals tbFittingsInfo.FittingsInfoID
                                    join tbFittingsType in myModels.SYS_FittingsType on tbFittingsInfo.FittingsTypeID equals tbFittingsType.FittingsTypeID
                                    join tbVehicleTypes in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleTypes.VehicleTypeID
                                    join tbSystemUnites in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnites.SystemUnitID
                                    where tbReturnDetail.ReturnFormID == ReturnFormID
                                    select new ReturnDetailVo
                                    {
                                        ReturnFormDetailID = tbReturnDetail.ReturnFormDetailID,
                                        FittingsInfoID = tbFittingsInfo.FittingsInfoID,
                                        FittingsCode = tbFittingsInfo.FittingsCode.Trim(),
                                        FittingsTypeName = tbFittingsType.FittingsTypeName.Trim(),
                                        FittingsName = tbFittingsInfo.FittingsName.Trim(),
                                        Specification = tbFittingsInfo.Specification.Trim(),
                                        VehicleType = tbVehicleTypes.VehicleType.Trim(),
                                        SystemUnit = tbSystemUnites.SystemUnit.Trim(),
                                        Quantity = tbReturnDetail.Quantity,
                                        UnitPrice = tbReturnDetail.UnitPrice,
                                        Discount = tbReturnDetail.Discount,
                                        Amount = tbReturnDetail.Amount,
                                        Remark = tbReturnDetail.Remark!=null? tbReturnDetail.Remark:"无"
                                    }).ToList();
            return Json(listReturnDetail, JsonRequestBehavior.AllowGet);
        }
        public ActionResult DeleteReturn(int ReturnFormID)//删除采购退货单
        {
            try
            {
                var listReturnForm = myModels.PW_ReturnForm.Where(m => m.ReturnFormID == ReturnFormID).Single();
                var listReturnDetail = myModels.SYS_ReturnFormDetail.Where(m => m.ReturnFormID == ReturnFormID).ToList();
                myModels.PW_ReturnForm.Remove(listReturnForm);
                myModels.SYS_ReturnFormDetail.RemoveRange(listReturnDetail);
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