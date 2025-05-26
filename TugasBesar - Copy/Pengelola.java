public class Pengelola extends Pengguna {
    public Pengelola(String NIK, String noTLP, String alamatDomisili, int TipeUser) {
        super(NIK, noTLP, alamatDomisili, TipeUser);
    }

    // public monitoring(int idx) { //index kamar sarusun

    // }

    @Override
    public void getProfile() {
        System.out.printf("NIK\t\t\t: %s\nNomor Telepon\t\t: %s\nAlamat Domisili\t\t: %s\nTipe User\t\t: %s",
                this.NIK, this.noTlp, this.alamatDomisili, "Pengelola\n");
    }

    public int getTipeUser() {
        return 2;
    }

    public void MonitoringPageAccess(Pengelola pengelola) {
        while (true) {
            MonitoringPage monitoring = new MonitoringPage(pengelola);

            System.out.println("Pilih Menu: ");
            System.out.println("1. Halaman Monitoring Pengguna");
            System.out.println("2. Back");

            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1: // cek Profil
                    monitoring.doAction();
                    break;
                case 2:
                    return;
                default:
                    break;
            }
        }
    }
}