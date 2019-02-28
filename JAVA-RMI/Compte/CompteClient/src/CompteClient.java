/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.*;

/**
 *
 * @author Red
 */

public class CompteClient
{
    public static  void main(String arg[])
    {
        try
        {
            ICompte c = (ICompte)Naming.lookup("nomdelobjet");
            System.out.println("Solde : " + c.afficherSolde());
            c.retrait(10);
            System.out.println("Solde : " + c.afficherSolde());
            c.depot(20);
            System.out.println("Solde : " + c.afficherSolde());
        }
        catch (Exception e) {System.err.println("Erreur:"+e);}while(true){}
    }
}