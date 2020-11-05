using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class TreeVo
    {

        public string id { get; set; }
        public string name { get; set; }
        public bool open { get; set; }
        public bool isParent { get; set; }
        public string pId { get; set; }

        public int idp { get; set; }

        /// <summary>
        /// 配件大类
        /// </summary>
        public int? ParentID { get; set; }
        public int? SonID { get; set; }
        public string FittingsTypeName { get; set; }
        public string FittingsTypeNum { get; set; }
        public string Remark { get; set; }

        /// <summary>
        /// 客户区域
        /// </summary>
        public int? SireID { get; set; }
        public int? ViscountID { get; set; }
        public string RegionName { get; set; }
        public string RegionNum { get; set; }
        public string ToDeactivate { get; set; }

        /// <summary>
        /// 修理类别
        /// </summary>
        public int? FatherID { get; set; }
        public int? SeedID { get; set; }
        public string RepairClassName { get; set; }
        public string RepairClassNum { get; set; }
    }
}