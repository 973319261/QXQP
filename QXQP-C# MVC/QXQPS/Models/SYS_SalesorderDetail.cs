//------------------------------------------------------------------------------
// <auto-generated>
//     此代码已从模板生成。
//
//     手动更改此文件可能导致应用程序出现意外的行为。
//     如果重新生成代码，将覆盖对此文件的手动更改。
// </auto-generated>
//------------------------------------------------------------------------------

namespace QXQPS.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class SYS_SalesorderDetail
    {
        public int SalesorderDetailID { get; set; }
        public int SalesordeID { get; set; }
        public int WarehouseID { get; set; }
        public int FittingsInfoID { get; set; }
        public Nullable<decimal> Amount { get; set; }
        public Nullable<decimal> Discount { get; set; }
        public Nullable<decimal> Money { get; set; }
        public string Atretail { get; set; }
        public Nullable<decimal> Unitcost { get; set; }
        public string Remark { get; set; }
        public string FittingsType { get; set; }
    }
}
