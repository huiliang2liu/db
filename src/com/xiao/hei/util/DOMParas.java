package com.xiao.hei.util;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParas extends Paras{
	static DOMParas domParas;
	static DocumentBuilder documentBuilder;

	public static DOMParas getDomParas() {
		if (domParas != null)
			return domParas;
		throw new RuntimeException("������ȵ���init����");
	}

	public static DOMParas init() {
		if (domParas == null) {
			synchronized (DOMParas.class.getName()) {
				if (domParas == null)
					try {
						domParas = new DOMParas();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						domParas = null;
					}
			}
		}
		return domParas;
	}

	private DOMParas() throws Exception {
		// TODO Auto-generated constructor stub
		documentBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
	}

	@Override
	List<NodeTree> paras(File file) {
		// TODO Auto-generated method stub
		if (file == null || !file.exists() || file.isDirectory())
			return null;
		try {
			Document document = documentBuilder.parse(file);
			return document2list(document);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	List<NodeTree> paras(String uri) {
		// TODO Auto-generated method stub
		if (uri == null || uri.equals(""))
			return null;
		try {
			Document document = documentBuilder.parse(uri);
			return document2list(document);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	@Override
	List<NodeTree> paras(InputStream is) {
		// TODO Auto-generated method stub
		if (is == null)
			return null;
		try {
			Document document = documentBuilder.parse(is);
			return document2list(document);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	private List<NodeTree> document2list(Document document) {
		if (document == null || !document.hasChildNodes())
			return null;
		List<NodeTree> nodeTrees = new ArrayList<NodeTree>();
		NodeList nodeList = document.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (!"#text".equals(node.getNodeName()))
				nodeTrees.add(node2nodeTree(nodeList.item(i)));
		}
		return nodeTrees;
	}

	private NodeTree node2nodeTree(Node node) {
		NodeTree nodeTree = new NodeTree();
		String name = node.getNodeName();
		nodeTree.setName(name);
		if (node.hasAttributes()) {
			nodeTree.setAttributes(paras(node.getAttributes()));
		}
		String value = node.getTextContent();
		if (value != null)
			nodeTree.setValue(value);
		if (node.hasChildNodes()) {
			List<NodeTree> nodeTrees = new ArrayList<NodeTree>();
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (!"#text".equals(n.getNodeName()))
					nodeTrees.add(node2nodeTree(nodeList.item(i)));
			}
			nodeTree.setChildNodeTrees(nodeTrees);
		} else {
			nodeTree.setValue(null);
		}
		return nodeTree;
	}

	private Map<String, String> paras(NamedNodeMap namedNodeMap) {
		Map<String, String> map = new HashMap<String, String>();
		if (namedNodeMap != null) {
			for (int i = 0; i < namedNodeMap.getLength(); i++) {
				Node node = namedNodeMap.item(i);
				map.put(node.getNodeName(), node.getNodeValue());
			}
		}
		return map;
	}

	private DOMParas(InputStream is) {
		// TODO Auto-generated constructor stub
	}

	private DOMParas(String uri) {
		// TODO Auto-generated constructor stub
	}



	/**
	 * NodeTree ת��ΪString
	 * 
	 * @param nodeTree
	 * @return
	 * @throws Exception
	 */
	@Override
	 String nodeTree2string(NodeTree nodeTree) {
	try {
		if (nodeTree == null)
			return null;
		Transformer transformer = getTransformer();
		Source source = nodeTree2source(nodeTree); // �����ĵ���Դ��doc
		return source2string(transformer, source);
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	}
	
	/**
	 * ��ȡת����
	 * 
	 * @return
	 * @throws Exception
	 */
	private Transformer getTransformer() throws Exception {
		TransformerFactory transFactory = TransformerFactory.newInstance();// ȡ��TransformerFactoryʵ��
		Transformer transformer = transFactory.newTransformer(); // ��transFactory��ȡTransformerʵ��
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // ����������õı��뷽ʽ
		transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // �Ƿ��Զ���Ӷ���Ŀհ�
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); // �Ƿ����XML����
		return transformer;
	}

	/**
	 * Source ת��ΪString
	 * 
	 * @param transformer
	 * @param source
	 * @return
	 * @throws Exception
	 */
	private String source2string(Transformer transformer, Source source)
			throws Exception {
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);// ����Ŀ����Ϊwriter
		transformer.transform(source, result); // ��ʼת��
		return writer.toString();
	}

	/**
	 * NodeTree ת��ΪSource
	 * 
	 * @param nodeTree
	 * @return
	 */
	private Source nodeTree2source(NodeTree nodeTree) {
		Document document = documentBuilder.newDocument();
		document.appendChild(nodeTree2node(document, nodeTree));
		return new DOMSource(document);
	}

	/**
	 * List<NodeTree> ת��ΪSource
	 * 
	 * @param nodeTrees
	 * @return
	 */
	private Source nodeTree2source(List<NodeTree> nodeTrees) {
		Document document = documentBuilder.newDocument();
		Element element = document.createElement("root");
		for (NodeTree nodeTree : nodeTrees) {
			element.appendChild(nodeTree2node(document, nodeTree));
		}
		document.appendChild(element);
		return new DOMSource(document);
	}

	/**
	 * NodeTree ת��Ϊnode
	 * 
	 * @param document
	 * @param nodeTree
	 * @return
	 */
	private Node nodeTree2node(Document document, NodeTree nodeTree) {
		// TODO Auto-generated method stub
		Element element = document.createElement(nodeTree.getName());
		String value = nodeTree.getValue();
		if (value != null)
			element.setTextContent(value);
		if (nodeTree.hasAttributes()) {
			Map<String, String> map = nodeTree.getAttributes();
			Set<Entry<String, String>> set = map.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				element.setAttribute(entry.getKey(), entry.getValue());
			}
		}
		if (nodeTree.hasChildNodeTrees()) {
			List<NodeTree> nodeTrees = nodeTree.getChildNodeTrees();
			for (NodeTree nodeTree2 : nodeTrees) {
				element.appendChild(nodeTree2node(document, nodeTree2));
			}
		}
		return element;
	}



}
