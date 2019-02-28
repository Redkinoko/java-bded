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

public class HelloServer extends UnicastRemoteObject implements IHello 
{
    public HelloServer() throws RemoteException
    {
        super();
    }
    public String ditBonjour() throws RemoteException
    {
        return "bonjour a tous";
    }
    public static void main(String arg[])
    {
        try 
        {
            LocateRegistry.createRegistry(1099);
            
            HelloServer s = new HelloServer();
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
