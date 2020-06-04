package fr.ddab.arbre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ArbreDecision extends ArbreBinaire <ArbreDecision.decisions> {
	public static enum decisions { 
		ALLER_EN_HAUT, ALLER_EN_BAS, ALLER_A_GAUCHE, ALLER_A_DROITE, 
		RAMASSER_NOURRITURE, DEPOSER_NOURRITURE,
		VERIFIER_NOURRITURE, VERIFIER_FOURMILIERE, TRANSPORTE_NOURRITURE,
		NE_RIEN_FAIRE
	};
	//ALLER_FOURMILIERE

	//Arbre vide
	public ArbreDecision () {
		this (decisions.NE_RIEN_FAIRE);
	}

	public ArbreDecision (decisions valeur) {
		super (valeur);

	}
	
	public ArbreDecision construire (int profondeurMaximale) {
		Random r = new Random ();
		decisions [] tabDecisions = decisions.values ();

		return construire (profondeurMaximale, r, tabDecisions);
	}

	private ArbreDecision construire (int profondeurMaximale, Random r, decisions [] tabDecisions) {
		ArbreDecision arbreFourmi = new ArbreDecision ();
		arbreFourmi.setValeur (tabDecisions [r.nextInt (tabDecisions.length)]);
		if (arbreFourmi.estAction () || profondeurMaximale == 1) {
			arbreFourmi.insererFils (new ArbreDecision ());
			arbreFourmi.insererFils (new ArbreDecision ());
		} else if (arbreFourmi.estCondition ()) {
			arbreFourmi.insererFils (construire (profondeurMaximale - 1, r, tabDecisions));
			arbreFourmi.insererFils (construire (profondeurMaximale - 1, r, tabDecisions));
		}
		//3e cas : On a un arbre vide

		return arbreFourmi;
	}

	//inserer et supprimer désactiver ; fg = action si vrai
	public ArbreDecision getAction (boolean condition) {
		return (ArbreDecision) getFils (condition ? 1 : 2);
	}
	
	public void setAction (ArbreDecision nouveauFils, boolean condition) {
		if (!possedeFils (condition ? 1 : 2)) {
			for (int i = getNbreFils (); i <= (condition ? 1 : 2); ++i) {
				insererFils (new ArbreDecision ());
			}
		}
		setFils (condition ? 1 : 2, nouveauFils);
		//à faire que si nouvelleAction n'est pas neRienFaire
	}
	
	public boolean estCondition () {
		ArrayList <decisions> conditions = new ArrayList (3);
		conditions.add (decisions.VERIFIER_FOURMILIERE);
		conditions.add (decisions.VERIFIER_NOURRITURE);
		conditions.add (decisions.TRANSPORTE_NOURRITURE);
		return conditions.contains (getValeur ());
	}
	
	public boolean estFeuille () {
		return getValeur () == decisions.NE_RIEN_FAIRE;
	}
	
	public boolean estAction () {
		return !estCondition () && !estFeuille ();
	}
	
	public boolean estDeplacement () {
		ArrayList <decisions> deplacements = new ArrayList (4);
		deplacements.add (decisions.ALLER_EN_HAUT);
		deplacements.add (decisions.ALLER_EN_BAS);
		deplacements.add (decisions.ALLER_A_GAUCHE);
		deplacements.add (decisions.ALLER_A_DROITE);
		return deplacements.contains (getValeur ());
	}
	
	public ArbreDecision dupliquer () {
		ArbreDecision copieArbre = new ArbreDecision ();
		if (!estFeuille ()) {
			copieArbre.setValeur (valeur);
			copieArbre.setAction (getAction (true).dupliquer (), true);
			copieArbre.setAction (getAction (false).dupliquer (), false);
		}
		return copieArbre;
	}

	public void afficher () {
		if (!estFeuille ()) {
			System.out.print ("[" + valeur);
			if (estCondition ()) System.out.print (",");
			getAction (true).afficher ();
			getAction (false).afficher ();
			System.out.print ("]");
		}
	}
	
	public ArbreDecision optimiser () {
		HashMap <decisions, Boolean> conditionsVisitees = new HashMap (3);
		return optimiser (conditionsVisitees);
	}
	
	private ArbreDecision optimiser (HashMap <decisions, Boolean> conditionsVisitees) {
		ArbreDecision arbreOptimise = new ArbreDecision ();
		if (!estFeuille ()) {
			if (estCondition ()) {
				if (conditionsVisitees.containsKey (valeur)) {
					//Je remplace l'arbre par le fils concerné
					arbreOptimise = getAction (conditionsVisitees.get (valeur)).optimiser (conditionsVisitees);
				} else {
					//Sinon j'ajoute la clé dans mon tableau
					arbreOptimise.setValeur (valeur);
					conditionsVisitees.put (valeur, true);
					arbreOptimise.setAction (getAction (true).optimiser (conditionsVisitees), true);
					conditionsVisitees.put (valeur, false);
					arbreOptimise.setAction (getAction (false).optimiser (conditionsVisitees), false);
				}
			} else {
				arbreOptimise.setValeur (valeur);
				arbreOptimise.setAction (getAction (true).optimiser (conditionsVisitees), true);
				arbreOptimise.setAction (getAction (false).optimiser (conditionsVisitees), false);
			}
		}
		return arbreOptimise; //notamment un arbre vide
	}
	
	public ArbreDecision optimiser2 () {
		ArbreDecision arbreOptimise = dupliquer ();
		for (int i = 0; i < 10; ++i) {
			arbreOptimise = arbreOptimise.optimiser2_ ();
		}
		return arbreOptimise;
	}
	
	//boolean car il faut optimiser plusieurs fois ; tableau pour passer en référence
	private ArbreDecision optimiser2_ () {
		ArbreDecision arbreOptimise = new ArbreDecision ();
		if (!estFeuille ()) {
			if (estCondition ()) {
				if (getAction (true).comparer (getAction (false))) {
					arbreOptimise = getAction (true); //par exemple
				} else {
					arbreOptimise.setValeur (valeur);
				}
				arbreOptimise.setAction (getAction (true).optimiser2_ (), true);
				arbreOptimise.setAction (getAction (false).optimiser2_ (), false);
			}
		}
		return arbreOptimise;
	}

	//suppose que l'on ne remplace pas tout l'arbre, mais seulement un des fils
	private ArbreDecision choisirFilsAleatoire (Random r) {
		if (estFeuille ()) {
			return this;
		}
		
		boolean brancheChoisie = r.nextBoolean ();
		boolean arreter = r.nextBoolean ();
		
		ArbreDecision filsAleatoire = getAction (brancheChoisie);
		if (filsAleatoire.estAction () || arreter) {
			return filsAleatoire;
		}
		return filsAleatoire.choisirFilsAleatoire (r);
	}

	public ArbreDecision choisirFilsAleatoire () {
		return choisirFilsAleatoire (new Random ());
	}

	public ArbreDecision croiserArbre (ArbreDecision secondArbre) {
		ArbreDecision hybride = dupliquer ();
		ArbreDecision ancienFils = hybride.choisirFilsAleatoire ();
		ArbreDecision nouveauFils = secondArbre.choisirFilsAleatoire ().dupliquer ();
		hybride.setFils (ancienFils, nouveauFils);
		return hybride;
		//voir nettoyage arbre + optimiser
	}

	public void supprimerAction (boolean condition) {
		setAction (new ArbreDecision (), condition);
	}
}
