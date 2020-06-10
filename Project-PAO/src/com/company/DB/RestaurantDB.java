package com.company.DB;

import com.company.Locations.RestaurantLocation;

import java.sql.*;

public class RestaurantDB {

    public RestaurantDB() {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String createSql = "CREATE TABLE IF NOT EXISTS restaurant (id int PRIMARY KEY AUTO_INCREMENT, name varchar(45)," +
                    " city varchar(45), country varchar(45), maxcapacity int, nroftables int, nrofseatsattable int, hascandybar varchar(45), pricepermenu int)";

            stmt.execute(createSql);
            stmt.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getData() {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "SELECT * FROM restaurant";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString(2));
            }

            stmt.close();
            rs.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RestaurantLocation getDataById(int id) {
        RestaurantLocation restaurantLocation = new RestaurantLocation();
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "SELECT * FROM restaurant WHERE id=?";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String name = rs.getString(2);
                String city = rs.getString(3);
                String country = rs.getString(4);
                int maxCapacity = rs.getInt(5);
                int nrOfTables = rs.getInt(6);
                int nrOfSeatsAtTable = rs.getInt(7);
                String hasCandyBar = rs.getString(8);
                int pricePerMenu = rs.getInt(9);
                restaurantLocation.setName(name);
                restaurantLocation.setCity(city);
                restaurantLocation.setCountry(country);
                restaurantLocation.setMaxCapacity(maxCapacity);
                restaurantLocation.setNrOfTables(nrOfTables);
                restaurantLocation.setNrOfSeatsAtTable(nrOfSeatsAtTable);
                restaurantLocation.setHasCandyBar(Boolean.parseBoolean(hasCandyBar));
                restaurantLocation.setPricePerMenu(pricePerMenu);
            }
            preparedStatement.close();
            rs.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantLocation;
    }

    public void insert(RestaurantLocation restaurantLocation) throws SQLException {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "INSERT INTO restaurant (name, city, country, maxcapacity, nroftables, nrofseatsattable, hascandybar, pricepermenu) " +
                    "VALUES ('" + restaurantLocation.getName() + "', '" + restaurantLocation.getCity() +"', '"
                    + restaurantLocation.getCountry() +"', " + restaurantLocation.getMaxCapacity() + ", " + restaurantLocation.getNrOfTables() + ", " +
                    + restaurantLocation.getNrOfSeatsAtTable() + " ,'" + restaurantLocation.isHasCandyBar() +"', " + restaurantLocation.getPricePerMenu() + ")";

            stmt.executeUpdate(sql);
            stmt.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateName(String name, int id) {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "UPDATE restaurant SET name=? WHERE id=?";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            dbConnection.close();
        } catch (SQLException e) {

        }
        System.out.println("Updated entry " + id);
    }

    public void deleteById(int id) {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "DELETE FROM restaurant WHERE id=?";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            dbConnection.close();
        } catch (SQLException e) {

        }
        System.out.println("Deleted entry " + id);
    }
}
