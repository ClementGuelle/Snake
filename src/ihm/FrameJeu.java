/**
 * @author GUELLE Clément
 */

package src.ihm;

import src.Controleur;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class FrameJeu extends JFrame
{

	/*--------------*/
	/*    Données   */
	/*--------------*/

	private PanelJeu   panelJeu;
	private Controleur ctrl;




	/*--------------*/
	/* Instructions */
	/*--------------*/

	/**
	 * Constructeur pour créer la Frame
	 * @param ctrl le contrôleur utilisé pour y avoir accès par la suite
	 */
	public FrameJeu(Controleur ctrl)
	{

		this.setTitle("Snake");
		this.setSize(900,1000);

		this.setLayout(new BorderLayout());

		this.ctrl     = ctrl;
		this.panelJeu = new PanelJeu(this.ctrl);

		this.add(this.panelJeu);

		// Cela permet de savoir quelle touche est pressé pour le déplacement du serpent
		this.addKeyListener(new TouchePresse());
		this.setFocusable(true);

		// Rendre visible la frame
		this.setVisible(true);

		// ferme la fenêtre grâce à la croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * Méthode permettant de mettre à jour le plateau du coté IHM
	 */
	public void majPlateau() { this.panelJeu.majPlateau(); }


	/**
	 * La classe permet de voir tous ce qui touche au clavier
	 */
	public class TouchePresse implements KeyListener
	{

		/**
		 * Méthode permettant en fonction de la touche pressé de tourner le serpent
		 * @param e élément pressé
		 */
		public void keyPressed(KeyEvent e)
		{
			char charTete   = ctrl.getSerpent().getDirectionTete();
			char charPresse = ' ';

			switch ( e.getKeyCode() )
			{
				case (KeyEvent.VK_UP)   ->
				{
					if ( charTete != 'S')
						charPresse = 'N';
				}
				case (KeyEvent.VK_DOWN) ->
				{
					if ( charTete != 'N')
						charPresse = 'S';
				}
				case (KeyEvent.VK_LEFT) ->
				{
					if ( charTete != 'E')
						charPresse = 'O';
				}
				case (KeyEvent.VK_RIGHT)->
				{
					if ( charTete != 'O')
						charPresse = 'E';
				}
			}

			if (charPresse != ' ')
				FrameJeu.this.ctrl.setDirectionTete(charPresse);
		}

		// Méthode inutilisé mais obliger à cause de l'implémentation
		public void keyReleased(KeyEvent e) { }

		// Méthode inutilisé mais obliger à cause de l'implémentation
		public void keyTyped(KeyEvent e) { }

	}



}