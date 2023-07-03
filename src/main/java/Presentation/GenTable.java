package Presentation;

import Util.Colors;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Generic table class
 * @param <T> type of the table
 */
public class GenTable<T> extends JTable {
    private Class<T> type;

    /**
     * Constructor
     * @param type type of the table
     * @param data data to be displayed
     * @param columnNames column names
     */
    public GenTable(Class<T> type, Object[][] data, Object[] columnNames) {
        super(data, columnNames);
        this.setSize(700, 500);
        this.setRowHeight(30);

        this.type = type;
        this.setName(this.type.getName());
        this.setVisible(true);

        this.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
    }

    /**
     * Custom cell renderer for row coloring
     */
    private static class CustomTableCellRenderer extends DefaultTableCellRenderer { //found in on StackOverflow
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (row % 2 == 1) {
                component.setBackground(Colors.background);
            } else {
                component.setBackground(Color.white);
            }
            if(column % 2==1)
                component.setBackground(component.getBackground().darker().darker().brighter());

            return component;
        }
    }
}
