package src.ihm;

import src.Controleur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class FrameJeu extends JFrame
{

	private Controleur ctrl;

	public FrameJeu(Controleur ctrl)
	{

		this.ctrl = ctrl;

		this.setTitle("Snake");
		this.setSize(500, 200);
		this.setLocation(400, 400);

		this.setLayout(new BorderLayout());
		// this.setLayout(new FlowLayout());
		// this.setLayout(new GridLayout(lig, col, hgap, vgap));

		this.add( new PanelJeu(this.ctrl));

		this.setVisible(true);


		/* Fermer la fenÃªtre */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}