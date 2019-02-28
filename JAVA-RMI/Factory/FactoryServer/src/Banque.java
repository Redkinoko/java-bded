
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Red
 */
public class Banque extends UnicastRemoteObject implements IBanque 
{
    private HashMap<Integer, Compte> comptes;
    
    public Banque() throws RemoteException {
        super();
        comptes = new HashMap<>();
    }
    
    public int ajoutCompte() {
        int id = comptes.size()+1;
        comptes.put(id, new Compte());
        return id;
    }
    
    @Override
    public int getCompte(int id) throws RemoteException {
        Compte c = comptes.getOrDefault(id, null);
        if(c!=null) return id;
        return ajoutCompte();
    }

    @Override
    public void depot(int id, int val) throws RemoteException {
        Compte c = comptes.get(id);
        if(c!=null) c.depot(val);
    }

    @Override
    public void retrait(int id, int val) throws RemoteException {
        Compte c = comptes.get(id);
        if(c!=null) c.retrait(val);
    }

    @Override
    public int afficherSolde(int id) throws RemoteException {
        Compte c = comptes.get(id);
        if(c!=null) return c.getSolde();
        return 0;
    }
    
}
