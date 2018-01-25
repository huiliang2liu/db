package com.xiao.hei.util;

import java.util.List;
import java.util.Map;

public class NodeTree {
String name;
String value;
Map<String, String> attributes;
List<NodeTree> childNodeTrees;
boolean is_end=false;

 boolean isIs_end() {
	return is_end;
}
 void setIs_end(boolean isEnd) {
	is_end = isEnd;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getValue() {
	if(hasChildNodeTrees())
		return null;
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public Map<String, String> getAttributes() {
	return attributes;
}
public void setAttributes(Map<String, String> attributes) {
	this.attributes = attributes;
}
public List<NodeTree> getChildNodeTrees() {
	return childNodeTrees;
}
public void setChildNodeTrees(List<NodeTree> childNodeTrees) {
	this.childNodeTrees = childNodeTrees;
}
public boolean hasAttributes(){
	return attributes!=null&&attributes.size()>0;
}
public boolean hasChildNodeTrees(){
	return childNodeTrees!=null&&childNodeTrees.size()>0;
}
@Override
	public String toString() {
		// TODO Auto-generated method stub
	StringBuffer sb=new StringBuffer(name==null?"null":name).append("\n");
	sb.append(getValue()).append("\n");
	if(hasAttributes())
	sb.append(attributes).append("\n");
	if(hasChildNodeTrees())
		for (NodeTree nodeTree : childNodeTrees) {
		sb.append(nodeTree.toString()).append("\n");	
		}
		return sb.toString();
	}
}
