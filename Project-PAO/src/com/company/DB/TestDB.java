package com.company.DB;

import com.company.Locations.MusicHallLocation;
import com.company.Locations.PoolLocation;
import com.company.Locations.PubLocation;
import com.company.Locations.RestaurantLocation;

import java.sql.SQLException;

public class TestDB {
    public TestDB() throws SQLException {

        MusicHallLocation musicHallLocation = new MusicHallLocation("Bumtzi", "Timisoara", "RO", 500);
        MusicHallDB musicHallDB = new MusicHallDB();
        musicHallDB.insert(musicHallLocation);
        musicHallDB.updateName("Mia Musica Pro", 3);
        System.out.println(musicHallDB.getDataById(3).getName());
        musicHallDB.deleteById(2);
        musicHallDB.getData();



        PoolLocation poolLocation = new PoolLocation("Menta", "Craiova", "RO", 200, true, true, false);
        PoolDB poolDB = new PoolDB();
        poolDB.insert(poolLocation);
        poolDB.updateName("Therme Pro", 2);
        System.out.println(poolDB.getDataById(3).getName());
        poolDB.deleteById(1);
        poolDB.getData();



        PubLocation pubLocation = new PubLocation("Revolut", "Costinesti", "RO", 300, 30, 3, true, false);
        PubDB pubDB = new PubDB();
        pubDB.insert(pubLocation);
        pubDB.updateName("Shelter Pro", 1);
        System.out.println(pubDB.getDataById(3).getName());
        pubDB.deleteById(2);
        pubDB.getData();



        RestaurantLocation restaurantLocation = new RestaurantLocation("Hanul", "Bucharest", "RO", 200, 100, 2, false, 50);
        RestaurantDB restaurantDB = new RestaurantDB();
        restaurantDB.insert(restaurantLocation);
        restaurantDB.updateName("Papito Pro", 1);
        System.out.println(restaurantDB.getDataById(3).getName());
        restaurantDB.deleteById(2);
        restaurantDB.getData();

    }
}
