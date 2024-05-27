package src.metier;

import src.metier.Plateau;
import src.metier.Timer;

import java.io.IOException;


public class Jeu
{
	Timer time   = new Timer();


	private Plateau plateau;

	public Jeu()
	{
		this.plateau = new Plateau();

		this.jeu(this.plateau);
	}

	public void jeu(Plateau plateau)
	{
		time.start();

		boolean estFini = false;


		System.out.println(plateau);

		while (!estFini)
		{
			try
			{
				if( time.estSecondePile() )
				{
					// Condition pour clear sous windows
					if (System.getProperty("os.name").contains("Windows"))
					{
						new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
					}

					// Condition pour clear sous Linux
					if (System.getProperty("os.name").contains("Linux"))
					{
						new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
					}

					this.plateau.majPlateau();

					if ( this.plateau.deplacerSerpent() )
					{
						System.out.print  (this.plateau.getSerpent().getTete().getCoordX() + " : ");
						System.out.println(this.plateau.getSerpent().getTete().getCoordY());

						System.out.println("Je me suis déplacé");
					}
					else
						estFini = true;



					System.out.println(plateau);
				}

			} catch (IOException | InterruptedException e) {}
		}

	}

}
