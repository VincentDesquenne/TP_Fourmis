
package fr.ddab.fichier;
import fr.ddab.gestion.*;
import fr.ddab.personnage.*;
import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 *<b>Classe SerialisationXML representant la sauvegarde d'un ou plusieurs elements d'une Simulation.</b>
 *
 */
public final class SerialisationXML {
	
	/**
	 * Sauvegarde l'objet passe en parametre.
	 * @param object : objet a sauvegarder.
	 * @param fileName : nom du fichier dans lequel l'objet est sauvegarde.
	 * @throws FileNotFoundException : Si le fichier n'est pas trouve.
	 * @throws IOException : Erreur ou interruption lors d'une operation I/O.
	 */
     public static void serialiser(Object object, String fileName) throws FileNotFoundException, IOException {
        // ouverture de l'encodeur vers le fichier
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName));
        try {
            // serialisation de l'objet
            encoder.writeObject(object);
            encoder.flush();
        } finally {
            // fermeture de l'encodeur
            encoder.close();
        }
    }
     
     /**
      * Sauvegarde le Monde passe en parametre.
      * @param monde : Monde a sauvegarder. 
      * @param fileName : nom du fichier dans lequel le Monde est sauvegarde.
      * @throws FileNotFoundException : Si le fichier n'est pas trouve.
      * @throws IOException : Erreur ou interruption lors d'une operation I/O.
      */
     
     public void sauvegarderMonde(Monde monde, String fileName) throws FileNotFoundException, IOException {
        try {
            this.serialiser(monde, fileName);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
     
     /**
      * Sauvegarde la Fourmi passee en parametre.
      * @param f : Fourmi a sauvegarder. 
      * @param fileName : nom du fichier dans lequel la Fourmi est sauvegarde.
      * @throws FileNotFoundException : Si le fichier n'est pas trouve.
      * @throws IOException : Erreur ou interruption lors d'une operation I/O.
      */
     
     public void sauvegarderFourmi(Fourmi f, String fileName) throws FileNotFoundException, IOException {
    	 try {
    		 this.serialiser(f, fileName);
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 }
     }
     
     /**
      * Sauvegarde toutes les Fourmi passees en parametre.
      * @param Tab : Tableau de Fourmi a sauvegarder. 
      * @param fileName : nom du fichier dans lequel les Fourmi sont sauvegardees.
      * @throws FileNotFoundException : Si le fichier n'est pas trouve.
      * @throws IOException : Erreur ou interuption lors d'une opération I/O.
      */
     
     public void sauvegarderToutesLesFourmis(ArrayList<Fourmi> Tab, String fileName) throws FileNotFoundException, IOException {
    	 try {
    		 this.serialiser(Tab, fileName);
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 }
     }
     
     /**
      * Sauvegarde les meilleures Fourmi.
      * @param Tab : Tableau de Fourmi trie par ordre croissant en fonction du score. Sauvegarde les trois dernieres Fourmi.
      * @param fileName : nom du fichier dans lequel l'objet est sauvegarde.
      * @throws FileNotFoundException : Si le fichier n'est pas trouve.
      * @throws IOException : Erreur ou interruption lors d'une operation I/O.
      */
     
     public void sauvegarderMeilleursFourmis(ArrayList<Fourmi> Tab, String fileName) throws FileNotFoundException, IOException{
    	 try {
    		 for (int i=1;i<4;i++) {
    			 this.serialiser(Tab.get(Tab.size()-i), fileName);
    		 }
    		 
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 }
     }
     
     /**
      * Sauvegarde les parametres du Monde passes en parametre.
      * @param m : Monde dont on veut sauvegarder les parametres. 
      * @param fileName : nom du fichier dans lequel les parametres sont sauvegardes.
      * @throws FileNotFoundException : Si le fichier n'est pas trouve.
      * @throws IOException : Erreur ou interruption lors d'une operation I/O.
      */
     
     public void sauvegarderParametres(Monde m, String filename) throws FileNotFoundException, IOException{
    	 try {
    		 this.serialiser(m.getNbFourmiliere(),filename);
    		 this.serialiser(m.getNbNourriture(),filename);
    		 this.serialiser(m.getGrille(),filename);
    		 this.serialiser(m.getTabFourmi().size(),filename);
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 }
    	  	 
     }
     
    
}
