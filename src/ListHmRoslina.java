public class ListHmRoslina {
    public int liczbaSadzonek;
    public Rosliny rosliny;

    ListHmRoslina(Rosliny rosliny,Integer liczbaSadzonek){
        this.rosliny=rosliny;
        this.liczbaSadzonek=liczbaSadzonek;
    }

    @Override
    public String toString() {
        return rosliny.getGatRoslin() + rosliny.getDzienKupna() + Integer.toString(liczbaSadzonek);
    }
}
