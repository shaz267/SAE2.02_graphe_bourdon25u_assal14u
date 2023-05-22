public class BellmanFord {

    public Valeur resoudre(Graphe g, String depart) {
        Valeur valeur = new Valeur();

        // Initialisation des valeurs et des parents
        for (String noeud : g.listeNoeuds()) {
            if (noeud.equals(depart)) {
                valeur.setValeur(noeud, 0.0);
            } else {
                valeur.setValeur(noeud, Double.MAX_VALUE);
            }
            valeur.setParent(noeud, null);
        }

        // Itérations de l'algorithme de Bellman-Ford
        for (int i = 0; i < g.listeNoeuds().size() - 1; i++) {
            for (String noeud : g.listeNoeuds()) {
                for (Arc voisin : g.suivants(noeud)) {

                    double distance = valeur.getValeur(noeud) + voisin.getCout();
                    if (distance < valeur.getValeur(voisin.getDest())) {
                        valeur.setValeur(voisin.getDest(), distance);
                        valeur.setParent(voisin.getDest(), noeud);
                    }
                }
            }
        }

        return valeur;
    }

}
