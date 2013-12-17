/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CorbaGUICtrl;

import client.CorbaClient_MO;
import corba.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Stefan Dietrich
 */
public class CorbaVeranstaltungKategorieCtrl {

    private  StructVeranstaltung _veranstaltung;
    private CorbaClient_MO _client;
    private ArrayList<StructKategorieInformation> _kategorien;

    public CorbaVeranstaltungKategorieCtrl(int veranstaltungID, CorbaClient_MO client) {
        _client = client;
//        try {
            _veranstaltung = _client.getVeranstaltungById(veranstaltungID);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        try {
            _kategorien = _client.getKategorieInfoVonVeranstaltung(new StructVeranstaltungAnzeigen(_veranstaltung.vid));
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    public StructVeranstaltung getVeranstaltung() {
        return _veranstaltung;
    }

    public TableModel getKategorieInfoModel() {
        Object[][] ob = new Object[_kategorien.size()][4];
        for (int i = 0; i < _kategorien.size(); i++) {
            ob[i][0] = _kategorien.get(i).kategId;
            ob[i][1] = _kategorien.get(i).katName;
            ob[i][2] = _kategorien.get(i).katPreis + "€";
            ob[i][3] = _kategorien.get(i).freiePlaetze;
        }
        return (new DefaultTableModel(
                ob,
                new String[]{
            "KategorieID", "Kategoriename", "Kategoriepreis", "Freie Plätze"
        }) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public void selectKategorie(int id) {
        StructKategorieInformation selectedKategorie = null;
//        try {
            selectedKategorie = _client.getKategorieInfo(id);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
        if (selectedKategorie != null) {
            CorbaMainGuiCtrl.KategorieAusgewählt(_veranstaltung.vid, selectedKategorie.kategId);
        }
    }

    public void cancelButton() {
        CorbaMainGuiCtrl.KategorieCancel();
    }

    void setVeranstaltungsID(int id) {
//        try {
            _veranstaltung = _client.getVeranstaltungById(id);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        try {
            _kategorien = _client.getKategorieInfoVonVeranstaltung(new StructVeranstaltungAnzeigen(_veranstaltung.vid));
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }
}
