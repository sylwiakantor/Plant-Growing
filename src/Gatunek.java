import java.util.Date;
import java.util.Scanner;

public class Gatunek {
    private final String nazwa;
    private final int wymaganeMiejsce;
    private final int wymaganeNaslonecznienie;
    private final int wymaganeNawodnienie;
    private final double cenaSadzonki;
    private final double cenaSprzedazy;
    private final int minCzasRozwoju;
    private final int maxCzasRozwoju;

    public Gatunek(String nazwa, int wymaganeMiejsce, int wymaganeNaslonecznienie, int wymaganeNawodnienie, double cenaSadzonki, double cenaSprzedazy, int minCzasRozwoju, int maxCzasRozwoju) {
        this.nazwa = nazwa;
        this.wymaganeMiejsce = wymaganeMiejsce;
        this.wymaganeNaslonecznienie = wymaganeNaslonecznienie;
        this.wymaganeNawodnienie = wymaganeNawodnienie;
        this.cenaSadzonki = cenaSadzonki;
        this.cenaSprzedazy = cenaSprzedazy;
        this.minCzasRozwoju = minCzasRozwoju;
        this.maxCzasRozwoju = maxCzasRozwoju;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getWymaganeMiejsce() {
        return wymaganeMiejsce;
    }

    public int getWymaganeNaslonecznienie() {
        return wymaganeNaslonecznienie;
    }

    public int getWymaganeNawodnienie() {
        return wymaganeNawodnienie;
    }

    public double getCenaSadzonki() {
        return cenaSadzonki;
    }

    public double getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    public int getMinCzasRozwoju() {
        return minCzasRozwoju;
    }

    public int getMaxCzasRozwoju() {
        return maxCzasRozwoju;
    }

    @Override
    public String toString() {
        String tmp = nazwa + ";" + Integer.toString(wymaganeMiejsce) + ";" + Integer.toString(wymaganeNaslonecznienie) + ";";
        tmp = tmp + Integer.toString(wymaganeNawodnienie) + ";" + Double.toString(cenaSadzonki) + ";" + Double.toString(cenaSprzedazy) + ";";
        tmp = tmp + Integer.toString(minCzasRozwoju) + ";" + Integer.toString(maxCzasRozwoju);
        return tmp;


    }
}
