import java.sql.ResultSet;
import java.sql.SQLException;

public class PerangkatIoT {
    // pada kelas ini, iot akan memberikan fitur fitur pengaksesan
    // jadi untuk pembatasan akses akan ada di setiap kelas pengguna
    // jika ingin input pemilik,
    private int noSerial, statusOn, UtilitasHarian, noKamar;

    private DBAccess db = new DBAccess();

    @Override
    public int hashCode() {
        return this.noKamar;
    }

    public PerangkatIoT(int noSerial, int statusOn) {
        this.noSerial = noSerial;
        this.statusOn = statusOn;
    }

    public String setStatus(boolean status) {
        this.statusOn = statusOn;

        try {
            addLogs(UtilitasHarian);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (statusOn == 1) {
            return "Saluran Air Dibuka";
        }
        return "Saluran Air Ditutup";
    }

    public void addLogs(int UtilitasHarian) throws SQLException {
        String waktu = java.time.LocalDateTime.now().toString().replace("T", " ");
        String query = "INSERT INTO Logs VALUES (sql, 8, statusOn," + waktu + ", 888)";
        db.executeUpdate(query);
        // data penggunaan air
    }
}