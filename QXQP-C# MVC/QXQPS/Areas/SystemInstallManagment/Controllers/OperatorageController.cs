using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.SystemInstallManagment.Controllers
{
    public class OperatorageController : Controller
    {
        // GET: SystemInstallManagment/Operatorage
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Operatorage()//操作员管理
        {
            return View();
        }
        public ActionResult SelectUser()//查询用户
        {
            var list = (from tbUser in myModels.PW_User
                        join tbUserType in myModels.SYS_UserType on tbUser.UserTypeID equals tbUserType.UserTypeID
                        join tbDepartment in myModels.SYS_Department on tbUser.DepartmentID equals tbDepartment.DepartmentID
                        orderby tbUser.UserNum
                        select new UserVo
                        {
                            UserID = tbUser.UserID,
                            UserNum = tbUser.UserNum.Trim(),
                            UserName = tbUser.UserName.Trim(),
                            Password = tbUser.Password,
                            DepartmentID = tbDepartment.DepartmentID,
                            DepartmentName = tbDepartment.DepartmentName.Trim(),
                            UserTypeID = tbUserType.UserTypeID,
                            UserTypeName = tbUserType.UserTypeName.Trim(),
                            Remark = tbUser.Remark.Trim()!=null? tbUser.Remark.Trim():"无",
                            StrToUse = tbUser.ToUse==true?"true":"false",
                        }).ToList();
            foreach (var item in list)
            {
                item.Password = Common.AESEncryptHelper.Decrypt(item.Password);
            }
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult JudgingUser(string UserNum)//判断用户是否存在
        {
            try
            {
                var list = myModels.PW_User.Where(m => m.UserNum == UserNum).SingleOrDefault();
                if (list != null)
                {
                    return Json(true, JsonRequestBehavior.AllowGet);
                }
                else {
                    return Json(false, JsonRequestBehavior.AllowGet);
                }
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult InsertUser(PW_User pw_User)//新增用户
        {
            try
            {
                pw_User.Password = Common.AESEncryptHelper.Encrypt(pw_User.Password);
                myModels.PW_User.Add(pw_User);
                myModels.SaveChanges();
                var UserID = pw_User.UserID;
                for (int i = 1; i < 94; i++)
                {
                    PW_Jurisdiction pw_Jurisdiction = new PW_Jurisdiction();
                    pw_Jurisdiction.UserID = UserID;
                    pw_Jurisdiction.ModularID = i;
                    if (pw_User.UserTypeID == 2)
                    {
                        pw_Jurisdiction.ToJdtion = true;
                    }
                    else {
                        if (i == 84 || i == 85)
                        {
                            pw_Jurisdiction.ToJdtion = false;
                        }
                        else
                        {
                            pw_Jurisdiction.ToJdtion = true;
                        }
                    }
                    myModels.PW_Jurisdiction.Add(pw_Jurisdiction);
                    myModels.SaveChanges();
                }
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult BaveUser(List<PW_User> listUser)//保存用户信息
        {
            try
            {
                foreach (var item in listUser)
                {
                    var UserID = item.UserID;
                    item.Password = Common.AESEncryptHelper.Encrypt(item.Password);
                    if (UserID == 0)
                    {

                        myModels.PW_User.Add(item);
                        myModels.SaveChanges();
                    }
                    else {
                        myModels.Entry(item).State = System.Data.Entity.EntityState.Modified;
                    }
                        myModels.SaveChanges();
                }
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public class UserID
        {
            public int UserId { get; set; }
        }
        public ActionResult DelectUser(List<UserID> UserID) {
            try
            {
                foreach (var item in UserID)
                {
                    var UserId = item.UserId;
                    var listUser = myModels.PW_User.Where(m => m.UserID == UserId).ToList();
                    myModels.PW_User.RemoveRange(listUser);
                    myModels.SaveChanges();
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