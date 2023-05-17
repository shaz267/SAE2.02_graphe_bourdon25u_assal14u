import java.util.ArrayList;
import java.util.List;

/**
 * Classe Noeud permettant de créer des noeuds
 */
public class Noeud {

    /**
     * attributs nom, correspondant au nom du noeud
     */
    private String nom;

    /**
     * attribut adj, etant la liste des noeuds adjacents
     */
    private List<Arc> adj;

    /**
     * Constructeur de Noeud
     *
     * @param n nom du noeud à creer
     */
    public Noeud(String n) {
        this.nom = n;
        this.adj = new ArrayList<Arc>();
    }

    /**
     * Méthode equals permettant de verifier si deux noeuds sont egaux
     * @param n, nom du 2eme noeud à tester
     * @return retourne true si les noeuds sont égaux, false sinon
     */
    public boolean equals(Noeud n) {
        return this.nom.equals(n.nom);
    }

    /**
     * Méthode ajouterArc permettant d'ajouter un arc partant du noeud courant dans la liste adj
     * @param destination, destination de l'arc a ajouter
     * @param cout, cout de l'arc
     */
    public void ajouterArc(String destination, double cout) {
        Arc arc = new Arc(destination, cout);
        adj.add(arc);
    }

    public List<Arc> getAdj(){
        return this.adj;
    }
}
