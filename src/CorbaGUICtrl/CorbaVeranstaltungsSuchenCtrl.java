
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CorbaGUICtrl;

import client.CorbaClient_MO;
import corba.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Monika
 */
public class CorbaVeranstaltungsSuchenCtrl {


    //Neu
    private ArrayList<StructVeranstaltung> _veranstaltungen = new ArrayList();
    private CorbaClient_MO _client;

    public CorbaVeranstaltungsSuchenCtrl(CorbaClient_MO client) {
         _client = client;
//        try {
            _veranstaltungen = _client.sucheVeranstaltungNachKriterien("", "", "");
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    public void searchingForEvents(String date, String place, String artist) {
        Date d = null;
        try {
//            SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
//            d = sdfToDate.parse(date);
          
        } catch (Exception e) {
        }
        System.out.println(d);
//        try {
            _veranstaltungen = _client.sucheVeranstaltungNachKriterien(date, place, artist);
//        } catch (RemoteException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    public void VeranstaltungAnzeigen(int vId) {
       CorbaMainGuiCtrl.VeranstaltungAusgewaehlt(vId);
    }

    public TableModel getVeranstaltungInfoModel() {
        Object[][] ob = new Object[_veranstaltungen.size()][5];
        for (int i = 0; i < _veranstaltungen.size(); i++) {
            StructVeranstaltung ev = _veranstaltungen.get(i);
            ob[i][0] = ev.vDatum;
            ob[i][1] = ev.vName;
            ob[i][2] = ev.vOrt;
            ob[i][3] = ev.kuenstler;
            ob[i][4] = ev.vid;
        }
        return (new DefaultTableModel(ob, new String[]{
            "Datum", "Name", "Ort", "Künstler", "Id"
        }) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public void CancelClicked() {
        CorbaMainGuiCtrl.VeranstaltungCancel();
    }
}
