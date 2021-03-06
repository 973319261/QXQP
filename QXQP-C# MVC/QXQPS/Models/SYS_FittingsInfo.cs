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
    
    public partial class SYS_FittingsInfo
    {
        public int FittingsInfoID { get; set; }
        public int FittingsTypeID { get; set; }
        public int SystemUnitID { get; set; }
        public int VehicleTypeID { get; set; }
        public int SuppliersID { get; set; }
        public string FittingsName { get; set; }
        public string FittingsCode { get; set; }
        public string Barcode { get; set; }
        public string Specification { get; set; }
        public string Brand { get; set; }
        public decimal Intake { get; set; }
        public decimal SalesPrice { get; set; }
        public decimal WholesalePrice { get; set; }
        public Nullable<int> InventoryMax { get; set; }
        public Nullable<int> InventoryMin { get; set; }
        public Nullable<decimal> OpenPrice { get; set; }
        public string Remark { get; set; }
        public byte[] Picture { get; set; }
        public Nullable<decimal> InvenQuan { get; set; }
    }
}
