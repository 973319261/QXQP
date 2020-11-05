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
    public class CollageController : Controller
    {
        // GET: MechanicsManagment/Collage
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult DraftBack()//维修领料
        {
            return View();
        }
        public ActionResult Collage()//领料
        {
            try
            {
                ViewBag.UserTypeName = Session["UserName"].ToString().Trim();
                ViewBag.ReceptionID = Session["ReceptionID"];
                return View();
            }
            catch (Exception)
            {
                //无法获取session 重定向到登录界面 重新登录
                return Redirect("/Home/Login");
            }
        }
        [HttpPost]
        public ActionResult Collage(int ReceptionID)//领料
        {
            Session["ReceptionID"] = ReceptionID;
            return View();
        }
        public ActionResult Restock()//退料
        {
                try
                {
                    ViewBag.UserTypeName = Session["UserName"].ToString().Trim();
                    ViewBag.ReceptionID = Session["receptionID"];
                    return View();
                }
                catch (Exception)
                {
                    //无法获取session 重定向到登录界面 重新登录
                    return Redirect("/Home/Login");
                }
        }
        [HttpPost]
        public ActionResult Restock(List<SYS_CollageDetai> listCollageDetai)//领料
        {
            Session["listCollageDetai"] = listCollageDetai;
            return View();
        }
        public ActionResult ArrlistToRestock()//Session返回列表
        {
            var list = Session["listCollageDetai"];
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult CleanArrlist()//清空列表
        {
            Session["receptionID"]=null;
            Session["listCollageDetai"] = null;
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectReception(BsgridPage bsgridPage, string StartDate, string EndDate,string CollageState,string CarNum)//查询客户接待单据信息
        {
            var lingItem = (from tbReception in myModels.PW_Reception
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
                                BalanceDateTo = tbReception.BalanceDate,//结算日期（判断）
                                Amount = tbReception.Amount,//总金额
                                AmountPaid = tbReception.AmountPaid,//应收金额
                                Describe = tbReception.Describe.Trim(),//描述
                                ToAudit = tbReception.ToAudit,//审核否
                                CompletionDates = tbReception.CompletionDate.ToString()!=null? tbReception.CompletionDate.ToString() : "0000-00-00",//完工日期
                                ToSendWork = tbReception.ToSendWork,//派工状态
                                CollageState = tbReception.CollageState.Trim(),//领料状态
                                MaintenAmount = tbReception.MaintenAmount,//维修总费
                            }).ToList();
            #region 拼接条件
           if (StartDate != "" && EndDate != "")
            {
                DateTime startDate = Convert.ToDateTime(StartDate);
                DateTime endDate = Convert.ToDateTime(EndDate);
                lingItem = lingItem.Where(m => m.BalanceDateTo >= startDate.Date && m.BalanceDateTo <= endDate.Date).ToList();
            }
            if (StartDate != "" && EndDate == "")
            {
                DateTime startDate = Convert.ToDateTime(StartDate);
                lingItem = lingItem.Where(m => m.BalanceDateTo >= startDate.Date).ToList();
            }
            if (StartDate == "" && EndDate != "")
            {
                DateTime endDate = Convert.ToDateTime(EndDate);
                lingItem = lingItem.Where(m =>m.BalanceDateTo <= endDate.Date).ToList();
            }
            if (!string.IsNullOrEmpty(CollageState))
            {
                lingItem = lingItem.Where(m => m.CollageState== CollageState).ToList();
            }
            if (!string.IsNullOrEmpty(CarNum))
            {
                lingItem = lingItem.Where(m => m.CarNum.Contains(CarNum)).ToList();
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
        
        public ActionResult ArrlistToCollage(int ReceptionID)//查询领料主信息
        {
            var list = myModels.PW_Reception.Where(m => m.ReceptionID == ReceptionID).Select(m => new
            {
                ReceptionID = m.ReceptionID,
                Owner = m.Owner.Trim(),
                CarNum = m.CarNum.Trim(),
                MaintenanceNum = m.MaintenanceNum.Trim(),
                CollageState=m.CollageState.Trim()!="已退料"? m.CollageState.Trim():"未领料",
            }).Single();
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectCollageData(int ReceptionID)//查询领料主信息
        {
            var list = myModels.PW_Collage.Where(m => m.ReceptionID == ReceptionID).Select(m=>new CollageVo{
                CollageID=m.CollageID,
                AuditDate = m.AuditDate.ToString(),
                ForeMan=m.ForeMan,
                CollageDate=m.CollageDate.ToString(),
                Amount= m.Amount,
                ToAudit= m.ToAudit,
                Operator= m.Operator,
                Auditor=m.Auditor,
                Remark=m.Remark,
              }).SingleOrDefault();
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult DaoRuPeiJian(BsgridPage bsgridPage,int ReceptionID)//查询领料导入配件信息
        {
            var lingItem = (from tbRecProductDetail in myModels.SYS_RecProductDetail
                            join tbMaintainability in myModels.SYS_Maintainability on tbRecProductDetail.MaintainabilityID equals tbMaintainability.MaintainabilityID
                            where tbRecProductDetail.ReceptionID== ReceptionID
                            select new ProductVo
                            {
                                ReceptionID = tbRecProductDetail.ReceptionID,
                                RecProductDetailID =tbRecProductDetail.RecProductDetailID,
                                MaintainabilityID=tbRecProductDetail.MaintainabilityID,
                                MaintainabilityName=tbMaintainability.MaintainabilityName,
                                FittingsCode=tbRecProductDetail.FittingsCode.Trim(),
                                FittingsName=tbRecProductDetail.FittingsName.Trim(),
                                SystemUnit=tbRecProductDetail.SystemUnit.Trim(),
                                Quantity = tbRecProductDetail.Quantity,
                                UnitPrice = tbRecProductDetail.UnitPrice,
                                FittingsSpec=tbRecProductDetail.FittingsSpec.Trim()!=null? tbRecProductDetail.FittingsSpec.Trim():"",
                                Amount =tbRecProductDetail.Amount,
                                Remark=tbRecProductDetail.Remark.Trim(),
                                VehicleType=tbRecProductDetail.VehicleType.Trim()
                            }).ToList();
            int count = lingItem.Count();
            List<ProductVo> listReception = lingItem.OrderBy(m => m.PreProductDetailID).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<ProductVo> bsgrid = new Bsgrid<ProductVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listReception
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ListCollage(List<PW_Collage> listCollage, List<SYS_CollageDetai> listCollageDetai)//保存领料主页面
        {
            ArrayList list = new ArrayList();
            try
            {
                try
                {
                    var Auditor = Session["UserName"].ToString().Trim();//审核人
                    var Operator = Session["UserName"].ToString().Trim();//操作人
                    var CollageStates = "已领料".Trim();
                    var AuditDate = DateTime.Now;//审核时间
                    list.Add(Auditor);
                    list.Add(Operator);
                    list.Add(AuditDate.ToString());
                    list.Add(CollageStates);
                    listCollage[0].Auditor = Auditor;
                    listCollage[0].Operator = Operator;
                    listCollage[0].AuditDate = AuditDate;
                    listCollage[0].ToAudit = true;
                }
                catch (Exception)
                {
                    return Redirect("/Home/Login");
                }
                var CollageState = "已领料".Trim();
                if (listCollage[0].CollageID == 0)
                {
                    myModels.PW_Collage.Add(listCollage[0]);
                }
                else {
                    myModels.Entry(listCollage[0]).State = System.Data.Entity.EntityState.Modified;
                }
                if (myModels.SaveChanges() > 0)
                {
                    var CollageID = listCollage[0].CollageID;
                    if (listCollageDetai!=null)
                    {
                        List<int> oldID = new List<int>();//原来ID
                        List<int> newID = new List<int>();//新ID
                        List<int> listdelectID = new List<int>();//需要删除的ID集合
                        var lists = myModels.SYS_CollageDetai.Where(m => m.CollageID == CollageID).Select(m => new { m.CollageDetaiID }).ToList();
                        foreach (var item in lists)
                        {
                            oldID.Add(item.CollageDetaiID);
                        }
                        for (int i = 0; i < listCollageDetai.Count; i++)
                        {
                            listCollageDetai[i].CollageID = CollageID;
                            var CollageDetaiID = listCollageDetai[i].CollageDetaiID;
                            var FittingsCode = listCollageDetai[i].FittingsCode.Trim();//配件编码
                            var VehicleType = listCollageDetai[i].VehicleType.Trim();//车型
                            var InvenQuan = listCollageDetai[i].Quantity;//数量
                            var WarehouseID = listCollageDetai[i].WarehouseID;//仓库ID
                            var listInventory = myModels.SYS_Inventory.Where(m => m.FittingsCode == FittingsCode&&m.WarehouseID== WarehouseID).SingleOrDefault();//库存表
                            var listInventorys = myModels.SYS_Inventory.Where(m => m.FittingsCode == FittingsCode).ToList();//库存表
                            var listFittingsInfos = (from tbFittingsInfo in myModels.SYS_FittingsInfo where tbFittingsInfo.FittingsCode == FittingsCode select tbFittingsInfo).SingleOrDefault();
                            var listFittingsInfo = (from tbFittingsInfo in myModels.SYS_FittingsInfo
                                                    join tbFittingsType in myModels.SYS_FittingsType on tbFittingsInfo.FittingsTypeID equals tbFittingsType.FittingsTypeID
                                                    join tbVehicleType in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleType.VehicleTypeID
                                                    join tbSystemUnit in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnit.SystemUnitID
                                                    where tbFittingsInfo.FittingsCode== FittingsCode
                                                    select new FittingsInfoVo
                                                    {
                                                        FittingsCode=tbFittingsInfo.FittingsCode.Trim(),
                                                        FittingsName=tbFittingsInfo.FittingsName.Trim(),
                                                        Intake=tbFittingsInfo.Intake,
                                                        SalesPrice=tbFittingsInfo.SalesPrice,
                                                        WholesalePrice =tbFittingsInfo.WholesalePrice,
                                                        FittingsTypeName=tbFittingsType.FittingsTypeName.Trim(),
                                                        VehicleType=tbVehicleType.VehicleType.Trim(),
                                                        SystemUnit=tbSystemUnit.SystemUnit.Trim(),
                                                    }).SingleOrDefault();

                            if (CollageDetaiID == 0)
                            {
                                myModels.SYS_CollageDetai.Add(listCollageDetai[i]);//新增
                            }
                            else {
                                newID.Add(listCollageDetai[i].CollageDetaiID);
                                myModels.Entry(listCollageDetai[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                            if (listFittingsInfos != null)
                            {
                                var lis = myModels.SYS_CollageDetai.Where(m => m.CollageDetaiID == CollageDetaiID).Select(m => new { m.FittingsCode, m.WarehouseID, m.Quantity }).SingleOrDefault();//查询原来明细信息
                                if (lis != null)
                                {
                                    var Quantity = lis.Quantity;
                                    listFittingsInfos.InvenQuan = listFittingsInfos.InvenQuan + (Quantity - InvenQuan);//修改配件表数量
                                }
                                else
                                {
                                    var liInvenQuan = listFittingsInfos.InvenQuan!=null? listFittingsInfos.InvenQuan:0;
                                    listFittingsInfos.InvenQuan = liInvenQuan - InvenQuan;//修改配件表数量
                                }
                                myModels.Entry(listFittingsInfos).State = System.Data.Entity.EntityState.Modified;
                            }
                            if (listInventory != null)//修改库存信息
                            {
                                if (CollageDetaiID != 0)
                                {
                                    var lis = myModels.SYS_CollageDetai.Where(m => m.CollageDetaiID == CollageDetaiID).Select(m => new { m.FittingsCode, m.WarehouseID , m.Quantity }).SingleOrDefault();//查询原来明细信息
                                    if (lis.WarehouseID != WarehouseID)
                                    {
                                        var liWarehouseID = lis.WarehouseID;
                                        var liFittingsCode = lis.FittingsCode;
                                        var oldInvenQuan = myModels.SYS_Inventory.Where(m => m.WarehouseID == liWarehouseID && m.FittingsCode == liFittingsCode).SingleOrDefault();//旧库存表
                                        oldInvenQuan.InvenQuan = oldInvenQuan.InvenQuan + InvenQuan;
                                        myModels.Entry(oldInvenQuan).State = System.Data.Entity.EntityState.Modified;//修改
                                        listInventory.InvenQuan = listInventory.InvenQuan - InvenQuan;
                                    }
                                    else
                                    {
                                        var Quantity = lis.Quantity;
                                        listInventory.InvenQuan = listInventory.InvenQuan + (Quantity - InvenQuan);
                                    }
                                }
                                else 
                                {
                                   listInventory.InvenQuan = listInventory.InvenQuan  - InvenQuan;
                                }
                                myModels.Entry(listInventory).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                            else//新增库存信息
                            {
                                SYS_Inventory sys_Inventory = new SYS_Inventory();
                                sys_Inventory.Position = listCollageDetai[i].Position;
                                sys_Inventory.WarehouseID = listCollageDetai[i].WarehouseID;
                                if (listFittingsInfo != null)
                                {
                                    sys_Inventory.FittingsType = listFittingsInfo.FittingsTypeName;
                                    sys_Inventory.FittingsCode = listFittingsInfo.FittingsCode;
                                    sys_Inventory.FittingsName = listFittingsInfo.FittingsName;
                                    sys_Inventory.VehicleType = listFittingsInfo.VehicleType;
                                    sys_Inventory.SystemUnit = listFittingsInfo.SystemUnit;
                                    sys_Inventory.InvenQuan = -InvenQuan;
                                    sys_Inventory.NewIntake = listFittingsInfo.Intake;
                                    sys_Inventory.SalePrice = listFittingsInfo.SalesPrice;
                                    sys_Inventory.WholePrice = listFittingsInfo.WholesalePrice;
                                    myModels.SYS_Inventory.Add(sys_Inventory);//新增
                                }
                                else
                                {
                                    sys_Inventory.NewIntake = 0;
                                    sys_Inventory.WholePrice = 0;
                                    sys_Inventory.FittingsCode = listCollageDetai[i].FittingsCode;
                                    sys_Inventory.FittingsName = listCollageDetai[i].FittingsName;
                                    sys_Inventory.VehicleType = listCollageDetai[i].VehicleType;
                                    sys_Inventory.SystemUnit = listCollageDetai[i].SystemUnit;
                                    sys_Inventory.InvenQuan = -listCollageDetai[i].Quantity;
                                    sys_Inventory.SalePrice = listCollageDetai[i].UnitPrice;
                                    sys_Inventory.Position = listCollageDetai[i].Position;
                                    myModels.SYS_Inventory.Add(sys_Inventory);//新增
                                }
                            }
                            myModels.SaveChanges();
                        }
                        var ReceptionID = listCollage[0].ReceptionID;
                        var listReception = (from tbReception in myModels.PW_Reception where tbReception.ReceptionID == ReceptionID select tbReception).Single();
                        listReception.CollageState = CollageState;
                        myModels.Entry(listReception).State = System.Data.Entity.EntityState.Modified;
                        myModels.SaveChanges();
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                        foreach (var item in listdelectID)
                        {
                            var listQuantity = myModels.SYS_CollageDetai.Where(m => m.CollageDetaiID == item).Select(m => new { m.Quantity, m.FittingsCode ,m.WarehouseID }).SingleOrDefault();

                            var FittingsCode = listQuantity.FittingsCode;
                            var WarehouseID = listQuantity.WarehouseID;
                            var Quantity = listQuantity.Quantity;
                            var listInventory = myModels.SYS_Inventory.Where(m => m.FittingsCode == FittingsCode && m.WarehouseID == WarehouseID).SingleOrDefault();//库存表
                            var listFittingsInfos = (from tbFittingsInfo in myModels.SYS_FittingsInfo where tbFittingsInfo.FittingsCode == FittingsCode select tbFittingsInfo).SingleOrDefault();//配件表
                            listInventory.InvenQuan = listInventory.InvenQuan + Quantity;
                            listFittingsInfos.InvenQuan = listFittingsInfos.InvenQuan + Quantity;
                            myModels.Entry(listInventory).State = System.Data.Entity.EntityState.Modified;//修改库存数量
                            myModels.Entry(listFittingsInfos).State = System.Data.Entity.EntityState.Modified;//修改配件数量
                            var listdelect = myModels.SYS_CollageDetai.Where(m => m.CollageDetaiID == item).Single();//删除
                            myModels.SYS_CollageDetai.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_CollageDetai.Where(m => m.CollageID == CollageID).ToList();//删除全部
                        myModels.SYS_CollageDetai.RemoveRange(listdelect);
                    }
                    myModels.SaveChanges();
                }
            }
            catch (Exception e)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectCollage(BsgridPage bsgridPage)//查询领料单据信息
        {
            var lingItem = (from tbCollage in myModels.PW_Collage
                            join tbReception in myModels.PW_Reception on tbCollage.ReceptionID equals tbReception.ReceptionID
                            select new CollageVo
                            {
                                CollageID=tbCollage.CollageID,//领料ID
                                ReceptionID=tbReception.ReceptionID,
                                CarNum=tbReception.CarNum.Trim(),//车牌
                                Owner=tbReception.Owner.Trim(),//车主
                                MaintenanceNum=tbReception.MaintenanceNum.Trim(),//结算单号
                                CollageState = tbReception.CollageState.Trim() != "已退料" ? tbReception.CollageState.Trim() : "未领料",
                                ForeMan =tbCollage.ForeMan.Trim(),//领料员
                                Operator=tbCollage.Operator.Trim(),//操作人
                                Auditor=tbCollage.Auditor.Trim(),//审核人
                                Amount=tbCollage.Amount,//总金额
                                AuditDate=tbCollage.AuditDate.ToString(),//审核时间
                                CollageDate=tbCollage.CollageDate.ToString(),//领料时间
                                ToAudit=tbCollage.ToAudit,//审核否
                                Remark=tbCollage.Remark.Trim(),//备注
                            }).ToList();
            int count = lingItem.Count();
            List<CollageVo> listCollage = lingItem.OrderBy(m => m.ToAudit).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<CollageVo> bsgrid = new Bsgrid<CollageVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listCollage
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectCollageDetai(int CollageID)//查询领料明细信息
        {
            try
            {
                var listCollageDetai = myModels.SYS_CollageDetai.Where(m => m.CollageID == CollageID).ToList();
                return Json(listCollageDetai, JsonRequestBehavior.AllowGet);
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult SelectCollageID(int ReceptionID)//ReceptionID查询CollageID 退料模态框
        {
            Session["receptionID"] = ReceptionID;
            var CollageID = 0;
            var list = myModels.PW_Collage.Where(m => m.ReceptionID == ReceptionID).Select(m => new { m.CollageID }).SingleOrDefault();
            if (list != null)
            {
                CollageID = list.CollageID;
            }
            return Json(CollageID, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SelectTableCollageDetai(BsgridPage bsgridPage, int CollageID)//查询退料明细信息
        {
            var lingItem = (from tbCollageDetai in myModels.SYS_CollageDetai
                            join tbMaintainability in myModels.SYS_Maintainability on tbCollageDetai.MaintainabilityID equals tbMaintainability.MaintainabilityID
                            join tbWarehouse in myModels.SYS_Warehouse on tbCollageDetai.WarehouseID equals tbWarehouse.WarehouseID
                            join tbCollage in myModels.PW_Collage on tbCollageDetai.CollageID equals tbCollage.CollageID
                            join tbReception in myModels.PW_Reception on tbCollage.ReceptionID equals tbReception.ReceptionID
                            where tbCollageDetai.CollageID == CollageID
                            select new CollageDetaiVo
                            {
                                CollageID = tbCollageDetai.CollageID,
                                CollageDetaiID=tbCollageDetai.CollageDetaiID,
                                MaintainabilityID = tbCollageDetai.MaintainabilityID,
                                MaintainabilityName = tbMaintainability.MaintainabilityName.Trim(),
                                MaintenanceNum=tbReception.MaintenanceNum.Trim(),
                                WarehouseID = tbCollageDetai.WarehouseID,
                                WarehouseName = tbWarehouse.WarehouseName.Trim(),
                                Quantity = tbCollageDetai.Quantity,
                                UnitPrice = tbCollageDetai.UnitPrice,
                                Amount = tbCollageDetai.Amount,
                                Position = tbCollageDetai.Position.Trim(),
                                VehicleType = tbCollageDetai.VehicleType.Trim(),
                                FittingsName = tbCollageDetai.FittingsName.Trim(),
                                FittingsCode = tbCollageDetai.FittingsCode.Trim(),
                                FittingsSpec = tbCollageDetai.FittingsSpec.Trim(),
                                SystemUnit = tbCollageDetai.SystemUnit.Trim(),
                            }).ToList();
            int count = lingItem.Count();
            List<CollageDetaiVo> listCollageDetai = lingItem.OrderBy(m => m.FittingsCode).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<CollageDetaiVo> bsgrid = new Bsgrid<CollageDetaiVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listCollageDetai
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectRestock(int ReceptionID)//查询退料主信息
        {
            var list = (from tbReception in myModels.PW_Reception
                        join tbCollage in myModels.PW_Collage on tbReception.ReceptionID equals tbCollage.ReceptionID
                        where tbReception.ReceptionID==ReceptionID
                        select new CollageVo
                        {
                            ReceptionID = tbReception.ReceptionID,
                            Owner = tbReception.Owner.Trim(),
                            CarNum = tbReception.CarNum.Trim(),
                            MaintenanceNum = tbReception.MaintenanceNum.Trim(),
                            CollageID = tbCollage.CollageID,
                            StrAudit = tbCollage.ToAudit!=true?"未审核": "已审核",
                        }).SingleOrDefault();
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public class RestockDetail
        {
            public int CollageDetaiID { get; set; }
            public decimal Quantity { get; set; }
            public string FittingsCode { get; set; }
            public int WarehouseID { get; set; }
        }
        public ActionResult JudgingQuantity(List<RestockDetail> listRestockDetai)//判断领料数量
        {
            ReturnJson returnJosn = new ReturnJson();
            returnJosn.State = true;
            foreach (var item in listRestockDetai)
            {
                var listCollage = myModels.SYS_CollageDetai.Where(m => m.CollageDetaiID == item.CollageDetaiID).SingleOrDefault();
                var Quantity = item.Quantity;
                if (listCollage!=null)
                {
                    if (Quantity < 0)
                    {
                        returnJosn.Text = "配件编码为" + listCollage.FittingsCode + "的退料数量不能小于0!";
                        returnJosn.State = false;
                    }
                    else if (Quantity > listCollage.Quantity)
                    {
                        returnJosn.Text = "配件编码为" + listCollage.FittingsCode + "的退料数量不能超过领料的数量!";
                        returnJosn.State = false;
                    }
                }
            }
            return Json(returnJosn, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ListRestock(List<PW_Restock> listRestock, List<RestockDetail> listRestockDetai)//保存退料主页面
        {
            ReturnJson returnJosn = new ReturnJson();
            try
            {
                foreach (var item in listRestockDetai)
                {
                    var FittingsCode = item.FittingsCode.Trim();
                    var WarehouseID = item.WarehouseID;
                    var listCollage = myModels.SYS_CollageDetai.Where(m => m.CollageDetaiID == item.CollageDetaiID).SingleOrDefault();
                    var listInventory = myModels.SYS_Inventory.Where(m => m.FittingsCode == FittingsCode && m.WarehouseID == WarehouseID).SingleOrDefault();//库存表
                    var listFittingsInfos = myModels.SYS_FittingsInfo.Where(m=>m.FittingsCode == FittingsCode).SingleOrDefault();
                    var Quantity = item.Quantity;
                    if (listCollage!=null)
                    {
                        listCollage.Quantity = listCollage.Quantity - Quantity;
                        if (listCollage.Quantity == 0)//退料数量==领料数量则删除
                        {
                            myModels.SYS_CollageDetai.Remove(listCollage);
                        }
                        else//退料数量！=领料数量则修改
                        {
                            myModels.Entry(listCollage).State = System.Data.Entity.EntityState.Modified;
                        }
                        listInventory.InvenQuan = listInventory.InvenQuan + Quantity;
                        myModels.Entry(listInventory).State = System.Data.Entity.EntityState.Modified;
                        if (listFittingsInfos != null)
                        {
                            listFittingsInfos.InvenQuan = listFittingsInfos.InvenQuan + Quantity;
                            myModels.Entry(listFittingsInfos).State = System.Data.Entity.EntityState.Modified;
                        }
                        myModels.SaveChanges();
                    }
                }
                var ReceptionID = listRestock[0].ReceptionID;
                var listReception = myModels.PW_Reception.Where(m => m.ReceptionID == ReceptionID).SingleOrDefault();
                listReception.CollageState = "已退料";
                myModels.Entry(listReception).State = System.Data.Entity.EntityState.Modified;
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