package corba;

/**
* TicTakTicket/corba/StructKategorieInformationHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TicTakTicket.idl
* Mittwoch, 20. November 2013 20:15 Uhr MEZ
*/

public final class StructKategorieInformationHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.StructKategorieInformation value = null;

  public StructKategorieInformationHolder ()
  {
  }

  public StructKategorieInformationHolder (corba.StructKategorieInformation initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.StructKategorieInformationHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.StructKategorieInformationHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.StructKategorieInformationHelper.type ();
  }

}