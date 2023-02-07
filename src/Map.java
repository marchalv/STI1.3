import java.util.ArrayList;
import java.util.Collections;


public class Map {
    private ArrayList<Ville> listVilles;

    public Map() {
        listVilles = new ArrayList<>();
    }

    public void init6Citys() {
        listVilles.clear();
        listVilles.add(new Ville("A", 1, 1));
        listVilles.add(new Ville("B", 2, 2));
        listVilles.add(new Ville("C", 1, 3));
        listVilles.add(new Ville("D", 3, 2));
        listVilles.add(new Ville("E", 3, 5));
        listVilles.add(new Ville("F", 7, 2));
    }

    public void init10Citys() {
        listVilles.clear();
        listVilles.add(new Ville("A", 1, 1));
        listVilles.add(new Ville("B", 2, 2));
        listVilles.add(new Ville("C", 1, 3));
        listVilles.add(new Ville("D", 3, 2));
        listVilles.add(new Ville("E", 3, 5));
        listVilles.add(new Ville("F", 7, 2));
        listVilles.add(new Ville("G", 7, 5));
        listVilles.add(new Ville("H", 8, 4));
        listVilles.add(new Ville("I", 10, 1));
        listVilles.add(new Ville("J", 11, 5));
    }

    public void init15Citys() {
        listVilles.clear();
        listVilles.add(new Ville("A", 1, 1));
        listVilles.add(new Ville("B", 2, 2));
        listVilles.add(new Ville("C", 1, 3));
        listVilles.add(new Ville("D", 3, 2));
        listVilles.add(new Ville("E", 3, 5));
        listVilles.add(new Ville("F", 7, 2));
        listVilles.add(new Ville("G", 7, 5));
        listVilles.add(new Ville("H", 8, 4));
        listVilles.add(new Ville("I", 10, 1));
        listVilles.add(new Ville("J", 11, 5));
        listVilles.add(new Ville("K", 1, 14));
        listVilles.add(new Ville("L", 2, 8));
        listVilles.add(new Ville("M", 5, 1));
        listVilles.add(new Ville("N", 6, 0));
        listVilles.add(new Ville("O", 6, 6));
    }

    public void init30Citys() {
        listVilles.clear();
        listVilles.add(new Ville("A", 1, 1));
        listVilles.add(new Ville("B", 2, 2));
        listVilles.add(new Ville("C", 1, 3));
        listVilles.add(new Ville("D", 3, 2));
        listVilles.add(new Ville("E", 3, 5));
        listVilles.add(new Ville("F", 7, 2));
        listVilles.add(new Ville("G", 7, 5));
        listVilles.add(new Ville("H", 8, 4));
        listVilles.add(new Ville("I", 10, 1));
        listVilles.add(new Ville("J", 11, 5));
        listVilles.add(new Ville("K", 1, 14));
        listVilles.add(new Ville("L", 2, 8));
        listVilles.add(new Ville("M", 5, 1));
        listVilles.add(new Ville("N", 6, 0));
        listVilles.add(new Ville("O", 6, 6));
        listVilles.add(new Ville("P", 7, 12));
        listVilles.add(new Ville("Q", 8, 14));
        listVilles.add(new Ville("R", 9, 1));
        listVilles.add(new Ville("S", 9, 4));
        listVilles.add(new Ville("T", 9, 14));
        listVilles.add(new Ville("U", 10, 5));
        listVilles.add(new Ville("V", 11, 1));
        listVilles.add(new Ville("W", 11, 12));
        listVilles.add(new Ville("X", 13, 12));
        listVilles.add(new Ville("Y", 14, 5));
        listVilles.add(new Ville("Z", 16, 3));
        listVilles.add(new Ville("AA", 21, 12));
        listVilles.add(new Ville("AB", 28, 13));
        listVilles.add(new Ville("AC", 30, 1));
        listVilles.add(new Ville("AD", 30, 17));
    }

    //Algo recursif qui liste toutes les possibilités
    public void algoRecursif() {
        double t1 = System.currentTimeMillis();
        System.out.println("------------- ALGO RECURSIF --------------");
        ArrayList<Ville> listVillesTemp = new ArrayList<>();
        //generate permutations
        listVillesTemp.addAll(listVilles);
        ArrayList<ArrayList<Ville>> listPermutations = new ArrayList<>();
        listPermutations = generatePermutations(listVillesTemp);
        //get the best path
        ArrayList<Ville> listVillesFinal = new ArrayList<>();
        double distanceMin = 0;
        for (ArrayList<Ville> listVilles : listPermutations) {
            listVilles.add(new Ville("O", 0, 0));
            Collections.reverse(listVilles);
            listVilles.add(new Ville("O", 0, 0));
            double distance = pathCost(listVilles);
            if (distanceMin == 0 || distance < distanceMin) {
                distanceMin = distance;
                listVillesFinal = listVilles;
            }
        }
        //print result
        //System.out.println(listPermutations.size() + " permutations générées");
        System.out.println("Distance : " + distanceMin);
        listVilles = listVillesFinal;
        System.out.println("Villes : " + toString());
        double t2 = System.currentTimeMillis();
        System.out.println("Temps : " + (t2 - t1) + " ms");
    }



    //Algo glouton ville la plus proche
    public void algoGloutonV1() {
        double t1 = System.currentTimeMillis();
        System.out.println("------------- ALGO GLOUTON -> LE + PROCHE --------------");
        ArrayList<Ville> listVillesTemp = new ArrayList<>();
        listVilles.add(new Ville("O", 0, 0));
        Collections.reverse(listVilles);
        listVillesTemp.addAll(listVilles);
        Ville villeDepart = listVillesTemp.get(0);
        listVillesTemp.remove(0);
        Ville villeActuelle = villeDepart;
        ArrayList<Ville> listVillesFinal = new ArrayList<>();
        listVillesFinal.add(villeDepart);
        while (listVillesTemp.size() > 0) {
            Ville villePlusProche = listVillesTemp.get(0);
            for (Ville ville : listVillesTemp) {
                if (villeActuelle.getDistance(ville) < villeActuelle.getDistance(villePlusProche)) {
                    villePlusProche = ville;
                }
            }
            listVillesFinal.add(villePlusProche);
            listVillesTemp.remove(villePlusProche);
            villeActuelle = villePlusProche;
        }
        listVillesFinal.add(villeDepart);
        listVilles = listVillesFinal;
        System.out.println("Path: " + toString());
        System.out.println("Path cost: " + pathCost(listVillesFinal));
        System.out.println("Temps: " + (System.currentTimeMillis() - t1) + " ms");
    }

    //Algo glouton ville la plus en bas à gauche
    public void algoGloutonV2() {
        double t1 = System.currentTimeMillis();
        System.out.println("------------- ALGO GLOUTON -> LE + HAUT A GAUCHE --------------");
        ArrayList<Ville> listVillesTemp = new ArrayList<>();
        listVilles.add(new Ville("O", 0, 0));
        Collections.reverse(listVilles);
        listVillesTemp.addAll(listVilles);
        Ville villeDepart = listVillesTemp.get(0);
        listVillesTemp.remove(0);
        Ville villeActuelle = villeDepart;
        ArrayList<Ville> listVillesFinal = new ArrayList<>();
        listVillesFinal.add(villeDepart);
        while (listVillesTemp.size() > 0) {
            Ville villePlusProche = listVillesTemp.get(0);
            for (Ville ville : listVillesTemp) {
                if (ville.getY() < villePlusProche.getY()) {
                    villePlusProche = ville;
                } else if (ville.getY() == villePlusProche.getY()) {
                    if (ville.getX() < villePlusProche.getX()) {
                        villePlusProche = ville;
                    }
                }
            }
            listVillesFinal.add(villePlusProche);
            listVillesTemp.remove(villePlusProche);
            villeActuelle = villePlusProche;
        }
        listVillesFinal.add(villeDepart);
        listVilles = listVillesFinal;
        System.out.println("Path: " + toString());
        System.out.println("Path cost: " + pathCost(listVillesFinal));
        System.out.println("Temps: " + (System.currentTimeMillis() - t1) + " ms");
    }


    public void algoDescenteV1() {
        double t1 = System.currentTimeMillis();
        System.out.println("------------- ALGO DESCENTE 1 --------------");
        //Collections.shuffle(listVilles);
        listVilles.add(new Ville("O", 0, 0));
        Collections.reverse(listVilles);
        listVilles.add(new Ville("O", 0, 0));
        boolean isBetter = true;
        while (isBetter) {
            double pathCost = pathCost(listVilles);
            double newPathCost = 0;
            double tempPathCost = pathCost(listVilles);
            int bestPermI = 0;
            int bestPermJ = 0;
            String s = "";
            for (int i = 1; i < listVilles.size()-1; i++) {
                for (int j = 1; j < listVilles.size()-1; j++) {
                    if ( simulatePermutation(i, j) < tempPathCost) {
                        tempPathCost = simulatePermutation(i, j);
                        bestPermI = i;
                        bestPermJ = j;
                    }
                }
            }
            if (tempPathCost < pathCost) {
                permute(bestPermI, bestPermJ, listVilles);
                pathCost = pathCost(listVilles);
            } else {
                isBetter = false;
            }
        }
        System.out.println(toString() + " : " + pathCost(listVilles));
        double t2 = System.currentTimeMillis();
        System.out.println("Time: " + (t2 - t1) / 1000 + "s");
    }

    public void algoDescenteV2() {
        double t1 = System.currentTimeMillis();
        System.out.println("------------- ALGO DESCENTE 2 --------------");
        //Collections.shuffle(listVilles);
        listVilles.add(new Ville("O", 0, 0));
        Collections.reverse(listVilles);
        listVilles.add(new Ville("O", 0, 0));
        boolean isBetter = true;
        while (isBetter) {
            double pathCost = pathCost(listVilles);
            int bestPermI = 0;
            int bestPermJ = 0;
            String s = "";
            for (int i = 1; i < listVilles.size()-1; i++) {
                for (int j = 1; j < listVilles.size()-1; j++) {
                    if ( simulatePermutation(i, j) < pathCost) {
                        permute(i, j, listVilles);
                    }
                }
            }
            if (pathCost(listVilles) == pathCost) {
                isBetter = false;
            }
        }
        System.out.println(toString() + " : " + pathCost(listVilles));
        double t2 = System.currentTimeMillis();
        System.out.println("Time: " + (t2 - t1) / 1000 + "s");
    }

    public void algoGenetique() {
        //generation population
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < listVilles.size(); i++) {
            s = s + listVilles.get(i).getNom();
        }
        return s;
    }

    private double pathCost(ArrayList<Ville> al) {
        double cost = 0;
        for (int i = 0; i < al.size()-1; i++) {
            cost = cost + al.get(i).getDistance(al.get(i+1));
        }
        return cost;
    }

    private void permute(int a, int b, ArrayList<Ville> al) {
        Ville temp = al.get(a);
        al.set(a, al.get(b));
        al.set(b, temp);
    }

    private double simulatePermutation(int a, int b) {
        ArrayList<Ville> listVillesTemp = (ArrayList<Ville>) listVilles.clone();
        permute(a, b, listVillesTemp);
        return pathCost(listVillesTemp);
    }

    // metohde recursive pour générer toutes les permutations
    private ArrayList<ArrayList<Ville>> generatePermutations(ArrayList<Ville> listVillesTemp) {
        ArrayList<ArrayList<Ville>> listPermutations = new ArrayList<>();
        if (listVillesTemp.size() == 1) {
            listPermutations.add(listVillesTemp);
            return listPermutations;
        }
        for (int i = 0; i < listVillesTemp.size(); i++) {
            ArrayList<Ville> listVillesTemp2 = (ArrayList<Ville>) listVillesTemp.clone();
            listVillesTemp2.remove(i);
            ArrayList<ArrayList<Ville>> listPermutationsTemp = generatePermutations(listVillesTemp2);
            for (ArrayList<Ville> listVillesTemp3 : listPermutationsTemp) {
                listVillesTemp3.add(0, listVillesTemp.get(i));
                listPermutations.add(listVillesTemp3);
            }
        }
        return listPermutations;
    }
}
