using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class MaintenanceCusVo: SYS_MaintenanceCus
    {
        public string InsuranceComName { get; set; }//保险公司名称
        public string InsuranceSpeName{ get; set; }//保险种类名称 
        public string DepartmentName{ get; set; }//部门名称 
        public string CarderName { get; set; }//接车人名称
        public string CustomerType { get; set; }//客户类型名称
        public string CustomerLevel { get; set; }//客户等级名称 
        public string RegionName { get; set; }//区域名称
        public string VehicCustomerSoue { get; set; }//客户来源名称

        private string birthday;//生日
        public string Birthdays
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    birthday = dt.ToString("yyyy/MM/dd");
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
        }//生日

        private string initialStartDate;//保险起始日期
        public string InitialStartDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    initialStartDate = dt.ToString("yyyy/MM/dd");
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
        }//保险起始日期

        private string initialEndDate;//保险终止日期
        public string InitialEndDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    initialEndDate = dt.ToString("yyyy/MM/dd");
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
        }//保险终止日期

        private string driveDate;//行驶证年审日期
        public string DriveDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    driveDate = dt.ToString("yyyy/MM/dd");
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
        }//行驶证年审日期

        private string drivingDate;//驾驶证年审日期
        public string DrivingDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    drivingDate = dt.ToString("yyyy/MM/dd");
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
        }//驾驶证年审日期
    }
}