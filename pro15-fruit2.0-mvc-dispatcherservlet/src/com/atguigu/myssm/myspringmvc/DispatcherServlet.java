package com.atguigu.myssm.myspringmvc;

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
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
        try {
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
                Method method = aClass.getDeclaredMethod("setServletContext", ServletContext.class);
                method.invoke(newInstance,this.getServletContext());
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
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        }

     @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        String servletPath = request.getServletPath();
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(1, lastIndexOf);

         Object beanObject = beanMap.get(servletPath);


        Method[] methods = beanObject.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.equals(operate)) {
                method.setAccessible(true);
                try {
                    method.invoke(this, request, response);
                    return;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

