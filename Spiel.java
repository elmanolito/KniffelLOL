import java.util.ArrayList;
import java.util.Scanner;

public class Spiel {
    public Spiel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===============================");
        System.out.println("KKK - Krass Kuhles Kniffel v1.0");
        System.out.println("===============================");
        System.out.println();
        System.out.println("Wie viele Ottos wollen denn spielen?");
        int spielerAnzahl = sc.nextInt();
        ArrayList<Spielblatt> spielblaetter = new ArrayList<>();

        for (int i = 0; i < spielerAnzahl; i++) {
            spielblaetter.add(new Spielblatt());
            spielblaetter.get(i).blattInitialisieren();
        }

        for (int i = 0; i < 13; i++) {
            System.out.println("Runde: " + (i + 1));
            for (int j = 0; j < spielerAnzahl; j++) {
                System.out.println("Spieler: " + spielblaetter.get(j).getSpielername());
                ArrayList<Integer> tempWurf = Spielmethoden.wuerfeln();
                spielblaetter.get(j).blattAnzeigen(spielblaetter.get(j));
                System.out.println("In welches Feld soll der Wurf eingetragen werden?");
                spielblaetter.get(j).inBlattSpeichern(tempWurf);
                spielblaetter.get(j).blattAnzeigen(spielblaetter.get(j));
            }
        }
        Spielmethoden.spielergebnisAnzeigen(spielblaetter);
    }
}
