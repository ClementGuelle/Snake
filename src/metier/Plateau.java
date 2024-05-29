package src.metier;

public class Plateau
{

	private char[][] plateau;
	private Serpent  serpent;
    private int[]    pomme;

	/**
	 *
	 * Constructeur de la classe Plateau
	 */
	public Plateau()
	{
        this.pomme   = new int[] {25, 16};
        this.serpent = new Serpent();
		this.plateau = initPlateau();
	}

	public char[][] getPlateau()
	{
		return this.plateau;
	}

    public void setDirectionTete(char directionTete)
    {
        this.serpent.setDirectionTete(directionTete);
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

        plateau[this.getYPomme()][this.getXPomme()] = 'P';


        plateau[this.serpent.getTete().getCoordY()][this.serpent.getTete().getCoordX()] = this.serpent.getTete().getPartieCorp();

		for(int cpt = 0; cpt < this.serpent.getCorp().size(); cpt ++)
			plateau[this.serpent.getCorp().get(cpt).getCoordY()][this.serpent.getCorp().get(cpt).getCoordX()] = this.serpent.getCorp().get(cpt).getPartieCorp();

		plateau[this.serpent.getQueue().getCoordY()][this.serpent.getQueue().getCoordX()] = this.serpent.getQueue().getPartieCorp();


		return plateau;
	}

	public Serpent getSerpent()
	{
		return this.serpent;
	}

    public int getXPomme()
    {
        return this.pomme[0];
    }

    public int getYPomme()
    {
        return this.pomme[1];
    }

    public boolean changementPlacePomme()
    {
        int xPomme = (int) (Math.random () * 32 );
        int yPomme = (int) (Math.random () * 32 );

        if ( this.plateau[xPomme][yPomme] == ' ' )
        {
            this.pomme[0] = xPomme;
            this.pomme[1] = yPomme;

            return true;
        }

        return false;
    }


    public boolean deplacerSerpent()
    {

        if ( this.serpent.getTete().getCoordX() == this.pomme[0] && this.serpent.getTete().getCoordY() == this.pomme[1]  )
        {
            this.serpent.ajouterCorp();
            while (!this.changementPlacePomme()) {}

            this.majPlateau();
        }
        else
        {
            this.serpent.getQueue().setCoordX(this.serpent.getCorp().get(this.serpent.getCorp().size()-1).getCoordX());
            this.serpent.getQueue().setCoordY(this.serpent.getCorp().get(this.serpent.getCorp().size()-1).getCoordY());

            // Moins 1 car size() donne un élément en plus car il par de 1
            for(int cpt = this.serpent.getCorp().size() -1; cpt > 0; cpt --)
            {
                // cpt - 1 pour avoir le corp avant celui où on change la place
                this.serpent.getCorp().get(cpt).setCoordX(this.serpent.getCorp().get(cpt-1).getCoordX());
                this.serpent.getCorp().get(cpt).setCoordY(this.serpent.getCorp().get(cpt-1).getCoordY());
            }

            this.serpent.getCorp().get(0).setCoordX(this.serpent.getTete().getCoordX());
            this.serpent.getCorp().get(0).setCoordY(this.serpent.getTete().getCoordY());
        }


        switch ( this.serpent.getDirectionTete() )
        {
            case 'N' ->
            {
                if ( this.serpent.getTete().getCoordY() - 1 > 0 &&
                     this.caseSvtEstPossible(this.serpent.getDirectionTete()))
                {
                    this.serpent.getTete().setCoordY(this.serpent.getTete().getCoordY() - 1);
                    this.majPlateau();

                    return true;
                }
            }
            case 'S' ->
            {
                if ( this.serpent.getTete().getCoordY() + 1 < 32 &&
                     this.caseSvtEstPossible(this.serpent.getDirectionTete()))
                {
                    this.serpent.getTete().setCoordY(this.serpent.getTete().getCoordY() + 1);
                    this.majPlateau();

                    return true;
                }
            }
            case 'O' ->
            {
                if ( this.serpent.getTete().getCoordX() - 1 > 0 &&
                     this.caseSvtEstPossible(this.serpent.getDirectionTete()))
                {
                    this.serpent.getTete().setCoordX(this.serpent.getTete().getCoordX() - 1);
                    this.majPlateau();

                    return true;
                }
            }
            case 'E' ->
            {
                if ( this.serpent.getTete().getCoordX() + 1 < 32 &&
                     this.caseSvtEstPossible(this.serpent.getDirectionTete()))
                {
                    this.serpent.getTete().setCoordX(this.serpent.getTete().getCoordX() + 1);
                    this.majPlateau();

                    return true;
                }

            }
        }

        return false;
    }

    /**
     * Dit si la prochaine caase ou le serpent va est libre ou pas
     * @param dir direction de la tête
     * @return un boolean si le serpent peut aller sur la case ou non
     */
    public boolean caseSvtEstPossible(char dir)
    {
        switch ( dir )
        {
            case 'N' ->
            {
                if ( this.serpent.getTete().getCoordY() - 1 > 0 &&
                     this.plateau[this.serpent.getTete().getCoordY() - 1][this.serpent.getTete().getCoordX()] == ' ' ||
                     this.plateau[this.serpent.getTete().getCoordY() - 1][this.serpent.getTete().getCoordX()] == 'P')
                    return true;
            }
            case 'S' ->
            {
                if ( this.serpent.getTete().getCoordY() + 1 < 32 &&
                     this.plateau[this.serpent.getTete().getCoordY() + 1][this.serpent.getTete().getCoordX()] == ' ' ||
                     this.plateau[this.serpent.getTete().getCoordY() + 1][this.serpent.getTete().getCoordX()] == 'P')
                    return true;
            }
            case 'O' ->
            {
                if ( this.serpent.getTete().getCoordX() - 1 > 0 &&
                     this.plateau[this.serpent.getTete().getCoordY()][this.serpent.getTete().getCoordX() - 1] == ' ' ||
                     this.plateau[this.serpent.getTete().getCoordY()][this.serpent.getTete().getCoordX() - 1] == 'P')
                    return true;
            }
            case 'E' ->
            {
                if ( this.serpent.getTete().getCoordX() + 1 < 32 &&
                     this.plateau[this.serpent.getTete().getCoordY()][this.serpent.getTete().getCoordX() + 1] == ' ' ||
                     this.plateau[this.serpent.getTete().getCoordY()][this.serpent.getTete().getCoordX() + 1] == 'P')
                 return true;
            }
        }

        return false;
    }


	public void majPlateau()
	{
		for (int lig = 0; lig < this.plateau.length; lig++)
			for (int col = 0; col < this.plateau[lig].length; col++)
                this.plateau[lig][col] = ' ';


        this.plateau[this.getYPomme()][this.getXPomme()] = 'P';

        this.plateau[this.serpent.getTete().getCoordY()][this.serpent.getTete().getCoordX()] = this.serpent.getTete().getPartieCorp();

		for(int cpt = 0; cpt < this.serpent.getCorp().size(); cpt ++)
            this.plateau[this.serpent.getCorp().get(cpt).getCoordY()][this.serpent.getCorp().get(cpt).getCoordX()] = this.serpent.getCorp().get(cpt).getPartieCorp();

        this.plateau[this.serpent.getQueue().getCoordY()][this.serpent.getQueue().getCoordX()] = this.serpent.getQueue().getPartieCorp();

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