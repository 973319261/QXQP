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
    
    public partial class SYS_SalesCustomer
    {
        public int SalesCustomerID { get; set; }
        public int CustomerLevelID { get; set; }
        public int CustomerTypeID { get; set; }
        public int PaymentID { get; set; }
        public int UserID { get; set; }
        public int RegionID { get; set; }
        public int CustomerSouID { get; set; }
        public string CustomerCode { get; set; }
        public string CustomerName { get; set; }
        public string Address { get; set; }
        public string ZipCode { get; set; }
        public string FullName { get; set; }
        public string PinYinCode { get; set; }
        public string IdNumber { get; set; }
        public string Contacts { get; set; }
        public string Facsimile { get; set; }
        public string TelePhone { get; set; }
        public string MobilePhone { get; set; }
        public string Emai { get; set; }
        public string Remark { get; set; }
        public string InputPerson { get; set; }
        public Nullable<bool> ToDeactivate { get; set; }
    }
}
