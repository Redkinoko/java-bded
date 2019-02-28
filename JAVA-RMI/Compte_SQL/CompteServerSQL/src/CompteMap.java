
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Red
 */
public class CompteMap extends UnicastRemoteObject implements ICompte {
    
    private int id;
    private int solde;
    
    private Connection co;
    
    public CompteMap(Connection c) throws RemoteException
    {
        super();
        co      = c;
        id      = 0;
        solde   = 0;
    }
    
    @Override
    public synchronized void depot(int val) throws RemoteException {
        String query = "UPDATE compte ";
        query       += "SET solde = solde + "+val+" ";
        query       += "WHERE id = "+id+";";
        
        try
        {
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
    public synchronized void retrait(int val) throws RemoteException {
        String query = "UPDATE compte ";
        query       += "SET solde = solde - "+val+" ";
        query       += "WHERE id = "+id+";";

        try 
        {
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
    public synchronized int afficherSolde() throws RemoteException {
        String query = "SELECT solde ";
        query       += "FROM compte ";
        query       += "WHERE id = "+id+";";
        int i = 0;
        try
        {
            Statement s = co.createStatement();
            ResultSet r = s.executeQuery(query);
            i = (r.first())?r.getInt("solde"):0;
            r.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return i;
    }

    public void setCo(Connection c) {
        this.co = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    
    
}
