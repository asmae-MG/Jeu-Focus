/*	
 ******************************************************************************
|    La classe Joueur permet de creer les joueurs pour le jeu Focus           |    
 ******************************************************************************         
*/

public class Joueur{

	private int piecesReserve; // les pièces en réserve du joueur
	private String couleur;// couleur des pieces du joueur (rouge ou vert)

	public Joueur (String couleur){
		this.couleur=couleur;
	}
	// Renvoie la couleur des pieces des joueurs 
	public String GetCouleur(){
		return this.couleur;
	}
	// incrémente ou desincremente les pieces en reserve du joueur 
	public void SetReserve(int ReserveJoueur){
		this.piecesReserve=this.piecesReserve+ReserveJoueur;
	} 
	// Renvoie la reserve du joueurs 
	public int GetReserve(){
		return this.piecesReserve;
	} 
	public String toString(){
		 return "Le nombre de piece en Reserve: "+piecesReserve+
		 	" de couleur: "+couleur;
	}

}
