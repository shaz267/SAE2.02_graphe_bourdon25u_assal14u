import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test ;

import java.util.ArrayList;
import java.util.List;

public class TestGrapheListe {

    @Test
    public void test01_AjouterArcContient(){

        //Preparation des données
        GrapheListe graphe = new GrapheListe();
        String nom1 = "Alain";
        String nom2 = "Albert";
        Noeud n1 = new Noeud(nom1);
        Noeud n2 = new Noeud(nom2);
        
        graphe.setEnsNom(nom1);
        graphe.setEnsNoeuds(n1);
        
        graphe.setEnsNom(nom2);
        graphe.setEnsNoeuds(n2);

        // methode a tester
        graphe.ajouterArc("Alain", "Albert", 10);

        // verifications
        assertEquals("Alain", graphe.listeNoeuds().get(0), "Le premier noeud doit avoir comme nom Alain");
        assertEquals("Albert", graphe.listeNoeuds().get(1), "Le deuxieme noeud doit avoir comme nom Albert");
        assertEquals(2, graphe.listeNoeuds().size(), "La taille de la liste de noeud doit etre 2");
        assertEquals(10, graphe.getEnsNoeuds().get(0).getAdj().get(0).getCout(), "Le cout de l'arc doit etre 10");
        assertEquals("Albert", graphe.getEnsNoeuds().get(0).getAdj().get(0).getDest(), "La destination de l'arc doit etre le noeud nommé Albert");

    }

    @Test
    public void test02_AjouterArcContientPas(){

        //Preparation des données
        GrapheListe graphe = new GrapheListe();

        // methode a tester
        graphe.ajouterArc("Alain", "Albert", 10);

        // verifications
        assertEquals("Alain", graphe.listeNoeuds().get(0), "Le premier noeud doit avoir comme nom Alain");
        assertEquals("Albert", graphe.listeNoeuds().get(1), "Le deuxieme noeud doit avoir comme nom Albert");
        assertEquals(2, graphe.listeNoeuds().size(), "La taille de la liste de noeud doit etre 2");
        assertEquals(10, graphe.getEnsNoeuds().get(0).getAdj().get(0).getCout(), "Le cout de l'arc doit etre 10");
        assertEquals("Albert", graphe.getEnsNoeuds().get(0).getAdj().get(0).getDest(), "La destination de l'arc doit etre le noeud nommé Albert");

    }
    
    @Test
    public void test03_listeNoeudsOK(){
        // Preparation des donnees
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        
        // Methode testee
        graphe.listeNoeuds();

        // Verifications
        assertEquals("A", graphe.listeNoeuds().get(0), "Le premier noeud doit etre A");
        assertEquals("B", graphe.listeNoeuds().get(1), "Le premier noeud doit etre B");
        assertEquals("D", graphe.listeNoeuds().get(2), "Le premier noeud doit etre D");
    }

    @Test
    public void test04_toStringOK(){
        // Preparation des donnees
        GrapheListe graphe = new GrapheListe();

        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("E", "D", 43);

        // Methode testee
        graphe.toString();

        // Verifications
        assertEquals("A -> B(12) D(87)\n" +
                "B -> E(11)\n" +
                "D -> B(23) C(10)\n" +
                "E -> D(43)\n" +
                "C -> A(19)", graphe.toString(), "erreur");
    }

    @Test
    public void test05_toGraphVizOK(){
        // Preparation des donnees
        GrapheListe graphe = new GrapheListe();

        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("E", "D", 43);

        // Methode testee
        graphe.toGraphviz();

        // Verification
        assertEquals("digraph G {\n" +
                "A -> B [label = 12]\n" +
                "A -> D [label = 87]\n" +
                "B -> E [label = 11]\n" +
                "D -> B [label = 23]\n" +
                "D -> C [label = 10]\n" +
                "E -> D [label = 43]\n" +
                "C -> A [label = 19]\n" +
                "}", graphe.toGraphviz(), "erreur");
    }

    @Test
    public void test06_SuivantsOK(){

        //Préparation des données
        GrapheListe graphe = new GrapheListe();

        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("E", "D", 43);

        Noeud n = graphe.getEnsNoeuds().get(0);

        //Verification
        assertEquals(n.getAdj(), graphe.suivants("A"), "La méthode doit retourner la liste des Arcs partant d'un noeud");
    }

}
