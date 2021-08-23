package kursJava;

import org.junit.jupiter.api.Test;

public class Metody {
    @Test
    public void example () {
        double poleTrapezuPierwszego = obliczPoleTrapezu(8, 10, 5);
        double poleTrapezuDrugiego = ((6+8)*3)/2;
        double poleTrapezuTrzeciego = ((12+10)*6)/2;
        String tekst = "Pola trapezów to " + poleTrapezuPierwszego + ", " + poleTrapezuDrugiego + ", " + poleTrapezuTrzeciego + ".";
        wypiszKoniec();
        wypiszTekst("Koniec testu.");
        boolean czyWieksza = czyWiekszaNiz100(120);
    }

    double obliczPoleTrapezu(double pierwszaPodstawa, double drugaPodstawa, double wysokość){
        double poleTrapezu = ((pierwszaPodstawa + drugaPodstawa)*wysokość)/2;
        return poleTrapezu;
    }

    boolean czyWiekszaNiz100 (int liczba){
        return liczba>100;
    }

    void wypiszKoniec(){
        System.out.println("Koniec testu.");
    }

    void wypiszTekst(String tekst){
        System.out.println(tekst);
    }
}

