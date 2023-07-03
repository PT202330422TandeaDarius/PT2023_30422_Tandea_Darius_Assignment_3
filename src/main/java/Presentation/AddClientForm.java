package Presentation;

import javax.swing.*;

import BusinessLogic.GenBL;
import Model.Client;
import Util.Colors;
import Util.Dimensions;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * This Class is the GUI for adding a client.
 */
public class AddClientForm extends JFrame {
    JLabel nameLabel = new JLabel("Name");
    JLabel addressLabel = new JLabel("Address");
    JLabel phoneLabel = new JLabel("Phone");
    JLabel emailLabel = new JLabel("Email");

    JTextField nameField = new JTextField("Full Name", 20);
    JTextField addressField = new JTextField("Address", 20);
    JTextField phoneField = new JTextField("Phone", 20);
    JTextField emailField = new JTextField("E-mail", 20);

    JButton btnAddClient = new JButton("Add Client");

    /**
     * This is the constructor of the class.
     * @param clientBL the business logic for the client
     */
    public AddClientForm(GenBL<Client> clientBL) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new FlowLayout());
        this.setBackground(Colors.background);
        this.setTitle("Add Client");
        this.setBackground(Colors.background);


        nameLabel.setPreferredSize(Dimensions.labelDimension);
        addressLabel.setPreferredSize(Dimensions.labelDimension);
        phoneLabel.setPreferredSize(Dimensions.labelDimension);
        emailLabel.setPreferredSize(Dimensions.labelDimension);

        nameField.setPreferredSize(Dimensions.fieldDimension);
        addressField.setPreferredSize(Dimensions.fieldDimension);
        phoneField.setPreferredSize(Dimensions.fieldDimension);
        emailField.setPreferredSize(Dimensions.fieldDimension);

        btnAddClient.setPreferredSize(Dimensions.buttonDimension);

        btnAddClient.setBackground(Colors.buttonDefault);
        nameField.setBackground(Colors.textField);
        nameField.setForeground(Color.GRAY);
        addressField.setBackground(Colors.textField);
        addressField.setForeground(Color.GRAY);
        phoneField.setBackground(Colors.textField);
        phoneField.setForeground(Color.GRAY);
        emailField.setBackground(Colors.textField);
        emailField.setForeground(Color.GRAY);

        add(nameLabel);
        add(nameField);
        add(addressLabel);
        add(addressField);
        add(phoneLabel);
        add(phoneField);
        add(emailLabel);
        add(emailField);
        add(btnAddClient);

        btnAddClient.addActionListener(e -> {

            if (nameField.getText().equals("") || addressField.getText().equals("") || phoneField.getText().equals("") || emailField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
            } else if (!emailField.getText().contains("@") || !emailField.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email address!");
            } else if (!phoneField.getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number!");
            } else {
                String name = nameField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();
                Client client = new Client(clientBL.returnMaxId() + 1, name, address, phone, email);

                if (clientBL.insertInDatabase(client)) {
                    JOptionPane.showMessageDialog(null, "Client added successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Client could not be added!");
                }
            }
            resetFields();
            this.dispose();
        });

        btnAddClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddClient.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddClient.setBackground(Colors.buttonDefault);
            }
        });

        nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals("Full Name")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().equals("")) {
                    nameField.setText("Full Name");
                    nameField.setForeground(Color.GRAY);
                }
            }
        });

        addressField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (addressField.getText().equals("Address")) {
                    addressField.setText("");
                    addressField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (addressField.getText().equals("")) {
                    addressField.setText("Address");
                    addressField.setForeground(Color.GRAY);
                }
            }
        });

        phoneField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneField.getText().equals("Phone")) {
                    phoneField.setText("");
                    phoneField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (phoneField.getText().equals("")) {
                    phoneField.setText("Phone");
                    phoneField.setForeground(Color.GRAY);
                }
            }
        });

        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("E-mail")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().equals("")) {
                    emailField.setText("E-mail");
                    emailField.setForeground(Color.GRAY);
                }
            }
        });
    }

    /**
     * This method resets the fields of the form.
     */
    public void resetFields() {
    	nameField.setText("Full Name");
    	nameField.setForeground(Color.GRAY);
    	addressField.setText("Address");
    	addressField.setForeground(Color.GRAY);
    	phoneField.setText("Phone");
    	phoneField.setForeground(Color.GRAY);
    	emailField.setText("E-mail");
    	emailField.setForeground(Color.GRAY);
    }


}
