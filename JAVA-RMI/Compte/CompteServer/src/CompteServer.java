/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Red
 */

public class CompteServer
{

    public static void main(String arg[])
    {
        try 
        {
            LocateRegistry.createRegistry(1099);
            
            Compte s = new Compte();
            String nom = "nomdelobjet";
            Naming.rebind(nom ,s);// enregistrement
            System.out.println("Serveur enregistré");
        }
        catch(Exception e)
        {
            System.err.println("Erreur :"+e);
        }
    }
}
