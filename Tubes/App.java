//pakai cmd untuk run
//javac -cp "lib\mssql-jdbc-12.10.0.jre11.jar" *.java && java -cp ".;lib\mssql-jdbc-12.10.0.jre11.jar" App
//janlup protitype kumpulkan

import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    /*
     * 0 Pemilik, 1 Administrator, 2 Pengelola
     * Input
     * 
     * Pengguna
     * 
     * 
     * Pemilik command
     * 1 : Cek Profil
     * 2 : Kendali Perangkat IOT milik Sendiri
     * 
     * Pengelola command
     * 1. Monitor pemakaian utilitas air
     * 2. Kendali Perangkat IOT berdasarkan NIK/Nomor Ponsel Pengguna
     * 
     * Admin command
     * ddl/dml master(sRusunDB)
     * 1. execute queri untuk ngubah data langsung ke DB
     * 2. Mengelola Pemilik
     * 3. Kendali Perangkat IOT berdasarkan NIK/Nomor Ponsel Pengguna
     * 
     * 
     * 
     */

    public static void runPemilik(Pengguna pemilik) {
        while (true) {
            System.out.println("1. Pemilik");
            System.out.println("2. Exit");
            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    pemilik.pemilikPageAccess(pemilik);
                    break;
                case 2:
                    System.out.println("====App Close====");
                    return;
                default:
                    break;
            }
        }
    }

    public static void runAdmin(Pengguna pemilik) {
        Admin admin = (Admin) pemilik;

        while (true) {
            System.out.println("1. Pemilik");
            System.out.println("2. Administrator");
            System.out.println("3. Exit");
            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    pemilik.pemilikPageAccess(pemilik);
                    break;
                case 2:
                    admin.adminPageAccess(admin);
                    break;
                case 3:
                    System.out.println("====App Close====");
                    return;
                default:
                    break;
            }
        }
    }

    public static void runPengelola(Pengguna pemilik) {
        Pengelola pengelola = (Pengelola) pemilik;

        while (true) {
            System.out.println("1. Pemilik");
            System.out.println("2. Pengelola");
            System.out.println("3. Exit");
            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    pemilik.pemilikPageAccess(pemilik);
                    break;

                case 2:
                    pengelola.MonitoringPageAccess(pengelola);

                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    // running page
    public static void main(String[] args) {
        // user login
        LoginPage login = new LoginPage();
        login.doLogin();

        // dapatkan akun setelah login
        Pengguna user = login.getPengguna();

        /*
         * 0 Pemilik, 1 Administrator, 2 Pengelola
         */
        if (user.getTipeUser() == 1) {
            // jalankan admin
            runAdmin(user);
        } else if (user.getTipeUser() == 2) {
            // jalankan pengelola
            runPengelola(user);
        } else {
            // jalankan pemilik
            runPemilik(user);
        }
    }
}