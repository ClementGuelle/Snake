package src;

import src.ihm.FrameJeu;
import src.metier.Jeu;
import src.metier.Plateau;

public class Controleur
{
	private Jeu jeu;
	private FrameJeu frameJeu;

	public Controleur()
	{
		this.jeu      = new Jeu();
		this.frameJeu = new FrameJeu(this);
	}

	public char[][] getPlateau()
	{
		return this.jeu.getPlateau();
	}




	public static void main(String[] args)
	{
		new Controleur();
	}
}
