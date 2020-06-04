package fr.ddab.plateau;

import java.util.ArrayList;

/**
 * <b>Grille est une classe qui compose Monde.</b>
 * <p>C'est une grille a deux dimensions.</p>
 * 
 *
 */

public class Grille {
    private ArrayList <ArrayList <Case>> tabCase;
	
    /**
     * Constructeur de la classe. Construit la grille en affectant une Case a chaque position du tableau.
     * @param taille : taille de la Grille a deux dimensions.
     */
    

    public Grille (ArrayList <Short> taille) {
		ArrayList <ArrayList <Case>> tabCase = new ArrayList <ArrayList <Case>> (taille.get(0));
	    for(byte i = 0; i < taille.get (0); i++)  {
	    	tabCase.add (new ArrayList <Case>());
			for (byte j = 0; j < taille.get (1); j++) {
				tabCase.get(i).add(new Case());
			}
	    }
	    this.tabCase=tabCase;
	}
	
	/**
	 * Retourne la taille de Grille selon X et Y.
	 * @return La taille de Grille selon X et Y.
	 */
	
	
	public ArrayList <Short> getTaille() {
		ArrayList <Short> taille = new ArrayList <Short> (2);
		taille.add ((short) tabCase.size ());
		taille.add ((short) tabCase.get (0).size ());
		return taille;
	}
	
	/**
	 * Retourne le tableau a deux dimensions de Grille.
	 * @return Le tableau a deux dimensions de Grille.
	 */
	    
	public ArrayList <ArrayList <Case>> getTabCase() {
		return tabCase;
	} 

	/**
	 * Retourne la Case de Grille selon la position passee en parametre.
	 * @param position : position (X,Y) sur Grille
	 * @return La Case de Grille selon la position passee en parametre.
	 * 
	 * @see Case
	 */
	
	public Case getCase (ArrayList <Short> position) {
		return tabCase.get (position.get (0)).get (position.get (1));
	}
	    
	/**
	 * Transforme une Case de Grille en Fourmiliere.
	 * @param position : position (X,Y) sur Grille.
	 * 
	 * @see Case
	 * @see Fourmiliere
	 */
	
	public void TransformerCaseEnFourmiliere (ArrayList <Short> position) {
		tabCase.get (position.get (0)).set (position.get (1), new Fourmiliere ());
    }
	
	/**
	 * Transforme une Case de Grille en Nourriture.
	 * @param position : position (X,Y) sur Grille.
	 * 
	 * @see Case
	 * @see Nourriture
	 */
	  
	public void TransformerCaseEnNourriture(ArrayList <Short> position) {
		tabCase.get (position.get (0)).set (position.get (1), new Nourriture ());
    }
	
	/**
	 * Transforme une Case de Grille en case vide.
	 * @param position : position (X,Y) sur Grille.
	 * 
	 * @see Case
	 */
	    
	public void TransformerCaseEnVide(ArrayList <Short> position) {
		tabCase.get (position.get (0)).set (position.get (1), new Case ());
    }	    
	
	/**
	 * Affiche Grille en affichant toutes les Case qui la composent.
	 * @return Affichage de Grille.
	 * 
	 * @see Case+afficheCase()
	 * @see Monde+afficheMonde()
	 */
	public String toString() {
		String s="";
		for (int i=0;i<tabCase.size();i++) {
			for (int j=0;j<tabCase.get(0).size();j++) {
				s+=tabCase.get(i).get(j).afficheCase();
			}
			s+="\n";
			
		}
		return s;
	}
}
