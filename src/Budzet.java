public class Budzet {
    double zarobiono;
    double wydano;
    double straty;

    public double getStanAktualny() {
        return zarobiono - wydano - straty;
    }

    public double getZarobiono() {
        return zarobiono;
    }

    public double getWydano() {
        return wydano;
    }

    public double getStraty() {
        return straty;
    }

    public void setZarobiono(double newZarobiono) {
        this.zarobiono = zarobiono + newZarobiono;
    }
    public void setWydano(double newWydano) {
        this.wydano = wydano + newWydano;
    }
    public void setStraty(double newStraty) {
        this.straty = straty + newStraty;
    }

    public Budzet(){
        this.zarobiono = 0.0;
        this.wydano = 0.0;
        this.straty = 0.0;
    }

}
