import java.util.ArrayList;
import java.util.List;

/**
 * Classe GrapheLabyrinthe permettant de créer un graphe à partir d'un labyrinthe que nous n'avons pas réussi à terminer
 */
public class GrapheLabyrinthe implements Graphe{

    private Labyrinthe labyrinthe;

    public GrapheLabyrinthe(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    public List<String> listeNoeuds() {
        return null;
    }

    public List<Arc> suivants(String n) {

        /*List<Arc> lA = new ArrayList<Arc>();

        for (int i = 0; i < labyrinthe.murs.length; i++) {

            for (int j = 0; j < labyrinthe.murs[i].length; j++) {

                if (labyrinthe.murs[i][j].getNom().equals(n)) {


                }
            }
        }*/
        return null;
    }
}
