

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CorbaGUICtrl;

import client.CorbaClient_MO;
import CorbaGUI.*;


/**
 *
 * @author Stefan Dietrich
 */
public class CorbaMainGuiCtrl {

    
    private static CorbaVeranstaltungSuchen _veranstaltungSuchen;
    private static CorbaVeranstaltungKategorie _veranstaltungKategorie;
    private static CorbaKartenInfo _kartenInfo;
    private static CorbaKartenInfoCtrl _kartenInfoCtrl;
    private static CorbaVeranstaltungKategorieCtrl _veranstaltungKategorieCtrl;
    private static CorbaVeranstaltungsSuchenCtrl _veranstaltungSuchenCtrl;
    private static CorbaClient_MO _client;

    public static void VeranstaltungAusgewaehlt(int veranstaltungID) {
        _veranstaltungSuchen.setVisible(false);
        _veranstaltungKategorie = new CorbaVeranstaltungKategorie(getVeranstaltungKategorieCtrl(veranstaltungID));
        _veranstaltungSuchen.Quit();
        _veranstaltungSuchen = null;
    }

    public static void KategorieAusgewählt(int veranstaltungID, int kategorieID) {
        _veranstaltungKategorie.setVisible(false);
        _kartenInfo = new CorbaKartenInfo(getKartenInfoCtrl(veranstaltungID, kategorieID));
        _veranstaltungKategorie.Quit();
        _veranstaltungKategorie = null;
    }

    public static void KategorieCancel() {
        _veranstaltungKategorie.setVisible(false);
        _veranstaltungSuchen = new CorbaVeranstaltungSuchen(getVeranstaltungSuchenCtrl());
        _veranstaltungKategorie.Quit();
        _veranstaltungKategorie = null;
    }

    public static void KarteCancel(int veranstaltungID) {
        _kartenInfo.setVisible(false);
        _veranstaltungKategorie = new CorbaVeranstaltungKategorie(getVeranstaltungKategorieCtrl(veranstaltungID));
        _kartenInfo.Quit();
        _kartenInfo = null;
    }

    public static void VeranstaltungCancel() {
        _veranstaltungSuchen.setVisible(false);
        _veranstaltungSuchen.Quit();
        _veranstaltungSuchen = null;
        System.exit(0);

    }

    public static void main(String[] args) throws Exception {
        _client = new CorbaClient_MO(args); 
        _veranstaltungSuchen = new CorbaVeranstaltungSuchen(getVeranstaltungSuchenCtrl());
    }

    public static CorbaVeranstaltungsSuchenCtrl getVeranstaltungSuchenCtrl() {
        if (_veranstaltungSuchenCtrl == null) {
            _veranstaltungSuchenCtrl = new CorbaVeranstaltungsSuchenCtrl(_client);
        }
        return _veranstaltungSuchenCtrl;
    }

    public static CorbaVeranstaltungKategorieCtrl getVeranstaltungKategorieCtrl(int id) {
        if (_veranstaltungKategorieCtrl == null) {
            _veranstaltungKategorieCtrl = new CorbaVeranstaltungKategorieCtrl(id, _client);
        } else {
            _veranstaltungKategorieCtrl.setVeranstaltungsID(id);
        }
        return _veranstaltungKategorieCtrl;
    }

    public static CorbaKartenInfoCtrl getKartenInfoCtrl(int veranstaltungID, int kategorieID) {
        if (_kartenInfoCtrl == null) {
            _kartenInfoCtrl = new CorbaKartenInfoCtrl(veranstaltungID, kategorieID, _client);
        } else {
            _kartenInfoCtrl.setVeranstaltung(veranstaltungID);
            _kartenInfoCtrl.setKategorieID(kategorieID);
        }
        return _kartenInfoCtrl;
    }
}
