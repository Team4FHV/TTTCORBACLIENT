/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CorbaGUICtrl;

import corba.*;
import client.CorbaClient_MO;
import Exceptions.KarteNichtVerfuegbarException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Stefan Dietrich
 */
public class CorbaKartenInfoCtrl {

    private StructVeranstaltung _veranstaltung;
    private StructKategorieInformation _kategorie;
    private StructKategorieKarte _Kategoriekarten;
    private CorbaClient_MO _client;

    public CorbaKartenInfoCtrl(int veranstaltungID, int kategorieID, CorbaClient_MO client) {
        _client = client;
        // try {
        _veranstaltung = client.getVeranstaltungById(veranstaltungID);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        try {
        _kategorie = client.getKategorieInfo(kategorieID);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        try {
        _Kategoriekarten = _client.getAlleFreieKartenNachKategorie(_kategorie.kategId);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    public TableModel getKartenInfo() {
        return null;
    }

    public StructVeranstaltung getVeranstaltung() {
        return _veranstaltung;
    }

    public StructKategorieInformation getKategorie() {
        return _kategorie;
    }

    public TableModel getKartenInfoModel() {
        Object[][] ob = new Object[_Kategoriekarten.kartenList.length][5];
        for (int i = 0; i < _Kategoriekarten.kartenList.length; i++) {
            StructKarte k = _Kategoriekarten.kartenList[i];
            ob[i][0] = false;
            ob[i][1] = k.kartenId;
            ob[i][2] = k.reihe;
            ob[i][3] = k.platz;
            ob[i][4] = false;
        }
        return (new DefaultTableModel(
                ob,
                new String[]{
            "Auswählen", "KartenID", "Reihe", "Sitzplatz", "Ermäßigt"
        }) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public void kartenBestellen(List<Object[]> bestellteKarten) throws CobraException {
        List<StructKarteBestellen> karten = new LinkedList();
        int kundenID = 0;
        for (Object[] o : bestellteKarten) {

            karten.add(new StructKarteBestellen((Integer) o[1], kundenID, Boolean.TRUE.equals(o[4])));
        }
        _client.verkaufSpeichern(karten);
        updateController();
    }

    private void updateController() {
//        try {
        _kategorie = _client.getKategorieInfo(_kategorie.kategId);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        try {
        _Kategoriekarten = _client.getAlleFreieKartenNachKategorie(_kategorie.kategId);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    public void cancelClicked() {
        CorbaMainGuiCtrl.KarteCancel(_veranstaltung.vid);
    }

    void setVeranstaltung(int veranstaltungID) {
//        try {
        _veranstaltung = _client.getVeranstaltungById(veranstaltungID);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    void setKategorieID(int kategorieID) {
//        try {
        _kategorie = _client.getKategorieInfo(kategorieID);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        try {
        _Kategoriekarten = _client.getAlleFreieKartenNachKategorie(_kategorie.kategId);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    public void loadKarten() {
        _Kategoriekarten = _client.getAlleFreieKartenNachKategorie(_kategorie.kategId);
    }
}
