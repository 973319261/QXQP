using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.BasicdataManagment.Controllers
{
    public class SupplierbasicController : Controller
    {
        // GET: BasicdataManagment/Supplierbasic
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Supplierbasic()//供应商基本资料
        {
            return View();
        }
        /// <summary>
        /// 编号
        /// </summary>
        /// <returns></returns>
        public ActionResult Num()
        {
            string Num = "";
            var Time = DateTime.Now.ToString("yyyyMMdd");
            try
            {
                int Count = myModels.SYS_Suppliers.Where(m => m.SuppliersFirm.Contains(Time)).Count() + 1;
                if (Count < 10)
                {
                    Num = "GYSH" + "-" + Time + "000" + Count;
                }
                else if (Count > 9 && Count < 100)
                {
                    Num = "GXSH" + "-" + Time + "-" + "00" + Count;
                }
                else if (Count > 99 && Count < 1000)
                {
                    Num = "GYSH" + "-" + Time + "-" + "0" + Count;
                }
            }
            catch (Exception)
            {
                return Json(Num, JsonRequestBehavior.AllowGet);
            }
            return Json(Num, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="bsgridPage"></param>
        /// <returns></returns>
        public ActionResult SelectSupplierbasic(BsgridPage bsgridPage)
        {
            int Count = myModels.SYS_Suppliers.Count();
            List<SYS_Suppliers> listSuppliers = myModels.SYS_Suppliers.OrderBy(m => m.SuppliersID).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<SYS_Suppliers> bsgrid = new Bsgrid<SYS_Suppliers>()
            {
                success = true,
                totalRows = Count,
                curPage = bsgridPage.curPage,
                data = listSuppliers
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 新增
        /// </summary>
        /// <returns></returns>
        public ActionResult InsertSupplierbasic(SYS_Suppliers Suppliers)
        {
            string strMsg = "failed";
            try
            {
                var SuppliersCount = (from tbSuppliers in myModels.SYS_Suppliers
                                      where tbSuppliers.SuppliersID == Suppliers.SuppliersID ||
                                      tbSuppliers.SuppliersName == Suppliers.SuppliersName
                                      select tbSuppliers).Count();
                if (SuppliersCount == 0)
                {
                    myModels.SYS_Suppliers.Add(Suppliers);
                    myModels.SaveChanges();
                    strMsg = "success";
                }
                else
                {
                    strMsg = "该供应商名称已经存在，不需要重复输入数据！";
                }


            }
            catch (Exception e)
            {
                strMsg = "failed";
            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="SuppliersID"></param>
        /// <returns></returns>
        public ActionResult UpdateSupplierbasicByID(int SuppliersID)
        {
            var list = myModels.SYS_Suppliers.Where(m => m.SuppliersID == SuppliersID).Select(m => new
            {
                SuppliersID = m.SuppliersID,//供应商ID
                SuppliersFirm = m.SuppliersFirm,//供应商号
                SuppliersName = m.SuppliersName,//供应商名称
                PinYinCode = m.PinYinCode,//拼音码
                MainBusiness = m.MainBusiness,//主要业务
                Address = m.Address,//地址
                Contacts = m.Contacts,//联系人
                TelePhone = m.TelePhone,//电话
                MobilePhone = m.MobilePhone,//手机
                Facsimile = m.Facsimile,//传真
                Mailbox = m.Mailbox,//邮箱
                Url = m.Url,//网址
                OpenBank = m.OpenBank,//开户行
                TaxNumber = m.TaxNumber,//税号
                InputPerson = m.InputPerson,//录入人
                StorageAdd = m.StorageAdd,//库房地址
                StorageTele = m.StorageTele,//库房电话
                Remark = m.Remark,//备注
                ToDeactivate = m.ToDeactivate,//停用标志
            });
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult UpdateSupplierbasic(SYS_Suppliers Suppliers)
        {
            var strMsg = "failed";
            try
            {
                //判断修改后的数据是否与数据库重复
                var oldSuppliersRow = (from tbSuppliers in myModels.SYS_Suppliers
                                       where tbSuppliers.SuppliersID != Suppliers.SuppliersID &&
                                       (tbSuppliers.SuppliersFirm == Suppliers.SuppliersFirm)
                                       select tbSuppliers).Count();
                if (oldSuppliersRow == 0)
                {
                    myModels.Entry(Suppliers).State = System.Data.Entity.EntityState.Modified;
                    //保存数据库
                    myModels.SaveChanges();
                    strMsg = "success";
                }
                else
                {
                    strMsg = "该客户信息已经存在，不需要重复录入！";
                }
            }
            catch (Exception)
            {
                strMsg = "failed";
            }
            return Json(strMsg, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="SuppliersID"></param>
        /// <returns></returns>
        public ActionResult DeleteSupplierbasic(int SuppliersID)
        {
            try
            {
                var listSupplierbasic = myModels.SYS_Suppliers
                    .Where(m => m.SuppliersID == SuppliersID).Single();
                myModels.SYS_Suppliers.Remove(listSupplierbasic);
                if (myModels.SaveChanges() > 0)
                {
                    return Json(true, JsonRequestBehavior.AllowGet);
                }
                else
                {
                    return Json(false, JsonRequestBehavior.AllowGet);
                }

            }
            catch (Exception E)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
    }
}