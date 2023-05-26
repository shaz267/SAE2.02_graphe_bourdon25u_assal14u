import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * classe labyrinthe represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";


    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on descend colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom){
        try {
            // ouvrir fichier
            FileReader fichier = new FileReader(nom);
            BufferedReader bfRead = new BufferedReader(fichier);

            int nbLignes, nbColonnes;
            // lecture nblignes
            nbLignes = Integer.parseInt(bfRead.readLine());
            // lecture nbcolonnes
            nbColonnes = Integer.parseInt(bfRead.readLine());

            // creation labyrinthe vide
            this.murs = new boolean[nbColonnes][nbLignes];

            // lecture des cases
            String ligne = bfRead.readLine();

            // stocke les indices courants
            int numeroLigne = 0;

            // parcours le fichier
            while (ligne != null) {

                // parcours de la ligne
                for (int colonne = 0; colonne < ligne.length(); colonne++) {
                    char c = ligne.charAt(colonne);
                    switch (c) {
                        case MUR:
                            this.murs[colonne][numeroLigne] = true;
                            break;
                        case VIDE:
                            this.murs[colonne][numeroLigne] = false;
                            break;

                        default:
                            throw new Error("caractere inconnu " + c);
                    }
                }

                // lecture
                ligne = bfRead.readLine();
                numeroLigne++;
            }

            // ferme fichier
            bfRead.close();
        }
        catch (IOException e){
            System.out.println("[ERREUR] " + e.getMessage());
        }
    }


    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public int[] deplacerPerso(int i, int j,String action) {
        // case courante
        int[] courante = {i,j};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        if (!this.murs[suivante[0]][suivante[1]]) {
            // on met a jour personnage
            return suivante;
        }
        return courante;
    }


    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    public static GrapheListe genererGraphe(String labyrinthe) throws IOException{
        Labyrinthe laby = new Labyrinthe(labyrinthe);

        GrapheListe graphe = new GrapheListe();

        // On remplit ensNoeuds avec l'aide de la méthode ajouterArc
        for(int i = 1; i < laby.murs.length - 1; i++){
            for(int j = 1; j < laby.murs[i].length - 1; j++) {

                // On crée le nom du Noeud
                String nom = "(" + i + "," + j + ")";

                // On vérifie la case du dessus
                if (!laby.getMur(i, j-1)) {
                    String nom_haut = "(" + i + "," + (j-1) + ")";
                    // On ajoute chaque arc en 'aller-retour' parce que certains noeuds ne seraient visités qu'une fois
                    graphe.ajouterArc(nom, nom_haut, 1);
                    graphe.ajouterArc(nom_haut, nom, 1);
                }

                // On vérifie la case du dessous
                if (!laby.getMur(i, j+1)) {
                    String nom_bas = "(" + i + "," + (j+1) + ")";
                    graphe.ajouterArc(nom, nom_bas, 1);
                    graphe.ajouterArc(nom_bas, nom, 1);
                }

                // On vérifie la case de gauche
                if (!laby.getMur(i-1, j)) {
                    String nom_gauche = "(" + (i-1) + "," + (j) + ")";
                    graphe.ajouterArc(nom, nom_gauche, 1);
                    graphe.ajouterArc(nom_gauche, nom, 1);
                }

                // On vérifie la case de droite
                if (!laby.getMur(i+1, j)) {
                    String nom_droite = "(" + (i+1) + "," + j + ")";
                    graphe.ajouterArc(nom, nom_droite, 1);
                    graphe.ajouterArc(nom_droite, nom, 1);
                }

            }
        }
        return graphe;
    }
}
