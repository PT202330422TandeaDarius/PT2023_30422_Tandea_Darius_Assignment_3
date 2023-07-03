package Presentation;

import BusinessLogic.GenBL;
import Model.Product;
import Util.Colors;
import Util.Dimensions;

import javax.swing.*;
import java.awt.*;

/**
 * This Class is the GUI for adding a product.
 */
public class AddProductForm extends JFrame {
    JLabel lblName = new JLabel("Name");
    JLabel lblStock = new JLabel("Stock");
    JLabel lblPrice = new JLabel("Price");


    JTextField txtName = new JTextField("Name", 20);
    JTextField txtStock = new JTextField("Stock", 20);
    JTextField txtPrice = new JTextField("Price", 20);

    JButton btnAddProduct = new JButton("Add Product");

    /**
     * This is the constructor of the class.
     * @param productBL is the business logic for the product.
     */
    public AddProductForm(GenBL<Product> productBL) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new FlowLayout());
        this.setTitle("Add Product");
        this.setBackground(Colors.background);

        lblName.setPreferredSize(Dimensions.labelDimension);
        lblStock.setPreferredSize(Dimensions.labelDimension);
        lblPrice.setPreferredSize(Dimensions.labelDimension);

        txtName.setPreferredSize(Dimensions.fieldDimension);
        txtName.setForeground(Color.GRAY);
        txtStock.setPreferredSize(Dimensions.fieldDimension);
        txtStock.setForeground(Color.GRAY);
        txtPrice.setPreferredSize(Dimensions.fieldDimension);
        txtPrice.setForeground(Color.GRAY);

        btnAddProduct.setPreferredSize(Dimensions.buttonDimension);

        add(lblName);
        add(txtName);
        add(lblStock);
        add(txtStock);
        add(lblPrice);
        add(txtPrice);
        add(btnAddProduct);

        btnAddProduct.addActionListener(e -> {

            //input validation
            if(txtName.getText().equals("") || txtName.getText().equals("Name") || txtStock.getText().equals("") || txtStock.getText().equals("Stock") || txtPrice.getText().equals("") || txtPrice.getText().equals("Price")){
                JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
            }else if(!txtStock.getText().matches("[0-9]+")){
                JOptionPane.showMessageDialog(null, "Stock must be a number!");
            }else if(Integer.parseInt(txtStock.getText()) < 0){
                JOptionPane.showMessageDialog(null, "Stock must be a positive number!");
            }else if(!txtPrice.getText().matches("[0-9]+(\\.[0-9]+)?")){
                JOptionPane.showMessageDialog(null, "Price must be a number!");
            }else if(Double.parseDouble(txtPrice.getText()) < 0){
                JOptionPane.showMessageDialog(null, "Price must be a positive number!");
            }else {

                int id = productBL.returnMaxId() + 1;
                String name = txtName.getText();
                int stock = Integer.parseInt(txtStock.getText());
                double price = Double.parseDouble(txtPrice.getText());

                Product product = new Product(id, name, stock, price);

                if(productBL.insertInDatabase(product)){
                    JOptionPane.showMessageDialog(null, "Product added successfully!");

                } else {
                    JOptionPane.showMessageDialog(null, "Product could not be added!");
                }
            }
            resetFields();
            this.dispose();
        });

        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(txtName.getText().equals("Name")) {
                    txtName.setText("");
                    txtName.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(txtName.getText().equals("")){
                    txtName.setText("Name");
                    txtName.setForeground(Color.GRAY);
                }
            }
        });

        txtStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(txtStock.getText().equals("Stock")) {
                    txtStock.setText("");
                    txtStock.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(txtStock.getText().equals("")){
                    txtStock.setText("Stock");
                    txtStock.setForeground(Color.GRAY);
                }
            }
        });

        txtPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(txtPrice.getText().equals("Price")) {
                    txtPrice.setText("");
                    txtPrice.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(txtPrice.getText().equals("")){
                    txtPrice.setText("Price");
                    txtPrice.setForeground(Color.GRAY);
                }
            }
        });
    }

    /**
     * This method resets the fields to their default values.
     */
    public void resetFields(){
        txtName.setText("Name");
        txtName.setForeground(Color.GRAY);
        txtStock.setText("Stock");
        txtStock.setForeground(Color.GRAY);
        txtPrice.setText("Price");
        txtPrice.setForeground(Color.GRAY);
    }
}
