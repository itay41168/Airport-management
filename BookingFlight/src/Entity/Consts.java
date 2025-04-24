package Entity;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;

public class Consts {
    protected static final String DB_FILEPATH = getDBPath();
    public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";

    private static String getDBPath() {
        try {
            String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decoded = URLDecoder.decode(path, "UTF-8");
            System.out.println("Decoded path: " + decoded);

            // במקרה של JAR
            if (decoded.contains(".jar")) {
                decoded = decoded.substring(0, decoded.lastIndexOf('/'));
                return decoded + "/database/project1.accdb"; // השתמש בנתיב היחסי
            } else {
                // במקרה של הרצת קוד מתוך IDE
                decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));  // או "build/", תלוי בסביבת העבודה
                return decoded + "src/Entity/project1.accdb"; // עדכון נתיב נוסף
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
