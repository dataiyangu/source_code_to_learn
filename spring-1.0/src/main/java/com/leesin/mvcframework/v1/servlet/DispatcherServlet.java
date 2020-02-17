package com.leesin.mvcframework.v1.servlet;

import com.leesin.mvcframework.annotation.Autowired;
import com.leesin.mvcframework.annotation.Controller;
import com.leesin.mvcframework.annotation.RequestMapping;
import com.leesin.mvcframework.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DispatcherServlet extends HttpServlet {
    private Map<String, Object> mapping = new HashMap<String, Object>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * @param req
     * @param resp
     * @description: 核心方法
     * @name: doDispatch
     * @return: void
     * @date: 2020/2/14 22:27
     * @auther: Administrator
     **/
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        /*
        request.getRequestURL() 返回全路径
        request.getRequestURI() 返回除去host（域名或者ip）部分的路径
        request.getContextPath() 返回工程名部分，如果工程映射为/，此处返回则为空
        request.getServletPath() 返回除去host和工程名部分的路径
        */
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        //去掉多余的//
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if (!this.mapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found!!");
            return;
        }
        Method method = (Method) this.mapping.get(url);
        Map<String, String[]> params = req.getParameterMap();
        /*java.lang.reflect.Method.getDeclaringClass()方法返回表示声明由此Method对象表示的方法的类的Class对象。
        //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/javareflect/javareflect_method_getdeclaringclass.html
        */
        /*
        * 这里就是执行controller下面的方法
        * */
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()), new Object[]{req, resp, params.get("name")[0]});
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream is = null;
        try {
            Properties configContext = new Properties();
            //指的就是web.xml文件
            is = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
            configContext.load(is);
            String scanPackage = configContext.getProperty("scanPackage");
            doScanner(scanPackage);
            for (String className : mapping.keySet()) {
                if (!className.contains(".")) {
                    continue;
                }
                Class<?> clazz = Class.forName(className);
                /*A.isAnnotationPresent(B.class)；意思就是：注释B是否在此A上。如果在则返回true；不在则返回false。*/
                if (clazz.isAnnotationPresent(Controller.class)) {
                    //如果是的话就把当前的controller的实例缓存为value
                    mapping.put(className, clazz.newInstance());
                    String baseUrl = "";
                    if (clazz.isAnnotationPresent(RequestMapping.class)) {
                        /*getAnnotation得到类的注解*/
                        RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                        /*获取requestMapping注解的值*/
                        baseUrl = requestMapping.value();
                    }
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        if (!method.isAnnotationPresent(RequestMapping.class)) {
                            continue;
                        }
                        RequestMapping requestMapping =
                                method.getAnnotation(RequestMapping.class);
                        String url = (baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                        mapping.put(url, method);
                        System.out.println("Mapped " + url + "," + method);
                    }
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);
                    String beanName = service.value();
                    if ("".equals(beanName)) {
                        beanName = clazz.getName();
                    }
                    Object instance = clazz.newInstance();
                    mapping.put(beanName, instance);
                    /*获得所有的接口*/
                    for (Class<?> i : clazz.getInterfaces()) {
                        mapping.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }
            for (Object object : mapping.values()) {
                if (object == null) {
                    continue;
                }
                Class clazz = object.getClass();
                if (clazz.isAnnotationPresent(Controller.class)) {
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if (!field.isAnnotationPresent(Autowired.class)) {
                            continue;
                        }
                        Autowired autowired = field.getAnnotation(Autowired.class);
                        String beanName = autowired.value();
                        if ("".equals(beanName)) {
                            beanName = field.getType().getName();
                        }
                        field.setAccessible(true);
                        try {
                            //从缓存里面拿类的实例直接赋值进去。
                            field.set(mapping.get(clazz.getName()), mapping.get(beanName));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.print("GP MVC Framework is init");
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" +
                scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String clazzName = (scanPackage + "." + file.getName().replace(".class", ""));
                //缓存
                mapping.put(clazzName, null);
            }
        }
    }
    
    /**
     * @description: Test
     * @name: main
     * @param args
     * @return: void
     * @date: 2020/2/15 22:41
     * @auther: Administrator
    **/

    public static void main(String[] args) {
    }
}
