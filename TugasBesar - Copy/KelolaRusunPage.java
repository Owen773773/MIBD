import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class KelolaRusunPage {
    Scanner sc = new Scanner(System.in);
    DBAccess db = new DBAccess();
    // fitur
    // +- pengguna ok
    // kelola iot
    // kelola sarusun
    Admin admin;

    public KelolaRusunPage(Admin admin) {
        this.admin = admin;
    }

    public void doAction() {
        while (true) {
            System.out.println("Pilih Menu: ");
            System.out.println("1. Kelola Pengguna");
            System.out.println("2. Kelola sRusun");
            System.out.println("3. Kelola Sarusun");
            System.out.println("4. Back");
            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    kelolaOrang();
                    break;

                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }

    public void kurangiPengguna() {
        System.out.print("Input Nomor Ponsel Pengguna yang ingin Dihapus: ");
        String sql = String.format("DELETE FROM Pengguna WHERE No_Ponsel = %s", sc.nextLine());

        try {
            db.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        System.out.println();
    }

    public void tambahPengguna() {
        System.out.print("Masukkan NIK: ");
        String NIK = sc.nextLine();
        System.out.println();

        System.out.print("Masukkan Nomor Ponsel: ");
        String No_Ponsel = sc.nextLine();
        System.out.println();

        System.out.print("Masukkan Alamat Domisili: ");
        String Alamat_Domisili = sc.nextLine();
        System.out.println();

        System.out.print("Masukkan Tipe User: ");
        int TipeUser = sc.nextInt();
        System.out.println();

        String sql = String.format(
                "INSERT INTO Pengguna (NIK, No_Ponsel, Alamat_Domisili, OTPGenerate_OTP, TipeUser) VALUES (%s, %s, %s, %d)",
                NIK, No_Ponsel, Alamat_Domisili, TipeUser);

        try {
            db.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void kelolaOrang() {
        boolean izinAkses = true;

        while (izinAkses) {
            System.out.println("1. Tambahkan Penguna");
            System.out.println("2. Hilangkan Penguna");
            System.out.println("3. Back");
            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    tambahPengguna();
                    break;
                case 2:
                    kurangiPengguna();
                case 3:
                    izinAkses = false;
                    break;
                default:
                    break;
            }
        }
    }
}