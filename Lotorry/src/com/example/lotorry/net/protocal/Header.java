package com.example.lotorry.net.protocal;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import org.apache.commons.codec.digest.DigestUtils;
import org.xmlpull.v1.XmlSerializer;

import com.example.lotorry.ConstantValue;
/**
 * ͷ���ķ�װ
 *
 */
public class Header {
	// <agenterid>889931</agenterid>
	private Leaf agenterid = new Leaf("agenterid", ConstantValue.AGENTERID);
	// <source>ivr</source>
	private Leaf source = new Leaf("source",ConstantValue.SOURCE);
	// <compress>DES</compress>
	private Leaf compress = new Leaf("compress",ConstantValue.COMPRESS);
	// <messengerid>20131013101533000001</messengerid>
	private Leaf messengerid = new Leaf("messengerid");
	// <timestamp>20131013101533</timestamp>
	private Leaf timestamp = new Leaf("timestamp");
	// <digest>7ec8582632678032d25866bd4bce114f</digest>
	private Leaf digest = new Leaf("digest");
	// <transactiontype>12002</transactiontype>
	private Leaf transactiontype = new Leaf("transactiontype");
	// <username>13200000000</username>
	private Leaf username = new Leaf("username");
	/**
	 * ���л�ͷ
	 * @param serializer
	 * @param body ������body�����ģ�
	 */
	public void serializerHeader(XmlSerializer serializer,String body){
		// ����ʱ���
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(new Date());
		timestamp.setTagValue(time);
		// ����messengerid��ʱ���+��λ�����
		Random random = new Random();
		int num = random.nextInt(99999)+1;//[0,n)
		DecimalFormat decimalFormat = new DecimalFormat("000000");
		messengerid.setTagValue(time + decimalFormat.format(num));
		// ����digest MD5��ʽ�����ݣ�ʱ��� + ���������� + ������body�����ģ�
 		String orgInfo = time + ConstantValue.AGENTER_PASSWORD + body;
 		String md5Hex = DigestUtils.md5Hex(orgInfo);
 		digest.setTagValue(md5Hex);
		
		try {
			serializer.startTag(null, "header");
			agenterid.serializerLeaf(serializer);
			source.serializerLeaf(serializer);
			compress.serializerLeaf(serializer);
			
			// ������
			messengerid.serializerLeaf(serializer);
			timestamp.serializerLeaf(serializer);
			digest.serializerLeaf(serializer);
			
			transactiontype.serializerLeaf(serializer);
			username.serializerLeaf(serializer);
			serializer.endTag(null, "header");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Leaf getTransactiontype() {
		return transactiontype;
	}
	public Leaf getUsername() {
		return username;
	}

	
}
