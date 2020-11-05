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
    public class PurchaseorderController : Controller
    {
        QXQPEntities myModels = new Models.QXQPEntities();
        // GET: AccessoriessalesManagment/Purchaseorder

        public ActionResult Purchaseorder()//采购订货
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
        public ActionResult MaintenanceNum()//订单号
        {

            string MaintenanceNum = "";
            var date = "J" + DateTime.Now.ToString("yyyyMMdd");
            try
            {
                var list = myModels.PW_Procurement.Where(m => m.IndentNumber.Contains(date)).OrderBy(m => m.IndentNumber).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().IndentNumber.Trim().Substring(9)) + 1;
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
        public ActionResult SuppliNum()//供应商号
        {
            string SuppliNum = "";
            var date = "GYSH-" + DateTime.Now.ToString("yyyyMMdd")+"-";
            try
            {
                var listxdas = myModels.SYS_Suppliers.Where(m => m.SuppliersFirm.Contains(date)).OrderBy(m => m.SuppliersFirm).ToList();
                if (listxdas.Count != 0)
                {
                    int num = Convert.ToInt32(listxdas.Last().SuppliersFirm.Trim().Substring(14)) + 1;
                    if (num < 10)
                    {
                        SuppliNum = date + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        SuppliNum = date + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        SuppliNum = date + "0" + num;
                    }
                }
                else
                {
                    SuppliNum = date + "0001";
                }
            }
            catch (Exception)
            {
                return Json("", JsonRequestBehavior.AllowGet);
            }
            return Json(SuppliNum, JsonRequestBehavior.AllowGet);
        }
        public ActionResult Datetime()
        {
            var date= DateTime.Now.ToString("yyyy-MM-dd");
            return Json(date, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectSuppliers(BsgridPage bsgridPage,string Suppliers)//查询供应商信息
        {
            var listSuppli = (from tbSuppliers in myModels.SYS_Suppliers
                              select new SupliersVo
                              {
                                  SuppliersID = tbSuppliers.SuppliersID,
                                  SuppliersFirm = tbSuppliers.SuppliersFirm,
                                  SuppliersName = tbSuppliers.SuppliersName,
                                  Address = tbSuppliers.Address,
                                  TelePhone = tbSuppliers.TelePhone,
                                  Contacts = tbSuppliers.Contacts,
                                  MainBusiness = tbSuppliers.MainBusiness,
                                  Facsimile = tbSuppliers.Facsimile,
                                  Mailbox = tbSuppliers.Mailbox,
                                  MobilePhone = tbSuppliers.MobilePhone,
                                  Url = tbSuppliers.Url,
                                  PinYinCode = tbSuppliers.PinYinCode,
                                  OpenBank = tbSuppliers.OpenBank,
                                  TaxNumber = tbSuppliers.TaxNumber,
                                  InputPerson = tbSuppliers.InputPerson,
                                  StorageTele = tbSuppliers.StorageTele,
                                  StorageAdd = tbSuppliers.StorageAdd,
                                  ToDeactivate = tbSuppliers.ToDeactivate,
                                  DeserveMoney = tbSuppliers.DeserveMoney,
                                  BalanceMode = tbSuppliers.BalanceMode,
                                  Remark = tbSuppliers.Remark
                              }).ToList();
            if (!string.IsNullOrEmpty(Suppliers))
            {
                listSuppli = listSuppli.Where(m => m.SuppliersName.Contains(Suppliers)).ToList();
            }
            int Count = listSuppli.Count();
            List<SupliersVo> listSuppliers = listSuppli.OrderBy(m => m.SuppliersID)
                .Skip(bsgridPage.GetStartIndex())
                .Take(bsgridPage.pageSize)
                .ToList();
            Bsgrid<SupliersVo> bsgrid = new Bsgrid<SupliersVo>()
            {
                success = true,
                totalRows = Count,
                curPage = bsgridPage.curPage,
                data = listSuppliers
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        
        public ActionResult InsertSuppliers(SYS_Suppliers sys_Suppliers)//新增或修改供应商表
        {
            int SuppliersID = sys_Suppliers.SuppliersID;
            try
            {
                if (SuppliersID == 0)
                {
                    myModels.SYS_Suppliers.Add(sys_Suppliers);
                    myModels.SaveChanges();
                }
                else
                {
                    myModels.Entry(sys_Suppliers).State = System.Data.Entity.EntityState.Modified;
                    myModels.SaveChanges();
                }
            }
            catch (Exception)
            {

                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }

        public ActionResult listProcur(List<PW_Procurement> listProcurement, List<SYS_ProcurementDetail> listFitting)//保存采购订单
        {
            var ProcurementID = 0;
            try
            {
                if (listProcurement[0].ProcurementID == 0)
                {
                    listProcurement[0].Documents = "未进货";//单据状况
                    myModels.PW_Procurement.Add(listProcurement[0]);
                }
                else
                {
                    listProcurement[0].Documents = "未进货";
                    myModels.Entry(listProcurement[0]).State = System.Data.Entity.EntityState.Modified;
                }
                if (myModels.SaveChanges() > 0)
                {
                    ProcurementID = listProcurement[0].ProcurementID;
                    if (listFitting != null)
                    {
                        List<int> oldID = new List<int>();//保存原来ID的集合
                        List<int> newID = new List<int>();//保存从页面传来的新ID的集合
                        List<int> DeleteID = new List<int>();//保存要删除的ID的集合，通过 Except() 算出前面两个集合的相同的元素。
                        var ProDetail = myModels.SYS_ProcurementDetail.Where(m => m.ProcurementID == ProcurementID).Select(m => new { m.ProcurementDetailID }).ToList();
                        foreach(var item in ProDetail)
                        {
                            oldID.Add(item.ProcurementDetailID);
                        }
                        for (int i = 0; i < listFitting.Count(); i++)
                        {
                            listFitting[i].ProcurementID = ProcurementID;
                            var ProcurementDetailID = listFitting[i].ProcurementDetailID;
                            if (ProcurementDetailID == 0)
                            {
                                myModels.SYS_ProcurementDetail.Add(listFitting[i]);
                            }
                            else
                            {
                                newID.Add(listFitting[i].ProcurementDetailID);
                                myModels.Entry(listFitting[i]).State = System.Data.Entity.EntityState.Modified;
                            }
                        }
                        DeleteID = oldID.Except(newID).ToList();//从某集合中删除与其另一个集合中相同的元素
                        foreach(var item in DeleteID)
                        {
                            var delete = myModels.SYS_ProcurementDetail.Where(m => m.ProcurementDetailID == item).Single();
                            myModels.SYS_ProcurementDetail.Remove(delete);
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
            return Json(ProcurementID, JsonRequestBehavior.AllowGet);
        }

        public ActionResult ToAuditProcur(int ProcurementID)//审核采购单
        {
            try
            {
                var list = myModels.PW_Procurement.Where(m => m.ProcurementID == ProcurementID).Single();
                list.ProcurementID = list.ProcurementID;
                list.ToAudit = true;
                list.Auditer= Session["UserName"].ToString();
                list.AuditerDate = DateTime.Now;
                myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }

        public ActionResult NoToAuditProcur(int ProcurementID)//反审核采购单
        {
            try
            {
                var list = myModels.PW_Procurement.Where(m => m.ProcurementID == ProcurementID).Single();
                list.ProcurementID = list.ProcurementID;
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

        public ActionResult SelectProcurement(BsgridPage bsgridPage,string IndentNum, string Dateone,string Datetow, string Documents,string ToAudit)//查询单据数据
        {
            var listProcur = (from tbProcur in myModels.PW_Procurement
                              join tbSuppli in myModels.SYS_Suppliers on tbProcur.SuppliersID equals tbSuppli.SuppliersID
                              join tbWareho in myModels.SYS_Warehouse on tbProcur.WarehouseID equals tbWareho.WarehouseID
                              join tbUser in myModels.PW_User on tbProcur.UserID equals tbUser.UserID
                              select new ProcurementVo
                              {
                                  ProcurementID = tbProcur.ProcurementID,
                                  SuppliersID = tbSuppli.SuppliersID,
                                  WarehouseID = tbWareho.WarehouseID,
                                  UserID = tbUser.UserID,
                                  SuppliersName = tbSuppli.SuppliersName.Trim(),
                                  SuppliersFirm = tbSuppli.SuppliersFirm.Trim(),
                                  MakerDates = tbProcur.MakerDate.ToString(),
                                  MakerDate = tbProcur.MakerDate,
                                  Maker = tbProcur.Maker.Trim(),
                                  IndentNumber = tbProcur.IndentNumber,
                                  DeliveryDeadlines = tbProcur.DeliveryDeadline.ToString(),
                                  WarehouseName = tbWareho.WarehouseName.Trim(),
                                  Aamount = tbProcur.Aamount,
                                  Handsel = tbProcur.Handsel!=null? tbProcur.Handsel:0,//判断是否为空？是，为本身；否，为0 。
                                  ToAudit = tbProcur.ToAudit,
                                  Documents=tbProcur.Documents,
                                  Auditer = tbProcur.Auditer.Trim(),
                                  AuditerDates = tbProcur.AuditerDate.ToString(),
                                  Remark = tbProcur.Remark.Trim(),
                              }).ToList();

            if (!string.IsNullOrEmpty(IndentNum))
            {
                listProcur = listProcur.Where(m => m.IndentNumber.Contains(IndentNum)).ToList();//单号查询
            }
            
            if (!string.IsNullOrEmpty(Documents))
            {
                listProcur = listProcur.Where(m => m.Documents == Documents).ToList();//单据状况
            }
            if (ToAudit == "1")
            {
                listProcur = listProcur.Where(m => m.ToAudit == true).ToList();// 审核
            }
            else if (ToAudit == "2")
            {
                listProcur = listProcur.Where(m => m.ToAudit == false).ToList();
            }
            if (!string.IsNullOrEmpty(Dateone) && !string.IsNullOrEmpty(Datetow))//时间范围查询
            {
                DateTime Time1 = Convert.ToDateTime(Dateone);
                DateTime Time2 = Convert.ToDateTime(Datetow);
                listProcur = listProcur.Where(m => m.MakerDate >= Time1 && m.MakerDate <= Time2).ToList();

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

        public ActionResult SelectProDetail(int ProcurementID)//查询采购明细数据
        {
                var listDetail = (from tbProcurDetail in myModels.SYS_ProcurementDetail
                                  join tbFittingsInfo in myModels.SYS_FittingsInfo on tbProcurDetail.FittingsInfoID equals tbFittingsInfo.FittingsInfoID
                                  join tbFittingsType in myModels.SYS_FittingsType on tbFittingsInfo.FittingsTypeID equals tbFittingsType.FittingsTypeID
                                  join tbVehicleTypes in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleTypes.VehicleTypeID
                                  join tbSystemUnites in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnites.SystemUnitID
                                  where tbProcurDetail.ProcurementID == ProcurementID
                                  select new ProDetailVo
                                  {
                                      ProcurementDetailID = tbProcurDetail.ProcurementDetailID,
                                      FittingsInfoID = tbFittingsInfo.FittingsInfoID,
                                      FittingsCode = tbFittingsInfo.FittingsCode.Trim(),
                                      FittingsTypeName = tbFittingsType.FittingsTypeName.Trim(),
                                      FittingsName = tbFittingsInfo.FittingsName.Trim(),
                                      Specification = tbFittingsInfo.Specification.Trim(),
                                      VehicleType = tbVehicleTypes.VehicleType.Trim(),
                                      SystemUnit = tbSystemUnites.SystemUnit.Trim(),
                                      Quantity = tbProcurDetail.Quantity,
                                      UnitPrice = tbProcurDetail.UnitPrice,
                                      Discount = tbProcurDetail.Discount,
                                      Amount = tbProcurDetail.Amount,
                                      FirmQuantity=tbProcurDetail.FirmQuantity
                                  }).ToList();
            return Json(listDetail, JsonRequestBehavior.AllowGet);
        }

        public ActionResult DeleteProcurement(int ProcurementID)//删除采购订单
        {
            try
            {
                var listProcur = myModels.PW_Procurement.Where(m => m.ProcurementID == ProcurementID).Single();
                var listproDetail = myModels.SYS_ProcurementDetail.Where(m => m.ProcurementID == ProcurementID).ToList();
                myModels.PW_Procurement.Remove(listProcur);
                myModels.SYS_ProcurementDetail.RemoveRange(listproDetail);
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