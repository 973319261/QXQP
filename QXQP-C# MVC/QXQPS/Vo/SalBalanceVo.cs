using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class SalBalanceVo: PW_SalBalance
    {
        public string CustomerCode;//客户编码
        public string CustomerName;//客户名称  
        private string auditdate;//审核日期
        public string ReceiptDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    auditdate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    auditdate = value;
                }
            }
            get
            {
                return auditdate;
            }
        } //审核日期      
    }
}