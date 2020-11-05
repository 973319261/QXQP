using System.Web.Mvc;

namespace QXQPS.Areas.SystemInstallManagment
{
    public class SystemInstallManagmentAreaRegistration : AreaRegistration 
    {
        public override string AreaName 
        {
            get 
            {
                return "SystemInstallManagment";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context) 
        {
            context.MapRoute(
                "SystemInstallManagment_default",
                "SystemInstallManagment/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}