import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDijkstra {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Donner le nom du fichier à lire (Sans le .txt): ");
        String fichier = sc.nextLine();

        Graphe g = new GrapheListe("Graphes/" + fichier + ".txt");

        System.out.println("Donner le nom du noeud de départ: ");
        String depart = sc.nextLine();

        System.out.println("Donner le nom du noeud d'arrivée: ");
        String arrivee = sc.nextLine();

        Valeur v = Dijkstra.resoudre(g, depart);

        List<String> chemin = v.calculerChemin(arrivee);

        System.out.println("Chemin le plus court de " + depart + " à " + arrivee + " : " + chemin);
    }
}
