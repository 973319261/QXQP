using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class MaintenanceVo : SYS_MaintenanceCus
    {
        /// <summary>
        /// 生日
        /// </summary>
        private string birthday;
        public string Birthdays
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    birthday = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    birthday = value;
                }
            }
            get
            {
                return birthday;
            }
        }
        /// <summary>
        /// 保险起始日
        /// </summary>
        private string initialStartDate;
        public string InitialStartDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    initialStartDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    initialStartDate = value;
                }
            }
            get
            {
                return initialStartDate;
            }
        }
        /// <summary>
        /// 保险终止日
        /// </summary>
        private string initialEndDate;
        public string InitialEndDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    initialEndDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    initialEndDate = value;
                }
            }
            get
            {
                return initialEndDate;
            }
        }
        /// <summary>
        ///行驶证年审日期
        /// </summary>
        private string driveDate;
        public string DriveDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    driveDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    driveDate = value;
                }
            }
            get
            {
                return driveDate;
            }
        }
        /// <summary>
        ///驾驶证年审日期
        /// </summary>
        private string drivingDate;
        public string DrivingDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    drivingDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    drivingDate = value;
                }
            }
            get
            {
                return drivingDate;
            }
        }


        public string VehicleType { get; set; }
        /// <summary>
        /// 车型
        /// </summary>
        public string InsuranceSpeName { get; set; }
        /// <summary>
        /// 保险种类
        /// </summary>
        public string DepartmentName { get; set; }
        /// <summary>
        /// 所属部门
        /// </summary>
        public string Carder { get; set; }
        /// <summary>
        /// 所属员工
        /// </summary>
        public string CustomerLevel { get; set; }
        /// <summary>
        /// 客户等级
        /// </summary>
        public string CustomerSou { get; set; }
        /// <summary>
        /// 客户来源
        /// </summary>
        public string CustomerType { get; set; }
        /// <summary>
        /// 客户类别
        /// </summary>
        public string Region { get; set; }
        /// <summary>
        /// 客户区域
        /// </summary>

    }
}