using QXQPS.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QXQPS.Vo
{
    public class ReceptionVo: PW_Reception//客户接待表字段
    {
        private string openDate;//开单日期
        private string OPenDate;//开单日期
        public string OpenDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    OPenDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    OPenDate = value;
                }
            }
            get
            {
                return OPenDate;
            }
        }//开单日期
        public string openDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    openDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    openDate = value;
                }
            }
            get
            {
                return openDate;
            }
        }//开单日期

        private string factoryDate;//进厂日期
        private string FActoryDate;//进厂日期
        public string FactoryDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    FActoryDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    FActoryDate = value;
                }
            }
            get
            {
                return FActoryDate;
            }
        }//进厂日期
        public string factoryDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    factoryDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    factoryDate = value;
                }
            }
            get
            {
                return factoryDate;
            }
        }//进厂日期
        private string BAlanceDate;//结算日期

        private string balanceDate;//结算日期
        public string BalanceDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    BAlanceDate = dt.ToString("yyyy-MM-dd HH:mm:ss");
                }
                catch (Exception)
                {

                    BAlanceDate = value;
                }
            }
            get
            {
                return BAlanceDate;
            }
        }//结算日期
        public string balanceDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    balanceDate = dt.ToString("yyyy-MM-dd");
                }
                catch (Exception)
                {

                    balanceDate = value;
                }
            }
            get
            {
                return balanceDate;
            }
        }//结算日期
        public Nullable<System.DateTime> BalanceDateTo{ get; set; }//结算日期(判断)

        private string completionDate;//完工日期
        public string CompletionDates
        {
            set
            {
                try
                {
                    DateTime dt = Convert.ToDateTime(value);
                    completionDate = dt.ToString("yyyy-MM-dd");
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
        }//完工日期
        public string RepairName { get; set; }//修理类别名称
        public string CustomerSou { get; set; }//客户来源名称
        public string DocumentState { get; set; }//单据状态名称
        public string BalanceState { get; set; }//结算状态名称
        public int CollageID { get; set; }//领料ID

        //三包保险
        public decimal ClaimMoney { get; set; }//索赔金额
        public int ClaimComID { get; set; }//索赔公司ID
        public string ClaimStaff { get; set; }//索赔员工
        public string ToTicket { get; set; }//是否开票
        public int ThreePacksID { get; set; }//三包ID
        public int ThreePacksDetailID { get; set; }//三包公索赔ID
        public string InsuranceNum { get; set; }//索赔单号
        public string Operator { get; set; }//操作人
       ///索赔
       /// 
       public int InsuranceID { get; set; }//索赔ID
        public decimal InsuranceMoney { get; set; }//索赔金额
        public int InsuranceDetailID { get; set; }//索赔公司ID
        public int InsuranceComID { get; set; }//索赔员工
        public string ReportNum { get; set; }//报案编号
        public string PolicyNum { get; set; }//保单号
        public decimal PolicyMoney { get; set; }//保单金额
    }
    }
    