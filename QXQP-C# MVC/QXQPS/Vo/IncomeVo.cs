using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class IncomeVo : PW_Incoming
    {
        private string makeDate;//制单日期
        public string MakerDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    makeDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
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
        }

        private string deliveryDeadline;//交货期限
        public string DeliveryDeadlines//交货期限
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
        }

        private string auditerDate;//审核时间
        public string AuditerDates//审核时间
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
        }
        public string SuppliersName { get; set; }//供应商名称
        public string SuppliersFirm { get; set; }//供应商编码
        public string WarehouseName { get; set; }//仓库名称
        public string BalanceState { get; set; }//结算状况
    }
}