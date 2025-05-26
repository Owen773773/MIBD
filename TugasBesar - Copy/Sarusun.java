import java.util.HashMap;
import java.lang.Math;
import java.sql.*;

public class Sarusun {
    // iot class
    class PerangkatIoT {
        // pembungkus iot dan pemilik
        // pada kelas ini, iot akan memberikan fitur fitur pengaksesan
        // jadi untuk pembatasan akses akan ada di setiap kelas pengguna
        // jika ingin input pemilik,
        private int noSerial, statusOn, UtilitasHarian;
        private String noKamar;

        private DBAccess db = new DBAccess();

        @Override
        public int hashCode() {
            int gedung = (int) (noKamar.charAt(0));
            int res = Integer.parseInt(noKamar.substring(1));
            return res * 10 + gedung;
        }

        public PerangkatIoT(int noSerial, int statusOn) {
            this.noSerial = noSerial;
            this.statusOn = statusOn;
        }
    }
    // =============================================================================

    DBAccess db = new DBAccess();
    HashMap<PerangkatIoT, Pengguna> sarusun;

    // input data ke dalam sarusun
    public Sarusun() {
        // inner join dengan iot dan unit (harusnya memang disatuin karena mengikuti
        // transformasi ERD)
        String query = "select * from Logs";

        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                sarusun.put(null, null);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // public void inputPemilikBaru(Pengguna p) {
    // int serial = Math.random();
    // sarusun.put(new PerangkatIoT(serial, 0), p);
    // }

}
