/*
Entrees :
G un graphe oriente avec une ponderation (poids) positive des arcs
A un sommet (depart) de G
Debut
Q <- {} // utilisation d’une liste de noeuds a traiter
Pour chaque sommet v de G faire
v.distance <- Infini
v.parent <- Indefini
Q <- Q U {v} // ajouter le sommet v a la liste Q
Fin Pour
A.distance <- 0
Tant que Q est un ensemble non vide faire
u <- un sommet de Q telle que u.distance est minimale
Q <- Q \ {u} // enlever le sommet u de la liste Q
Pour chaque sommet v de Q tel que l’arc (u,v) existe faire
D <- u.distance + poids(u,v)
Si D < v.distance
Alors v.distance <- D
v.parent <- u
Fin Si
Fin Pour
Fin Tant que
Fin
 */

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
    public static Valeur resoudre(Graphe g, String depart){

        List<Noeud> Q = new ArrayList<Noeud>();
        Valeur valeur = new Valeur();

        for (String v : g.listeNoeuds()) {

            valeur.setValeur(v, Double.MAX_VALUE);
            valeur.setParent(v, null);
            Q.add(new Noeud(v));
        }

        System.out.println(valeur);

        valeur.setValeur(depart, 0.0);

        while (!Q.isEmpty()){

            Noeud u = Q.get(0);
            for (Noeud n : Q) {
                if (valeur.getValeur(n.getNom()) < valeur.getValeur(u.getNom())) {
                    u = n;
                }
            }
            Q.remove(u);

            for (Arc a : g.suivants(u.getNom())) {

                double distance = valeur.getValeur(u.getNom()) + a.getCout();

                if (distance < valeur.getValeur(a.getDest())) {

                    valeur.setValeur(a.getDest(), distance);
                    valeur.setParent(a.getDest(), u.getNom());
                }
            }

            System.out.println(valeur);
        }

        return valeur;
    }
}
