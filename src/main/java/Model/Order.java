package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * The Order class represents an order in the Orders Management system.
 * It stores information about the order, including its ID, date, client ID, product ID, and quantity.
 */
public class Order {
    private int id;
    private Date date;
    private int c_id;
    private int p_id;
    private int quantity;

    /**
     * Constructs a new Order object with the specified attributes.
     *
     * @param id       the ID of the order
     * @param date     the date of the order
     * @param c_id     the ID of the client associated with the order
     * @param p_id     the ID of the product associated with the order
     * @param quantity the quantity of the product in the order
     */
    public Order(int id, Date date, int c_id, int p_id, int quantity) {
        this.id = id;
        this.date = date;
        this.c_id = c_id;
        this.p_id = p_id;
        this.quantity = quantity;
    }

    /**
     * Default constructor for the Order class.
     */
    public Order() {
    }

    /**
     * Constructs a new Order object using an ArrayList of objects.
     * The ArrayList should contain the ID, date, client ID, product ID, and quantity
     * in the same order.
     *
     * @param objects an ArrayList of objects containing the order attributes
     * @throws IllegalArgumentException if the ArrayList size is not 5 or if any value is null
     */
    public Order(ArrayList<Object> objects) throws IllegalArgumentException {
        if (objects != null && objects.size() == 5) {
            this.id = (Integer) objects.get(0);
            this.date = (Date) objects.get(1);
            this.c_id = (Integer) objects.get(2);
            this.p_id = (Integer) objects.get(3);
            this.quantity = (Integer) objects.get(4);
        } else {
            throw new IllegalArgumentException("Invalid ArrayList size or null values, expected an ArrayList of 5 elements.");
        }
    }

    // Getters and Setters for the order attributes

    /**
     * Retrieves the ID of the order.
     *
     * @return the ID of the order
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id the ID of the order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the date of the order.
     *
     * @return the date of the order
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the order.
     *
     * @param date the date of the order
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Retrieves the ID of the client associated with the order.
     *
     * @return the ID of the client associated with the order
     */
    public int getC_id() {
        return c_id;
    }

    /**
     * Sets the ID of the client associated with the order.
     *
     * @param c_id the ID of the client associated with the order
     */
    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    /**
     * Retrieves the ID of the product associated with the order.
     *
     * @return the ID of the product associated with the order
     */
    public int getP_id() {
        return p_id;
    }

    /**
     * Sets the ID of the product associated with the order.
     *
     * @param p_id the ID of the product associated with the order
     */
    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    /**
     * Retrieves the quantity of the product in the order.
     *
     * @return the quantity of the product in the order
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the order.
     *
     * @param quantity the quantity of the product in the order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * The Bill class represents a bill for an order in the Orders Management system.
     * It is an immutable class that stores information about the bill, including its ID, client name, product name,
     * quantity, total amount, and time.
     */
    public record Bill(int id, String clientName, String productName, int quantity, double totalAmount, Date time) {
        // The Bill class is an immutable class generated using Java records.
        // It automatically provides constructors, accessors, and other methods.
        // The attribute names are used as constructor parameters and can be accessed using the generated accessors.
    }

}

