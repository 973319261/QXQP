using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Controllers
{
    public class HomeController : Controller
    {
        // GET: Home
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Login()//登陆页面
        {
            //声明三个空白的字符串
            string UserNum = "";
            string Password = "";
            //初始化一个bool类型变量
            bool isRember = false;
            //实例化一个HttpCookie获取会话机制Cooke中的数据
            HttpCookie cookie = System.Web.HttpContext.Current.Request.Cookies["user"];
            //判断会话机制中得到的数据是否为空
            if (cookie != null)
            {
                //cookie不为空
                if (cookie["UserNum"] != null)
                {
                    //把cookie的值给到初始声明的变量
                    UserNum = System.Web.HttpContext.Current.Server.UrlDecode(cookie["UserNum"]);
                }
                if (cookie["Password"] != null)
                {
                    //把cookie的值给到初始声明的变量
                    Password = System.Web.HttpContext.Current.Server.UrlDecode(cookie["Password"]);
                }
                isRember = true;
            }
            //ViewBag获取动态视图数据字典
            ViewBag.UserNum = UserNum;
            ViewBag.Password = Password;
            ViewBag.isRember = isRember;
            return View();
        }
        public ActionResult Main()//主页面
        {
            try
            {
                string strUserID = Session["UserID"].ToString();// 传递 UserID   
                string strUserTypeName = Session["UserTypeName"].ToString();// 传递 UserTypeID  
                string strUserName = Session["UserName"].ToString();//登录时间
                ViewBag.UserID = strUserID;
                ViewBag.UserTypeName = strUserTypeName;//ViewBag：获取动态视图数据字典
                ViewBag.UserName = strUserName;//ViewBag：获取动态视图数据字典
                return View();
            }
            catch (Exception)
            {
                return RedirectToAction("Login");
            }
        }
        public ActionResult Welcome()//我的桌面
        {
            return View();
        }
        public ActionResult ValideCode()//生成验证码
        {
            //调用封装好的ValidCodeUtils里面的方法生成4位数随机字符串
            string strValideCode = Common.ValidCodeUtils.GetRandomCode(4);
            //借用会话机制保存字符串
            Session["ValideCode"] = strValideCode;
            return Json(strValideCode, JsonRequestBehavior.AllowGet);
        }
        public ActionResult CheckLogin()//验证登录

        {
            string strmsg = "操作失败！";
            //control获取页面的数据
            //Request.Form 使用 post(只能是post提交) 方法由表单发送的所有的表单（输入）值
            string strUserNum = Request.Form["UserNum"];//用户名
            string strPassword = Request.Form["Password"];//密码
            string strValidateCode = Request.Form["ValidateCode"];//验证码
            string strRememberMe = Request.Form["RememberMe"];//是否记住我

            //1、判断验证码是否 
            string myValideCode = "";
            //直接获取session中的Session["ValideCode"]
            if (Session["ValideCode"] != null)
            {
                //事先生成的字符串
                myValideCode = Session["ValideCode"].ToString().Trim();
                //忽略大小写比较：输入文本的二维码和 随机生成的5位数二维码是否一致
                if (myValideCode.Equals(strValidateCode, StringComparison.CurrentCultureIgnoreCase))
                {
                    //2、判断用户名：根据用户名去数据库查询看看是否有唯一条数据
                    try
                    {
                        var tbUser = (from dbUser in myModels.PW_User
                                      join dbUserType in myModels.SYS_UserType on dbUser.UserTypeID equals dbUserType.UserTypeID
                                      where dbUser.UserNum == strUserNum.Trim()
                                      select new
                                      {
                                          dbUser.UserID,
                                          dbUser.UserName,
                                          dbUser.Password,
                                          dbUserType.UserTypeName,
                                          dbUser.ToUse
                                      }).Single();//Single返回序列中的唯一元素
                                                  //3、判断密码：对页面获取到的密码进行256位AES加密算法加密 
                        string strPass = Common.AESEncryptHelper.Encrypt(strPassword);
                        //比较数据库中保存的密码和页面加密的密码是否一致
                        if (tbUser.Password.Equals(strPass))
                        {
                            if (tbUser.ToUse==true)
                            {
                                //3、记住我(判断页面那边的复选框是否打上勾)
                                if (strRememberMe != null)
                                {
                                    //创建Cookie
                                    HttpCookie cookie = new HttpCookie("user");
                                    cookie["UserNum"] = strUserNum;
                                    cookie["Password"] = strPassword;
                                    //设置cookie的有效期
                                    cookie.Expires = DateTime.Now.AddDays(7);
                                    Response.Cookies.Add(cookie);
                                }
                                else
                                { //删除 cookie（即从用户的硬盘中物理移除 cookie）是修改 cookie 的一种形式。 
                                  //由于 cookie 在用户的计算机中，因此无法将其直接移除。    
                                  //但是，可以让浏览器来为您删除 cookie。     
                                  //该技术是创建一个与要删除的 cookie 同名的新 cookie，    
                                  //并将该 cookie 的到期日期设置为早于当前日期的某个日期。     
                                  //当浏览器检查 cookie 的到期日期时，浏览器便会丢弃这个现已过期的 cookie。 

                                    //下面的代码删除应用程序中cookie 的一种方法：
                                    HttpCookie cookie = new HttpCookie("user");
                                    cookie.Expires = DateTime.Now.AddDays(-1);
                                    Response.Cookies.Add(cookie);
                                }
                                //向主Main页面传输数据
                                //生成一个字符串接收当前登录那一刻的时间，格式yyyy-MM-dd HH:mm
                                // 设置session
                                Session["UserID"] = tbUser.UserID; // 传递 UserID   
                                Session["UserName"] = tbUser.UserName;// 传递 UserTypeName  
                                Session["UserTypeName"] = tbUser.UserTypeName;// 传递 UserTypeName  
                                strmsg = "seccess";
                            }
                            else {
                                strmsg = "O";
                            }
                        }
                        else
                        {
                            strmsg = "A";
                        }
                    }
                    catch (Exception)
                    {
                        strmsg = "B";
                    }
                }
                else
                {
                    strmsg = "C";
                }
            }
            else
            {
                strmsg = "登陆出错";
            }
            return Json(strmsg, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectJurisdiction(int UserID)//查询权限
        {
            try
            {
                var list = myModels.PW_Jurisdiction.Where(m => m.UserID == UserID).Select(m=>new{
                    m.ModularID,
                    m.ToJdtion
                }).ToList();
                return Json(list, JsonRequestBehavior.AllowGet);
            }
            catch (Exception)
            {
                return Json("", JsonRequestBehavior.AllowGet);
            }
        }
    }
}