package corba;


/**
* TicTakTicket/corba/StructVeranstaltung.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TicTakTicket.idl
* Mittwoch, 20. November 2013 20:15 Uhr MEZ
*/

public final class StructVeranstaltung implements org.omg.CORBA.portable.IDLEntity
{
  public int vid = (int)0;
  public String vDatum = null;
  public String vName = null;
  public String vOrt = null;
  public String kuenstler = null;
  public boolean ermaessigt = false;

  public StructVeranstaltung ()
  {
  } // ctor

  public StructVeranstaltung (int _vid, String _vDatum, String _vName, String _vOrt, String _kuenstler, boolean _ermaessigt)
  {
    vid = _vid;
    vDatum = _vDatum;
    vName = _vName;
    vOrt = _vOrt;
    kuenstler = _kuenstler;
    ermaessigt = _ermaessigt;
  } // ctor

} // class StructVeranstaltung