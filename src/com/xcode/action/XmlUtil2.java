/********************************************************************
 *  用于操作ＸＭＬ文件，包括查找、新增、删除、修改结点
********************************************************************/
package com.xcode.action;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * 类描述：对XML文件进行读写操作,均为静态函数 <p>
 * 主要public成员变量：<p>
 * 主要public方法：   <p>
 **/

public class XmlUtil2
{
	public static void main(String[] args) {
			init();
		 Element [] ress=getElementsByName(root,"mappers");
		 Element [] ress2=getElementsByName(ress[0],"mapper");
		 for (int i = 0; i < ress2.length; i++) {
			 System.err.println(getElementAttr(ress2[i],"resource"));
		}
		 
	}
	/*全局变量*/  
    static Logger logger=Logger.getLogger("XmlOper");
    public static String path="/Users/xcj/Code/whz/src/mybatis_config.xml";//xml文件路径
    public static Document doc=null;//xml文件对应的document
    public static Element root=null;//xml文件的根结点
	 public static void init()
	    {
	      
	        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	        try
	        {
	            DocumentBuilder builder=factory.newDocumentBuilder();
	            logger.debug("Construct document builder success.");
	            doc=builder.parse(new File(path));            
	            logger.debug("Build xml document success.");
	        }catch(ParserConfigurationException e)
	        {
	            logger.error("Construct document builder error:"+e);
	        }catch(SAXException e)
	        {
	            logger.error("Parse xml file error:"+e);
	        }catch(IOException e)
	        {
	            logger.error("Read xml file error:"+e);
	        }
	  
	        root=doc.getDocumentElement();
	    }
	      
  
/**
* 方法名称：getNodeList<p>
* 方法功能：获取父结点parent的所有子结点<p>
* 参数说明：@param parent
* 参数说明：@return <p>
**/
public static NodeList getNodeList(Element parent)
{
    return parent.getChildNodes();
}
      
    /**
    * 方法名称：getElementsByName<p>
    * 方法功能：在父结点中查询指定名称的结点集            <p>
    * 参数说明：@param parent
    * 参数说明：@param name
    * 参数说明：@return <p>
    * 返回：Element[] <p>
    **/
    public static Element [] getElementsByName(Element parent,String name)
    {
        ArrayList resList=new ArrayList();
        NodeList nl=getNodeList(parent);
    for(int i=0;i<nl.getLength();i++)
    {
        Node nd=nl.item(i);
        if(nd.getNodeName().equals(name))
        {
            resList.add(nd);
        }
    }
    Element [] res=new Element [resList.size()];
    for(int i=0;i<resList.size();i++)
    {
        res[i]=(Element)resList.get(i);
    }        
    logger.debug(parent.getNodeName()+"'s children of "+name+
            "'s num:"+res.length);
    return res;
}
  
/**
* 方法名称：getElementName<p>
* 方法功能：获取指定Element的名称            <p>
* 参数说明：@param element
    * 参数说明：@return <p>
    * 返回：String <p>
**/
public static String getElementName(Element element)
{
return element.getNodeName();
}

/**
* 方法名称：getElementValue<p>
* 方法功能：获取指定Element的值<p>
* 参数说明：@param element
* 参数说明：@return <p>
* 返回：String <p>
**/
public static String getElementValue(Element element)
{
    NodeList nl=element.getChildNodes();
    for(int i=0;i<nl.getLength();i++)
        {
            if(nl.item(i).getNodeType()==Node.TEXT_NODE)//是一个Text Node
            {            
                logger.debug(element.getNodeName()+" has a Text Node.");
                return element.getFirstChild().getNodeValue();
            }
        }   
        logger.error(element.getNodeName()+" hasn't a Text Node.");
        return null;
    }
      
    /**
    * 方法名称：getElementAttr<p>
    * 方法功能：获取指定Element的属性attr的值            <p>
    * 参数说明：@param element
    * 参数说明：@param attr
    * 参数说明：@return <p>
    * 返回：String <p>
    **/
    public static String getElementAttr(Element element,String attr)
    {
        return element.getAttribute(attr);
    }
      
    /**
    * 方法名称：setElementValue<p>
    * 方法功能：设置指定Element的值            <p>
    * 参数说明：@param element
    * 参数说明：@param val <p>
    * 返回：void <p>
    **/
    public static void setElementValue(Element element,String val)
    {
        Node node=element.getOwnerDocument().createTextNode(val);
        NodeList nl=element.getChildNodes();
        for(int i=0;i<nl.getLength();i++)
        {
            Node nd=nl.item(i);
            if(nd.getNodeType()==Node.TEXT_NODE)//是一个Text Node
            {            
                  nd.setNodeValue(val);
                  logger.debug("modify "+element.getNodeName()+"'s node value succe.");
                  return;
            }
        }   
        logger.debug("new "+element.getNodeName()+"'s node value succe.");
        element.appendChild(node);        
    }
      
    /**
    * 方法名称：setElementAttr<p>
    * 方法功能：设置结点Element的属性<p>
    * 参数说明：@param element
    * 参数说明：@param attr
    * 参数说明：@param attrVal <p>
    * 返回：void <p>
    **/
    public static void setElementAttr(Element element,
            String attr,String attrVal)
    {
        element.setAttribute(attr,attrVal);
    }
      
      
    /**
    * 方法名称：addElement<p>
    * 方法功能：在parent下增加结点child<p>
    * 参数说明：@param parent
    * 参数说明：@param child <p>
    * 返回：void <p>
    **/
    public static void addElement(Element parent,Element child)
    {
        parent.appendChild(child);
    }
      
    /**
    * 方法名称：addElement<p>
    * 方法功能：在parent下增加字符串tagName生成的结点<p>
    * 参数说明：@param parent
    * 参数说明：@param tagName <p>
    * 返回：void <p>
    **/
    public static void addElement(Element parent,String tagName)
    {        
        Document doc=parent.getOwnerDocument();
        Element child=doc.createElement(tagName);
        parent.appendChild(child);
    }
      
    /**
    * 方法名称：addElement<p>
    * 方法功能：在parent下增加tagName的Text结点，且值为text<p>
    * 参数说明：@param parent
    * 参数说明：@param tagName
    * 参数说明：@param text <p>
    * 返回：void <p>
    **/
    public static void addElement(Element parent,String tagName,String text)
    {
        Document doc=parent.getOwnerDocument();
        Element child=doc.createElement(tagName);
        setElementValue(child,text);
        parent.appendChild(child);
    }
      
    /**
    * 方法名称：removeElement<p>
    * 方法功能：将父结点parent下的名称为tagName的结点移除<p>
    * 参数说明：@param parent
    * 参数说明：@param tagName <p>
    * 返回：void <p>
    **/
    public static void removeElement(Element parent,String tagName)
    {
        logger.debug("remove "+parent.getNodeName()+"'s children by tagName "+tagName+" begin...");
        NodeList nl=parent.getChildNodes();
        for(int i=0;i<nl.getLength();i++)
        {
            Node nd=nl.item(i);
            if(nd.getNodeName().equals(tagName))
            {
                parent.removeChild(nd);
                logger.debug("remove child '"+nd+"' success.");
            }
        }
        logger.debug("remove "+parent.getNodeName()+"'s children by tagName "+tagName+" end.");
    }
      
      
    
}