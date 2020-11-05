using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.BasicdataManagment.Controllers
{
    public class ClientlocaleController : Controller
    {
        // GET: BasicdataManagment/Clientlocale
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Client()//客户区域设置
        {
            return View();
        }
        /// <summary>
        /// 树形查询
        /// </summary>
        /// <returns></returns>
        public JsonResult Get_TreeView()
        {
            List<Dictionary<string, object>> jsonlist = new List<Dictionary<string, object>>();
            List<TreeVo> treeList = new List<TreeVo>();

            var listAuthorityTemplet = (from TbTrees in myModels.SYS_Region
                                        select new TreeVo
                                        {
                                            SireID = TbTrees.SireID, //父亲id  
                                            ViscountID = TbTrees.ViscountID,   //儿子id                                   
                                            RegionName = TbTrees.RegionName,//节点名
                                        }).ToList();
            for (int i = 0; i < listAuthorityTemplet.Count; i++)
            {
                TreeVo tree = new TreeVo();
                tree.SireID = listAuthorityTemplet[i].SireID;//父亲id  
                tree.ViscountID = listAuthorityTemplet[i].ViscountID;//儿子id   
                tree.RegionName = listAuthorityTemplet[i].RegionName.ToString();//节点名
                treeList.Add(tree);
            }

            foreach (var model in treeList)
            {
                Dictionary<string, object> jsonobj = new Dictionary<string, object>();
                jsonobj.Add("id", model.SireID); //父亲id
                jsonobj.Add("pId", model.ViscountID);//儿子id
                jsonobj.Add("name", model.RegionName);//节点名称
                //jsonobj.Add("icon", "");
                jsonlist.Add(jsonobj);
            }
            return Json(jsonlist, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 新增
        /// </summary>
        /// <param name="Client"></param>
        /// <returns></returns>
        //public ActionResult InsertClient(SYS_Region Client)
        //{
        //    string strMsg = "failed";
        //    try
        //    {
        //        var ClientCount = (from tbClient in myModels.SYS_Region
        //                           where tbClient.RegionID == Client.RegionID ||
        //                           tbClient.RegionName == Client.RegionName
        //                           select tbClient).Count();
        //        if (ClientCount == 0)
        //        {
        //            myModels.SYS_Region.Add(Client);
        //            myModels.SaveChanges();
        //            strMsg = "success";
        //        }
        //        else
        //        {
        //            strMsg = "该区域已经存在，不需要重复输入数据！";
        //        }
        //    }
        //    catch (Exception e)
        //    {
        //        strMsg = "failed";
        //    }
        //    return Json(strMsg, JsonRequestBehavior.AllowGet);
        //}
    }
}