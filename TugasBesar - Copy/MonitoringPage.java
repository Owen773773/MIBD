import java.util.Scanner;

public class MonitoringPage {
    Scanner sc = new Scanner(System.in);
    DBAccess db = new DBAccess();

    private Pengelola pengelola;

    public MonitoringPage(Pengelola pengelola) {
        this.pengelola = pengelola;
    }

    public void doAction() {
        // monitoring sRusun sarusun (tampilkan utilitas air)
        // mengendalikan iot setiap sRusun

        while (true) {
            System.out.println("1. Monitoring sRusun Pengguna");
            System.out.println("2. Pengelolaan sRusun Pengguna");
            System.out.print("Isi Perintah: ");
            int input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    return;
                default:
                    break;
            }
        }
    }
}
