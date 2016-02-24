package com.strv.linecounter.utility;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.PsiMethod;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import com.strv.linecounter.entity.TreeNodeEntity;
import com.strv.linecounter.entity.TreeNodeTypeEnum;
import com.strv.linecounter.renderer.CustomTreeCellRenderer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;


public class UIManager {
    public static JPanel initUI(ToolWindow toolWindow) {
        // create UI
        final JPanel myToolWindowContent = new JPanel();
        final Content content = ContentFactory.SERVICE.getInstance().createContent(myToolWindowContent, "", false);
        myToolWindowContent.setLayout(new BoxLayout(myToolWindowContent, BoxLayout.Y_AXIS));
        toolWindow.getContentManager().removeAllContents(true);
        toolWindow.getContentManager().addContent(content);

        //create the tree by passing in the root node
        JTree tree = new Tree(new DefaultMutableTreeNode(new TreeNodeEntity(TreeNodeTypeEnum.ROOT, "Result")));
        tree.setOpaque(false);
        tree.setCellRenderer(new CustomTreeCellRenderer());
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setRootVisible(false);

        final JBScrollPane contentScrollPanel = new JBScrollPane(tree, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentScrollPanel.setName("contentScrollPanel");

        myToolWindowContent.add(Box.createRigidArea(new Dimension(0, DimensionManager.reDimension(10))));
        myToolWindowContent.add(createHeaderPanel()); // Header
        myToolWindowContent.add(Box.createRigidArea(new Dimension(0, DimensionManager.reDimension(10))));

        myToolWindowContent.add(contentScrollPanel); // Content scroll

        myToolWindowContent.add(Box.createRigidArea(new Dimension(0, DimensionManager.reDimension(10))));
        myToolWindowContent.add(createFooterPanel()); // Footer
        myToolWindowContent.add(Box.createRigidArea(new Dimension(0, DimensionManager.reDimension(10))));

        return myToolWindowContent;
    }


    private static JPanel createHeaderPanel() {
        final JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

        final JLabel methodLabel = new JLabel("<html><center>Max lines in method:</center></html>");
        methodLabel.setFont(new Font("Ariel", Font.BOLD, DimensionManager.fontSize(11)));
        methodLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        final JTextField methodField = new JTextField("100");
        methodField.setFont(new Font("Ariel", Font.BOLD, DimensionManager.fontSize(11)));
        methodField.setHorizontalAlignment(SwingConstants.CENTER);
        methodField.setName("methodField");
        methodField.setMaximumSize(new Dimension(DimensionManager.reDimension(70), DimensionManager.reDimension(20)));
        methodField.setMinimumSize(new Dimension(DimensionManager.reDimension(70), DimensionManager.reDimension(20)));
        methodField.setPreferredSize(new Dimension(DimensionManager.reDimension(70), DimensionManager.reDimension(20)));
        methodField.setSize(new Dimension(DimensionManager.reDimension(70), DimensionManager.reDimension(20)));
        methodField.setColumns(3);

        final JLabel classLabel = new JLabel("<html><center>Max lines in file:</center></html>");
        classLabel.setFont(new Font("Ariel", Font.BOLD, DimensionManager.fontSize(11)));
        classLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        final JTextField classField = new JTextField("1000");
        classField.setFont(new Font("Ariel", Font.BOLD, DimensionManager.fontSize(11)));
        classField.setHorizontalAlignment(SwingConstants.CENTER);
        classField.setName("classField");
        classField.setMaximumSize(new Dimension(DimensionManager.reDimension(70), DimensionManager.reDimension(20)));
        classField.setMinimumSize(new Dimension(DimensionManager.reDimension(70), DimensionManager.reDimension(20)));
        classField.setPreferredSize(new Dimension(DimensionManager.reDimension(70), DimensionManager.reDimension(20)));
        classField.setSize(new Dimension(DimensionManager.reDimension(70), DimensionManager.reDimension(20)));
        classField.setColumns(3);

        final JLabel spacerLabel = new JLabel("<html><center></center></html>");
        spacerLabel.setFont(new Font("Ariel", Font.BOLD, DimensionManager.fontSize(11)));

        headerPanel.add(methodLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(DimensionManager.reDimension(5), 0)));
        headerPanel.add(methodField);

        headerPanel.add(classLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(DimensionManager.reDimension(5), 0)));
        headerPanel.add(classField);
        headerPanel.add(spacerLabel);

        ((PlainDocument) methodField.getDocument()).setDocumentFilter(new IntFilter(4));
        ((PlainDocument) classField.getDocument()).setDocumentFilter(new IntFilter(4));
        return headerPanel;
    }


    private static JPanel createFooterPanel() {
        final JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        final JButton findButton = new JButton("<html><center>Find</center></html>");
        findButton.setFont(new Font("Ariel", Font.BOLD, DimensionManager.fontSize(13)));
        findButton.setHorizontalAlignment(SwingConstants.CENTER);
        findButton.setName("findButton");
        findButton.setMaximumSize(new Dimension(DimensionManager.reDimension(150), DimensionManager.reDimension(30)));
        findButton.setMinimumSize(new Dimension(DimensionManager.reDimension(150), DimensionManager.reDimension(30)));
        findButton.setPreferredSize(new Dimension(DimensionManager.reDimension(150), DimensionManager.reDimension(30)));
        findButton.setSize(new Dimension(DimensionManager.reDimension(150), DimensionManager.reDimension(30)));
        findButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(findButton);

        return footerPanel;
    }

    public static void addClassLabel(JPanel panel, String name) {
        final JLabel classNameText = new JLabel("<html><center>Class: " + name + "</center></html>");
        classNameText.setFont(new Font("Ariel", Font.BOLD, DimensionManager.fontSize(18)));
        classNameText.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(classNameText);
    }


    public static void addMethodLabel(JPanel panel, String name) {
        final JLabel methodText = new JLabel("<html><center>" + name + "()</center></html>");
        methodText.setFont(new Font("Ariel", Font.ITALIC, DimensionManager.fontSize(14)));
        methodText.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(methodText);
    }


    private static int getMethodStartLineNumber(@NotNull PsiMethod method, @NotNull Document document) {
        if (method.getNameIdentifier() == null)
            return -1;

        int offset = method.getNameIdentifier().getTextOffset();
        return document.getLineNumber(offset);
    }


    public static Component findComponentByName(Container container, String componentName) {
        for (Component component : container.getComponents()) {
//            Log.d("Compare: " + componentName + " TO: " + component.getName());
            if (componentName.equals(component.getName())) {
                return component;
            }
            if (component instanceof JRootPane) {
                JRootPane nestedJRootPane = (JRootPane) component;
                Component comp = findComponentByName(nestedJRootPane.getContentPane(), componentName);
                if (comp != null)
                    return comp;
            }
            if (component instanceof JPanel) {
                // JPanel found. Recursing into this panel.
                JPanel nestedJPanel = (JPanel) component;
                Component comp = findComponentByName(nestedJPanel, componentName);
                if (comp != null)
                    return comp;
            }
        }
        return null;
    }


    static class IntFilter extends DocumentFilter {

        private int limit;

        public IntFilter(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("Limit can not be <= 0");
            }
            this.limit = limit;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            javax.swing.text.Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString()))
                super.insertString(fb, offset, string, attr);
        }

        private boolean test(String text) {
            try {
                if (text.equals(""))
                    return true;
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

            int currentLength = fb.getDocument().getLength();
            int overLimit = (currentLength + text.length()) - limit - length;
            if (overLimit > 0) {
                text = text.substring(0, text.length() - overLimit);
            }
            if (text.length() > 0) {
                javax.swing.text.Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder();
                sb.append(doc.getText(0, doc.getLength()));
                sb.replace(offset, offset + length, text);

                if (test(sb.toString()))
                    super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            javax.swing.text.Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (test(sb.toString()))
                super.remove(fb, offset, length);
        }
    }
}
