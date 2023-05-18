import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet de repr´esenter les données associées à un graphe
 */
public class GrapheListe implements Graphe {

    /**
     * contient les noms des objets nœuds stockés
     */
    private List<String> ensNom;

    /**
     * une liste d’objet Noeud permettant de stocker les arcs.
     */
    private List<Noeud> ensNoeuds;

    public GrapheListe() {

        this.ensNom = new ArrayList<String>();
        this.ensNoeuds = new ArrayList<Noeud>();
    }

    /**
     * Méthode ajouterArc permettant d'ajouter un arc dans ensNoeuds
     *
     * @param depart,      nom du noeud de depart
     * @param destination, nom du noeud de destination
     * @param cout,        cout de l'arc
     */
    /*public void ajouterArc(String depart, String destination, double cout){  // premiere version

        Noeud noeudDepart = new Noeud(depart);
        this.ensNoeuds.add(noeudDepart);
        this.ensNom.add(depart);

        Noeud noeudDest = new Noeud(destination);
        this.ensNoeuds.add(noeudDest);
        this.ensNom.add(destination);

        if (ensNoeuds.contains(noeudDepart) &&  ensNoeuds.contains(noeudDest)){

            noeudDepart.ajouterArc(destination, cout);
        }
    }*/

    //deuxième
    public void ajouterArc(String depart, String destination, double cout) {

        // Vérification si les nœuds existent dans le graphe
        if (!ensNom.contains(depart)) {
            ensNom.add(depart);
            ensNoeuds.add(new Noeud(depart));
        }
        if (!ensNom.contains(destination)) {
            ensNom.add(destination);
            ensNoeuds.add(new Noeud(destination));
        }

        // Recherche du nœud de départ dans la liste ensNoeuds
        Noeud noeudDepart = new Noeud(depart);
        for (Noeud noeud : ensNoeuds) {
            if (noeud.equals(noeudDepart)) {
                noeudDepart = noeud;
                break;
            }
        }

        noeudDepart.ajouterArc(destination, cout);
    }


    /**
     * Méthode listeNoeuds retournant la liste des Noeuds du graphe en fonction de son nom
     *
     * @return retourne la liste des noeuds stockes pour le graphe en question
     */
    public List<String> listeNoeuds() {
        return ensNom;
    }


    public List<Arc> suivants(String n) {

        List<Arc> lA = null;

        for (Noeud noeud : ensNoeuds) {

            if (noeud.getNom().equals(n)) {

                lA = noeud.getAdj();
            }
        }

        return lA;
    }

    public String toString() {

        String rep = "";
        int count = 0;
        int totalNodes = this.listeNoeuds().size();

        for (String s : this.listeNoeuds()) {
            rep += s + " ->";

            for (Arc a : this.suivants(s)) {
                rep += " " + a.getDest() + "(" + (int) a.getCout() + ")";
            }

            count++;
            if (count < totalNodes) {
                rep += "\n";
            }
        }

        return rep;
    }

    public String toGraphviz() {

        String rep = "diagraph G {\n";

        for (String s : this.listeNoeuds()) {

            for (Arc a : this.suivants(s)) {
                rep += s + " ->" + " " + a.getDest() + " [label = " + (int) a.getCout() + "]\n";
            }
        }

        rep += "}";

        return rep;
    }

}
