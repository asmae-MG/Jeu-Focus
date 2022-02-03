/*	
 ******************************************************************************
|     La classe Piece contient une surcharge de constructeur pour créer       |
|         		des pieces pour la classe Plateau                      |    
 ******************************************************************************         
*/
public class Piece{

	/* la piece peut etre "Rouge" ou Verte ainsi que "Empty" 
		car le Stack n'accepte pas d'etre vide*/
	private String couleur;
	private int fleche;  	// fleche de depilage et empilage    	

	public Piece(String couleur){
		this.couleur=couleur;
	}

	public Piece(int fleche){
		this.fleche = fleche; 
	}
	//Cette fonction retourne la couleur de la piece
	public String GetCouleur(){    
		return this.couleur;
	}
	public String toString(){
		 if ( fleche == 1)  return "<--";

		 if ( fleche == 2)  return "-->";

		 if (couleur =="Rouge") return "\u001B[31m"+"[+]"+"\u001B[0m";
		 
		 if (couleur =="Empty") return "   ";
	
		 if (couleur =="Vert") return "\u001B[32m"+"[*]"+"\u001B[0m";

	         else return "Veuillez saisir Rouge ou Vert";
	}
}
