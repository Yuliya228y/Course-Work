package Database;

import Classes.Pet;
import Classes.Shop;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends Config {

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;
    private static String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return connection;
    }

    public static void insertInto(Pet pet){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "INSERT INTO pets (ID, Name, Cost, Passport, Breed) " +
                    "VALUES(" + pet.getId() + ", '" + pet.getName() + "', " + pet.getCost() + ", " + pet.getPassport() + ", '" + pet.getBreed() + "');";
            stmt.executeUpdate(query);
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public static void insertIntoShop(Shop shop){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "INSERT INTO shopInfo (ID, Name, Balance) " +
                    "VALUES (" + shop.getID() + ", '" + shop.getShop() + "', " + shop.getBalance() + ");";
            stmt.executeUpdate(query);
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public static List<Pet> selectFromPet(){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "SELECT * FROM pets;";
            rs = stmt.executeQuery(query);
            while (rs.next()){
                Pet pet = new Pet(Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(1)),
                        rs.getString(2), rs.getString(5),
                        intToBoolean(Integer.parseInt(rs.getString(4))));
                Pet.add(pet);
            }

        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return Pet.petList;
    }

    public static void deleteFromPets(Pet pet){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "DELETE FROM pets WHERE ID = " + pet.getId() + ";";
            stmt.executeUpdate(query);
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public static void updateShopInfo(Shop shop){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "UPDATE shopinfo\n" +
                    "SET Balance = " + shop.getBalance() + "\n" +
                    "WHERE ID = " + shop.getID() + ";";
            stmt.executeUpdate(query);
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public static Shop selectFromShop(){
        Shop shop = new Shop();
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "SELECT * FROM shopinfo WHERE ID = 1;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                shop = new Shop(Integer.parseInt(rs.getString(1)),
                        Integer.parseInt(rs.getString(3)), rs.getString(2));
            }
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return shop;
    }

    public static Shop selectFromShopWhere(Shop shop){
        try {
            connection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            stmt = connection.createStatement();
            String query = "SELECT * FROM shopinfo WHERE ID = " + shop.getID() + ";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                shop = new Shop(Integer.parseInt(rs.getString(1)),
                        Integer.parseInt(rs.getString(3)), rs.getString(2));
            }
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return shop;
    }



    private static boolean intToBoolean(int input) {
        if((input==0)||(input==1)) {
            return input!=0;
        }else {
            throw new IllegalArgumentException("Входное значение может быть равно только 0 или 1 !");
        }
    }

}
