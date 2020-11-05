using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class OutboundVo: PW_Outbound
    {
        public string CustomerCode { get; set; } //客户编码
        public string CustomerName { get; set; } //客户名称  

        private string OpenDated; //开单时间
        public string OpenDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    OpenDated = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    OpenDated = value;
                }
            }
            get
            {
                return OpenDated;
            }
        } //开单时间     

        private string Auditdated; //审核日期
        public string Auditdate
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    Auditdated = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    Auditdated = value;
                }
            }
            get
            {
                return Auditdated;
            }
        } //审核日期  
    }
}