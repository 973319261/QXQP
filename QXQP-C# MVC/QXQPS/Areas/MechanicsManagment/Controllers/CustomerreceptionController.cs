using QXQPS.Models;
using QXQPS.Vo;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace QXQPS.Areas.MechanicsManagment.Controllers
{
    public class CustomerreceptionController : Controller
    {
        // GET: MechanicsManagment/Customerreception
        Models.QXQPEntities myModels = new Models.QXQPEntities();
        public ActionResult Customer()//客户接待
        {
            return View();
        }
        public ActionResult MaintenanceNum()//生成预约单号
        {
            string MaintenanceNum = "";
            var date = "W" + DateTime.Now.ToString("yyyyMMdd");
            var datetime = DateTime.Now.ToString("yyyyMMddHHmmss");
            try
            {
                var list = myModels.PW_Reception.Where(m => m.MaintenanceNum.Contains(date)).OrderBy(m => m.MaintenanceNum).ToList();
                if (list.Count != 0)
                {
                    int num = Convert.ToInt32(list.Last().MaintenanceNum.Trim().Substring(9)) + 1;
                    if (num < 10)
                    {
                        MaintenanceNum = date + "000" + num;
                    }
                    else if (num > 9 && num < 100)
                    {
                        MaintenanceNum = date + "00" + num;
                    }
                    else if (num > 99 && num < 1000)
                    {
                        MaintenanceNum = date + "0" + num;
                    }
                }
                else {
                    MaintenanceNum = date + "0001";
                }
               

            }
            catch (Exception)
            {
                return Json(MaintenanceNum, JsonRequestBehavior.AllowGet);
            }
            return Json(MaintenanceNum, JsonRequestBehavior.AllowGet);
        }
        [HttpPost]
        public ActionResult Customer(List<PW_Predate> listPredate, List<SYS_PreRepairItemDetail> listPreRepairItem, List<ProductVo> listPreProduct,
          List<SYS_PreOtherCostDetail> listPreOtherCost)//转维修单列表
        {
            ArrayList list = new ArrayList();
            list.Add(listPreRepairItem);
            list.Add(listPreProduct);
            list.Add(listPreOtherCost);
            list.Add(listPredate[0]);
            Session["list"] = list;
            return View();
        }
        public ActionResult ArrlistToAppointment()//Session返回列表
        {
            var list= Session["list"];
            return Json(list, JsonRequestBehavior.AllowGet);
        }
        public ActionResult CleanArrlist()//清空列表
        {
            Session["list"] = null;
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult ListReceptione(List<PW_Reception> listReception, List<SYS_RecRepairItemDetail> listRecRepairItem, List<SYS_RecProductDetail> listRecProduct,
           List<SYS_RecOtherCostDetail> listRecOtherCost, List<SYS_InsuranceDetail> listArrInsuranceMoney, List<SYS_ThreePacksDetail> listThreePacksDetail)//保存主页面信息
        {
            var ReceptionID = 0;
            try
            {
                //预约表
                ReceptionID = listReception[0].ReceptionID;
                if (ReceptionID == 0)
                {
                    listReception[0].DocumentStateID = 1; //未结算
                    listReception[0].BalanceStateID = 3;//未付款
                    listReception[0].CollageState = "未领料";//领料状态
                    listReception[0].ToCompletion = false;//领料状态
                    myModels.PW_Reception.Add(listReception[0]);
                }
                else
                {
                    var list = myModels.PW_Reception.Where(m => m.ReceptionID == ReceptionID).Select(m => new
                    {
                        MaintenAmount= m.MaintenAmount,//维修总额
                        DocumentStateID= m.DocumentStateID,//单据状态
                        BalanceStateID = m.BalanceStateID,//
                        CollageState=m.CollageState,//领料状态
                        ToCompletion=m.ToCompletion,
                    }).Single();
                    listReception[0].MaintenAmount = list.MaintenAmount;
                    listReception[0].DocumentStateID = list.DocumentStateID;
                    listReception[0].BalanceStateID = list.BalanceStateID;
                    listReception[0].CollageState = list.CollageState;
                    listReception[0].ToCompletion = list.ToCompletion;
                    myModels.Entry(listReception[0]).State = System.Data.Entity.EntityState.Modified;
                }
                if (myModels.SaveChanges() > 0)
                {
                    ReceptionID = listReception[0].ReceptionID;
                    if (listRecRepairItem != null)
                    {  //维修明细表
                        List<int> oldID = new List<int>();//原来ID
                        List<int> newID = new List<int>();//新ID
                        List<int> listdelectID = new List<int>();//需要删除的ID集合
                        var list = myModels.SYS_RecRepairItemDetail.Where(m => m.ReceptionID == ReceptionID).Select(m => new { m.RecRepairItemDetailID }).ToList();
                        foreach (var item in list)
                        {
                            oldID.Add(item.RecRepairItemDetailID);
                        }
                        for (int i = 0; i < listRecRepairItem.Count; i++)
                        {
                            listRecRepairItem[i].ReceptionID = ReceptionID;
                            var RecRepairItemDetailID = listRecRepairItem[i].RecRepairItemDetailID;
                            if (RecRepairItemDetailID == 0)
                            {
                                myModels.SYS_RecRepairItemDetail.Add(listRecRepairItem[i]);//新增
                            }
                            else
                            {
                                newID.Add(listRecRepairItem[i].RecRepairItemDetailID);
                                var lists = myModels.SYS_RecRepairItemDetail.Where(m => m.RecRepairItemDetailID == RecRepairItemDetailID).Select(m => new
                                {
                                    CompletionDate = m.CompletionDate,//完工时间
                                    RepairManID = m.RepairManID,//修理工ID
                                    MaintenanceCrewID = m.MaintenanceCrewID,//修理组ID
                                    JobHours = m.JobHours,//工时
                                    JobAmount = m.JobAmount,//工时费
                                }).Single();
                                listRecRepairItem[i].CompletionDate = lists.CompletionDate;
                                listRecRepairItem[i].RepairManID = lists.RepairManID;
                                listRecRepairItem[i].MaintenanceCrewID = lists.MaintenanceCrewID;
                                listRecRepairItem[i].JobHours = lists.JobHours;
                                listRecRepairItem[i].JobAmount = lists.JobAmount;
                                myModels.Entry(listRecRepairItem[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_RecRepairItemDetail.Where(m => m.RecRepairItemDetailID == item).Single();//删除
                            myModels.SYS_RecRepairItemDetail.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_RecRepairItemDetail.Where(m => m.ReceptionID == ReceptionID).ToList();//删除全部
                        myModels.SYS_RecRepairItemDetail.RemoveRange(listdelect);
                    }
                    if (listRecProduct != null)
                    {
                        //配件明细表
                        List<int> oldID = new List<int>();//原来ID
                        List<int> newID = new List<int>();//新ID
                        List<int> listdelectID = new List<int>();//需要删除的ID集合
                        var list = myModels.SYS_RecProductDetail.Where(m => m.ReceptionID == ReceptionID).Select(m => new { m.RecProductDetailID }).ToList();
                        foreach (var item in list)
                        {
                            oldID.Add(item.RecProductDetailID);
                        }
                        for (int i = 0; i < listRecProduct.Count; i++)
                        {
                            listRecProduct[i].ReceptionID = ReceptionID;
                            if (listRecProduct[i].RecProductDetailID == 0)
                            {
                                myModels.SYS_RecProductDetail.Add(listRecProduct[i]);//新增
                            }
                            else
                            {
                                newID.Add(listRecProduct[i].RecProductDetailID);
                                myModels.Entry(listRecProduct[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_RecProductDetail.Where(m => m.RecProductDetailID == item).Single();//删除
                            myModels.SYS_RecProductDetail.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_RecProductDetail.Where(m => m.ReceptionID == ReceptionID).ToList();//删除全部
                        myModels.SYS_RecProductDetail.RemoveRange(listdelect);
                    }
                    if (listRecOtherCost != null)
                    {
                        //配件明细表
                        List<int> oldID = new List<int>();//原来ID
                        List<int> newID = new List<int>();//新ID
                        List<int> listdelectID = new List<int>();//需要删除的ID集合
                        var list = myModels.SYS_RecOtherCostDetail.Where(m => m.ReceptionID == ReceptionID).Select(m => new { m.RecOtherCostDetailID }).ToList();
                        foreach (var item in list)
                        {
                            oldID.Add(item.RecOtherCostDetailID);
                        }
                        for (int i = 0; i < listRecOtherCost.Count; i++)
                        {
                            listRecOtherCost[i].ReceptionID = ReceptionID;
                            if (listRecOtherCost[i].RecOtherCostDetailID == 0)
                            {
                                myModels.SYS_RecOtherCostDetail.Add(listRecOtherCost[i]);//新增
                            }
                            else
                            {
                                newID.Add(listRecOtherCost[i].RecOtherCostDetailID);
                                myModels.Entry(listRecOtherCost[i]).State = System.Data.Entity.EntityState.Modified;//修改
                            }
                        }
                        listdelectID = oldID.Except(newID).ToList();//从某集合中删除其与另一个集合中相同的项；其实这个说简单点就是某集合中独有的元素(差集)
                        foreach (var item in listdelectID)
                        {
                            var listdelect = myModels.SYS_RecOtherCostDetail.Where(m => m.RecOtherCostDetailID == item).Single();//删除
                            myModels.SYS_RecOtherCostDetail.Remove(listdelect);
                        }
                    }
                    else
                    {
                        var listdelect = myModels.SYS_RecOtherCostDetail.Where(m => m.ReceptionID == ReceptionID).ToList();//删除全部
                        myModels.SYS_RecOtherCostDetail.RemoveRange(listdelect);
                    }
                    if (listArrInsuranceMoney != null)
                    {
                        //保险理赔明细
                        listArrInsuranceMoney[0].ReceptionID = ReceptionID;
                        if (listArrInsuranceMoney[0].InsuranceDetailID == 0)
                        {
                            myModels.SYS_InsuranceDetail.Add(listArrInsuranceMoney[0]);
                        }
                        else {
                            myModels.Entry(listArrInsuranceMoney[0]).State = System.Data.Entity.EntityState.Modified;//修改
                        }
                    }
                    if (listThreePacksDetail != null)
                    {
                        //三包索赔明细
                        listThreePacksDetail[0].ReceptionID = ReceptionID;
                        if (listThreePacksDetail[0].ThreePacksDetailID == 0)
                        {
                            myModels.SYS_ThreePacksDetail.Add(listThreePacksDetail[0]);
                        }
                        else
                        {
                            myModels.Entry(listThreePacksDetail[0]).State = System.Data.Entity.EntityState.Modified;//修改
                        }
                    }
                    myModels.SaveChanges();
                }
                else
                {
                    return Json(false, JsonRequestBehavior.AllowGet);
                }
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(ReceptionID, JsonRequestBehavior.AllowGet);
        }
        public ActionResult DelectReception(int ReceptionID)//删除客户接待单据信息
        {
            try
            {
                var list = myModels.PW_Reception.Where(m => m.ReceptionID == ReceptionID).Single();
                var listRecProductDetail = myModels.SYS_RecProductDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                var listRecRepairItemDetail = myModels.SYS_RecRepairItemDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                var listRecOtherCostDetail = myModels.SYS_RecOtherCostDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                var listInsuranceDetail = myModels.SYS_InsuranceDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                var listThreePacksDetail = myModels.SYS_ThreePacksDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                myModels.PW_Reception.Remove(list);
                myModels.SYS_RecProductDetail.RemoveRange(listRecProductDetail);
                myModels.SYS_RecRepairItemDetail.RemoveRange(listRecRepairItemDetail);
                myModels.SYS_RecOtherCostDetail.RemoveRange(listRecOtherCostDetail);
                myModels.SYS_InsuranceDetail.RemoveRange(listInsuranceDetail);
                myModels.SYS_ThreePacksDetail.RemoveRange(listThreePacksDetail);
                myModels.SaveChanges();
            }
            catch (Exception)
            {
                return Json(false, JsonRequestBehavior.AllowGet);
            }
            return Json(true, JsonRequestBehavior.AllowGet);
        }
        public ActionResult SelectReception(BsgridPage bsgridPage, string ToAudit, string MaintenanceNum,string CarNum,int DocumentStateID,int BalanceStateID)//查询客户接待单据信息
        {
            var lingItem = (from tbReception in myModels.PW_Reception
                            join tbRepair in myModels.SYS_Repair on tbReception.RepairID equals tbRepair.RepairID
                            join tbDocumentState in myModels.SYS_DocumentState on tbReception.DocumentStateID equals tbDocumentState.DocumentStateID
                            join tbBalanceState in myModels.SYS_BalanceState on tbReception.BalanceStateID equals tbBalanceState.BalanceStateID
                            join tbCustomerSou in myModels.SYS_CustomerSou on tbReception.CustomerSouID equals tbCustomerSou.CustomerSouID
                            select new ReceptionVo
                            {
                                ReceptionID = tbReception.ReceptionID,//客户接待ID
                                RepairID = tbReception.RepairID,//修理类别ID
                                RepairName = tbRepair.RepairName.Trim(),//修理类别名称
                                CustomerSouID = tbReception.CustomerSouID,//客户来源ID
                                CustomerSou = tbCustomerSou.CustomerSou.Trim(),//客户来源名称
                                DocumentStateID=tbDocumentState.DocumentStateID,//单据状态ID
                                DocumentState = tbDocumentState.DocumentState.Trim(),//单据状态名称
                                BalanceStateID =tbBalanceState.BalanceStateID,//结算状态ID
                                BalanceState = tbBalanceState.BalanceState.Trim(),//结算状态名称
                                CarNum = tbReception.CarNum.Trim(),//车牌
                                CustomerNum=tbReception.CustomerNum.Trim(),//客户编号
                                VehicleType = tbReception.VehicleType.Trim(),//车型
                                Carder = tbReception.Carder.Trim(),//接车人名称
                                Mileage = tbReception.Mileage.Trim(),//进厂里程
                                MaintenanceNum = tbReception.MaintenanceNum.Trim(),//维修单号
                                Owner = tbReception.Owner.Trim(),//车主
                                Address = tbReception.Address.Trim(),//地址
                                OilQuantity = tbReception.OilQuantity.Trim(),//油量
                                OwnerTele = tbReception.OwnerTele,//车主电话
                                EngineNum = tbReception.EngineNum.Trim(),//发动机号码
                                Repairman = tbReception.Repairman.Trim(),//送修人
                                SelfCoding = tbReception.SelfCoding.Trim(),//自编号
                                FrameNum = tbReception.FrameNum,//车架号码
                                RepairmanTele = tbReception.RepairmanTele,//送修人电话
                                OpenDates = tbReception.OpenDate.ToString(),//开单日期
                                FactoryDates = tbReception.FactoryDate.ToString(),//进厂日期
                                BalanceDates = tbReception.BalanceDate.ToString(),//结算日期
                                openDates = tbReception.OpenDate.ToString(),//开单日期
                                factoryDates = tbReception.FactoryDate.ToString(),//进厂日期
                                balanceDates = tbReception.BalanceDate.ToString(),//结算日期
                                Amount = tbReception.Amount,//总金额
                                AmountPaid = tbReception.AmountPaid,//应收金额
                                Describe=tbReception.Describe.Trim(),//描述
                                ToAudit =tbReception.ToAudit,//审核否
                                CompletionDates = tbReception.CompletionDate.ToString() != null ? tbReception.CompletionDate.ToString() : "0000-00-00",//完工日期
                                ToSendWork =tbReception.ToSendWork,//派工状态
                                CollageState=tbReception.CollageState.Trim(),//领料状态
                                MaintenAmount =tbReception.MaintenAmount,//维修总费
                            }).ToList();
            #region 拼接条件
            if (!string.IsNullOrEmpty(MaintenanceNum))
            {
                lingItem = lingItem.Where(m => m.MaintenanceNum.Contains(MaintenanceNum)).ToList();
            }
            if (!string.IsNullOrEmpty(CarNum))
            {
                lingItem = lingItem.Where(m => m.CarNum==CarNum).ToList();
            }
            if (!string.IsNullOrEmpty(ToAudit))
            {
                if (ToAudit == "true")
                {
                    lingItem = lingItem.Where(m => m.ToAudit == true).ToList();

                }
                else
                {
                    lingItem = lingItem.Where(m => m.ToAudit == false).ToList();
                }
            }
            if (DocumentStateID>0)
            {
                lingItem = lingItem.Where(m => m.DocumentStateID == DocumentStateID).ToList();
            }
            if (BalanceStateID > 0)
            {
                lingItem = lingItem.Where(m => m.BalanceStateID == BalanceStateID).ToList();
            }
            #endregion
            int count = lingItem.Count();
            List<ReceptionVo> listReception = lingItem.OrderBy(m => m.ToAudit).Skip(bsgridPage.GetStartIndex()).Take(bsgridPage.pageSize).ToList();
            Bsgrid<ReceptionVo> bsgrid = new Bsgrid<ReceptionVo>()
            {
                success = true,
                totalRows = count,
                curPage = bsgridPage.curPage,
                data = listReception
            };
            return Json(bsgrid, JsonRequestBehavior.AllowGet);
        }

        private string completionDate;//生日
        public string CompletionDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    completionDate = dt.ToString("yyyy/MM/dd");
                }
                catch (Exception)
                {

                    completionDate = value;
                }
            }
            get
            {
                return completionDate;
            }
        }//生日
        public ActionResult SelectReceptionDetail(int ReceptionID)//查询客户接待明细信息
        {
            ArrayList lists = new ArrayList();
            try
            {
                //SYS_RecRepairItemDetail修理项目明细表
                var listRecRepairItemDetail = myModels.SYS_RecRepairItemDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                //SYS_RecProductDetail产品明细表
                var listRecProductDetail = myModels.SYS_RecProductDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                //SYS_RecOtherCostDetail其他费用明细表
                var listRecOtherCostDetail = myModels.SYS_RecOtherCostDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                //SYS_InsuranceDetail保险理赔明细表
                var listInsuranceDetail = myModels.SYS_InsuranceDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                //SYS_ThreePacksDetail三包索赔明细表
                var listThreePacksDetail = myModels.SYS_ThreePacksDetail.Where(m => m.ReceptionID == ReceptionID).ToList();
                lists.Add(listRecRepairItemDetail);
                lists.Add(listRecProductDetail);
                lists.Add(listRecOtherCostDetail);
                lists.Add(listInsuranceDetail);
                lists.Add(listThreePacksDetail);
            }
            catch (Exception)
            {
                throw;
            }
            return Json(lists, JsonRequestBehavior.AllowGet);
        }
    }
}