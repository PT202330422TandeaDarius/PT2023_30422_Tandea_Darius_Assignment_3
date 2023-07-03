package Model;

import java.util.ArrayList;
import java.util.ArrayList;

/**
 * The Product class represents a product in the Products Management system.
 * It stores information about the product, including its ID, name, stock, and price.
 */
public class Product {
    private int id;
    private String name;
    private int stock;
    private double price;

    /**
     * Constructs a new Product object with the specified attributes.
     *
     * @param id    the ID of the product
     * @param name  the name of the product
     * @param stock the stock quantity of the product
     * @param price the price of the product
     */
    public Product(int id, String name, int stock, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    /**
     * Default constructor for the Product class.
     */
    public Product() {
    }

    /**
     * Constructs a new Product object using an ArrayList of objects.
     * The ArrayList should contain the ID, name, stock, and price in the same order.
     *
     * @param values an ArrayList of objects containing the product attributes
     * @throws IllegalArgumentException if the ArrayList size is not 4 or if any value is null
     */
    public Product(ArrayList<Object> values) throws IllegalArgumentException {
        if (values != null && values.size() == 4) {
            this.id = (Integer) values.get(0);
            this.name = (String) values.get(1);
            this.stock = (Integer) values.get(2);
            this.price = (Double) values.get(3);
        } else {
            throw new IllegalArgumentException("Invalid ArrayList size or null values, expected an ArrayList of 4 elements.");
        }
    }

    // Getters and Setters for the product attributes

    /**
     * Retrieves the ID of the product.
     *
     * @return the ID of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id the ID of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the stock quantity of the product.
     *
     * @return the stock quantity of the product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the product.
     *
     * @param stock the stock quantity of the product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
