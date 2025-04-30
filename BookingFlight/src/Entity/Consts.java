package Entity;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;

public class Consts {
	
	// Private constructor to prevent instantiation
    private Consts() {
        throw new AssertionError();
    }
	
    protected static final String DB_FILEPATH = getDBPath();
    public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
    
    /*----------------------------------------- Passengers QUERIES -----------------------------------------*/
    public static final String SQL_SEL_PASSENGERS = "SELECT * FROM Passengers";
    public static final String SQL_INS_PASSENGERS = "INSERT INTO Passengers (passenger_id, passenger_name, address) VALUES (?, ?, ?)";
    public static final String SQL_DEL_PASSENGERS = "DELETE FROM Passengers WHERE passenger_id = ?";
    public static final String SQL_UPD_PASSENGERS = "UPDATE Passengers SET passenger_name = ?, address = ? WHERE passenger_id = ?";


    // Private method to get the database path
    private static String getDBPath() {
        try {
            // Get the path of the class file or JAR
            String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decoded = URLDecoder.decode(path, "UTF-8");
            System.out.println("Decoded path: " + decoded);

            if (decoded.contains(".jar")) {
                // If running from a JAR, get the directory of the JAR file
                decoded = decoded.substring(0, decoded.lastIndexOf('/'));
                String dbPath = decoded + "/database/project1.accdb";
                System.out.println("Database path (JAR): " + dbPath);
                return dbPath;
            } else {
                // If running from a class file, get the directory of the class file
                decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
                String dbPath = decoded + "src/Entity/project1.accdb";
                System.out.println("Database path (class): " + dbPath);
                return dbPath;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
