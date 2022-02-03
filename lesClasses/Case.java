import java.util.Stack;

/*	
*******************************************************************************
|  La classe Case permet de gérer les différentes case du plateau du jeu      |                     
*******************************************************************************        
*/
public class Case{

	Stack<Piece> laCase = new Stack<Piece>();

	public Case (String couleur){
		empiler(new Piece(couleur));
	}
	/*  Surcharge de constructeur, le constructeur sans paramétres d'entree 
	    me permet créer une case temporaire de mémoire */
	public Case (){} 
	//  Retourne la  piece depiler
	public Piece depiler(){ 
		return laCase.pop();
	}
	//  Retourne la  piece depiler
	public void empiler(Piece pieceAEmpiler){ 
		 laCase.push(pieceAEmpiler);
	}
	//  Retourne l'index du sommet de la case
	public int indexTop(){
		return laCase.indexOf(laCase.peek());
	}
	//  Retourne la piece du sommet de la case	
	public Piece piecesTop(){//
		return laCase.peek();
	}
	//  Permet de supprimer une piece du tableau	
	public void remove(Piece piece){
		laCase.remove(piece);
	}
	//  Retourne un true si la case est vide sinon un false	
	public boolean caseVide(){
		if (laCase.empty() == true)  return true;
		return false;
	}
	// Teste si la case contient plus de 5 pieces
	public boolean testMaxdeLaPile(){
		int indexDuTop = indexTop();
		if (indexDuTop > 4 ) return true; 
		else return false;
	}
	// trouver la piece par son index
	public Piece couleurPiece(int index){
		return laCase.get(index);
	} 
	// Renvoie la couleur de la piece dans la case
	public String couleurPieceString(Piece piece){
		return piece.GetCouleur();
	} 
	/* Renvoie l'index d'une piece dans la case*/
	public int getIndex(Piece pieceIndex){
		return laCase.indexOf(pieceIndex);
	} 
	public Piece get(int l){
		return laCase.get(l);
	} 
	public String toString(){
	return laCase.get(indexTop()).toString();
	}
}


