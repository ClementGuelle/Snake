package src.metier;

public class PartieCorpSerpent
{

	private int               coordX;
	private int               coordY;
	private PartieCorpSerpent precedent;
	private PartieCorpSerpent suivant;
	private char              partieCorp;

	public PartieCorpSerpent(int coordX, int coordY, char partieCorp)
	{
		this.coordX     = coordX;
		this.coordY     = coordY;
		this.partieCorp = partieCorp;
		this.precedent  = null;
		this.suivant    = null;
	}

	public int               getCoordX    () { return coordX; }
	public int               getCoordY    () { return coordY; }
	public PartieCorpSerpent getPrecedent () { return precedent; }
	public PartieCorpSerpent getSuivant   () { return suivant; }
	public char              getPartieCorp() { return partieCorp; }


	public void setCoordX (int coordX) { this.coordX = coordX; }
	public void setCoordY (int coordY) { this.coordY = coordY; }


	public void ajouterEntre(PartieCorpSerpent corp, PartieCorpSerpent queue)
	{
		this.suivant    = corp;
		this.precedent  = queue;
		corp.precedent  = this;
		queue.precedent = this;
	}

}
