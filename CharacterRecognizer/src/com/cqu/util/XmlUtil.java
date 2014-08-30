package com.cqu.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlUtil {
	
	public static Document openXmlDocument(String path)
	{
		SAXBuilder builder=new SAXBuilder();
		try {
			Document doc=builder.build(new File(path));
			return doc;
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveXML(Document doc, String path)
	{
		Format format=Format.getRawFormat();
		format.setEncoding("UTF-8");
		XMLOutputter out=new XMLOutputter(format);
		try {
			out.output(doc, new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String readSetting(String xmlPath, String settingName)
	{
		Document doc=XmlUtil.openXmlDocument(xmlPath);
		Element root=doc.getRootElement();
		
		Element settingElement=root.getChild(settingName);
		return settingElement.getText();
	}
	
	public static void writeSetting(String xmlPath, String settingName, String settingValue)
	{
		Document doc=XmlUtil.openXmlDocument(xmlPath);
		Element root=doc.getRootElement();
		
		Element settingElement=root.getChild(settingName);
		settingElement.setText(settingValue);
		
		saveXML(doc, xmlPath);
	}
}
