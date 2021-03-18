import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sektor {
    private int listaMiejsc;
    private int paramNaslonecznienie;
    private int paramNawodnienie;
    private String nazwa;
    HashMap<Rosliny, Integer> hmRosliny = new HashMap<Rosliny,Integer>();
    private int liczbaWolnychMiejsc;                                  ////!!!dodane
    private int liczbaZajetychMiejsc = 0;
    public int getListaMiejsc() {
        return listaMiejsc;
    }

    public int getParamNaslonecznienie() {
        return paramNaslonecznienie;
    }

    public int getParamNawodnienie() {
        return paramNawodnienie;
    }

    public String getNazwa() {
        return nazwa;
    }




    public void setListaMiejsc(int listaMiejsc) {
        this.listaMiejsc = listaMiejsc;  //GUI wyrzuca włąd gdy nie w przedziale od 0-100
    }

    public void setParamNaslonecznienie(int paramNaslonecznienie) {
        this.paramNaslonecznienie = paramNaslonecznienie; //GUI wyrzuca włąd gdy nie w przedziale od 0-5
    }

    public void setParamNawodnienie(int paramNawodnienie) {
        this.paramNawodnienie = paramNawodnienie;       //GUI wyrzuca włąd gdy nie w przedziale od 0-5
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }


    public Sektor(String nazwa, int listaMiejsc, int paramNaslonecznienie, int paramNawodnienie){
        this.nazwa = nazwa;
        this.listaMiejsc = listaMiejsc;
        this.paramNaslonecznienie = paramNaslonecznienie;
        this.paramNawodnienie = paramNawodnienie;
    }

    @Override
    public String toString() {
        String tmp = nazwa + ";" + Integer.toString(listaMiejsc) + ";" + Integer.toString(listaMiejsc - liczbaZajetychMiejsc) + ";";
        tmp = tmp + Integer.toString(paramNaslonecznienie) + ";" + Integer.toString(paramNawodnienie);
        return tmp;

    }
}
