package com.xiao.hei.util;

import java.util.List;

public class ProvinceManager {
	List<NodeTree> provinces;// 省
	private DOMParas  domParas;
	static ProvinceManager provinceManager;
	public static ProvinceManager init() {
		if (provinceManager==null)
			synchronized (ProvinceManager.class.getName()) {
				if (provinceManager==null)
					provinceManager=new ProvinceManager();
			}
		return provinceManager;
	}


	private ProvinceManager() {
		domParas = DOMParas.init();
		NodeTree province = domParas.uri2nodeTree(ProvinceManager.class.getResource("/db/city.xml").toString());
		if (province != null)
			provinces = province.getChildNodeTrees();
	}

	
	/**
	 * 获取省
	 * author:xh
	 * email:825378291@qq.com
	 * time:2017-1-22 下午05:08:43
	 * @return
	 */
	public List<NodeTree> getProvinces() {
		return provinces;
	}

//	public static void main(String[] args) {
//		DbManage dbManage = DbManage.init();
//		SAXParas saxParas=SAXParas.init();
//		List<NodeTree> nodeTrees=dbManage.getProvinces();
//		for (NodeTree nodeTree : nodeTrees) {
//			Map<String, String> atts=nodeTree.getAttributes();
//			String name=atts.get("name");
//			String string=Chinese2PinYin.getPinyin(name).split("=")[0];
//			if(name.equals("广东省"))
//				string="GuangDongSheng";
//			atts.put("quanpin", string);
//			char[] arrs=string.toCharArray();
//			String string2="";
//			for (char c : arrs) {
//				if (c >= 65 && c < 91)
//					string2+=c;
//			}
//			atts.put("jianpin", string2);
//			atts.put("shouzimu", string2.substring(0, 1));
//			nodeTree.setAttributes(atts);
//		}
//		List<NodeTree> list=new ArrayList<NodeTree>();
//		System.out.println(nodeTrees.size());
//		list.add(nodeTrees.get(0));
//	for (int i = 1; i < nodeTrees.size(); i++) {
//		NodeTree nodeTreei=nodeTrees.get(i);
//		boolean is_add=false;
//		for (int j = 0; j < list.size(); j++) {
//			NodeTree nodeTreej=list.get(j);
//			if(nodeTreei.getAttributes().get("shouzimu").toCharArray()[0]<nodeTreej.getAttributes().get("shouzimu").toCharArray()[0]){
//				list.add(j, nodeTreei);
//				is_add=true;
//				break;
//			}
//		}
//		if(!is_add)
//			list.add(nodeTreei);
//	}
//		Map<String, String> atts=new TreeMap<String, String>();
//		atts.put("name", "中国");
//		saxParas.save("G:\\me\\myEclipse\\City\\src\\db/city1.xml", list, "root", atts);
//		System.out.println(Chinese2PinYin.getPinyin("广东省"));
//	}
}
