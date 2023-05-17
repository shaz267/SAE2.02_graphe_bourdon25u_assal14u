import java.util.List;

/**
 * Interface Graphe
 */
public interface Graphe {

    /**
     * Méthode listeNoeuds permettant de lister les noeuds d'un graphe
     * @return retourne tous les noeuds du graphe sous forme de String, c'est a dire le nom du graphe
     */
    public List<String> listeNoeuds();

    /**
     * Méthode suivants permettant de recuperer la liste des arcs partant du noeud passe en parametre
     * @param n, nom du noeud a etudier
     * @return retourne la liste des arcs partant du noeud n passe en parametre
     */
    public List<Arc> suivants(String n);
}
