
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Red
 */
public class BanqueMap extends UnicastRemoteObject implements IBanque 
{
    Connection co;
    
    public BanqueMap(Connection c) throws RemoteException {
        super();
        co = c;
    }
    
    @Override
    public synchronized int createCompte()
    {
        System.out.println("["+new Date().toString()+"] Création de compte");
        int id = 0;
        try
        {
            Statement s = co.createStatement();
            String query = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'compte'";
            ResultSet r = s.executeQuery(query);
            id = (r.first())?r.getInt("AUTO_INCREMENT"):0;
            
            StringInsertBuilder str = new StringInsertBuilder();
            str.addColumns("id", id);
            str.addColumns("solde", 0);
            query = str.build("compte");
            s.executeUpdate(query);
            
            r.close();
            s.close();
        }
        catch (SQLException ex)
        {
            System.out.println();
        }
        return id;
    }
    
    @Override
    public synchronized boolean getCompte(int id) throws RemoteException {
        System.out.println("["+new Date().toString()+"] Compte n°"+id);
        try {
            String query = "SELECT * FROM compte WHERE id = "+id;
            Statement s = co.createStatement();
            ResultSet r = s.executeQuery(query);
            if(r.first())
            {
                r.close();
                s.close();
                return true;
            }
            r.close();
            s.close();
            return false;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return false;
    }

    @Override
    public synchronized void depot(int id, int val) throws RemoteException {
        System.out.println("["+new Date().toString()+"] Dépot de "+val+"$ sur le compte n°"+id);
        try
        {
            String query = "UPDATE compte SET solde = solde + "+val+" WHERE id = "+id;
            Statement s = co.createStatement();
            s.executeUpdate(query);
            s.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    @Override
    public synchronized void retrait(int id, int val) throws RemoteException {
        System.out.println("["+new Date().toString()+"] Retrait de "+val+"$ sur le compte n°"+id);
        try
        {
            String query = "UPDATE compte SET solde = solde - "+val+" WHERE id = "+id;
            Statement s = co.createStatement();
            s.executeUpdate(query);
            s.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    @Override
    public synchronized int afficherSolde(int id) throws RemoteException {
        int solde = 0;
        try
        {
            String query = "SELECT solde FROM compte WHERE id = "+id;
            Statement s = co.createStatement();
            ResultSet r = s.executeQuery(query);
            solde = (r.first())?r.getInt("solde"):0;
            r.close();
            s.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return solde;
    }
    
}
