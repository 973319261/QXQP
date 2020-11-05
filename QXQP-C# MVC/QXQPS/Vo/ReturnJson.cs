using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class ReturnJson
    {
        public bool State { get; set; }

        public string Text { get; set; }

        public object ObjData { get; set; }
        public string States { get; set; }
    }
}