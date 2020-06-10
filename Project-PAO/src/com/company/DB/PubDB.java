package com.company.DB;

import com.company.Locations.PubLocation;

import java.sql.*;

public class PubDB {

    public PubDB() {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String createSql = "CREATE TABLE IF NOT EXISTS pub (id int PRIMARY KEY AUTO_INCREMENT, name varchar(45)," +
                    " city varchar(45), country varchar(45), maxcapacity int, nroftables int, nrofseatsattable int, hasbar varchar(45), hasscene varchar(45))";

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
            String sql = "SELECT * FROM pub";

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

    public PubLocation getDataById(int id) {
        PubLocation pubLocation = new PubLocation();
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "SELECT * FROM pub WHERE id=?";

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
                String hasBar = rs.getString(8);
                String hasScene = rs.getString(9);
                pubLocation.setName(name);
                pubLocation.setCity(city);
                pubLocation.setCountry(country);
                pubLocation.setMaxCapacity(maxCapacity);
                pubLocation.setNrOfTables(nrOfTables);
                pubLocation.setNrOfSeatsAtTable(nrOfSeatsAtTable);
                pubLocation.setHasScene(Boolean.parseBoolean(hasBar));
                pubLocation.setHasGames(Boolean.parseBoolean(hasScene));
            }
            preparedStatement.close();
            rs.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pubLocation;
    }

    public void insert(PubLocation pubLocation) throws SQLException {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "INSERT INTO pub (name, city, country, maxcapacity, nroftables, nrofseatsattable, hasbar, hasscene) " +
                    "VALUES ('" + pubLocation.getName() + "', '" + pubLocation.getCity() +"', '"
                    + pubLocation.getCountry() +"', " + pubLocation.getMaxCapacity() + ", " + pubLocation.getNrOfTables() + ", " + pubLocation.getNrOfSeatsAtTable()
                    + " ,'" + pubLocation.isHasGames() +"', '" + pubLocation.isHasScene() + "')";

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
            String sql = "UPDATE pub SET name=? WHERE id=?";

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
            String sql = "DELETE FROM pub WHERE id=?";

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
