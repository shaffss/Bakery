public class Customer extends Bakery{
    private int id; //id watku
    public Customer(int id){this.id = id;} //kostruktor wymuszajacy podanie id nowego klienta
    //metoda zwracajaca najwiekszy numerek:
    private int findMax(int[] ii){
        int temp = 0;
        for(int i : ii) if(i > temp) temp = i;
        return temp;
    }
    //metoda blokujaca dostep do sekcji krytycznej do momentu zwolnienia miejsca do obslugi przez clerka:
    private void lock(int i){
        list[i] = true;
        number[i] = 1 + findMax(number); //podajemy nowy numerek wiekszy o 1 od najwiekszego znajdujacego sie w kolejce
        list[i] = false;
        for (int j = 0; j < n; j++) {
            while (list[j]){} //nalezy zaczekac na numerek
            while (number[j] != 0 && (number[j] < number[i] || ((number[j] == number[i]) && (j < i)))){}
            //jesli numerek nie byl w uzyciu I moj numerek jest wiekszy niz kolegi LUB taki sam, ale ja mam mniejsza wartosc ID to czekam
        }
    }
    private void unlock(int i){
        number[i] = 0; //oznaczenie ze numer zostal juz uzyty i nie bedzie brany pod uwage
    }
    @Override
    public void run() {
        //klient pobiera numerek nie wiekszy niz zadany limit
        while(count<limit) {
            lock(id); //pobranie numerka i czekanie w kolejce
            /*poczatek sekcji krytycznej*/
            if(count<limit) System.out.println("T" + id + ":" + ++count); //bramka na wypadek inkremetacji w lock() przez inny watek powyzej limitu
            /*koniec sekcji krytycznej*/
            unlock(id); //pozbywamy sie numerka po obsluzeniu
        }
    }
}
