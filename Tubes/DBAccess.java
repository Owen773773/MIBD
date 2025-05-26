import java.sql.*;

//Dependecies for printTable
import java.util.ArrayList;
import java.util.List;

public class DBAccess {
    private String db_url = "jdbc:sqlserver://LAPTOP-H2N2C3LD;databaseName=sRusunDB;user=Administrator;password=owen12345;encrypt=true;trustServerCertificate=true";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(db_url);
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public int executeUpdate(String sql) throws SQLException {
        try (Connection connection = DriverManager.getConnection(db_url);
                Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        }
    }

    public static void printTable(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Initialize column widths with column name lengths
        int[] columnWidths = new int[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnWidths[i] = metaData.getColumnLabel(i + 1).length();
        }

        // Store all rows temporarily
        List<String[]> rowData = new ArrayList<>();

        while (rs.next()) {
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                String value = rs.getString(i + 1);
                value = (value == null) ? "NULL" : value;
                row[i] = value;

                // Update column width if this value is longer
                columnWidths[i] = Math.max(columnWidths[i], value.length());
            }
            rowData.add(row);
        }

        // Print column headers
        for (int i = 0; i < columnCount; i++) {
            System.out.printf("%-" + (columnWidths[i] + 2) + "s", metaData.getColumnLabel(i + 1));
        }
        System.out.println();

        // Print separator
        for (int i = 0; i < columnCount; i++) {
            System.out.print("-".repeat(columnWidths[i] + 2));
        }
        System.out.println();

        // Print data rows
        for (String[] row : rowData) {
            for (int i = 0; i < columnCount; i++) {
                System.out.printf("%-" + (columnWidths[i] + 2) + "s", row[i]);
            }
            System.out.println();
        }
    }
}
