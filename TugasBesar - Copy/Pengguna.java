import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Pengguna {
    Scanner sc = new Scanner(System.in);
    // 0: Pemilik
    // 1: Administrator
    // 2: Pengelola
    protected String NIK, alamatDomisili, noTlp;
    protected int TipeUser;

    public Pengguna(String NIK, String noTLP, String alamatDomisili, int TipeUser) {
        this.NIK = NIK;
        this.noTlp = noTLP;
        this.alamatDomisili = alamatDomisili;
        this.TipeUser = TipeUser;
    }

    public String getNIK() {
        return this.NIK;
    }

    public void getProfile() {
        System.out.printf("NIK\t\t\t: %s\nNomor Telepon\t\t: %s\nAlamat Domisili\t\t: %s\nTipe User\t\t: %s",
                this.NIK, this.noTlp, this.alamatDomisili, "Pemilik\n");
    }

    public void pemilikPageAccess(Pengguna pemilik) {
        // page access
        while (true) {
            sRusunPage sRusun = new sRusunPage(pemilik);

            System.out.println("Pilih Menu: ");
            System.out.println("1. Cek Profil");
            System.out.println("2. Halaman Perangkat IOT");
            System.out.println("3. Back");

            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();

            switch (input) {
                case 1: // cek Profil
                    System.out.println("====Profile====");
                    pemilik.getProfile();
                    System.out.println("==================");
                    break;
                case 2: // Halaman Perangkat IOT
                    System.out.println("====IoT sRusun====");
                    sRusunPage srusun = new sRusunPage(pemilik); // pindah ke halaman sRusun
                    srusun.doAction();
                    System.out.println("==================");
                    break;
                case 3:
                    System.out.println("==================");
                    return;
                default:
                    break;
            }
        }
    }

    public abstract int getTipeUser();
}