package corba.CorbaConterollerInterfacePackage;


/**
* TicTakTicket/corba/CorbaConterollerInterfacePackage/kategorieListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TicTakTicket.idl
* Mittwoch, 20. November 2013 20:15 Uhr MEZ
*/

public final class kategorieListHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.StructKategorieInformation value[] = null;

  public kategorieListHolder ()
  {
  }

  public kategorieListHolder (corba.StructKategorieInformation[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.CorbaConterollerInterfacePackage.kategorieListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.CorbaConterollerInterfacePackage.kategorieListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.CorbaConterollerInterfacePackage.kategorieListHelper.type ();
  }

}
