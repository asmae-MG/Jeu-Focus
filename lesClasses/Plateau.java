/*	
 **********************************************************************************
|     La classe Plateau construit le plateau du jeu				      |    
 **********************************************************************************         
*/
class Plateau{

	Case tabPlateau [][] = new Case [6][6];
	Case caseBufferDepiler = new Case(); // Case temp. pour depiler du bas
		public Plateau(){
			creationPieces();
		}
	// renvoie l'index top de la pile
	public int indexTop(int i , int j){
		return tabPlateau[i][j].getIndex( tabPlateau[i][j].piecesTop());
	}
	// affichage du tableau position initial
	public void afficherPlateau(){
		int max;
		int espace;
		int k=0;
		bordurePlateau();
		for (int i=0;i<=5;i++){
		    for (int j=0;j<=5;j++){
			   max=tabPlateau[i][j].indexTop();
			   System.out.print("|");
			   espace = max*3;
			   for ( int l=0; l<=max ; l++){
				System.out.print(tabPlateau[i][j].get(l));
			
			   }
	//gerer les espaces automatiquement 
			   for ( int m=1; m<=(15-espace) ; m++){
				System.out.print(" ");
			   }
			   k++;
		    if ( (k == 6) || ( k == 12) ||( k == 18) 
		       || (k == 24) || (k== 30) ||(k == 36)){			
				System.out.print("|");
				System.out.print("\n");
				bordurePlateau();
				}				

		      }
		}
	}
	// Creation des pieces et remplier le tableau avec les bonnes couleur 
	public void creationPieces(){
		for (int i=0;i<=5;i++){
		   for (int j=0;j<=5;j++){
		
			if ( ((i%2)==0) && ((j==2) || (j==3)) ){
				tabPlateau[i][j]=new Case("Vert");
			}
			else if ( ((i%2)==0) && ((j==0) || 
				(j==1) ||(j==4) || (j==5) )) {
				tabPlateau[i][j]=new Case("Rouge");
			}
			else if ( ((i%2)!=0) && ((j==2) || (j==3))){
				tabPlateau[i][j]=new Case("Rouge");
			}
			else if ( ((i%2)!=0) && ((j==0) || (j==1) 
				||(j==4) || (j==5) )) {
				tabPlateau[i][j]=new Case("Vert");
			}
		     }
		}
	}
	// affichage des bordure du Plateau
	public void bordurePlateau(){
		for( int i=0; i<3;i++){
		     System.out.print("+------------------+------------------");
		}
		     System.out.print("+\n");	
	}
	// empiler les fleches empiler : "-->"
	public void affichageflechesEmpiler(int i2 ,int j2){
		tabPlateau[i2][j2].empiler(new Piece(2));
	}
	// empiler les fleches depiler : "<--"
	public void affichageflechesDepiler( int i2 , int j2){
		tabPlateau[i2][j2].empiler(new Piece(1));
	}
	// faire disparaitre les fleches empiler : "-->"
	public void disparitionflechesEmpiler(int i2 ,int j2 ){
		tabPlateau[i2][j2].depiler();
	}
	// faire disparaitre les depiler : "<--"
	public void disparitionflechesDepiler( int i2 , int j2){			
		tabPlateau[i2][j2].depiler();
	}
	// faire déplacer les pieces d'une case à l'autre	
	public void deplacerPieces(int i1 ,int j1 , int i2 , int j2, int nbPieces){
		if ( tabPlateau[i2][j2].couleurPieceString((tabPlateau[i2][j2].couleurPiece(tabPlateau[i2][j2].indexTop())))== "Empty" ){
			tabPlateau[i2][j2].depiler();
		}
		for (int i=1;i<=nbPieces;i++){
			caseBufferDepiler.empiler(tabPlateau[i1][j1].depiler());
		}
		for (int i=1;i<=nbPieces;i++){
			tabPlateau[i2][j2].empiler(caseBufferDepiler.depiler());
			if (tabPlateau[i1][j1].caseVide() == true){
				tabPlateau[i1][j1].empiler(new Piece("Empty"));
			}
		}
			
	}
	// génerer automatiquement les fleches des deplacement possible en prenant compte le nombre de pieces ainsi que laffichage possible
	public boolean generationAutomatiqueFleches(int i1 ,int j1 , int nbPieces){
		if ( nbPieces <= 0) {
			System.out.println("Veuillez saisir un nombre entre 1 et 5.");
			return false; 
		}
		if ( (tabPlateau[i1][j1].indexTop()+1) >= nbPieces){
			tabPlateau[i1][j1].empiler(new Piece(2));
			if ( i1-nbPieces >= 0 ) {
				if ( tabPlateau[i1-nbPieces][j1].couleurPieceString((tabPlateau[i1-nbPieces][j1].couleurPiece(tabPlateau[i1-nbPieces][j1].indexTop())))== "Empty" ){
					tabPlateau[i1-nbPieces][j1].depiler();
				}
				tabPlateau[i1-nbPieces][j1].empiler(new Piece(1));
			}
			if ( i1+nbPieces < 6){
				if ( tabPlateau[i1+nbPieces][j1].couleurPieceString((tabPlateau[i1+nbPieces][j1].couleurPiece(tabPlateau[i1+nbPieces][j1].indexTop())))== "Empty" ){
					tabPlateau[i1+nbPieces][j1].depiler();
				}
				tabPlateau[i1+nbPieces][j1].empiler(new Piece(1));
			}
			if ( j1-nbPieces >= 0 ){
				if ( tabPlateau[i1][j1-nbPieces].couleurPieceString((tabPlateau[i1][j1-nbPieces].couleurPiece(tabPlateau[i1][j1-nbPieces].indexTop())))== "Empty" ){
					tabPlateau[i1][j1-nbPieces].depiler();
				}
				
				tabPlateau[i1][j1-nbPieces].empiler(new Piece(1));
			}
			if ( j1+nbPieces < 6 ){
				if ( tabPlateau[i1][j1+nbPieces].couleurPieceString((tabPlateau[i1][j1+nbPieces].couleurPiece(tabPlateau[i1][j1+nbPieces].indexTop())))== "Empty" ){
					tabPlateau[i1][j1+nbPieces].depiler();
				}
				tabPlateau[i1][j1+nbPieces].empiler(new Piece(1));
			}
			return true;
		}
			
		else {
			System.out.println("La case ne posséde pas autant de pieces. Veuillez saisir une autre valeur.");
			return false;
		}
	}
	// faire disparaitre les fleches automatiques en les dépilant 
	public void disparitionAutomatiqueFleches(int i1 ,int j1 , int nbPieces){
		tabPlateau[i1][j1].depiler();
		if ( i1-nbPieces >= 0 ) {
			tabPlateau[i1-nbPieces][j1].depiler();
			if (tabPlateau[i1-nbPieces][j1].caseVide() == true) 
			{
				tabPlateau[i1-nbPieces][j1].empiler(new Piece("Empty"));
			}
		}
		if ( i1+nbPieces < 6)  {
			tabPlateau[i1+nbPieces][j1].depiler();
			if (tabPlateau[i1+nbPieces][j1].caseVide() == true) {
					tabPlateau[i1+nbPieces][j1].empiler(new Piece("Empty"));
			}
		}
		if ( j1-nbPieces >=0 )  {
			tabPlateau[i1][j1-nbPieces].depiler();
			if (tabPlateau[i1][j1-nbPieces].caseVide() == true){
				tabPlateau[i1][j1-nbPieces].empiler(new Piece("Empty"));
			}
		}
		if ( j1+nbPieces < 6 )  {
			tabPlateau[i1][j1+nbPieces].depiler();
			if (tabPlateau[i1][j1+nbPieces].caseVide() == true) {
				tabPlateau[i1][j1+nbPieces].empiler(new Piece("Empty"));
			}
		}
			
	}
	// Le joueur choisis la case pour depiler depuis les fleches automatique
	public boolean choisirLeDeplacement(int deplacement,int i1 , int j1, int nbPieces){
		// si le bon choix saisit la fonction génere des fleches automatiques et renvoie un true 
		if ( deplacement == 6) {	 
			if ( j1+nbPieces < 6 )  {
				disparitionAutomatiqueFleches(i1 , j1, nbPieces);
				deplacerPieces(i1 ,j1 , i1, j1+nbPieces,nbPieces);
				return true;					
			}
			else 	{
				System.out.println("Action impossible. Veuillez choisir une direction possible.");
				return false;	
			}					
		}
		if ( deplacement == 4) {
			if ( j1-nbPieces >=0  )  {
				disparitionAutomatiqueFleches(i1 , j1, nbPieces);
				deplacerPieces(i1 ,j1 , i1,  j1-nbPieces,nbPieces);
				return true;
			}
					
			else 	{
				System.out.println("Action impossible. Veuillez choisir une direction possible.");
				return false;	
			}									
		}
		if ( deplacement == 8) {
			if ( i1+nbPieces < 6 )  {
				disparitionAutomatiqueFleches(i1 , j1, nbPieces);
				deplacerPieces(i1 ,j1 , i1+nbPieces, j1,nbPieces);
				return true;
			}
			else 	{
				System.out.println("Action impossible. Veuillez choisir une direction possible.");
				return false;	
			}
		}
		if ( deplacement == 2) {
			if ( i1-nbPieces >= 0 )  {
				disparitionAutomatiqueFleches(i1 , j1, nbPieces);
		        	deplacerPieces(i1 ,j1 ,i1-nbPieces, j1,nbPieces);
				return true;
			}
			else 	{
				System.out.println("Action impossible. Veuillez choisir une direction possible.");
				return false;	
			}
		}
		if ( deplacement != 6 && deplacement != 4 && deplacement != 8 && deplacement != 2) {					
				System.out.println("Veuillez saisir un autre déplacement : (2 = en haut), (4 = à gauche) , (6 = à droite),(8 = en bas)");
				return false;			
			}	
		else return true ;	
	}
}
