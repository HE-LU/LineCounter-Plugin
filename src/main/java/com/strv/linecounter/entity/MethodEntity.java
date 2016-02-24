package com.strv.linecounter.entity;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;

import java.util.List;

public class MethodEntity {
    private PsiFile mContainingFile;
    private List<PsiMethod> mMethodList;

    public MethodEntity(PsiFile containingClass, List<PsiMethod> methodList) {
        mContainingFile = containingClass;
        mMethodList = methodList;
    }

    public String getClassName() {
        return mContainingFile.getName();
    }

    public List<PsiMethod> getMethodList() {
        return mMethodList;
    }
}