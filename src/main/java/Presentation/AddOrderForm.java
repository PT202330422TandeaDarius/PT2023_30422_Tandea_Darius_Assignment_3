package Presentation;

import BusinessLogic.GenBL;
import Model.Order;
import Util.Colors;
import Util.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

/**
 * This Class is the GUI for adding an order.
 */
public class AddOrderForm extends JFrame {
    JLabel lblClientId = new JLabel("Client ID");
    JLabel lblProductId = new JLabel("Product ID");
    JLabel lblQuantity = new JLabel("Quantity");

    JTextField txtClientId = new JTextField();
    JTextField txtProductId = new JTextField();
    JTextField txtQuantity = new JTextField();

    JButton btnAddOrder = new JButton("Make Order");

    /**
     * This is the constructor of the class.
     * @param orderBL is the business logic for the order.
     */
    public AddOrderForm(GenBL<Order> orderBL) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 250);
        this.setLayout(new FlowLayout());
        this.setTitle("Add Order");
        this.setBackground(Colors.background);

        lblClientId.setPreferredSize(Dimensions.labelDimension);
        lblProductId.setPreferredSize(Dimensions.labelDimension);
        lblQuantity.setPreferredSize(Dimensions.labelDimension);

        txtClientId.setPreferredSize(Dimensions.fieldDimension);
        txtClientId.setForeground(Color.GRAY);
        txtProductId.setPreferredSize(Dimensions.fieldDimension);
        txtProductId.setForeground(Color.GRAY);
        txtQuantity.setPreferredSize(Dimensions.fieldDimension);
        txtQuantity.setForeground(Color.GRAY);
        
        btnAddOrder.setPreferredSize(Dimensions.buttonDimension);

        add(lblClientId);
        add(txtClientId);
        add(lblProductId);
        add(txtProductId);
        add(lblQuantity);
        add(txtQuantity);
        add(btnAddOrder);

        btnAddOrder.addActionListener(e -> {
            //input validation
            if (txtClientId.getText().equals("") || txtProductId.getText().equals("") || txtQuantity.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all the fields!");

            }else if(!txtClientId.getText().matches("[0-9]+") || !txtProductId.getText().matches("[0-9]+") || !txtQuantity.getText().matches("[0-9]+")){
                JOptionPane.showMessageDialog(null, "Please fill in the fields with numbers!");

            }else {
                int clientId = Integer.parseInt(txtClientId.getText());
                int productId = Integer.parseInt(txtProductId.getText());
                int quantity = Integer.parseInt(txtQuantity.getText());
                Date date = new Date(System.currentTimeMillis());
                if (clientId < 0 || productId < 0 || quantity < 0) {
                    JOptionPane.showMessageDialog(null, "Please fill in the fields with positive numbers!");
                    return;
                }

                int id = orderBL.returnMaxId()+1;

                Order order = new Order(id, date, clientId, productId, quantity);

                if (orderBL.insertInDatabase(order)) {
                    JOptionPane.showMessageDialog(null, "Order added successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Order could not be added!");
                }
            }
            resetFields();
            this.dispose();
        });

        txtClientId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(txtClientId.getText().equals("Client ID")){
                    txtClientId.setText("");
                    txtClientId.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(txtClientId.getText().equals("")){
                    txtClientId.setText("Client ID");
                    txtClientId.setForeground(Color.GRAY);
                }
            }
        });

        txtProductId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(txtProductId.getText().equals("Product ID")){
                    txtProductId.setText("");
                    txtProductId.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(txtProductId.getText().equals("")){
                    txtProductId.setText("Product ID");
                    txtProductId.setForeground(Color.GRAY);
                }
            }
        });

        txtQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(txtQuantity.getText().equals("Quantity")){
                    txtQuantity.setText("");
                    txtQuantity.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(txtQuantity.getText().equals("")){
                    txtQuantity.setText("Quantity");
                    txtQuantity.setForeground(Color.GRAY);
                }
            }
        });

    }

    /**
     * This method resets the fields of the form.
     */
    public void resetFields(){
        txtClientId.setText("Client ID");
        txtClientId.setForeground(Color.GRAY);
        txtProductId.setText("Product ID");
        txtProductId.setForeground(Color.GRAY);
        txtQuantity.setText("Quantity");
        txtQuantity.setForeground(Color.GRAY);
    }


}
