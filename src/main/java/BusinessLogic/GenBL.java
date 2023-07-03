package BusinessLogic;

import DataAccess.GenDAO;
import Model.Order;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class represents a generic business logic class that provides common operations for a specific type of object.
 *
 * @param <T> the type of object to perform operations on
 */
public class GenBL<T> {

    private Class<T> type;
    private GenDAO<T> genDAO;

    /**
     * Constructs a GenBL object with the specified type.
     *
     * @param type the class type of the object
     */
    public GenBL(Class<T> type) {
        this.type = type;
        genDAO = new GenDAO<T>(type);
    }

    /**
     * Retrieves the names of the fields in the object's class.
     *
     * @return an array of field names
     */
    public String[] getFieldName() {
        String[] fieldNames = new String[0];
        Field[] fields = type.getDeclaredFields();
        fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * Returns all the data objects of the specified type from the database.
     *
     * @return a list of lists representing the data objects
     */
    public ArrayList<ArrayList<Object>> returnAllDataObjects() {
        genDAO = new GenDAO<T>(type);
        return genDAO.returnAllDataObjects();
    }

    /**
     * Inserts an object into the database.
     *
     * @param object the object to be inserted
     * @return true if the insertion is successful, false otherwise
     */
    public boolean insertInDatabase(Object object) {
        if (type.getSimpleName().equals("Order")) {
            Order order = (Order) object;
            if (order.getQuantity() > 0) {
                if (genDAO.stockOfProduct(order.getP_id()) >= order.getQuantity()) {
                    genDAO.insertInDatabase(order);
                    return genDAO.updateStock(order.getP_id(), order.getQuantity());
                }
            }
            return false;
        } else {
            return genDAO.insertInDatabase(object);
        }
    }

    /**
     * Updates an object in the database with the specified ID.
     *
     * @param object the object to be updated
     * @param id     the ID of the object in the database
     * @return true if the update is successful, false otherwise
     */
    public boolean updateInDatabase(Object object, int id) {
        return genDAO.updateInDatabase(id, object);
    }

    /**
     * Deletes an object from the database with the specified ID.
     *
     * @param id the ID of the object to be deleted
     * @return true if the deletion is successful, false otherwise
     */
    public boolean deleteFromDatabase(int id) {
        return genDAO.deleteFromDatabase(id);
    }

    /**
     * Returns the maximum ID value from the database.
     *
     * @return the maximum ID value
     */
    public int returnMaxId() {
        return genDAO.returnMaxId();
    }

    /**
     * Returns an object from the database with the specified ID.
     *
     * @param id the ID of the object to retrieve
     * @return the retrieved object, or null if not found
     */
    public Object returnObjectById(int id) {
        return genDAO.returnObjectById(id);
    }
}

