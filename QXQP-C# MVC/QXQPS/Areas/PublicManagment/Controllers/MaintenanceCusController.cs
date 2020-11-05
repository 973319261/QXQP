using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.PublicManagment.Controllers
{
    public class MaintenanceCusController : Controller
    {

        // GET: PublicManagment/MaintenanceCus 
        /// <summary>
        /// 公共模态框信息
        /// </summary>
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult SeleceMaintenanceCus(BsgridPage bsgridPage, string LicenseCode)//查询维修客户表
        {
            var lingItem = (from tbMaintenanceCus in myModels.SYS_MaintenanceCus
                            join tbInsuranceCom in myModels.SYS_InsuranceCom on tbMaintenanceCus.InsuranceComID equals tbInsuranceCom.InsuranceComID
                            join tbInsuranceSpe in myModels.SYS_InsuranceSpe on tbMaintenanceCus.InsuranceSpeID equals tbInsuranceSpe.InsuranceSpeID
                            join tbDepartment in myModels.SYS_Department on tbMaintenanceCus.DepartmentID equals tbDepartment.DepartmentID
                            join tbCarder in myModels.SYS_Carder on tbMaintenanceCus.CarderID equals tbCarder.CarderID
                            join tbCustomerType in myModels.SYS_CustomerType on tbMaintenanceCus.CustomerTypeID equals tbCustomerType.CustomerTypeID
                            join tbCustomerLevel in myModels.SYS_CustomerLevel on tbMaintenanceCus.CustomerLevelID equals tbCustomerLevel.CustomerLevelID
                            join tbRegion in myModels.SYS_Region on tbMaintenanceCus.RegionID equals tbRegion.RegionID
                            join tbCustomerSou in myModels.SYS_CustomerSou on tbMaintenanceCus.CustomerSouID equals tbCustomerSou.CustomerSouID
                            select new MaintenanceCusVo
                            {
                                MaintenanceCusID = tbMaintenanceCus.MaintenanceCusID,//维修客户ID
                                Owner = tbMaintenanceCus.Owner.Trim(),//车主
                                Birthdays = tbMaintenanceCus.Birthday.ToString(),//生日
                                IdNumber = tbMaintenanceCus.IdNumber.Trim(),//身份证号码
                                MobilePhone = tbMaintenanceCus.MobilePhone.Trim(),//车主号码
                                LicenseCode = tbMaintenanceCus.LicenseCode.Trim(),//车牌号码
                                VehicleType = tbMaintenanceCus.VehicleType.Trim(),//车型名称
                                EngineNum = tbMaintenanceCus.EngineNum.Trim(),//发动机号码
                                FrameNum = tbMaintenanceCus.FrameNum.Trim(),//车架号码
                                RepairMan = tbMaintenanceCus.RepairMan.Trim(),//送修人
                                RepairTele = tbMaintenanceCus.RepairTele.Trim(),//送修人号码
                                InsuranceComID = tbInsuranceCom.InsuranceComID,//保险公司ID
                                InsuranceSpeID = tbInsuranceSpe.InsuranceSpeID,//保险种类ID
                                InitialStartDates = tbMaintenanceCus.InitialStartDate.ToString(),//保险起始日
                                InitialEndDates = tbMaintenanceCus.InitialEndDate.ToString(),//保险终止日期
                                Address = tbMaintenanceCus.Address.Trim(),//地址
                                CustomerNum = tbMaintenanceCus.CustomerNum.Trim(),//客户编号
                                DriveDates = tbMaintenanceCus.DriveDate.ToString(),//行驶证年审日期
                                RegionID = tbRegion.RegionID,//区域ID
                                DepartmentID = tbDepartment.DepartmentID,//部门ID
                                CarderID = tbCarder.CarderID,//接车人ID
                                CustomerLevelID = tbCustomerLevel.CustomerLevelID,//客户等级ID
                                CustomerSouID = tbCustomerSou.CustomerSouID,//客户来源ID
                                CustomerTypeID = tbCustomerType.CustomerTypeID,//客户类型ID
                                DrivingDates = tbMaintenanceCus.DrivingDate.ToString(),//驾驶证年审日期
                                InputPerson = tbMaintenanceCus.InputPerson.Trim(),//录入人
                            }).ToList();
            #region 拼接条件
            if (!string.IsNullOrEmpty(LicenseCode))
            {
                lingItem = lingItem.Where(m => m.LicenseCode.Contains(LicenseCode)).ToList();
            }
            #endregion
            int count = lingItem.Count();
            List<MaintenanceCusVo> listMaintenanceCus = lingItem.OrderByDescending(m => m.CustomerNum).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<MaintenanceCusVo> bsgrid = new Bsgrid<MaintenanceCusVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listMaintenanceCus,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);

        }
        public ActionResult InsertMaintenanceCus(SYS_MaintenanceCus sys_MaintenanceCus)//新增或修改维修客户表
        {
            int MaintenanceCusID = sys_MaintenanceCus.MaintenanceCusID;
            try
            {
                if (MaintenanceCusID == 0)
                {
                    myModels.SYS_MaintenanceCus.Add(sys_MaintenanceCus);
                    myModels.SaveChanges();
                }
                else
                {
                    myModels.Entry(sys_MaintenanceCus).State = System.Data.Entity.EntityState.Modified;
                    myModels.SaveChanges();
                }
            }
            catch (Exception)
            {

                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult CustomerNum()//生成客户编号
        {
            string CustomerNum = "";
            var date = DateTime.Now.ToString("yyyyMMdd");
            try
            {
                var list = myModels.SYS_SalesCustomer.Where(m => m.CustomerCode.Contains(date)).OrderBy(m => m.CustomerCode).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().CustomerCode.Trim().Substring(15)) + 1;
                    if (num < 10)
                    {
                        CustomerNum = "KH-" + date + "-000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        CustomerNum = "KH-" + date + "-00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        CustomerNum = "KH-" + date + "-0" + num;
                    }
                }
                else
                {
                    CustomerNum = "KH-" + date + "-0001";
                }
            }
            catch (Exception)
            {
                return Json(CustomerNum, JsonRequestBehavior.AllowGet);
            }
            return Json(CustomerNum, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectFittingsType()//查询修理大类
        {
            var list = from tbFittingsType in myModels.SYS_FittingsType
                       select tbFittingsType;
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SeleceFittingsInfo(BsgridPage bsgridPage, string InfoOne, string InfoTow,
            string InfoThree, string InfoFour,int FittingsTypeID)//查询配件信息
        {
            var lingItem = (from tbFittingsInfo in myModels.SYS_FittingsInfo
                            join tbFittingsType in myModels.SYS_FittingsType on tbFittingsInfo.FittingsTypeID equals tbFittingsType.FittingsTypeID
                            join tbSystemUnit in myModels.SYS_SystemUnit on tbFittingsInfo.SystemUnitID equals tbSystemUnit.SystemUnitID
                            join tbVehicleType in myModels.SYS_VehicleType on tbFittingsInfo.VehicleTypeID equals tbVehicleType.VehicleTypeID
                            join tbSuppliers in myModels.SYS_Suppliers on tbFittingsInfo.SuppliersID equals tbSuppliers.SuppliersID
                            select new FittingsInfoVo
                            {
                                FittingsInfoID = tbFittingsInfo.FittingsInfoID,//配件信息ID
                                FittingsTypeID = tbFittingsInfo.FittingsTypeID,//配件类型ID
                                FittingsTypeName = tbFittingsType.FittingsTypeName.Trim(),//配件类型名称
                                FittingsCode = tbFittingsInfo.FittingsCode.Trim() != null ? tbFittingsInfo.FittingsCode.Trim() : "",//配件编码
                                FittingsName = tbFittingsInfo.FittingsName.Trim() != null ? tbFittingsInfo.FittingsName.Trim() : "",//配件名称
                                Specification = tbFittingsInfo.Specification.Trim() != null ? tbFittingsInfo.Specification.Trim() : "",//配件规格
                                VehicleTypeID = tbVehicleType.VehicleTypeID,//车型ID
                                VehicleType = tbVehicleType.VehicleType.Trim(),//车型名称
                                SystemUnitID = tbSystemUnit.SystemUnitID,//单位ID
                                SystemUnit = tbSystemUnit.SystemUnit.Trim(),//单位名称
                                Intake = tbFittingsInfo.Intake,//进价
                                SalesPrice = tbFittingsInfo.SalesPrice,//销售价格
                                SuppliersID = tbSuppliers.SuppliersID,//供应商ID
                                SuppliersName = tbSuppliers.SuppliersName.Trim(),//供应商名称
                                Remark = tbFittingsInfo.Remark.Trim(),//Remark备注
                            }).ToList();

            #region 拼接条件
            if (FittingsTypeID>0)
            {
                lingItem = lingItem.Where(m => m.FittingsTypeID == FittingsTypeID).ToList();
            }
            if (!string.IsNullOrEmpty(InfoOne))
            {
                lingItem = lingItem.Where(m => m.FittingsTypeName.Contains(InfoOne)).ToList();//配件类型查询
            }
            if (!string.IsNullOrEmpty(InfoTow))
            {
                lingItem = lingItem.Where(m => m.FittingsCode.Contains(InfoTow)).ToList();//配件类型查询
            }
            if (!string.IsNullOrEmpty(InfoThree))
            {
                lingItem = lingItem.Where(m => m.FittingsName.Contains(InfoThree)).ToList();//配件类型查询
            }
            if (!string.IsNullOrEmpty(InfoFour))
            {
                lingItem = lingItem.Where(m => m.VehicleType.Contains(InfoFour)).ToList();//配件类型查询
            }
            #endregion
            int count = lingItem.Count();
            List<FittingsInfoVo> listFittingsInfo = lingItem.OrderByDescending(m => m.FittingsInfoID).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<FittingsInfoVo> bsgrid = new Bsgrid<FittingsInfoVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listFittingsInfo,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        public ActionResult InsertFittingsInfo(SYS_FittingsInfo sys_FittingsInfo, HttpPostedFileBase fileCarPicture)//新增配件信息
        {
            try
            {
                byte[] imgFile = null;
                if (fileCarPicture != null && fileCarPicture.ContentLength > 0)
                {   //读取该图片文件
                    //将图片转为流结束位置
                    //将流读取为byte[]，参数：byte[]，读取开始位置，读取字节数
                    imgFile = new byte[fileCarPicture.ContentLength];//初始化图片的长度
                    fileCarPicture.InputStream.Read(imgFile, 0, fileCarPicture.ContentLength);
                    sys_FittingsInfo.Picture = imgFile;
                }
                myModels.SYS_FittingsInfo.Add(sys_FittingsInfo);
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult JudgingFittingsCode(string FittingsCode)//判断是否存在配件编码
        {
            try
            {
                var list = myModels.SYS_FittingsInfo.Where(m => m.FittingsCode == FittingsCode.Trim()).SingleOrDefault();
                if (list != null)
                {
                    return Json(false, JsonRequestBehavior.AllowGet);
                }
                else {
                    return Json(true, JsonRequestBehavior.AllowGet);
                }
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
    }
}