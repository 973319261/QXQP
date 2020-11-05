package com.koi.qxqp.util;

public class ServiceUrls {

    public static final String SP_PHONE="phone";
    public static final String SP_PASSWORD="password";


    //private static String serviceUrl="http://192.168.191.2:8080/QXQP-SSM/";
    //Android虚拟机 AVD访问电脑的地址
    private static String serviceUrl="http://10.0.2.2:8080/QXQP-SSM/";
    private static String urlPostfix=".action";



    /**
     * 获取 AppMainPageController 方法的路径
     *
     * @param method 方法路径名称
     * @return url
     */
    public static String getClientMethodUrl(String method){
        return serviceUrl+"clientController/"+method+urlPostfix;
    }
    /**
     * 获取 commonController 方法的路径
     *
     * @param method 方法路径名称
     * @return url
     */
    public static String getCommonMethodUrl(String method){
        return serviceUrl+"commonController/"+method+urlPostfix;
    }
}
