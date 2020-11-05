using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class SpareVo : SYS_FittingsInfo
    {
        public string FittingsTypeName { get; set; }
        /// <summary>
        /// 商品类别
        /// </summary>
        public string SystemUnit { get; set; }
        /// <summary>
        /// 单位
        /// </summary>
        public string VehicleType { get; set; }
        /// <summary>
        /// 车型
        /// </summary>
        public string SuppliersName { get; set; }
    }
}