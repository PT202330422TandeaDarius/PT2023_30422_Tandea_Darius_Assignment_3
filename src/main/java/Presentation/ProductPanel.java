package Presentation;

import BusinessLogic.GenBL;
import Model.Product;
import Util.Colors;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This Class is the GUI for the Product Panel.
 * It contains the buttons for adding, removing, editing and viewing all products.
 */
public class ProductPanel extends JPanel {

    JButton btnAddProduct;
    JButton btnRemoveProduct;
    JButton btnEditProduct;
    JButton btnViewAllProduct;

    GenBL<Product> productBL;

    AddProductForm addProductForm;
    DeleteProductForm removeProductForm;
    EditProductForm editProductForm;
    ViewFrame<Product> productTable;

    boolean initialized = false;

    public ProductPanel(){
        this.setSize(500, 500);

        productBL = new GenBL<>(Product.class);

        btnAddProduct = new JButton("Add Product");
        btnRemoveProduct = new JButton("Remove Product");
        btnEditProduct = new JButton("Edit Product");
        btnViewAllProduct = new JButton("View All Products");

        add(btnAddProduct);
        add(btnRemoveProduct);
        add(btnEditProduct);
        add(btnViewAllProduct);

        btnAddProduct.setBackground(Colors.buttonDefault);
        btnRemoveProduct.setBackground(Colors.buttonDefault);
        btnEditProduct.setBackground(Colors.buttonDefault);
        btnViewAllProduct.setBackground(Colors.buttonDefault);


        btnAddProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddProduct.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddProduct.setBackground(Colors.buttonDefault);
            }
        });

        btnRemoveProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRemoveProduct.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRemoveProduct.setBackground(Colors.buttonDefault);
            }
        });

        btnEditProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditProduct.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditProduct.setBackground(Colors.buttonDefault);
            }
        });

        btnViewAllProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllProduct.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewAllProduct.setBackground(Colors.buttonDefault);
            }
        });

        btnAddProduct.addActionListener(e->{
            addProductForm.setVisible(true);
            removeProductForm.setVisible(false);
            editProductForm.setVisible(false);
        });

        btnEditProduct.addActionListener(e->{
            addProductForm.setVisible(false);
            removeProductForm.setVisible(false);
            editProductForm.setVisible(true);
        });

        btnRemoveProduct.addActionListener(e->{
            addProductForm.setVisible(false);
            removeProductForm.setVisible(true);
            editProductForm.setVisible(false);
        });

        btnViewAllProduct.addActionListener(e->{
            productTable.dispose();
            productTable = new ViewFrame<Product>(Product.class, productBL.returnAllDataObjects(),productBL.getFieldName());
            productTable.setVisible(true);
        });

        setVisible(true);
    }

    protected void initialize(){
        if(!initialized){
            addProductForm = new AddProductForm(productBL);
            removeProductForm = new DeleteProductForm(productBL);
            editProductForm = new EditProductForm(productBL);
            productTable = new ViewFrame<Product>(Product.class, productBL.returnAllDataObjects(),productBL.getFieldName());
            initialized = true;

            addProductForm.setVisible(false);
            removeProductForm.setVisible(false);
            editProductForm.setVisible(false);
            productTable.setVisible(false);
        }
    }


}
