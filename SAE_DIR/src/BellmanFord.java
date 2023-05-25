public class BellmanFord {

    /**
     * Méthode qui permet de resoudre le probleme du plus court chemin a partir d'un noeud de depart en utilisant l'algorithme de Bellman-Ford
     * @param g
     * @param depart
     * @return
     */
    public static Valeur resoudre(Graphe g, String depart) {
        //variable qui va contenir les valeurs des noeuds qui serra retournee
        Valeur valeur = new Valeur();

        //initialisation des valeurs
        for (String noeud : g.listeNoeuds()) {
            if (noeud.equals(depart)) {
                valeur.setValeur(noeud, 0.0);
            } else {
                valeur.setValeur(noeud, Double.MAX_VALUE);
            }
        }

        System.out.println("Etat initial : ");
        System.out.println(valeur);

        int nbIterations = 0;

        //boucle qui va permettre de trouver les valeurs des noeuds
        boolean pointFixe = false;
        while (!pointFixe) {
            nbIterations++;
            pointFixe = true;
            for (String noeud : g.listeNoeuds()) {
                for (Arc voisin : g.suivants(noeud)) {

                    double distance = valeur.getValeur(noeud) + voisin.getCout();

                    if (distance < valeur.getValeur(voisin.getDest())) {

                        valeur.setValeur(voisin.getDest(), distance);
                        valeur.setParent(voisin.getDest(), noeud);
                        pointFixe = false;
                    }
                }
            }

            System.out.println("Itération : " + nbIterations);
            System.out.println(valeur);
        }

        return valeur;
    }

}
