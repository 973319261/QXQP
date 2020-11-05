using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class SalesVo : SYS_SalesCustomer
    {
        public string CustomerType { get; set; }
        /// <summary>
        /// 客户类别
        /// </summary>
        public string CustomerLevel { get; set; }
        /// <summary>
        /// 客户等级
        /// </summary>
        public string RegionName { get; set; }
        /// <summary>
        /// 客户区域
        /// </summary>
        public string CustomerSou { get; set; }
        /// <summary>
        /// 客户来源
        /// </summary>
        public string UserName { get; set; }
        /// <summary>
        /// 录入人
        /// </summary>
        public string PaymentName { get; set; }
        /// <summary>
        /// 付款方式
        /// </summary>
        /// 
    }
}