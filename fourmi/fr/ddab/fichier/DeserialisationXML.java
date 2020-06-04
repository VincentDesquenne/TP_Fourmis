

package fr.ddab.fichier;
import fr.ddab.gestion.*;
import fr.ddab.personnage.*;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 *<b>Classe DeserialisationXML permettant le chargement d'un ou plusieurs elements d'une Simulation.</b>
 * 
 */
public class DeserialisationXML {
	
	/**
	 * Charge le fichier dont le nom est en parametre.
	 * @param fileName : nom du fichier a charger.
	 * @return L'objet du fichier a charger.
	 * @throws FileNotFoundException : Si le fichier n'est pas trouve.
	 * @throws IOException : Erreur ou interuption lors d'une opération I/O.
	 */
    public static Object deserialiser(String fileName) throws FileNotFoundException, IOException {
        Object object = null;
        // ouverture de decodeur
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName));
        try {
            // deserialisation de l'objet
            object = decoder.readObject();
        } finally {
            // fermeture du decodeur
            decoder.close();
        }
        return object;
    }
    
    /**
     * Charge le Monde sauvegarde dans le fichier dont le nom est en parametre.
     * @param fileName : nom du fichier à charger.
     * @return Le Monde du fichier a charger.
     * @throws FileNotFoundException : Si le fichier n'est pas trouve.
	 * @throws IOException : Erreur ou interruption lors d'une operation I/O.
     */
    
    public Monde lireMonde(String fileName) throws FileNotFoundException, IOException{
        Monde monde;
        try {
            monde = (Monde)this.deserialiser(fileName);
            return monde;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Charge la Fourmi sauvegarde dans le fichier dont le nom est en parametre.
     * @param fileName : nom du fichier a charger.
     * @return La Fourmi du fichier a charger.
     * @throws FileNotFoundException : Si le fichier n'est pas trouve.
	 * @throws IOException : Erreur ou interruption lors d'une operation I/O.
     */
    
    public Fourmi lireFourmi(String filename) throws FileNotFoundException, IOException {
    	Fourmi fourmi;
    	try {
    		fourmi = (Fourmi)this.deserialiser(filename);
    		return fourmi;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * Charge les Fourmi sauvegarde dans le fichier dont le nom est en parametre.
     * @param fileName : nom du fichier a charger.
     * @return Les Fourmi du fichier a charger.
     * @throws FileNotFoundException : Si le fichier n'est pas trouve.
	 * @throws IOException : Erreur ou interruption lors d'une operation I/O.
     */
    
    public ArrayList<Fourmi> lirePlusieursFourmis(String fileName) throws FileNotFoundException, IOException {
    	ArrayList<Fourmi> tabFourmi;
    	try {
    		
    		tabFourmi = (ArrayList<Fourmi>)this.deserialiser(fileName);
    		return tabFourmi;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * Charge les parametres du Monde sauvegarde dans le fichier dont le nom est en parametre.
     * @param fileName : nom du fichier a charger.
     * @return Les parametres du Monde du fichier a charger.
     * @throws FileNotFoundException : Si le fichier n'est pas trouve.
	 * @throws IOException : Erreur ou interruption lors d'une operation I/O.
     */
    
    public int[] lireParametres(String fileName) throws FileNotFoundException, IOException {
    	int[] parametres = new int[4];
    	try {
    		parametres = (int[])this.deserialiser(fileName);
    		return parametres;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    
}
