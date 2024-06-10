/**
 * @author GUELLE Clément
 */

package src.ihm;

import src.Controleur;
import src.metier.PartieCorpSerpent;

import javax.swing.*;

import java.awt.*;


public class PanelJeu extends JPanel
{

	/*--------------*/
	/*    Données   */
	/*--------------*/

	private static final int TAILLE_PLATEAU = 32;

	private Controleur ctrl;
	private JPanel     panelPlateau;
	private JLabel[][] plateau;



	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur pour créer le Panel
	 * @param ctrl le contrôleur utilisé pour y avoir accès par la suite
	 */
	public PanelJeu(Controleur ctrl)
	{

		this.setLayout(new BorderLayout());


		this.ctrl         = ctrl;
		this.panelPlateau = new JPanel(new GridLayout(PanelJeu.TAILLE_PLATEAU, PanelJeu.TAILLE_PLATEAU));
		this.plateau      = new JLabel[PanelJeu.TAILLE_PLATEAU][PanelJeu.TAILLE_PLATEAU];



		for ( int lig = 0; lig < this.plateau.length; lig ++ )
			for ( int col = 0; col < this.plateau[lig].length; col ++ )
			{

				// variable utilisé pour parcourir le serpent
				PartieCorpSerpent partiSerpentVerif = this.ctrl.getSerpent().getQueue();
				this.plateau[lig][col]              = new JLabel();

				// Permet de mettre un contour noir aux cases
				//this.plateau[lig][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


				// Si la case est la queue ou une partie du corps du serpent
				do
				{

					if ( partiSerpentVerif.getCoordX() == lig && partiSerpentVerif.getCoordY() == col )
					{
						Icon image = new ImageIcon(this.ctrl.getImages( lig, col, partiSerpentVerif.getPartieCorp() ));
						this.plateau[lig][col].setIcon(image);
					}

					partiSerpentVerif = partiSerpentVerif.getSuivant();

				} while ( partiSerpentVerif.getPartieCorp() != 'T' );


				// Si aucune icon n'a était placé
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


					// Sinon la case est vide
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


	/**
	 * Méthode permettant la mise à jour coté IHM pour voir l'avancé du serpent
	 */
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