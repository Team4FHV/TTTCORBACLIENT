package corba;


/**
* TicTakTicket/corba/StructKategorieKarte.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TicTakTicket.idl
* Mittwoch, 20. November 2013 20:15 Uhr MEZ
*/

public final class StructKategorieKarte implements org.omg.CORBA.portable.IDLEntity
{
  public corba.StructKarte kartenList[] = null;

  public StructKategorieKarte ()
  {
  } // ctor

  public StructKategorieKarte (corba.StructKarte[] _kartenList)
  {
    kartenList = _kartenList;
  } // ctor

} // class StructKategorieKarte
