/**
 * main qui crée le graphe présenté dans la figure 1
 */
public class Main {

    /**
     * main qui applique l’algorithme du point fixe sur le graphe fourni pour construire le chemin le plus court. Afficher les valeurs de distance pour chaque nœud et v´erifier qu’elles correspondent aux valeurs que nous avons calcul´ees dans le module graphe
     * @param args
     */
    public static void main(String[] args) {

        //Ajout des noeuds
        /*Noeud nA = new Noeud("A");
        Noeud nB = new Noeud("B");
        Noeud nC = new Noeud("C");
        Noeud nD = new Noeud("D");
        Noeud nE = new Noeud("E");*/

        /*GrapheListe graphe = new GrapheListe();

        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("E", "D", 43);*/

        GrapheListe graphe = new GrapheListe("Graphes/GrapheBoucle.txt");

        //System.out.println(graphe.toGraphviz());

        //GrapheListe graphe2 = new GrapheListe("C:\\Users\\bourd\\Desktop\\cours\\saé\\SAE2.02_graphe\\SAE2.02_graphe_bourdon25u_assal14u\\SAE_DIR\\Graphes\\Graphe1.txt");
        //System.out.println(graphe2.toGraphviz());

        //graphe2.genererListeArcs("C:\\Users\\bourd\\Desktop\\cours\\saé\\SAE2.02_graphe\\SAE2.02_graphe_bourdon25u_assal14u\\SAE_DIR\\Graphes\\Graphe1.txt");

        //GrapheListe graphe = new GrapheListe();
        //graphe.genererListeArcs("C:\\Users\\hugoa\\OneDrive - Universite de Lorraine\\Documents\\SAE2.02_graphe_bourdon25u_assal14u\\SAE_DIR\\Graphes\\testMatrice.txt");


        String depart = "A";

        System.out.println("Dijkstra");
        Valeur v = Dijkstra.resoudre(graphe, depart);

        System.out.println(v.toString());

        System.out.println("Bellman-Ford");
        Valeur v2 = BellmanFord.resoudre(graphe, depart);

        System.out.println(v2.toString());

        //System.out.println("Vérification des valeurs ...");

        //TODO: vérifier les valeurs
    }
}
