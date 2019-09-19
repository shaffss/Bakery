public class Bakery extends Thread {
    protected static final int n = 10; //liczba klientow (watkow)
    protected static final int limit = 100; //limit numerkow
    protected static volatile boolean[] list = new boolean[n]; //przechowujemy tu informacje czy dany numerek zostal juz pobrany
    protected static volatile int[] number = new int[n]; //a tu przechowujemy wartosci tych numerkow
    protected static volatile int count = 0; //licznik - bezwzgledna liczba numerkow

    public static void main(String[] args){
        //zgodnie z algorytmem ustawiamy wszystkie wartosci tablic na false i 0:
        for(int i=0; i<n; i++){
            list[i] = false;
            number[i] = 0;
        }
        //tworzymy tablice watkow - podprocesy odpowiadajace za zachowanie klientow:
        Customer[] threads = new Customer[n];
        //wypelniamy ja klientami:
        for (int i = 0; i < n; i++) {
            threads[i] = new Customer(i);
            threads[i].start();
        }
    }
}
