/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Red
 */

public class FactoryServer
{

    private static String DB_TYPE       = "mysql";
    private static String DB_ADDRESS    = "localhost";
    private static    int DB_PORT       = 3306;
    private static String DB_NAME       = "bded";
    
    private static String DB_LOGIN      = "root";
    private static String DB_PASSWORD   = "";
    
    public static void main(String arg[])
    {
        Connection co;
        try
        {
            co = DriverManager.getConnection(
                "jdbc:"+DB_TYPE+"://"+DB_ADDRESS+":"+DB_PORT+"/"+DB_NAME+"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", 
                DB_LOGIN, 
                DB_PASSWORD
            );
            System.out.println("Ouverture de la connexion.");
            //--------------------------------------------------
            if(co != null && !co.isClosed())
            {
                try 
                {
                    LocateRegistry.createRegistry(1099);
                    truncateCompte(co);
                    BanqueMap s = new BanqueMap(co);
                    String nom = "nomdelobjet";
                    Naming.rebind(nom ,s);// enregistrement
                    System.out.println("Serveur enregistré");
                    while(true){}
                }
                catch(Exception e)
                {
                    System.err.println("Erreur :"+e);
                }
                finally
                {
                    co.close();
                    System.out.println("Fermeture de la connexion.");
                }
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void truncateCompte(Connection co)
    {
        System.out.println("Vidage de la table 'compte'");
        try
        {
            String query = "TRUNCATE TABLE compte;";
            Statement s = co.createStatement();
            s.execute(query);
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }
}
