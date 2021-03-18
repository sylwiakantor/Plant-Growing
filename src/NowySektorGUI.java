import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NowySektorGUI extends JFrame {
    private JPanel NowySektor;
    private JTextField NazwaNowegoSektoraTx;
    private JButton dodajButton;
    private JButton wyjdźButton;
    private JLabel NazwaNowegoSektora;
    private JLabel LiczbaMiejscNowegoSektora;
    private JLabel NawodnienieNowegoSektora;
    private JLabel NaslonecznienieNowegoSektora;


    private JSpinner nawodnienieS;
    private JSpinner naslonecznienieS;
    private JSpinner lMiejscS;

    private Sektor sektor;


    public NowySektorGUI(HodowlaGUI sektorGUI) {
        setContentPane(NowySektor);
        pack();


        SpinnerNumberModel model = new SpinnerNumberModel(3,1,5,1);
        nawodnienieS.setModel(model);
        ((JSpinner.DefaultEditor) nawodnienieS.getEditor()).getTextField().setEditable(false);
        SpinnerNumberModel model2 = new SpinnerNumberModel(3,1,5,1);
        naslonecznienieS.setModel(model2);
        ((JSpinner.DefaultEditor) naslonecznienieS.getEditor()).getTextField().setEditable(false);
        SpinnerNumberModel model3 = new SpinnerNumberModel(10,10,null,10);
        lMiejscS.setModel(model3);
        ((JSpinner.DefaultEditor) lMiejscS.getEditor()).getTextField().setEditable(false);




        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




                String nazwa = NazwaNowegoSektoraTx.getText();
                int paramLiczbaMiejsc = (Integer) lMiejscS.getValue();
                int paramNaslonecznienie = (Integer) naslonecznienieS.getValue();
                int paramNawodnienie = (Integer) nawodnienieS.getValue();

                boolean istniejeTakiSektor = false;
                for(String key: sektorGUI.hodowla.hmSektorow.keySet()){
                    if(nazwa.equals(key)){
                        istniejeTakiSektor = true;
                        break;
                    }
                }

                if(istniejeTakiSektor == true){
                    JOptionPane.showMessageDialog(null, "Istnieje już sektor o takiej nazwie!");
                }else {

                    sektorGUI.getSektoryCB().addItem(nazwa);
                    sektorGUI.hodowla.addSektor(nazwa, paramLiczbaMiejsc, paramNaslonecznienie, paramNawodnienie);
                    sektorGUI.getZSNazwaSektoruCB().addItem(nazwa);
                    ////////
                    dispose();
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
