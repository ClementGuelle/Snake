package src.metier;


public class PartieCorpSerpent
{

	/*--------------*/
	/*    Données   */
	/*--------------*/

	private int               coordX;
	private int               coordY;
	private PartieCorpSerpent precedent;
	private PartieCorpSerpent suivant;
	private char              partieCorp;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur permettant la création de la parti du corps du serpent
	 * @param coordX La coordonnée X de la partie du corps du serpent
	 * @param coordY La coordonnée Y de la partie du corps du serpent
	 * @param partieCorp caractère permettant de savoir la parti du corps
	 */
	public PartieCorpSerpent(int coordX, int coordY, char partieCorp)
	{
		this.coordX     = coordX;
		this.coordY     = coordY;
		this.precedent  = null;
		this.suivant    = null;
		this.partieCorp = partieCorp;
	}



	/*--------------*/
	/*     Get      */
	/*--------------*/

	public int               getCoordX    () { return this.coordX;     }
	public int               getCoordY    () { return this.coordY;     }
	public PartieCorpSerpent getPrecedent () { return this.precedent;  }
	public PartieCorpSerpent getSuivant   () { return this.suivant;    }
	public char              getPartieCorp() { return this.partieCorp; }


	/*--------------*/
	/*     Set      */
	/*--------------*/

	public void setCoordX (int coordX) { this.coordX = coordX; }
	public void setCoordY (int coordY) { this.coordY = coordY; }



	/*--------------------------*/
	/*     Autres méthodes      */
	/*--------------------------*/

	/**
	 * Méthode permettant d'ajouter d'ajouter une parti au corps du serpent entre la tête et la parti du corps précédent la tête
	 * @param partie1 tête du serpent
	 * @param partie2 partie du corps précédent la tête
	 */
	public void ajouterEntre(PartieCorpSerpent partie1, PartieCorpSerpent partie2)
	{
		this.suivant      = partie1;
		this.precedent    = partie2;
		partie1.precedent = this;
		partie2.suivant   = this;
	}

}
