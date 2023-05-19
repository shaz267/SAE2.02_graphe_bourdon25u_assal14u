import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test ;

public class TestNoeud {

    @Test
    public void test01_equalsOK() {
        // Preparation des donnees
        Noeud noeud1 = new Noeud("noeudtest");
        Noeud noeud2 = new Noeud("noeudtest");

        // Methode testee
        noeud1.equals(noeud2);

        // Verification
        assertEquals(true, noeud1.equals(noeud2), "les noeuds ont le meme nom, cela devrait alors retourner true");
    }

    @Test
    public void test02_equalsKO(){
        // Preparation des donnees
        Noeud noeud1 = new Noeud("noeudtest");
        Noeud noeud2 = new Noeud("noeud2test");

        // Methode testee
        noeud1.equals(noeud2);

        // Verification
        assertEquals(false, noeud1.equals(noeud2), "les noeuds n'ont pas le meme nom, cela devrait alors retourner false");
    }

    @Test
    public void test03_ajouterArcOK(){
        // Preparation des donnees
        Noeud noeud = new Noeud("noeud");
        Noeud noeudadj = new Noeud("noeudadj");

        // Methode testee
        noeud.ajouterArc("noeudadj", 10);

        // Verifications
        assertEquals("noeudadj", noeud.getAdj().get(0).getDest(), "le seul noeud adjacent devrait etre noeudadj");
    }

    @Test
    public void test04_getNomOK(){
        // Preparation des donnees
        Noeud noeud = new Noeud("NomNoeud");

        // Methode testee
        noeud.getNom();

        // Verifications
        assertEquals("NomNoeud", noeud.getNom(), "Le nom retourne devrait etre NomNoeud");
    }
}
