import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test ;

import java.util.List;

public class TestDijkstra {

    @Test
    public void test01_resoudreOK(){
        // Initialisation des donnees
        GrapheListe graphe = new GrapheListe("Graphes/Graphe21.txt");
        String depart = "1";
        String attendu = "1 ->  V:0.0 p:null\n" +
                "10 ->  V:7.0 p:18\n" +
                "11 ->  V:14.0 p:10\n" +
                "12 ->  V:14.0 p:4\n" +
                "13 ->  V:21.0 p:12\n" +
                "14 ->  V:11.0 p:2\n" +
                "15 ->  V:9.0 p:19\n" +
                "16 ->  V:13.0 p:10\n" +
                "17 ->  V:10.0 p:9\n" +
                "18 ->  V:4.0 p:1\n" +
                "19 ->  V:8.0 p:18\n" +
                "2 ->  V:10.0 p:20\n" +
                "20 ->  V:9.0 p:18\n" +
                "3 ->  V:10.0 p:1\n" +
                "4 ->  V:6.0 p:18\n" +
                "5 ->  V:8.0 p:4\n" +
                "6 ->  V:9.0 p:10\n" +
                "7 ->  V:11.0 p:3\n" +
                "8 ->  V:11.0 p:3\n" +
                "9 ->  V:7.0 p:1\n";

        // Methode testee
        Valeur v = Dijkstra.resoudre(graphe, depart);

        // Verifications
        assertEquals(attendu, v.toString(), "erreur");
    }

    @Test
    public void test02_Dijkstra_avec_calculerCheminOK(){
        // Initialisation des donnees
        Graphe graphe = new GrapheListe("Graphes/Graphe1.txt");
        Valeur v = Dijkstra.resoudre(graphe, "1");
        List<String> chemin = v.calculerChemin("10");

        String obtenu = "Chemin le plus court de 1 à 10 : " + chemin;

        // Methode testee
        Dijkstra.resoudre(graphe, "1");

        // Verifications
        assertEquals("Chemin le plus court de 1 à 10 : [1, 2, 5, 10]", obtenu, "erreur");
    }

}
