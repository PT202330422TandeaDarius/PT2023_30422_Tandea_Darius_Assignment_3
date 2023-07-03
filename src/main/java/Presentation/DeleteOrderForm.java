package Presentation;

import BusinessLogic.GenBL;
import Model.Order;
import Util.Colors;
import Util.Dimensions;

import javax.swing.*;
import java.awt.*;

/**
 * This Class is the GUI for deleting an order.
 */
public class DeleteOrderForm extends JFrame {
    JLabel idLabel = new JLabel("ID");

    JTextField idField = new JTextField();

    JButton deleteButton = new JButton("Delete");

    /**
     * This is the constructor of the class.
     * @param orderBL is the business logic for the order.
     */
    public DeleteOrderForm(GenBL<Order> orderBL) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new FlowLayout());
        this.setTitle("Delete Order");
        this.setBackground(Colors.background);

        idLabel.setPreferredSize(Dimensions.labelDimension);
        idField.setPreferredSize(Dimensions.fieldDimension);
        deleteButton.setPreferredSize(Dimensions.buttonDimension);

        this.add(idLabel);
        this.add(idField);
        this.add(deleteButton);


        deleteButton.addActionListener(e -> {
            if (idField.getText().equals("ID") || idField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter an ID!");
                return;
            }

            int id = Integer.parseInt(idField.getText());

            if (orderBL.deleteFromDatabase(id)) {
                JOptionPane.showMessageDialog(null, "Order deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Order could not be deleted!");
            }
            resetFields();
            this.dispose();
        });
    }

    public void resetFields() {
        idField.setText("ID");
    }
}
