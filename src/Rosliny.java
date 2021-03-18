public class Rosliny {
    private final String dzienKupna;
    private final String gatRoslin;

    public String getDzienKupna() {
        return dzienKupna;
    }

    public String getGatRoslin() {
        return gatRoslin;
    }



    public Rosliny(String dzienKupna, String gatRoslin) {
        this.dzienKupna = dzienKupna;
        this.gatRoslin = gatRoslin;
    }

    @Override
    public String toString() {
        String tmp = dzienKupna + "  " + gatRoslin;
        return tmp;
    }
    public boolean compare(Rosliny rosliny2){
        if(getDzienKupna().equals(rosliny2.getDzienKupna()) && getGatRoslin().equals(rosliny2.getGatRoslin())){
            return true;
        }else{
            return false;
        }
    }
}
