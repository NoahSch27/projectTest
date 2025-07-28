// C:/Users/Orcag/IdeaProjects/projectTest/src/UserManagment.java

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManagment {

    // Die User-Klasse ist hier gut aufgehoben.
    public static class User {
        public String name;
        public boolean firstStart;
        public int MaxHealth;
        public int currentHealth;
        public int hunger;
        public int thirst;
        public int currentDay;
        public int hour;

        // Ein leerer Konstruktor ist für Jackson (JSON-Bibliothek) wichtig.
        public User() {}

        public User(String name) {
            this.name = name;
            this.firstStart = true;
            this.MaxHealth = 0;
            this.currentHealth = 0;
            this.hunger = 0;
            this.thirst = 0;
            this.currentDay = 0;
            this.hour = 0;
        }

        // Eine toString()-Methode ist nützlich für Debugging.
        @Override
        public String toString() {
            return "User{name='" + name + "', firstStart=" + firstStart + "}";
        }
    }

    // --- Zentrale Verwaltung der Benutzerdaten ---

    private static final String DATA_FILE = "userData";
    private static final ObjectMapper mapper = new ObjectMapper();

    // Die userList gehört jetzt der Klasse und ist privat.
    private static List<User> userList = new ArrayList<>();

    // Statischer Initializer: Konfiguriert den Mapper einmal beim Laden der Klasse.
    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Lädt die Benutzerliste aus der Datei in das statische Feld userList.
     * Sollte am Anfang des Programms einmal aufgerufen werden.
     */
    public static void loadUsers() {
        File dataFile = new File(DATA_FILE);
        if (dataFile.exists() && dataFile.length() > 2) { // > 2 um leere JSON-Arrays "[]" zu ignorieren
            try {
                userList = mapper.readValue(dataFile, new TypeReference<List<User>>() {});
                System.out.println("✅ Benutzerdatei gefunden und " + userList.size() + " Benutzer geladen.");
            } catch (IOException e) {
                System.out.println("⚠️ Fehler beim Lesen der Datei. Starte mit leerer Liste.");
                userList = new ArrayList<>(); // Sicherstellen, dass die Liste leer und nicht null ist.
            }
        } else {
            System.out.println("ℹ️ Keine Benutzerdatei gefunden. Starte mit einer leeren Liste.");
        }
    }

    /**
     * Speichert die aktuelle userList in die Datei.
     */
    public static void saveUsers() {
        try {
            mapper.writeValue(new File(DATA_FILE), userList);
            System.out.println("✅ Benutzerliste erfolgreich gespeichert.");
        } catch (IOException e) {
            System.out.println("⚠️ Fehler beim Speichern der Benutzerliste: " + e.getMessage());
        }
    }

    /**
     * Fragt nach einem neuen Benutzernamen, fügt ihn zur Liste hinzu und speichert.
     */
    public static void addUser() {
        System.out.println("\n--- Neuen Benutzer anlegen ---");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Gib den Namen für den neuen Benutzer ein: ");
        String userName = scanner.nextLine();

        // Logik zur Überprüfung, ob der Benutzer bereits existiert (optional, aber empfohlen)
        if (userName != null && !userName.trim().isEmpty()) {
            User newUser = new User(userName);
            userList.add(newUser);
            saveUsers(); // Die zentrale Speicherfunktion aufrufen
            System.out.println("Benutzer '" + userName + "' wurde erfolgreich angelegt!");
        } else {
            System.out.println("⚠️ Ungültiger Name. Der Benutzer wurde nicht erstellt.");
        }
    }

    /**
     * Gibt eine Kopie der Benutzerliste zurück.
     * @return Die aktuelle Liste der Benutzer.
     */
    public static List<User> getUserList() {
        return userList;
    }
}