package com.strv.linecounter.entity;


public class TreeNodeEntity {
    private TreeNodeTypeEnum mTreeNodeType;
    private Object mObject;
    private String mText; // some text
    private Integer mValue; // like number for tree (5)
    private Integer mLineCount; // like number for tree (5)

    public TreeNodeEntity(TreeNodeTypeEnum mNodeType, String mText) {
        this.mTreeNodeType = mNodeType;
        this.mText = mText;
        this.mValue = 0;
        this.mLineCount = 0;
    }

    public TreeNodeEntity(TreeNodeTypeEnum mNodeType, String mText, int value) {
        this.mTreeNodeType = mNodeType;
        this.mText = mText;
        this.mValue = value;
        this.mLineCount = 0;
    }

    public TreeNodeEntity(TreeNodeTypeEnum mNodeType, Object o, String mText, int lineCount) {
        this.mTreeNodeType = mNodeType;
        this.mObject = o;
        this.mText = mText;
        this.mValue = 0;
        this.mLineCount = lineCount;
    }

    public TreeNodeTypeEnum getTreeNodeType() {
        return mTreeNodeType;
    }


    public void setTreeNodeType(TreeNodeTypeEnum mTreeNodeType) {
        this.mTreeNodeType = mTreeNodeType;
    }


    public String getText() {
        return mText;
    }


    public void setText(String mText) {
        this.mText = mText;
    }


    public Integer getValue() {
        return mValue;
    }


    public void setValue(Integer mValue) {
        this.mValue = mValue;
    }


    public int getLineCount() {
        return this.mLineCount;
    }


    public Object getObject() {
        return mObject;
    }
}