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
    public static void main(String arg[])
    {
        try
        {
            ICompte c = (ICompte)Naming.lookup("nomdelobjet");
            System.out.println("Solde : " + c.afficherSolde());
            for(int i=0 ; i<1000 ; i++)
            {
                int r = 1;
                System.out.print("Retrait de "+r+"$ : ");
                c.retrait(r);
                System.out.println("Solde : " + c.afficherSolde());
                
                int d = 0;
                System.out.print("Dépot de "+d+"$ : ");
                c.depot(d);
                System.out.println("Solde : " + c.afficherSolde());
            }
        }
        catch (Exception e) {System.err.println("Erreur:"+e);}
    }
}