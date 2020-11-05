using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class GppVo
    {
        private string documentsDate;//制单日期
        public string DocumentsDate
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    documentsDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    documentsDate = value;
                }
            }
            get
            {
                return documentsDate;
            }
        }//制单日期
        public string SingleNumber;//制单日期
        public string ShouldAmount;//制单日期
        public string DocumentsType;//制单日期
        public string BalanceState;//制单日期 
        public int DocumentsTypeID;
        public int BalanceStateID; 
    }
}