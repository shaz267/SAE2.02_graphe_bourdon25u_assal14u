import java.util.*;

/**
 * déclaration de la classe GrapheLabyrinthe
 */
public class GrapheLabyrinthe implements Graphe {

    /**
     * attribut labyrinthe, correspondant au labyrinthe du graphe
     */
    private Labyrinthe labyrinthe;

    /**
     * constructeur de GrapheLabyrinthe
     * @param labyrinthe
     */
    public GrapheLabyrinthe(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    /**
     * méthode listeNoeuds, permettant de retourner la liste des noeuds du graphe
     * @return noeuds, liste des noeuds du graphe
     */
    public ArrayList<String> listeNoeuds() {
        ArrayList<String> noeuds = new ArrayList<>();
        int longueur = labyrinthe.getLength();
        int longueurY = labyrinthe.getLengthY();

        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < longueurY; j++) {
                if (!labyrinthe.getMur(i, j)) {
                    String noeud = "(" + i + "," + j + ")";
                    noeuds.add(noeud);
                }
            }
        }

        return noeuds;
    }

    /**
     * méthode suivants, permettant de retourner la liste des arcs ayant comme départ le noeud 'noeud' passé en paramètre
     * @param noeud noeud en question
     * @return liste des arcs partant du noeud 'noeud'
     */
    public ArrayList<Arc> suivants(String noeud) {
        ArrayList<Arc> arcs = new ArrayList<>();
        String[] souschaine = noeud.substring(1, noeud.length() - 1).split(",");
        int x = Integer.parseInt(souschaine[0]);
        int y = Integer.parseInt(souschaine[1]);

        // On vérifie chaque déplacement (Haut, Bas, Gauche, Droite)
        String[] deplacement = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};
        for (String action : deplacement) {
            int[] suivant = labyrinthe.getSuivant(x, y, action);
            int suivantX = suivant[0];
            int suivantY = suivant[1];

            if (suivantX >= 0 && suivantX < labyrinthe.getLength() && suivantY >= 0 && suivantY < labyrinthe.getLengthY()) {
                if (!labyrinthe.getMur(suivantX, suivantY)) {
                    String noeudSuivant = "(" + suivantX + "," + suivantY + ")";
                    arcs.add(new Arc(noeudSuivant, 1));
                }
            }
        }
        return arcs;
    }
}
