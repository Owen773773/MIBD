import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sRusunPage {
    Scanner sc = new Scanner(System.in);
    // fitur
    // mati nyala air
    // data: no unit sarusun, liter per menit, no serial iot, Akumulasi,
    // data: utilitas air per

    private Pengguna p;
    private String noKamar;
    private DBAccess db = new DBAccess();

    public sRusunPage(Pengguna p) {
        this.p = p;
    }

    public void pilihSarusun() {
        Queue<String> kamar = new LinkedList<>();

        while (true) {
            String query = "SELECT NomorUnitSarusun FROM Unit WHERE " + p.getNIK() + " = NIK_Pengguna";

            try {
                ResultSet rs = db.executeQuery(query);

                // tampilkan nomor sarusun
                System.out.println("Nomor Sarusun yang Tersedia: ");
                int i, ct = 1;
                while (rs.next()) {
                    System.out.println(ct + ". " + rs.getString(1));
                    kamar.add(rs.getString(1));
                    ct++;
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

            System.out.print("Isi Nomor Kamar: ");
            String input = sc.nextLine();
            System.out.println();

            while (!kamar.isEmpty()) {
                String kamarNo = kamar.poll();

                if (input.equals(kamarNo)) {
                    noKamar = input;
                    break;
                }
            }

            if (noKamar == null) {
                System.out.println("Nomor Kamar Salah. Coba Lagi.");
            } else {
                break;
            }
        }
    }

    public void doAction() {
        boolean izinAkses = true;

        while (izinAkses) {

            pilihSarusun();

            System.out.println("Pilih Menu: ");
            System.out.println("1. Buka/Tutup Saluran Air");
            System.out.println("2. Penggunaan Utilitas Air");
            System.out.println("3. Tampilkan Histori");
            System.out.println("4. Back");

            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    setSaluranAir();
                    break;
                case 2:
                    showUtilitasAir();
                    break;
                case 3:
                    showLogs();
                    break;
                case 4:
                    izinAkses = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void setSaluranAir() {
        // cari aksi sesuai no kamar dipilih
        int aksiNow = 0;
        String priv = "SELECT TOP 1 ID_Log, Logs.Aksi FROM Logs " +
                "INNER JOIN Unit ON Unit.NIK_Pengguna = Logs.NIK_Pengguna " +
                "WHERE Unit.NomorUnitSarusun = '" + noKamar + "' " +
                "ORDER BY ID_Log DESC";
        String pub = "SELECT TOP 1 ID_Log FROM Logs " +
                "ORDER BY ID_Log DESC";
        try {
            ResultSet rsPriv = db.executeQuery(priv);
            ResultSet rsPub = db.executeQuery(pub);
            // ambil no id terakhir
            int nextID = 0;

            if (rsPriv.next() && rsPub.next()) {
                nextID = rsPub.getInt(1) + 1;
                aksiNow = rsPriv.getInt(2); // kolom aksi
                String waktu = java.time.LocalDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String sql;
                if (aksiNow == 1) {
                    sql = "INSERT INTO Logs (ID_log, Aksi, Waktu, NIK_Pengguna) VALUES" + "(" + nextID + ", " + 0
                            + ",'"
                            + waktu
                            + "', " + p.NIK + ")";
                    db.executeUpdate(sql);
                } else {
                    sql = "INSERT INTO Logs (ID_log, Aksi, Waktu, NIK_Pengguna) VALUES" + "(" + nextID + ", " + 1
                            + ", '"
                            + waktu
                            + "', " + p.NIK + ")";
                    db.executeUpdate(sql);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        // query = "SELECT ID_log FROM Logs ORDER BY desc";

        // try {
        // ResultSet rs = db.executeQuery(query);
        // aksiNow = rs.getInt(1); // kolom aksi
        // String sql = "INSERT INTO Logs (ID_log, Aksi, Waktu, NIK_Pengguna) VALUES";
        // int affect = db.executeUpdate(sql);
        // System.out.printf("row affected : %d\n", affect);
        // } catch (SQLException e) {
        // System.out.println("Database error: " + e.getMessage());
        // }
    }

    public void showUtilitasAir() {
        String query = "SELECT * FROM Saluran_Air";
        try {
            ResultSet rs = db.executeQuery(query);
            DBAccess.printTable(rs);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void showLogs() {
        String query = String.format("""
                SELECT
                    ID_LOG, Aksi,
                CASE Aksi
                    WHEN 0 THEN 'TUTUP Saluran Air'
                    WHEN 1 THEN 'BUKA Saluran Air'
                END AS Deskripsi_Aksi,
                Waktu
                FROM LOGS
                WHERE NIK_Pengguna = %s
                ORDER BY Waktu DESC
                """, p.getNIK());

        try {
            ResultSet rs = db.executeQuery(query);
            DBAccess.printTable(rs);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
