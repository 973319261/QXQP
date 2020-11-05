using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class CollageDetaiVo: SYS_CollageDetai
    {
        public string MaintainabilityName { get; set; }
        public string WarehouseName { get; set; }
        public string MaintenanceNum { get; set; }
    }
}