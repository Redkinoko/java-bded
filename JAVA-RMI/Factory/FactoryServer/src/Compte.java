/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Red
 */
public class Compte {
    
    private int solde = 0;
    
    public Compte() {
        solde = 0;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    public void retrait(int val) {
        this.solde -= val;
    }
    
    public void depot(int val) {
        this.solde += val;
    }
    
    @Override
    public String toString() {
        return "Solde: "+solde;
    }
}
