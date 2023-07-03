package Presentation;

import BusinessLogic.GenBL;
import Model.Client;
import Util.Colors;

import javax.swing.*;

/**
 * This Class is the GUI for the Client Panel.
 * It contains the buttons for adding, removing, editing and viewing all clients.
 */
public class ClientPanel extends JPanel {
    JButton btnAddClient;
    JButton btnEditClient;
    JButton btnDeleteClient;
    JButton btnViewAllClients;

    GenBL<Client> clientBL;

    AddClientForm addClientForm;
    EditClientForm editClientForm;
    DeleteClientForm deleteClientForm;
    ViewFrame<Client> clientTable;

    boolean initialized = false;

    public ClientPanel() {
        this.setSize(500, 500);

        btnAddClient = new JButton("Add Client");
        btnEditClient = new JButton("Edit Client");
        btnDeleteClient = new JButton("Remove Client");
        btnViewAllClients = new JButton("View All Clients");

        add(btnAddClient);
        add(btnDeleteClient);
        add(btnEditClient);
        add(btnViewAllClients);

        btnAddClient.setBackground(Colors.buttonDefault);
        btnDeleteClient.setBackground(Colors.buttonDefault);
        btnEditClient.setBackground(Colors.buttonDefault);
        btnViewAllClients.setBackground(Colors.buttonDefault);

        // Hover effect
        btnAddClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddClient.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddClient.setBackground(Colors.buttonDefault);
            }
        });

        btnDeleteClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteClient.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteClient.setBackground(Colors.buttonDefault);
            }
        });

        btnEditClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditClient.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditClient.setBackground(Colors.buttonDefault);
            }
        });

        btnViewAllClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllClients.setBackground(Colors.buttonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewAllClients.setBackground(Colors.buttonDefault);
            }
        });




        btnAddClient.addActionListener(e -> {
            addClientForm.setVisible(true);
            editClientForm.setVisible(false);
            deleteClientForm.setVisible(false);
        });

        btnEditClient.addActionListener(e -> {
            addClientForm.setVisible(false);
            editClientForm.setVisible(true);
            deleteClientForm.setVisible(false);
        });

        btnDeleteClient.addActionListener(e -> {
            addClientForm.setVisible(false);
            editClientForm.setVisible(false);
            deleteClientForm.setVisible(true);
        });

        btnViewAllClients.addActionListener(e -> {
            clientTable.dispose();
            clientTable=new ViewFrame<Client>(Client.class, clientBL.returnAllDataObjects(), clientBL.getFieldName());
            clientTable.setVisible(true);
        });


        setVisible(true);
    }

    protected synchronized void initialize() {
        if(!initialized){
            initialized = true;
            clientBL = new GenBL<>(Client.class);
            addClientForm = new AddClientForm(clientBL);
            editClientForm = new EditClientForm(clientBL);
            deleteClientForm = new DeleteClientForm(clientBL);
            clientTable = new ViewFrame<Client>(Client.class, clientBL.returnAllDataObjects(), clientBL.getFieldName());

            clientTable.setVisible(false);
            addClientForm.setVisible(false);
            editClientForm.setVisible(false);
            deleteClientForm.setVisible(false);
        }
    }


}
