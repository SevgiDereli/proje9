package Utilities;

public class MyFunc {
    public static void bekle(int sn) {
        try {
            Thread.sleep(sn * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int randomSayiUret(int a) { // 0dan a'ya kadar üretir. tek parametreli metot. a dahil değil

        return (int) (Math.random() * a);
    }
}
