package fr.ddab.personnage;

import fr.ddab.arbre.*;
import fr.ddab.plateau.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Classe representant une Fourmi</b>
 * <p>Fourmi est represente par sa position (X,Y), son etat (si elle transporte de la nourriture ou non), son score,
 * son etatPrecedent dans l'Arbre, son Arbre de comportement, et sa generation</p>
 * 
 * @see ArbreDecision
 *
 */


public class Fourmi {
    private ArrayList <Short> position;
    private boolean nourriture;
    private short score;
    private ArbreDecision.decisions etatPrecedent;
    private ArbreDecision arbreComportement;
    private int generation;

    /**Constructeur Fourmi.
     * <p>A la construction d'un objet Fourmi, "nourriture" est fixe a faux, le score est fixe a 0.
     * On instancie arbreComportement comme un objet d'ArbreDecision.</p>
     * 
     * @param position : Position de Fourmi
     * @param generation : Generation de Fourmi
     */
    
    public Fourmi (ArrayList <Short> position, int generation){
        this.position = position;
        this.nourriture = false;
        this.score = 0;

        this.etatPrecedent = ArbreDecision.decisions.ALLER_EN_HAUT;
        this.arbreComportement = new ArbreDecision ().construire (5);
        this.generation = generation;

    }

    public short getScore () {
    	return score;
    }
    /**
     * Getter de la position de Fourmi. On obtient la position de Fourmi
     * @return position : Position de la fourmi
     */
    public ArrayList <Short> getPosition () {
        return position;
    }
    
    /**
     * Setter de la position de Fourmi. On affecte une position a Fourmi
     * @param position : Position de la fourmi
     */
    
    public void setPosition (ArrayList <Short> position) {
    	this.position = position;
    }

    /**
     * Informe si Fourmi transporte de la nourriture ou non.
     * @return nourriture : Vrai si la Fourmi transporte de la nourriture.
     */
    public boolean transporteNourriture () {
        return nourriture;
    }
    
    /**
     * Met a jour l'etat de nourriture de Fourmi
     * @param b : nouvel etat de nourriture
     */
    
    public void setNourriture (boolean b) {
        this.nourriture = b;
    }
    
    /**
     * Retourne l'etat precedent de la Fourmi
     * @return etatPrecedent : l'etat precedent de type ArbreDecision.decision
     * @see ArbreDecision
     */
    
    public ArbreDecision.decisions getEtatPrecedent () {
        return etatPrecedent;
    }
    
    /**
     * Met a jour le score
     * @param bonus : Ce qu'il faut rajouter au score actuel
     */

    public void augmenterScore (short bonus) {
        score += bonus;
    }
    
    /**
     * Met a jour le score
     * @param malus : Ce qu'il faut enlever au score actuel
     */
    
    public void diminuerScore (short malus) {
        score -= malus;
    }
    /**
     * Deplacement de Fourmi par rapport a l'ArbreDecision de la Fourmi.
     * @param direction : la direction que la Fourmi doit prendre.
     * @param plateau : le plateau du monde dans laquelle la Fourmi se trouve.
     * 
     * @see ArbreDecision
     */
    

    public void seDeplacer (ArbreDecision.decisions direction, Grille plateau) {
        short x = position.get (0);
        short y = position.get (1);
        short taille_x = plateau.getTaille ().get (0);
        short taille_y = plateau.getTaille ().get (1);
        switch (direction) {
            case ALLER_EN_HAUT:
            	y = (y == 0) ? (short) (taille_y - 1) : (short) (y - 1);
            	
            	position.set(1,(short)(y-1));
            	break;
            case ALLER_EN_BAS:
            	y = (y == taille_y - 1) ? 0 : (short) (y + 1);
            	
            	position.set(1, (short) (y + 1));
            	break;
            case ALLER_A_GAUCHE:
            	x = (x == 0) ? (short) (taille_x - 1) : (short) (x - 1);
            	position.set(0, (short) (x - 1));
            	
            	break;
            case ALLER_A_DROITE:
            	x = (x == taille_x - 1) ? 0 : (short) (x + 1);
            	position.set(0, (short) (x + 1));
            	
            	break;
        }
    }
      
    /**
     * Definit l'action que doit faire Fourmi en fonction de son ArbreDecision.
     * @param plateau : le plateau du monde dans laquelle la Fourmi se trouve.
     * 
     * @see ArbreDecision
     */
 
    public void faireAction (Grille plateau) {
    	ArbreDecision parcours = arbreComportement;
    	while (!parcours.estFeuille ()) {
    		if (parcours.estCondition ()) {
    			switch (parcours.getValeur ()) {
    				case VERIFIER_NOURRITURE:
    					parcours = parcours.getAction (plateau.getCase (position).estNourriture ());
    					break;
    				case VERIFIER_FOURMILIERE:
    					parcours = parcours.getAction (plateau.getCase (position).estFourmiliere ());
    					break;
    				case TRANSPORTE_NOURRITURE:
    					parcours = parcours.getAction (transporteNourriture ());
    					break;
    			}
    		} else { //c'est une action
    			if (parcours.estDeplacement ()) {
    				seDeplacer (parcours.getValeur (), plateau);
    			} else {
    				switch (parcours.getValeur ()) {
    					//et fourmiliere et faireaction
    					case RAMASSER_NOURRITURE:
    						ramasserNourriture (plateau);
    						break;
    					case DEPOSER_NOURRITURE:
    						deposerNourriture (plateau);
    						break;
    					/*case ALLER_FOURMILIERE:
    						//A modifier pour coller avec la fonction de cheminLePlusProche
    						//voire rajouter un param�tre pour stocker le chemin
    						this.CheminLePlusProche(plateau);
    						break;
    					*/
    				}
    			}
    			parcours = parcours.getAction (true);
    		} 
    	}
    }
    
    /**
     * Action de Fourmi : Ramasser la nourriture. Retourne un booleen
     * @param grille : le plateau du monde dans laquelle la Fourmi se trouve.
     * @return Succes ou non de l'action.
     * 
     * @see Grille
     */
    
    public boolean ramasserNourriture(Grille grille){
    	short x = this.getPosition().get(0);
        short y = this.getPosition().get(1);
    	if (grille.getTabCase().get(x).get(y).estNourriture() && !transporteNourriture()) {
    		setNourriture(true);
    		grille.TransformerCaseEnVide(getPosition());
    		return true;
    	}
    	return false;
        
    }
    
    /**
     * Action de Fourmi : Deposer la nourriture. Retourne un booleen
     * @param grille : le plateau du monde dans laquelle la Fourmi se trouve.
     * @return Succes ou non de l'action.
     */
    
    public boolean deposerNourriture(Grille grille){
    	short x = this.getPosition().get(0);
        short y = this.getPosition().get(1);
        if (grille.getTabCase().get(x).get(y).estFourmiliere() && transporteNourriture()){
        	 setNourriture(false);
        	 augmenterScore((short)5);             
             return true;
        }
        else if (grille.getTabCase().get(x).get(y).estVide() && transporteNourriture()) {
        	setNourriture(false);
        	grille.TransformerCaseEnNourriture(getPosition());
        	return true;
        }
        return false;
        	 
       
    }
    
    /**
     * Retourne la distance qui separe une position (X,Y) d'une fourmiliere.
     * @param fourmiliere : position d'une fourmiliere sous forme d'ArrayList (position X en 0 et position Y en 1).
     * @param PositionX : position X d'une position.
     * @param PositionY : position Y d'une position.
     * @return La distance totale (X+Y) qui separe la position (PositionX,PositionY) de la position d'une fourmiliere.
     */
    
    public double distance(ArrayList<Short> fourmiliere, Short PositionX, Short PositionY ) {
    	int X = (int)Math.pow(((int)PositionX - (int)fourmiliere.get(0)), 2);
    	int Y = (int)Math.pow(((int)PositionY - (int)fourmiliere.get(1)), 2);
    	return Math.sqrt(X + Y);
   }
    
    /**
     * Retourne la position de la fourmiliere la plus proche de la position de Fourmi
     * @param grille : le plateau du monde dans laquelle la Fourmi se trouve.
     * @return la position de la fourmiliere la plus proche de la position de Fourmi.
     * 
     * @see +distance(ArbreDecision.decisions direction, Grille plateau)
     * 
     */
    
    public ArrayList<Short> PositionProcheFourmiliere(Grille grille) {
    		ArrayList<ArrayList<Short>>PosFourmiliere = new ArrayList<ArrayList<Short>>();
    		ArrayList<Short>PositionXY = new ArrayList<Short>(2);
    		List<Double> Distance = new ArrayList<Double>();
    		for(int i=0 ;i<(int)grille.getTaille().get(0);i++) {
    			for(int j=0;j<(int)grille.getTaille().get(1);j++) {
    				if(grille.getTabCase().get(i).get(j).estFourmiliere()) {
    					PositionXY.set(0,(short) i);
    					PositionXY.set(1,(short) j);
    					PosFourmiliere.add(PositionXY);
    				}
    			}
    		}
    		
    		double min = distance(PosFourmiliere.get(0),this.getPosition().get(0), this.getPosition().get(1));
    		int m =0;
    		for(int k=1;k<PosFourmiliere.size();k++) {
    			Distance.add(distance(PosFourmiliere.get(k),this.getPosition().get(0),this.getPosition().get(1)));
    			if(Distance.get(k) < min) {
    				min = Distance.get(k);
    				m = k;
    			}
    		}
    		return PosFourmiliere.get(m);
    	}
    /**
     * Retourne le chemin le plus court pour Fourmi pour arriver sur une case Fourmiliere.
     * @param grille : le plateau du monde dans laquelle la Fourmi se trouve.
     * @return le chemin le plus court pour Fourmi pour arriver sur une case Fourmiliere.
     * 
     * @see +PositionProcheFourmiliere(Grille grille)
     */
    
    public String  CheminLePlusProche(Grille grille) {
    	ArrayList<Short> prochefourmiliere = PositionProcheFourmiliere(grille);
    	ArrayList<Short> MinCoordonnees = new ArrayList<Short>(2);
    	short X = this.getPosition().get(0);
    	short Y = this.getPosition().get(1);
    	double minDistance = 999999;
    	
    	if(nourriture) {
	    	if(X == prochefourmiliere.get(0) && Y== prochefourmiliere.get(1)) {
	    		  //isNourriture = false;
	    		 faireAction(grille); //isNourriture = false;
	    	}
	    	
	    	if(distance(prochefourmiliere, X ,(short) (Y +1)) < minDistance) {
	    		minDistance = distance(prochefourmiliere, X, (short)(Y+1));
	    		MinCoordonnees.set(0, X);
	    		MinCoordonnees.set(1, (short) (Y+1));
	    	}
	    	
	    	if(distance(prochefourmiliere, X ,(short) (Y - 1)) < minDistance) {
	    		minDistance = distance(prochefourmiliere, X, (short)(Y - 1));
	    		MinCoordonnees.set(0, X);
	    		MinCoordonnees.set(1, (short) (Y - 1));
	    	}
	    	
	    	if(distance(prochefourmiliere, (short) (X +1), Y) < minDistance) {
	    		minDistance = distance(prochefourmiliere,(short) (X + 1), Y);
	    		MinCoordonnees.set(0, (short)(X + 1));
	    		MinCoordonnees.set(1, Y);
	    	}
	    	
	    	if(distance(prochefourmiliere, (short) (X - 1), Y) < minDistance) {
	    		minDistance = distance(prochefourmiliere,(short) (X - 1), Y);
	    		MinCoordonnees.set(0, (short)(X - 1));
	    		MinCoordonnees.set(1, Y);
	    	}
	    	
	    	if(MinCoordonnees.get(0) ==X && MinCoordonnees.get(1) == (short)(Y + 1)) {
	    		seDeplacer(ArbreDecision.decisions.ALLER_EN_HAUT,grille);
	    	}
	    	else if(MinCoordonnees.get(0) ==X && MinCoordonnees.get(1) == (byte)(Y - 1)){
	    		seDeplacer(ArbreDecision.decisions.ALLER_EN_BAS,grille);
	    	}
	    	else if(MinCoordonnees.get(0) ==(byte)(X + 1) && MinCoordonnees.get(1) == Y){
	    		seDeplacer(ArbreDecision.decisions.ALLER_A_DROITE,grille);
	    	}
	    	else if(MinCoordonnees.get(0) ==(byte)(X - 1) && MinCoordonnees.get(1) == Y){
	    		seDeplacer(ArbreDecision.decisions.ALLER_A_GAUCHE,grille);
	    	}
	    	
	    	return this.CheminLePlusProche(grille);
	     }
    	else {
    		return "Nourriture Depose";
    	}
    }

    //ajouter dans Monde pour modifierTabFourmis
    public Fourmi seReproduire (Fourmi secondeFourmi) {
    	return null;
    }
    
    /**
     * Retourne le score d'une Fourmi moins le score d'une Fourmi passee en parametre. Compare le score de deux Fourmi.
     * @param secondeFourmi : Fourmi dont le score soustrait le score de la Fourmi actuelle.
     * @return Le score de la Fourmi actuelle moins le score d'une Fourmi passee en parametre.
     */
    
    public int compareTo (Fourmi secondeFourmi) {
        return score - secondeFourmi.score;
    }
   
    
}