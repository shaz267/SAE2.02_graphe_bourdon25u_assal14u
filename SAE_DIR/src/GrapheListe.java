import java.util.List;

public class GrapheListe implements Graphe{

    /**
     * contient les noms des objets nœuds stockés
     */
    private List<String> ensNom;

    /**
     * une liste d’objet Noeud permettant de stocker les arcs.
     */
    private List<Noeud> ensNoeuds;

    /**
     * Méthode ajouterArc permettant d'ajouter un arc dans ensNoeuds
     * @param depart, nom du noeud de depart
     * @param destination, nom du noeud de destination
     * @param cout, cout de l'arc
     */
    public void ajouterArc(String depart, String destination, double cout){

        Noeud noeud = new Noeud(depart);
        noeud.ajouterArc(destination, cout);
        this.ensNoeuds.add(noeud);
    }

    /**
     * Méthode listeNoeuds retournant la liste des Noeuds du graphe, soit son nom
     * @return retourne la liste des noeuds stockes pour le graphe en question
     */
    public List<String> listeNoeuds(){
        return ensNom;
    }


    public List<Arc> suivants(String n){
        for(int i = 0; i < ensNom.size(); i++){

        }
    }
}
