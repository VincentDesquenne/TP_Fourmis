package fr.ddab.arbre;

import java.util.ArrayList;

//tests unitaires
//Arbre de type homogène //javadoc
class Arbre <T> {
	protected int nbreMaxFils;
	protected T valeur;
	protected ArrayList <Arbre <T>> fils;
	
	// CONSTRUCTEURS
	//Constructeur d'un arbre vide
	public Arbre (int nbreMaxFils) {
		this.nbreMaxFils = nbreMaxFils; //try catch : exception personnalisée ?
		this.fils = new ArrayList <Arbre <T>> ();
	}
	
	public Arbre (int nbreMaxFils, T valeur) {
		this (nbreMaxFils);
		this.valeur = valeur;
	}
	
	public Arbre () {
		this (2);
	}
	
	// GET / SET
	public int getNbreMaxFils () {
		return nbreMaxFils;
	}
	
	public int getNbreFils () {
		return fils.size ();
	}
	
	public T getValeur () {
		return valeur;
	}
	
	public void setValeur (T valeur) {
		this.valeur = valeur;
	}
	
	//les indices commencent à 1
	public Arbre <T> getFils (int position) {
		return fils.get (position - 1); //try catch
	}
	
	public void setFils (int position, Arbre <T> nouveauFils) {
		fils.set (position - 1, nouveauFils); //try catch
	}
	
	//remplace TOUS les FILS
	public void setFils (Arbre <T> ancienFils, Arbre <T> nouveauFils) {
		int nbreFils = getNbreFils ();
		if (!estFeuille ()) {
			for (int i = 1; i <= nbreFils; ++i) {
				if (getFils (i).comparer(ancienFils)) {
					setFils (i, nouveauFils);
				}
			}
		}
	}
	
	// TESTS ARBRE
	public boolean estFeuille () {
		return getNbreFils () == 0;
	}
	
	public boolean possedeFils (int position) {
		return getNbreFils () >= position;
	}
	
	// MODIFICATION ARBRE
	public void insererFils (int position, Arbre <T> nouveauFils) {
		fils.add (position - 1, nouveauFils); //try catch
	}
	
	public void insererFils (Arbre <T> nouveauFils) {
		fils.add (nouveauFils);
	}
	
	public void supprimerFils (int position) {
		fils.remove (position - 1);
	}
	
	public void supprimerFils () {
		fils.clear ();
	}
	
	// AUTRES FONCTIONS
	public Arbre <T> dupliquer () {
		Arbre <T> copieArbre = new Arbre <T> (nbreMaxFils, valeur);
		int nbreFils = getNbreFils ();
		
		if (!estFeuille ()) {
			for (int i = 1; i <= nbreFils; ++i) {
				copieArbre.insererFils (getFils (i).dupliquer ());
			}
		}
		return copieArbre;
	}
	
	//Si l'arbre est vide dès le départ : valeur par défaut + affichable ?
	public void afficher () {
		int nbreFils = getNbreFils ();
		System.out.print ("[" + valeur);
		if (!estFeuille ()) {
			System.out.print(",");
			for (int i = 1; i <= nbreFils; ++i) {
				getFils (i).afficher ();
			}
		}
		System.out.print("]");
	}
	
	public boolean comparer (Arbre <T> secondArbre) {
		int nbreFils = getNbreFils ();
		int nbreFilsSecondArbre = secondArbre.getNbreFils ();
		if (nbreFils != nbreFilsSecondArbre) {
			return false;
		} else if (getValeur () != secondArbre.getValeur ()) {
			return false;
		}
		for (int i = 1; i <= nbreFils; ++i) {
			if (!getFils (i).comparer (secondArbre.getFils (i))) {
				return false;	
			}
		}
		return true;
	}
}