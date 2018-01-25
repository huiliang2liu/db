package com.xiao.hei.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public abstract class Paras {
	/**
	 * 将文件转换为list
	 * 
	 * @param file
	 * @return
	 */
	public List<NodeTree> file2list(File file) {
		return paras(file);
	}

	/**
	 * 将文件转化为nodetree
	 * 
	 * @param file
	 * @return
	 */
	public NodeTree file2nodeTree(File file) {
		List<NodeTree> nodeTrees = file2list(file);
		return nodeTrees == null || nodeTrees.size() == 0 ? null : nodeTrees
				.get(0);
	}

	/**
	 * 将uri转换为list
	 * 
	 * @param uri
	 * @return
	 */
	public List<NodeTree> uri2list(String uri) {
		return paras(uri);
	}

	/**
	 * 将uri转换为nodetree
	 * 
	 * @param uri
	 * @return
	 */
	public NodeTree uri2nodeTree(String uri) {
		List<NodeTree> nodeTrees = uri2list(uri);
		return nodeTrees == null || nodeTrees.size() == 0 ? null : nodeTrees
				.get(0);
	}

	/**
	 * 将输入流转换为list
	 * 
	 * @param is
	 * @return
	 */
	public List<NodeTree> is2list(InputStream is) {
		return paras(is);
	}

	/**
	 * 将输入流转换为nodetree
	 * 
	 * @param is
	 * @return
	 */
	public NodeTree is2nodeTree(InputStream is) {
		List<NodeTree> nodeTrees = is2list(is);
		return nodeTrees == null || nodeTrees.size() == 0 ? null : nodeTrees
				.get(0);
	}

	/**
	 * 将文件转化为list
	 * 
	 * @param file
	 * @return
	 */
	abstract List<NodeTree> paras(File file);

	/**
	 * 将uri转换为list
	 * 
	 * @param uri
	 * @return
	 */
	abstract List<NodeTree> paras(String uri);

	/**
	 * 将is转化为list
	 * 
	 * @param is
	 * @return
	 */
	abstract List<NodeTree> paras(InputStream is);

	/**
	 * 将nodetree转化为string
	 * 
	 * @param nodeTree
	 * @return
	 */
	abstract String nodeTree2string(NodeTree nodeTree);

	/**
	 * 将list转化为string
	 * 
	 * @param nodeTrees
	 * @return
	 */
 String list2string(List<NodeTree> nodeTrees,String father_name,Map<String, String> atts){
	 if(nodeTrees==null||father_name==null)
		 return "";
	NodeTree nodeTree=new NodeTree();
	nodeTree.setChildNodeTrees(nodeTrees);
	nodeTree.setName(father_name);
	nodeTree.setAttributes(atts);
	return nodeTree2string(nodeTree);
 }
	/**
	 * 保存
	 * @param savePath
	 * @param name
	 * @param nodeTree
	 */
	public boolean save(String savePath,String name,NodeTree nodeTree){
		try {
			return save(pathAname2os(savePath, name), nodeTree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 保存
	 * @param savePath
	 * @param name
	 * @param nodeTrees
	 */
	public boolean save(String savePath,String name,List<NodeTree> nodeTrees,String father_name,Map<String, String> atts){
		try {
			return save(pathAname2os(savePath, name), nodeTrees,father_name,atts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 保存
	 * @param file
	 * @param nodeTree
	 */
	public boolean save(File file, NodeTree nodeTree){
		try {
			return save(file2os(file), nodeTree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 保存
	 * @param file
	 * @param nodeTrees
	 */
	public boolean save(File file, List<NodeTree> nodeTrees,String father_name,Map<String, String> atts){
		try {
			return save(file2os(file), nodeTrees,father_name,atts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存
	 * 
	 * @param path
	 * @param nodeTree
	 */
	public boolean  save(String path, NodeTree nodeTree) {
		try {
			return save(path2os(path), nodeTree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存
	 * 
	 * @param path
	 * @param nodeTrees
	 */
	public boolean save(String path, List<NodeTree> nodeTrees,String father_name,Map<String, String> atts) {
		try {
			return save(path2os(path), nodeTrees,father_name,atts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存
	 * 
	 * @param os
	 * @param nodeTree
	 */
	public boolean save(OutputStream os, NodeTree nodeTree) {
		try {
			return save(os, nodeTree2string(nodeTree));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存
	 * 
	 * @param os
	 * @param nodeTrees
	 */
	public boolean save(OutputStream os, List<NodeTree> nodeTrees,String father_name,Map<String, String> atts) {
		try {
			return save(os, list2string(nodeTrees,father_name,atts));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param savePath
	 *            保存目录
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws Exception
	 */
	public OutputStream pathAname2os(String savePath, String fileName)
			throws Exception {
		if (savePath == null || savePath.isEmpty() || fileName == null
				|| fileName.isEmpty())
			return null;
		File file_path = new File(savePath);
		if (!file_path.exists())
			file_path.mkdirs();
		return path2os(savePath + "/" + fileName);
	}

	/**
	 * 将文件名转化为输出流
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public OutputStream path2os(String path) throws Exception {
		if (path == null || path.isEmpty())
			return null;
		return new FileOutputStream(path);
	}

	/**
	 * 将文件转化为输出流
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public OutputStream file2os(File file) throws Exception {
		if (file == null)
			return null;
		return new FileOutputStream(file);
	}

	/**
	 * 保存数据
	 * 
	 * @param os
	 * @param s
	 * @throws Exception
	 */
	public boolean save(OutputStream os, String s) throws Exception {
		return save(os, s, "UTF-8");
	}

	/**
	 * 保存数据
	 * 
	 * @param os
	 * @param s
	 * @param charsetName
	 * @throws Exception
	 */
	public boolean save(OutputStream os, String s, String charsetName)
			throws Exception {
		if (s == null)
			return false;
		return save(os, s.getBytes(charsetName));
	}

	/**
	 * 保存数据
	 * 
	 * @param os
	 * @param buff
	 * @throws Exception
	 */
	public synchronized boolean save(OutputStream os, byte[] buff)
			throws Exception {
		if (os != null) {
			if (buff != null)
				os.write(buff);
			os.flush();
			os.close();
		}
		return true;
	}
}
