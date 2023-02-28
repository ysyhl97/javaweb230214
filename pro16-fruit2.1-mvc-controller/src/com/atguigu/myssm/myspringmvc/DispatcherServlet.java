package com.atguigu.myssm.myspringmvc;

import com.alibaba.druid.sql.visitor.functions.If;
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
    private Map<String, Object> beanMap = new HashMap<>();

    public DispatcherServlet() {

    }


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            // 创建documentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // 创建document对象
            Document document = documentBuilder.parse(inputStream);

            NodeList bean = document.getElementsByTagName("bean");
            for (int i = 0; i < bean.getLength(); i++) {
                Node nodeLit = bean.item(i);
                Element element = (Element) nodeLit;
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");
                Class<?> aClass = Class.forName(className);
                Object newInstance = aClass.newInstance();
                beanMap.put(id, newInstance);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");


        String servletPath = request.getServletPath();
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(1, lastIndexOf);

        Object controllerBeanObject = beanMap.get(servletPath);

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

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
                try {
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObject, parameterValues);
                    String returnStr = (String) returnObj;

                    if (returnStr.startsWith("redirect:")) {
                        String substring = returnStr.substring("redirect:".length());
                        response.sendRedirect(substring);
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

