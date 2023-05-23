/**
 * Classe représentant un arc partant d’un noeud
 */
public class Arc {

    /**
     * représente le nom du nœud destination de l’arc.
     */
    private String dest;

    /**
     * correspond au coût (ou poids) de l’arc.
     */
    private double cout;

    /**
     * Constructeur de la classe Arc.
     * @param destination paramètres servant à initialiser l'attribut dest
     * @param poids paramètres servant à initialiser l'attribut cout
     */
    public Arc(String destination, double poids) {

        this.dest = destination;

        if (poids > 0)
            this.cout = poids;
        else
            this.cout = 0;
    }

    /**
     * Méthode getDest permettant de recuperer le nom du noeud destination de l'arc
     * @return
     */
    public String getDest() {
        return dest;
    }

    /**
     * Méthode getCout permettant de recuperer le cout de l'arc
     * @return
     */
    public double getCout() {
        return cout;
    }
}
