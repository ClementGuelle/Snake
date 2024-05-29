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

	private PanelJeu  panelJeu;
	private Controleur ctrl;

	public FrameJeu(Controleur ctrl)
	{

		this.ctrl = ctrl;

		this.setTitle("Snake");
		this.setSize(500, 200);
		this.setLocation(400, 400);

		this.setLayout(new BorderLayout());

		this.panelJeu = new PanelJeu(this.ctrl);

		this.add(this.panelJeu);
		this.addKeyListener(new TouchePresse());
		this.setFocusable(true);

		this.setVisible(true);


		/* Fermer la fenÃªtre */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void majPlateau()
	{
		this.panelJeu.majPlateau();
	}


	public class TouchePresse implements KeyListener
	{

		@Override
		public void keyTyped(KeyEvent e)
		{
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			System.out.println("char = " + e.getKeyChar());
			String misEnString = "" + e.getKeyChar();
			String majuscule   = misEnString.toUpperCase();

			FrameJeu.this.ctrl.setDirectionTete(majuscule.charAt(0));
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
		}

	}

}