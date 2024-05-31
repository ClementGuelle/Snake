package src;

import src.ihm.FrameJeu;
import src.metier.Plateau;
import src.metier.Timer;
import java.io.IOException;

import java.io.IOException;

public class Controleur
{
	Timer time = new Timer();

	private Plateau  plateau;
	private FrameJeu frameJeu;

	public Controleur()
	{
		this.plateau  = new Plateau();
		this.frameJeu = new FrameJeu(this);


		time.start();

		boolean estFini = false;


		while (!estFini)
		{
			if( time.estSecondePile() )
			{
				if ( this.plateau.deplacerSerpent() )
				{
					this.plateau.majPlateau();
				}
				else
				{
					estFini = true;
					System.out.print("Perdu");
				}

				this.majPlateau();
			}
		}
	}

	public char[][] getPlateau()
	{
		return this.plateau.getPlateau();
	}

	public void setDirectionTete(char directionTete)
	{
		this.plateau.setDirectionTete(directionTete);
	}

	public String getImages(char elementJeu)
	{
		switch ( elementJeu )
		{
            case 'T' ->
			{
                return "../images/Tete.png";
            }
			case 'C' ->
			{
				return "../images/Corps.png";
			}
			case 'Q' ->
			{
				return "../images/Queue.png";
			}
			case 'P' ->
			{
				return "../images/Pomme.png";
			}
        }

		return "../images/Vide.png";
	}

	public void majPlateau () { this.frameJeu.majPlateau(); }



	public static void main(String[] args)
	{
		new Controleur();
	}
}
