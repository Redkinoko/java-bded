/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Red
 */

public class CompteServer
{
    private static String DB_TYPE       = "mysql";
    private static String DB_ADDRESS    = "localhost";
    private static    int DB_PORT       = 3306;
    private static String DB_NAME       = "bded";
    
    private static String DB_LOGIN      = "root";
    private static String DB_PASSWORD   = "";
    
    public static void main(String arg[]) throws RemoteException
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
                    CompteMap s = getCompte(co);
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
    
    public static CompteMap getCompte(Connection co) throws SQLException, RemoteException
    {
        String query = "SELECT * FROM compte;";
        Statement s = co.createStatement();
        ResultSet r = s.executeQuery(query);
        CompteMap compte = new CompteMap(co);
        int id = 0;
        if(r.first()) {
            id    = r.getInt("id");
            int solde = r.getInt("solde");
            System.out.println("Un compte trouvé : (id: "+id+", solde: "+solde+")");
            compte.setId(id);
            compte.setSolde(solde);
        }
        else
        {
            query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'compte'";
            r = s.executeQuery(query);
            int i = (r.first())?r.getInt("AUTO_INCREMENT"):0;
            compte.setId(i);
            compte.setSolde(0);
            createCompte(s, compte);
            System.out.println("Aucun compte trouvé.");
            System.out.println("Création...");
        }
        
        s.close();
        
        return compte;
    }
    
    public static void createCompte(Statement s, CompteMap c)
    {
        try
        {
            StringInsertBuilder str = new StringInsertBuilder();
            str.addColumns("id", c.getId());
            str.addColumns("solde", c.getSolde());
            String query = str.build("compte");
            
            s.executeUpdate(query);
            s.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    public static void truncateCompte(Connection co)
    {
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
