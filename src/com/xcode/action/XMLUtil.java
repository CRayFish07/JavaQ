package com.xcode.action;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLUtil {
public static void addMybat(String path,String resource) {
	//String path="/Users/xcj/Code/whz/src/mybatis_config.xml";//xml文件路径
	File file=new File(path);
	XStream xm=new XStream(new DomDriver());
	xm.autodetectAnnotations(true);
	xm.alias("configuration",Configuration.class);
	xm.alias("mappers",Mappers.class);
	xm.alias("mapper",Mapper.class);
	Configuration configuration=(Configuration)xm.fromXML(file);
	List l=configuration.getMappers().getMapperList();
	System.err.println(configuration.getMappers().getMapperList().size());
	Mapper newp=new Mapper();
	newp.setResource(resource);
	l.add(newp);
	System.err.println(configuration.getMappers().getMapperList().size());
	FileWriter writer;
	try {
		writer = new FileWriter(path);
		xm.toXML(configuration,writer);
	} catch (IOException e) {
		e.printStackTrace();
	} 
}

public static void main(String[] args) {
	
}
public static void addMapper(String path,String mapperbean,String name) {
	File file = new File(path);
	StringBuffer sb=new StringBuffer();
	StringBuffer sb1=new StringBuffer();
    BufferedReader reader = null;
    FileOutputStream out;
    BufferedOutputStream bos = null;
    try {
        System.out.println("以行为单位读取文件内容，一次读一整行：");
        reader = new BufferedReader(new FileReader(file));
        String tempString = null;
      
        int f=1;
        int line = 1;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            line++;
          sb.append(tempString+"\n");
          if(tempString.contains(name)){
        	  f=0;
        	  break;
          }
        }
        reader.close();
        if(1==f){
        	sb1.append(sb.toString().replace("</mappers>",mapperbean));
            System.out.println(sb1.toString());
            out = new FileOutputStream(path);
             bos=new BufferedOutputStream(out);
    		bos.write(sb1.toString().getBytes("UTF-8"));
    		bos.close();
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
            }
        }
        if (bos != null) {
        	try {
        		bos.close();
        	} catch (IOException e1) {
        	}
        }
    }
  

}
public static void addview(String pathtemp,String formstr,String path) {
	File file = new File(pathtemp);
	StringBuffer sb=new StringBuffer();
	StringBuffer sb1=new StringBuffer();
    BufferedReader reader = null;
    FileOutputStream out;
    BufferedOutputStream bos = null;
    try {
        System.out.println("以行为单位读取文件内容，一次读一整行：");
        reader = new BufferedReader(new FileReader(file));
        String tempString = null;
      
        int f=1;
        int line = 1;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            line++;
          
          if(tempString.contains("@this@")){
        	  sb.append(formstr);
          }else{
        	  sb.append(tempString+"\n");
          }
        }
        reader.close();
            System.out.println(sb.toString());
            out = new FileOutputStream(path);
             bos=new BufferedOutputStream(out);
    		bos.write(sb.toString().getBytes("UTF-8"));
    		bos.close();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
            }
        }
        if (bos != null) {
        	try {
        		bos.close();
        	} catch (IOException e1) {
        	}
        }
    }
  

}
}
