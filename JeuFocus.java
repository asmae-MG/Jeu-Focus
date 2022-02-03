import java.util.Scanner;
/*	
 **********************************************************************************
|     La classe JeuFocus possede le deroulement du jeu avec les différents regles  |    
 **********************************************************************************         
*/
public class JeuFocus {

	int j;// cordonné des colonnes de la case 
	int i;// cordonnées des lignes de la case
	Plateau plateauJeu = new Plateau();
	Joueur joueur1;
	Joueur joueur2; 

	public JeuFocus(Joueur joueur1, Joueur joueur2){
		this.joueur1=joueur1;
		this.joueur2=joueur2;
	}
	// déchiffre les coordonnées ij = i × 10 + j saisit par le joueur
	public void SetCordonnee (int ij){ 	
		this.i= ij/10;
		this.j= ij-(i*10 );
	}
	/* Renvoie les coordonnées de i et j */
	public int getCordonneeI ()   { return this.i;}

	public int getCordonneeJ ()	{ return this.j;}
	//  Renvoie 0 si lindex top est rouge , 1 si le stack est vide et 2 si le top est vert
	public int joueurQuiControleLacase(){ 
		if ( plateauJeu.tabPlateau[i][j].couleurPieceString((plateauJeu.tabPlateau[i][j].couleurPiece(plateauJeu.indexTop(i,j))))  == "Rouge") return 0 ;	 
		if ( plateauJeu.tabPlateau[i][j].couleurPieceString((plateauJeu.tabPlateau[i][j].couleurPiece(plateauJeu.indexTop(i,j))))  == "Empty") return 1 ;
		else return 2 ; 
	}
	// tester la fin du jeu 
	public boolean finDuJeu(Joueur joueur1,Joueur joueur2){ 
		int  k= 0, k1=0; // compter combien de case a la meme couleur du top
		
		for ( int i=0 ; i<6 ; i++){
			for ( int j=0 ; j<6 ; j++){				
				if (  plateauJeu.tabPlateau[i][j].couleurPieceString((plateauJeu.tabPlateau[i][j].couleurPiece(plateauJeu.indexTop(i,j))))  == "Rouge" ) {
					k++;	
				}
				if (  plateauJeu.tabPlateau[i][j].couleurPieceString((plateauJeu.tabPlateau[i][j].couleurPiece(plateauJeu.indexTop(i,j))))  == "Vert" ) {
					k1++;	
				}		
			}
		}	
		if ( k1 == 0 && joueur2.GetReserve() == 0 ) {
			System.out.println("-------------------------->  Le joueur Rouge a gagné !"); 
			return true;
		}
		if ( k == 0 && joueur1.GetReserve() == 0) {
			System.out.println("-------------------------->  Le joueur Vert a gagné !");
			return true;
		}
		return false;
	}	
	// quand la pile dépasse 5 , le joueur prendera la reserve en fonction du couleur du bas de la case
	public void prendPiecesEnReserve(int i, int j ,int deplacement,int nbPieces,  Joueur joueur1 ,Joueur joueur2){
		int k =0;
		// je teste si la piece du bas et la piece du haut sont de la meme couleur et que la case dépasse 5 Piece 
		// si la condition est validé le joueur aura une piece en plus  dans sa reserve
		if ( deplacement == 6){	 			
			while ( plateauJeu.tabPlateau[i][j+nbPieces].testMaxdeLaPile() == true){	
			
				if (plateauJeu.tabPlateau[i][j+nbPieces].couleurPieceString(plateauJeu.tabPlateau[i][j+nbPieces].get(k))== "Rouge" && 
				plateauJeu.tabPlateau[i][j+nbPieces].couleurPieceString((plateauJeu.tabPlateau[i][j+nbPieces].couleurPiece(plateauJeu.tabPlateau[i][j+nbPieces].indexTop())))== "Rouge")
				{
					joueur1.SetReserve(1);// ajouter une piece en plus dans la reserve du joueur
				}
				if (plateauJeu.tabPlateau[i][j+nbPieces].couleurPieceString(plateauJeu.tabPlateau[i][j+nbPieces].get(k))== "Vert" && 
				plateauJeu.tabPlateau[i][j+nbPieces].couleurPieceString((plateauJeu.tabPlateau[i][j+nbPieces].couleurPiece(plateauJeu.tabPlateau[i][j+nbPieces].indexTop())))== "Vert" )	 					{
					joueur2.SetReserve(1);
				}
				plateauJeu.tabPlateau[i][j+nbPieces].remove(plateauJeu.tabPlateau[i][j+nbPieces].get(k));	
			}			
		}
		if ( deplacement == 4){
			while ( plateauJeu.tabPlateau[i][j-nbPieces].testMaxdeLaPile() == true){	
				
				if (plateauJeu.tabPlateau[i][j-nbPieces].couleurPieceString(plateauJeu.tabPlateau[i][j-nbPieces].get(k))== "Rouge" && 
				plateauJeu.tabPlateau[i][j-nbPieces].couleurPieceString((plateauJeu.tabPlateau[i][j-nbPieces].couleurPiece(plateauJeu.tabPlateau[i][j-nbPieces].indexTop())))== "Rouge" ) 
				{
					joueur1.SetReserve(1);
				}
				if (plateauJeu.tabPlateau[i][j-nbPieces].couleurPieceString(plateauJeu.tabPlateau[i][j-nbPieces].get(k))== "Vert" && 
				plateauJeu.tabPlateau[i][j-nbPieces].couleurPieceString((plateauJeu.tabPlateau[i][j-nbPieces].couleurPiece(plateauJeu.tabPlateau[i][j-nbPieces].indexTop())))== "Vert" ) 				{
					joueur2.SetReserve(1);
				}
				plateauJeu.tabPlateau[i][j-nbPieces].remove(plateauJeu.tabPlateau[i][j-nbPieces].get(k));							
			}
		}
		if ( deplacement == 8){	
			while ( plateauJeu.tabPlateau[i+nbPieces][j].testMaxdeLaPile() == true){

				if (plateauJeu.tabPlateau[i+nbPieces][j].couleurPieceString(plateauJeu.tabPlateau[i+nbPieces][j].get(k))== "Rouge" && 
				plateauJeu.tabPlateau[i+nbPieces][j].couleurPieceString((plateauJeu.tabPlateau[i+nbPieces][j].couleurPiece(plateauJeu.tabPlateau[i+nbPieces][j].indexTop())))== "Rouge")  				{
					joueur1.SetReserve(1);
				}
				if (plateauJeu.tabPlateau[i+nbPieces][j].couleurPieceString(plateauJeu.tabPlateau[i+nbPieces][j].get(k))== "Vert" && 
				plateauJeu.tabPlateau[i+nbPieces][j].couleurPieceString((plateauJeu.tabPlateau[i+nbPieces][j].couleurPiece(plateauJeu.tabPlateau[i+nbPieces][j].indexTop())))== "Vert") 					{
					joueur2.SetReserve(1);
				}
				plateauJeu.tabPlateau[i+nbPieces][j].remove(plateauJeu.tabPlateau[i+nbPieces][j].get(k));	
			}
		}
		if ( deplacement == 2) { 	
		
			while ( plateauJeu.tabPlateau[i-nbPieces][j].testMaxdeLaPile() == true){	
		
				if (plateauJeu.tabPlateau[i-nbPieces][j].couleurPieceString(plateauJeu.tabPlateau[i-nbPieces][j].get(k))== "Rouge" && 
				plateauJeu.tabPlateau[i-nbPieces][j].couleurPieceString((plateauJeu.tabPlateau[i-nbPieces][j].couleurPiece(plateauJeu.tabPlateau[i-nbPieces][j].indexTop())))== "Rouge") 				{
					joueur1.SetReserve(1);
				}
				if (plateauJeu.tabPlateau[i-nbPieces][j].couleurPieceString(plateauJeu.tabPlateau[i-nbPieces][j].get(k))== "Vert" &&
				plateauJeu.tabPlateau[i-nbPieces][j].couleurPieceString((plateauJeu.tabPlateau[i-nbPieces][j].couleurPiece(plateauJeu.tabPlateau[i-nbPieces][j].indexTop())))== "Vert") 					{
					joueur2.SetReserve(1);
				}
				plateauJeu.tabPlateau[i-nbPieces][j].remove(plateauJeu.tabPlateau[i-nbPieces][j].get(k));		
			}
		}  
	}
	// Quand la reserve est superieur à 1 et que la pile dépasse 5 , la reserve du joueur dépend de la couleur du bas de la case
	public void testMaxPileReserve(int i, int j ,int nbPieces,  Joueur joueur1 ,Joueur joueur2){
		int k =0;

			while ( plateauJeu.tabPlateau[i][j].testMaxdeLaPile() == true){		
				if (plateauJeu.tabPlateau[i][j].couleurPieceString(plateauJeu.tabPlateau[i][j].get(k))== "Rouge" && 
				plateauJeu.tabPlateau[i][j].couleurPieceString((plateauJeu.tabPlateau[i][j].couleurPiece(plateauJeu.tabPlateau[i][j].indexTop())))== "Rouge") 
				{
					joueur1.SetReserve(1);
				}
				if (plateauJeu.tabPlateau[i][j].couleurPieceString(plateauJeu.tabPlateau[i][j].get(k))== "Vert" &&
				plateauJeu.tabPlateau[i][j].couleurPieceString((plateauJeu.tabPlateau[i][j].couleurPiece(plateauJeu.tabPlateau[i][j].indexTop())))== "Vert" ) 
				{
					joueur2.SetReserve(1);
				}
				plateauJeu.tabPlateau[i][j].remove(plateauJeu.tabPlateau[i][j].get(k));		
			}
					
	}
	// Le joueur saisit combien de piece il veut utiliser ainsi que la case à empiler	
	public void jouerDepuisReserve(Joueur joueur,int i, int j ,int pieceDepuisReserve,String couleur){
		for( int l =1; l<=pieceDepuisReserve ;l++){
			plateauJeu.tabPlateau[i][j].empiler(new Piece(couleur));
			joueur.SetReserve(-1); 	
		}			
	}
	// afficher la reserve de chaque joueur
	public void affichageReserve(){
		System.out.println("\u001B[31m"+"Reserve Rouge : "+ joueur1.GetReserve()+"\u001B[0m");
		System.out.println("\u001B[32m"+"Reserve Vert  : "+ joueur2.GetReserve()+"\u001B[0m");		
	}
	// verifier si le joueur à saisit les bonnes coordonnées
 	public boolean verificationMarge(int ij ){
		if ( ij > 55 || ij < 00){
			System.out.println("Veuillez saisir une valeur entre 00 et 55.");
			return false;		
		}
		return true;
	}
	// verifier si le joueur a saisit un nombre de pieces pas disponible	
	public boolean verificationPieces(int pieces){
			
		if ( pieces> 6 ||pieces< 1){
			System.out.println("Veuillez saisir une valeur entre 1 et 5");
			return false;		
		}
		return true;
	}
	// verifier si le joueur a bien saisit un int			
	public boolean verificationINT(Scanner clavier){	
		if ( clavier.hasNextInt() == false){
			System.out.println("Veuillez saisir une valeur numérique. Merci");
			return false;		
		}
		return true;
	}
	// verifier si le joueur saisit une valeur différente de 0 et 1 			
	public boolean reponseJoueur(int reponse){	
		if ( reponse > 1 || reponse < 0  ){
			System.out.println(" Veuillez saisir un 0 pour 'non' ou un 1 pour 'oui'");
			return false;		
		}
		return true;
	}
	// verifier le nombre de pieces pour joueur depuis la reserve				
	public boolean verificationNombrePiecesReserve(Joueur joueur, int nbPieces){	
		if ( joueur.GetReserve() < nbPieces ){
			System.out.println("La reserve ne contient pas autant de pieces.");
			return false;		
		}
		if ( nbPieces == 0 ){
			System.out.println("Vous n'avez aucune piece dans la reserve.");
			return false;		
		}
		if ( nbPieces < 0 ){
			System.out.println("Veuillez saisir une valeur correcte.");
			return false;		
		}
		return true;
	}
	// Le déroulement du jeu pendant la partie 				
	public void jeuDuJoueur(Joueur Joueur1, String couleur, String couleurAffichage , int couleurIndexTopRouge, int couleurIndexTopVert, int tour){
		int nbPieces;
		int deplacement;// le deplacement choisit par le joueur
		int deplacerdeI= getCordonneeI();
		int deplacerdeJ= getCordonneeJ();
		int ij ,reserveNulle =0 ;
		int reponseJoueur=0;
		int pieceDepuisReserve;
		int tourAffichage = tour+1;
		System.out.println("");			
		System.out.println(couleurAffichage+"_____________________________________________________  Tour "+ tourAffichage +" :____________________________________________________"+"\u001B[0m"+" \n");
		// Si la reserve est différent de 0 je demande au joueur si il veut jouer depuis la reserve
		if  (  Joueur1.GetReserve() > 0 ){
			System.out.println("Voulez vous jouer depuis le reserve? . repondre 1 pour oui sinon 0");
			Scanner clavier = new Scanner(System.in);
			while ( verificationINT(clavier) != true){
				 clavier = new Scanner(System.in);
			}
			reponseJoueur= clavier.nextInt();
			while ( reponseJoueur(reponseJoueur) == false){
				while ( verificationINT(clavier) != true){
					clavier = new Scanner(System.in);
				}
				reponseJoueur=clavier.nextInt();
			
			}
			// si le joueur répond par oui alors je vérifie qui rentre les bonnes valeur a chaque fois
			if ( reponseJoueur == 1 ){
				System.out.println("Combien de piece voulez vous utiliser depuis la reserve ?");
				clavier = new Scanner(System.in);
				while ( verificationINT(clavier) != true){
					 clavier = new Scanner(System.in);
				}	
				pieceDepuisReserve= clavier.nextInt();
			while (verificationNombrePiecesReserve(Joueur1,pieceDepuisReserve) == false){	
				while ( verificationINT(clavier) != true){
					clavier = new Scanner(System.in);		
				}
				pieceDepuisReserve= clavier.nextInt();
			}			
				System.out.println("Choisissez une case sous la forme ( ij ): les lignes correspand à 'i' et 'j' aux colonnes entre 00 et 55");
				clavier = new Scanner(System.in);
				while ( verificationINT(clavier) != true){
					clavier = new Scanner(System.in);
				}		
				ij = clavier.nextInt();
				SetCordonnee(ij);
				while (verificationMarge(ij) == false)
				{	
					while ( verificationINT(clavier) != true){
						clavier = new Scanner(System.in);		
		
					}
					ij= clavier.nextInt();
					SetCordonnee(ij);
				}
				// Pour bien emiler les pieces j'utilise ces conditions, et c'est surtout par rapport à la couleur Empty
				if (joueurQuiControleLacase() == couleurIndexTopRouge){
					deplacerdeI= getCordonneeI();
					deplacerdeJ= getCordonneeJ();
					jouerDepuisReserve(Joueur1,deplacerdeI,deplacerdeJ ,pieceDepuisReserve,couleur);			
				}
				if (joueurQuiControleLacase()  != couleurIndexTopRouge){
					if (joueurQuiControleLacase()  == couleurIndexTopVert){	
						deplacerdeI= getCordonneeI();
						deplacerdeJ= getCordonneeJ();
						jouerDepuisReserve(Joueur1,deplacerdeI,deplacerdeJ ,pieceDepuisReserve,couleur);	
					}
					deplacerdeI= getCordonneeI();
					deplacerdeJ= getCordonneeJ();
					System.out.println(deplacerdeI);
					System.out.println(deplacerdeJ);
					//Si le top de la case est de type Empty alors je dépile et j'empile la case joueur depuis le reserve
					if (joueurQuiControleLacase()  == 1){
						deplacerdeI= getCordonneeI();
						deplacerdeJ= getCordonneeJ();
						System.out.println(deplacerdeI);
						System.out.println(deplacerdeJ);
						plateauJeu.tabPlateau[deplacerdeI][deplacerdeJ].depiler();
						jouerDepuisReserve(Joueur1,deplacerdeI,deplacerdeJ ,pieceDepuisReserve,couleur);			
					}
				}
				testMaxPileReserve(deplacerdeI , deplacerdeJ,pieceDepuisReserve, joueur1 , joueur2);	
			}
			plateauJeu.afficherPlateau();
			affichageReserve();
			if (Joueur1.GetReserve() == 0) reserveNulle = 1;// me permet d'avoir un bon affichage sinon les conditions se mélange
		}
		// si le réserve est nulle le joueur suit les  indications
		if  (  (Joueur1.GetReserve() == 0 && reserveNulle == 0) || reponseJoueur == 0){
			System.out.println("Choisissez une case sous la forme ( ij ): les lignes correspand à 'i' et 'j' aux colonnes entre 00 et 55");
			Scanner clavier = new Scanner(System.in);
			while ( verificationINT(clavier) != true){
				clavier = new Scanner(System.in);
			}
			ij= clavier.nextInt();
			SetCordonnee(ij);
			while (verificationMarge(ij) == false){	
				while ( verificationINT(clavier) != true){
					clavier = new Scanner(System.in);		
	
				}
			ij= clavier.nextInt();
			SetCordonnee(ij);
			}
			/* Ces conditions me permet de faire un tour de role entre les 2 joueurs*/			
			if (joueurQuiControleLacase()  == 1){     
				while (joueurQuiControleLacase()  == 1 ){
					System.out.println("La case est vide. Veuillez choisir une autre case");	
					clavier = new Scanner(System.in);
					while ( verificationINT(clavier) != true){
						clavier = new Scanner(System.in);
					}
					ij = clavier.nextInt();
					SetCordonnee(ij);
					while (verificationMarge(ij) == false){
						while ( verificationINT(clavier) != true){
								clavier = new Scanner(System.in);
						}
					ij = clavier.nextInt();
					SetCordonnee(ij);
					}	
				}

			}

			if (joueurQuiControleLacase()  != couleurIndexTopRouge){ 		
			
				while (joueurQuiControleLacase()  != couleurIndexTopRouge ){	
					System.out.println("Ce n'est pas encore votre tour");
					clavier = new Scanner(System.in);
					while ( verificationINT(clavier) != true){
						clavier = new Scanner(System.in);
					}
					ij = clavier.nextInt();
					SetCordonnee(ij);
					while (verificationMarge(ij) == false){
						while ( verificationINT(clavier) != true){
							clavier = new Scanner(System.in);
						}
					ij = clavier.nextInt();
					SetCordonnee(ij);
					}	
				}
			}		

 			if (joueurQuiControleLacase() == couleurIndexTopVert){ 	
				while (joueurQuiControleLacase()  != couleurIndexTopRouge ){
					System.out.println("Ce n'est pas votre tour encore");
					clavier = new Scanner(System.in);
					while ( verificationINT(clavier) != true){
						clavier = new Scanner(System.in);
					}
					ij = clavier.nextInt();
					SetCordonnee(ij);
					while (verificationMarge(ij) == false){
						while ( verificationINT(clavier) != true){
							clavier = new Scanner(System.in);
						}
					ij = clavier.nextInt();
					SetCordonnee(ij);
					}	
				}
			}		
		SetCordonnee(ij);
		deplacerdeI= getCordonneeI();
		deplacerdeJ= getCordonneeJ();	
		plateauJeu.affichageflechesEmpiler(deplacerdeI,deplacerdeJ);
		plateauJeu.afficherPlateau();
		affichageReserve();
		plateauJeu.disparitionflechesEmpiler(deplacerdeI,deplacerdeJ);
		System.out.println("Combien de pieces voulez vous déplacer?");
		clavier = new Scanner(System.in);
		while ( verificationINT(clavier) != true){
				clavier = new Scanner(System.in);
		}
		nbPieces = clavier.nextInt();
		while (verificationPieces(nbPieces) == false){
			while ( verificationINT(clavier) != true){
				clavier = new Scanner(System.in);
			}
			nbPieces= clavier.nextInt();
		}
		while ( plateauJeu.generationAutomatiqueFleches(deplacerdeI,deplacerdeJ,nbPieces) == false) nbPieces = clavier.nextInt();
		plateauJeu.afficherPlateau();
		affichageReserve();
		System.out.println("Veuillez saisir un autre déplacement : (2 = en haut), (4 = à gauche) , (6 = à droite),(8 = en bas)");
		System.out.println("ou voulez vous déplacer cette case ?");
		clavier = new Scanner(System.in);
		while ( verificationINT(clavier) != true){
			clavier = new Scanner(System.in);
		}
		deplacement = clavier.nextInt();
		while ( plateauJeu.choisirLeDeplacement(deplacement,deplacerdeI , deplacerdeJ, nbPieces) == false) {

			while ( verificationINT(clavier) != true){
				clavier = new Scanner(System.in);
			}
			deplacement = clavier.nextInt();
		}
		// tester si la case depasse 5 et incrémente la reserve si possible
     	        prendPiecesEnReserve(deplacerdeI , deplacerdeJ ,deplacement,nbPieces,joueur1 ,joueur2);
	      	plateauJeu.afficherPlateau();
		affichageReserve();
		reserveNulle = 0;	
	 }	
	 		tourAffichage++;	
}	
public static void main(String args[]){

		Joueur joueur1 = new Joueur("Rouge");
		Joueur joueur2 = new Joueur("Vert") ;
		JeuFocus partie1 = new JeuFocus( joueur1 , joueur2);
		int tour= 0; // paire  pour le vert et impaire pour le rouge 
		String couleurAffichage ; 		
		partie1.plateauJeu.afficherPlateau();
		partie1.affichageReserve();
		//  La partie s’arrete lorsqu’un joueurne contrˆole plus aucune piece et est d ́epourvu de pieces en reeserve
		while (partie1.finDuJeu( joueur1 , joueur2) != true ){
			if ( tour%2 == 0 ){
				couleurAffichage = "\u001B[31m"; 				
				partie1.jeuDuJoueur(joueur1,"Rouge",couleurAffichage , 0 , 2,tour);
			}
			else {
				couleurAffichage = "\u001B[32m";
				partie1.jeuDuJoueur(joueur2,"Vert",couleurAffichage , 2 , 0,tour);			
			}
			tour++;
		}	
}
}

