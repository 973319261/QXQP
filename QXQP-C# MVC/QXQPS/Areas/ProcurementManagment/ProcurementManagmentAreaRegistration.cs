using System.Web.Mvc;

namespace QXQPS.Areas.ProcurementManagment
{
    public class ProcurementManagmentAreaRegistration : AreaRegistration 
    {
        public override string AreaName 
        {
            get 
            {
                return "ProcurementManagment";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context) 
        {
            context.MapRoute(
                "ProcurementManagment_default",
                "ProcurementManagment/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}