using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Common
{
    public class Tools
    {
        /// <summary>
        /// 绑定下拉框信息
        /// </summary>
        /// <param name="seletce"></param>
        /// <returns></returns>
        public static List<SelectVo> SetSelectJson(List<SelectVo> seletce)
        {
            //实例化表格
            List<SelectVo> list = new List<SelectVo>();
            //实例化实体
            SelectVo selectVo = new SelectVo
            {
                id = 0,
                text = "---请选择---"
            };
            list.Add(selectVo);
            list.AddRange(seletce);
            return list;
        }
    }
}