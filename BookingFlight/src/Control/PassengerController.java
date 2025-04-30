package Control;

import Entity.Consts;
import Entity.Passenger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class PassengerController {
	
    private static PassengerController _instance;
    private static HashMap<String, Passenger> passengerMap;
    private String lastErrorMessage;

    // Private constructor for singleton pattern
    private PassengerController() {
    	passengerMap = getAllPassengers();
    }
    
    // Get the singleton instance of CustomerLogic
    public static PassengerController getInstance() {
        if (_instance == null)
            _instance = new PassengerController();
        return _instance;
    }
    
    // Retrieve all passengers from the database
    public HashMap<String, Passenger> getAllPassengers() {
        HashMap<String, Passenger> results = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
             PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_PASSENGERS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int i = 1;
                String id = rs.getString(i++);
                String name = rs.getString(i++);
                String address = rs.getString(i++);
                
                Passenger passenger = new Passenger(id, name, address);
                results.put(id, passenger);
                                     
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
    
    // Insert a new customer
    public static String insertPassenger(String id,String name,String address) {
        if (id.length()!=3) {
            return "Pessenger ID must contain exactly 3 digits.";
        }

        if (name.isEmpty() || address.isEmpty()) {
            return "All fields must be filled.";
        }

        if (passengerMap.containsKey(id)) {
            return "Customer with ID " + id + " already exists.";
        }

      
        try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
             PreparedStatement stmt = conn.prepareStatement(Consts.SQL_INS_PASSENGERS)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, address);
            boolean inserted = stmt.executeUpdate() > 0;
            if (inserted) {
                passengerMap.put(id, new Passenger(id, name, address));
                return "passenger added successfully.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to insert passenger due to a database error.";
        }
        return "Failed to add passenger.";
    }
    
    
   
}
