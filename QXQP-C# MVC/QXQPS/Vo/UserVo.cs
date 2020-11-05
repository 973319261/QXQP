using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class UserVo:PW_User
    {
        public string DepartmentName { get; set; }
        public string UserTypeName { get; set; }
        public string StrToUse { get; set; }
    }
}