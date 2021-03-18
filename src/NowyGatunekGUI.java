import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NowyGatunekGUI extends JFrame{
    private JPanel NowyGatunek;
    private JLabel NazwaNowegoGatunku;
    private JButton dodajButton;
    private JButton wyjdźButton;
    private JTextField NazwaNowegoGatunkuTx;
    private JLabel MiejsceNowegoGatunku;
    private JTextField MiejsceNowegoGatunkuTx;
    private JLabel NaslonecznienieNowegoGatunku;
    private JTextField NaslonecznienieNowegoGatunkuTx;
    private JLabel NawodnienieNowegoGatunku;
    private JTextField NawodnienieNowegoGatunkuTx;
    private JLabel CenaSadzonkiNowegoGatunku;
    private JTextField CenaSadzonkiNowegoGatunkuTx;
    private JLabel CenaSprzedazyNowegoGatunku;
    private JTextField CenaSprzedazyNowegoGatunkuTx;
    private JLabel MinimalnyCzasRozwojuNowegoGatunku;
    private JLabel MaksymalnyCzasRozwojuNowegoGatunku;
    private JSpinner wymMiejsceS;
    private JSpinner wymNaslonecznienieS;
    private JSpinner wymNawodnienieS;
    private JSpinner cenaKupnaSadzS;
    private JSpinner cenaSprzeSadzS;
    private JSpinner minCzasRozS;
    private JSpinner maxCzasRozS;
    private JTextField MinimalnyCzasRozwojuNowegoGatunkuTx;
    private JTextField MaksymalnyCzasRozwojuNowegoGatunkuTx;
    private Hodowla hodowla;
    private Gatunek gatunek;


    public NowyGatunekGUI(HodowlaGUI gatunekGUI) {
        setContentPane(NowyGatunek);
        pack();

        SpinnerNumberModel model0 = new SpinnerNumberModel(1,1,10,1);
        wymMiejsceS.setModel(model0);
        ((JSpinner.DefaultEditor) wymMiejsceS.getEditor()).getTextField().setEditable(false);
        SpinnerNumberModel model1 = new SpinnerNumberModel(3,1,5,1);
        wymNaslonecznienieS.setModel(model1);
        ((JSpinner.DefaultEditor) wymNaslonecznienieS.getEditor()).getTextField().setEditable(false);
        SpinnerNumberModel model2 = new SpinnerNumberModel(3,1,5,1);
        wymNawodnienieS.setModel(model2);
        ((JSpinner.DefaultEditor) wymNawodnienieS.getEditor()).getTextField().setEditable(false);
        SpinnerNumberModel model3 = new SpinnerNumberModel(1.00,0.01,null,0.01);
        cenaKupnaSadzS.setModel(model3);
        ((JSpinner.DefaultEditor) cenaKupnaSadzS.getEditor()).getTextField().setEditable(false);
        SpinnerNumberModel model4 = new SpinnerNumberModel((Double) cenaKupnaSadzS.getValue() *2,0.01,null,0.01);
        cenaSprzeSadzS.setModel(model4);
        ((JSpinner.DefaultEditor) cenaSprzeSadzS.getEditor()).getTextField().setEditable(false);
        SpinnerNumberModel model5 = new SpinnerNumberModel(180,1,null,1);
        minCzasRozS.setModel(model5);
        ((JSpinner.DefaultEditor) minCzasRozS.getEditor()).getTextField().setEditable(false);
        SpinnerNumberModel model6 = new SpinnerNumberModel((Integer)minCzasRozS.getValue() *2,1,null,1);
        maxCzasRozS.setModel(model6);
        ((JSpinner.DefaultEditor) maxCzasRozS.getEditor()).getTextField().setEditable(false);

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazwa = NazwaNowegoGatunkuTx.getText();
                int miejsce = (Integer) wymMiejsceS.getValue();
                int naslonecznienie = (Integer) wymNaslonecznienieS.getValue();
                int nawodnienie = (Integer) wymNawodnienieS.getValue();
                double cenaSadzonki = (Double) cenaKupnaSadzS.getValue();
                double cenaSprzedazy = (Double) cenaSprzeSadzS.getValue();
                int minCzasRozwoju = (Integer) minCzasRozS.getValue();
                int maxCzasRozwoju = (Integer) maxCzasRozS.getValue();

                boolean czySpelniaWarunki = true;
                boolean nieMaGatOTakiejNazwie = true;
                for(String key: hodowla.hmGatunkow.keySet()){
                    if(key.equals(nazwa)){
                        nieMaGatOTakiejNazwie = false;
                        break;
                    }
                }

                if(maxCzasRozwoju <= minCzasRozwoju){
                    czySpelniaWarunki = false;
                }
                if(cenaSprzedazy <= cenaSadzonki){
                    czySpelniaWarunki = false;
                }

                if((czySpelniaWarunki==true)&&(nieMaGatOTakiejNazwie==true)){
                    gatunekGUI.getGatunkiCB().addItem(nazwa);
                    gatunekGUI.hodowla.addGatunek(nazwa, miejsce, naslonecznienie, nawodnienie, cenaSadzonki, cenaSprzedazy, minCzasRozwoju, maxCzasRozwoju);
                    gatunekGUI.getZSNazwaGatunkuCB().addItem(nazwa);
                    dispose();

                }else if(nieMaGatOTakiejNazwie == false){
                    JOptionPane.showMessageDialog(null, "Istnieje gatunek o takiej nazwie");
                } else {
                    JOptionPane.showMessageDialog(null, "Gatunek nie spełnia jednego z warunków: cena sprzedaży większa od ceny kupna");
                    // max czas rozwoju krótszy od min czas rozwoju
                }
            }


        });
        wyjdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }


}
