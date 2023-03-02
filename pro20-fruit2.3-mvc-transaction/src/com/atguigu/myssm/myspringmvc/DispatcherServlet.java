package com.atguigu.myssm.myspringmvc;

import com.alibaba.druid.sql.visitor.functions.If;
import com.atguigu.myssm.io.BeanFactory;
import com.atguigu.myssm.io.impl.ClassPathXmlApplicationContext;
import com.atguigu.myssm.util.StringUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * @Create 2023/2/16 10:43
 * @Version 1.0
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet() {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(1, lastIndexOf);

        Object controllerBeanObject = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }
        try {
            //获取controller中的所有方法
            Method[] methods = controllerBeanObject.getClass().getDeclaredMethods();
            //遍历每一个方法
            for (Method method : methods) {
                //根据operate来调用对应的方法
                if (operate.equals(method.getName())) {

                    //获取方法的所有参数
                    Parameter[] parameters = method.getParameters();
                    //创建数组,用来存放方法参数值
                    Object[] parameterValues = new Object[parameters.length];
                    //遍历方法中的每个参数
                    for (int i = 0; i < parameters.length; i++) {
                        //依次获取方法中的参数
                        Parameter parameter = parameters[i];
                        //获取参数名
                        String parameterName = parameter.getName();
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            //通过方法的参数名,来获取请求中的对应参数值
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;
                            if (parameterObj != null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }

                            parameterValues[i] = parameterObj;
                        }

                    }
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObject, parameterValues);
                    String returnStr = (String) returnObj;

                    if (returnStr.startsWith("redirect:")) {
                        String substring = returnStr.substring("redirect:".length());
                        response.sendRedirect(substring);
                    } else {
                        super.processTemplate(returnStr, request, response);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("dispatcherServlet出错了....");
        }


    }
}


