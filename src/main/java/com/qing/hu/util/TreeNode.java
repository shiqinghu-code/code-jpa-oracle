package com.qing.hu.util;

import java.io.Serializable;
import java.util.List;

/**
 *  树节点
 * @author  
 *
 */

public class TreeNode implements Serializable {

    private static final long serialVersionUID = 4304371854910923353L;
    private String id;

    private String parentId;

    private String name;
    /**
     *   一般情况下 树节点不止需要这些基础信息 还需要部分业务信息便于操作属性节点
     */
    private Object obj;

    private boolean isLeaf;

    /**
     *  当前树节点深度
     */
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    private List<TreeNode> children;

    public TreeNode() {

    }

    public TreeNode(String id, String name, String parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public TreeNode(String id, String name, TreeNode parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "id='" + id + '\'' + ", parentId='" + parentId + '\'' + ", name='" + name + '\''
                + ", children=" + children + '}';
    }
}
