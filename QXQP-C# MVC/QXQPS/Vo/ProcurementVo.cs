using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class ProcurementVo:PW_Procurement
    {
        private string makeDate;//制单日期
        public string MakerDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    makeDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    makeDate = value;
                }
            }
            get
            {
                return makeDate;
            }
        }//制单日期

        private string deliveryDeadline;//交货期限
        public string DeliveryDeadlines
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    deliveryDeadline = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    deliveryDeadline = value;
                }
            }
            get
            {
                return deliveryDeadline;
            }
        }//交货期限


        private string auditerDate;//交货期限
        public string AuditerDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    auditerDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    auditerDate = value;
                }
            }
            get
            {
                return auditerDate;
            }
        }//交货期限
        public string SuppliersName { get; set; }//供应商名称
        public string SuppliersFirm { get; set; }//供应商编码
        public string WarehouseName { get; set; }//仓库名称
    }

}