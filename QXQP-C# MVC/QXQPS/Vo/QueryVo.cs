using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class QueryVo: SYS_Inventory
    {
        public string WarehouseName { get; set; }
        /// <summary>
        /// 仓库名称
        /// </summary>
        /// 

        public string FittingsTypeName { get; set; }
    }
}