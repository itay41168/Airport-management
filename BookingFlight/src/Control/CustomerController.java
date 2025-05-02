package Control;

import Entity.Customer;
import Entity.Consts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerController {

    public static boolean registerCustomer(Customer customer) {
        try (Connection conn = Consts.getConnection()) {
            // תחילה, בדוק אם ה-ID כבר קיים במסד
            String checkSql = "SELECT COUNT(*) FROM Passengers WHERE passenger_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, customer.getId());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            
            // אם ה-ID קיים כבר, החזר false
            if (count > 0) {
                System.out.println("ID already exists in the database!");
                return false;
            }
            
            // אם ה-ID לא קיים, המשך עם ההכנסה למסד
            String sql = "INSERT INTO Passengers (passenger_id, passenger_name, address) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customer.getId());  // כאן תוכל להכניס את ה-ID שהמשתמש הזין
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());

            int rows = stmt.executeUpdate();
            stmt.close();
            return rows > 0;

        } catch (Exception e) {
            System.err.println("Error inserting customer:");
            e.printStackTrace();
            return false;
        }
    }
}
