using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.OtherfeaturesManagment.Controllers
{
    public class InsuranceController : Controller
    {
        // GET: OtherfeaturesManagment/Insurance
        public ActionResult Insurance()//保险索赔明细表
        {
            return View();
        }
    }
}