package src.metier;

import java.time.Duration;
import java.time.Instant;

public class Timer
{
	private Instant debutTemps;
	private Instant finTemps;
	private boolean running;


	public Timer()
	{
		this.running    = false;
		this.debutTemps = null;
		this.finTemps   = null;
	}

	public void start()
	{
		if (!running)
		{
			debutTemps = Instant.now();
			running    = true;
		}
	}

	public void stop()
	{
		if (running)
		{
			finTemps = Instant.now();
			running  = false;
		}
	}

	public void reset()
	{
		running    = false;
		debutTemps = null;
		finTemps   = null;
	}

	public long getElapsedMillis()
	{
		if (running)
		{
			return Duration.between(debutTemps, Instant.now()).toMillis();
		}
		else if (debutTemps != null && finTemps != null)
		{
			return Duration.between(debutTemps, finTemps).toMillis();
		}
		else
		{
			return 0;
		}
	}

	public String getTime()
	{
		long millis  = getElapsedMillis();
		long heure   =  millis / 3600000;
		long minutes = (millis % 3600000) / 60000;
		long seconde = (millis % 60000)   / 1000;

		return String.format("%02d:%02d:%02d", heure, minutes, seconde);
	}

	public boolean estSecondePile()
	{
		long millis  = getElapsedMillis();
		long seconde = (millis % 60000) / 1000;

		millis = millis - (seconde * 1000);

		if( millis == 0 )
			return true;

		return false;
	}

}
