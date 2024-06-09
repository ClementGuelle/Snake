package src.ihm;

import src.Controleur;
import src.metier.PartieCorpSerpent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.net.URL;

public class PanelJeu extends JPanel
{
	private static final int TAILLE_PLATEAU = 32;

	private Controleur ctrl;
	private JPanel     panelPlateau;
	private JLabel[][] plateau;

	public PanelJeu(Controleur ctrl)
	{

		this.setLayout(new BorderLayout());


		this.ctrl         = ctrl;
		this.panelPlateau = new JPanel(new GridLayout(PanelJeu.TAILLE_PLATEAU, PanelJeu.TAILLE_PLATEAU));

		// Moins 1 car on par de 0
		this.plateau = new JLabel[PanelJeu.TAILLE_PLATEAU - 1][PanelJeu.TAILLE_PLATEAU - 1];



		for ( int lig = 0; lig < this.plateau.length; lig ++ )
			for ( int col = 0; col < this.plateau[lig].length; col ++ )
			{

				// variable utilisé pour parcourir le serpent
				PartieCorpSerpent partiSerpentVerif = this.ctrl.getSerpent().getQueue();
				this.plateau[lig][col]              = new JLabel();


				// Si la case est la queue ou une partie du corps du serpent
				do
				{

					if ( partiSerpentVerif.getCoordX() == lig && partiSerpentVerif.getCoordY() == col )
					{
						Icon image = new ImageIcon(this.ctrl.getImages( lig, col, partiSerpentVerif.getPartieCorp() ));
						this.plateau[lig][col].setIcon(image);
						System.out.println(this.plateau[lig][col].getIcon());

					}

					partiSerpentVerif = partiSerpentVerif.getSuivant();

				} while ( partiSerpentVerif.getPartieCorp() != 'T' );


				if ( this.plateau[lig][col].getIcon() == null)
				{
					// Si la case est la tête
					if ( this.ctrl.getSerpent().getTete().getCoordX() == lig && this.ctrl.getSerpent().getTete().getCoordY() == col)
					{
						Icon image = new ImageIcon(this.ctrl.getImages(lig, col, 'T' ));
						this.plateau[lig][col].setIcon(image);
					}

					// Si la case est la pomme
					else if ( this.ctrl.getpomme()[0] == lig && this.ctrl.getpomme()[1] == col)
					{
						Icon image = new ImageIcon(this.ctrl.getImages(lig, col, 'P' ));
						this.plateau[lig][col].setIcon(image);
					}


					// Si la case est vide
					else
					{
						Icon image = new ImageIcon(this.ctrl.getImages(lig, col, 'V' ));
						this.plateau[lig][col].setIcon(image);
					}
				}

				this.plateau[lig][col].setOpaque(true);

				this.panelPlateau.add(this.plateau[lig][col]);
			}



		this.add(this.panelPlateau, BorderLayout.CENTER);

	}


	public void majPlateau()
	{
		for ( int lig = 0; lig < this.plateau.length; lig ++ )
			for ( int col = 0; col < this.plateau[lig].length; col ++ )
			{

				// Remise de la case à vide
				Icon image = new ImageIcon(this.ctrl.getImages(lig, col, 'V' ));
				this.plateau[lig][col].setIcon(image);


				// variable utilisé pour parcourir le serpent
				PartieCorpSerpent partiSerpentVerif = this.ctrl.getSerpent().getQueue();


				// Si la case est la queue ou une partie du corps du serpent
				do
				{
					if ( partiSerpentVerif.getCoordX() == lig && partiSerpentVerif.getCoordY() == col )
					{
						image = new ImageIcon(this.ctrl.getImages( lig, col, partiSerpentVerif.getPartieCorp() ));
						this.plateau[lig][col].setIcon(image);
					}

					partiSerpentVerif = partiSerpentVerif.getSuivant();

				} while ( partiSerpentVerif.getPartieCorp() != 'T' );



				// Si la case est la tête
				if ( this.ctrl.getSerpent().getTete().getCoordX() == lig && this.ctrl.getSerpent().getTete().getCoordY() == col)
				{
					image = new ImageIcon(this.ctrl.getImages(lig, col, 'T' ));
					this.plateau[lig][col].setIcon(image);
					System.out.println(this.plateau[lig][col].getIcon());

				}

				// Si la case est la pomme
				else if ( this.ctrl.getpomme()[0] == lig && this.ctrl.getpomme()[1] == col)
				{
					image = new ImageIcon(this.ctrl.getImages(lig, col, 'P' ));
					this.plateau[lig][col].setIcon(image);
				}

			}
	}
}