import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZawartoscSektoraGUI extends JFrame{
    private JPanel panel1;
    private JLabel NazwaSektoru;
    private JComboBox NazwaSektoruCB;
    private JTextField WolneMiejscaTx;
    private JLabel NazwaGatunku;
    private JLabel LiczbaSadzonek;
    private JComboBox NazwaGatunkuCB;
    private JTextField LiczbaSadzonekTx;
    private JButton kupRoślinyButton;
    private JList list1;
    private JTextField WybranyZestawRoślinTx;
    private JTextField LiczbaSadzonekDoSprzedazyTx;
    private JButton sprzedajRoślinyButton;
    private JButton button1;
    private Hodowla hodowla;
    private ZawartoscSektoraGUI zawartoscSektora;
    private Sektor sektor;
    private Gatunek gatunek;
    private Rosliny rosliny;
    public JComboBox getNazwaSektoruCB(){
        return NazwaSektoruCB;
    }

    public ZawartoscSektoraGUI(HodowlaGUI zawartoscGUI) {

        setContentPane(panel1);
        pack();

        kupRoślinyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int liczbaSadzonek = Integer.parseInt(LiczbaSadzonekTx.getText());

            }
        });
        //
        NazwaSektoruCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sektor = (Sektor) NazwaSektoruCB.getSelectedItem();

            }
        });
        NazwaGatunkuCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gatunek = (Gatunek) NazwaGatunkuCB.getSelectedItem();
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //rosliny = list1.getSelectedValue();

            }
        });

        Timer timer2 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odswiez();
            }
        });
        timer2.start();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NowySektorGUI sektorGUI;
                //sektorGUI.getNazwa
            }
        });
    }

        public void odswiez(){
            WolneMiejscaTx.setText(Integer.toString(hodowla.getLiczbaWolnychMiejsc(sektor)));
            LiczbaSadzonek.getText();
            LiczbaSadzonekDoSprzedazyTx.getText();
            NazwaGatunkuCB.getSelectedItem();
            NazwaSektoruCB.getSelectedItem();
            list1.getSelectedValue(); ///??????????????
            //WybranyZestawRoślinTx.setText();
        }
    }

