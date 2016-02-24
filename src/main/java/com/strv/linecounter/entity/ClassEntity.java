package com.strv.linecounter.entity;

import com.intellij.psi.PsiFile;
import org.apache.commons.lang.StringUtils;

public class ClassEntity {
    private PsiFile mFile;

    public ClassEntity(PsiFile file) {
        mFile = file;
    }

    public PsiFile getFile() {
        return this.mFile;
    }

    public String getName() {
        return mFile.getName();
    }

    public int getLineCount() {
        return StringUtils.countMatches(mFile.getText(), "\n") + 1;
    }
}