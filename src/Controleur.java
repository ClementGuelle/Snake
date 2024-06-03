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

	private boolean estFini = false;


	public Controleur()
	{
		this.plateau  = new Plateau();
		this.frameJeu = new FrameJeu(this);


		time.start();


		while (!this.estFini)
		{
			if( time.estSecondePile() )
			{
				if ( this.plateau.deplacerSerpent() )
				{
					this.plateau.majPlateau();
				}
				else
				{
					this.estFini = true;
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
				if ( this.estFini )
					return "../images/Mort" + this.plateau.getSerpent().getDirectionTete() + ".png";

                return "../images/Tete" + this.plateau.getSerpent().getDirectionTete() + ".png";
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
