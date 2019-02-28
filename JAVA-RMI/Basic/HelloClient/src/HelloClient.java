/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.*;

/**
 *
 * @author Red
 */

public class HelloClient
{
    public static  void main(String arg[])
    {
        try
        {
            IHello h = (IHello)Naming.lookup("nomdelobjet");
            String messageRecu = h.ditBonjour();
            System.out.println(messageRecu);
        }
        catch (Exception e) {System.err.println("Erreur:"+e);}
    }
}