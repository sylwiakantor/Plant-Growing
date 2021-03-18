import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class HodowlaGUI {
    private JComboBox<String> SektoryCB;
    private JComboBox<String> GatunkiCB;
    private JButton dodajGatunekButton;
    private JButton usuńGatunekButton;
    private JTextField WydanoTx;
    private JTextField ZarobionoTx;
    private JTextField StratyTx;
    private JButton wyświetlZawartośćSektoraButton;
    private JTextField NazwaGatunkuTx;
    private JTextField MiejsceGatunkuTx;
    private JTextField NawodnienieGatunekTx;
    private JTextField NaslonecznienieGatunekTx;
    private JTextField CenaSadzonkiTx;
    private JTextField CenaSprzedazyTx;
    private JTextField MinCzasRozwojuTx;
    private JTextField MaxCzasRozwojuTx;
    private JTextField NazwaSektoraTx;
    private JTextField LiczbaMiejscTx;
    private JTextField NawodnienieSektoraTx;
    private JTextField NaslonecznienieSektoraTx;
    private JButton dodajSektorButton;
    private JButton usuńSektorButton;
    private JPanel Hodowla;
    private JLabel Wydano;
    private JLabel zarobiono;
    private JLabel Straty;
    private JTextField LiczbaWolnychMiejscTx;
    private JLabel NazwaSektora;
    private JLabel LiczbaMiejsc;
    private JLabel NawodnienieSektora;
    private JLabel NaslonecznienieSektora;
    private JLabel NazwaSektoru;
    private JComboBox<String> ZSNazwaSektoruCB;
    private JTextField ZSWolneMiejscaTx;
    private JLabel NazwaGatunku;
    private JLabel LiczbaSadzonek;
    private JComboBox<String> ZSNazwaGatunkuCB;
    private JTextField ZSLiczbaSadzonekTx;
    private JButton ZSkupRoślinyButton;
    private JButton ZSsprzedajRoślinyButton;
    private JTextField ZSWybranyZestawRoślinTx;
    private JTextField ZSLiczbaSadzonekDoSprzedazyTx;
    private JList<String> list1;
    private JTextField StanAktualnyTx;

    public JComboBox<String> getSektoryCB(){
        return SektoryCB;
    }
    public JComboBox<String> getGatunkiCB(){ return GatunkiCB; }
    public JComboBox<String> getZSNazwaSektoruCB() {return  ZSNazwaSektoruCB;}
    public JComboBox<String> getZSNazwaGatunkuCB() {return ZSNazwaGatunkuCB;}

    private Sektor sektor;
    private Gatunek gatunek;
    private Sektor ZSsektor;
    private Gatunek ZSgatunek;
    private Budzet budzet;
    Hodowla hodowla;
    int sadzonkiJlist;
    Rosliny roslinyJlist;


    public HodowlaGUI() {
        HashMap<String,Sektor> hmSektorow = new HashMap<>();
        HashMap<String,Gatunek> hmGatunkow = new HashMap<>();
        Budzet budzet = new Budzet();
        hodowla = new Hodowla(hmSektorow,hmGatunkow,budzet);
        DefaultListModel<String> instListModel = new DefaultListModel<String>();
        //////////////////
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                odswiez();
            }
        });

        timer.start();

        HodowlaGUI hodowlaGUI_this = this;

        dodajSektorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NowySektorGUI nowySektor = new NowySektorGUI(hodowlaGUI_this);
                nowySektor.setVisible(true);
            }
        });

        usuńSektorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (hodowla.getLiczbaWolnychMiejsc(sektor) < sektor.getListaMiejsc()) {
                    JOptionPane.showMessageDialog(null, "Nie można usunąć sektora, który ma zawartość");

                } else {

                    if (SektoryCB.getItemCount() == 1) {
                        int panel = JOptionPane.showConfirmDialog(null, "Czy chcesz usunąć sektor?", null, JOptionPane.YES_NO_OPTION);
                        if (panel == 0) {
                            SektoryCB.removeAllItems();
                            ZSNazwaSektoruCB.removeAllItems(); ////
                        } else {

                        }
                    } else {
                        int panel = JOptionPane.showConfirmDialog(null, "Czy chcesz usunąć sektor?");
                        if (panel == 0) {
                            SektoryCB.removeItem(sektor.getNazwa());
                            ZSNazwaSektoruCB.removeItem(sektor.getNazwa()); ////
                        } else {

                        }
                    }
                }
            }
        });

        dodajGatunekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NowyGatunekGUI nowyGatunek = new NowyGatunekGUI(HodowlaGUI.this);
                nowyGatunek.setVisible(true);

            }
        });

        usuńGatunekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                boolean istniejaRoslinyTegoGat = false;
                for (String nazwaSektora: hodowla.hmSektorow.keySet()){
                    Sektor tmpSektor = hodowla.hmSektorow.get(nazwaSektora);
                    for(Rosliny tmpRoslina: tmpSektor.hmRosliny.keySet()){
                        if(gatunek.getNazwa().equals(tmpRoslina.getGatRoslin())){
                            istniejaRoslinyTegoGat = true;
                            break;
                        }

                    }
                if (istniejaRoslinyTegoGat){
                    JOptionPane.showMessageDialog(null, "Nie można usunąć gatunku");
                }else {
                    if (GatunkiCB.getItemCount() == 1) {
                        int panel = JOptionPane.showConfirmDialog(null, "Czy chcesz usunąć gatunek?", null, JOptionPane.YES_NO_OPTION);
                        if (panel == 0) {
                            GatunkiCB.removeAllItems();
                            ZSNazwaGatunkuCB.removeAllItems(); ///jeśli istnieje roślina tego gat. to nie można usuwać!!!!
                        } else {

                        }
                    } else {
                        int panel = JOptionPane.showConfirmDialog(null, "Czy chcesz usunąć gatunek?");
                        if (panel == 0) {
                            GatunkiCB.removeItem(gatunek);
                            JOptionPane.showConfirmDialog(null, "usuwanie");
                        } else {

                        }
                        }
                    }
                }
            }
        });
        ZSkupRoślinyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Integer.parseInt(ZSLiczbaSadzonekTx.getText()) <= ZSsektor.getLiczbaWolnychMiejsc()){
                    hodowla.kupRosliny(ZSgatunek.getNazwa(),Integer.parseInt(ZSLiczbaSadzonekTx.getText()),ZSsektor.getNazwa());

                boolean sektorSpelniaWymagania = true;
                if(Integer.parseInt(ZSLiczbaSadzonekTx.getText()) > hodowla.getLiczbaWolnychMiejsc(ZSsektor)){
                    sektorSpelniaWymagania = false;

                }


                instListModel.removeAllElements();
                for (HashMap.Entry<Rosliny,Integer> entry: ZSsektor.hmRosliny.entrySet())
                {
                    instListModel.addElement(entry.getKey().toString() + "  " + entry.getValue());
                }
                list1.setModel(instListModel);

                if(ZSsektor.getParamNaslonecznienie() != ZSgatunek.getWymaganeNaslonecznienie()){
                    sektorSpelniaWymagania = false;
                }
                if (ZSsektor.getParamNawodnienie() != ZSgatunek.getWymaganeNawodnienie()){
                    sektorSpelniaWymagania = false;
                }


                if(sektorSpelniaWymagania == false){
                    JOptionPane.showMessageDialog(null, "Nie można umieścić roślin w tym sektorze, inne wymagania!");
                }else {
                    hodowla.kupRosliny(ZSgatunek.getNazwa(),Integer.parseInt(ZSLiczbaSadzonekTx.getText()),ZSsektor.getNazwa());

                    ///jest sens odświeżać tylko gdy weszły zmiany
                    instListModel.removeAllElements();
                    for (HashMap.Entry<Rosliny,Integer> entry: ZSsektor.hmRosliny.entrySet())
                    {
                        instListModel.addElement(entry.getKey().toString() + "  " + entry.getValue());
                    }
                    list1.setModel(instListModel);
                }
            }
        });

        ZSsprzedajRoślinyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dzienKupna = null;
                try {
                    dzienKupna = hodowla.myFormat1.parse(roslinyJlist.getDzienKupna());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                Date dzienObecny = new Date();
                long diff = (dzienObecny.getTime() - dzienKupna.getTime());
                int czasRozwoju = (int) (diff / (1000*60*60*24));

                if (czasRozwoju >= hmGatunkow.get(roslinyJlist.getGatRoslin()).getMinCzasRozwoju()) {
                    int sadzonkiDoSprzedazy = Integer.parseInt(ZSLiczbaSadzonekDoSprzedazyTx.getText());
                    hodowla.sprzedajRosliny(roslinyJlist.getGatRoslin(), sadzonkiDoSprzedazy, ZSsektor.getNazwa(), roslinyJlist.getDzienKupna());

                    instListModel.removeAllElements();
                    for (HashMap.Entry<Rosliny, Integer> entry : ZSsektor.hmRosliny.entrySet()) {
                        instListModel.addElement(entry.getKey().toString() + "  " + entry.getValue());
                    }
                    list1.setModel(instListModel);
                }else {
                    JOptionPane.showMessageDialog(null, "Rośliny nie są jeszcze wystarczająco rozwinięte");
                }
            }
        });


        SektoryCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sektor = hodowla.hmSektorow.get(SektoryCB.getSelectedItem());
            }
        });
        //git
        GatunkiCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gatunek = hodowla.hmGatunkow.get(GatunkiCB.getSelectedItem());
            }
        });
        ///////////////////////
        ZSNazwaGatunkuCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ZSgatunek = hodowla.hmGatunkow.get(ZSNazwaGatunkuCB.getSelectedItem());
            }
        });
        ZSNazwaSektoruCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ZSsektor = hodowla.hmSektorow.get(ZSNazwaSektoruCB.getSelectedItem());
                instListModel.removeAllElements();
                for (HashMap.Entry<Rosliny,Integer> entry: ZSsektor.hmRosliny.entrySet())
                {
                    instListModel.addElement(entry.getKey().toString() + "  " + entry.getValue());
                }
                list1.setModel(instListModel);


            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list1.isSelectionEmpty() == false){
                    String selectedValue = list1.getSelectedValue();
                    String [] s = selectedValue.split("\\s+");
                    roslinyJlist = new Rosliny(s[0],s[1]);
                    sadzonkiJlist = Integer.parseInt(s[2]);
                    ZSWybranyZestawRoślinTx.setText(selectedValue);
                }

            }
        });



        hodowla.addGatunek("Tulipan",1,3,3,0.30,2.5,15,30);
        GatunkiCB.addItem("Tulipan");
        ZSNazwaGatunkuCB.addItem("Tulipan");
        hodowla.addGatunek("Tuja",4,2,2,0.2,20,50,150);
        GatunkiCB.addItem("Tuja");
        ZSNazwaGatunkuCB.addItem("Tuja");
        hodowla.addSektor("Krzewy",200,2,2);
        SektoryCB.addItem("Krzewy");
        ZSNazwaSektoruCB.addItem("Krzewy");
        hodowla.addSektor("Rośliny jednoroczne",100,3,3);
        SektoryCB.addItem("Rośliny jednoroczne");
        ZSNazwaSektoruCB.addItem("Rośliny jednoroczne");
    }

        public void odswiez() {

            if (sektor == null) {
                NazwaSektoraTx.setText("");
                LiczbaMiejscTx.setText("");
                NawodnienieSektoraTx.setText("");
                NaslonecznienieSektoraTx.setText("");
                LiczbaWolnychMiejscTx.setText("");

            } else {
                NazwaSektoraTx.setText(sektor.getNazwa());
                LiczbaMiejscTx.setText(Integer.toString(sektor.getListaMiejsc()));
                NawodnienieSektoraTx.setText(Integer.toString(sektor.getParamNawodnienie()));
                NaslonecznienieSektoraTx.setText(Integer.toString(sektor.getParamNaslonecznienie()));
                LiczbaWolnychMiejscTx.setText(Integer.toString(hodowla.getLiczbaWolnychMiejsc(sektor)));    //dodane

            }
            if (gatunek == null) {
                NazwaGatunkuTx.setText("");
                MiejsceGatunkuTx.setText("");
                NawodnienieGatunekTx.setText("");
                NaslonecznienieGatunekTx.setText("");
                CenaSadzonkiTx.setText("");
                CenaSprzedazyTx.setText("");
                MinCzasRozwojuTx.setText("");
                MaxCzasRozwojuTx.setText("");
            } else {
                NazwaGatunkuTx.setText(gatunek.getNazwa());
                MiejsceGatunkuTx.setText(Integer.toString(gatunek.getWymaganeMiejsce()));
                NawodnienieGatunekTx.setText((Integer.toString(gatunek.getWymaganeNawodnienie())));
                NaslonecznienieGatunekTx.setText((Integer.toString(gatunek.getWymaganeNaslonecznienie())));
                CenaSadzonkiTx.setText((Double.toString(gatunek.getCenaSadzonki())));
                CenaSprzedazyTx.setText((Double.toString(gatunek.getCenaSprzedazy())));
                MinCzasRozwojuTx.setText(Double.toString(gatunek.getMinCzasRozwoju()));
                MaxCzasRozwojuTx.setText((Double.toString(gatunek.getMaxCzasRozwoju())));
            }

            WydanoTx.setText((Double.toString(hodowla.myBudzet.getWydano())));
            ZarobionoTx.setText(Double.toString(hodowla.myBudzet.getZarobiono()));
            StratyTx.setText(Double.toString(hodowla.myBudzet.getStraty()));
            StanAktualnyTx.setText(Double.toString(hodowla.myBudzet.getStanAktualny()));

            if(ZSsektor == null){
                ZSWolneMiejscaTx.setText("");
                ///lista ma być pusta
            }else {
                ZSWolneMiejscaTx.setText(Integer.toString(hodowla.getLiczbaWolnychMiejsc(ZSsektor)));

                ///wypełniona lista
            }





        }



    public static void main(String[] args) {

        JFrame frame = new JFrame("HodowlaGUI");
        frame.setContentPane(new HodowlaGUI().Hodowla);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
