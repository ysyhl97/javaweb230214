package com.atguigu.myssm.io.impl;

import com.atguigu.myssm.io.BeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ClassPathXmlApplicationContext
 * Package: com.atguigu.myssm.io.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/28 14:25
 * @Version 1.0
 */

public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext() {
        System.out.println("this is ClassPathXmlApplicationContext constructor");
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            // 创建documentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            NodeList nodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    String beanId = elementNode.getAttribute("id");
                    String beanClassName = elementNode.getAttribute("class");
                    Class<?> controllerBeanClass = Class.forName(beanClassName);
                    Object controllerBeanObj = controllerBeanClass.newInstance();
                    beanMap.put(beanId, controllerBeanObj);
                }
            }
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    String beanId = elementNode.getAttribute("id");
                    NodeList childNodes = elementNode.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(childNode.getNodeName())){
                            Element propertyElement = (Element) childNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            Object refObj = beanMap.get(propertyRef);
                            Object beanObj = beanMap.get(beanId);
                            Class<?> beanClazz = beanObj.getClass();
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }
                    }
                }
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
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
