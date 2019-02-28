

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Red
 */
import java.rmi.*;
public interface IBanque extends Remote 
{
    public int getCompte(int id) throws java.rmi.RemoteException;
    public void depot(int id, int val) throws java.rmi.RemoteException;
    public void retrait(int id, int val) throws java.rmi.RemoteException;
    public int afficherSolde(int id) throws java.rmi.RemoteException;
}
