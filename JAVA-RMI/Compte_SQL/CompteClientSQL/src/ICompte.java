

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
public interface ICompte extends Remote 
{
    public void depot(int val) throws java.rmi.RemoteException;
    public void retrait(int val) throws java.rmi.RemoteException;
    public int afficherSolde() throws java.rmi.RemoteException;
}
