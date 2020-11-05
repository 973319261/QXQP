using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class BingVo
    {
        private string receipstDate;//单据日期
        public string ReceipstDate
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    receipstDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    receipstDate = value;
                }
            }
            get
            {
                return receipstDate;
            }
        }//单据日期
        public string ReceipstType;//单据类型
        public string Oddnumbers;//单号
        public string Paymentstate;//结算状态
        public string Totalmoney;//总金额
        public int BalanceStateID;
        public int DocumentsTypeID;

    }
}