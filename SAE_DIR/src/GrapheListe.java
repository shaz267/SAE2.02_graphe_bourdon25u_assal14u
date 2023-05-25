import javax.print.attribute.standard.Destination;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Classe qui permet de representer les données associées à un graphe
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
     * Constructeur prenant un nom de fichier en paramètres et permettant de créer un graphe à partir d’un fichier
     * @param fichier
     */
    public GrapheListe(String fichier) {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");

                if (tokens.length != 3) {
                    throw new IllegalArgumentException("Format de fichier Invalide");
                }

                String depart = tokens[0];
                String destination = tokens[1];
                double cout = Double.parseDouble(tokens[2]);

                ajouterArc(depart, destination, cout);
            }
        } catch (IOException e) {
            throw new Error("Error reading file: " + fichier);
        }
    }

    /**
     * Constructeur qui génére des graphes automatiquement d’une taille donnée (par exemple 1000 nœuds) en choisissant un départ et une arrivée. Les noms des nœuds seront des entiers.
     */
    public GrapheListe(int tailleGraphe ,String depart, String arrivee) {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();

        int i = 0;

        ajouterArc(depart, String.valueOf(i) , (int)(Math.random() * 50));

        for (i = 0; i < tailleGraphe; i++) {

            ajouterArc(String.valueOf(i), String.valueOf(i+1), (int)(Math.random() * 50));
        }

        ajouterArc(String.valueOf(tailleGraphe), arrivee, (int)(Math.random() * 50));

        //Ajout supplémentaires de noeuds pour avoir un graphe plus complexe
        for (int j = (int)(Math.random() * tailleGraphe - 1); j < tailleGraphe; j++) {

            ajouterArc(String.valueOf(j), String.valueOf(j+2), (int)(Math.random() * 50));
        }

    }

    /**
     * Méthode ajouterArc permettant d'ajouter un arc dans ensNoeuds
     *
     * @param depart,      nom du noeud de depart
     * @param destination, nom du noeud de destination
     * @param cout,        cout de l'arc
     */
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
        return this.ensNom;
    }

    /**
     * Methode suivants permettant de recuperer la liste des Arcs partant d'un noeud
     * @param n, nom du noeud a etudier
     * @return retourne la liste des Arcs partant du noeud n
     */
    public List<Arc> suivants(String n) {

        List<Arc> lA = null;

        for (Noeud noeud : ensNoeuds) {

            if (noeud.getNom().equals(n)) {

                lA = noeud.getAdj();
            }
        }

        return lA;
    }

    /**
     * Methode toString permettant d'afficher le contenu d'un graphe
     * @return retourne une chaine de caracteres contenant les caracteristiques du graphe
     */
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

    /**
     * Methode toGraphViz permettant de representer le graphe en respectant le format GraphViz
     * @return retourne une chaine de caractere representant le graphe sous le format GraphViz
     */
    public String toGraphviz() {

        String rep = "digraph G {\n";

        for (String s : this.listeNoeuds()) {

            for (Arc a : this.suivants(s)) {
                rep += s + " ->" + " " + a.getDest() + " [label = " + (int) a.getCout() + "]\n";
            }
        }

        rep += "}";

        return rep;
    }


    /**
     * Méthode qui permet de tester cette classe
     * @return l'attribut ensNoeuds
     */
    public List<Noeud> getEnsNoeuds(){

        return this.ensNoeuds;
    }

    /**
     * Méthode qui permet de rajouter un nom
     */
    public void setEnsNom(String nom){

        this.ensNom.add(nom);
    }

    /**
     * Méthode qui permet de rajouter un noeud
     */
    public void setEnsNoeuds(Noeud noeud){

        this.ensNoeuds.add(noeud);
    }


    /**
     * Méthode qui permet de générer la liste des arcs à partir d'un fichier texte en mode matrice
     * @param fichier
     */
    public static void genererListeArcs(String fichier) {
        List<String> lignes = new ArrayList<String>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(fichier));

            String ligne = bf.readLine();

            while ( ligne != null ) {

                lignes.add(ligne);
                ligne = bf.readLine() ;
            }

            bf.close() ;

        } catch (IOException e) {
            throw new Error("Erreur de lecture du fichier : " + fichier);
        }

        List<String> arcs = new ArrayList<>();

        List<String> Destination = new ArrayList<String>();

        for (int i = 0; i < lignes.size(); i++){
            String ligne = lignes.get(i);
            String[] tokens = ligne.split("\t");

            String Depart = "";

            if (i != 0){

                Depart = tokens[0];
            }
            for (int j = 0; j < tokens.length; j++){

                if (i == 0){

                    Destination.add(tokens[j]);
                }
                else if (!tokens[j].equals("0.") && j != 0){

                    String arc = Depart + " " + Destination.get(j) + " " + tokens[j];
                    arcs.add(arc);
                }
            }
        }

        String sortie = fichier.replace(".txt", "_arcs.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sortie))) {
            for (String arc : arcs) {
                writer.write(arc);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new Error("Erreur d'écriture du fichier :" + sortie);
        }
    }
}
