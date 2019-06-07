import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Spielmethoden {



    public static ArrayList<Integer> wuerfeln(){
        int wurfNr = 1;
        Random rand = new Random();
        ArrayList<Integer> wurfAL = new ArrayList<>();
        ArrayList<Integer> wurfZwischenAL = new ArrayList<>();
        for (int i=0;i<5;i++){
            wurfAL.add(rand.nextInt(6)+1);
        }
        while(wurfNr <= 3){
            Scanner sc = new Scanner(System.in);
            System.out.println("Wurf: "+wurfNr);
            System.out.println("---------------");
            System.out.println(wurfAL);
            System.out.println("---------------");
            if(wurfNr == 3){
                break;
            }
            System.out.println("nochmal? (j/n)");
            String auswahl = sc.nextLine();
            if(auswahl.equals("j")){
                System.out.println("Welche Würfel willste behalten? (1-5) --- Einzeln eingeben und mit Enter bestätigen. Mit 0 und Enter kann die Auswahl beendet werden.");
                int wurfZaehler = 0;
                for(int i=0;i<5;i++){
                    int wuerfelNeu = sc.nextInt();
                    if(wuerfelNeu == 0){
                        break;
                    }
                    if(wuerfelNeu > 0 && wuerfelNeu-1 < wurfAL.size()){
                        wurfZwischenAL.add(wurfAL.get(wuerfelNeu-1));
                        wurfZaehler++;
                    }else {
                        System.out.println("zwischen 1 und 5 zu Otto...");
                    }
                }
                wurfAL.clear();
                wurfAL.addAll(wurfZwischenAL);
                wurfZwischenAL.clear();
                wurfNr++;
                int frei = 5-wurfAL.size();
                for(int i=0;i<frei;i++){
                    wurfAL.add(rand.nextInt(6)+1);
                }
            }
            else if(auswahl.equals("n")){
                System.out.println("Jut, dann nich...");
                break;
            }
        }
        return wurfAL;
    }

    public static int punkteAlleAugen(ArrayList<Integer> wurf) {
        int punkteAlleAugen = 0;
        for (int i = 0; i < 5; i++) {
            punkteAlleAugen += wurf.get(i);
        }
        return punkteAlleAugen;
    }

    public static int punkteBestimmteAugen(int zahl, ArrayList<Integer> wurf) {
        int punkteBestimmteAugen = 0;
        for (int i = 0; i < 5; i++) {
            if (wurf.get(i) == zahl) {
                punkteBestimmteAugen += zahl;
            }
        }
        return punkteBestimmteAugen;
    }

    public static int punkteTeil(String teil, ArrayList<Integer> punkteInFeld) {
        int punkteTeil = 0;
        if (teil.equals("oben")) {
            for (int i = 0; i < 7; i++) {
                punkteTeil += punkteInFeld.get(i);
            }
            if (punkteTeil >= 63) {
                punkteTeil += 35;
            }
        }
        if (teil.equals("unten")) {
            for (int i = 7; i < 13; i++) {
                punkteTeil += punkteInFeld.get(i);
            }
        }
        return punkteTeil;
    }

    public static int punkteGesamt(int oben, int unten) {
        return oben+unten;
    }

    public static ArrayList<Integer> leerWurf() {
        ArrayList<Integer> leerWurf = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            leerWurf.add(0);
        }
        return leerWurf;
    }

    public static void spielergebnisAnzeigen(ArrayList<Spielblatt> spielblatt) {
        int meistenPunkte = 0;
        String spielerName = "";
        for (Spielblatt sb : spielblatt) {
            if (sb.getPunkteGesamt() > meistenPunkte) {
                meistenPunkte = sb.getPunkteGesamt();
                spielerName = sb.getSpielername();
            }
        }
        System.out.println("Der Gewinner ist "+spielerName+" mit "+meistenPunkte+"!");
    }

    public static String combiChecker(ArrayList<Integer> wurf) {
        String kombination = "";
        int[] anzahlZahlen = {0, 0, 0, 0, 0, 0};
        for (int i : wurf) {
            if (i == 1) {
                anzahlZahlen[0] += 1;
            }
            if (i == 2) {
                anzahlZahlen[1] += 1;
            }
            if (i == 3) {
                anzahlZahlen[2] += 1;
            }
            if (i == 4) {
                anzahlZahlen[3] += 1;
            }
            if (i == 5) {
                anzahlZahlen[4] += 1;
            }
            if (i == 6) {
                anzahlZahlen[5] += 1;
            }
        }

        // Pasch
        boolean dp = false;
        boolean vp = false;
        for (int i : anzahlZahlen) {
            if (i == 3) {
                dp = true;
            }
            if (i == 4) {
                vp = true;
            }
        }
        if (dp == true) {
            kombination = "dreierpasch";
        }
        if (vp == true) {
            kombination = "viererpasch";
        }
        // ENDE Pasch

        // Full House
        boolean fh1 = false;
        boolean fh2 = false;
        for (int i : anzahlZahlen) {
            if (i == 2) {
                fh1 = true;
            }
            if (i == 3) {
                fh2 = true;
            }
        }
        if (fh1 == true && fh2 == true) {
            kombination = "fullhouse";
        }
        // ENDE Full House

        // Kleine Straße
        if (anzahlZahlen[0] == 1 && anzahlZahlen[1] == 1 && anzahlZahlen[2] == 1 && anzahlZahlen[3] == 1 && anzahlZahlen[4] == 1) {
            kombination = "kleinestrasse";
        }
        // ENDE Kleine Straße

        // Große Straße
        if (anzahlZahlen[1] == 1 && anzahlZahlen[2] == 1 && anzahlZahlen[3] == 1 && anzahlZahlen[4] == 1 && anzahlZahlen[5] == 1) {
            kombination = "grossestrasse";
        }
        // ENDE Große Straße

        // Kniffel
        boolean kniffel = false;
        for (int i : anzahlZahlen) {
            if (i == 5) {
                kniffel = true;
            }
        }
        // ENDE Kniffel

        return kombination;
    }
}