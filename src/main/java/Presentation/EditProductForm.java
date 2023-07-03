package Presentation;

import BusinessLogic.GenBL;
import Model.Product;
import Util.Colors;
import Util.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * This Class is the GUI for editing a product.
 */
public class EditProductForm extends JFrame {
    JLabel lblId = new JLabel("ID");
    JLabel lblName = new JLabel("Name");
    JLabel lblStock = new JLabel("Stock");
    JLabel lblPrice = new JLabel("Price");

    JTextField txtId = new JTextField("Id", 20);
    JTextField txtName = new JTextField("Name", 20);
    JTextField txtStock = new JTextField("Stock", 20);
    JTextField txtPrice = new JTextField("Price", 20);

    JButton btnEditProduct = new JButton("Edit Product");

    /**
     * This is the constructor of the class.
     * @param productBL is the business logic for the product.
     */
    public EditProductForm(GenBL<Product> productBL) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 400);
        this.setLayout(new FlowLayout());
        this.setTitle("Edit Product");
        this.setBackground(Colors.background);

        lblId.setPreferredSize(Dimensions.labelDimension);
        lblName.setPreferredSize(Dimensions.labelDimension);
        lblStock.setPreferredSize(Dimensions.labelDimension);
        lblPrice.setPreferredSize(Dimensions.labelDimension);

        txtId.setPreferredSize(Dimensions.fieldDimension);
        txtName.setPreferredSize(Dimensions.fieldDimension);
        txtStock.setPreferredSize(Dimensions.fieldDimension);
        txtPrice.setPreferredSize(Dimensions.fieldDimension);

        btnEditProduct.setPreferredSize(Dimensions.buttonDimension);

        add(lblId);
        add(txtId);
        add(lblName);
        add(txtName);
        add(lblStock);
        add(txtStock);
        add(lblPrice);
        add(txtPrice);
        add(btnEditProduct);

        btnEditProduct.addActionListener(e -> {

            if(txtId.getText().equals("") || txtId.getText().equals("ID") || !txtId.getText().matches("[0-9]+")){
                JOptionPane.showMessageDialog(null, "Please fill in the ID field with a number!");
                return;
            }

            int id = Integer.parseInt(txtId.getText());

            Product product = (Product) productBL.returnObjectById(id);

            if(product == null){
                JOptionPane.showMessageDialog(null, "Product with ID " + id + " does not exist!");
                return;
            }

            if(!Objects.equals(txtName.getText(), "Name") && !Objects.equals(txtName.getText(), ""))
                product.setName(txtName.getText());
            if(txtStock.getText().matches("[0-9]+") && Integer.parseInt(txtStock.getText()) >= 0)
                product.setStock(Integer.parseInt(txtStock.getText()));
            if(txtPrice.getText().matches("[0-9]+\\.[0-9]+?") || txtPrice.getText().matches("[0-9]+"))
                product.setPrice(Double.parseDouble(txtPrice.getText()));


            if(productBL.updateInDatabase(product,id)){
                JOptionPane.showMessageDialog(null, "Product updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error updating product!");
            }
        });

    }
}
