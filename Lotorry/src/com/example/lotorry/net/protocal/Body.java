package com.example.lotorry.net.protocal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

import com.example.lotorry.ConstantValue;
import com.example.lotorry.utils.DES;


import android.util.Xml;

/**
 * ��Ϣ��ڵ��װ
 * @author Administrator
 *
 */
public class Body {
	private List<Element> elements = new ArrayList<Element>();
	
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * ���л�����
	 * @param serializer
	 */
	public void serializerBody(XmlSerializer serializer){
		try {
			serializer.startTag(null, "body");
			serializer.startTag(null, "elements");
			for (Element element : elements) {
				element.serializerElement(serializer);
			}
			serializer.endTag(null, "elements");
			serializer.endTag(null, "body");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getWholeBody(){
		XmlSerializer tempSerializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			tempSerializer.setOutput(writer);
			serializerBody(tempSerializer);
			// ����ˢ������
			tempSerializer.flush();
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getBodyInsideDESInfo(){
		// Ҫ���ܵ�����
		String wholeBody = getWholeBody();
		String desInfo = StringUtils.substringBetween(wholeBody, "<body>", "</body>");
		DES des = new DES();
		String authcode = des.authcode(desInfo, "DECODE", ConstantValue.DES_PASSWORD);
		return authcode;
	}
}
