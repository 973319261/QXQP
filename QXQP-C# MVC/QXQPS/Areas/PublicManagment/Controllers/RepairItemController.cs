using QXQPS.Vo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.PublicManagment.Controllers
{
    public class RepairItemController : Controller
    {
        // GET: PublicManagment/RepairItem
        /// <summary>
        ///公共表格信息
        /// </summary>
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult RepairItemChange(int RepairItemID)//表格修理项目下拉框改变查询
        {
            var list = (from tbRepairItem in myModels.SYS_RepairItem
                       join tbMaintenance in myModels.SYS_Maintenance on tbRepairItem.MaintenanceID equals tbMaintenance.MaintenanceID
                       where tbRepairItem.RepairItemID == RepairItemID
                        select new RepairItemVo
                       {
                           MaintenanceID = tbRepairItem.MaintenanceID,
                           RepairItemID = tbRepairItem.RepairItemID,
                           RepairCharge = (decimal)tbRepairItem.RepairCharge
                       }).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ExpensesChange(int ExpensesID)//表格其他费用下拉框改变查询
        {
            var list = (from tbExpenses in myModels.SYS_Expenses
                        where tbExpenses.ExpensesID == ExpensesID
                        select tbExpenses).ToList();
            return Json(list, JsonRequestBehavior.AllowGet);
        }
    }
}