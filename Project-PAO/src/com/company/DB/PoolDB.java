package com.company.DB;

import com.company.Locations.PoolLocation;

import java.sql.*;

public class PoolDB {

    public PoolDB() {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String createSql = "CREATE TABLE IF NOT EXISTS pool (id int PRIMARY KEY AUTO_INCREMENT, name varchar(45)," +
                    " city varchar(45), country varchar(45), maxcapacity int, hasbar varchar(45), poolopenedatnight varchar(45), hasscene varchar(45))";

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
            String sql = "SELECT * FROM pool";

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

    public PoolLocation getDataById(int id) {
        PoolLocation poolLocation = new PoolLocation();
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "SELECT * FROM pool WHERE id=?";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String name = rs.getString(2);
                String city = rs.getString(3);
                String country = rs.getString(4);
                int maxCapacity = rs.getInt(5);
                String hasBar = rs.getString(6);
                String poolOpenedAtNight = rs.getString(7);
                String hasScene = rs.getString(8);
                poolLocation.setName(name);
                poolLocation.setCity(city);
                poolLocation.setCountry(country);
                poolLocation.setMaxCapacity(maxCapacity);
                poolLocation.setHasBar(Boolean.parseBoolean(hasBar));
                poolLocation.setPoolOpenedAtNight(Boolean.parseBoolean(poolOpenedAtNight));
                poolLocation.setHasScene(Boolean.parseBoolean(hasScene));
            }
            preparedStatement.close();
            rs.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return poolLocation;
    }

    public void insert(PoolLocation poolLocation) throws SQLException {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "INSERT INTO pool (name, city, country, maxcapacity, hasbar, poolopenedatnight, hasscene) " +
                    "VALUES ('" + poolLocation.getName() + "', '" + poolLocation.getCity() +"', '"
                    + poolLocation.getCountry() +"', " + poolLocation.getMaxCapacity() + " ,'" + poolLocation.isHasBar() +"', '"
                    + poolLocation.isPoolOpenedAtNight() + "', '" + poolLocation.isHasScene() + "')";

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
            String sql = "UPDATE pool SET name=? WHERE id=?";

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
            String sql = "DELETE FROM pool WHERE id=?";

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
