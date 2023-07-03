package Presentation;

import BusinessLogic.GenBL;
import Model.Order;
import Util.Colors;
import Util.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

/**
 * This Class is the GUI for editing an order.
 */
public class EditOrderForm extends JFrame {
    JLabel lblOrderID = new JLabel("Order ID");
    JLabel lblClientID = new JLabel("Client ID");
    JLabel lblProductID = new JLabel("Product ID");
    JLabel lblQuantity = new JLabel("Quantity");

    JTextField txtOrderID = new JTextField();
    JTextField txtClientID = new JTextField();
    JTextField txtProductID = new JTextField();
    JTextField txtQuantity = new JTextField();

    JButton btnEditOrder = new JButton("Save Changes");

    /**
     * This is the constructor of the class.
     * @param orderBL is the Business Logic for the Order class.
     */
    public EditOrderForm(GenBL<Order> orderBL){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 500);
        this.setLayout(new FlowLayout());
        this.setTitle("Edit Order");
        this.setBackground(Colors.background);

        lblOrderID.setPreferredSize(Dimensions.labelDimension);
        lblClientID.setPreferredSize(Dimensions.labelDimension);
        lblProductID.setPreferredSize(Dimensions.labelDimension);
        lblQuantity.setPreferredSize(Dimensions.labelDimension);

        txtOrderID.setPreferredSize(Dimensions.fieldDimension);
        txtClientID.setPreferredSize(Dimensions.fieldDimension);
        txtProductID.setPreferredSize(Dimensions.fieldDimension);
        txtQuantity.setPreferredSize(Dimensions.fieldDimension);

        btnEditOrder.setPreferredSize(Dimensions.buttonDimension);

        add(lblOrderID);
        add(txtOrderID);
        add(lblClientID);
        add(txtClientID);
        add(lblProductID);
        add(txtProductID);
        add(lblQuantity);
        add(txtQuantity);
        add(btnEditOrder);

        btnEditOrder.addActionListener(e -> {

            if(txtOrderID.getText().equals("") || txtOrderID.getText().equals("Order ID") || !txtOrderID.getText().matches("[0-9]+")){
                JOptionPane.showMessageDialog(null, "Please fill in the Order ID field with a number!");
                return;
            }

            int orderID = Integer.parseInt(txtOrderID.getText());

            Order order = (Order) orderBL.returnObjectById(orderID);

            if (order == null) {
                JOptionPane.showMessageDialog(null, "Order with ID " + orderID + " does not exist!");
                return;
            }


            if(!txtClientID.getText().equals("") && !txtClientID.getText().equals("Client ID") && txtClientID.getText().matches("[0-9]+")){
                order.setC_id(Integer.parseInt(txtClientID.getText()));
            }
            if (!txtProductID.getText().equals("") && !txtProductID.getText().equals("Product ID") && txtProductID.getText().matches("[0-9]+")){
                order.setP_id(Integer.parseInt(txtProductID.getText()));
            }
            if (!txtQuantity.getText().equals("") && !txtQuantity.getText().equals("Quantity") && txtQuantity.getText().matches("[0-9]+")){
                order.setQuantity(Integer.parseInt(txtQuantity.getText()));
            }


            if (orderBL.updateInDatabase(order, order.getId())) {
                JOptionPane.showMessageDialog(null, "Order edited successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Order could not be edited!");
            }
        });
    }
}
