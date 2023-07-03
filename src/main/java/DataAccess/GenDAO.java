package DataAccess;

import java.lang.reflect.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Generic DAO class
 * @param <T> Class type of the object
 */
public class GenDAO <T> {
    private static class Database{
        private static final String URL = "jdbc:postgresql://localhost:5432/assign";
        private static final String USER = "postgres";
        private static final String PASSWORD = "darius2002";

        private static Connection connection = null;

        protected static void makeConnection() {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error connecting to database");
            }
        }

        public static boolean executeUpdate(String query) {
            System.out.println(query);
            makeConnection();
            try {
                connection.createStatement().executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error executing update query");
                return false;
            } finally {
                closeConnection();
            }
            return true;
        }


        public static Object returnObject(String query, Class<?> type) {
            makeConnection();
            Object obj;
            try {
                ResultSet resultSet = connection.createStatement().executeQuery(query);
                resultSet.next();
                obj = type.getDeclaredConstructor().newInstance();
                for (int i = 0; i < type.getDeclaredFields().length; i++) {
                    type.getDeclaredFields()[i].setAccessible(true); // Make the field accessible
                    type.getDeclaredFields()[i].set(obj, resultSet.getObject(i + 1));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error connecting to the database", e);
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            return obj;
        }

        public static int returnMaxId(Class<?> type) {
            makeConnection();
            int id = 0;
            try {
                String tableName = "\"" + type.getSimpleName().toLowerCase() + "\"";
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT MAX(id) FROM " + tableName);
                if (resultSet == null) {
                    return 0;
                }
                resultSet.next();
                id = resultSet.getInt(1);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("There are no " + type.getSimpleName().toLowerCase() + "s in the database");
                return 0;
            }
            return id;
        }

        public static boolean deleteFromDatabase(String query) {
            System.out.println(query);
            makeConnection();
            try {
                connection.createStatement().executeUpdate(query);
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error connecting to the database", e);
            } finally {
                closeConnection();
            }
        }

        public static ArrayList<ArrayList<Object>> returnAllFromDatabase(String query) {
            makeConnection();
            ArrayList<ArrayList<Object>> data;
            try {
                System.out.println(query);
                data = new ArrayList<>();
                try (ResultSet resultSet = connection.createStatement().executeQuery(query)) {
                    int i = 0;
                    while (resultSet.next()) {
                        data.add(new ArrayList<>());
                        for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
                            data.get(i).add(resultSet.getObject(j));
                        }
                        i++;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error connecting to the database", e);
            }
            return data;
        }

        private static void closeConnection() {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static int returnStock(String query) {
            System.out.println(query);
            makeConnection();
            int stock = 0;
            try {
                ResultSet resultSet = connection.createStatement().executeQuery(query);
                resultSet.next();
                stock = resultSet.getInt("stock");
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error connecting to the database", e);
            }
            return stock;
        }
    }
    Class <T> type;

    /**
     * Constructor
     * @param type Class type of the object
     */
    public GenDAO(Class<T> type) {
        this.type = type;
    }

    /**
     * inserts the object in the database
     * @param object Object to be inserted in the database
     */
    public boolean insertInDatabase(Object object) {
        String query = "INSERT INTO \"" + type.getSimpleName().toLowerCase() + "\" VALUES(";
        Field[] fields = type.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                Object value = f.get(object);
                if (value instanceof String) {
                    query += "'" + value + "',";
                } else if (value instanceof Date) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format((Date) value);
                    query += "'" + formattedDate + "',";
                } else {
                    query += value + ",";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        query = query.substring(0, query.length() - 1);
        query += ");";
        return Database.executeUpdate(query);
    }


    /**
     * Returns the object from the database
     * @param id int id of the object to be returned from the database
     * @return Object the object from the database
     */
    public Object returnObjectById(int id){
        String query = "SELECT * FROM \""+type.getSimpleName().toLowerCase()+"\" WHERE id = "+id+";";
        return Database.returnObject(query,type);
    }

    /**
     * Returns the maximum id from the database
     * @return int the maximum id from the database
     */
    public int returnMaxId(){
        return Database.returnMaxId(type);
    }

    /**
     * Deletes the object from the database
     * @param id int id of the object to be deleted from the database
     */
    public boolean deleteFromDatabase(int id){
        String query = "DELETE FROM \""+type.getSimpleName().toLowerCase()+"\" WHERE id = "+id+";";
        return Database.deleteFromDatabase(query);
    }

    /**
     * Updates the object in the database
     * @param id int id of the object to be updated in the database
     * @param object Object the object to be updated in the database
     */
    public boolean updateInDatabase(int id, Object object) {
        String query = "UPDATE \"" + type.getSimpleName().toLowerCase() + "\" SET ";
        Field[] fields = type.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                Object value = f.get(object);
                if (value instanceof String) {
                    query += f.getName() + " = '" + value + "',";
                }else if (value instanceof Date) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format((Date) value);
                    query += f.getName() + " = '" + formattedDate + "',";
                } else {
                    query += f.getName() + " = " + value + ",";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        query = query.substring(0, query.length() - 1);
        query += " WHERE id = " + id + ";";
        return Database.executeUpdate(query);
    }


    /**
     * Returns all data objects from the database
     * @return ArrayList<ArrayList<Object>>
     */
    public ArrayList<ArrayList<Object>> returnAllDataObjects(){
        String query = "SELECT * FROM \""+type.getSimpleName().toLowerCase()+"\";";
        return Database.returnAllFromDatabase(query);
    }

    public int stockOfProduct(int id){
        String query = "SELECT stock FROM \"product\" WHERE id = "+id+";";
        return Database.returnStock(query);
    }

    public boolean updateStock(int pId, int quantity) {
        int stock = Database.returnStock("SELECT stock FROM \"product\" WHERE id = "+pId+";");
        int newStock = stock-quantity;
        String query = "UPDATE \"product\" SET stock = "+newStock+" WHERE id = "+pId+";";
        System.out.println(query);
        return Database.executeUpdate(query);
    }


}
