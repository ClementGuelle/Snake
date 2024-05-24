package src.metier;

public class Plateau
{

	private char[][] plateau;
	private Serpent  serpent;

	/**
	 *
	 * Constructeur de la classe Plateau
	 */
	public Plateau()
	{
		this.serpent = new Serpent();
		this.plateau = initPlateau();
	}

	/**
	 * Création du plateau
	 * 
	 * @return le plateau construit avec le serpent et une pomme
	 */
	public char[][] initPlateau()
	{
		final int TAILLE_TABLEAU = 32;

		char[][] plateau = new char[TAILLE_TABLEAU][TAILLE_TABLEAU];


		for (int lig = 0; lig < plateau.length; lig++)
			for (int col = 0; col < plateau[lig].length; col++)
				plateau[lig][col] = ' ';


		plateau[this.serpent.getTete().getCoordX()][this.serpent.getTete().getCoordY()] = this.serpent.getTete().getPartieCorp();

		for(int cpt = 0; cpt < this.serpent.getCorp().size(); cpt ++)
			plateau[this.serpent.getCorp().get(cpt).getCoordX()][this.serpent.getCorp().get(cpt).getCoordY()] = this.serpent.getCorp().get(cpt).getPartieCorp();

		plateau[this.serpent.getQueue().getCoordX()][this.serpent.getQueue().getCoordY()] = this.serpent.getQueue().getPartieCorp();



		plateau[16][25] = 'P';

		return plateau;
	}

	public Serpent getSerpent()
	{
		return this.serpent;
	}


	public void majPlateau()
	{
		for (int lig = 0; lig < plateau.length; lig++)
			for (int col = 0; col < plateau[lig].length; col++)
				plateau[lig][col] = ' ';


		plateau[this.serpent.getTete().getCoordX()][this.serpent.getTete().getCoordY()] = this.serpent.getTete().getPartieCorp();

		for(int cpt = 0; cpt < this.serpent.getCorp().size(); cpt ++)
			plateau[this.serpent.getCorp().get(cpt).getCoordX()][this.serpent.getCorp().get(cpt).getCoordY()] = this.serpent.getCorp().get(cpt).getPartieCorp();

		plateau[this.serpent.getQueue().getCoordX()][this.serpent.getQueue().getCoordY()] = this.serpent.getQueue().getPartieCorp();



		plateau[16][25] = 'P';
	}

	/**
	 * Affiche le Plateau avec le serpent et la pomme
	 * 
	 * @return l'affichage du plateau
	 */
	public String toString()
	{
		String sRet         = "";
		String tracageLigne = "";

		for (int cpt = 0; cpt < this.plateau.length; cpt++)
			tracageLigne += "+-";
		tracageLigne += "+";

		for (int lig = 0; lig < this.plateau.length; lig++)
		{
			sRet += tracageLigne + "\n";

			for (int col = 0; col < this.plateau[lig].length; col++)
			{
				sRet += "|" + this.plateau[lig][col];
			}

			sRet += "|\n";
		}

		sRet += tracageLigne;

		return sRet;
	}
}