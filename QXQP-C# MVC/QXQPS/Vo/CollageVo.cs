using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class CollageVo: PW_Reception
    {
        public int CollageID { get; set; }//领料ID
        public string ForeMan { get; set; }//领料员
        public string Auditor { get; set; }//审核人
        public string Operator { get; set; }//操作人
        public string Remark { get; set; }//备注
        public string StrAudit { get; set; }//备注


        private string collageDate;//领料日期
        public string CollageDate
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    collageDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    collageDate = value;
                }
            }
            get
            {
                return collageDate;
            }
        }//领料日期

        private string auditDate;//审核日期
        public string AuditDate
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    auditDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    auditDate = value;
                }
            }
            get
            {
                return auditDate;
            }
        }//审核日期

    }
}