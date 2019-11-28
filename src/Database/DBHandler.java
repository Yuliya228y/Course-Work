package Database;

import Classes.Pet;

import java.sql.*;

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

    public void insertInto(Pet pet){
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
}
