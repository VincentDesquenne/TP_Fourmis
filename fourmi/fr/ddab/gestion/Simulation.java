package fr.ddab.gestion;

import fr.ddab.personnage.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * <b>Classe Simulation representant tous les Monde.</b>
 * <p>Comporte un tableau de Monde.</p>
 *
 */
public class Simulation {

	private static Monde m;
	private static ArrayList<Monde> tabMondes;
	
	/**
	 * Creer un Monde dans Simulation en appellant son constructeur.
	 * @param nbNourriture : nombre de Nourriture initial du Monde.
	 * @param nbFourmiliere : nombre de Fourmiliere initial du Monde.
	 * @param nbFourmi : nombre de Foumi initial du Monde.
	 * @param taille : taille de Grille.
	 * 
	 * @see Monde
	 */

	public static  void creerMonde(int nbNourriture, int nbFourmiliere, int nbFourmi, ArrayList <Short> taille) {
		m = new Monde(nbNourriture, nbFourmiliere, nbFourmi, taille);
		tabMondes.add(m);
		
	}
	
	/**
	 * Supprime un Monde du tableau de Monde.
	 * @param m : Monde a supprimer.
	 */
	
	public void supprimerMonde(Monde m) {
		tabMondes.remove(m);

	}
	
	/**
	 * Supprime tous les Monde du tableau de Monde.
	 */
	
	public void detruireMondes() {
		m = null;
		tabMondes = null;
	}
	
	/**
	 * Definit l'action des Fourmi de chaque Monde de Simulation.
	 * 
	 * @see Monde+faireAction()
	 * @see Fourmi+faireAction(Grille)
	 */

	public static void faireAction() {
	    for (Monde m : tabMondes) {
	    	m.faireAction ();
	    }

	}
	
	/**
	 * Lance la simulation du Monde passe en parametre.
	 * @param m : Monde dont on veut la simulation
	 * @throws InterruptedException : si le thread est interrompu.
	 */
	
	public static void LancerSimulation(Monde m) throws InterruptedException {
		//Affichage :
		System.out.println("Hauteur : "+m.getGrille().getTaille().get(0));
		System.out.println("Largeur : "+m.getGrille().getTaille().get(1));
		
		for(int i = 0;i<m.getGrille().getTaille().get(0);i++) {
			System.out.println(m.getGrille ());
		}
	
		while(m.getNbNourriture() != 0) {
			
			System.out.println("Nombre de nourritures : "+m.getNbNourriture());
			m.afficherFourmis ();
			m.faireAction();
			m.MettreAJourNbCase();
			Thread.sleep(1000);
			
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		ArrayList<Short> taille = new ArrayList<Short>();
		taille.add((short)5);
		taille.add((short)4);
		Monde m = new Monde(5,4,8,taille);
		
		m.AjouteraleatoireNourriture();
		m.AjouteraleatoireFourmiliere();

		
		//Genere aleatoirement les Fourmis dans le plateau
		Random r = new Random ();
		 for(int i = 0;i<m.getNbFourmiInit();i++) {
			ArrayList<Short> p = new ArrayList<Short>();
			int randomX = r.nextInt (m.getGrille().getTaille().get(0));
			int randomY = r.nextInt (m.getGrille().getTaille().get(1));
			p.add((short) randomX);
			p.add((short) randomY);
			m.getTabFourmi().add(new Fourmi(p,0));
		} 
		
		/*ArrayList<Short> p = new ArrayList<Short>();
		p.add((short) 1);
		p.add((short) 1);
		m.getTabFourmi().add(new Fourmi(p));
		p.set(0,(short) 4);
		p.set(1,(short) 3);
		m.getTabFourmi().add(new Fourmi(p));
		p.set(0,(short) 2);
		p.set(1,(short) 1);
		m.getTabFourmi().add(new Fourmi(p));
		System.out.println(m.getTabFourmi().toString());
		*/
		
		LancerSimulation(m);
		
				
	}
}
