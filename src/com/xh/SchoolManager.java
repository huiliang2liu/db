package com.xh;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiao.hei.util.DOMParas;
import com.xiao.hei.util.NodeTree;

/**
 * author:xh 
 * email:825378291@qq.com
 * time：2017-2-16 上午09:40:07
 *
 */
public class SchoolManager {
	private List<NodeTree> list;
	HashMap<String, Object> map;
	
    static SchoolManager schoolManager;
	  public static SchoolManager init()
	  {
	    if (schoolManager==null) {
	      synchronized (SchoolManager.class.getName()) {
	        	schoolManager = new SchoolManager();
	      }
	    }
	    return schoolManager;
	  }
	  private SchoolManager() {
		  DOMParas domParas=DOMParas.init();
		  NodeTree nodeTree=domParas.uri2nodeTree(SchoolManager.class.getResource("/db/schools.xml").toString());
	      if(nodeTree!=null)
		  list=nodeTree.getChildNodeTrees();
	      map.get("");
	  }

	  public List<NodeTree> getList() {
	    return this.list;
	  }
	  public static void main(String[] args) {
//		  SchoolManager schoolManager=SchoolManager.getSchoolManager();
//		  List<NodeTree> nodeTrees=schoolManager.getList();
//		  for (NodeTree nodeTree : nodeTrees) {
//			System.out.println(nodeTree);
//		}
	  }
}
