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
            // 创建document对象
            Document document = documentBuilder.parse(inputStream);

            // 获取所有bean标签节点
            NodeList nodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                // 根据索引返回对应位置的node
                Node node = nodeList.item(i);
                // 如果node类型是element节点
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // 将node转为元素节点
                    Element elementNode = (Element) node;
                    // 获取bean标签中id属性值
                    String beanId = elementNode.getAttribute("id");
                    // 获取bean标签中class属性值(也就是id对应的全类名)
                    String beanClassName = elementNode.getAttribute("class");
                    // 创建该类的Class对象
                    Class<?> controllerBeanClass = Class.forName(beanClassName);
                    // 根据Class对象创建该类的对象
                    Object controllerBeanObj = controllerBeanClass.newInstance();
                    // 将该对象放到map集合中
                    beanMap.put(beanId, controllerBeanObj);
                }
            }
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    String beanId = elementNode.getAttribute("id");
                    // 获取当前元素节点的所有子节点
                    NodeList childNodes = elementNode.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        // 获取对应索引位置的子节点
                        Node childNode = childNodes.item(j);
                        // 子节点的类型是元素节点 并且 子节点的节点名为property
                        if (childNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(childNode.getNodeName())){
                            // 将子节点转换元素节点
                            Element propertyElement = (Element) childNode;
                            // 获取子元素节点中的name属性值
                            String propertyName = propertyElement.getAttribute("name");
                            // 获取子元素节点中的ref属性值(String类型)
                            String propertyRef = propertyElement.getAttribute("ref");
                            // 根据ref属性值获取对应的Object对象
                            // 前面我们已经将所有bean标签对应的对象,放在beanMap中
                            // 此处当然可以获取对象中属性值
                            Object refObj = beanMap.get(propertyRef);
                            // 获取相应的bean对象
                            Object beanObj = beanMap.get(beanId);
                            // 获取bean对象的Class对象
                            Class<?> beanClazz = beanObj.getClass();
                            // 获取属性名对应的属性对象(此处为property标签中name属性对应的对象)
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            // 强制访问
                            propertyField.setAccessible(true);
                            // set(obj,value) obj是需要修改字段的对象 value是需要修改字段对象的字段的修改后的新值
                            // 这里的beanObj表明需要修改的是该bean标签下的property标签中name属性
                            // refObj是name属性修改后的值
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
