using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.BasicdataManagment.Controllers
{
    public class CustomerbasicController : Controller
    {
        // GET: BasicdataManagment/Customerbasic ///BasicdataManagment/Customerbasic/Num 0 ()
      
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Maintenance()//维修客户基本资料
        {
            return View();
        }
        /// <summary>
        /// 查询客户区域
        /// </summary>
        /// <returns></returns>
        public ActionResult SelectRegion()
        {
            var list = from tbRegion in myModels.SYS_Region
                       select tbRegion;
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="bsgridPage"></param>
        /// <returns></returns>
        public ActionResult SelectMaintenance(BsgridPage bsgridPage, int RegionID)
        {
            var listMaintenance = (from tbMaintenance in myModels.SYS_MaintenanceCus
                                       //join tbVehicleType in myModels.SYS_VehicleType on tbMaintenance.VehicleTypeID equals tbVehicleType.VehicleTypeID//车型
                                   join tbInsuranceSpe in myModels.SYS_InsuranceSpe on tbMaintenance.InsuranceSpeID equals tbInsuranceSpe.InsuranceSpeID//保险种类
                                   join tbDepartment in myModels.SYS_Department on tbMaintenance.DepartmentID equals tbDepartment.DepartmentID//所属部门
                                   join tbCarder in myModels.SYS_Carder on tbMaintenance.CarderID equals tbCarder.CarderID//所属员工
                                   join tbCustomerType in myModels.SYS_CustomerType on tbMaintenance.CustomerLevelID equals tbCustomerType.CustomerTypeID//客户类别
                                   join tbCustomerLevel in myModels.SYS_CustomerLevel on tbMaintenance.CustomerLevelID equals tbCustomerLevel.CustomerLevelID//客户等级
                                   join tbRegion in myModels.SYS_Region on tbMaintenance.RegionID equals tbRegion.RegionID//所在区域
                                   join tbCustomerSou in myModels.SYS_CustomerSou on tbMaintenance.CustomerSouID equals tbCustomerSou.CustomerSouID//客户来源
                                   select new MaintenanceVo
                                   {
                                       RegionID = tbRegion.RegionID,//区域ID
                                       MaintenanceCusID = tbMaintenance.MaintenanceCusID,//维修客户ID
                                       CustomerNum = tbMaintenance.CustomerNum,//客户编号
                                       Owner = tbMaintenance.Owner,//车主
                                       LicenseCode = tbMaintenance.LicenseCode,//车牌号码
                                       VehicleType = tbMaintenance.VehicleType,//车型
                                       EngineNum = tbMaintenance.EngineNum,//发动机号
                                       FrameNum = tbMaintenance.FrameNum,//车架号
                                       RepairMan = tbMaintenance.RepairMan,//送修人
                                       RepairTele = tbMaintenance.RepairTele,//送修人电话
                                       MobilePhone = tbMaintenance.MobilePhone,//车主手机
                                       InsuranceSpeName = tbInsuranceSpe.InsuranceSpeName,//保险种类
                                       InitialStartDates = tbMaintenance.InitialStartDate.ToString(),//保险起始日
                                       InitialEndDates = tbMaintenance.InitialEndDate.ToString(),//保险终止日
                                       Address = tbMaintenance.Address,//车主地址
                                       DriveDates = tbMaintenance.DriveDate.ToString(),//行驶证年审日期
                                       DepartmentName = tbDepartment.DepartmentName,//所属部门
                                       Carder = tbCarder.CarderName,//所属员工
                                       CustomerLevel = tbCustomerLevel.CustomerLevel,//客户等级
                                       CustomerSou = tbCustomerSou.CustomerSou,//客户来源
                                       CustomerType = tbCustomerType.CustomerType,//客户类别

                                       Region = tbRegion.RegionName,//所在区域
                                       InputPerson = tbMaintenance.InputPerson,//录入人                                       
                                   }).ToList();
            if (RegionID > 0)
            {
                listMaintenance = listMaintenance.Where(m => m.RegionID == RegionID).ToList();
            }
            int count = listMaintenance.Count();
            List<MaintenanceVo> listFittingsInfo = listMaintenance.OrderByDescending(m => RegionID).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<MaintenanceVo> bsgrid = new Bsgrid<MaintenanceVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listFittingsInfo,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 编号
        /// </summary>
        /// <returns></returns>
        public ActionResult Num()
        {
            string Num = "";
            var Time = DateTime.Now.ToString("yyyyMMdd");
            try
            {
                int Count = myModels.SYS_MaintenanceCus.Where(m => m.CustomerNum.Contains(Time)).Count() + 1;
                if (Count < 10)
                {
                    Num = "WXKE" + "-" + Time + "000" + Count;
                }
                else if (Count > 9 && Count < 100)
                {
                    Num = "WXKE" + "-" + Time + "-" + "00" + Count;
                }
                else if (Count > 99 && Count < 1000)
                {
                    Num = "WXKE" + "-" + Time + "-" + "0" + Count;
                }
            }
            catch (Exception)
            {
                return Json(Num, JsonRequestBehavior.AllowGet);
            }
            return Json(Num, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 新增
        /// </summary>
        /// <param name="Maintenance"></param>
        /// <returns></returns>
        public ActionResult InsertMaintenance(SYS_MaintenanceCus MaintenanceCus)
        {
            string strMsg = "failed";
            try
            {
                //判断维修客户表中是否已经存在新增的维修客户信息
                var SelectCount = (from tbMaintenanceCus in myModels.SYS_MaintenanceCus
                                   where tbMaintenanceCus.MaintenanceCusID == MaintenanceCus.MaintenanceCusID ||
                                   tbMaintenanceCus.IdNumber == MaintenanceCus.IdNumber
                                   select tbMaintenanceCus).Count();
                if (SelectCount == 0)
                {
                    myModels.SYS_MaintenanceCus.Add(MaintenanceCus);
                    myModels.SaveChanges();
                    strMsg = "success";
                }
                else
                {
                    strMsg = "该维修客户信息已经存在，不需要重复输入数据！";
                }

            }
            catch (Exception e)
            {
                strMsg = "failed";
            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="MaintenanceCus"></param>
        /// <returns></returns>
        public ActionResult SelectMaintenanceByID(int MaintenanceCusID)
        {
            var list = myModels.SYS_MaintenanceCus.Where(m => m.MaintenanceCusID == MaintenanceCusID).Select(m => new
            {
                MaintenanceCusID = m.MaintenanceCusID,//维修客户ID
                Owner = m.Owner,//车主
                Birthday = m.Birthday.ToString(),//生日
                IdNumber = m.IdNumber,//身份证号
                MobilePhone = m.MobilePhone,//车主手机
                LicenseCode = m.LicenseCode,//车牌号码
                VehicleType = m.VehicleType,//车型
                EngineNum = m.EngineNum,//发动机号
                FrameNum = m.FrameNum,//车架
                RepairMan = m.RepairMan,//送修人
                RepairTele = m.RepairTele, //送修人电话
                InsuranceComID = m.InsuranceComID,//保险公司
                InsuranceSpeID = m.InsuranceSpeID,//保险种类
                InitialStartDate = m.InitialStartDate.ToString(), //保险起始日
                InitialEndDate = m.InitialEndDate.ToString(), //保险终止日
                Address = m.Address,//地址
                CustomerNum = m.CustomerNum,//客户编号
                DriveDate = m.DriveDate.ToString(),//行驶证年审
                RegionID = m.RegionID,//所属区域
                DepartmentID = m.DepartmentID,//所属部门
                CarderID = m.CarderID,//所属员工
                CustomerLevelID = m.CustomerLevelID,//客户等级
                CustomerSouID = m.CustomerSouID,//客户来源
                CustomerTypeID = m.CustomerTypeID,//客户类别
                InputPerson = m.InputPerson,//录入人
                DrivingDate = m.DrivingDate.ToString(),//驾驶证年审
            });
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult UpdateMaintenance(SYS_MaintenanceCus Maintenance)
        {
            var strMsg = "failed";
            try
            {
                //判断修改后的数据是否与数据库重复
                var oldMaintenanceCusRow = (from tbMaintenance in myModels.SYS_MaintenanceCus
                                            where tbMaintenance.MaintenanceCusID != Maintenance.MaintenanceCusID &&
                                            (tbMaintenance.IdNumber == Maintenance.IdNumber)
                                            select tbMaintenance).Count();
                if (oldMaintenanceCusRow == 0)
                {
                    myModels.Entry(Maintenance).State = System.Data.Entity.EntityState.Modified;
                    //保存数据库
                    myModels.SaveChanges();
                    strMsg = "success";
                }
                else
                {
                    strMsg = "该客户信息已经存在，不需要重复录入！";
                }
            }
            catch (Exception)
            {
                strMsg = "failed";
            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="MaintenanceCusID"></param>
        /// <returns></returns>
        public ActionResult DelectMaintenance(int MaintenanceCusID)
        {
            try
            {
                var listMaintenance = myModels.SYS_MaintenanceCus
                    .Where(m => m.MaintenanceCusID == MaintenanceCusID).Single();
                myModels.SYS_MaintenanceCus.Remove(listMaintenance);
                if (myModels.SaveChanges() > 0)
                {
                    return Json(true, JsonRequestBehavior.AllowGet);
                }
                else
                {
                    return Json(false, JsonRequestBehavior.AllowGet);
                }
            }
            catch (Exception e)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
        /// <summary>
        /// 导出
        /// </summary>
        /// <param name="MaintenanceCusID"></param>
        /// <returns></returns>
        //public ActionResult ExportToExcel( int MaintenanceCusID)
        //{
        //    //查询表格中所有的数据
        //    List<MaintenanceVo> list = (from tbMaintenance in myModels.SYS_MaintenanceCus
        //                                join tbVehicleType in myModels.SYS_VehicleType on tbMaintenance.VehicleTypeID equals tbVehicleType.VehicleTypeID//车型
        //                                join tbInsuranceSpe in myModels.SYS_InsuranceSpe on tbMaintenance.InsuranceSpeID equals tbInsuranceSpe.InsuranceSpeID//保险种类
        //                                join tbDepartment in myModels.SYS_Department on tbMaintenance.DepartmentID equals tbDepartment.DepartmentID//所属部门
        //                                join tbCarder in myModels.SYS_Carder on tbMaintenance.CarderID equals tbCarder.CarderID//所属员工
        //                                join tbCustomerType in myModels.SYS_CustomerType on tbMaintenance.CustomerLevelID equals tbCustomerType.CustomerTypeID//客户类别
        //                                join tbCustomerLevel in myModels.SYS_CustomerLevel on tbMaintenance.CustomerLevelID equals tbCustomerLevel.CustomerLevelID//客户等级
        //                                join tbRegion in myModels.SYS_Region on tbMaintenance.RegionID equals tbRegion.RegionID//所在区域
        //                                join tbCustomerSou in myModels.SYS_CustomerSou on tbMaintenance.CustomerSouID equals tbCustomerSou.CustomerSouID//客户来源
        //                                select new MaintenanceVo
        //                                {
        //                                    MaintenanceCusID = tbMaintenance.MaintenanceCusID,//维修客户ID
        //                                    CustomerNum = tbMaintenance.CustomerNum,//客户编号
        //                                    Owner = tbMaintenance.Owner,//车主
        //                                    LicenseCode = tbMaintenance.LicenseCode,//车牌号码
        //                                    VehicleType = tbVehicleType.VehicleType,//车型
        //                                    EngineNum = tbMaintenance.EngineNum,//发动机号
        //                                    FrameNum = tbMaintenance.FrameNum,//车架号
        //                                    RepairMan = tbMaintenance.RepairMan,//送修人
        //                                    RepairTele = tbMaintenance.RepairTele,//送修人电话
        //                                    MobilePhone = tbMaintenance.MobilePhone,//车主手机
        //                                    InsuranceSpeName = tbInsuranceSpe.InsuranceSpeName,//保险种类
        //                                    InitialStartDate = tbMaintenance.InitialStartDate,//保险起始日
        //                                    InitialEndDate = tbMaintenance.InitialEndDate,//保险终止日
        //                                    Address = tbMaintenance.Address,//车主地址
        //                                    DriveDate = tbMaintenance.DriveDate,//行驶证年审日期
        //                                    DepartmentName = tbDepartment.DepartmentName,//所属部门
        //                                    Carder = tbCarder.CarderName,//所属员工
        //                                    CustomerLevel = tbCustomerLevel.CustomerLevel,//客户等级
        //                                    CustomerSou = tbCustomerSou.CustomerSou,//客户来源
        //                                    CustomerType = tbCustomerType.CustomerType,//客户类别
        //                                    Region = tbRegion.RegionName,//所在区域
        //                                    InputPerson = tbMaintenance.InputPerson,//录入人                                       
        //                                }).ToList();
        //    if (MaintenanceCusID > 0)
        //    {
        //        list = list.Where(m => m.MaintenanceCusID == MaintenanceCusID).ToList();
        //    }
        //    //Excel表格的创建步骤
        //    //第一步：创建Excel对象
        //    NPOI.HSSF.UserModel.HSSFWorkbook book = new NPOI.HSSF.UserModel.HSSFWorkbook();
        //    //第二步：创建Excel对象的工作簿
        //    NPOI.SS.UserModel.ISheet sheet = book.CreateSheet();
        //    //第三步：Excel表头设置
        //    NPOI.SS.UserModel.IRow row1 = sheet.CreateRow(0);
        //    row1.CreateCell(0).SetCellValue("客户编号");              
        //    row1.CreateCell(1).SetCellValue("车主");                  
        //    row1.CreateCell(2).SetCellValue("车牌号码");              
        //    row1.CreateCell(3).SetCellValue("车型");                  
        //    row1.CreateCell(4).SetCellValue("发动机号");              
        //    row1.CreateCell(5).SetCellValue("车架号");                
        //    row1.CreateCell(6).SetCellValue("送修人");                
        //    row1.CreateCell(7).SetCellValue("送修人电话");            
        //    row1.CreateCell(8).SetCellValue("车主手机");              
        //    row1.CreateCell(9).SetCellValue("保险种类");              
        //    row1.CreateCell(10).SetCellValue("保险起始日");           
        //    row1.CreateCell(11).SetCellValue("保险终止日");           
        //    row1.CreateCell(12).SetCellValue("车主地址");             
        //    row1.CreateCell(13).SetCellValue("行驶证年审");           
        //    row1.CreateCell(14).SetCellValue("所属部门");             
        //    row1.CreateCell(15).SetCellValue("所属员工");             
        //    row1.CreateCell(16).SetCellValue("客户等级");             
        //    row1.CreateCell(17).SetCellValue("客户来源");             
        //    row1.CreateCell(18).SetCellValue("客户类别");             
        //    row1.CreateCell(19).SetCellValue("所属区域");             
        //    row1.CreateCell(20).SetCellValue("录入人");
        //    //第四步：for循环给sheet的每行添加数据
        //    for(int i = 0; i < list.Count; i++)
        //    {
        //        NPOI.SS.UserModel.IRow row = sheet.CreateRow(i + 1);
        //        row.CreatCell(0).SetCellValue(list[i].CustomerNum);  //客户编号
        //        row.CreatCell(1).SetCellValue(list[i].Owner); //车主
        //        row.CreatCell(2).SetCellValue(list[i].LicenseCode); //车牌号码
        //        row.CreatCell(3).SetCellValue(list[i].VehicleType);//车型
        //        row.CreatCell(4).SetCellValue(list[i].EngineNum);//发动机号
        //        row.CreatCell(5).SetCellValue(list[i].FrameNum);//车架号
        //        row.CreatCell(6).SetCellValue(list[i].RepairMan);//送修人
        //        row.CreatCell(7).SetCellValue(list[i].RepairTele);//送修人电话
        //        row.CreatCell(8).SetCellValue(list[i].MobilePhone);//车主手机
        //        row.CreatCell(9).SetCellValue(list[i].InsuranceSpeName);//保险种类
        //        row.CreatCell(10).SetCellValue(list[i].InitialStartDate);//保险起始日
        //        row.CreatCell(11).SetCellValue(list[i].InitialEndDate);//保险终止日
        //        row.CreatCell(12).SetCellValue(list[i].Address);//车主地址
        //        row.CreatCell(13).SetCellValue(list[i].DriveDate);//行驶证年审日期
        //        row.CreatCell(14).SetCellValue(list[i].DepartmentName);//所属部门
        //        row.CreatCell(15).SetCellValue(list[i].Carder);//所属员工
        //        row.CreatCell(16).SetCellValue(list[i].CustomerLevel);//客户等级
        //        row.CreatCell(17).SetCellValue(list[i].CustomerSou);//客户来源
        //        row.CreatCell(18).SetCellValue(list[i].CustomerType);//客户类别
        //        row.CreatCell(19).SetCellValue(list[i].Region); //所在区域
        //        row.CreatCell(20).SetCellValue(list[i].InputPerson);//录入人              
        //    }
        //    //把Excel转化为文件流，输出
        //    MemoryStream BookStream = new MemoryStream();//定义文件流
        //    book.Write(BookStream);//将工作簿写入文件流
        //    //输出之前调用Seek(偏移量，游标位置)方法：获取文件流的长度
        //    BookStream.Seek(0, SeekOrigin.Begin);
        //    //输出的文件名称
        //    string filename = "医生信息" + DateTime.Now.ToString("yyyyMMddHHmmss") + ".xls";
        //    return File(BookStream, "application/vnd.ms-excel", filename);//文件类型/文件名称
        //}

    }
}