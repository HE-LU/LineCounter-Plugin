package com.strv.linecounter.toolwindow;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.ui.components.JBScrollPane;
import com.strv.linecounter.entity.*;
import com.strv.linecounter.utility.ProjectManager;
import com.strv.linecounter.utility.UIManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.*;

public class MainToolWindow extends JFrame implements ToolWindowFactory {
    private ToolWindow mToolWindow;
    private Project mProject;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        mToolWindow = toolWindow;
        mProject = project;

        createUI();
    }

    private void createUI() {
        final JPanel panel = UIManager.initUI(mToolWindow);

        final JButton findButton = (JButton) UIManager.findComponentByName(panel, "findButton");
        final JBScrollPane scrollPanel = (JBScrollPane) UIManager.findComponentByName(panel, "contentScrollPanel");
        final JTextField methodField = (JTextField) UIManager.findComponentByName(panel, "methodField");
        final JTextField classField = (JTextField) UIManager.findComponentByName(panel, "classField");

        if (scrollPanel != null) {
            JViewport viewport = scrollPanel.getViewport();
            final JTree tree = (JTree) viewport.getView();
            setTreeClickListener(tree);

            if (findButton != null && tree != null && methodField != null && classField != null) {
                findButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        refreshContent(tree, Integer.valueOf(classField.getText()), Integer.valueOf(methodField.getText()));
                        scrollPanel.updateUI();
                    }
                });
            }
        }
    }

    private void refreshContent(JTree tree, Integer classesLines, Integer methodsLines) {
        AnalyzeEntity analyzeEntity = ProjectManager.analyzeProject(mProject, classesLines, methodsLines);

        if (analyzeEntity.getClassList().isEmpty() && analyzeEntity.getMethodsList().isEmpty())
            tree.setRootVisible(false);
        else
            tree.setRootVisible(true);

        int methodProblemCount = 0;
        for (MethodEntity entity : analyzeEntity.getMethodsList())
            methodProblemCount += entity.getMethodList().size();

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        root.removeAllChildren();
        DefaultMutableTreeNode classList = new DefaultMutableTreeNode(new TreeNodeEntity(TreeNodeTypeEnum.FILES_CATEGORY, "Files", analyzeEntity.getClassList().size()));
        DefaultMutableTreeNode methodList = new DefaultMutableTreeNode(new TreeNodeEntity(TreeNodeTypeEnum.METHODS_CATEGORY, "Methods", methodProblemCount));

        if (!analyzeEntity.getClassList().isEmpty())
            root.add(classList);
        if (!analyzeEntity.getMethodsList().isEmpty())
            root.add(methodList);

        for (ClassEntity entity : analyzeEntity.getClassList()) {
            classList.add(new DefaultMutableTreeNode(new TreeNodeEntity(TreeNodeTypeEnum.FILES_ENTRY, entity.getFile(), entity.getName(), entity.getLineCount())));
        }

        for (MethodEntity entity : analyzeEntity.getMethodsList()) {
            DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(new TreeNodeEntity(TreeNodeTypeEnum.FILES_NODE, entity.getClassName(), entity.getMethodList().size()));
            methodList.add(classNode);

            for (PsiMethod method : entity.getMethodList()) {
                classNode.add(new DefaultMutableTreeNode(new TreeNodeEntity(TreeNodeTypeEnum.METHODS_ENTRY, method, method.getName(), ProjectManager.getMethodLineCount(method))));
            }
        }

        tree.updateUI();
    }


    private void setTreeClickListener(final JTree tree) {
        tree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                try {
                    int keyCode = e.getKeyCode();

                    TreePath path = tree.getSelectionPath();

                    if (keyCode == KeyEvent.VK_ENTER) {
                        itemClicked(path);
                    }
                } catch (NullPointerException ex) {//exception
                }
            }
        });
        tree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                TreePath path = tree.getSelectionPath();
                if (e.getClickCount() == 2) try {
                    itemClicked(path);
                } catch (Exception ex) {//exception
                }
            }
        });
    }


    private void itemClicked(TreePath path) {
        TreeNodeEntity entity = ((TreeNodeEntity) ((DefaultMutableTreeNode) path.getLastPathComponent()).getUserObject());

        switch (entity.getTreeNodeType()) {
            case FILES_ENTRY:
                PsiFile psiFile = (PsiFile) entity.getObject();
                VirtualFile vf = psiFile.getVirtualFile();
                new OpenFileDescriptor(mProject, vf).navigate(true);
                break;
            case METHODS_ENTRY:
                PsiMethod psiMethod = (PsiMethod) entity.getObject();
                vf = psiMethod.getContainingFile().getVirtualFile();
                int line = ProjectManager.getMethodStartLineNumber(mProject, psiMethod);
                new OpenFileDescriptor(mProject, vf, line, 0).navigate(true);
                break;
        }
    }
}
