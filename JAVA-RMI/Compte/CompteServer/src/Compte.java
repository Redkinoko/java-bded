
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Red
 */
public class Compte extends UnicastRemoteObject implements ICompte 
{
    private int solde = 0;
    
    public Compte() throws RemoteException {
        super();
        solde = 0;
    }
    
    @Override
    public synchronized void depot(int val) throws RemoteException {
        solde += val;
    }

    @Override
    public synchronized void retrait(int val) throws RemoteException {
        solde -= val;
    }

    @Override
    public synchronized int afficherSolde() throws RemoteException {
        return solde;
    }
    
}
