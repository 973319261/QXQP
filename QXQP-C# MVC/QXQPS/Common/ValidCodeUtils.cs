using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Web;

namespace QXQPS.Common
{
    public class ValidCodeUtils
    {
        /// <summary>
        /// 获得随机字符串
        /// </summary>
        /// <param name="intLength">随机数的长度</param>
        /// <returns>随机数字符串</returns>
        public static string GetRandomCode(int intLength)
        {
            /*产生数字和密码混合的随机数*/
            string strReturn = string.Empty;
            Random random = new Random();//随机数
            for (int i = 0; i < intLength; i++)
            {
                char cRerult;
                int intRandom = random.Next();//产生一个非负随机整数
                /*根据当前随机数来确定字符串*/
                //intRandom % 3 获取的是intRandom/3 得到的余数
                if (intRandom % 3 == 0)
                {
                    //产生数字
                    //位数来产生数字
                    cRerult = (char)(0x30 + (intRandom % 10));
                }
                else if (intRandom % 3 == 1)
                {
                    //位数产生大写字母:大写字符 65-97 A 65
                    //68 D  25 Z
                    cRerult = (char)(0x41 + (intRandom % 0x1a));
                }
                else
                {
                    //余数为2
                    //产生小写字母 98-116
                    cRerult = (char)(0x61 + (intRandom % 0x1a));
                }
                strReturn += cRerult.ToString();
            }
            return strReturn;
        }

        /// <summary>
        /// 根据字符串创建验证码
        /// </summary>
        /// <param name="strRandom">字符串</param>
        /// <returns>图片</returns>
        public static byte[] CreateImage(string strRandom)
        {
            //新增图片
            Bitmap newBitmap = new Bitmap(strRandom.Length * 20, 30);

            Graphics g = Graphics.FromImage(newBitmap);
            g.Clear(Color.White);
            //在图片上绘制文字
            SolidBrush solidBrush = new SolidBrush(Color.Red);
            g.DrawString(strRandom, new Font("Aril", 17), solidBrush, 12, 1);
            //在图片上绘制干扰线
            Random random = new Random();
            for (int i = 0; i < 10; i++)
            {
                //产生一条线，并绘制到画布。 起始点（x,y）  总结点
                int x1 = random.Next(newBitmap.Width);
                int y1 = random.Next(newBitmap.Height);
                int x2 = random.Next(newBitmap.Width);
                int y2 = random.Next(newBitmap.Height);
                g.DrawLine(new Pen(Color.DarkGray), x1, y1, x2, y2);
            }
            //绘制图片的前景干扰点
            for (int i = 0; i < 100; i++)
            {
                int x = random.Next(newBitmap.Width);
                int y = random.Next(newBitmap.Height);
                newBitmap.SetPixel(x, y, Color.FromArgb(random.Next()));
            }
            //在最外边绘制边框
            g.DrawRectangle(new Pen(Color.Blue), 0, 0, newBitmap.Width, newBitmap.Height);
            g.DrawRectangle(new Pen(Color.Blue), -1, -1, newBitmap.Width, newBitmap.Height);
            //将图转保存到内存流中
            MemoryStream ms = new MemoryStream();
            newBitmap.Save(ms, ImageFormat.Jpeg);
            return ms.ToArray();//将流内容写入byte数组返回
        }
    }
}