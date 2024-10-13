import java.util.Random;
import java.util.Scanner;

public class DiceGame2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.ENGLISH);

        System.out.println("Velkommen til vores 1v1 terningespil!");
        System.out.println("Der skal vaere 2 spillere der skiftes med at kaste 2 terninger.");
        System.out.println("Vinderen er spilleren som foerst samlet set runder 40 point.");
        System.out.println("Tryk Enter for at starte spillet! (Indtast 'test' for at se slag henover 1000 kast)");

        String userInput = scanner.nextLine(); 
        // Hvis brugeren skriver 'test' i scanneren starter testen af 1000 slag. 
        // Hvis brugeren skriver hvad som helst andet eller trykker 'Enter' starter spillet.
        if (userInput.equalsIgnoreCase("test")) {
            System.out.println("Laver test af terningerne");
            testDice();  // starter testDice sektionen, den nederste
        } else {
            System.out.println("Starter spillet");
            diceGame(scanner);  // Starter vores 1v1 terningespil
        }

        scanner.close();
    }

    // DETTE ER VORES TERNINGE SPIL. 1v1 spillet:
    private static void diceGame(Scanner scanner) {
        Random r = new Random();

        int spiller1Points = 0;  // Her holdes styr på spiller 1's point
        int spiller2Points = 0;  // og her er spiller 2's point
        int turn = 1;      //'turn' bruger vi til at ændre turen på hvem der kaster terningerne, og hvem der får point. 
                           //Flere detaljer om dette på linje 52 ved 'switch' sektionen.

        //så længe ingen har rundet 40 point køres dette while loop.
        while (spiller1Points < 40 && spiller2Points < 40) {
            // '\n' bruges bare til at lave et mellemrum i teksten når man kører programmet.:)
            System.out.println("\nSpiller " + turn + ": Tryk Enter for at kaste med terningerne");
            scanner.nextLine();
            
            // Her genereres random tal for result1 og result2. Det er basically Terning1 og Terning2.
            // 6 giver et tilfældigt resultat mellem 0 og 5, så derfor lægger vi 1 til result ( + 1), så det er mellem 1 og 6 istedet for at matche en terning.
            int result1 = r.nextInt(6) + 1;  // Første terning
            int result2 = r.nextInt(6) + 1;  // Anden terning
            int sum = result1 + result2; // Summen af de 2 terninge slag
            
            System.out.println("Du slog " + result1 + " og " + result2 + " = " + sum);

            // Tjek hvilken spiller der skal have point ved brug af switch, og hvis tur den ændre til ved næste loop. 
            // Der er en 'turn case 1' og 'turn case 2'. Dvs, der bliver skiftet rundt mellem de 2 cases ved hver kast.
            // spiller 1 er tildelt turn 1, og spiller 2 er tildelt turn 2. point og turerne bliver skiftet(switch) rundt på efter hvert loop, indtil en når 40+ point.
            switch (turn) {
                case 1: // Hvis det er spiller 1s tur
                    spiller1Points += sum;  
                    // '+=' betyder at en value lægges til en variabel. 
                    // Dvs i det her tilfælde lægges summen af de 2 terninge slag til variablen 'spiller1Points'.

                    System.out.println("Spiller 1s samlede point er nu: " + spiller1Points);
                    turn = 2;  // Skift til spiller 2
                    break;

                case 2: // Hvis det er spiller 2s tur
                    spiller2Points += sum;  // Ligesom beskrevet ovenfor, er '+=' metoden der bruges til at lægge summen(value) til spiller variablen.
                    System.out.println("Spiller 2s samlede point er nu: " + spiller2Points);
                    turn = 1;  // Skift til spiller 1
                    break; // Afslut case
            }
        }

        // Der er en vinder hvis en af spillerne rammer 40 eller over i point.
        if (spiller1Points >= 40) {
            System.out.println("\nSpiller 1 vinder med " + spiller1Points + " point!");
        } else {
            System.out.println("\nSpiller 2 vinder med " + spiller2Points + " point!");
        }
    }

    // DETTE ER TERNINGE TESTEN HENOVER 1000 KAST
    private static void testDice() {
        Random r = new Random();

        //Fortæller bare den skal generere tal mellem 1 og 6 to gange, 1000 gange.
        for (int i = 0; i < 1000; i++) {
            int result1 = r.nextInt(6) + 1;  // første nummer mellem 1 og 6
            int result2 = r.nextInt(6) + 1;  // andet nummer mellem 1 og 6
            int sum = result1 + result2;

            // resultatet af hvert kast
            System.out.println("Du slog " + result1 + " og " + result2 + " = " + sum);
        }
    }
}
