using System.Web.Mvc;

namespace QXQPS.Areas.MechanicsManagment
{
    public class MechanicsManagmentAreaRegistration : AreaRegistration 
    {
        public override string AreaName 
        {
            get 
            {
                return "MechanicsManagment";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context) 
        {
            context.MapRoute(
                "MechanicsManagment_default",
                "MechanicsManagment/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}