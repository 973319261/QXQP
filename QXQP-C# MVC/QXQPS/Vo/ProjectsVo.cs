using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class ProjectsVo : SYS_RepairItem
    {
        public string RepairClassNum { get; set; }
        public string RepairClassName { get; set; }
        public string Maintenance { get; set; }
        public string MaintenanceName { get; set; }
    }
}