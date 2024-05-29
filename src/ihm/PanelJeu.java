package src.ihm;

import src.Controleur;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanelJeu extends JPanel
{

	private Controleur ctrl;
	private JPanel     panelPlateau;
	private JLabel[][] plateau;

	public PanelJeu(Controleur ctrl)
	{

		this.setLayout(new BorderLayout());

		/* ------------------------ */
		/*  CrÃ©ation des composants */
		/* ------------------------ */

		this.ctrl = ctrl;


		this.panelPlateau  = new JPanel(new BorderLayout());
		this.plateau       = new JLabel[this.ctrl.getPlateau().length][this.ctrl.getPlateau()[0].length];

		for ( int lig = 0; lig < this.ctrl.getPlateau().length; lig ++ )
			for ( int col = 0; col < this.ctrl.getPlateau().length; col ++ )
			{
				this.plateau[lig][col] = new JLabel();
				this.panelPlateau.add(this.plateau[lig][col]);
			}


		for ( int lig = 0; lig < this.ctrl.getPlateau().length; lig ++ )
			for ( int col = 0; col < this.ctrl.getPlateau().length; col ++ )
			{
				Icon image = new ImageIcon(this.ctrl.getImages(this.ctrl.getPlateau()[lig][col]));

				this.plateau[lig][col].setIcon(image);
			}

		this.add(this.panelPlateau, BorderLayout.CENTER);


		/* -------------------------- */
		/*  Activation des composants */
		/* -------------------------- */


	}

	public void majPlateau()
	{
		for ( int lig = 0; lig < this.ctrl.getPlateau().length; lig ++ )
			for ( int col = 0; col < this.ctrl.getPlateau().length; col ++ )
			{
				Icon image = new ImageIcon(this.ctrl.getImages(this.ctrl.getPlateau()[lig][col]));

				this.plateau[lig][col].setIcon(image);
			}
	}



}