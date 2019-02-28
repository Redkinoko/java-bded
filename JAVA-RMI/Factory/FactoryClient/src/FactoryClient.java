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

public class FactoryClient
{
    public static  void main(String arg[])
    {
        try
        {
            IBanque c = (IBanque)Naming.lookup("nomdelobjet");
            int id = c.getCompte(0);
            System.out.println("Solde["+id+"] : " + c.afficherSolde(id));
            c.retrait(id, 10);
            System.out.println("Solde["+id+"] : " + c.afficherSolde(id));
            c.depot(id, 20);
            System.out.println("Solde["+id+"] : " + c.afficherSolde(id));
        }
        catch (Exception e) {System.err.println("Erreur:"+e);}
    }
}