package com.example.lotorry.net.protocal;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

/**
 * �������ݵķ�װ
 * @author Administrator
 *
 */
public abstract class Element {
	// ���Ὣ���е������Ҷ�Ӷ�����element��
	// Element����Ϊ���е�������Ϊ����Element������Ĺ�������
	// 1��ÿ��������Ҫ���л��Լ�
	/**
	 * ÿ��������Ҫ���л��Լ�
	 * @param serializer
	 */
	public abstract void serializerElement(XmlSerializer serializer);
	// 2��ÿ���������Լ��ı�ʾ
	/**
	 * ÿ���������Լ��ı�ʾ
	 * @return
	 */
	public abstract String getTransactionType();
	// Ψһ�ı�ʾ
	// <lotteryid>118</lotteryid>
//	private Leaf lotteryid = new Leaf("lotteryid");
//	// <issues>1</issues>
//	private Leaf issues = new Leaf("issues", "1");
//	/**
//	 * ���л�����
//	 * @param serializer
//	 */
//	public void serializerElement(XmlSerializer serializer){
//		try {
//			serializer.startTag(null, "element");
//			lotteryid.serializerLeaf(serializer);
//			issues.serializerLeaf(serializer);
//			serializer.endTag(null, "element");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public Leaf getLotteryid() {
//		return lotteryid;
//	}
//	public String getTransactionType(){
//		return "12002";
//	}
}
