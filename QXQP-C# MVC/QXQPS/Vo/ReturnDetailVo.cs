using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class ReturnDetailVo: SYS_ReturnFormDetail
    {
        public string FittingsCode { get; set; }//配件编码
        public string FittingsTypeName { get; set; }//配件类型名称
        public string FittingsName { get; set; }//配件名称
        public string Specification { get; set; }
        public string VehicleType { get; set; }//车型名称
        public string SystemUnit { get; set; }//单位名称
    }
}