using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class ProductVo: SYS_PreProductDetail
    {
        //产品材料字段
        public string FittingsSpec { get; set; }//配件规格
        public int RecProductDetailID { get; set; }
        public int ReceptionID { get; set; }
        public string MaintainabilityName { get; set; }
    }
}