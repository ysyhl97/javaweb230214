package com.atguigu.myssm.IOC.impl;

import com.atguigu.myssm.IOC.BeanFactory;
import com.atguigu.myssm.util.StringUtil;
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
 * Package: com.atguigu.myssm.IOC.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/5 15:26
 * @Version 1.0
 */

public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();
    private String path;

    public ClassPathXmlApplicationContext() {
        this("applicationContent.xml");
    }

    public ClassPathXmlApplicationContext(String path) {
        if (StringUtil.isEmpty(path)) {
            new RuntimeException("IOC配置文件没有指定");
        }

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            // 创建DocumentBuilderFactory实例
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            // 创建DocumentBuilder实例
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // 创建Document实例
            Document document = documentBuilder.parse(inputStream);
            // 获取所有的bean节点
            NodeList nodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                // 获取nodeList中的每个node节点
                Node node = nodeList.item(i);
                // 判断是否是element节点
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // 转换为element节点
                    Element elementNode = (Element) node;
                    String id = elementNode.getAttribute("id");
                    String aClass = elementNode.getAttribute("class");
                    Class<?> clazz = Class.forName(aClass);
                    // 创建bean实例
                    Object objectBean = clazz.newInstance();
                    // 将bean实例保存到map容器中
                    beanMap.put(id, objectBean);
                    // 注意此时bean与bean之间的依赖关系还没组装
                }
            }

            // 组装bean与bean之间的依赖关系
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    String beanId = elementNode.getAttribute("id");
                    // 获得所有bean节点的子节点
                    NodeList childNodes = elementNode.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        // 判断子节点时element并且节点名称为property
                        if (childNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(childNode.getNodeName())) {
                            // 将子节点转为element节点
                            Element childElementNode = (Element) childNode;

                            String propertyName = childElementNode.getAttribute("name");
                            String propertyRef = childElementNode.getAttribute("ref");
                            // 在beanMap中获取property标签下ref对象
                            Object refObj = beanMap.get(propertyRef);
                            // 将refObj设置到当前bean所对应的property属性上去
                            // 根据bean标签的id获取对应的bean对象
                            Object beanObj = beanMap.get(beanId);
                            // 获取beanObj的Class对象
                            Class<?> beanClazz = beanObj.getClass();
                            // 根据property的name属性的值,获取bean对象中的property属性
                            Field propertyField = beanClazz.getDeclaredField(propertyName);

                            propertyField.setAccessible(true);
                            propertyField.set(beanObj, refObj);
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
        // 根据id返回beanMap中存放的对象
        return beanMap.get(id);
    }
}
