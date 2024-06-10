/**
 * @author GUELLE Clément
 */


package src.metier;


public class Serpent
{

	/*--------------*/
	/*    Données   */
	/*--------------*/

	private PartieCorpSerpent tete;
	private PartieCorpSerpent queue;
	private char              directionTete;



	/*--------------*/
	/* Instructions */
	/*--------------*/


	/**
	 * Constructeur permettant la création du serpent grâce à des Maillons (PartieCorpSerpent) où l'on connais la tête et la queue
	 */
	public Serpent()
	{
		this.tete          = new PartieCorpSerpent(7, 16, 'T');
		this.queue         = new PartieCorpSerpent(5, 16, 'Q');
		this.directionTete = 'S';

		new PartieCorpSerpent(6, 16, 'C').ajouterEntre(this.tete, this.queue);
	}


	/*--------------*/
	/*     Get      */
	/*--------------*/

	public PartieCorpSerpent getTete         () { return tete;               }
	public PartieCorpSerpent getQueue        () { return queue;              }
	public char              getDirectionTete() { return this.directionTete; }


	/*--------------*/
	/*     Set      */
	/*--------------*/

	public void setDirectionTete(char direction) { this.directionTete = direction; }


	/*--------------------------*/
	/*     Autres méthodes      */
	/*--------------------------*/

	/**
	 * Méthode permettant d'ajouter une parti au corps du serpent entre la tête et la parti du corps précédent la tête
	 */
	public void ajouterCorp()
	{
		new PartieCorpSerpent(this.tete.getCoordX(), this.tete.getCoordY(), 'C').ajouterEntre(this.tete, this.tete.getPrecedent());
	}


}
