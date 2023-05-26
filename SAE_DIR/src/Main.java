import java.io.File;
import java.io.IOException;

/**
 * main qui crée le graphe présenté dans la figure 1
 */
public class Main {

    /**
     * méthode getNomsFichiers qui permet de récupérer les noms des fichiers pour les mettre dans un tableau de String
     * @param cheminDossier
     * @return
     */
    public static String[] getNomsFichiers(String cheminDossier) {
        // On crée un objet File avec le chemin du dossier passé en paramètres
        File dossier = new File(cheminDossier);

        // On récupère les fichiers dans un tableau de File
        File[] fichiers = dossier.listFiles((dir, nomFichier) -> nomFichier.toLowerCase().endsWith(".txt"));

        // On récupère tous les noms de fichiers pour les insérer dans le tableau de String 'nomFichiersTXT'
        String[] nomsFichiersTXT = new String[fichiers.length];

        for (int i = 0; i < fichiers.length; i++) {
            nomsFichiersTXT[i] = fichiers[i].getName();
        }
        return nomsFichiersTXT;
    }

    /**
     * Méthode afficherTemps qui permet d'afficher les temps d'exécution des algorithmes de BellmanFord et Dijkstra
     * @param nomsFichiers
     */
    public static void afficherTemps(String[] nomsFichiers){
        // On sépare les résultats en 3 colonnes pour faciliter le remplissage du fichier csv: 1) Noms des graphes
        // 2) temps d'exécution pour BellmanFord 3) temps d'exécution pour Dijkstra
        // On crée la colonne des noms des graphes
        String nomsGraphes = "Nom\n";
        // On arrête la boucle à 4 itérations avant la fin (car les 4 derniers fichiers sont des matrices et non des Graphes)
        for(int i = 0; i < nomsFichiers.length - 4; i++){
            nomsGraphes += nomsFichiers[i] + "\n";
        }
        System.out.println(nomsGraphes);

        // Calcul du temps en nanosecondes pour l'algorithme de BellmanFord
        String temps_BF = "BellmanFord\n";
        double departBF;
        double finBF;
        double tempsExeBF;

        for(int i = 1; i < nomsFichiers.length; i++){
            Graphe graphe = new GrapheListe("Graphes/" + nomsFichiers[i]);
            departBF = System.nanoTime();
            Valeur val = BellmanFord.resoudre(graphe, graphe.listeNoeuds().get(0));
            finBF = System.nanoTime();
            tempsExeBF = finBF - departBF;
            temps_BF += tempsExeBF + "\n";
        }

        // Calcul du temps en nanosecondes pour l'algorithme de Dijkstra
        String temps_D = "Dijkstra\n";
        double departD;
        double finD;
        double tempsExeD;

        for(int i = 1; i < nomsFichiers.length; i++){
            Graphe graphe = new GrapheListe("Graphes/" + nomsFichiers[i]);
            departD = System.nanoTime();
            Valeur val = BellmanFord.resoudre(graphe, graphe.listeNoeuds().get(0));
            finD = System.nanoTime();
            tempsExeD = finD - departD;
            temps_D += tempsExeD + "\n";
        }
    }

    /**
     * main qui applique l’algorithme du point fixe sur le graphe fourni pour construire le chemin le plus court. Afficher les valeurs de distance pour chaque nœud et v´erifier qu’elles correspondent aux valeurs que nous avons calcul´ees dans le module graphe
     * @param args
     */
    public static void main(String[] args) {

        //Question 7 :
        //Ajout des noeuds
        /*Noeud nA = new Noeud("A");
        Noeud nB = new Noeud("B");
        Noeud nC = new Noeud("C");
        Noeud nD = new Noeud("D");
        Noeud nE = new Noeud("E");*/

        /*GrapheListe graphe = new GrapheListe();

        //Tests de l'ajout des noeuds et des arcs
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("E", "D", 43);*/

        //GrapheListe graphe = new GrapheListe(10, "1", "50");

        //Test de la méthode toGraphviz()
        //System.out.println(graphe.toGraphviz());

        //Test de la méthode genererListeArcs()
        //graphe2.genererListeArcs("C:\\Users\\bourd\\Desktop\\cours\\saé\\SAE2.02_graphe\\SAE2.02_graphe_bourdon25u_assal14u\\SAE_DIR\\Graphes\\Graphe1.txt");

        //Variable qui est utilisée pour avoir un noeud de départ
        String depart = "1";

        //Question 22 :
        /*System.out.println("Algorithme de Dijkstra : \n");
        Valeur v = Dijkstra.resoudre(graphe, depart);

        System.out.println("Etat final : \n" + v);

        System.out.println("Algorithme de Bellman-Ford : \n");
        Valeur v2 = BellmanFord.resoudre(graphe, depart);

        System.out.println("Etat final : \n" + v2);*/

        //GrapheListe graphe2 = new GrapheListe(100, "1" , "100");
        //System.out.println(graphe2.toGraphviz());

        //Question 24 :
        //Main.afficherTemps(Main.getNomsFichiers("Graphes"));


        //Question 25 :
        /*int compteur = 0;
        long moyB = 0;
        long moyD = 0;
        for (int i = 1; i < 500; i +=10){
            compteur++;

            GrapheListe graphe = new GrapheListe(i, depart , String.valueOf(i));
            long departTB = System.nanoTime();
            BellmanFord.resoudre(graphe, "1");
            long arriveB = System.nanoTime();
            long tempsB = arriveB - departTB;

            moyB += tempsB;

            System.out.println("Graphe de taille : " + i + " résultat pour algo BellmanFord : " + tempsB);

            long departTD = System.nanoTime();
            Dijkstra.resoudre(graphe, "1");
            long arriveD = System.nanoTime();
            long tempsD = arriveD - departTD;

            moyD += tempsD;

            System.out.println("Graphe de taille : " + i + " résultat pour algo Dijkstra : " + tempsD);

            System.out.println("ratio de performance temps de BellmanFord divisé par le temps de Dijkstra : " + (double) tempsB / tempsD);

            System.out.println("");
        }
        moyB /= compteur;
        moyD /= compteur;
        System.out.println("Moyenne pour BellmanFord : " + moyB);
        System.out.println("Moyenne pour Dijkstra : " + moyD);*/


        //Partie Labyrinthe :
        /*try {

            GrapheListe graphe = Labyrinthe.genererGraphe("labySimple/laby0.txt");
            System.out.println(BellmanFord.resoudre(graphe, "(1,1)").calculerChemin("(5,3)"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        // test Question 32:
        Labyrinthe labyrinthe = new Labyrinthe("labySimple/laby0.txt");
        GrapheLabyrinthe graphe = new GrapheLabyrinthe(labyrinthe);
        GrapheListe grapheListe = new GrapheListe();
        // On ajoute les noms des noeuds
        for(int i = 0; i < graphe.listeNoeuds().size(); i++){
            grapheListe.setEnsNom(graphe.listeNoeuds().get(i));
        }
        // On ajoute les noeuds
        for(int i = 0; i < graphe.listeNoeuds().size(); i++){
            Noeud noeud = new Noeud(graphe.listeNoeuds().get(i));
            noeud.setAdj(graphe.suivants(noeud.getNom()));
            grapheListe.setEnsNoeuds(noeud);
        }
        // On affiche le graphe
        System.out.println(grapheListe.toGraphviz());
        // On affiche enfin le chemin le plus court pour parcourir le graphe
        System.out.println(BellmanFord.resoudre(grapheListe, "(1,1)"));
    }
}
