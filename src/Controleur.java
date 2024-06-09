package src;

import src.ihm.FrameJeu;
import src.metier.PartieCorpSerpent;
import src.metier.ComposantePlateau;
import src.metier.Serpent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur
{

	/*--------------*/
	/*    Données   */
	/*--------------*/

	private ComposantePlateau plateau;
	private FrameJeu          frameJeu;
	private boolean           estFini;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur permettant de lancer le jeu
	 */
	public Controleur()
	{

		this.plateau  = new ComposantePlateau(this);
		this.frameJeu = new FrameJeu(this);
		this.estFini  = false;


		// Timer de 1 seconde
		Timer timer = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!estFini)
				{
					if (plateau.deplacerSerpent())
					{
						majPlateau();
					}
					else
					{
						estFini = true;
						System.out.print("Perdu");
						((Timer) e.getSource()).stop();
					}
				}
			}
		});

		if ( !this.estFini )
			timer.start();
	}



	/*--------------*/
	/*     Get      */
	/*--------------*/

	/**
	 * Méthode permettant d'avoir une chaîne de caractère qui correspond au chemin de l'image recherché
	 * @param x Coordonnée x de la partie du serpent recherché
	 * @param y Coordonnée y de la partie du serpent recherché
	 * @param partiCorps L'image avec cette partie du corps recherché
	 * @return Le chemin vers l'image correspondante
	 */
	public String getImages(int x, int y, char partiCorps)
	{
		// variable utilisé pour parcourir le serpent
		PartieCorpSerpent partiSerpentVerif     = this.getSerpent().getQueue();
		PartieCorpSerpent partieCorpsRechercher = null;

		do
		{
			if ( partiSerpentVerif.getCoordX() == x && partiSerpentVerif.getCoordY() == y )
			{
				partieCorpsRechercher = partiSerpentVerif;
			}

			partiSerpentVerif = partiSerpentVerif.getSuivant();

		} while ( partiSerpentVerif.getPartieCorp() != 'T' );

		if (partiCorps!='V')
			System.out.println(partiCorps);


		switch (partiCorps)
		{
			case 'T' ->
			{
				if (this.estFini)
				{
					return "../images/Mort" + this.plateau.getSerpent().getDirectionTete() + ".png";
				}

				return "../images/Tete" + this.plateau.getSerpent().getDirectionTete() + ".png";
			}

			case 'C' ->
			{
				return "../images/Corps" + this.getDirectionPartieCorp(partieCorpsRechercher, partiCorps) + ".png";
			}

			case 'Q' ->
			{
				return "../images/Queue" + this.getDirectionPartieCorp(partieCorpsRechercher, partiCorps) + ".png";
			}

			case 'P' ->
			{
				return "../images/Pomme.png";
			}
		}

		return "../images/Vide.png";
	}




	public String getDirectionPartieCorp(PartieCorpSerpent partieCorp, char charPartiCorps)
	{

		switch (charPartiCorps)
		{
			case 'C'->
			{
				if ( partieCorp.getPrecedent().getCoordX() == partieCorp.getCoordX() )
				{
					// Corps droit
					if ( partieCorp.getSuivant().getCoordX() == partieCorp.getCoordX() )
						return "OE";

					// Corps courbé vers le haut
					if ( partieCorp.getSuivant().getCoordX() == partieCorp.getCoordX() - 1 )
					{
						if ( partieCorp.getPrecedent().getCoordY() == partieCorp.getCoordY() - 1 )
							return "NO";
						else
							return "NE";
					}

					// Corps courbé vers le bas
					if ( partieCorp.getSuivant().getCoordX() == partieCorp.getCoordX() + 1 )
					{
						if ( partieCorp.getPrecedent().getCoordY() == partieCorp.getCoordY() - 1 )
							return "SO";
						else
							return "SE";
					}
				}

				if ( partieCorp.getPrecedent().getCoordY() == partieCorp.getCoordY() )
				{
					// Corps droit
					if ( partieCorp.getSuivant().getCoordY() == partieCorp.getCoordY() )
						return "NS";

					// Corps courbé vers la gauche
					if ( partieCorp.getSuivant().getCoordY() == partieCorp.getCoordY() - 1 )
					{
						if ( partieCorp.getPrecedent().getCoordX() == partieCorp.getCoordX() - 1 )
							return "NO";
						else
							return "SO";
					}

					// Corps courbé vers la droite
					if ( partieCorp.getSuivant().getCoordY() == partieCorp.getCoordY() + 1 )
					{
						if ( partieCorp.getPrecedent().getCoordX() == partieCorp.getCoordX() - 1 )
							return "NE";
						else
							return "SE";
					}
				}
			}

			case 'Q' ->
			{

				if ( partieCorp.getSuivant().getCoordX() == partieCorp.getCoordX() + 1 )
					return "S";
				if ( partieCorp.getSuivant().getCoordX() == partieCorp.getCoordX() - 1 )
					return "N";
				if ( partieCorp.getSuivant().getCoordY() == partieCorp.getCoordY() + 1 )
					return "E";
				if ( partieCorp.getSuivant().getCoordY() == partieCorp.getCoordY() - 1 )
					return "O";

			}
		}

		return null;
	}

	public Serpent getSerpent() { return this.plateau.getSerpent(); }
	public int[]   getpomme  () { return this.plateau.getPomme  (); }


	/*--------------*/
	/*     Set      */
	/*--------------*/

	public void setDirectionTete(char directionTete) { this.plateau.setDirectionTete(directionTete); }




	/*--------------------------*/
	/*     Autres méthodes      */
	/*--------------------------*/


	public void majPlateau()
	{
		this.frameJeu.majPlateau();
	}



	public static void main(String[] args)
	{
		new Controleur();
	}
}
