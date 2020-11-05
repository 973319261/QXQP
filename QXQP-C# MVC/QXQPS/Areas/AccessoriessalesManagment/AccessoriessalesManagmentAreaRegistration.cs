using System.Web.Mvc;

namespace QXQPS.Areas.AccessoriessalesManagment
{
    public class AccessoriessalesManagmentAreaRegistration : AreaRegistration 
    {
        public override string AreaName 
        {
            get 
            {
                return "AccessoriessalesManagment";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context) 
        {
            context.MapRoute(
                "AccessoriessalesManagment_default",
                "AccessoriessalesManagment/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}