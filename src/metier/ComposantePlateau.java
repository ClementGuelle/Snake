package src.metier;


import src.Controleur;

public class ComposantePlateau
{

	/*--------------*/
	/*    Données   */
	/*--------------*/

	private Serpent    serpent;
	private int[]      pomme;
	private Controleur ctrl;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur permettant la création du serpent et de la pomme
	 */
	public ComposantePlateau(Controleur ctrl)
	{
		// coordonnées x et y de la pomme
        this.pomme   = new int[] {25, 16};
        this.serpent = new Serpent();
		this.ctrl    = ctrl;
	}



	/*--------------*/
	/*     Get      */
	/*--------------*/

	public Serpent getSerpent() { return this.serpent;  }
	public int[]   getPomme  () { return this.pomme;    }



	/*--------------*/
	/*     Set      */
	/*--------------*/

    public void setDirectionTete(char directionTete) { this.serpent.setDirectionTete(directionTete); }




	/*--------------------------*/
	/*     Autres méthodes      */
	/*--------------------------*/


	/**
	 * Méthode permettant le changement de place de la pomme
	 */
	public void changementPlacePomme()
    {
		// variable utilisé pour parcourir le serpent et voir si la pomme ne va pas être posé sur une parti du serpent
		PartieCorpSerpent partiSerpentVerif = this.serpent.getQueue();

        int xPomme = (int) ( Math.random () * 32 );
        int yPomme = (int) ( Math.random () * 32 );


		while ( partiSerpentVerif.getPartieCorp() != 'T')
		{

			// si les coordonnées sont les même alors on relance la méthode

			if ( partiSerpentVerif.getCoordX() == xPomme && partiSerpentVerif.getCoordY() == yPomme )
				this.changementPlacePomme();


			partiSerpentVerif = partiSerpentVerif.getSuivant();
		}

		if ( partiSerpentVerif.getCoordX() == xPomme && partiSerpentVerif.getCoordY() == yPomme )
		{
			this.changementPlacePomme();
		}

		this.pomme[0] = xPomme;
		this.pomme[1] = yPomme;
	}


	/**
	 * Méthode permettant le déplacement de tous le corps du serpent
	 * @return vrai si le déplacement peut se faire
	 */
    public boolean deplacerSerpent()
    {

        if ( this.serpent.getTete().getCoordX() == this.pomme[0] && this.serpent.getTete().getCoordY() == this.pomme[1] )
		{
			this.changementPlacePomme();
			this.serpent.ajouterCorp();
		}


        switch ( this.serpent.getDirectionTete() )
        {

            case 'N' ->
            {
                if ( this.serpent.getTete().getCoordX() - 1 >= 0 && this.caseSvtEstPossible(this.serpent.getDirectionTete()) )
                {
					this.deplacerQueueCorps();
                    this.serpent.getTete().setCoordX(this.serpent.getTete().getCoordX() - 1);

                    return true;
                }
            }
            case 'S' ->
            {
                if (  this.serpent.getTete().getCoordX() + 1 < 32 && this.caseSvtEstPossible(this.serpent.getDirectionTete()) )
                {
					this.deplacerQueueCorps();
					this.serpent.getTete().setCoordX(this.serpent.getTete().getCoordX() + 1);

					return true;
                }
            }
            case 'O' ->
            {
                if (  this.serpent.getTete().getCoordY() - 1 >= 0 && this.caseSvtEstPossible(this.serpent.getDirectionTete()) )
                {
					this.deplacerQueueCorps();
					this.serpent.getTete().setCoordY(this.serpent.getTete().getCoordY() - 1);

                    return true;
                }

			}
            case 'E' ->
            {
                if (  this.serpent.getTete().getCoordY() + 1 < 32 && this.caseSvtEstPossible(this.serpent.getDirectionTete()) )
                {
					this.deplacerQueueCorps();
					this.serpent.getTete().setCoordY(this.serpent.getTete().getCoordY() + 1);

                    return true;
                }

            }
        }

        return false;
    }



    /**
     * Méthode permettant de voir si la case suivante est libre ou non pour que la tête puisse y aller
     * @param dir direction de la tête
     * @return vrai si la tête peut aller sur la case ou non
     */
    public boolean caseSvtEstPossible(char dir)
    {
		PartieCorpSerpent partiSerpentVerif = this.serpent.getQueue();


		switch ( dir )
        {
            case 'N' ->
            {
				if ( this.serpent.getTete().getCoordX() - 1 >= 0 )
					do
					{
						if ( this.serpent.getTete().getCoordY() == partiSerpentVerif.getCoordY() && this.serpent.getTete().getCoordX() -1 == partiSerpentVerif.getCoordX() )
							return false;

						partiSerpentVerif = partiSerpentVerif.getSuivant();

					} while (partiSerpentVerif.getPartieCorp() != 'T');
            }
            case 'S' ->
            {
				if ( this.serpent.getTete().getCoordX() + 1 < 32 )
					do
					{

						if ( this.serpent.getTete().getCoordY() == partiSerpentVerif.getCoordY() && this.serpent.getTete().getCoordX() + 1 == partiSerpentVerif.getCoordX() )
							return false;

						partiSerpentVerif = partiSerpentVerif.getSuivant();

					} while (partiSerpentVerif.getPartieCorp() != 'T');
            }
            case 'O' ->
            {
				if ( this.serpent.getTete().getCoordY() - 1 >= 0 )
					do
					{

						if ( this.serpent.getTete().getCoordY() - 1 == partiSerpentVerif.getCoordY() && this.serpent.getTete().getCoordX() == partiSerpentVerif.getCoordX() )
							return false;

						partiSerpentVerif = partiSerpentVerif.getSuivant();

					} while (partiSerpentVerif.getPartieCorp() != 'T');
            }
            case 'E' ->
            {
				if ( this.serpent.getTete().getCoordY() + 1 < 32 )
					do
					{

						if ( this.serpent.getTete().getCoordY() + 1 == partiSerpentVerif.getCoordY() && this.serpent.getTete().getCoordX() == partiSerpentVerif.getCoordX() )
							return false;

						partiSerpentVerif = partiSerpentVerif.getSuivant();

					} while (partiSerpentVerif.getPartieCorp() != 'T');
            }
        }

        return true;
    }

	public void deplacerQueueCorps()
	{
		// variable utilisé pour parcourir le serpent et déplacer chaque partie une à une
		PartieCorpSerpent partiSerpentVerif = this.serpent.getQueue();


		// Ici se fait le déplacement de la queue et le déplacement du corps

		while (partiSerpentVerif.getPartieCorp() != 'T')
		{
			System.out.println("Avant modif");
			System.out.println(partiSerpentVerif.getCoordX() + " : " + partiSerpentVerif.getCoordY() + " -> " + partiSerpentVerif.getPartieCorp());

			partiSerpentVerif.setCoordX(partiSerpentVerif.getSuivant().getCoordX());
			partiSerpentVerif.setCoordY(partiSerpentVerif.getSuivant().getCoordY());

			System.out.println("apres modif");
			System.out.println(partiSerpentVerif.getCoordX() + " : " + partiSerpentVerif.getCoordY() + " -> " + partiSerpentVerif.getPartieCorp());


			partiSerpentVerif = partiSerpentVerif.getSuivant();
		}

	}

}