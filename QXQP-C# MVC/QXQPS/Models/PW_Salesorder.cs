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
    
    public partial class PW_Salesorder
    {
        public int SalesordeID { get; set; }
        public int SalesCustomerID { get; set; }
        public string SalesorderNum { get; set; }
        public Nullable<System.DateTime> Invoicedatek { get; set; }
        public string Invoicno { get; set; }
        public Nullable<System.DateTime> Deliverydate { get; set; }
        public string Operatorf { get; set; }
        public Nullable<bool> Reviewwhether { get; set; }
        public string Thereviewer { get; set; }
        public Nullable<System.DateTime> Auditdate { get; set; }
        public Nullable<decimal> Theamount { get; set; }
        public string Remark { get; set; }
        public string Documents { get; set; }
    }
}
