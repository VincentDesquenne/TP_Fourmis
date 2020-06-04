package fr.ddab.plateau;
/**
 * <b>Case est la classe qui compose la Grille du Monde.</b>
 * <p>Cette case peut etre une Fourmiliere, une case Nourriture, ou encore une case vide.</p>
 *
 */
public class Case {
	
	/**
	 * Indique si Case est une case Fourmiliere ou non.
	 * @return Vrai si Case est une fourmiliere, faux sinon.
	 */
	
    public boolean estFourmiliere () {
        return this instanceof Fourmiliere;
    }
    
    /**
     * Indique si Case est une case Nourriture ou non.
     * @return Vrai si Case est une case Nourriture, faux sinon.
     */
    
    public boolean estNourriture () {
        return this instanceof Nourriture;
    }
    
    /**
     * Indique si Case est une case vide ou non.
     * @return Vrai si Case est vide, faux sinon.
     */
    
    public boolean estVide () {
        return (!(this.estFourmiliere () || this.estNourriture ()));
    }
    
    /**
     * Affiche Case par une lettre selon le type de celle-ci.
     * @return Une chaine de caractere representant le type de Case.
     * 
     * @see Grille+afficheGrille()
     * @see Monde+afficheMonde()
     */
    
    public String afficheCase() {
    	if (estFourmiliere())
    		return "F ";
    	else if(estNourriture())
    		return "N ";
    	return " ";
    	
    }
}