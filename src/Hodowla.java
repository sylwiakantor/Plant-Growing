import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Hodowla extends Thread{
    Budzet myBudzet = new Budzet();
    HashMap<String,Sektor> hmSektorow = new HashMap<String,Sektor>();
    SimpleDateFormat myFormat1 = new SimpleDateFormat("dd.MM.yyyy"); ///!!!!dodane

    public String getDate(){    //!!!!!!!!!dodane
        Date data = new Date();
        return myFormat1.format(data);
    }

    public void addSektor(String nazwa, int liczbaMiejsc, int paramNaslonecznienie, int paramNawodnienie){
        ///GUI wyrzuca bład gdy istnieje sektor o podanej nazwie
        Sektor newSektor = new Sektor(nazwa,liczbaMiejsc,paramNaslonecznienie,paramNawodnienie);
        hmSektorow.put(nazwa, newSektor);
    }

    public void removeSektor(String nazwa){

        hmSektorow.remove(nazwa);
    }

    HashMap<String, Gatunek> hmGatunkow = new HashMap<String, Gatunek>();

    public void addGatunek(String nazwa, int wymaganeMiejsce, int wymaganeNaslonecznienie, int wymaganeNawodnienie, double cenaSadzonki, double cenaSprzedazy, int minCzasRozwoju, int maxCzasRozwoju){

        Gatunek newGatunek = new Gatunek(nazwa, wymaganeMiejsce, wymaganeNaslonecznienie, wymaganeNawodnienie, cenaSadzonki, cenaSprzedazy, minCzasRozwoju, maxCzasRozwoju);
        hmGatunkow.put(nazwa, newGatunek);
    }

    public void removeGatunek(String nazwa){

        hmGatunkow.remove(nazwa);
    }

    public int getLiczbaWolnychMiejsc(Sektor sektor){
        int liczbaZajetychMiejsc = 0;
        List<Rosliny> zestawyRoslin = new ArrayList<Rosliny>(sektor.hmRosliny.keySet());
        for(Rosliny zestawRoslin: zestawyRoslin){
            int ileZajmujeSadz = hmGatunkow.get(zestawRoslin.getGatRoslin()).getWymaganeMiejsce();
                    liczbaZajetychMiejsc += sektor.hmRosliny.get(zestawRoslin)*ileZajmujeSadz;
        }
        return sektor.getListaMiejsc() - liczbaZajetychMiejsc;
    }

    //roslina
    public void kupRosliny(String nazwaGatunku, int iloscSadzonek, String nazwaSektoru){
        Rosliny tmpRosliny = new Rosliny(getDate(), nazwaGatunku);
        boolean czyIstnieje = false;
        for(Rosliny entry: hmSektorow.get(nazwaSektoru).hmRosliny.keySet())
                if(tmpRosliny.compare(entry)){
                    czyIstnieje = true;
                    tmpRosliny = entry;
                }
        if (czyIstnieje){
            int liczbaStarychSadzonek = hmSektorow.get(nazwaSektoru).hmRosliny.get(tmpRosliny);
            hmSektorow.get(nazwaSektoru).hmRosliny.remove(tmpRosliny);
            hmSektorow.get(nazwaSektoru).hmRosliny.put(tmpRosliny, liczbaStarychSadzonek + iloscSadzonek);
        }
        else {hmSektorow.get(nazwaSektoru).hmRosliny.put(tmpRosliny, iloscSadzonek);}

        myBudzet.setWydano(iloscSadzonek*hmGatunkow.get(nazwaGatunku).getCenaSadzonki());
    }

    public void sprzedajRosliny(String nazwaGatunku, int iloscSadzonekDoSprzedazy, String nazwaSektoru,String dzienKupna){
        Rosliny tmpRosliny = new Rosliny(dzienKupna,nazwaGatunku);
        boolean czyIstnieje = false;
        for(Rosliny entry: hmSektorow.get(nazwaSektoru).hmRosliny.keySet())
            if(tmpRosliny.compare(entry)){
                czyIstnieje = true;
                tmpRosliny = entry;
            }
        if (czyIstnieje){
            if ((hmSektorow.get(nazwaSektoru).hmRosliny.get(tmpRosliny) - iloscSadzonekDoSprzedazy)==0){
                hmSektorow.get(nazwaSektoru).hmRosliny.remove(tmpRosliny);
                myBudzet.zarobiono += hmGatunkow.get(nazwaGatunku).getCenaSprzedazy()*iloscSadzonekDoSprzedazy;
            }else if((hmSektorow.get(nazwaSektoru).hmRosliny.get(tmpRosliny) - iloscSadzonekDoSprzedazy)>0) {
                hmSektorow.get(nazwaSektoru).hmRosliny.put(tmpRosliny, hmSektorow.get(nazwaSektoru).hmRosliny.get(tmpRosliny) - iloscSadzonekDoSprzedazy);
                myBudzet.zarobiono += hmGatunkow.get(nazwaGatunku).getCenaSprzedazy() * iloscSadzonekDoSprzedazy;
            }
        }

    }


    public void usunZwiedłeRosliny() throws ParseException {
        List<String> listaSektorow = new ArrayList<String>(hmSektorow.keySet());

        for (String nazwaSektora : listaSektorow){
            Sektor tmpSektor = hmSektorow.get(nazwaSektora);
            List<Rosliny> tmpZawartoscSektoraKeys = new ArrayList<Rosliny>(tmpSektor.hmRosliny.keySet());
            for (Rosliny zestawRoslin : tmpZawartoscSektoraKeys){
                String tmpGatRoslin = zestawRoslin.getGatRoslin();
                String stringDataZakupu = zestawRoslin.getDzienKupna();
                Date dataZakupu = myFormat1.parse(stringDataZakupu);
                Date dataObecna = myFormat1.parse(getDate());
                int maxDniRozwoju = hmGatunkow.get(tmpGatRoslin).getMaxCzasRozwoju();
                if ((dataZakupu.getTime() - dataObecna.getTime())>maxDniRozwoju){
                    hmSektorow.get(nazwaSektora).hmRosliny.remove(zestawRoslin);
                    myBudzet.straty += hmGatunkow.get(tmpGatRoslin).getCenaSadzonki() * hmSektorow.get(nazwaSektora).hmRosliny.get(zestawRoslin);
                }
            }

        }
    }
    public Hodowla(HashMap hmSektorow, HashMap hmGatunkow, Budzet myBudzet){
        this.hmSektorow = hmSektorow;
        this.hmGatunkow = hmGatunkow;
        this.myBudzet = myBudzet;
    }

    private int timer = 0;
    private static int secToDay = 2000;

    public void run(){
        Date previousDate = new Date();
        while(true){
            Date currentDate = new Date();
            if (previousDate != currentDate){
                try {
                    usunZwiedłeRosliny();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                previousDate = currentDate;
            }
            timer +=1 ;

            try {
                Thread.sleep(secToDay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
