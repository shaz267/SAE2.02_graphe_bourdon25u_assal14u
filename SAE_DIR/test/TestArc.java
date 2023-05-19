import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test ;
public class TestArc {

    @Test
    public void TestArcOk(){

        //Constructeur à tester
        Arc a = new Arc("Miami", 10);

        // verifications
        assertEquals("Miami", a.getDest(), "La destination doit être Miami");
        assertEquals(10, a.getCout(), "Le cout doit etre 10");
    }

    @Test
    public void TestArcCoutNegatif(){

        //Constructeur à tester
        Arc a = new Arc("Miami", -10);

        // verifications
        assertEquals(0, a.getCout(), "Le cout doit etre 0");
    }


}
