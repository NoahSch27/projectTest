import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        File dataFile = new File("userData");
        List<UserManagment.User> userList = new ArrayList<>();

        if (dataFile.exists() && dataFile.length() > 2) {
            try {
                userList = mapper.readValue(dataFile, new TypeReference<List<UserManagment.User>>() {});
                System.out.println("✅ Benutzerdatei gefunden und geladen.");
            } catch (IOException e) {
                System.out.println("⚠️ Fehler beim Lesen der Datei. Starte mit leerer Liste.");
            }
        } else {
            System.out.println("ℹ️ Keine Benutzerdatei gefunden. Starte mit einer leeren Liste.");
        }
        if (userList.isEmpty()) {
            System.out.println("\n--- Keinen Benutzer gefunden ---");
            UserManagment.addUser();

        } else {
            userSelect(userList);
        }
    }
    public static void userSelect(List<UserManagment.User> userList) throws IOException {
        System.out.println("\n--- Willkommen zurück! Folgende Benutzer sind verfügbar: ---");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println((i + 1) + ".: " + userList.get(i).name);
        }
        System.out.println("\nGib die Nummer des Benutzers ein oder schreibe 'New game', um einen neuen anzulegen.");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("New game")) {
            UserManagment.addUser();
        } else {
            System.out.print("Placeholder");
        }
    }
}