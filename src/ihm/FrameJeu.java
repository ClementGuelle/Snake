package src.ihm;

import src.Controleur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class FrameJeu extends JFrame
{

	private PanelJeu   panelJeu;
	private Controleur ctrl;

	public FrameJeu(Controleur ctrl)
	{

		this.ctrl = ctrl;

		this.setTitle("Snake");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocation(400, 400);

		this.setLayout(new BorderLayout());

		this.panelJeu = new PanelJeu(this.ctrl);

		this.add(this.panelJeu);

		// Cela permet de savoir quelle touche est pressé pour le déplacement du serpent
		this.addKeyListener(new TouchePresse());
		this.setFocusable(true);

		this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void majPlateau() { this.panelJeu.majPlateau(); }

	public class TouchePresse implements KeyListener
	{

		public void keyTyped(KeyEvent e)
		{
		}

		public void keyPressed(KeyEvent e)
		{
			char charPresse = ' ';

			switch ( e.getKeyCode() )
			{
				case (KeyEvent.VK_UP)   -> charPresse = 'N';
				case (KeyEvent.VK_DOWN) -> charPresse = 'S';
				case (KeyEvent.VK_LEFT) -> charPresse = 'O';
				case (KeyEvent.VK_RIGHT)-> charPresse = 'E';
			}

			if (charPresse != ' ')
				FrameJeu.this.ctrl.setDirectionTete(charPresse);
		}

		public void keyReleased(KeyEvent e)
		{
		}

	}



}