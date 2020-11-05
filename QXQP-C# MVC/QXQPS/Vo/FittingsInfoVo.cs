using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class FittingsInfoVo: SYS_FittingsInfo
    {
        public string FittingsTypeName { get; set; }//配件类型名称
        public string VehicleType { get; set; }//车型名称
        public string SystemUnit { get; set; }//单位名称
        public string SuppliersName { get; set; }//供应商名称
    }
}