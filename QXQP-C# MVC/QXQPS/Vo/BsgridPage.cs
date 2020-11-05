using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class BsgridPage
    {
        public int pageSize { get; set; }//页面大小

        public int curPage { get; set; }

        public string sortName { get; set; }

        public string sortOrder { get; set; }

        public int GetStartIndex()//分页开始序号
        {
            return (curPage - 1) * pageSize;
        }
        public int GetEndIndex()//分页结束序号
        {
            return curPage * pageSize - 1;
        }
    }
}