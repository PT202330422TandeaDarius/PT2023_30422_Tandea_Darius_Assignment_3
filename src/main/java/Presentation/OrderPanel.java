package Presentation;

import BusinessLogic.GenBL;
import Model.Order;
import Util.Colors;

import javax.swing.*;

/**
 * This Class is the GUI for the Order Panel.
 * It contains the buttons for adding, removing, editing and viewing all orders.
 */
public class OrderPanel extends JPanel {

    JButton btnAddOrder;
    JButton btnRemoveOrder;
    JButton btnEditOrder;
    JButton btnViewAllOrder;

    GenBL<Order> orderBL;

    AddOrderForm addOrderForm;
    DeleteOrderForm removeOrderForm;
    EditOrderForm editOrderForm;
    ViewFrame<Order> orderTable;

    boolean initialized = false;

    public OrderPanel(){
        this.setSize(500, 500);

        btnAddOrder = new JButton("Add Order");
        btnRemoveOrder = new JButton("Remove Order");
        btnEditOrder = new JButton("Edit Order");
        btnViewAllOrder = new JButton("View All Orders");

        add(btnAddOrder);
        add(btnRemoveOrder);
        add(btnEditOrder);
        add(btnViewAllOrder);

        btnAddOrder.setBackground(Colors.buttonDefault);
        btnRemoveOrder.setBackground(Colors.buttonDefault);
        btnEditOrder.setBackground(Colors.buttonDefault);
        btnViewAllOrder.setBackground(Colors.buttonDefault);

        // Hover effect
        btnAddOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddOrder.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddOrder.setBackground(Colors.buttonDefault);
            }
        });

        btnRemoveOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRemoveOrder.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRemoveOrder.setBackground(Colors.buttonDefault);
            }
        });

        btnEditOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditOrder.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditOrder.setBackground(Colors.buttonDefault);
            }
        });

        btnViewAllOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllOrder.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewAllOrder.setBackground(Colors.buttonDefault);
            }
        });

        orderBL = new GenBL<Order>(Order.class);


        //action listener
        btnAddOrder.addActionListener(e->{
            addOrderForm.setVisible(true);
            removeOrderForm.setVisible(false);
            editOrderForm.setVisible(false);
        });

        btnRemoveOrder.addActionListener(e->{
            addOrderForm.setVisible(false);
            removeOrderForm.setVisible(true);
            editOrderForm.setVisible(false);
        });

        btnEditOrder.addActionListener(e->{
            addOrderForm.setVisible(false);
            removeOrderForm.setVisible(false);
            editOrderForm.setVisible(true);
        });

        btnViewAllOrder.addActionListener(e->{
            orderTable.dispose();
            orderTable = new ViewFrame<Order>(Order.class,orderBL.returnAllDataObjects(),orderBL.getFieldName());
            orderTable.setVisible(true);
        });

        setVisible(true);
    }

    protected synchronized void initialize() {
        if(!initialized){
            initialized = true;
            addOrderForm = new AddOrderForm(orderBL);
            removeOrderForm = new DeleteOrderForm(orderBL);
            editOrderForm = new EditOrderForm(orderBL);
            orderTable = new ViewFrame<Order>(Order.class,orderBL.returnAllDataObjects(),orderBL.getFieldName());

            addOrderForm.setVisible(false);
            removeOrderForm.setVisible(false);
            editOrderForm.setVisible(false);
            orderTable.setVisible(false);
        }

    }

}
