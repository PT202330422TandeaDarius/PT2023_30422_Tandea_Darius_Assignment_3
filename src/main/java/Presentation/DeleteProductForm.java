package Presentation;

import BusinessLogic.GenBL;
import Model.Order;
import Model.Product;
import Util.Colors;
import Util.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * This Class is the GUI for deleting a product.
 */
public class DeleteProductForm extends JFrame {
    JLabel lblId = new JLabel("Id");
    JTextField txtId = new JTextField("Id", 20);
    JButton btnDeleteProduct = new JButton("Delete Product");

    /**
     * This is the constructor of the class.
     * @param productBL is the business logic for the product.
     */
    public DeleteProductForm(GenBL<Product> productBL) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new FlowLayout());
        this.setTitle("Delete Product");
        this.setBackground(Colors.background);

        lblId.setPreferredSize(Dimensions.labelDimension);
        txtId.setPreferredSize(Dimensions.fieldDimension);
        btnDeleteProduct.setPreferredSize(Dimensions.buttonDimension);


        add(lblId);
        add(txtId);
        add(btnDeleteProduct);

        btnDeleteProduct.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            GenBL<Order> orderBL = new GenBL<>(Order.class);

            // Check if the product is referenced in any order
            boolean productReferenced = false;
            for (ArrayList<Object> order : orderBL.returnAllDataObjects()) {
                if ((int) order.get(3) == id) {
                    productReferenced = true;
                    break;
                }
            }

            if (productReferenced) {
                JOptionPane.showMessageDialog(null, "Product is in an order! \n Cannot delete!");
                return;
            }

            // If the product is not referenced, delete it from the database
            if (productBL.deleteFromDatabase(id)) {
                JOptionPane.showMessageDialog(null, "Product deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error deleting product!");
            }
        });
    }
}
