package src.metier;

import java.util.ArrayList;
import java.util.List;

public class Serpent
{

	private PartieCorpSerpent       tete;
	private List<PartieCorpSerpent> corp;
	private PartieCorpSerpent       queue;
	private char                    directionTete;



	public Serpent()
	{
		this.corp          = new ArrayList<>();

		this.tete          = new PartieCorpSerpent(16, 7, 'T');
		this.queue         = new PartieCorpSerpent(16, 5, 'Q');

		this.directionTete = 'E';

		this.corp.add(new PartieCorpSerpent(16, 6, 'C'));

		this.corp.get(0).ajouterEntre(this.tete, this.queue);
	}


	public PartieCorpSerpent       getTete         () { return tete; }
	public List<PartieCorpSerpent> getCorp         () { return corp; }
	public PartieCorpSerpent       getQueue        () { return queue; }
	public char                    getDirectionTete() { return this.directionTete; }
	public int                     getLongueurCorp () { return this.corp.size(); }


	public void setDirectionTete(char direction) { this.directionTete = direction; }


	public void ajouterCorp(int coordX, int coordY)
	{
		this.corp.add(new PartieCorpSerpent(coordX, coordY, 'C'));

		this.corp.get(this.corp.size()-1).ajouterEntre(this.corp.get(this.corp.size()-2), this.queue);
	}

	public boolean deplacerSerpent()
	{

		this.queue.setCoordX(this.corp.get(this.corp.size()-1).getCoordX());
		this.queue.setCoordY(this.corp.get(this.corp.size()-1).getCoordY());

		// Moins 1 car size() donne un élément en plus car il par de 1
		for(int cpt = this.corp.size() - 1; cpt > 0; cpt --)
		{
			// cpt - 1 pour avoir le corp avant celui où on change la place
			this.corp.get(cpt).setCoordX(this.corp.get(cpt-1).getCoordX());
			this.corp.get(cpt).setCoordY(this.corp.get(cpt-1).getCoordY());
		}

		this.corp.get(0).setCoordX(this.tete.getCoordX());
		this.corp.get(0).setCoordY(this.tete.getCoordY());


		switch ( this.directionTete )
		{
			case 'N' ->
			{
				if ( this.tete.getCoordX() - 1 > 0 )
				{
					this.tete.setCoordX(this.tete.getCoordX() - 1);
					return true;
				}
			}
			case 'S' ->
			{
				if ( this.tete.getCoordX() + 1 < 32 )
				{
					this.tete.setCoordX(this.tete.getCoordX() + 1);
					return true;
				}
			}
			case 'O' ->
			{
				if ( this.tete.getCoordY() - 1 > 0 )
				{
					this.tete.setCoordY(this.tete.getCoordY() - 1);
					return true;
				}
			}
			case 'E' ->
			{
				if ( this.tete.getCoordY() + 1 < 32 )
				{
					this.tete.setCoordY(this.tete.getCoordY() + 1);
					return true;
				}

			}
		}

		return false;
	}

}
