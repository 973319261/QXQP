using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.PublicManagment.Controllers
{
    public class CreateSelectController : Controller
    {
        // GET: PublicManagment/CreateSelect
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        #region 查询下拉框
        public ActionResult SelectVehicleType()//查询车型
        {
            List<SelectVo> listVehicleType = (from tbVehicleType in myModels.SYS_VehicleType
                                              select new SelectVo
                                              {
                                                  id = tbVehicleType.VehicleTypeID,
                                                  text = tbVehicleType.VehicleType.Trim()
                                              }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listVehicleType = Common.Tools.SetSelectJson(listVehicleType);//设置selectjson
            return Json(listVehicleType, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SelectInsuranceCom()//查询保险公司
        {
            List<SelectVo> listInsuranceCom = (from tbInsuranceCom in myModels.SYS_InsuranceCom
                                               select new SelectVo
                                               {
                                                   id = tbInsuranceCom.InsuranceComID,
                                                   text = tbInsuranceCom.InsuranceComName.Trim()
                                               }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listInsuranceCom = Common.Tools.SetSelectJson(listInsuranceCom);//设置selectjson
            return Json(listInsuranceCom, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SelectInsuranceSpe()//查询保险种类表
        {
            List<SelectVo> listInsuranceSpe = (from tbInsuranceSpe in myModels.SYS_InsuranceSpe
                                               select new SelectVo
                                               {
                                                   id = tbInsuranceSpe.InsuranceSpeID,
                                                   text = tbInsuranceSpe.InsuranceSpeName.Trim()
                                               }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listInsuranceSpe = Common.Tools.SetSelectJson(listInsuranceSpe);//设置selectjson
            return Json(listInsuranceSpe, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SelectDepartment()//查询部门表
        {
            List<SelectVo> listDepartment = (from tbDepartment in myModels.SYS_Department
                                             select new SelectVo
                                             {
                                                 id = tbDepartment.DepartmentID,
                                                 text = tbDepartment.DepartmentName.Trim()
                                             }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            return Json(listDepartment, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SelectCarder()//查询接车人
        {
            List<SelectVo> listCarder = (from tbCarder in myModels.SYS_Carder
                                         select new SelectVo
                                         {
                                             id = tbCarder.CarderID,
                                             text = tbCarder.CarderName.Trim()
                                         }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listCarder = Common.Tools.SetSelectJson(listCarder);//设置selectjson
            return Json(listCarder, JsonRequestBehavior.AllowGet);
        }

        public ActionResult SelectCustomerLevel()//查询客户等级
        {
            List<SelectVo> listCustomerLevel = (from tbCustomerLevel in myModels.SYS_CustomerLevel
                                                select new SelectVo
                                                {
                                                    id = tbCustomerLevel.CustomerLevelID,
                                                    text = tbCustomerLevel.CustomerLevel.Trim()
                                                }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listCustomerLevel = Common.Tools.SetSelectJson(listCustomerLevel);//设置selectjson
            return Json(listCustomerLevel, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectCustomerType()//查询客户类型
        {
            List<SelectVo> listCustomerType = (from tbCustomerType in myModels.SYS_CustomerType
                                               select new SelectVo
                                               {
                                                   id = tbCustomerType.CustomerTypeID,
                                                   text = tbCustomerType.CustomerType.Trim()
                                               }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listCustomerType = Common.Tools.SetSelectJson(listCustomerType);//设置selectjson
            return Json(listCustomerType, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectRegion()//查询客户区域
        {
            List<SelectVo> listRegion = (from tbRegion in myModels.SYS_Region
                                         select new SelectVo
                                         {
                                             id = tbRegion.RegionID,
                                             text = tbRegion.RegionName.Trim()
                                         }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listRegion = Common.Tools.SetSelectJson(listRegion);//设置selectjson
            return Json(listRegion, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectCustomerSou()//查询客户来源
        {
            List<SelectVo> listCustomerSou = (from tbCustomerSou in myModels.SYS_CustomerSou
                                              select new SelectVo
                                              {
                                                  id = tbCustomerSou.CustomerSouID,
                                                  text = tbCustomerSou.CustomerSou.Trim()
                                              }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listCustomerSou = Common.Tools.SetSelectJson(listCustomerSou);//设置selectjson
            return Json(listCustomerSou, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectWarehouse()//查询仓库
        {
            List<SelectVo> listWarehouse = (from tbWarehouse in myModels.SYS_Warehouse
                                         select new SelectVo
                                         {
                                             id = tbWarehouse.WarehouseID,
                                             text = tbWarehouse.WarehouseName.Trim()
                                         }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            return Json(listWarehouse, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectFittingsType()//查询配件类型
        {
            List<SelectVo> listFittingsType = (from tbFittingsType in myModels.SYS_FittingsType
                                              select new SelectVo
                                              {
                                                  id = tbFittingsType.FittingsTypeID,
                                                  text = tbFittingsType.FittingsTypeName.Trim()
                                              }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listFittingsType = Common.Tools.SetSelectJson(listFittingsType);//设置selectjson
            return Json(listFittingsType, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectMaintainability()//查询维修性质
        {
            List<SelectVo> listMaintainability = (from tbMaintainability in myModels.SYS_Maintainability
                                            select new SelectVo
                                            {
                                                id = tbMaintainability.MaintainabilityID,
                                                text = tbMaintainability.MaintainabilityName.Trim()
                                            }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            return Json(listMaintainability, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectClaimCom()//查询索赔公司
        {
            List<SelectVo> listClaimCom = (from tbClaimCom in myModels.SYS_ClaimCom
                                               select new SelectVo
                                               {
                                                   id = tbClaimCom.ClaimComID,
                                                   text = tbClaimCom.ClaimComName.Trim()
                                               }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listClaimCom = Common.Tools.SetSelectJson(listClaimCom);//设置selectjson
            return Json(listClaimCom, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectPayment()//查询付款方式
        {
            List<SelectVo> listPayment = (from tbPayment in myModels.SYS_Payment
                                                  select new SelectVo
                                                  {
                                                      id = tbPayment.PaymentID,
                                                      text = tbPayment.PaymentName.Trim()
                                                  }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listPayment = Common.Tools.SetSelectJson(listPayment);//设置selectjson
            return Json(listPayment, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectMaintenance()//查询维修工艺
        {
            List<SelectVo> listMaintenance = (from tbMaintenance in myModels.SYS_Maintenance
                                           select new SelectVo
                                           {
                                               id = tbMaintenance.MaintenanceID,
                                               text = tbMaintenance.MaintenanceName.Trim()
                                           }).ToList();
            return Json(listMaintenance, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectForeMan()//查询领料员
        {
            List<SelectVo> listForeMan = (from tbForeMan in myModels.SYS_ForeMan
                                          select new SelectVo
                                          {
                                              id = tbForeMan.ForeManID,
                                              text = tbForeMan.ForeManName.Trim()
                                          }).ToList();
            return Json(listForeMan, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectExpenses()//查询其他费用表
        {
            List<SelectVo> listExpenses = (from tbExpenses in myModels.SYS_Expenses
                                              select new SelectVo
                                              {
                                                  id = tbExpenses.ExpensesID,
                                                  text = tbExpenses.ExpensesName.Trim()
                                              }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            return Json(listExpenses, JsonRequestBehavior.AllowGet);
        }
        
             public ActionResult SelectRepairItem()//查询修理项目表
        {
            List<SelectVo> listRepairItem = (from tbRepairItem in myModels.SYS_RepairItem
                                           select new SelectVo
                                           {
                                               id = tbRepairItem.RepairItemID,
                                               text = tbRepairItem.RepairItemName.Trim()
                                           }).ToList();
            return Json(listRepairItem, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectRepairMan()//查询修理工
        {
            List<SelectVo> listRepairMan = (from tbRepairMan in myModels.SYS_RepairMan
                                          select new SelectVo
                                          {
                                              id = tbRepairMan.RepairManID,
                                              text = tbRepairMan.RepairManName.Trim()
                                          }).ToList();
            listRepairMan = Common.Tools.SetSelectJson(listRepairMan);//设置selectjson
            return Json(listRepairMan, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectRepair()//查询修理类别
        {
            List<SelectVo> listRepair = (from tbRepair in myModels.SYS_Repair
                                           select new SelectVo
                                           {
                                               id = tbRepair.RepairID,
                                               text = tbRepair.RepairName.Trim()
                                           }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listRepair = Common.Tools.SetSelectJson(listRepair);//设置selectjson
            return Json(listRepair, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectSystemUnit()//查询系统单位
        {
            List<SelectVo> listSystemUnit = (from tbSystemUnit in myModels.SYS_SystemUnit
                                           select new SelectVo
                                           {
                                               id = tbSystemUnit.SystemUnitID,
                                               text = tbSystemUnit.SystemUnit.Trim()
                                           }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listSystemUnit = Common.Tools.SetSelectJson(listSystemUnit);//设置selectjson
            return Json(listSystemUnit, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectMaintenanceCrew()//查询修理班组
        {
            List<SelectVo> listMaintenanceCrew = (from tbMaintenanceCrew in myModels.SYS_MaintenanceCrew
                                            select new SelectVo
                                            {
                                                id = tbMaintenanceCrew.MaintenanceCrewID,
                                                text = tbMaintenanceCrew.MaintenanceCrewName.Trim()
                                            }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listMaintenanceCrew = Common.Tools.SetSelectJson(listMaintenanceCrew);//设置selectjson
            return Json(listMaintenanceCrew, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectRepairClass()//查询修理大类
        {
            List<SelectVo> listRepairClass = (from tbRepairClass in myModels.SYS_RepairClass
                                         select new SelectVo
                                         {
                                             id = tbRepairClass.RepairClassID,
                                             text = tbRepairClass.RepairClassName.Trim()
                                         }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listRepairClass = Common.Tools.SetSelectJson(listRepairClass);//设置selectjson
            return Json(listRepairClass, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectDocumentState()//查询单据状态
        {
            List<SelectVo> listDocumentState = (from tbDocumentState in myModels.SYS_DocumentState
                                                  select new SelectVo
                                                  {
                                                      id = tbDocumentState.DocumentStateID,
                                                      text = tbDocumentState.DocumentState.Trim()
                                                  }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listDocumentState = Common.Tools.SetSelectJson(listDocumentState);//设置selectjson
            return Json(listDocumentState, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectBalanceState()//查询结算状态
        {
            List<SelectVo> listBalanceState = (from tbBalanceState in myModels.SYS_BalanceState
                                              select new SelectVo
                                              {
                                                  id = tbBalanceState.BalanceStateID,
                                                  text = tbBalanceState.BalanceState.Trim()
                                              }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listBalanceState = Common.Tools.SetSelectJson(listBalanceState);//设置selectjson
            return Json(listBalanceState, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectDocumentsType()//查询单据类型
        {
            List<SelectVo> listDocumentsType = (from tbDocumentsType in myModels.SYS_DocumentsType
                                                select new SelectVo
                                                {
                                                    id = tbDocumentsType.DocumentsTypeID,
                                                    text = tbDocumentsType.DocumentsType.Trim()
                                                }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listDocumentsType = Common.Tools.SetSelectJson(listDocumentsType);//设置selectjson
            return Json(listDocumentsType, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectUser()//查询用户
        {
            List<SelectVo> listUser = (from tbUser in myModels.PW_User
                                               select new SelectVo
                                               {
                                                   id = tbUser.UserID,
                                                   text = tbUser.UserName.Trim()
                                               }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listUser = Common.Tools.SetSelectJson(listUser);//设置selectjson
            return Json(listUser, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectUserType()//查询用户
        {
            List<SelectVo> listUserType = (from tbUserType in myModels.SYS_UserType
                                       select new SelectVo
                                       {
                                           id = tbUserType.UserTypeID,
                                           text = tbUserType.UserTypeName.Trim()
                                       }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            return Json(listUserType, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectSuppliers()//查询供应商
        {
            List<SelectVo> listSuppliers = (from tbSuppliers in myModels.SYS_Suppliers
                                       select new SelectVo
                                       {
                                           id = tbSuppliers.SuppliersID,
                                           text = tbSuppliers.SuppliersName.Trim()
                                       }).ToList();
            //把数据库数据加到表格：跟默认然一行数据进行合并
            listSuppliers = Common.Tools.SetSelectJson(listSuppliers);//设置selectjson
            return Json(listSuppliers, JsonRequestBehavior.AllowGet);
        }

    }
   
    #endregion
}
