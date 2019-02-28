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
            IBanque b = (IBanque)Naming.lookup("nomdelobjet");
            int id = 2;
            if(!b.getCompte(id))
            {
                System.out.println("Le compte n°"+id+" n'existe pas!");
                System.out.println("Demande de création...");
                id = b.createCompte();
                System.out.println("Le compte attribué est le n°"+id);
            }
            else
            {
                System.out.println("Le compte n°"+id+" trouvé!");
            }
            
            int d = 10;
            b.depot(id, d);
            System.out.println("Dépot de "+d+"$ : Solde = "+b.afficherSolde(id));
            
            int r = 5;
            b.retrait(id, r);
            System.out.println("Retrait de "+r+"$ : Solde = "+b.afficherSolde(id));
        }
        catch (Exception e) {System.err.println("Erreur:"+e);}
    }
}