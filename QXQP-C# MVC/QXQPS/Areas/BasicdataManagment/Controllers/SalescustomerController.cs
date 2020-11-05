using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.BasicdataManagment.Controllers
{
    public class SalescustomerController : Controller
    {
        // GET: BasicdataManagment/Salescustomer
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Sales()//销售客户设置
        {
            return View();
        }
        /// <summary>
        /// 销售客户单号
        /// </summary>
        /// <returns></returns>
        public ActionResult Num()
        {
            string Num = "";
            var Time = DateTime.Now.ToString("yyyyMMdd");
            try
            {
                int Count = myModels.SYS_SalesCustomer.Where(m => m.CustomerCode.Contains(Time)).Count() + 1;
                if (Count < 10)
                {
                    Num = "XSKH" + "-" + Time + "000" + Count;
                }
                else if (Count > 9 && Count < 100)
                {
                    Num = "XSKH" + "-" + Time + "00" + Count;
                }
                else if (Count > 99 && Count < 1000)
                {
                    Num = "XSKH" + "-" + Time + "0" + Count;
                }
            }
            catch (Exception)
            {
                return Json(Num, JsonRequestBehavior.AllowGet);
            }
            return Json(Num, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 查询客户区域
        /// </summary>
        /// <returns></returns>
        public ActionResult SelectRegion()
        {
            var list = from tbRegion in myModels.SYS_Region
                       select tbRegion;
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="bsgridPage"></param>
        /// <returns></returns>
        public ActionResult SelectSales(BsgridPage bsgridPage, int RegionID)
        {
            var listSales = (from tbSales in myModels.SYS_SalesCustomer
                             join tbCustomerType in myModels.SYS_CustomerType on tbSales.CustomerTypeID equals tbCustomerType.CustomerTypeID
                             join tbCustomerLevel in myModels.SYS_CustomerLevel on tbSales.CustomerLevelID equals tbCustomerLevel.CustomerLevelID
                             join tbRegion in myModels.SYS_Region on tbSales.RegionID equals tbRegion.RegionID
                             join tbCustomerSou in myModels.SYS_CustomerSou on tbSales.CustomerSouID equals tbCustomerSou.CustomerSouID
                             join tbPayment in myModels.SYS_Payment on tbSales.PaymentID equals tbPayment.PaymentID
                             join tbUser in myModels.PW_User on tbSales.UserID equals tbUser.UserID
                             select new SalesVo
                             {
                                 RegionID = tbRegion.RegionID,//客户区域ID
                                 SalesCustomerID = tbSales.SalesCustomerID,//销售客户ID
                                 CustomerCode = tbSales.CustomerCode,//客户编号
                                 CustomerName = tbSales.CustomerName,//客户名称
                                 FullName = tbSales.FullName,//全称
                                 CustomerType = tbCustomerType.CustomerType,//客户类别
                                 CustomerLevel = tbCustomerLevel.CustomerLevel,//客户等级
                                 RegionName = tbRegion.RegionName,//客户区域
                                 CustomerSou = tbCustomerSou.CustomerSou,//客户来源
                                 Contacts = tbSales.Contacts,//联系人
                                 TelePhone = tbSales.TelePhone,//电话
                                 MobilePhone = tbSales.MobilePhone,//手机
                                 IdNumber = tbSales.IdNumber,//身份证
                                 Address = tbSales.Address,//地址
                                 ZipCode = tbSales.ZipCode,//邮箱
                                 UserName = tbUser.UserName,//业务员
                                 ToDeactivate = tbSales.ToDeactivate,//停用标志
                                 PaymentName = tbPayment.PaymentName,//付款方式
                                 InputPerson = tbSales.InputPerson,//录入人
                                 Remark = tbSales.Remark,//备注
                             }).ToList();
            if (RegionID > 0)
            {
                listSales = listSales.Where(m => m.RegionID == RegionID).ToList();
            }
            int count = listSales.Count();
            List<SalesVo> listFittingsInfo = listSales.OrderByDescending(m => RegionID).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<SalesVo> bsgrid = new Bsgrid<SalesVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listFittingsInfo,
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 新增
        /// </summary>
        /// <param name="Sales"></param>
        /// <returns></returns>
        public ActionResult InsertSales(SYS_SalesCustomer Sales)
        {
            string strMsg = "failed";
            try
            {
                //判断销售客户表中是否已经存在新增的销售客户信息
                var SalesCount = (from tbSales in myModels.SYS_SalesCustomer
                                  where tbSales.SalesCustomerID == Sales.SalesCustomerID ||
                                  tbSales.IdNumber == Sales.IdNumber
                                  select tbSales).Count();
                if (SalesCount == 0)
                {
                    if (Sales.CustomerName != null)
                    {
                        if (Sales.IdNumber != null)
                        {
                            if (Sales.CustomerCode != null)
                            {
                                myModels.SYS_SalesCustomer.Add(Sales);
                                myModels.SaveChanges();
                                strMsg = "success";
                            }
                            else
                            {
                                strMsg = "编号不能为空，请输入完整信息";
                            }
                        }
                        else
                        {
                            strMsg = "身份证不能为空，请输入完整信息";

                        }
                    }
                    else
                    {
                        strMsg = "客户名称不能为空，请输入完整信息";
                    }
                }
                else
                {
                    strMsg = "该销售客户信息已经存在，不需要重复输入数据！";
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
        /// <param name="SalesCustomerID"></param>
        /// <returns></returns>
        public ActionResult SelectSale(int SalesCustomerID)
        {
            var list = myModels.SYS_SalesCustomer.Where(m => m.SalesCustomerID == SalesCustomerID).Select(m => new
            {
                SalesCustomerID = m.SalesCustomerID,
                RegionID = m.RegionID,
                CustomerCode = m.CustomerCode,
                CustomerName = m.CustomerName,
                CustomerSouID = m.CustomerSouID,
                Address = m.Address,
                ZipCode = m.ZipCode,
                FullName = m.FullName,
                PinYinCode = m.PinYinCode,
                IdNumber = m.IdNumber,
                Contacts = m.Contacts,
                Facsimile = m.Facsimile,
                TelePhone = m.TelePhone,
                MobilePhone = m.MobilePhone,
                Emai = m.Emai,
                CustomerLevelID = m.CustomerLevelID,
                CustomerTypeID = m.CustomerTypeID,
                PaymentID = m.PaymentID,
                Remark = m.Remark,
                UserID = m.UserID,
                InputPerson = m.InputPerson,
                ToDeactivate = m.ToDeactivate,
            });
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult UpdateSales(SYS_SalesCustomer Sales)
        {
            var strMsg = "failed";
            try
            {
                //判断修改后的数据是否与数据库数据重复
                var oldSalesRow = (from tbSales in myModels.SYS_SalesCustomer
                                   where tbSales.SalesCustomerID != Sales.SalesCustomerID &&
                                   (tbSales.CustomerCode == Sales.CustomerCode ||
                                   tbSales.IdNumber == Sales.IdNumber)
                                   select tbSales).Count();
                if (oldSalesRow == 0)
                {
                    if (Sales.CustomerName != null)
                    {
                        if (Sales.IdNumber != null)
                        {
                            if (Sales.CustomerCode != null)
                            {
                                myModels.Entry(Sales).State = System.Data.Entity.EntityState.Modified;
                                //保存数据库
                                myModels.SaveChanges();
                                strMsg = "success";
                            }
                            else
                            {
                                strMsg = "编号不能为空，请输入完整信息";
                            }
                        }
                        else
                        {
                            strMsg = "身份证不能为空，请输入完整信息";

                        }
                    }
                    else
                    {
                        strMsg = "客户名称不能为空，请输入完整信息";
                    }
                }
                else
                {
                    strMsg = "该客户信息已存在，不需要重复录入！";
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
        /// <param name="SalesCustomerID"></param>
        /// <returns></returns>
        public ActionResult DeleteSales(int SalesCustomerID)
        {
            try
            {
                var listSales = myModels.SYS_SalesCustomer
                    .Where(m => m.SalesCustomerID == SalesCustomerID).Single();
                myModels.SYS_SalesCustomer.Remove(listSales);
                if (myModels.SaveChanges() > 0)
                {
                    return Json(true, JsonRequestBehavior.AllowGet);
                }
                else
                {
                    return Json(false, JsonRequestBehavior.AllowGet);
                }
            }
            catch (Exception e)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
        }
    }
}