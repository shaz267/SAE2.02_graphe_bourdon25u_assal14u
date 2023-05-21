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

    public GrapheListe(String fichier) {
        this.ensNom = new ArrayList<>();
        this.ensNoeuds = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");

                if (tokens.length != 3) {
                    throw new IllegalArgumentException("Invalid file format");
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

    public void genererListeArcs(String fichier) {
        List<String> lignes;
        try {
            lignes = Files.readAllLines(Path.of(fichier));
        } catch (IOException e) {
            throw new Error("Error reading file: " + fichier);
        }

        List<String> arcs = new ArrayList<>();

        for (int i = 1; i < lignes.size(); i++) {
            String ligne = lignes.get(i);
            String[] tokens = ligne.split("\\s+");
            String depart = Character.toString((char) ('A' + i - 1));
            for (int j = 1; j < tokens.length; j++) {
                String poidsStr = tokens[j];
                if (!poidsStr.equals("0.")) {
                    String destination = Character.toString((char) ('A' + j - 1));
                    String arc = depart + " " + destination + " " + poidsStr;
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
            throw new Error("Error writing to file: " + sortie);
        }
    }
}
