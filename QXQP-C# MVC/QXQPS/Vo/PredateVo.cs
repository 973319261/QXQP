using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class PredateVo : PW_Predate//预约表字段
    {
        private string openDate;//开单日期
        public string OpenDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    openDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    openDate = value;
                }
            }
            get
            {
                return openDate;
            }
        }//开单日期

        private string maintainData;//维修日期
        public string MaintainDatas
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    maintainData = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    maintainData = value;
                }
            }
            get
            {
                return maintainData;
            }
        }//维修日期

        public string CarderName { get; set; }//接车人名称
        public string RepairName { get; set; }//接车人名称
        public string PredateNums { get; set; }//预约单ID
    }
}
