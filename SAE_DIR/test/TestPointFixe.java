import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test ;

public class TestPointFixe {


    @Test
    public void test01_pointFixeOK() {
        // Préparation des données
        GrapheListe graphe = new GrapheListe("Graphes/GrapheTest.txt");

        // Méthode testée
        Valeur resultat = BellmanFord.resoudre(graphe, "1");

        // Vérifications
        String expected = "1 ->  V:0.0 p:null\n" +
                "2 ->  V:4.0 p:1\n" +
                "3 ->  V:2.0 p:1\n" +
                "4 ->  V:5.0 p:2\n";
        assertEquals(expected, resultat.toString(), "erreur");
    }
}
