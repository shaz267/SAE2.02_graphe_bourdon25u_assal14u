/**
 * main qui crée le graphe présenté dans la figure 1
 */
public class Main {

    public static void main(String[] args) {

        //Ajout des noeuds
        /*Noeud nA = new Noeud("A");
        Noeud nB = new Noeud("B");
        Noeud nC = new Noeud("C");
        Noeud nD = new Noeud("D");
        Noeud nE = new Noeud("E");*/

        GrapheListe graphe = new GrapheListe();

        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("E", "D", 43);

        System.out.println(graphe.toGraphviz());
    }
}
