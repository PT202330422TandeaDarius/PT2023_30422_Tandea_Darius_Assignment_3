package Model;

import java.util.ArrayList;
/**
 * The Client class represents a client in the Orders Management system.
 * It stores information about the client, including their ID, name, address,
 * email, and phone number.
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;

    /**
     * Constructs a new Client object with the specified attributes.
     *
     * @param id      the ID of the client
     * @param name    the name of the client
     * @param address the address of the client
     * @param email   the email of the client
     * @param phone   the phone number of the client
     */
    public Client(int id, String name, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Default constructor for the Client class.
     */
    public Client() {
    }

    /**
     * Constructs a new Client object using an ArrayList of objects.
     * The ArrayList should contain the ID, name, address, email, and phone
     * in the same order.
     *
     * @param objects an ArrayList of objects containing the client attributes
     * @throws IllegalArgumentException if the ArrayList size is not 5 or if any value is null
     */
    public Client(ArrayList<Object> objects) throws IllegalArgumentException {
        if (objects != null && objects.size() == 5) {
            this.id = (Integer) objects.get(0);
            this.name = (String) objects.get(1);
            this.address = (String) objects.get(2);
            this.email = (String) objects.get(3);
            this.phone = (String) objects.get(4);
        } else {
            throw new IllegalArgumentException("Invalid ArrayList size or null values, expected an ArrayList of 5 elements.");
        }
    }

    // Getters and Setters for the client attributes

    /**
     * Retrieves the ID of the client.
     *
     * @return the ID of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the client.
     *
     * @param id the ID of the client
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name the name of the client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the address of the client.
     *
     * @return the address of the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the client.
     *
     * @param address the address of the client
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the email of the client.
     *
     * @return the email of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the client.
     *
     * @param email the email of the client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the phone number of the client.
     *
     * @return the phone number of the client
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the client.
     *
     * @param phone the phone number of the client
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
