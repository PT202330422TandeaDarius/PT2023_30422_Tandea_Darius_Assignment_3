package Presentation;

import BusinessLogic.GenBL;
import Model.Client;
import Util.Colors;
import Util.Dimensions;

import javax.swing.*;
import java.awt.*;

/**
 * This Class is the GUI for editing a client.
 */
public class EditClientForm extends JFrame {

    JLabel lblId = new JLabel("ID");
    JLabel lblName = new JLabel("Name");
    JLabel lblAddress = new JLabel("Address");
    JLabel lblEmail = new JLabel("Email");
    JLabel lblPhone = new JLabel("Phone");

    JTextField txtId = new JTextField("ID", 20);
    JTextField txtName = new JTextField("Full Name",20);
    JTextField txtAddress = new JTextField("Address", 20);
    JTextField txtEmail = new JTextField("Email",20);
    JTextField txtPhone = new JTextField("Phone",20);

    JButton btnEditClient;

    /**
     * This is the constructor of the class.
     * @param clientBL is the business logic for the client.
     */
    public EditClientForm(GenBL<Client> clientBL){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 400);

        this.setLayout(new FlowLayout());

        this.setTitle("Edit Client");
        this.setBackground(Colors.background);


        btnEditClient = new JButton("Edit Client");

        lblId.setPreferredSize(Dimensions.labelDimension);
        lblName.setPreferredSize(Dimensions.labelDimension);
        lblAddress.setPreferredSize(Dimensions.labelDimension);
        lblEmail.setPreferredSize(Dimensions.labelDimension);
        lblPhone.setPreferredSize(Dimensions.labelDimension);

        txtAddress.setPreferredSize(Dimensions.fieldDimension);
        txtName.setPreferredSize(Dimensions.fieldDimension);
        txtAddress.setPreferredSize(Dimensions.fieldDimension);
        txtEmail.setPreferredSize(Dimensions.fieldDimension);
        txtPhone.setPreferredSize(Dimensions.fieldDimension);

        btnEditClient.setPreferredSize(Dimensions.buttonDimension);

        add(lblId);
        add(txtId);
        add(lblName);
        add(txtName);
        add(lblAddress);
        add(txtAddress);
        add(lblEmail);
        add(txtEmail);

        add(lblPhone);
        add(txtPhone);

        add(btnEditClient);

        btnEditClient.addActionListener(e -> {

            if(txtId.getText().equals("") || txtId.getText().equals("ID") || !txtId.getText().matches("[0-9]+")){
                JOptionPane.showMessageDialog(null, "Please fill in the ID field with a number!");
                return;
            }


            Client client = (Client) clientBL.returnObjectById(Integer.parseInt(txtId.getText()));

            if(client == null){
                JOptionPane.showMessageDialog(null, "Client not found!");
                return;
            }

            if(!txtName.getText().equals("") && !txtName.getText().equals("Full Name")){
                client.setName(txtName.getText());
            }

            if(!txtAddress.getText().equals("") && !txtAddress.getText().equals("Address")){
                client.setAddress(txtAddress.getText());
            }

            if(!txtEmail.getText().equals("") && !txtEmail.getText().equals("Email")){
                if(txtEmail.getText().contains("@") && txtEmail.getText().contains(".")){
                    client.setEmail(txtEmail.getText());
                } else{
                    JOptionPane.showMessageDialog(null, "Please fill in a valid email!");
                    return;
                }
            }

            if(!txtPhone.getText().equals("") && !txtPhone.getText().equals("Phone") && txtPhone.getText().matches("[0-9]+")){
                client.setPhone(txtPhone.getText());
            }


            if(clientBL.updateInDatabase(client, client.getId())){
                JOptionPane.showMessageDialog(null, "Client updated successfully!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Client not found!");
            }
        });

        btnEditClient.setBackground(Colors.buttonDefault);
        txtId.setBackground(Colors.textField);
        txtName.setBackground(Colors.textField);
        txtAddress.setBackground(Colors.textField);
        txtEmail.setBackground(Colors.textField);
        txtPhone.setBackground(Colors.textField);
    }
}
