import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Spielblatt {
    // I
    String spielername = "";
    HashMap<Integer, ArrayList<Integer>> wurfInFeld = new HashMap<>();
    ArrayList<Integer> punkteInFeld = new ArrayList<>();
    int punkteGesamt = 0;

    // II
    public String getSpielername() {
        return this.spielername;
    }
    public void setSpielername(String spielername) {
        this.spielername = spielername;
    }
    public HashMap<Integer, ArrayList<Integer>> getWurfInFeld() {
        return wurfInFeld;
    }
    public void setWurfInFeld(HashMap<Integer, ArrayList<Integer>> wurfInFeld) {
        this.wurfInFeld = wurfInFeld;
    }
    public ArrayList<Integer> getPunkteInFeld() {
        return punkteInFeld;
    }
    public void setPunkteInFeld(int index, Integer punkte) {
        this.punkteInFeld.set(index, punkte);
    }
    public int getPunkteGesamt() {
        return this.punkteGesamt;
    }
    public void setPunkteGesamt(int punkteGesamt) {
        this.punkteGesamt = punkteGesamt;
    }

    // III
    public Spielblatt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte Spielernamen eingeben:");
        this.setSpielername(sc.nextLine());
    }

    // V
    public void blattInitialisieren() {
        for (int i = 0; i < 13; i++) {
            punkteInFeld.add(0);
            wurfInFeld.put(i, new ArrayList<Integer>());
        }
    }

    public void blattAnzeigen(Spielblatt spielblatt) {
        System.out.println("1.  Nur Einsen zählen              - Punkte: " + spielblatt.getPunkteInFeld().get(0) + " Wurf: " + spielblatt.getWurfInFeld().get(0));
        System.out.println("2.  Nur Zweien zählen              - Punkte: " + spielblatt.getPunkteInFeld().get(1) + " Wurf: " + spielblatt.getWurfInFeld().get(1));
        System.out.println("3.  Nur Dreien zählen              - Punkte: " + spielblatt.getPunkteInFeld().get(2) + " Wurf: " + spielblatt.getWurfInFeld().get(2));
        System.out.println("4.  Nur Vieren zählen              - Punkte: " + spielblatt.getPunkteInFeld().get(3) + " Wurf: " + spielblatt.getWurfInFeld().get(3));
        System.out.println("5.  Nur Fünfen zählen              - Punkte: " + spielblatt.getPunkteInFeld().get(4) + " Wurf: " + spielblatt.getWurfInFeld().get(4));
        System.out.println("6.  Nur Sechsen zählen             - Punkte: " + spielblatt.getPunkteInFeld().get(5) + " Wurf: " + spielblatt.getWurfInFeld().get(5));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Punkte oberer Teil: " + Spielmethoden.punkteTeil("oben", punkteInFeld));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("7.  Dreierpasch, alle Augen zählen - Punkte: " + spielblatt.getPunkteInFeld().get(6) + " Wurf: " + spielblatt.getWurfInFeld().get(6));
        System.out.println("8.  Viererpasch alle Augen zählen  - Punkte: " + spielblatt.getPunkteInFeld().get(7) + " Wurf: " + spielblatt.getWurfInFeld().get(7));
        System.out.println("9.  Full House                     - Punkte: " + spielblatt.getPunkteInFeld().get(8) + " Wurf: " + spielblatt.getWurfInFeld().get(8));
        System.out.println("10. Kleine Straße                  - Punkte: " + spielblatt.getPunkteInFeld().get(9) + " Wurf: " + spielblatt.getWurfInFeld().get(9));
        System.out.println("11. Große Straße                   - Punkte: " + spielblatt.getPunkteInFeld().get(10) + " Wurf: " + spielblatt.getWurfInFeld().get(10));
        System.out.println("12. Kniffel                        - Punkte: " + spielblatt.getPunkteInFeld().get(11) + " Wurf: " + spielblatt.getWurfInFeld().get(11));
        System.out.println("13. Chance                         - Punkte: " + spielblatt.getPunkteInFeld().get(12) + " Wurf: " + spielblatt.getWurfInFeld().get(12));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Punkte unterer Teil: " + Spielmethoden.punkteTeil("unten", punkteInFeld));
        System.out.println("----------------------------------------------------------------------------------------");
        setPunkteGesamt(Spielmethoden.punkteGesamt(Spielmethoden.punkteTeil("oben", punkteInFeld),Spielmethoden.punkteTeil("unten", punkteInFeld)));
        System.out.println("Punkte gesamt: "+getPunkteGesamt());
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println();
    }

    public void inBlattSpeichern(ArrayList<Integer> wurf){

        System.out.println("Bitte die Zahl des Feldes eingeben:");
        System.out.println("(Wenn nirgendwo eingetragen werden kann bitte '0' eingeben)");
        Scanner sc = new Scanner(System.in);
        int auswahlFeld = sc.nextInt();
        wurfInFeld.replace(auswahlFeld-1, wurf);
        switch(auswahlFeld){
            case 0: // Wenn nirgendwo eingetragen werden kann
                System.out.println("Bitte das zu streichende Feld wählen:");
                int feldLeeren = sc.nextInt();
                ArrayList<Integer> leerFeld = new ArrayList<>();
                setPunkteInFeld(feldLeeren-1, 0);
                wurfInFeld.replace(feldLeeren-1, Spielmethoden.leerWurf());
                break;
            case 1: case 2: case 3: case 4: case 5: case 6: // nur Einser ... Sechsen zählen
                setPunkteInFeld(auswahlFeld-1, Spielmethoden.punkteBestimmteAugen(auswahlFeld, wurf));
                break;
            case 7:  // Dreierpasch
                if (Spielmethoden.combiChecker(wurfInFeld.get(auswahlFeld - 1)).equals("dreierpasch")) {
                    setPunkteInFeld(auswahlFeld-1, Spielmethoden.punkteAlleAugen(wurf));
                }
                break;
            case 8: // Viererpasch
                if (Spielmethoden.combiChecker(wurfInFeld.get(auswahlFeld - 1)).equals("viererpasch")) {
                    setPunkteInFeld(auswahlFeld-1, Spielmethoden.punkteAlleAugen(wurf));
                }
                break;
            case 9: // Full House
                if (Spielmethoden.combiChecker(wurfInFeld.get(auswahlFeld - 1)).equals("fullhouse")) {
                    setPunkteInFeld(auswahlFeld-1, 25);
                }
                break;
            case 10: // Kleine Straße
                if (Spielmethoden.combiChecker(wurfInFeld.get(auswahlFeld - 1)).equals("kleinestrasse")) {
                    setPunkteInFeld(auswahlFeld-1, 30);
                }
                break;
            case 11: // Große Straße
                if (Spielmethoden.combiChecker(wurfInFeld.get(auswahlFeld - 1)).equals("grossestrasse")) {
                    setPunkteInFeld(auswahlFeld-1, 40);
                }
                break;
            case 12: // Kniffel
                if (Spielmethoden.combiChecker(wurfInFeld.get(auswahlFeld - 1)).equals("kniffel")) {
                    setPunkteInFeld(auswahlFeld-1, 50);
                }
                break;
            case 13: // Chance
                setPunkteInFeld(auswahlFeld-1, Spielmethoden.punkteAlleAugen(wurf));
                break;
        }
    }
}