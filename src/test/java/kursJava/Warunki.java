package kursJava;

import org.junit.jupiter.api.Test;

public class Warunki {

    @Test
    public void ifExample () {
        int liczba = 88;

        if(liczba>100) {
            System.out.println("Liczba " + liczba + " jest większa niż 100");
            liczba++;
        }
        else if (liczba>=0 && liczba <=100) {
            System.out.println("Liczba " + liczba + " jest większa lub równa 0 i mniejsza lub równa 100");
            liczba = 100;
        }
        else {
            System.out.println("Liczba " + liczba + " jest liczbą ujemną");
        }
        System.out.println("Koniec testu. Liczba jest równa " + liczba);
    }
}
