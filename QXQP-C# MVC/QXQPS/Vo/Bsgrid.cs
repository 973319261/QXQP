using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    //T:泛型
    public class Bsgrid<T>
    {
        public bool success { get; set; }//成功否

        public int totalRows { get; set; }//总行数

        public int curPage { get; set; }//当前页面

        public List<T> data { get; set; }//数据
    }
}