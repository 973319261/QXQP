using System.Web.Mvc;

namespace QXQPS.Areas.OtherfeaturesManagment
{
    public class OtherfeaturesManagmentAreaRegistration : AreaRegistration 
    {
        public override string AreaName 
        {
            get 
            {
                return "OtherfeaturesManagment";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context) 
        {
            context.MapRoute(
                "OtherfeaturesManagment_default",
                "OtherfeaturesManagment/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}