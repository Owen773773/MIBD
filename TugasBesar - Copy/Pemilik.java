public class Pemilik extends Pengguna {
    // jangan disentuh dulu bang
    // buangan

    public Pemilik(String NIK, String noTLP, String alamatDomisili, int TipeUser) {
        super(NIK, noTLP, alamatDomisili, TipeUser);
    }

    public int getTipeUser() {
        return 0;
    }

}