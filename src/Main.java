// C:/Users/Orcag/IdeaProjects/projectTest/src/Main.java

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Lade die Benutzer zentral über die UserManagment-Klasse.
        UserManagment.loadUsers();

        // 2. Hole die geladene Liste für die weitere Logik.
        List<UserManagment.User> currentUsers = UserManagment.getUserList();

        // 3. Prüfe, ob Benutzer vorhanden sind und reagiere entsprechend.
        if (currentUsers.isEmpty()) {
            System.out.println("\n--- Keinen Benutzer gefunden ---");
            UserManagment.addUser();
            // Nach dem Hinzufügen die Auswahl erneut starten, um den neuen User zu wählen.
            userSelect(UserManagment.getUserList());
        } else {
            userSelect(currentUsers);
        }
    }

    public static void userSelect(List<UserManagment.User> userList) throws IOException {
        // Sicherheitscheck, falls die Methode mit einer leeren Liste aufgerufen wird.
        if (userList.isEmpty()) {
            System.out.println("Es sind keine Benutzer zum Auswählen vorhanden.");
            // Hier könnte man direkt wieder addUser() aufrufen.
            UserManagment.addUser();
            userSelect(UserManagment.getUserList()); // Erneuter Versuch nach dem Hinzufügen
            return;
        }

        System.out.println("\n--- Willkommen zurück! Folgende Benutzer sind verfügbar: ---");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println((i + 1) + ".: " + userList.get(i).name);
        }
        System.out.println("\nGib die Nummer des Benutzers ein oder schreibe 'New game', um einen neuen anzulegen.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("New game")) {
                UserManagment.addUser();
                // Nach dem Hinzufügen die Auswahl mit der aktualisierten Liste neu starten.
                userSelect(UserManagment.getUserList());
                break; // Die aktuelle Schleife beenden.
            }

            try {
                // Variablen in Java beginnen konventionell mit einem Kleinbuchstaben (camelCase).
                int userIndex = Integer.parseInt(input) - 1;

                if (userIndex >= 0 && userIndex < userList.size()) {
                    // Die eigentliche Spiellogik mit dem ausgewählten Index aufrufen.
                    MainGameLogic.GameLogic(userIndex);
                    break; // Auswahl erfolgreich, Schleife verlassen.
                } else {
                    System.out.println("⚠️ Ungültige Nummer. Bitte wähle eine Nummer aus der Liste.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ungültige Eingabe. Bitte gib eine Nummer oder 'New game' ein.");
            }
        }
    }
}