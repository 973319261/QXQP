using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class SalesorderDetailVo : SYS_SalesorderDetail
    {
        public string FittingsCode;//配件编码

        public string FittingsName;//配件名称

        public string Specification;//配件规格

        public int SystemUnitID;//系统单位ID
        
        public string SystemUnit;//系统名称

        public string VehicleType;//车辆名称


    }
}
