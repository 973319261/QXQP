using System.Web.Mvc;

namespace QXQPS.Areas.AccInventManagment
{
    public class AccInventManagmentAreaRegistration : AreaRegistration 
    {
        public override string AreaName 
        {
            get 
            {
                return "AccInventManagment";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context) 
        {
            context.MapRoute(
                "AccInventManagment_default",
                "AccInventManagment/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}