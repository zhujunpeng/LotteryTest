package com.example.lotorry.net.protocal;

import java.io.IOException;
import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class Message {
	
	private Header header = new Header();
	private Body body = new Body();
	
	/**
	 * ���л�����Э��
	 * @param serializer
	 */
	public void serializerMessage(XmlSerializer serializer){
		try {
//			serializer.s
			serializer.startTag(null, "message");
			serializer.attribute(null, "version", "1.0");
			header.serializerHeader(serializer, body.getWholeBody());
			
//			body.serializerBody(serializer);
			serializer.startTag(null, "body");
			serializer.text(body.getBodyInsideDESInfo());
			serializer.endTag(null, "body");
			
			serializer.endTag(null, "message");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String createXml(Element element){
		if (element == null) {
			throw new IllegalArgumentException("element is null");
		}
		// �����ʾ��Ҫ���ã�����������Ҫ����
		header.getTransactiontype().setTagValue(element.getTransactionType());
		body.getElements().add(element);
		// ���л�
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("utf-8", true);
			this.serializerMessage(serializer);
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// ��Ҫ���������
	// 1��md5�����ʱ����Ҫһ�����ĵ�body
	// 2������ı�ʾ��Ҫ���ã�����Ҳ��Ҫ����
	// 3������body���������
	
	// 5��Elment��ͨ������
	
}
