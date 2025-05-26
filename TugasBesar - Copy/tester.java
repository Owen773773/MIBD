import java.sql.*;

// javac -cp "lib\mssql-jdbc-12.10.0.jre11.jar" *.java && java -cp ".;lib\mssql-jdbc-12.10.0.jre11.jar" tester

public class tester {
    public static void main(String[] args) throws SQLException {
        // default penggunaan dan pencarian data di sql
        DBAccess db = new DBAccess();
        String sql = "DELETE FROM Logs WHERE ID_log = 8888";
        db.printTable(db.executeQuery(
                "SELECT NomorUnitSarusun,No_Ponsel, TipeUser FROM Unit INNER JOIN Pengguna ON Pengguna.NIK = Unit.NIK_Pengguna"));
        int rowsAffected = db.executeUpdate(sql);
        // System.out.println("Baris yang ditambahkan: " + rowsAffected);

        String q = "select * from Logs";
        ResultSet rs = db.executeQuery(q);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
}
