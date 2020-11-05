using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.SystemInstallManagment.Controllers
{
    public class OperatingController : Controller
    {
        // GET: SystemInstallManagment/Operating
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Operating()//操作权限
        {
            return View();
        }
        public ActionResult SelectUser()//查询用户
        {
            var list = myModels.PW_User.OrderBy(m=>m.UserNum).Select(m=>new {
                UserID = m.UserID,
                UserNum = m.UserNum.Trim(),
                UserName = m.UserName.Trim(),
                ToUse = m.ToUse==true?"true":"false",
            }).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectJurisdiction(string UserID)//查询权限
        {
            try
            {
                var UserId = 0;
                var listUser = myModels.PW_User.OrderBy(m => m.UserNum).Select(m => new {
                    m.UserID
                }).First();
                if (UserID != "undefined")
                {
                    var ArrUser = UserID.Split(',');
                    UserId = Convert.ToInt32(ArrUser[1]);
                }
                else
                {
                    UserId = listUser.UserID;
                }
                var list = (from tbJurisdiction in myModels.PW_Jurisdiction
                            join tbModular in myModels.SYS_Modular on tbJurisdiction.ModularID equals tbModular.ModularID
                            join tbModularType in myModels.SYS_ModularType on tbModular.ModularTypeID equals tbModularType.ModularTypeID
                            where tbJurisdiction.UserID== UserId
                            select new
                            {
                                tbJurisdiction.UserID,
                                tbJurisdiction.JdtionID,
                                tbJurisdiction.ToJdtion,
                                tbModular.ModularID,
                                tbModular.ModularCode,
                                tbModular.ModularName,
                                tbModularType.ModularTypeID,
                                tbModularType.ModularTypeCode,
                                tbModularType.ModularTypeName,
                            }).ToList();
                return Json(list, JsonRequestBehavior.AllowGet);
            }
            catch (Exception)
            {
                return Json("", JsonRequestBehavior.AllowGet);
            }
        }
        public class Users
        {
            public string UserID { get; set; }
            public bool ToUse { get; set; }
        }
        public class Juris
        {
            public int JdtionID { get; set; }
            public bool ToJdtion { get; set; }
        }
        public ActionResult BaveJurisdiction(List<Users> listUser,List<Juris> listJuris)
        {
            try
            {
                foreach (var item in listUser)
                {
                    var UserID =Convert.ToInt32(item.UserID.Split(',')[1]);
                    var list = myModels.PW_User.Where(m => m.UserID == UserID).Single();
                    list.ToUse = item.ToUse;
                    myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                    myModels.SaveChanges();
                }
                if (listJuris != null)
                {
                    foreach (var item in listJuris)
                    {
                        var list = myModels.PW_Jurisdiction.Where(m => m.JdtionID == item.JdtionID).Single();
                        list.ToJdtion = item.ToJdtion;
                        myModels.Entry(list).State = System.Data.Entity.EntityState.Modified;
                        myModels.SaveChanges();
                    }
                }
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
    }
}