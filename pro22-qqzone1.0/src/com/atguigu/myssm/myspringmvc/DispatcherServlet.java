package com.atguigu.myssm.myspringmvc;

import com.atguigu.myssm.IOC.BeanFactory;
import com.atguigu.qqzone.service.UserBasicService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DispatcherServlet
 * Package: com.atguigu.myssm.myspringmvc
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 14:10
 * @Version 1.0
 */

public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    @Override
    public void init() throws ServletException {
        // 为了初始化thymeleaf的引擎对象
        super.init();
        //以前我们是在这里主动创建IOC容器
        // 为了优化dispatcherServlet的首次接收请求时的速度(servlet只有在接收到请求时,才会开始执行init())
        // 改为从application域中获取
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if (beanFactoryObj != null) {
            beanFactory = ((BeanFactory) beanFactoryObj);
        }else {
            throw new RuntimeException("获取IOC容器失败");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //http://localhost:8080/pro21/page.do?operate=login&page=login


        String servletPath = request.getServletPath();
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(1, lastIndexOf);

        // 从beanMap集合中获取servlet初始化时加载的bean
        // 现在该从beanFactory中调用getBean(),getBean()
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if (operate == null) {
            operate = "index";
        }

        //获取controller类中的所有方法
        Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
        // 遍历方法
        for (Method method : methods) {
            // 如果operate等于method名
            if (operate.equals(method.getName())) {
                // 获取method的所有参数
                Parameter[] parameters = method.getParameters();
                // 创建数组用来存放参数
                Object[] parametersValue = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    // 获取方法参数的参数名
                    String parameterName = parameters[i].getName();
                    // 如果是这些参数名就直接赋值
                    if ("request".equals(parameterName)) {
                        parametersValue[i] = request;
                    } else if ("response".equals(parameterName)) {
                        parametersValue[i] = response;
                    } else if ("session".equals(parameterName)) {
                        parametersValue[i] = request.getSession();
                    }

                    // 如果不是就从请求中获取请求参数
                    String parameterValue = request.getParameter(parameterName);
                    // 获取method参数类型名称
                    String typeName = parameters[i].getType().getName();

                    if (parameterValue != null) {
                        if ("java.lang.Integer".equals(typeName)) {
                        }
                    }

                    if (parameterValue != null) {
                        parametersValue[i] = parameterValue;
                    }

                }

                try {
                    // 通过反射调用方法
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parametersValue);

                    String returnStr = (String) returnObj;
                    if (returnStr.startsWith("redirect:")) {
                        response.sendRedirect(returnStr.substring("redirect".length()));
                    } else {
                        super.processTemplate(returnStr, request, response);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }


            }

        }
    }
}

