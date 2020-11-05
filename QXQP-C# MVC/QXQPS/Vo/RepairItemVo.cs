using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class RepairItemVo
    {
        //修理项目字段
        public int MaintenanceID { get; set; }
        public int PreRepairItemDetailID { get; set; }
        public int RecRepairItemDetailID { get; set; }
        public int RepairItemID { get; set; }
        public decimal RepairCharge { get; set; }
        public decimal Discount { get; set; }
        public decimal AmountPaid { get; set; }
        public int MaintainabilityID { get; set; }
        public string Remark { get; set; }
    }
}