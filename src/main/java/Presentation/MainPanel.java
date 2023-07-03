package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Util.Colors;

public class MainPanel extends JPanel{
    JButton btnProduct;
    JButton btnOrder;
    JButton btnClient;

    ClientPanel clientPanel;
    ProductPanel productPanel;
    OrderPanel orderPanel;

    public MainPanel(){
        this.setLayout(null);
        this.setSize(600, 700);
        this.setBackground(Colors.background);

        btnProduct = new JButton("Product");
        btnOrder = new JButton("Order");
        btnClient = new JButton("Client");

        btnProduct.setBounds(200, 100, 200, 50);
        btnOrder.setBounds(200, 200, 200, 50);
        btnClient.setBounds(200, 300, 200, 50);

        btnProduct.setBackground(Colors.buttonDefault);
        btnOrder.setBackground(Colors.buttonDefault);
        btnClient.setBackground(Colors.buttonDefault);

        add(btnProduct);
        add(btnOrder);
        add(btnClient);

        clientPanel = new ClientPanel();
        productPanel = new ProductPanel();
        orderPanel = new OrderPanel();

        btnProduct.addActionListener(e -> {
            clientPanel.setVisible(false);
            productPanel.setVisible(true);
            orderPanel.setVisible(false);

            productPanel.initialize();

            btnProduct.setEnabled(false);
            btnOrder.setEnabled(true);
            btnClient.setEnabled(true);

            btnProduct.setBackground(Colors.buttonInactive);
            btnOrder.setBackground(Colors.buttonDefault);
            btnClient.setBackground(Colors.buttonDefault);
        });

        btnOrder.addActionListener(e -> {
            clientPanel.setVisible(false);
            productPanel.setVisible(false);
            orderPanel.setVisible(true);

            orderPanel.initialize();

            btnOrder.setEnabled(false);
            btnProduct.setEnabled(true);
            btnClient.setEnabled(true);

            btnOrder.setBackground(Colors.buttonInactive);
            btnProduct.setBackground(Colors.buttonDefault);
            btnClient.setBackground(Colors.buttonDefault);

        });

        btnClient.addActionListener(e -> {
            clientPanel.setVisible(true);
            productPanel.setVisible(false);
            orderPanel.setVisible(false);

            clientPanel.initialize();

            btnClient.setEnabled(false);
            btnProduct.setEnabled(true);
            btnOrder.setEnabled(true);

            btnClient.setBackground(Colors.buttonInactive);
            btnProduct.setBackground(Colors.buttonDefault);
            btnOrder.setBackground(Colors.buttonDefault);
        });

        add(clientPanel);
        add(productPanel);
        add(orderPanel);

        clientPanel.setBackground(Colors.background);
        productPanel.setBackground(Colors.background);
        orderPanel.setBackground(Colors.background);

        clientPanel.setBounds(0, 400, 600, 300);
        productPanel.setBounds(0, 400, 600, 300);
        orderPanel.setBounds(0, 400, 600, 300);

        clientPanel.setVisible(false);
        productPanel.setVisible(false);
        orderPanel.setVisible(false);

        btnClient.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(!btnClient.isEnabled())
                    btnClient.setBackground(Colors.buttonInactive);
                else
                    btnClient.setBackground(Colors.buttonClicked);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(btnClient.isEnabled())
                    btnClient.setBackground(Colors.buttonHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(btnClient.isEnabled())
                    btnClient.setBackground(Colors.buttonDefault);
            }
        });

        btnProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(!btnProduct.isEnabled())
                    btnProduct.setBackground(Colors.buttonInactive);
                else
                    btnProduct.setBackground(Colors.buttonClicked);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(btnProduct.isEnabled())
                    btnProduct.setBackground(Colors.buttonHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(btnProduct.isEnabled())
                    btnProduct.setBackground(Colors.buttonDefault);
            }
        });

        btnOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(!btnOrder.isEnabled())
                    btnOrder.setBackground(Colors.buttonInactive);
                else
                    btnOrder.setBackground(Colors.buttonClicked);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(btnOrder.isEnabled())
                    btnOrder.setBackground(Colors.buttonHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(btnOrder.isEnabled())
                    btnOrder.setBackground(Colors.buttonDefault);
            }
        });

        setVisible(true);
    }

}
