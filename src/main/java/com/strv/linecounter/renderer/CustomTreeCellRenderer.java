package com.strv.linecounter.renderer;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.strv.linecounter.entity.TreeNodeEntity;
import com.strv.linecounter.utility.DimensionManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class CustomTreeCellRenderer extends JLabel implements TreeCellRenderer {
    public CustomTreeCellRenderer() {
    }

    public static BufferedImage getResourceBufferedImage(String filePath) {
        if (CustomTreeCellRenderer.class.getClassLoader().getResourceAsStream(filePath) != null)
            try {
                return ImageIO.read(CustomTreeCellRenderer.class.getClassLoader().getResourceAsStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        return com.intellij.util.ui.UIUtil.createImage(10, 10, 0);
    }

    @SuppressWarnings("unchecked")
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        // Find out which node we are rendering and get its text
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        TreeNodeEntity entity = (TreeNodeEntity) node.getUserObject();

        // Format cell and add custom text to the cell
        setFont(new Font("Arial", Font.BOLD, 14));
        setForeground(EditorColorsManager.getInstance().getGlobalScheme().getDefaultForeground());

        switch (entity.getTreeNodeType()) {
            case ROOT:
                setFont(new Font("Arial", Font.BOLD, DimensionManager.fontSize(DimensionManager.FONT_SMALL)));
                setForeground(EditorColorsManager.getInstance().getGlobalScheme().getDefaultForeground());
                setText(entity.getText());
                setIcon(new ImageIcon(getResourceBufferedImage("drawable/folder.png")));
                break;
            case FILES_CATEGORY:
                setFont(new Font("Arial", Font.BOLD, DimensionManager.fontSize(DimensionManager.FONT_SMALL)));
                setForeground(EditorColorsManager.getInstance().getGlobalScheme().getDefaultForeground());
                setText(entity.getText() + " (" + entity.getValue() + ")");
                setIcon(new ImageIcon(getResourceBufferedImage("drawable/folder.png")));
                break;
            case METHODS_CATEGORY:
                setFont(new Font("Arial", Font.BOLD, DimensionManager.fontSize(DimensionManager.FONT_SMALL)));
                setForeground(EditorColorsManager.getInstance().getGlobalScheme().getDefaultForeground());
                setText(entity.getText() + " (" + entity.getValue() + ")");
                setIcon(new ImageIcon(getResourceBufferedImage("drawable/folder.png")));
                break;
            case FILES_NODE:
                setFont(new Font("Arial", Font.PLAIN, DimensionManager.fontSize(DimensionManager.FONT_SMALL)));
                setForeground(EditorColorsManager.getInstance().getGlobalScheme().getDefaultForeground());
                setText(entity.getText() + " (" + entity.getValue() + ")");
                setIcon(new ImageIcon(getResourceBufferedImage("drawable/classTypeJavaClass.png")));
                break;
            case FILES_ENTRY:
                setFont(new Font("Arial", Font.PLAIN, DimensionManager.fontSize(DimensionManager.FONT_SMALL)));
                setForeground(EditorColorsManager.getInstance().getGlobalScheme().getDefaultForeground());
                setText(entity.getText() + ": " + entity.getLineCount());
                setIcon(new ImageIcon(getResourceBufferedImage("drawable/classTypeJavaClass.png")));
                break;
            case METHODS_ENTRY:
                setFont(new Font("Arial", Font.PLAIN, DimensionManager.fontSize(DimensionManager.FONT_SMALL)));
                setForeground(EditorColorsManager.getInstance().getGlobalScheme().getDefaultForeground());
                setText(entity.getText() + ": " + entity.getLineCount());
                setIcon(new ImageIcon(getResourceBufferedImage("drawable/method.png")));
                break;
        }
        return this;
    }
}