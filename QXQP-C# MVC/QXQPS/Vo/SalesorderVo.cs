using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    /// <summary>
    /// 销售订单
    /// </summary>
    public class SalesorderVo: PW_Salesorder
    {
        public string CustomerCode { get; set; } //客户编码
        public string CustomerName { get; set; } //客户名称
        public string Invoicedateks { get; set; } //开单日期

        private string deliverydate; //交货日期
        public string Deliverydates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    deliverydate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    deliverydate = value;
                }
            }
            get
            {
                return deliverydate;
            }
        } //交货日期

        private string auditdate;//审核日期
        public string Auditdates
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