using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class ProBalanceVo: PW_ProBalance
    {
        private string auditerDate;//制单日期
        public string ReceiptDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    auditerDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    auditerDate = value;
                }
            }
            get
            {
                return auditerDate;
            }
        }//交货期限

        private string auditDate;//审核时间
        public string AuditDates
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
        public string SuppliersFirm { get; set; }//供应商编码
        public string SuppliersName { get; set; }//供应商名称
        public string PaymentName { get; set; }//付款方式
        public string Address   { get; set; }//地址
        public string OpenBank { get; set; }//开行户
        public string TaxNumber { get; set; }//税号

    }
}