package fr.ddab.arbre;

import fr.ddab.arbre.ArbreDecision.decisions;

public class Test {
	public static void main (String [] args) {
		ArbreBinaire <Integer> arbre = new ArbreBinaire <Integer> ();
		arbre.setValeur(2);
		arbre.insererFils(new ArbreBinaire <Integer> ());
		arbre.getFils(1).setValeur(3);
		arbre.getFils(1).insererFils(new ArbreBinaire <Integer> ());
		arbre.getFils(1).getFils (1).setValeur(4);
		arbre.insererFils(new ArbreBinaire <Integer> ());
		arbre.getFils(2).setValeur(5);
		ArbreBinaire <Integer> arbre2 = arbre.dupliquer();
		arbre.afficher ();
		System.out.println();
		arbre.setValeur(3);
		arbre.afficher ();
		System.out.println();
		arbre2.afficher();
		System.out.println();
//		arbre2.getFils(1).getFils(1).rien();

		ArbreDecision a1 = new ArbreDecision ().construire (5);
		a1.afficher();
		System.out.println();
		ArbreDecision a2 = a1.dupliquer ();
		a2.afficher();
		System.out.println();
		ArbreDecision a3 = a1.dupliquer ();
		//a3.setValeur(decisions.ALLER_A_GAUCHE);
		System.out.println(a1.comparer(a2)); //true
		System.out.println(a1.comparer(a3)); //false
		System.out.println(arbre.comparer(arbre2)); //false
		System.out.println(arbre.comparer(arbre)); //true
		System.out.println(arbre.comparer(arbre.dupliquer())); //true
		a1.afficher ();
		System.out.println();
		a1.optimiser ().afficher ();
		int a = 5;
		//optimisation
		ArbreDecision a4 = new ArbreDecision ();
		a4.setValeur (decisions.VERIFIER_NOURRITURE);
		System.out.println();
		a1.choisirFilsAleatoire ().afficher ();
		System.out.println();
		//afficher estVide (fils g)
		//a1.setValeur (decisions.RAMASSER_NOURRITURE);
		//System.out.println(a1.estAction ());
		System.out.println("Croisement");
		ArbreDecision a10 = new ArbreDecision ().construire (3);
		ArbreDecision a11 = new ArbreDecision ().construire (3);
		a10.afficher ();
		System.out.println();
		a11.afficher ();
		System.out.println();
		a10.croiserArbre(a11).afficher ();
		System.out.println();
		a10.afficher ();
		System.out.println();
		a11.afficher ();
		System.out.println();
		a1.afficher ();
		System.out.println();
		a1.optimiser2().afficher ();
		System.out.println();
	}
}

