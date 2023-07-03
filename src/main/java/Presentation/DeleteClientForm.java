package Presentation;

import BusinessLogic.GenBL;
import Model.Client;
import Model.Order;
import Util.Colors;
import Util.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This Class is the GUI for deleting a client.
 */
public class DeleteClientForm extends JFrame {

    JLabel idLabel = new JLabel("ID");
    JTextField idField = new JTextField("ID",20);
    JButton btnDeleteClient = new JButton("Delete Client");

    /**
     * This is the constructor of the class.
     * @param clientBL is the business logic for the client.
     */
    public DeleteClientForm(GenBL<Client> clientBL) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new FlowLayout()); // Set layout manager to null for absolute positioning
        this.setTitle("Delete Client");
        this.setBackground(Colors.background);

        idLabel.setPreferredSize(Dimensions.labelDimension);
        idField.setPreferredSize(Dimensions.fieldDimension);
        btnDeleteClient.setPreferredSize(Dimensions.buttonDimension);

        add(idLabel);
        add(idField);
        add(btnDeleteClient);


        btnDeleteClient.addActionListener(e -> {

            if (idField.getText().equals("ID") || idField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter an ID!");
                return;
            }

            int id = Integer.parseInt(idField.getText());

            GenBL<Order> orderBL = new GenBL<>(Order.class);

            for (ArrayList<Object> order : orderBL.returnAllDataObjects()) {
                if ((int) order.get(2) == id) {
                    JOptionPane.showMessageDialog(null, "Client is in an order! \n Cannot delete!");
                    return;
                }
            }

            if (clientBL.deleteFromDatabase(id)) {
                JOptionPane.showMessageDialog(null, "Client deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Client not found!");
            }
            resetFields();
            this.dispose();
        });

        btnDeleteClient.setBackground(Colors.buttonDefault);
        idField.setBackground(Colors.textField);

        btnDeleteClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteClient.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteClient.setBackground(Colors.buttonDefault);
            }
        });
    }

    public void resetFields() {
        idField.setText("ID");
    }
}
