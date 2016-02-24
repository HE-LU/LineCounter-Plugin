package com.strv.linecounter.entity;

public enum TreeNodeTypeEnum {
    // 	root
    //		files_category
    //			files_entry
    //		methods_category
    //			files_node
    // 		        methods_entry

    ROOT("root"),
    FILES_CATEGORY("files_category"),
    METHODS_CATEGORY("methods_category"),
    FILES_ENTRY("files_entry"),
    FILES_NODE("files_node"),
    METHODS_ENTRY("methods_entry");

    private final String value;


    private TreeNodeTypeEnum(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }

}