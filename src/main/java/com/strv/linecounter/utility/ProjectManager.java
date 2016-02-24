package com.strv.linecounter.utility;


import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import com.intellij.psi.*;
import com.strv.linecounter.entity.AnalyzeEntity;
import com.strv.linecounter.entity.ClassEntity;
import com.strv.linecounter.entity.MethodEntity;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProjectManager {

    public static AnalyzeEntity analyzeProject(final Project project, final Integer maxFileLines, final Integer maxMethodLines) {
        final AnalyzeEntity entity = new AnalyzeEntity();

        for (VirtualFile file : ProjectRootManager.getInstance(project).getContentSourceRoots()) {
            VfsUtilCore.visitChildrenRecursively(file, new VirtualFileVisitor() {
                @Override
                public boolean visitFile(@NotNull VirtualFile file) {
                    PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
                    if (psiFile instanceof PsiJavaFile) {
                        if (psiFile.getName().equals("R.java"))
                            return super.visitFile(file);
                        if ((StringUtils.countMatches(psiFile.getText(), "\n") + 1) > maxFileLines)
                            entity.addClass(new ClassEntity(psiFile));

                        List<PsiMethod> methodList = new ArrayList<>();
                        for (PsiClass psiClass : ((PsiJavaFile) psiFile).getClasses()) {
                            for (PsiMethod method : psiClass.getMethods()) {
                                if (getMethodLineCount(method) > maxMethodLines)
                                    methodList.add(method);
                            }
                        }

                        if (!methodList.isEmpty())
                            entity.addMethod(new MethodEntity(psiFile, methodList));
                    }

                    return super.visitFile(file);
                }
            });
        }
        return entity;
    }


    public static int getMethodLineCount(PsiMethod method) {
        return StringUtils.countMatches(method.getText(), "\n") + 1;
    }


    public static int getMethodStartLineNumber(@NotNull Project project, @NotNull PsiMethod method) {
        if (method.getNameIdentifier() == null)
            return 0;

        int offset = method.getNameIdentifier().getTextOffset();
        Document document = PsiDocumentManager.getInstance(project).getDocument(method.getContainingFile());
        if (document != null)
            return document.getLineNumber(offset);
        else
            return 0;
    }
}
