package src.metier;

import src.metier.Tete;

public class Plateau
{

    private char[][] plateau;
    private Tete     tete;

    /**
     *
     * Constructeur de la classe Plateau
     */
    public Plateau()
    {
        this.tete    = new Tete();
        this.plateau = initPlateau();
    }


    /**
     * Création du plateau
     * @return le plateau construit avec le serpent et un pomme
     */
    public char[][] initPlateau()
    {
        char[][] plateau = new char[32][32];

        for( int lig = 0; lig < plateau.length; lig ++)
            for( int col = 0; col < plateau[lig].length; col ++)
                plateau[lig][col] = 'V';


        plateau[16][10] = 'Q';
        plateau[16][11] = 'C';
        plateau[this.tete.getCoordX()][this.tete.getCoordX()] = 'T';

        plateau[16][25] = 'P';


        return plateau;
    }
}