import java.sql.*;
import java.util.Scanner;

public class LoginPage {
    private Scanner sc = new Scanner(System.in);

    // data
    private String noTLP, OTP = null, getOTP = null;
    private Pengguna p;

    public void doLogin() {
        while (this.OTP == null || !this.OTP.equals(this.getOTP)) { // cek apakah otp sesuai dengan yang diberikan
            System.out.println("====LOGIN====\n");

            // input username
            System.out.print("Masukkan Nomor Telepon: ");
            this.noTLP = sc.next();

            try {
                if (!searchPengguna()) {
                    System.out.println("Nomor Telepon Tidak Terdaftar\n");
                    continue;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // ambil otp
            this.getOTP = noTLP.substring(noTLP.length() - 3) + String.valueOf((int) (Math.random() * 900) + 100);
            System.out.println("OTP: " + getOTP);

            // input otp
            System.out.print("Masukkan OTP: ");
            this.OTP = sc.next();
            if (!this.OTP.equals(this.getOTP)) {
                System.out.println("====OTP SALAH====");
            }
        }
        // Print jika sudah keluar while loop
        System.out.println("====LOGIN BERHASIL====\n");
        System.out.println("====Selamat Datang di Aplikasi sRusun====\n");
    }

    public boolean searchPengguna() throws SQLException { // cari di db
        // menyamakan No_Telp di dalam db untuk mencari tipe user
        DBAccess db = new DBAccess();
        String query = "SELECT * FROM Pengguna";
        ResultSet rs = db.executeQuery(query);

        while (rs.next()) {
            if (rs.getString(2).equals(noTLP)) {
                if (rs.getInt(6) == 0) { // pemilik
                    this.p = new Pemilik(rs.getString(1), this.noTLP, rs.getString(3), 0);
                } else if (rs.getInt(6) == 1) { // admin
                    this.p = new Admin(rs.getString(1), this.noTLP, rs.getString(3), 1);
                } else { // pengelola
                    this.p = new Pengelola(rs.getString(1), this.noTLP, rs.getString(3), 2);
                }
                return true;
            }
        }

        return false;
    }

    public Pengguna getPengguna() {
        return p;
    }
}
