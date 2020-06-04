package fr.ddab.arbre;

public class ArbreBinaire <T> extends Arbre <T> {
	
	public ArbreBinaire () {
		super (2);
	}
	
	public ArbreBinaire (T valeur) {
		super (2, valeur);
	}

	public ArbreBinaire <T> getFils (int position) {
		return (ArbreBinaire <T>) super.getFils (position);
	}
	
	public ArbreBinaire <T> dupliquer () {
		ArbreBinaire <T> copieArbre = new ArbreBinaire <T> (valeur);
		
		if (estFeuille ()) {
			return copieArbre;
		}
		for (Arbre <T> filsADupliquer : fils) {
			copieArbre.insererFils (filsADupliquer.dupliquer ());
		}
		return copieArbre;
	}
}
