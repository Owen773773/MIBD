public class Admin extends Pengguna {
    public Admin(String NIK, String noTLP, String alamatDomisili, int TipeUser) {
        super(NIK, noTLP, alamatDomisili, TipeUser);
    }

    @Override
    public void getProfile() {
        System.out.printf("NIK\t\t\t: %s\nNomor Telepon\t\t: %s\nAlamat Domisili\t\t: %s\nTipe User\t\t: %s",
                this.NIK, this.noTlp, this.alamatDomisili, "Administrator\n");
    }

    public int getTipeUser() {
        return 1;
    }

    public void adminPageAccess(Admin admin) {
        while (true) {
            KelolaRusunPage kelola = new KelolaRusunPage(admin);

            System.out.println("Pilih Menu: ");
            System.out.println("1. Halaman Pengelolaan sRusun");
            System.out.println("2. Back");

            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    kelola.doAction();
                    break;

                case 2:
                    return;
                default:
                    break;
            }
        }
    }
}