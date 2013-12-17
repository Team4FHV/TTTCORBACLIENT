/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import corba.CobraException;
import corba.CorbaConterollerInterface;
import corba.CorbaConterollerInterfaceHelper;
import corba.StructKarteBestellen;
import corba.StructKategorieInformation;
import corba.StructVeranstaltung;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

/**
 *
 * @author Monika
 */
public class CorbaClient_MO {

    CorbaConterollerInterface Stub;
    String[] args;

    public CorbaClient_MO(String[] args) {
        this.args = args;
        startClient();
    }

    private void startClient() {
        try {
            System.out.println("Bitte geben Sie eine HOST ein");
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String host = console.readLine();

            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "2050");
            props.put("org.omg.CORBA.ORBInitialHost", host);
            ORB orb = ORB.init(args, props);
            org.omg.CORBA.Object objRef;
            objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            Stub = CorbaConterollerInterfaceHelper.narrow(ncRef.resolve_str("ControllerObject"));
            System.out.println("Obtained a handle on server object: ControllerObject ");
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    public ArrayList<StructVeranstaltung> sucheVeranstaltungNachKriterien(String datum, String ort, String kuenstler) {
        ArrayList<StructVeranstaltung> result = new ArrayList();
        corba.StructVeranstaltung[] list = Stub.sucheVeranstaltungNachKriterien(datum, ort, kuenstler);
        result.addAll(Arrays.asList(list));
        return result;
    }

    public ArrayList<StructKategorieInformation> getKategorieInfoVonVeranstaltung(corba.StructVeranstaltungAnzeigen veranstaltung) {
        ArrayList<StructKategorieInformation> result = new ArrayList();
        StructKategorieInformation[] list = Stub.getKategorieInfoVonVeranstaltung(veranstaltung);
        result.addAll(Arrays.asList(list));
        return result;
    }

    public corba.StructKategorieKarte getAlleFreieKartenNachKategorie(int id) {
        return Stub.getAlleFreieKartenNachKategorie(new corba.StructKategorieAuswaehlen(id));
    }
   
    public void verkaufSpeichern(List<StructKarteBestellen> list) {
        corba.StructKarteBestellen[] karten = new StructKarteBestellen[list.size()];
        for (int i = 0; i < list.size(); i++) {
            karten[i] = list.get(i);
        }
        try {
            Stub.verkaufSpeichern(karten);
        } catch (CobraException ex) {
            Logger.getLogger(CorbaClient_MO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public corba.StructKategorieInformation getKategorieInfo(int id) {
        return Stub.getKategorieInfo(id);
    }
    
    public StructVeranstaltung getVeranstaltungById(int veranstaltungID) {
        StructVeranstaltung[] veranstaltungen =  Stub.sucheVeranstaltungNachKriterien("", "", "");
        for(int i = 0; i<veranstaltungen.length; i++)
        {
            if(veranstaltungen[i].vid == veranstaltungID)
            {
                return veranstaltungen[i];
            }
        }
        return new StructVeranstaltung(veranstaltungID, "", "", "", "", true);
    } 
}
