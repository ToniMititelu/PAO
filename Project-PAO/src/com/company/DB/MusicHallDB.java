package com.company.DB;

import com.company.Locations.MusicHallLocation;

import java.sql.*;

public class MusicHallDB {

    public MusicHallDB() {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String createSql = "CREATE TABLE IF NOT EXISTS musichall (id int PRIMARY KEY AUTO_INCREMENT, name varchar(45)," +
                    " city varchar(45), country varchar(45), maxcapacity int)";

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
            String sql = "SELECT * FROM musichall";

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

    public MusicHallLocation getDataById(int id) {
        MusicHallLocation musicHallLocation = new MusicHallLocation();
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "SELECT * FROM musichall WHERE id=?";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String name = rs.getString(2);
                String city = rs.getString(3);
                String country = rs.getString(4);
                int maxCapacity = rs.getInt(5);
                musicHallLocation.setName(name);
                musicHallLocation.setCity(city);
                musicHallLocation.setCountry(country);
                musicHallLocation.setMaxCapacity(maxCapacity);
            }
            preparedStatement.close();
            rs.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicHallLocation;
    }

    public void insert(MusicHallLocation musicHallLocation) throws SQLException {
        try {
            Connection dbConnection = DBConnection.getDatabaseConnection();
            Statement stmt = dbConnection.createStatement();
            String sql = "INSERT INTO musichall (name, city, country, maxcapacity) " +
                    "VALUES ('" + musicHallLocation.getName() + "', '" + musicHallLocation.getCity() +"', '"
                    + musicHallLocation.getCountry() +"', " + musicHallLocation.getMaxCapacity() + ")";

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
            String sql = "UPDATE musichall SET name=? WHERE id=?";

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
            String sql = "DELETE FROM musichall WHERE id=?";

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
