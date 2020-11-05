using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class ProBalatDetailVo: SYS_ProBalancetDetail
    {
        private string auditDate;//审核时间
        public string DocumentsDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    auditDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    auditDate = value;
                }
            }
            get
            {
                return auditDate;
            }
        }
        public string DocumentsType { get; set; }
        public string BalanceState { get; set; }
    }
}