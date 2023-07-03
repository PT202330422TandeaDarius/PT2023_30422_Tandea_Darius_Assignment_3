package Presentation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is used to create a frame that contains a table with the data from the database
 * @param <T> type of the table to be displayed in the frame
 */
public class ViewFrame<T> extends JFrame {
    Class<T> type;
    JScrollPane scrollPane;
    GenTable<T> table;
    public ViewFrame(Class<T> type, ArrayList<ArrayList<Object>> data, Object[] columnNames) {
        super("Table "+ type.getSimpleName());
        this.type = type;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        System.out.println(Arrays.toString(columnNames));
        this.setResizable(false);
        table = new GenTable<T>(type,convert(data),columnNames);
        scrollPane = new JScrollPane(table);
        scrollPane.setSize(700, 500);
        add(scrollPane);
        scrollPane.setVisible(true);
        setVisible(true);
    }

    public Object[][] convert(ArrayList<ArrayList<Object>> data){
        Object[][] data2 = new Object[data.size()][];
        for(int i = 0; i < data.size(); i++){
            data2[i] = data.get(i).toArray();
        }

        System.out.println(Arrays.deepToString(data2));
        return data2;
    }
}
