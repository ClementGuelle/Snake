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
					//if (System.getProperty("os.name").contains("Linux"))
					//{
				//		new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
			//		}


					if ( this.plateau.deplacerSerpent() )
					{
						this.plateau.majPlateau();
					}
					else
					{
						estFini = true;
						System.out.println("Perdu");
					}

					System.out.println(plateau);
					System.out.println("Affichage nombre corps pl " + this.plateau.getSerpent().getCorp().size());

				}

			} catch (IOException | InterruptedException e) {}
		}

	}

}
