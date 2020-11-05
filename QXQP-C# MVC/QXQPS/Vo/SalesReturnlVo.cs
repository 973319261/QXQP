using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class SalesReturnlVo: PW_SalesReturnl
    {
        public string CustomerCode { get; set; } //客户编码
        public string CustomerName { get; set; } //客户名称 
        private string OpenDateds; //开单时间
        public string OpenDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    OpenDateds = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    OpenDateds = value;
                }
            }
            get
            {
                return OpenDateds;
            }
        } //开单时间     

        private string Auditdateds; //审核日期
        public string AuditDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    Auditdateds = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    Auditdateds = value;
                }
            }
            get
            {
                return Auditdateds;
            }
        } //审核日期   
    }
}