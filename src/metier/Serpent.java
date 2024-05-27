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

		this.tete          = new PartieCorpSerpent(7, 16, 'T');
		this.queue         = new PartieCorpSerpent(5, 16, 'Q');

		this.directionTete = 'E';

		this.corp.add(new PartieCorpSerpent(6, 16, 'C'));

		this.corp.get(0).ajouterEntre(this.tete, this.queue);
	}


	public PartieCorpSerpent       getTete         () { return tete; }
	public List<PartieCorpSerpent> getCorp         () { return corp; }
	public PartieCorpSerpent       getQueue        () { return queue; }
	public char                    getDirectionTete() { return this.directionTete; }
	public int                     getLongueurCorp () { return this.corp.size(); }


	public void setDirectionTete(char direction) { this.directionTete = direction; }


	public void ajouterCorp()
	{
		this.corp.add(new PartieCorpSerpent(this.corp.get(this.corp.size()-1).getCoordX(), this.corp.get(this.corp.size()-1).getCoordY(), 'C'));

		this.corp.get(this.corp.size()-1).ajouterEntre(this.corp.get(this.corp.size()-2), this.queue);
	}



}
