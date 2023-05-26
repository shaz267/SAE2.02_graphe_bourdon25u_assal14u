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
     * Méthode equals permettant de verifier si deux noeuds ont le meme nom
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

        adj.add(new Arc(destination, cout));
    }


    /**
     * Méthode getAdj permettant de recuperer la liste adj
     * @return liste adj
     */
    public List<Arc> getAdj(){
        return this.adj;
    }


    /**
     * Méthode getNom permettant de recuperer le nom du noeud
     * @return nom du noeud
     */
    public String getNom(){

        return this.nom;
    }

    /**
     * Méthode setAdj permettant de modifier la liste adj
     * @param adj, nouvelle liste adj
     */
    public void setAdj(List<Arc> adj){
        this.adj = adj;
    }

}
