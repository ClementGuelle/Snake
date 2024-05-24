package src.metier;

public class Tete
{

	private int  coordX;
	private int  coordY;
	private char direction;

	public Tete()
	{
		this.coordX    = 16;
		this.coordY    = 12;
		this.direction = 'E';
	}

	public int getCoordX()
	{
		return coordX;
	}

	public void setCoordX(int coordX)
	{
		this.coordX = coordX;
	}

	public int getCoordY()
	{
		return coordY;
	}

	public void setCoordY(int coordY)
	{
		this.coordY = coordY;
	}

	public char getDirection()
	{
		return direction;
	}

	public void setDirection(char direction)
	{
		this.direction = direction;
	}
}
