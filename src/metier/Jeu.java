package src.metier;

import java.awt.event.KeyEvent;
import java.io.IOException;


public class Jeu
{
	Timer time = new Timer();

	private Plateau plateau;


	public Jeu()
	{
		this.plateau = new Plateau();

		this.jeu();
	}

	public char[][] getPlateau()
	{
		return this.plateau.getPlateau();
	}

	public void jeu()
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
					if (System.getProperty("os.name").contains("Linux"))
					{
						new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
					}


					if ( this.plateau.deplacerSerpent() )
					{
						this.plateau.majPlateau();
					}
					else
					{
						estFini = true;
						System.out.println("Perdu");
					}

					System.out.println(this.plateau);
				}

			} catch (IOException | InterruptedException e) {}
		}

	}

}
