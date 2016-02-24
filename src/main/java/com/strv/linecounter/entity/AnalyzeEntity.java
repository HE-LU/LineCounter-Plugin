package com.strv.linecounter.entity;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeEntity {
    private List<ClassEntity> mClassList = new ArrayList<>();
    private List<MethodEntity> mMethodList = new ArrayList<>();

    public void addClass(ClassEntity entity) {
        mClassList.add(entity);
    }

    public void addMethod(MethodEntity entity) {
        mMethodList.add(entity);
    }


    public List<ClassEntity> getClassList() {
        return mClassList;
    }

    public List<MethodEntity> getMethodsList() {
        return mMethodList;
    }
}
