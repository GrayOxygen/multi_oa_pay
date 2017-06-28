package com.huiyang.wang.common.util;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * Jaxb解析工具
 */
public class JaxbParser {

	private static Logger logger = LoggerFactory.getLogger(JaxbParser.class);

	private String[] cdataNode;


	private JaxbParser() {
		super();
	}

	public static JaxbParser build() {
		return new JaxbParser();
	}

	/**
	 * 设置需要包含CDATA的节点
	 *
	 * @param cdataNode
	 */
	public JaxbParser setCdataNode(String[] cdataNode) {
		this.cdataNode = cdataNode;
		return this;
	}


	/**
	 * 转为xml串
	 *
	 * @param obj
	 * @return
	 */
	public String toXML(Object obj) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_FRAGMENT, true);// 去掉报文头
			StringWriter writer = new StringWriter();
			XMLSerializer serializer = getXMLSerializer(writer);
			m.marshal(obj, serializer.asContentHandler());
			result = writer.toString();
		} catch (Exception e) {
			throw new IllegalStateException("to xml error", e);
		}
		logger.debug("to text:{}", result);
		return result;
	}


	public <T> T toObj(Class<T> clazz, Reader reader) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(clazz);
			Unmarshaller um = context.createUnmarshaller();
			return (T) um.unmarshal(reader);
		} catch (Exception e) {
			logger.error("data parse error");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * XML转为对象
	 *
	 * @param xmlStr
	 * @return
	 */
	public <T> T toObj(Class<T> clazz, String xmlStr) {
		return toObj(clazz, new StringReader(xmlStr));
	}

	/**
	 * 设置属性
	 *
	 * @param os
	 * @return
	 */
	private XMLSerializer getXMLSerializer(Writer os) {
		OutputFormat of = new OutputFormat();
		formatCDataTag();
		if (cdataNode != null) {
			of.setCDataElements(cdataNode);
		}
		of.setPreserveSpace(true);
		of.setIndenting(true);
		of.setOmitXMLDeclaration(true);
		XMLSerializer serializer = new XMLSerializer(of);
		serializer.setOutputCharStream(os);
		return serializer;
	}

	/**
	 * 适配cdata tag
	 */
	private void formatCDataTag() {
		if (cdataNode == null) {
			return;
		}
		for (int i = 0; i < cdataNode.length; i++) {
			cdataNode[i] = "^" + cdataNode[i];
		}
	}
}